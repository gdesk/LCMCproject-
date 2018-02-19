grammar FOOL;

@header {
	import java.util.HashMap;
	import ast.*;
}

@parser::members {
	private int nestingLevel = 0;
	/* Array di tabelle dove l'indice dell'array è il livello sintattico, ossia il livello di scope, indice 0 = dichiarazioni globali, indice 1 = dichiarazioni locali (mappano identificatori con i valori) */
	ArrayList<HashMap<String,STentry>> symTable = new ArrayList<HashMap<String,STentry>>();
	/* Il livello dell'ambiente con dichiarazioni più esterne è 0 (nelle slide è 1); il fronte della lista di tabelle è "symTable.get(nestingLevel)" */
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
	|  LET (cllist (declist)? | d=declist) IN e=exp 
		{ 
			$ast = new ProgLetInNode($d.astlist,$e.ast);
		}
	) 
	{	
		symTable.remove(nestingLevel);
	}  SEMIC ;

//lista di funzioni 
cllist  : ( CLASS ID (EXTENDS ID)? LPAR (ID COLON type (COMMA ID COLON type)* )? RPAR    
              CLPAR
                 ( FUN ID COLON type LPAR (ID COLON hotype (COMMA ID COLON hotype)* )? RPAR
	                     (LET (VAR ID COLON type ASS exp SEMIC)* IN)? exp 
        	       SEMIC
        	     )*                
              CRPAR
          )+
        ; 


// Lista di dichiarazioni (di variabili o funzioni). La chiusura "+" indica una o più volte.
declist	returns [ArrayList<Node> astlist]:
	{	$astlist = new ArrayList<Node>();
		int offset = -2; /* Indice di convenzione di inizio (che viene decrementato) */ 
	}
	( 
		(	VAR i=ID COLON t=type ASS e=exp
			{	VarNode v = new VarNode($i.text,$t.ast,$e.ast);
				$astlist.add(v);
				HashMap<String,STentry> hm = symTable.get(nestingLevel); /* Tabella del livello corrente (detta tabella del fronte) */
				/* Verificare che nello scope attuale (il fronte della tabella), la variabile sia già stata dichiarata. "put" sostituisce, ma se la chiave era già occupata restituisce la coppia vecchia, altrimenti null.*/ 
				if(hm.put($i.text, new STentry(nestingLevel,$t.ast,offset--)) != null) {
					/*Errore identificatore (variabile) già dichiarata*/
					System.out.println("Var id" + $i.text + " at line " + $i.line + " already declared.");
					System.exit(0);
				};
			}
		| FUN i=ID COLON t=type 
			{	
				FunNode f = new FunNode($i.text,$t.ast);
				$astlist.add(f);
				HashMap<String,STentry> hm = symTable.get(nestingLevel);
				/* Verificare che nello scope attuale (il fronte della tabella), la funzione sia già stata dichiarata. "put" sostituisce, ma se la chiave era già occupata restituisce la coppia vecchia, altrimenti null.*/
				STentry entry = new STentry(nestingLevel,offset--);
				if(hm.put($i.text, entry) != null) {
					System.out.println("Fun id" + $i.text + " at line " + $i.line + " already declared.");
					System.exit(0);
				};
				/* Entro dentro un nuovo scope. */
				nestingLevel++;  /* Aumento il livello perchè sono all'interno di una funzione (anche i parametri passati alla funzione rientrano nel livello interno)*/
				HashMap<String,STentry> hmn = new HashMap<String,STentry>();
				symTable.add(hmn);
			}
			LPAR {	ArrayList<Node> parTypes = new ArrayList<Node>();
					int parOffset = 1;
				}
				(i=ID COLON fty=type
					{ /* Creare il ParNode, lo attacco al FunNode invocando addPar, aggiungo una STentry alla hashmap hmn*/
						parTypes.add($fty.ast);
						ParNode p1 = new ParNode($i.text,$fty.ast);
						f.addPar(p1);
						if (hmn.put($i.text, new STentry(nestingLevel,$fty.ast,parOffset++)) != null) {
							/* Errore identificatore (parametro) già dichiarato*/
							System.out.println("Par ID: " + $i.text + " at line " + $i.line + " already declared");
							System.exit(0);
						}
					}
				(COMMA i=ID COLON ty=type
					{/* Creare il ParNode, lo attacco al FunNode invocando addPar, aggiungo una STentry alla hashmap hmn */
						parTypes.add($ty.ast);
						ParNode p2 = new ParNode($i.text,$ty.ast);
						f.addPar(p2);
						if (hmn.put($i.text, new STentry(nestingLevel,$ty.ast,parOffset++)) != null){
							/* Errore identificatore (parametro) già dichiarato */
							System.out.println("Par ID: " + $i.text + " at line " + $i.line + " already declared");
							System.exit(0);
						}
					}
				)*
			)?
			RPAR { entry.addType(new ArrowTypeNode(parTypes,$t.ast)); }
			(LET d=declist IN {f.addDec($d.astlist);})? e=exp
				{	f.addBody($e.ast);
					symTable.remove(nestingLevel--); /* Diminuisco nestingLevel perchè esco dallo scope della funzione */
				}
		) SEMIC 
	)+;
	
