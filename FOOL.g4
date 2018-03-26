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
	{	HashMap<String,STentry> hm = new HashMap<String,STentry> ();
		symTable.add(hm);
		boolean isCl = false;
		boolean isDec = false;
		boolean isOnlyDec = false;
	}
	( 
		e=exp {
			$ast = new ProgNode($e.ast);
		}
	| LET (c=cllist {isCl = true;}
		 (cd=declist {isDec = true;})? | d=declist {isOnlyDec = true;}
	) IN e=exp 
		{ 
			if(isOnlyDec){
				$ast = new ProgLetInNode($d.astlist,$e.ast);
			}
			if(isCl && isDec){
				$ast = new ProgLetInNode($c.astlist, $cd.astlist, $e.ast);
			}
			if(isCl && !isDec){
				$ast = new ProgLetInNode($c.astlist, new ArrayList<DecNode>(), $e.ast);
			}
				
		}
	) 
	{	
		
		isCl = false;
		isDec = false;
		isOnlyDec = false;
	}  SEMIC {symTable.remove(nestingLevel--); /* A che serve? */};


/*-----------------------------CLLIST-----------------------------*/

cllist returns [ArrayList<ClassNode> astlist]:
	{	
		$astlist = new ArrayList<ClassNode>();
		/* Indice di convenzione di inizio  per le dichiarazioni delle classi (che viene decrementato) */
		int offset = -2; 
		boolean isExtends=false;
		/* Variabile locale che tiene contro della ridefinizione erronea di cammpi e metodi (ottimizzazione) */
		HashSet<String> localVariable; 
	}
		
	 ( CLASS ic=ID 
	 		{
	 			ClassNode classNode = new ClassNode($ic.text);	
	 		 	offset--; 	
	 		 	localVariable = new HashSet<String>();
	 		}
	 	(EXTENDS ic1=ID 
	 		{
	 			isExtends = true;
	 			FOOLlib.addSuperType($ic.text, $ic1.text);
	 		}
	 	)? 
	 	{
	 		if(isExtends == false){
	 			HashMap<String,STentry> sym = symTable.get(nestingLevel);
	 			ClassTypeNode symType = new ClassTypeNode(new ArrayList<FieldNode>(),new ArrayList<MethodNode>());
	 			STentry cstentry = new STentry(nestingLevel, symType, offset);
	 		 	if(sym.put($ic.text,cstentry) != null) {
					System.out.println("Class id" + $ic.text + " at line " + $ic.line + " already created.");
					System.exit(0);
				}; 
				classNode.setSymType(symType);
				nestingLevel++;
				HashMap<String, STentry> virtualTable = new HashMap<String, STentry>();
				symTable.add(nestingLevel,virtualTable); 
				if(classTable.put($ic.text, virtualTable) != null) {
                   System.out.println("Class id  "+$ic.text+" at line "+$ic.line+" already declared");
                   System.exit(0); 
                } 
	 		 	
	 		} else {
	 			/* Controllo che la classe ereditata sia stata dichiarata */
	 			if(!classTable.keySet().contains($ic1.text)){
	 			 	System.out.println("Extended class id" + $ic1.text + " at line " + $ic1.line + " never declarated.");
					System.exit(0);
	 			 }
		 		HashMap<String,STentry> sym = symTable.get(nestingLevel);
		 		/* STetry della classe ereditata*/
		 		STentry erhm1 = sym.get($ic1.text);
	 			/* Setto STentry della classe ereditata */
		 		classNode.setSuperEntry(erhm1);
		 		ClassTypeNode erClassTypeNode = (ClassTypeNode) erhm1.getType();
		 		ClassTypeNode symType = new ClassTypeNode(erClassTypeNode.getFields(),erClassTypeNode.getMethods()); 	
		 		/* STentry della classe corrente */
		 		STentry cstentry1 = new STentry(nestingLevel, symType ,offset);
		 		if(sym.put($ic.text, cstentry1) != null) {
					System.out.println("Class id" + $ic.text + " at line " + $ic.line + " already created.");
					System.exit(0);
				}; 
				classNode.setSymType(symType);
				/* Copio la virtual table della classe ereditata */
				classTable.put($ic.text, classTable.get($ic1.text));
		 		nestingLevel++; 
	 			HashMap<String,STentry> chm = new HashMap<String,STentry>();
				symTable.add(chm);
	 		}
	 	}
	 		
	 		{ClassTypeNode cTypeNode = (ClassTypeNode)symTable.get(nestingLevel-1).get($ic.text).getType();}
	 			
	 	  LPAR (campo=ID COLON t=type
	 	  	{
	 	  		/* Offset dei campi settato a 0, perchè decrementato al primo utilizzo. (Primo campo offset = -1) */
	 	  		int offsetCampo=0;
	 	  		if(isExtends) {
	 	  			offsetCampo = -cTypeNode.getFields().size()-1;
	 	  		}
	 	  		
	 	  		/* Controllo che il field non è presente all'interno dei localVariable */
	 	  		if(localVariable.contains($campo.text)) {
	 	  			System.out.println("Field" + $campo.text + " at line " + $campo.line + " already created in localVariable(HashSet<String>).");
					System.exit(0);
	 	  		}
	 	  		localVariable.add($campo.text);
	 	  		
	 	  		FieldNode field = new FieldNode($campo.text,$t.ast);
	 	  		STentry entry = new STentry(nestingLevel, $t.ast, offsetCampo--);
	 	  		symTable.get(nestingLevel).put($campo.text,entry);
	 	  		field.setOffset(offsetCampo); 		 
	 	  		if( classTable.get($ic.text).put($campo.text,entry) != null) {
	 	  			/* Overriding: sostituisco il nuovo STentry alla vecchia STentry, preservando l'offset */
	 	  			STentry oldEntry = classTable.get($ic.text).get($campo.text);
	 	  			oldEntry.addType($t.ast);
	 	  			oldEntry.setNestingLevel(nestingLevel);
	 	  		}
	 	  		if(!(cTypeNode.getIDs().contains($campo.text))){
	 	  			cTypeNode.addField(field);
	 	  		}
	 	  		 		
	 	  	}
	 	  	(COMMA campo1=ID COLON t1=type
	 	  		{ 
	 	  			/* Controllo che il campo non è presente all'interno dei localVariable */
	 	  			if(localVariable.contains($campo1.text)) {
	 	  				System.out.println("Field" + $campo1.text + " at line " + $campo1.line + " already created in localVariable(HashSet<String>).");
						System.exit(0);
	 	  			}
	 	  			localVariable.add($campo1.text);
	 	  			
	 	  			FieldNode field1 = new FieldNode($campo1.text,$t1.ast);
	 	  			STentry entry1 = new STentry(nestingLevel, $t1.ast, offsetCampo--);
	 	  			symTable.get(nestingLevel).put($campo1.text,entry1);
	 	  			field1.setOffset(offsetCampo);
		 	  		if( classTable.get($ic.text).put($campo1.text,entry1) != null) {
		 	  			/* Overriding: sostituisco il nuovo STentry alla vecchia STentry, preservando l'offset */
		 	  			STentry oldEntry = classTable.get($ic.text).get($campo1.text);
		 	  			oldEntry.addType($t1.ast);
		 	  			oldEntry.setNestingLevel(nestingLevel);
	 	  			}
	 	  			if(!(cTypeNode.getIDs().contains($campo1.text))){
	 	  				cTypeNode.addField(field1);
	 	  			}
	 	  		}
	 	  	)* 
	 	  	
	 	  	{
	 	  		/* Aggiornamento classTypeNode: posizione = -offset-1 */
	 	  		/*cTypeNode.refreshFields(); */
	 	  	}
	 	  	
	 	  )? RPAR    
              CLPAR
              { 
	              	int methodOffset = 0;
	              	if(isExtends) {
		 	  			methodOffset = (cTypeNode.getMethods().size());
		 	  		}
              }
                 ( FUN fid=ID COLON ret=type 
                {
	 	  			/* Controllo che il metodo non è presente all'interno dei localVariable */
	 	  			if(localVariable.contains($fid.text)) {
	 	  				System.out.println("Method" + $fid.text + " at line " + $fid.line + " already created in localVariable(HashSet<String>).");
						System.exit(0);
	 	  			}
	 	  			localVariable.add($fid.text);
	 	  			
                	MethodNode method = new MethodNode($fid.text, $ret.ast);
                	method.setOffset(methodOffset);
                	cTypeNode.addMethod(method);
                	STentry mentry = new STentry(nestingLevel, $ret.ast, methodOffset);
                	method.setSymType($ret.ast);
                	mentry.setIsMethod();
                	
                	HashMap<String, STentry> msym = symTable.get(nestingLevel);
                	msym.put($fid.text, mentry);
                	       	 
                	if(classTable.get($ic.text).put($fid.text, mentry) != null ) {
                	 	/* Overriding: sostituisco il nuovo STentry alla vecchia STentry, preservando l'offset */
                	 	STentry oldEntry = classTable.get($ic.text).get($fid.text);
                	 	oldEntry.addType($ret.ast);
                	 	oldEntry.setNestingLevel(nestingLevel);
                	}
                	
                	methodOffset++;
					/* Aumento il livello perchè sono all'interno di una metodo (anche i parametri passati alla funzione rientrano nel livello interno) */
					nestingLevel++;
					HashMap<String,STentry> mhm = new HashMap<String,STentry>();
					symTable.add(mhm);
                	 
                }
                 	LPAR (parid=ID COLON fh=hotype
                 		{
                 			int parOffset = 1;

                 			ArrayList<ParNode> parlist = new ArrayList<>();
                 			ParNode par = new ParNode($parid.text, $fh.ast);
                 			parlist.add(par);
                 			if($fh.ast instanceof ArrowTypeNode) parOffset++; 
                 			if(mhm.put($parid.text, new STentry(nestingLevel, $fh.ast, parOffset++)) != null){
								System.out.println("Par ID: " + $parid.text + " at line " + $parid.line + " already declared");
								System.exit(0);
	                 		}
                 		}
                 	   (COMMA parid1=ID COLON fh1=hotype
                 	   	{
                 	   		ParNode par1 = new ParNode($parid1.text, $fh1.ast);
                 			parlist.add(par1);
                 			if($fh1.ast instanceof ArrowTypeNode) parOffset++; 
                 			if(mhm.put($parid1.text, new STentry(nestingLevel, $fh1.ast, parOffset++)) != null){
								System.out.println("Par ID: " + $parid1.text + " at line " + $parid1.line + " already declared");
								System.exit(0);
	                 		}
                 	   	}
                 	   )*
                 	   {
                 	   	method.addParList(parlist);
                 	   }
                 	)? 
                 	RPAR
                 		 {
                 		 	/* Aggiornamento classTypeNode: posizione = offset */
                 		 	/*cTypeNode.refreshMethods();*/
                 		 	ArrayList<VarNode> varlist = new ArrayList<>();
                 		 }
                 	 (LET 
                 	 	(VAR vid=ID COLON vt=type ASS ex=exp 
	                     	{
	                     		VarNode var = new VarNode($vid.text, $vt.ast, $ex.ast);
	                     		varlist.add(var);
								if(mhm.put($vid.text,new STentry(nestingLevel, $vt.ast, offset--)) != null) {
									System.out.println("Var id" + $vid.text + " at line " + $vid.line + " already declared.");
									System.exit(0);
	                     		}
	                     		if($vt.ast instanceof ArrowTypeNode) offset--;
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
         	classNode.addFields(cTypeNode.getFields());
         	classNode.addMethods(cTypeNode.getMethods());
    
         	isExtends = false;
         	$astlist.add(classNode); 
         	symTable.remove(nestingLevel--);
         }
           )+ ; 

/*-----------------------------DECLIST-----------------------------*/

declist	returns [ArrayList<DecNode> astlist]:
	{	
		$astlist = new ArrayList<DecNode>();
		int offset = -2; 
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
				nestingLevel++;  
				HashMap<String,STentry> hmn = new HashMap<String,STentry>();
				symTable.add(hmn);
			}
			LPAR 
			{	
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
				}
			(LET d=declist  {f.addDec($d.astlist);} IN)? e=exp
				{	
					symTable.remove(nestingLevel--); 
					f.addBody($e.ast);
				}
		) SEMIC 
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
	| NEW nid=ID 
	{
			if(!classTable.keySet().contains($nid.text)){
				System.out.println("Class id" + $nid.text + " at line " + $nid.line + " not declared.");
				System.exit(0);
			}
			STentry entry = symTable.get(0).get($nid.text);
			
			System.out.println("size "+ ((ClassTypeNode)entry.getType()).getFields().size());
			for(FieldNode field: ((ClassTypeNode)entry.getType()).getFields()){
			System.out.println(field.getID()+" \n");	
			}
		
			NewNode newNode = new NewNode($nid.text,entry);
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
		{	/* Cerco la dichiarazione dentro la symbol table e il livello di scope da scope corrente fino allo scope globale (level = 0)*/
			int j = nestingLevel;
			STentry entry = null;
			while(j >= 0 && entry == null) {
				entry = symTable.get(j--).get($i.text);
			}
			
			if(entry==null) {
				System.out.println("Var id " + $i.text + " at line " + $i.line + " not declared.");
				System.exit(0);
			}
			/* Inserito il nestinglevel per verifiche sullo scope della variabile */
			$ast = new IdNode($i.text, entry, nestingLevel); 
		}
		( LPAR 
			{ 
				ArrayList<Node> arglist = new ArrayList<Node>();
			}
			( a=exp 
				{
					arglist.add($a.ast);
				}
				( COMMA a1=exp 
					{
						arglist.add($a1.ast);
					}
				)*
			)? RPAR 
			{ 
				$ast = new CallNode($i.text, entry, arglist, nestingLevel);
			} 
		
		| DOT mid=ID
			{ 
			  	if (!(entry.getType() instanceof RefTypeNode)) {
               		System.out.println("id " + $i.text + " is not a objects ");
               		System.exit(0);
             	}
			  	
			  	ArrayList<Node> argslist = new ArrayList<>();           
				RefTypeNode ref = (RefTypeNode)entry.getType();
             	String objectID = ref.getID();
				if(!classTable.get(objectID).containsKey($mid.text)){
					System.out.println("Method id" + $mid.text + " at line " + $i.line + " not declared.");
					System.exit(0);
				}
				STentry classEntry = symTable.get(0).get(ref.getID());
				STentry methodEntry = classTable.get(ref.getID()).get($mid.text);
							
			}
			 LPAR
			 ( e=exp 
			 	{ 
			 		argslist.add($e.ast);
			 	} 
				 ( COMMA e1=exp 	
				 	{ 
				 		argslist.add($e1.ast);
				 	}
				 )* 
			 
			   )? RPAR 
			     {
			     	ClassCallNode clCallNode = new ClassCallNode(ref.getID(), $mid.text, classEntry, methodEntry, nestingLevel);
			     	clCallNode.addArgs(argslist);
			     	$ast = clCallNode;
			     }
			  
		  )?
		  ;

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