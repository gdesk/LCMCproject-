grammar FOOL;

@header {
	import java.util.HashMap;
	import java.util.HashSet;
	import ast.*;
	import ast.value.*;
	import ast.exp.*;
	import ast.type.*;
	import ast.prog.*;
	import ast.term.*;
	import ast.factor.*;
	import lib.*;
}

@parser::members {
	private int nestingLevel = 0;
	private int globalOffset = -2;
	/* Array di tabelle dove l'indice dell'array è il livello sintattico, ossia il livello di scope, indice 0 = dichiarazioni globali, indice 1 = dichiarazioni locali (mappano identificatori con i valori) */
	ArrayList<HashMap<String,STentry>> symTable = new ArrayList<HashMap<String,STentry>>();
	/* Il livello dell'ambiente con dichiarazioni più esterne è 0 (nelle slide è 1); il fronte della lista di tabelle è "symTable.get(nestingLevel)" */
	HashMap<String, HashMap<String,STentry>> classTable = new HashMap<String, HashMap<String,STentry>>(); 
}

@lexer::members {
	int lexicalErrors=0;
}


/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

prog returns [Node ast]:

	{
		HashMap<String,STentry> hm = new HashMap<String,STentry> ();
		symTable.add(hm);

		/* Usiamo dei flag per memorizzare quali dichiarazioni ci saranno dentro al LET: cllist, declist o entrambe */
		boolean clFlag = false;
		boolean decFlag = false;
	}
	(
		e=exp
		{
			$ast = new ProgNode($e.ast);
		}
		| LET
		(
			c=cllist  /* ricorda che se hai dichiarazione di classi (cllist) devi dichiararle tutte subito */
			{
				clFlag = true;
			}
			(
				d=declist
				{
					decFlag = true;
				}
			)?
			| d=declist
			{
				decFlag = true;
			}
		)
		IN e=exp
		{
			/* Inseriamo lista vuota dove necessario */
			if (clFlag && decFlag) {
				$ast = new ProgLetInNode($c.astlist, $d.astlist, $e.ast);
			} else if (clFlag) {
				$ast = new ProgLetInNode($c.astlist, new ArrayList<DecNode>(), $e.ast);
			} else { /* if (decFlag) { */
				$ast = new ProgLetInNode(new ArrayList<DecNode>(), $d.astlist, $e.ast);
			}
		}
	)
	{
		symTable.remove(nestingLevel);
	}
	SEMIC
;


/*-----------------------------CLLIST-----------------------------*/