hotype returns [Node ast] : 
		  t=type {$ast = $t.ast;}
        | a=arrow {$ast = $a.ast;}
        ;
        
        
arrow returns [Node ast]	: 
		  LPAR (hotype (COMMA hotype)* )? RPAR ARROW type ;   

type returns [Node ast]	: 
	  	  INT	{$ast = new IntTypeNode();} // Rappresenta l'elemento sintattico del tipo int e non il valore.
		| BOOL	{$ast = new BoolTypeNode();}
		| ID	{$ast = new IdTypeNode();}
		  ;

exp	returns [Node ast]: t=term {$ast = $t.ast;}
		(  PLUS t=term {$ast = new PlusNode($ast,$t.ast);}
		 | MINUS t=term {$ast = new MinusNode($ast, $t.ast);}
		 | OR t=term {$ast = new OrNode($ast, $t.ast);}
		)* ;

term returns [Node ast]: f=factor {$ast = $f.ast;} 
		(  TIMES f=factor {$ast = new MultNode($ast,$f.ast);}
		 | DIV f=factor {$ast = new DivNode($ast,$f.ast);}
		 | AND f=factor{ $ast = new AndNode($ast,$f.ast);}
		)* ;

factor returns [Node ast] : v=value {$ast = $v.ast;}
		(  EQ v=value {$ast = new EqualNode($ast,$v.ast);}
		 | GE v=value {$ast = new GENode($ast,$v.ast);}
		 | LE v=value {$ast = new LENode($ast,$v.ast);}
		)* ;

value returns [Node ast]	:
	i = INTEGER	{$ast = new IntNode(Integer.parseInt($i.text));}
	| TRUE		{$ast = new BoolNode(true);}
	| FALSE		{$ast = new BoolNode(false);}
	| NULL
	| NEW ID LPAR (exp (COMMA exp)* )? RPAR
	| LPAR e = exp RPAR {$ast = $e.ast;}  // Le parentesi lasciano l'albero inalterato.
	| IF e1 = exp THEN CLPAR e2 =exp CRPAR
		ELSE CLPAR e3 = exp CRPAR {$ast = new IfNode($e1.ast,$e2.ast,$e3.ast);}
	| NOT LPAR exp RPAR 
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
			(COMMA a=exp { arglist.add($a.ast); }
			)*
		)? RPAR { $ast = new CallNode($i.text,entry,arglist,nestingLevel); } // Inserito il nestinglevel per verifiche sullo scope della funzione chiamata
		)?
	| ID ( LPAR (exp (COMMA exp)* )? RPAR 
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