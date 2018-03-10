grammar FOOL;

@header {
	import java.util.HashMap;
	import ast.*;
	import ast.value.*;
	import ast.exp.*;
	import ast.type.*;
	import ast.hotype.*;
	import ast.prog.*;
	import ast.term.*;
	import ast.declist.*;
	import ast.cllist.*;
	import ast.factor.*;
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
	}
	( 
		e=exp {
			$ast = new ProgNode($e.ast);
		}
	|  LET (c=cllist (cd=declist)? | d=declist
	) IN e=exp 
		{ 
			if($c.astlist == null){
				$ast = new ProgLetInNode($d.astlist,$e.ast);
			}else{
				$ast = new ProgLetInNode($c.astlist, $cd.astlist, $e.ast);
			}
			
		}
	) 
	{	
		symTable.remove(nestingLevel);
	}  SEMIC ;

 /* lista delle classi */
cllist returns [ArrayList<DecNode> astlist]:
	{	$astlist = new ArrayList<DecNode>();
		int offset = -2; /* Indice di convenzione di inizio (che viene decrementato) */
		boolean isExtends=false;
	}
		
	 ( CLASS ic=ID 
	 		{ClassNode classNode = new ClassNode($ic.text);	
	 		 offset--; 		
	 		}
	 	(EXTENDS ic1=ID {isExtends = true;}	)? 
	 	{
	 		if(isExtends == false){
	 			HashMap<String,STentry> sym = symTable.get(nestingLevel);
	 			STentry cstentry = new STentry(nestingLevel, new ClassTypeNode(new ArrayList<FieldNode>(),new ArrayList<MethodNode>()),offset);
	 		 	if(sym.put($ic.text,cstentry) != null) {
					System.out.println("Class id" + $ic.text + " at line " + $ic.line + " already created.");
					System.exit(0);
				}; 
				nestingLevel++;
				HashMap<String, STentry> virtualTable = new HashMap<String, STentry>();
				symTable.add(nestingLevel,virtualTable); /* è vuota */
				  if(classTable.put($ic.text, virtualTable) != null) {
                   System.out.println("Class "+$ic.text+" at line "+$ic.line+" already declared");
                   System.exit(0); 
                } 
	 		 	
	 		}else{
	 			HashMap<String,STentry> sym = symTable.get(nestingLevel);
	 			STentry erhm1 = sym.get($ic1.text); /*stetry della classe ereditata*/
	 			ClassTypeNode erClassTypeNode = (ClassTypeNode) erhm1.getType();
	 			STentry cstentry1 = new STentry(nestingLevel, new ClassTypeNode(erClassTypeNode.getFields(),erClassTypeNode.getMethods()),offset);/* mappa classe corrente */
	 			if(sym.put($ic.text,cstentry1) != null) {
					System.out.println("Class id" + $ic.text + " at line " + $ic.line + " already created.");
					System.exit(0);
				}; 
				classTable.put($ic1.text, classTable.get($ic1.text)); /* copio vtable della classe ereditata*/
	 			nestingLevel++; /*perchè finita la dichiarazione classe*/
	 		}
	 	}
	 		{ClassTypeNode cTypeNode = (ClassTypeNode)symTable.get(nestingLevel-1).get($ic.text).getType();}
	 	  LPAR (campo=ID COLON t=type
	 	  	{
	 	  		FieldNode field = new FieldNode($campo.text,$t.ast);
	 	  		cTypeNode.addField(field);
	 	  		int offsetCampo=0;
	 	  		if(isExtends){
	 	  			offsetCampo = (cTypeNode.getFields().size()+1)*(-1); /* sistemato offset per ereditarietà */
	 	  		}
	 	  		STentry entry = new STentry(nestingLevel, $t.ast, offsetCampo--);
	 	  		/* inserimento in symbol table */
	 	  		symTable.get(nestingLevel).put($campo.text,entry);
	 	  		/* inserimento in classTable*/
	 	  		if( classTable.get($ic.text).put($campo.text,entry) != null){ /* overriding */
	 	  			STentry oldEntry = classTable.get($ic.text).get($campo.text);
	 	  			oldEntry.addType($t.ast);
	 	  			oldEntry.setNestingLevel(nestingLevel);
	 	  		}	 	  		
	 	  		
	 	  	}
	 	  	(COMMA campo1=ID COLON t1=type
	 	  		{ 
	 	  			FieldNode field1 = new FieldNode($campo1.text,$t1.ast);
	 	  			cTypeNode.addField(field1);	
	 	  			STentry entry1 = new STentry(nestingLevel, $t1.ast, offsetCampo--);
	 	  			/* inserimento in symbol table */
	 	  			symTable.get(nestingLevel).put($campo.text,entry1);
		 	  		/* inserimento in classTable*/
		 	  		if( classTable.get($ic.text).put($campo1.text,entry1) != null){/* overriding */
		 	  			STentry oldEntry = classTable.get($ic.text).get($campo1.text);
		 	  			oldEntry.addType($t1.ast);
		 	  			oldEntry.setNestingLevel(nestingLevel);
	 	  			}
	 	  		}
	 	  	)*
	 	  	
	 	  )? RPAR    
              CLPAR
              {int methodOffset = 0;
              	if(isExtends){
	 	  			methodOffset = (cTypeNode.getMethods().size()); /* sistemato offset per ereditarietà */
	 	  		}
              }
                 ( FUN fid=ID COLON ret=type 
                {
                	 MethodNode method = new MethodNode($fid.text, $ret.ast);
                	 cTypeNode.addMethod(method); /*Ricordati che vanno da m-1 a 0 */
                	 STentry mentry = new STentry(nestingLevel, $ret.ast, methodOffset);
                	 HashMap<String, STentry> msym = symTable.get(nestingLevel);
                	 msym.put($fid.text, mentry);
                	              	 
                	 if(classTable.get($ic.text).put($fid.text, mentry) != null ) {
                	 	STentry oldEntry = classTable.get($ic.text).get($fid.text);
                	 	oldEntry.addType($ret.ast);
                	 	oldEntry.setNestingLevel(nestingLevel);
                	 }
                	 methodOffset++;
                	 /* Entro dentro un nuovo scope e aggiorno con una mappa vuota la symTable. */
					nestingLevel++;  /* Aumento il livello perchè sono all'interno di una metodo (anche i parametri passati alla funzione rientrano nel livello interno)*/
					HashMap<String,STentry> mhm = new HashMap<String,STentry>();
					symTable.add(mhm);
                	 
                }
                 	LPAR {int boffset = -2;}(parid=ID COLON fh=hotype
                 		{
                 			ArrayList<ParNode> parlist = new ArrayList<>();
                 			ParNode par = new ParNode($parid.text, $fh.ast);
                 			parlist.add(par);
                 			if($fh.ast instanceof ArrowTypeNode) boffset++; 
                 			STentry mpentry = new STentry(nestingLevel, $fh.ast, boffset++);
                 			if(mhm.put($parid.text, mpentry) != null){
                 				/* Errore identificatore (parametro) già dichiarato*/
								System.out.println("Par ID: " + $parid.text + " at line " + $parid.line + " already declared");
								System.exit(0);
	                 		}
                 		}
                 	   (COMMA parid1=ID COLON fh1=hotype
                 	   	{
                 	   		ParNode par1 = new ParNode($parid1.text, $fh1.ast);
                 			parlist.add(par1);
                 			if($fh1.ast instanceof ArrowTypeNode) boffset++; 
                 			STentry mpentry1 = new STentry(nestingLevel, $fh1.ast, boffset++);
                 			if(mhm.put($parid1.text, mpentry1) != null){
                 				/* Errore identificatore (parametro) già dichiarato*/
								System.out.println("Par ID: " + $parid1.text + " at line " + $parid1.line + " already declared");
								System.exit(0);
	                 		}
                 	   	}
                 	   )*
                 	   {
                 	   	method.addParList(parlist);
                 	   }
                 	)? RPAR
                 		 {ArrayList<VarNode> varlist = new ArrayList<>();}
                 	 (LET (VAR vid=ID COLON vt=type ASS ex=exp 
	                     	{
	                     		VarNode var = new VarNode($vid.text, $vt.ast, $ex.ast);
	                     		varlist.add(var);
	                     		STentry ventry = new STentry(nestingLevel, $vt.ast, offset--);
	                     		if(mhm.put($vid.text,ventry) != null){
	                     			/*Errore identificatore (variabile) già dichiarata*/
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
	                     	symTable.remove(nestingLevel--);/* Diminuisco nestingLevel perchè esco dallo scope della funzione */
	                     } 
        	       SEMIC
        	     )* 
        	                   
              CRPAR
         {	isExtends = false;
         	/* buttare dentro a classNode tutte le info dalla classTable */
         	STentry classEntry = classTable.get($ic.text).get($ic.text);
         	System.out.println(" classEntri:"+classEntry+" \nclassType??? "+ classEntry.getType());
         	ClassTypeNode classType = (ClassTypeNode) classEntry.getType();
         	
         	classNode.addFields(classType.getFields());
         	classNode.addMethods(classType.getMethods());
         	/* aggiugere il classNode all lista da ritornare   	*/
         	$astlist.add(classNode); 
         	/* diminuire nestingLevel */
         	  	symTable.remove(nestingLevel--);
         }
          )+
          
        ; 

// Lista di dichiarazioni (di variabili o funzioni). La chiusura "+" indica una o più volte.
declist	returns [ArrayList<DecNode> astlist]:
	{	$astlist = new ArrayList<DecNode>();
		int offset = -2; /* Indice di convenzione di inizio (che viene decrementato) */ 
	}
	( 
		(	VAR i=ID COLON h=hotype ASS e=exp
			{	VarNode v = new VarNode($i.text,$h.ast,$e.ast);
				$astlist.add(v);
				HashMap<String,STentry> hm = symTable.get(nestingLevel); /* Tabella del livello corrente (detta tabella del fronte) */
				/* Verificare che nello scope attuale (il fronte della tabella), la variabile sia già stata dichiarata. "put" sostituisce, ma se la chiave era già occupata restituisce la coppia vecchia, altrimenti null.*/ 
				if(hm.put($i.text, new STentry(nestingLevel,$h.ast,offset--)) != null) {
					/*Errore identificatore (variabile) già dichiarata*/
					System.out.println("Var id" + $i.text + " at line " + $i.line + " already declared.");
					System.exit(0);
				}; 
				{if($h.ast instanceof ArrowTypeNode) offset--;} /*offset doppio per tipi funzionali*/
			}
		| FUN i=ID COLON t=type 
			{	
				FunNode f = new FunNode($i.text,$t.ast);
				$astlist.add(f);
				HashMap<String,STentry> hm = symTable.get(nestingLevel);
				/* Verificare che nello scope attuale (il fronte della tabella), la funzione sia già stata dichiarata. "put" sostituisce, ma se la chiave era già occupata restituisce la coppia vecchia, altrimenti null.*/
				STentry entry = new STentry(nestingLevel,offset--);
				offset--;
				if(hm.put($i.text, entry) != null) {
					System.out.println("Fun id" + $i.text + " at line " + $i.line + " already declared.");
					System.exit(0);
				}
				/* Entro dentro un nuovo scope. */
				nestingLevel++;  /* Aumento il livello perchè sono all'interno di una funzione (anche i parametri passati alla funzione rientrano nel livello interno)*/
				HashMap<String,STentry> hmn = new HashMap<String,STentry>();
				symTable.add(hmn);
			}
			LPAR {	ArrayList<Node> parTypes = new ArrayList<Node>();
					int parOffset = 1;
				}
				(i1=ID COLON fty=hotype
					{ /* Creare il ParNode, lo attacco al FunNode invocando addPar, aggiungo una STentry alla hashmap hmn*/
						parTypes.add($fty.ast);
						ParNode p1 = new ParNode($i1.text,$fty.ast);
						f.addPar(p1);
						{if($fty.ast instanceof ArrowTypeNode) parOffset++;}
						if (hmn.put($i1.text, new STentry(nestingLevel,$fty.ast,parOffset++)) != null) {
							/* Errore identificatore (parametro) già dichiarato*/
							System.out.println("Par ID: " + $i1.text + " at line " + $i1.line + " already declared");
							System.exit(0);
						}
						
					}
				(COMMA i2=ID COLON ty=hotype
					{/* Creare il ParNode, lo attacco al FunNode invocando addPar, aggiungo una STentry alla hashmap hmn */
						parTypes.add($ty.ast);
						ParNode p2 = new ParNode($i2.text,$ty.ast);
						f.addPar(p2);
						{if($ty.ast instanceof ArrowTypeNode) parOffset++;}
						if (hmn.put($i2.text, new STentry(nestingLevel,$ty.ast,parOffset++)) != null){
							/* Errore identificatore (parametro) già dichiarato */
							System.out.println("Par ID: " + $i2.text + " at line " + $i2.line + " already declared");
							System.exit(0);
						}
						
					}
				)*
			)?
			RPAR { entry.addType(new ArrowTypeNode(parTypes,$t.ast)); }
			(LET d=declist IN {f.addDec($d.astlist);})? e=exp
				{	
					symTable.remove(nestingLevel--); /* Diminuisco nestingLevel perchè esco dallo scope della funzione */
					f.addBody($e.ast);
				}
		) SEMIC 
	)+;
	
hotype returns [Node ast] : 
		  t=type {$ast = $t.ast;}
        | a=arrow {$ast = $a.ast;}
        ;
        
        
arrow returns [Node ast]	: 
			{ArrayList<Node> hotypeList = new ArrayList<>();}
		  LPAR (h=hotype 
		  	{hotypeList.add($h.ast);}
		  	(COMMA h1=hotype
		  	{hotypeList.add($h1.ast);}	
		  	)*
		  )? RPAR ARROW t=type		
		  	{$ast = new ArrowTypeNode(hotypeList, $t.ast);}
		  ;
		 
type returns [Node ast]	: 
	  	  INT	{$ast = new IntTypeNode();} // Rappresenta l'elemento sintattico del tipo int e non il valore.
		| BOOL	{$ast = new BoolTypeNode();}
		| ID	{$ast = new IdTypeNode();}
		  ;

exp	returns [Node ast]: t=term {$ast = $t.ast;}
		(  PLUS p=term {$ast = new PlusNode($ast,$p.ast);}
		 | MINUS m=term {$ast = new MinusNode($ast, $m.ast);}
		 | OR o=term {$ast = new OrNode($ast, $o.ast);}
		)* ;

term returns [Node ast]: f=factor {$ast = $f.ast;} 
		(  TIMES t=factor {$ast = new TimesNode($ast,$t.ast);}
		 | DIV d=factor {$ast = new DivNode($ast,$d.ast);}
		 | AND a=factor{ $ast = new AndNode($ast,$a.ast);}
		)* ;

factor returns [Node ast] : v=value {$ast = $v.ast;}
		(  EQ e=value {$ast = new EqualNode($ast,$e.ast);}
		 | GE g=value {$ast = new GENode($ast,$g.ast);}
		 | LE l=value {$ast = new LENode($ast,$l.ast);}
		)* ;

value returns [Node ast]	:
	in = INTEGER	{$ast = new IntNode(Integer.parseInt($in.text));}
	| TRUE		{$ast = new BoolNode(true);}
	| FALSE		{$ast = new BoolNode(false);}
	| NULL		{$ast = new EmptyNode();}
	| NEW ID LPAR (exp (COMMA exp)* )? RPAR
	| LPAR e = exp RPAR {$ast = $e.ast;}  // Le parentesi lasciano l'albero inalterato.
	| IF e1 = exp THEN CLPAR e2 =exp CRPAR
		ELSE CLPAR e3 = exp CRPAR {$ast = new IfNode($e1.ast,$e2.ast,$e3.ast);}
	| NOT LPAR e=exp {$ast = new NotNode($e.ast);} RPAR
	| PRINT LPAR e=exp RPAR	{$ast = new PrintNode($e.ast);}
	| i=ID // Identificatore di una variabile o funzione. Combinazioni possibili ID (variabile) 
		{	/* Cerco la dichiarazione dentro la symbol table e il livello di scope corrente fino allo scope globale (level = 0)*/
			int j = nestingLevel;
			STentry entry = null;
			while(j>=0 && entry==null) {
				entry = symTable.get(j--).get($i.text);
			}
			if(entry==null) { /* Dichiarazione non presente nella symbol table quindi variabile non dichiarata*/
				System.out.println("Id" + $i.text + " at line " + $i.line + " not declared.");
				System.exit(0);
			}
			$ast = new IdNode($i.text, entry, nestingLevel); /* Inserito il nestinglevel per verifiche sullo scope della variabile */
		}
		// Supporto alle chiamate a funzioni. Combinazioni possibili ID() (funzione vuota) - ID(exp) (funzione con variabili)
		( LPAR { ArrayList<Node> arglist = new ArrayList<Node>(); }
			( a=exp { arglist.add($a.ast); } //tutte volte che incontro un'espressione l'aggiungo alla lista dei parametri
			(COMMA a1=exp { arglist.add($a1.ast); }
			)*
		)? RPAR { $ast = new CallNode($i.text,entry,arglist,nestingLevel); } // Inserito il nestinglevel per verifiche sullo scope della funzione chiamata
	// | ID ( LPAR (exp (COMMA exp)* )? RPAR già fatto ELIMINARE
	
	| DOT ID LPAR (exp (COMMA exp)* )? RPAR 
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