cllist returns  [ArrayList<DecNode> astlist]
:
	{
		$astlist = new ArrayList<DecNode>();
		HashSet<String> localVariable;
		STentry classEntry;
		STentry fieldEntry;
		STentry methodEntry;
	}
	
	( CLASS cid=ID 
		{
			localVariable = new HashSet<>();
			int fieldoffset = -1;
			int methodoffset = 0;
			int varoffset = -2;		
			ClassNode classNode = new ClassNode($cid.text);
			ClassTypeNode classType = new ClassTypeNode(new ArrayList<FieldNode>(), new ArrayList< MethodNode>());
			$astlist.add(classNode);
			HashMap<String,STentry> hm = symTable.get(0);  /* hm sara' sempre a nesting level 0 per le definizioni di classi */
			classEntry = new STentry(nestingLevel, classType, globalOffset--);
			if ( hm.put($cid.text, classEntry) != null ) {
				System.out.println("Class id "+$cid.text+" at line "+$cid.line+" already declared");
				System.exit(0);
			}
			HashMap<String,STentry> vhm = new HashMap<String,STentry>(); /*virtual table nuova*/
		}
		(EXTENDS eid=ID
			{
				STentry superEntry = symTable.get(0).get($eid.text);
				if (classTable.get($eid.text) == null || superEntry == null) {  /* ID2 deve essere in Class Table */
					System.out.println("Class "+ $cid.text +" extends from nonexisting "+$eid.text+ " class");
					System.exit(0);
				}
				classNode.setSuperEntry(superEntry);
				FOOLlib.addSuperType($cid.text, $eid.text);
				
				ClassTypeNode superType = (ClassTypeNode) superEntry.getType();
				classType = new ClassTypeNode(superType.getFields(), superType.getMethods());
				vhm = (HashMap<String,STentry>) classTable.get($eid.text);  /* prendo la virtual table della classe che abbiamo esteso e la clono */
				fieldoffset = - classType.getFields().size() - 1;                    /* aggiorno offset per i campi nel caso stia ereditando */
				methodoffset = classType.getMethods().size(); 
			}
			
			)?
			{
				symTable.add(vhm);             /* aggiungiamo virtual table alla Symbol Table */
				classTable.put($cid.text, vhm);  /* aggiungiamo virtual table alla Class Table */
				classEntry.addType(classType);       /* aggiungiamo il ClassTypeNode alla STentry della classe (dentro al nesting level 0 della Symbol Table) */
				classNode.setSymType(classType);
				nestingLevel++;    
			}			
			 LPAR (fid=ID COLON ft=type
			 	{
			 	
			 		if(localVariable.contains($fid.text)) {
		 	  			System.out.println("Field" + $fid.text + " at line " + $fid.line + " already created in localVariable(HashSet<String>).");
						System.exit(0);
	 	  			}
	 	  			localVariable.add($fid.text); 
	 	  			
	 	  			FieldNode field = new FieldNode($fid.text, $ft.ast);
	 	  			
	 	  			if(vhm.get($fid.text) != null) {
	 	  			
						if(vhm.get($fid.text).isMethod()) {
							System.out.println("Field id "+$fid.text+" cannot override a method");
							System.exit(0); 
				 		}
				 		
				 		/* override */
				 		fieldEntry = new STentry(nestingLevel, $ft.ast, vhm.get($fid.text).getOffset());
				 		/* Aggiornamento classTypeNode*/
				 		int index = - fieldEntry.getOffset() - 1;
						classType.setField(index, field);  
			 		}else{
			 			/* non override*/
			 			fieldEntry = new STentry(nestingLevel, $ft.ast, fieldoffset--);
			 			classType.addField(field);
			 		}
			 		vhm.put($fid.text, fieldEntry);                         /* aggiornamento Virtual Table */
					field.setOffset(fieldEntry.getOffset());          /* Ottimizzazione typehecking parametri: ci salviamo l'offset */   
			 
			 	}
			 	(COMMA fid1=ID COLON ft1=type
			 		{
			 		if(localVariable.contains($fid1.text)) {
		 	  			System.out.println("Field" + $fid1.text + " at line " + $fid1.line + " already created in localVariable(HashSet<String>).");
						System.exit(0);
	 	  			}
	 	  			localVariable.add($fid1.text); 
	 	  			
	 	  			FieldNode field1 = new FieldNode($fid1.text, $ft1.ast);
	 	  			
	 	  			if(vhm.get($fid1.text) != null) {
						if(vhm.get($fid1.text).isMethod()) {
							System.out.println("Field id "+$fid1.text+" cannot override a method");
							System.exit(0); 
				 		}
				 		
				 		/* override */
				 		
				 		fieldEntry = new STentry(nestingLevel, $ft1.ast, vhm.get($fid1.text).getOffset());
				 		/* Aggiornamento classTypeNode*/
				 		int index1 = - fieldEntry.getOffset() - 1;
						classType.setField(index1, field1);  
			 		}else{
			 			/* non override*/
			 			fieldEntry = new STentry(nestingLevel, $ft1.ast, fieldoffset--);
			 			classType.addField(field1);
			 		}
			 		vhm.put($fid1.text, fieldEntry);                         /* aggiornamento Virtual Table */
					field1.setOffset(fieldEntry.getOffset());          /* Ottimizzazione typehecking parametri: ci salviamo l'offset */   
			 	}
			 	)*
				)? RPAR {classNode.addFields(classType.getFields());}   
              CLPAR
                 ( FUN mid=ID COLON mt=type 
                 	{
			 		if(localVariable.contains($mid.text)) {
		 	  			System.out.println("Method" + $mid.text + " at line " + $mid.line + " already created in localVariable(HashSet<String>).");
						System.exit(0);
	 	  			}
	 	  			localVariable.add($mid.text); 
	 	  			
	 	  			MethodNode method = new MethodNode($mid.text, $mt.ast);
	 	  			
	 	  			if(vhm.get($mid.text) != null) {
						if(!vhm.get($mid.text).isMethod()) {
							System.out.println("Method id "+$mid.text+" cannot override a method");
							System.exit(0); 
				 		}
				 		
				 		/* override */
				 		
				 		methodEntry = new STentry(nestingLevel, new ArrowTypeNode(new ArrayList<Node>(), $mt.ast ), vhm.get($mid.text).getOffset());
				 		methodEntry.setIsMethod();
				 		/* Aggiornamento classTypeNode*/
				 		int index = methodEntry.getOffset();
						classType.setMethod(index, method);  
			 		}else{
			 			/* non override*/
			 			methodEntry = new STentry(nestingLevel, new ArrowTypeNode(new ArrayList<Node>(), $mt.ast ),  methodoffset++);
			 			methodEntry.setIsMethod();
			 			classType.addMethod(method);
			 		}
			 		vhm.put($mid.text, methodEntry);                         /* aggiornamento Virtual Table */
					method.setOffset(methodEntry.getOffset());          /* Ottimizzazione typehecking parametri: ci salviamo l'offset */   
                 	HashMap<String,STentry> mhm = new HashMap<String,STentry> (); /* vhmn = 'Virtual Hash Map Nested' */
					symTable.add(mhm);
			 	}
                 	LPAR 
                 	{
                 		ArrayList<ParNode> parlist = new ArrayList<ParNode>();
                 		ArrayList<Node> parlistnode = new ArrayList<Node>();
						int parOffset = 1;
						nestingLevel++;
                 	}
                 		(parid=ID COLON fh=hotype
                 		{
                 			ParNode par = new ParNode($parid.text, $fh.ast);
                 			parlist.add(par);
                 			parlistnode.add(par);
                 			
                 		if(par.getSymType() instanceof ArrowTypeNode) {
							parOffset++;
						}
                 			
                 			if(mhm.put($parid.text, new STentry(nestingLevel, $fh.ast, parOffset++)) != null){
								System.out.println("Par ID: " + $parid.text + " at line " + $parid.line + " already declared");
								System.exit(0);
	                 		}
					}
                 		 (COMMA parid1=ID COLON fh1=hotype
                 	   	{
                 	   		ParNode par1 = new ParNode($parid1.text, $fh1.ast);
                 			parlist.add(par1);
                 			parlistnode.add(par1);              		
                 		if(par1.getSymType() instanceof ArrowTypeNode) {
							parOffset++;
						}
                 			if(mhm.put($parid1.text, new STentry(nestingLevel, $fh1.ast, parOffset++)) != null){
								System.out.println("Par ID: " + $parid1.text + " at line " + $parid1.line + " already declared");
								System.exit(0);
	                 		}
                 	   	}
                 	   )*
                 	 {
                 	 	for(Node p : parlistnode) System.out.println(((ParNode)p).getSymType());
                 	 	
                 	   	method.addParList(parlist);
                 	   	methodEntry.addType(new ArrowTypeNode(parlistnode, $mt.ast));
                 	   }
                 	)? 
                 	RPAR
                 		 {
                 		 	ArrayList<VarNode> varlist = new ArrayList<>();
                 		 }
                 	 (LET 
                 	 	(VAR vid=ID COLON vt=type ASS ex=exp 
	                     	{
	                     	
	                     		VarNode var = new VarNode($vid.text, $vt.ast, $ex.ast);
	                     		varlist.add(var);
								if(mhm.put($vid.text,new STentry(nestingLevel, $vt.ast, varoffset--)) != null) {
									System.out.println("Var id" + $vid.text + " at line " + $vid.line + " already declared.");
									System.exit(0);
	                     		}
	                     	}                    	
	                     		                     	
	                     	SEMIC
	                     )*
	                     {
	                     	method.addVarList(varlist);
	                     } 
	                     IN)? exp1=exp
	                     {
	                     	method.addExp($exp1.ast);
                    		symTable.remove(nestingLevel--);
	                     } 
        	       SEMIC
        	     )*                
              CRPAR
              {	
	         	/* Inserisco i campi e metodi dentro l'istanza di ClassNode, prendendoli dal ClassTypeNode corrente */	
	         	classNode.addMethods(classType.getMethods());
	         	symTable.remove(nestingLevel--);
         }
          )+
        ; 

/*-----------------------------DECLIST-----------------------------*/

declist	returns [ArrayList<DecNode> astlist]:
	{	
		$astlist = new ArrayList<DecNode>();
		int offset = -2;
		if (nestingLevel == 0) {
			offset = globalOffset;
		} 
	}
	( 
		(	VAR i=ID COLON h=hotype ASS e=exp
			{	
				VarNode v = new VarNode($i.text,$h.ast,$e.ast);
				$astlist.add(v);
				HashMap<String,STentry> hm = symTable.get(nestingLevel);
				if(hm.put($i.text, new STentry(nestingLevel,$h.ast,offset--)) != null) {
					System.out.println("Var id" + $i.text + " at line " + $i.line + " already declared.");
					System.exit(0);
				}; 
				/* Offset doppio per tipi funzionali */
				if($h.ast instanceof ArrowTypeNode) offset--; 
			}
		| FUN i=ID COLON t=type 
			{	
				FunNode f = new FunNode($i.text,$t.ast);
				$astlist.add(f);
				HashMap<String,STentry> hm = symTable.get(nestingLevel);
				STentry entry = new STentry(nestingLevel,offset--);
				offset--;
				if(hm.put($i.text, entry) != null) {
					System.out.println("Fun id" + $i.text + " at line " + $i.line + " already declared.");
					System.exit(0);
				}
			}
			LPAR 
			{	
				nestingLevel++;  
				HashMap<String,STentry> hmn = new HashMap<String,STentry>();
				symTable.add(hmn);
				ArrayList<Node> parTypes = new ArrayList<Node>();
				int parOffset = 1;
			}
				(i1=ID COLON fty=hotype
				
					{ 
						parTypes.add($fty.ast);
						ParNode p1 = new ParNode($i1.text,$fty.ast);
						f.addPar(p1);
						/* Offset doppio per tipi funzionali */
						if($fty.ast instanceof ArrowTypeNode) parOffset++;
						if (hmn.put($i1.text, new STentry(nestingLevel,$fty.ast,parOffset++)) != null) {
							System.out.println("Par ID: " + $i1.text + " at line " + $i1.line + " already declared");
							System.exit(0);
						}
					
					}
				(COMMA i2=ID COLON ty=hotype
					{
						parTypes.add($ty.ast);
						ParNode p2 = new ParNode($i2.text,$ty.ast);
						f.addPar(p2);
						/* Offset doppio per tipi funzionali */
						if($ty.ast instanceof ArrowTypeNode) parOffset++;
						if (hmn.put($i2.text, new STentry(nestingLevel,$ty.ast,parOffset++)) != null){
							System.out.println("Par ID: " + $i2.text + " at line " + $i2.line + " already declared");
							System.exit(0);
						}
						
					}
				)*
			)?
			RPAR 
				{ 
					entry.addType(new ArrowTypeNode(parTypes,$t.ast));
					ArrowTypeNode symType = new ArrowTypeNode(parTypes, $t.ast);
					entry.addType(symType);
					f.setSymType(symType);
				}
			(LET d=declist  {f.addDec($d.astlist);} IN)? e=exp
				{	
					f.addBody($e.ast);
					symTable.remove(nestingLevel--); 
				}
		) SEMIC 
			{
			if (nestingLevel == 0) {
				globalOffset = offset;
			}
		}
	)+;
	
hotype returns [Node ast] : 
		  t=type {$ast = $t.ast;}
        | a=arrow {$ast = $a.ast;}
        ;
        
        
arrow returns [Node ast]	: 
				{
					ArrayList<Node> hotypeList = new ArrayList<>();
				}
		  LPAR (h=hotype 
		  		{
		  			hotypeList.add($h.ast);
		  		}
		  	(COMMA h1=hotype
		  		{
		  			hotypeList.add($h1.ast);
		  		}	
		  	)*
		  )? RPAR ARROW t=type		
		  	{
		  		$ast = new ArrowTypeNode(hotypeList, $t.ast);
		  	}
		  ;
		 
type returns [Node ast]	: 
	  	  INT	{$ast = new IntTypeNode();} 
		| BOOL	{$ast = new BoolTypeNode();}
		| id=ID	{$ast = new RefTypeNode($id.text);}
		  ;

exp	returns [Node ast]: t=term {$ast = $t.ast;}
		(  PLUS p=term 	{$ast = new PlusNode($ast,$p.ast);}
		 | MINUS m=term {$ast = new MinusNode($ast, $m.ast);}
		 | OR o=term 	{$ast = new OrNode($ast, $o.ast);}
		)* ;

term returns [Node ast]: f=factor {$ast = $f.ast;} 
		(  TIMES t=factor 	{$ast = new TimesNode($ast,$t.ast);}
		 | DIV d=factor 	{$ast = new DivNode($ast,$d.ast);}
		 | AND a=factor		{$ast = new AndNode($ast,$a.ast);}
		)* ;

factor returns [Node ast] : v=value {$ast = $v.ast;}
		(  EQ e=value {$ast = new EqualNode($ast,$e.ast);}
		 | GE g=value {$ast = new GENode($ast,$g.ast);}
		 | LE l=value {$ast = new LENode($ast,$l.ast);}
		)* ;

value returns [Node ast]	:
	in = INTEGER	{$ast = new IntNode(Integer.parseInt($in.text));}
	| TRUE			{$ast = new BoolNode(true);}
	| FALSE			{$ast = new BoolNode(false);}
	| NULL			{$ast = new EmptyNode();}
	| NEW newid=ID 
	{
			if(!classTable.keySet().contains($newid.text)){
				System.out.println("Class id" + $newid.text + " at line " + $newid.line + " not declared.");
				System.exit(0);
			}
			STentry entry = symTable.get(0).get($newid.text);
			NewNode newNode = new NewNode($newid.text,entry);
		}
	LPAR (e1=exp 
			{
				newNode.addArg($e1.ast);
			} 
			(COMMA e2=exp 
				{
					newNode.addArg($e2.ast);
				}
			)*
		)? 
		{
			$ast= newNode;
			
		}
		RPAR 
	
	| LPAR e = exp RPAR {$ast = $e.ast;}
	| IF e1 = exp THEN CLPAR e2 =exp CRPAR
		ELSE CLPAR e3 = exp CRPAR {$ast = new IfNode($e1.ast,$e2.ast,$e3.ast);}
	| NOT LPAR e=exp {$ast = new NotNode($e.ast);} RPAR
	| PRINT LPAR e=exp RPAR	{$ast = new PrintNode($e.ast);}
	| i=ID
	{
		/* cercare la dichiarazione */
		int j = nestingLevel;
		STentry entry = null;
		while (j>=0 && entry==null)
			entry = (symTable.get(j--)).get($i.text);
		if (entry==null) {
			System.out.println("Id "+$i.text+" at line "+$i.line+" not declared");
			System.exit(0);
		}
		$ast = new IdNode($i.text,entry,nestingLevel);
	}
	(
		LPAR                                                                                              // sto chiamando una funzione "i"
		{
			ArrayList<Node> arglist = new ArrayList<Node>();
		}
		(
			a=exp                                                                                 // primo parametro attuale della funzione
			{
				arglist.add($a.ast);
			}
			( COMMA a=exp                                                         // parametro attuale della funzione successivo al primo
				{
					arglist.add($a.ast);
				}
			)*
		)? RPAR
		{
			$ast= new CallNode($i.text,entry,arglist,nestingLevel);
		}
		| DOT im=ID LPAR                                                                   // sto invocando il medodo "im" sull'oggetto "i"
		{
			if (!(entry.getType() instanceof RefTypeNode)) {
				System.out.println($i.text+" at line "+$i.line+" is not an object.");
				System.exit(0);
			}
			ArrayList<Node> arglist = new ArrayList<Node>();
			String classId = ((RefTypeNode) entry.getType()).getID();
			STentry methodEntry = classTable.get(classId).get($im.text);
			if (methodEntry == null) {
				System.out.println("Method Id "+$im.text+" at line "+$im.line+" not declared");
				System.exit(0);
			}
		}
		( 
			fe=exp                                                                                    // primo parametro attuale del metodo
			{
				arglist.add($fe.ast);
			}
			(
				COMMA e=exp                                                             // parametro attuale del metodo successivo al primo
				{
					arglist.add($e.ast);
				}
			)*
		)?
		RPAR
		{
			ClassCallNode clCallNode = new ClassCallNode($i.text, $im.text, entry, methodEntry, nestingLevel);
			clCallNode.addArgs(arglist);
			$ast = clCallNode;
		}
	)?;
	
	
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
PLUS  	: '+' ;
MINUS   : '-' ;
TIMES   : '*' ;
DIV 	: '/' ;
LPAR	: '(' ;
RPAR	: ')' ;
CLPAR	: '{' ;
CRPAR	: '}' ;
SEMIC 	: ';' ;
COLON   : ':' ; 
COMMA	: ',' ;
DOT	    : '.' ;
OR	    : '||' ;
AND	    : '&&' ;
NOT	    : '!' ;
GE	    : '>=' ;
LE	    : '<=' ;
EQ	    : '==' ;	
ASS	    : '=' ;
TRUE	: 'true' ;
FALSE	: 'false' ;
IF	    : 'if' ;
THEN	: 'then';
ELSE	: 'else' ;
PRINT	: 'print' ;
LET     : 'let' ;	
IN      : 'in' ;	
VAR     : 'var' ;
FUN	    : 'fun' ; 
CLASS	: 'class' ; 
EXTENDS : 'extends' ;	
NEW 	: 'new' ;	
NULL    : 'null' ;	  
INT	    : 'int' ;
BOOL	: 'bool' ;
ARROW   : '->' ; 	
INTEGER : '0' | ('-')?(('1'..'9')('0'..'9')*) ; 


ID		: ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')* ;

WHITESP	: (' '|'\t'|'\n'|'\r')+ -> channel(HIDDEN) ;

COMMENT	: '/*' (.)*? '*/' -> channel(HIDDEN) ;

ERR		: . { System.out.println("Invalid char: "+ getText()); lexicalErrors++; } -> channel(HIDDEN);