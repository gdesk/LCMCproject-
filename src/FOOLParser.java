// Generated from FOOL.g4 by ANTLR 4.4

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FOOLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PLUS=1, MINUS=2, TIMES=3, DIV=4, LPAR=5, RPAR=6, CLPAR=7, CRPAR=8, SEMIC=9, 
		COLON=10, COMMA=11, DOT=12, OR=13, AND=14, NOT=15, GE=16, LE=17, EQ=18, 
		ASS=19, TRUE=20, FALSE=21, IF=22, THEN=23, ELSE=24, PRINT=25, LET=26, 
		IN=27, VAR=28, FUN=29, CLASS=30, EXTENDS=31, NEW=32, NULL=33, INT=34, 
		BOOL=35, ARROW=36, INTEGER=37, ID=38, WHITESP=39, COMMENT=40, ERR=41;
	public static final String[] tokenNames = {
		"<INVALID>", "'+'", "'-'", "'*'", "'/'", "'('", "')'", "'{'", "'}'", "';'", 
		"':'", "','", "'.'", "'||'", "'&&'", "'!'", "'>='", "'<='", "'=='", "'='", 
		"'true'", "'false'", "'if'", "'then'", "'else'", "'print'", "'let'", "'in'", 
		"'var'", "'fun'", "'class'", "'extends'", "'new'", "'null'", "'int'", 
		"'bool'", "'->'", "INTEGER", "ID", "WHITESP", "COMMENT", "ERR"
	};
	public static final int
		RULE_prog = 0, RULE_cllist = 1, RULE_declist = 2, RULE_hotype = 3, RULE_arrow = 4, 
		RULE_type = 5, RULE_exp = 6, RULE_term = 7, RULE_factor = 8, RULE_value = 9;
	public static final String[] ruleNames = {
		"prog", "cllist", "declist", "hotype", "arrow", "type", "exp", "term", 
		"factor", "value"
	};

	@Override
	public String getGrammarFileName() { return "FOOL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		private int nestingLevel = 0;
		private int globalOffset = -2;
		/* Array di tabelle dove l'indice dell'array � il livello sintattico, ossia il livello di scope, indice 0 = dichiarazioni globali, indice 1 = dichiarazioni locali (mappano identificatori con i valori) */
		ArrayList<HashMap<String,STentry>> symTable = new ArrayList<HashMap<String,STentry>>();
		/* Il livello dell'ambiente con dichiarazioni pi� esterne � 0 (nelle slide � 1); il fronte della lista di tabelle � "symTable.get(nestingLevel)" */
		HashMap<String, HashMap<String,STentry>> classTable = new HashMap<String, HashMap<String,STentry>>(); 

	public FOOLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public Node ast;
		public ExpContext e;
		public CllistContext c;
		public DeclistContext d;
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public CllistContext cllist() {
			return getRuleContext(CllistContext.class,0);
		}
		public TerminalNode SEMIC() { return getToken(FOOLParser.SEMIC, 0); }
		public TerminalNode LET() { return getToken(FOOLParser.LET, 0); }
		public TerminalNode IN() { return getToken(FOOLParser.IN, 0); }
		public DeclistContext declist() {
			return getRuleContext(DeclistContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

					HashMap<String,STentry> hm = new HashMap<String,STentry> ();
					symTable.add(hm);

					boolean isCl = false;
					boolean isDec = false;
				
			setState(41);
			switch (_input.LA(1)) {
			case LPAR:
			case NOT:
			case TRUE:
			case FALSE:
			case IF:
			case PRINT:
			case NEW:
			case NULL:
			case INTEGER:
			case ID:
				{
				setState(21); ((ProgContext)_localctx).e = exp();

							((ProgContext)_localctx).ast =  new ProgNode(((ProgContext)_localctx).e.ast);
						
				}
				break;
			case LET:
				{
				setState(24); match(LET);
				setState(35);
				switch (_input.LA(1)) {
				case CLASS:
					{
					setState(25); ((ProgContext)_localctx).c = cllist();
					 isCl = true; 
					setState(30);
					_la = _input.LA(1);
					if (_la==VAR || _la==FUN) {
						{
						setState(27); ((ProgContext)_localctx).d = declist();
						 isDec = true; 
						}
					}

					}
					break;
				case VAR:
				case FUN:
					{
					setState(32); ((ProgContext)_localctx).d = declist();
					 isDec = true; 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(37); match(IN);
				setState(38); ((ProgContext)_localctx).e = exp();

									if (isCl && isDec) {
										((ProgContext)_localctx).ast =  new ProgLetInNode(((ProgContext)_localctx).c.astlist, ((ProgContext)_localctx).d.astlist, ((ProgContext)_localctx).e.ast);
									} else if (isCl) {
										((ProgContext)_localctx).ast =  new ProgLetInNode(((ProgContext)_localctx).c.astlist, new ArrayList<DecNode>(), ((ProgContext)_localctx).e.ast);
									} else { 
										((ProgContext)_localctx).ast =  new ProgLetInNode(new ArrayList<DecNode>(), ((ProgContext)_localctx).d.astlist, ((ProgContext)_localctx).e.ast);
									}
								
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

					symTable.remove(nestingLevel);
				
			setState(44); match(SEMIC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CllistContext extends ParserRuleContext {
		public ArrayList<DecNode> astlist;
		public Token cid;
		public Token eid;
		public Token fid;
		public TypeContext ft;
		public Token fid1;
		public TypeContext ft1;
		public Token mid;
		public TypeContext mt;
		public Token parid;
		public HotypeContext fh;
		public Token parid1;
		public HotypeContext fh1;
		public Token vid;
		public TypeContext vt;
		public ExpContext ex;
		public ExpContext exp1;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public List<TerminalNode> CRPAR() { return getTokens(FOOLParser.CRPAR); }
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public TerminalNode EXTENDS(int i) {
			return getToken(FOOLParser.EXTENDS, i);
		}
		public List<TerminalNode> EXTENDS() { return getTokens(FOOLParser.EXTENDS); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public TerminalNode RPAR(int i) {
			return getToken(FOOLParser.RPAR, i);
		}
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode CLPAR(int i) {
			return getToken(FOOLParser.CLPAR, i);
		}
		public HotypeContext hotype(int i) {
			return getRuleContext(HotypeContext.class,i);
		}
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public List<TerminalNode> VAR() { return getTokens(FOOLParser.VAR); }
		public TerminalNode LPAR(int i) {
			return getToken(FOOLParser.LPAR, i);
		}
		public TerminalNode CRPAR(int i) {
			return getToken(FOOLParser.CRPAR, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TerminalNode LET(int i) {
			return getToken(FOOLParser.LET, i);
		}
		public List<TerminalNode> COLON() { return getTokens(FOOLParser.COLON); }
		public TerminalNode FUN(int i) {
			return getToken(FOOLParser.FUN, i);
		}
		public List<HotypeContext> hotype() {
			return getRuleContexts(HotypeContext.class);
		}
		public TerminalNode VAR(int i) {
			return getToken(FOOLParser.VAR, i);
		}
		public List<TerminalNode> LPAR() { return getTokens(FOOLParser.LPAR); }
		public List<TerminalNode> CLPAR() { return getTokens(FOOLParser.CLPAR); }
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> IN() { return getTokens(FOOLParser.IN); }
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode IN(int i) {
			return getToken(FOOLParser.IN, i);
		}
		public TerminalNode COLON(int i) {
			return getToken(FOOLParser.COLON, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(FOOLParser.RPAR); }
		public List<TerminalNode> CLASS() { return getTokens(FOOLParser.CLASS); }
		public TerminalNode CLASS(int i) {
			return getToken(FOOLParser.CLASS, i);
		}
		public List<TerminalNode> FUN() { return getTokens(FOOLParser.FUN); }
		public List<TerminalNode> LET() { return getTokens(FOOLParser.LET); }
		public List<TerminalNode> ASS() { return getTokens(FOOLParser.ASS); }
		public TerminalNode ASS(int i) {
			return getToken(FOOLParser.ASS, i);
		}
		public CllistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cllist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterCllist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitCllist(this);
		}
	}

	public final CllistContext cllist() throws RecognitionException {
		CllistContext _localctx = new CllistContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_cllist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

					((CllistContext)_localctx).astlist =  new ArrayList<DecNode>();
					/* variabile locale per la ridefinizione erronea di campi e metodi */
					HashSet<String> localVariable;
					STentry classEntry;
					STentry fieldEntry;
					STentry methodEntry;
				
			setState(134); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(47); match(CLASS);
				setState(48); ((CllistContext)_localctx).cid = match(ID);

							localVariable = new HashSet<>();
							/* L'offset della classe non � definito qui perch� � il globalOffset gi� definito sopra */
							int fieldoffset = -1;
							int methodoffset = 0;
							int varoffset = -2;	
								
							ClassNode classNode = new ClassNode((((CllistContext)_localctx).cid!=null?((CllistContext)_localctx).cid.getText():null));
							ClassTypeNode classType = new ClassTypeNode(new ArrayList<Node>(), new ArrayList<Node>());
							_localctx.astlist.add(classNode);
							HashMap<String,STentry> hm = symTable.get(0);  
							classEntry = new STentry(nestingLevel, classType, globalOffset--);
							if ( hm.put((((CllistContext)_localctx).cid!=null?((CllistContext)_localctx).cid.getText():null), classEntry) != null ) {
								System.out.println("Class id "+(((CllistContext)_localctx).cid!=null?((CllistContext)_localctx).cid.getText():null)+" at line "+(((CllistContext)_localctx).cid!=null?((CllistContext)_localctx).cid.getLine():0)+" already declared");
								System.exit(0);
							}
							/* creazione virtual table (vuota) */
							HashMap<String,STentry> vhm = new HashMap<String,STentry>();
						
				setState(53);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(50); match(EXTENDS);
					setState(51); ((CllistContext)_localctx).eid = match(ID);

									STentry superEntry = symTable.get(0).get((((CllistContext)_localctx).eid!=null?((CllistContext)_localctx).eid.getText():null));
									if (classTable.get((((CllistContext)_localctx).eid!=null?((CllistContext)_localctx).eid.getText():null)) == null || superEntry == null) {  /* ID2 deve essere in Class Table */
										System.out.println("Class "+ (((CllistContext)_localctx).cid!=null?((CllistContext)_localctx).cid.getText():null) +" extends from nonexisting "+(((CllistContext)_localctx).eid!=null?((CllistContext)_localctx).eid.getText():null)+ " class");
										System.exit(0);
									}
									
									classNode.setSuperEntry(superEntry);
									/* aggiunta nella HasMap che mappa gli ID delle classi con ID delle sue superclassi */
									FOOLlib.addSuperType((((CllistContext)_localctx).cid!=null?((CllistContext)_localctx).cid.getText():null), (((CllistContext)_localctx).eid!=null?((CllistContext)_localctx).eid.getText():null));
									
									/* Inseriamo nel classTypeNode i metodi e i campi ereditati dalla superclasse */
									ClassTypeNode superType = (ClassTypeNode) superEntry.getType();
									classType = new ClassTypeNode(superType.getFields(), superType.getMethods());
									/* Prendo la virtual Table della classe creata */
									vhm = (HashMap<String,STentry>) classTable.get((((CllistContext)_localctx).eid!=null?((CllistContext)_localctx).eid.getText():null));  
									/* Aggiornamento offset classTypeNode nel caso in cui ci sia una classe ereditata */
									fieldoffset = - classType.getFields().size() - 1;                  
									methodoffset = classType.getMethods().size(); 
								
					}
				}


								/* Aggiunta della virtual Table nella symTable */
								symTable.add(vhm);             
								/* Aggiunta della virtual Table nella classTable */
								classTable.put((((CllistContext)_localctx).cid!=null?((CllistContext)_localctx).cid.getText():null), vhm);  
							
								classEntry.addType(classType);      
								classNode.setSymType(classType);
								
								nestingLevel++;    
							
				setState(56); match(LPAR);
				setState(72);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(57); ((CllistContext)_localctx).fid = match(ID);
					setState(58); match(COLON);
					setState(59); ((CllistContext)_localctx).ft = type();

								 		/* Controllo se il campo � gi� stato creato (attraverso la local variable) */
								 		if(localVariable.contains((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null))) {
							 	  			System.out.println("Field" + (((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null) + " at line " + (((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getLine():0) + " already created.");
											System.exit(0);
						 	  			}
						 	  			localVariable.add((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null)); 
						 	  			
						 	  			FieldNode field = new FieldNode((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null), ((CllistContext)_localctx).ft.ast);
						 	  			
						 	  			if(vhm.get((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null)) != null) {
						 	  				
											/* Controllo che non sia un metodo */
											if(vhm.get((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null)).isMethod()) {
												System.out.println("Field id "+(((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null)+" cannot override a method");
												System.exit(0); 
									 		}
									 		
									 		/* Faccio override (campo gi� presente in virtual table) */
									 		
									 		fieldEntry = new STentry(nestingLevel, ((CllistContext)_localctx).ft.ast, vhm.get((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null)).getOffset());
									 		/* Aggiornamento classTypeNode */
									 		int index = - fieldEntry.getOffset() - 1;
											classType.setField(index, fieldEntry.getType());  
								 		}else{
								 			/* Non faccio override*/
								 			fieldEntry = new STentry(nestingLevel, ((CllistContext)_localctx).ft.ast, fieldoffset--);
								 			classType.addField(fieldEntry.getType());
								 		}
								 		
								 		/* Aggiunta del campo in virtual table */
								 		vhm.put((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null), fieldEntry);                        
										classNode.addField(field);
										/* Ottimizzazione typeChecking parametri: settiamo l'offset del campo */   
										field.setOffset(fieldEntry.getOffset());         
								 	
					setState(69);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(61); match(COMMA);
						setState(62); ((CllistContext)_localctx).fid1 = match(ID);
						setState(63); match(COLON);
						setState(64); ((CllistContext)_localctx).ft1 = type();

									 		/* Controllo se il campo � gi� stato creato (attraverso la local variable) */
									 		if(localVariable.contains((((CllistContext)_localctx).fid1!=null?((CllistContext)_localctx).fid1.getText():null))) {
								 	  			System.out.println("Field" + (((CllistContext)_localctx).fid1!=null?((CllistContext)_localctx).fid1.getText():null) + " at line " + (((CllistContext)_localctx).fid1!=null?((CllistContext)_localctx).fid1.getLine():0) + " already created.");
												System.exit(0);
							 	  			}
							 	  			localVariable.add((((CllistContext)_localctx).fid1!=null?((CllistContext)_localctx).fid1.getText():null)); 
							 	  			
							 	  			FieldNode field1 = new FieldNode((((CllistContext)_localctx).fid1!=null?((CllistContext)_localctx).fid1.getText():null), ((CllistContext)_localctx).ft1.ast);
							 	  			
							 	  			if(vhm.get((((CllistContext)_localctx).fid1!=null?((CllistContext)_localctx).fid1.getText():null)) != null) {
												
												/* Controllo che non sia un metodo */
												if(vhm.get((((CllistContext)_localctx).fid1!=null?((CllistContext)_localctx).fid1.getText():null)).isMethod()) {
													System.out.println("Field id "+(((CllistContext)_localctx).fid1!=null?((CllistContext)_localctx).fid1.getText():null)+" cannot override a method");
													System.exit(0); 
										 		}
										 		
										 		/* Faccio override (campo gi� presente in virtual table) */
										 		
										 		fieldEntry = new STentry(nestingLevel, ((CllistContext)_localctx).ft1.ast, vhm.get((((CllistContext)_localctx).fid1!=null?((CllistContext)_localctx).fid1.getText():null)).getOffset());
										 		/* Aggiornamento classTypeNode */
										 		int index1 = - fieldEntry.getOffset() - 1;
												classType.setField(index1, fieldEntry.getType());  
									 		}else{
									 			/* Non faccio override*/
									 			fieldEntry = new STentry(nestingLevel, ((CllistContext)_localctx).ft1.ast, fieldoffset--);
									 			classType.addField(fieldEntry.getType());
									 		}
									 		/* Aggiunta del campo in virtual table */
									 		vhm.put((((CllistContext)_localctx).fid1!=null?((CllistContext)_localctx).fid1.getText():null), fieldEntry);                       
											classNode.addField(field1);
											/* Ottimizzazione typeChecking parametri: settiamo l'offset del campo */  
											field1.setOffset(fieldEntry.getOffset());           
									 	
						}
						}
						setState(71);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(74); match(RPAR);
				setState(75); match(CLPAR);
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FUN) {
					{
					{
					setState(76); match(FUN);
					setState(77); ((CllistContext)_localctx).mid = match(ID);
					setState(78); match(COLON);
					setState(79); ((CllistContext)_localctx).mt = type();

								 		/* Controllo se il metodo � gi� stato creato (attraverso la local variable) */
								 		if(localVariable.contains((((CllistContext)_localctx).mid!=null?((CllistContext)_localctx).mid.getText():null))) {
							 	  			System.out.println("Method" + (((CllistContext)_localctx).mid!=null?((CllistContext)_localctx).mid.getText():null) + " at line " + (((CllistContext)_localctx).mid!=null?((CllistContext)_localctx).mid.getLine():0) + " already created.");
											System.exit(0);
						 	  			}
						 	  			localVariable.add((((CllistContext)_localctx).mid!=null?((CllistContext)_localctx).mid.getText():null)); 
						 	  			
						 	  			MethodNode method = new MethodNode((((CllistContext)_localctx).mid!=null?((CllistContext)_localctx).mid.getText():null), ((CllistContext)_localctx).mt.ast);
						 	  			
						 	  			if(vhm.get((((CllistContext)_localctx).mid!=null?((CllistContext)_localctx).mid.getText():null)) != null) {
											
											/* Controllo che sia un metodo */
											if(!vhm.get((((CllistContext)_localctx).mid!=null?((CllistContext)_localctx).mid.getText():null)).isMethod()) {
												System.out.println("Method id "+(((CllistContext)_localctx).mid!=null?((CllistContext)_localctx).mid.getText():null)+" cannot override a method");
												System.exit(0); 
									 		}
									 		
									 		/* Faccio override (metodo gi� presente in virtual table) */
									 		
									 		methodEntry = new STentry(nestingLevel, new ArrowTypeNode(new ArrayList<Node>(), ((CllistContext)_localctx).mt.ast ), vhm.get((((CllistContext)_localctx).mid!=null?((CllistContext)_localctx).mid.getText():null)).getOffset());
									 		methodEntry.setIsMethod();
									 		/* Aggiornamento classTypeNode */
									 		int index = methodEntry.getOffset();
											classType.setMethod(index, methodEntry.getType());  
								 		}else{
								 			/* Non faccio override*/
								 			methodEntry = new STentry(nestingLevel, new ArrowTypeNode(new ArrayList<Node>(), ((CllistContext)_localctx).mt.ast ),  methodoffset++);
								 			methodEntry.setIsMethod();
								 			classType.addMethod(methodEntry.getType());
								 		}
								 		/* Aggiunta del metodo in virtual table */
								 		vhm.put((((CllistContext)_localctx).mid!=null?((CllistContext)_localctx).mid.getText():null), methodEntry);                         
										classNode.addMethod(method);
										/* Ottimizzazione typeChecking : settiamo l'offset del metodo */ 
										method.setOffset(methodEntry.getOffset());             
					                 	
					                 	nestingLevel++;
					                 	HashMap<String,STentry> mhm = new HashMap<String,STentry> (); 
										symTable.add(mhm);
								 	
					setState(81); match(LPAR);

					                 		ArrayList<Node> parlist = new ArrayList<Node>();
											
											int parOffset = 1;
					                 	
					setState(100);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(83); ((CllistContext)_localctx).parid = match(ID);
						setState(84); match(COLON);
						setState(85); ((CllistContext)_localctx).fh = hotype();

						                 			ParNode par = new ParNode((((CllistContext)_localctx).parid!=null?((CllistContext)_localctx).parid.getText():null), ((CllistContext)_localctx).fh.ast);
						                 			parlist.add(par);
						                 			
						                 		if(par.getSymType() instanceof ArrowTypeNode) parOffset++;
						                 		/* Aggiunta paramentri in virtual table se non presenti (altrimenti errore)*/
						                 		if(mhm.put((((CllistContext)_localctx).parid!=null?((CllistContext)_localctx).parid.getText():null), new STentry(nestingLevel, ((CllistContext)_localctx).fh.ast, parOffset++)) != null){
													System.out.println("Par ID: " + (((CllistContext)_localctx).parid!=null?((CllistContext)_localctx).parid.getText():null) + " at line " + (((CllistContext)_localctx).parid!=null?((CllistContext)_localctx).parid.getLine():0) + " already declared");
													System.exit(0);
							                 	}
											
						setState(95);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(87); match(COMMA);
							setState(88); ((CllistContext)_localctx).parid1 = match(ID);
							setState(89); match(COLON);
							setState(90); ((CllistContext)_localctx).fh1 = hotype();

							                 	   		ParNode par1 = new ParNode((((CllistContext)_localctx).parid1!=null?((CllistContext)_localctx).parid1.getText():null), ((CllistContext)_localctx).fh1.ast);
							                 			parlist.add(par1);
							                 			            		
							                 			if(par1.getSymType() instanceof ArrowTypeNode) parOffset++;
							                 			/* Aggiunta paramentri in virtual table se non presenti (altrimenti errore)*/
							                 			if(mhm.put((((CllistContext)_localctx).parid1!=null?((CllistContext)_localctx).parid1.getText():null), new STentry(nestingLevel, ((CllistContext)_localctx).fh1.ast, parOffset++)) != null){
															System.out.println("Par ID: " + (((CllistContext)_localctx).parid1!=null?((CllistContext)_localctx).parid1.getText():null) + " at line " + (((CllistContext)_localctx).parid1!=null?((CllistContext)_localctx).parid1.getLine():0) + " already declared");
															System.exit(0);
								                 		}
							                 	   	
							}
							}
							setState(97);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}

						                 	 	                 	 	
						                 	   	method.addParList(parlist);
												/* Aggiornamento tipo del metodo */                 	   
						                 	   	methodEntry.addType(new ArrowTypeNode(parlist, ((CllistContext)_localctx).mt.ast));
						                 	   
						}
					}

					setState(102); match(RPAR);

					                 		 	ArrayList<VarNode> varlist = new ArrayList<>();
					                 		 
					setState(121);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(104); match(LET);
						setState(116);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==VAR) {
							{
							{
							setState(105); match(VAR);
							setState(106); ((CllistContext)_localctx).vid = match(ID);
							setState(107); match(COLON);
							setState(108); ((CllistContext)_localctx).vt = type();
							setState(109); match(ASS);
							setState(110); ((CllistContext)_localctx).ex = exp();

								                     	
								                     		VarNode var = new VarNode((((CllistContext)_localctx).vid!=null?((CllistContext)_localctx).vid.getText():null), ((CllistContext)_localctx).vt.ast, ((CllistContext)_localctx).ex.ast);
								                     		varlist.add(var);
															/* Aggiunta variabili in virtual table se non presenti (altrimenti errore)*/
															if(mhm.put((((CllistContext)_localctx).vid!=null?((CllistContext)_localctx).vid.getText():null),new STentry(nestingLevel, ((CllistContext)_localctx).vt.ast, varoffset--)) != null) {
																System.out.println("Var id" + (((CllistContext)_localctx).vid!=null?((CllistContext)_localctx).vid.getText():null) + " at line " + (((CllistContext)_localctx).vid!=null?((CllistContext)_localctx).vid.getLine():0) + " already declared.");
																System.exit(0);
								                     		}
								                     	
							setState(112); match(SEMIC);
							}
							}
							setState(118);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}

							                     	method.addVarList(varlist);
							                     
						setState(120); match(IN);
						}
					}

					setState(123); ((CllistContext)_localctx).exp1 = exp();

						                     	method.addExp(((CllistContext)_localctx).exp1.ast);
					                    		
					                    		symTable.remove(nestingLevel--);
						                     
					setState(125); match(SEMIC);
					}
					}
					setState(131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(132); match(CRPAR);
						
					         	symTable.remove(nestingLevel--);
				       		  
				}
				}
				setState(136); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclistContext extends ParserRuleContext {
		public ArrayList<DecNode> astlist;
		public Token i;
		public HotypeContext h;
		public ExpContext e;
		public TypeContext t;
		public Token i1;
		public HotypeContext fty;
		public Token i2;
		public HotypeContext ty;
		public DeclistContext d;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public TerminalNode RPAR(int i) {
			return getToken(FOOLParser.RPAR, i);
		}
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public HotypeContext hotype(int i) {
			return getRuleContext(HotypeContext.class,i);
		}
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public List<TerminalNode> VAR() { return getTokens(FOOLParser.VAR); }
		public TerminalNode LPAR(int i) {
			return getToken(FOOLParser.LPAR, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TerminalNode LET(int i) {
			return getToken(FOOLParser.LET, i);
		}
		public List<DeclistContext> declist() {
			return getRuleContexts(DeclistContext.class);
		}
		public DeclistContext declist(int i) {
			return getRuleContext(DeclistContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(FOOLParser.COLON); }
		public TerminalNode FUN(int i) {
			return getToken(FOOLParser.FUN, i);
		}
		public List<HotypeContext> hotype() {
			return getRuleContexts(HotypeContext.class);
		}
		public TerminalNode VAR(int i) {
			return getToken(FOOLParser.VAR, i);
		}
		public List<TerminalNode> LPAR() { return getTokens(FOOLParser.LPAR); }
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> IN() { return getTokens(FOOLParser.IN); }
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode IN(int i) {
			return getToken(FOOLParser.IN, i);
		}
		public TerminalNode COLON(int i) {
			return getToken(FOOLParser.COLON, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(FOOLParser.RPAR); }
		public List<TerminalNode> ASS() { return getTokens(FOOLParser.ASS); }
		public List<TerminalNode> FUN() { return getTokens(FOOLParser.FUN); }
		public List<TerminalNode> LET() { return getTokens(FOOLParser.LET); }
		public TerminalNode ASS(int i) {
			return getToken(FOOLParser.ASS, i);
		}
		public DeclistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterDeclist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitDeclist(this);
		}
	}

	public final DeclistContext declist() throws RecognitionException {
		DeclistContext _localctx = new DeclistContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				
					((DeclistContext)_localctx).astlist =  new ArrayList<DecNode>();
					int offset = -2;
					if (nestingLevel == 0) {
						offset = globalOffset;
					} 
				
			setState(188); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(183);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(139); match(VAR);
					setState(140); ((DeclistContext)_localctx).i = match(ID);
					setState(141); match(COLON);
					setState(142); ((DeclistContext)_localctx).h = hotype();
					setState(143); match(ASS);
					setState(144); ((DeclistContext)_localctx).e = exp();
						
									VarNode v = new VarNode((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),((DeclistContext)_localctx).h.ast,((DeclistContext)_localctx).e.ast);
									_localctx.astlist.add(v);
									HashMap<String,STentry> hm = symTable.get(nestingLevel);
									if(hm.put((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null), new STentry(nestingLevel,((DeclistContext)_localctx).h.ast,offset--)) != null) {
										System.out.println("Var id" + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null) + " at line " + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getLine():0) + " already declared.");
										System.exit(0);
									}; 
									/* Offset doppio per tipi funzionali */
									if(((DeclistContext)_localctx).h.ast instanceof ArrowTypeNode) offset--; 
								
					}
					break;
				case FUN:
					{
					setState(147); match(FUN);
					setState(148); ((DeclistContext)_localctx).i = match(ID);
					setState(149); match(COLON);
					setState(150); ((DeclistContext)_localctx).t = type();
						
									FunNode f = new FunNode((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),((DeclistContext)_localctx).t.ast);
									_localctx.astlist.add(f);
									HashMap<String,STentry> hm = symTable.get(nestingLevel);
									STentry entry = new STentry(nestingLevel,offset--);
									offset--;
									if(hm.put((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null), entry) != null) {
										System.out.println("Fun id" + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null) + " at line " + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getLine():0) + " already declared.");
										System.exit(0);
									}
								
					setState(152); match(LPAR);
						
									nestingLevel++;  
									HashMap<String,STentry> hmn = new HashMap<String,STentry>();
									symTable.add(hmn);
									ArrayList<Node> parTypes = new ArrayList<Node>();
									int parOffset = 1;
								
					setState(169);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(154); ((DeclistContext)_localctx).i1 = match(ID);
						setState(155); match(COLON);
						setState(156); ((DeclistContext)_localctx).fty = hotype();
						 
												parTypes.add(((DeclistContext)_localctx).fty.ast);
												ParNode p1 = new ParNode((((DeclistContext)_localctx).i1!=null?((DeclistContext)_localctx).i1.getText():null),((DeclistContext)_localctx).fty.ast);
												f.addPar(p1);
												/* Offset doppio per tipi funzionali */
												if(((DeclistContext)_localctx).fty.ast instanceof ArrowTypeNode) parOffset++;
												if (hmn.put((((DeclistContext)_localctx).i1!=null?((DeclistContext)_localctx).i1.getText():null), new STentry(nestingLevel,((DeclistContext)_localctx).fty.ast,parOffset++)) != null) {
													System.out.println("Par ID: " + (((DeclistContext)_localctx).i1!=null?((DeclistContext)_localctx).i1.getText():null) + " at line " + (((DeclistContext)_localctx).i1!=null?((DeclistContext)_localctx).i1.getLine():0) + " already declared");
													System.exit(0);
												}
											
											
						setState(166);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(158); match(COMMA);
							setState(159); ((DeclistContext)_localctx).i2 = match(ID);
							setState(160); match(COLON);
							setState(161); ((DeclistContext)_localctx).ty = hotype();

													parTypes.add(((DeclistContext)_localctx).ty.ast);
													ParNode p2 = new ParNode((((DeclistContext)_localctx).i2!=null?((DeclistContext)_localctx).i2.getText():null),((DeclistContext)_localctx).ty.ast);
													f.addPar(p2);
													/* Offset doppio per tipi funzionali */
													if(((DeclistContext)_localctx).ty.ast instanceof ArrowTypeNode) parOffset++;
													if (hmn.put((((DeclistContext)_localctx).i2!=null?((DeclistContext)_localctx).i2.getText():null), new STentry(nestingLevel,((DeclistContext)_localctx).ty.ast,parOffset++)) != null){
														System.out.println("Par ID: " + (((DeclistContext)_localctx).i2!=null?((DeclistContext)_localctx).i2.getText():null) + " at line " + (((DeclistContext)_localctx).i2!=null?((DeclistContext)_localctx).i2.getLine():0) + " already declared");
														System.exit(0);
													}
													
												
							}
							}
							setState(168);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(171); match(RPAR);
					 
										entry.addType(new ArrowTypeNode(parTypes,((DeclistContext)_localctx).t.ast));
										ArrowTypeNode symType = new ArrowTypeNode(parTypes, ((DeclistContext)_localctx).t.ast);
										entry.addType(symType);
										f.setSymType(symType);
									
					setState(178);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(173); match(LET);
						setState(174); ((DeclistContext)_localctx).d = declist();
						f.addDec(((DeclistContext)_localctx).d.astlist);
						setState(176); match(IN);
						}
					}

					setState(180); ((DeclistContext)_localctx).e = exp();
						
										f.addBody(((DeclistContext)_localctx).e.ast);
										
										symTable.remove(nestingLevel--); 
									
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(185); match(SEMIC);

								if (nestingLevel == 0) {
									globalOffset = offset;
							}
						
				}
				}
				setState(190); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VAR || _la==FUN );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HotypeContext extends ParserRuleContext {
		public Node ast;
		public TypeContext t;
		public ArrowContext a;
		public ArrowContext arrow() {
			return getRuleContext(ArrowContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public HotypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hotype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterHotype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitHotype(this);
		}
	}

	public final HotypeContext hotype() throws RecognitionException {
		HotypeContext _localctx = new HotypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_hotype);
		try {
			setState(198);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(192); ((HotypeContext)_localctx).t = type();
				((HotypeContext)_localctx).ast =  ((HotypeContext)_localctx).t.ast;
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(195); ((HotypeContext)_localctx).a = arrow();
				((HotypeContext)_localctx).ast =  ((HotypeContext)_localctx).a.ast;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrowContext extends ParserRuleContext {
		public Node ast;
		public HotypeContext h;
		public HotypeContext h1;
		public TypeContext t;
		public HotypeContext hotype(int i) {
			return getRuleContext(HotypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public List<HotypeContext> hotype() {
			return getRuleContexts(HotypeContext.class);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public TerminalNode ARROW() { return getToken(FOOLParser.ARROW, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public ArrowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterArrow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitArrow(this);
		}
	}

	public final ArrowContext arrow() throws RecognitionException {
		ArrowContext _localctx = new ArrowContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arrow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

								ArrayList<Node> hotypeList = new ArrayList<>();
							
			setState(201); match(LPAR);
			setState(213);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(202); ((ArrowContext)_localctx).h = hotype();

						  			hotypeList.add(((ArrowContext)_localctx).h.ast);
						  		
				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(204); match(COMMA);
					setState(205); ((ArrowContext)_localctx).h1 = hotype();

							  			hotypeList.add(((ArrowContext)_localctx).h1.ast);
							  		
					}
					}
					setState(212);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(215); match(RPAR);
			setState(216); match(ARROW);
			setState(217); ((ArrowContext)_localctx).t = type();

					  		((ArrowContext)_localctx).ast =  new ArrowTypeNode(hotypeList, ((ArrowContext)_localctx).t.ast);
					  	
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public Node ast;
		public Token id;
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TerminalNode BOOL() { return getToken(FOOLParser.BOOL, 0); }
		public TerminalNode INT() { return getToken(FOOLParser.INT, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		try {
			setState(226);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(220); match(INT);
				((TypeContext)_localctx).ast =  new IntTypeNode();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(222); match(BOOL);
				((TypeContext)_localctx).ast =  new BoolTypeNode();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(224); ((TypeContext)_localctx).id = match(ID);
				((TypeContext)_localctx).ast =  new RefTypeNode((((TypeContext)_localctx).id!=null?((TypeContext)_localctx).id.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public Node ast;
		public TermContext t;
		public TermContext p;
		public TermContext m;
		public TermContext o;
		public TerminalNode MINUS(int i) {
			return getToken(FOOLParser.MINUS, i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(FOOLParser.PLUS); }
		public List<TerminalNode> MINUS() { return getTokens(FOOLParser.MINUS); }
		public List<TerminalNode> OR() { return getTokens(FOOLParser.OR); }
		public TerminalNode PLUS(int i) {
			return getToken(FOOLParser.PLUS, i);
		}
		public TerminalNode OR(int i) {
			return getToken(FOOLParser.OR, i);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228); ((ExpContext)_localctx).t = term();
			((ExpContext)_localctx).ast =  ((ExpContext)_localctx).t.ast;
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0)) {
				{
				setState(242);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(230); match(PLUS);
					setState(231); ((ExpContext)_localctx).p = term();
					((ExpContext)_localctx).ast =  new PlusNode(_localctx.ast,((ExpContext)_localctx).p.ast);
					}
					break;
				case MINUS:
					{
					setState(234); match(MINUS);
					setState(235); ((ExpContext)_localctx).m = term();
					((ExpContext)_localctx).ast =  new MinusNode(_localctx.ast, ((ExpContext)_localctx).m.ast);
					}
					break;
				case OR:
					{
					setState(238); match(OR);
					setState(239); ((ExpContext)_localctx).o = term();
					((ExpContext)_localctx).ast =  new OrNode(_localctx.ast, ((ExpContext)_localctx).o.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public Node ast;
		public FactorContext f;
		public FactorContext t;
		public FactorContext d;
		public FactorContext a;
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public TerminalNode AND(int i) {
			return getToken(FOOLParser.AND, i);
		}
		public TerminalNode TIMES(int i) {
			return getToken(FOOLParser.TIMES, i);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> TIMES() { return getTokens(FOOLParser.TIMES); }
		public List<TerminalNode> AND() { return getTokens(FOOLParser.AND); }
		public List<TerminalNode> DIV() { return getTokens(FOOLParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(FOOLParser.DIV, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247); ((TermContext)_localctx).f = factor();
			((TermContext)_localctx).ast =  ((TermContext)_localctx).f.ast;
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TIMES) | (1L << DIV) | (1L << AND))) != 0)) {
				{
				setState(261);
				switch (_input.LA(1)) {
				case TIMES:
					{
					setState(249); match(TIMES);
					setState(250); ((TermContext)_localctx).t = factor();
					((TermContext)_localctx).ast =  new TimesNode(_localctx.ast,((TermContext)_localctx).t.ast);
					}
					break;
				case DIV:
					{
					setState(253); match(DIV);
					setState(254); ((TermContext)_localctx).d = factor();
					((TermContext)_localctx).ast =  new DivNode(_localctx.ast,((TermContext)_localctx).d.ast);
					}
					break;
				case AND:
					{
					setState(257); match(AND);
					setState(258); ((TermContext)_localctx).a = factor();
					((TermContext)_localctx).ast =  new AndNode(_localctx.ast,((TermContext)_localctx).a.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public Node ast;
		public ValueContext v;
		public ValueContext e;
		public ValueContext g;
		public ValueContext l;
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public List<TerminalNode> GE() { return getTokens(FOOLParser.GE); }
		public TerminalNode GE(int i) {
			return getToken(FOOLParser.GE, i);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode EQ(int i) {
			return getToken(FOOLParser.EQ, i);
		}
		public TerminalNode LE(int i) {
			return getToken(FOOLParser.LE, i);
		}
		public List<TerminalNode> LE() { return getTokens(FOOLParser.LE); }
		public List<TerminalNode> EQ() { return getTokens(FOOLParser.EQ); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266); ((FactorContext)_localctx).v = value();
			((FactorContext)_localctx).ast =  ((FactorContext)_localctx).v.ast;
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << LE) | (1L << EQ))) != 0)) {
				{
				setState(280);
				switch (_input.LA(1)) {
				case EQ:
					{
					setState(268); match(EQ);
					setState(269); ((FactorContext)_localctx).e = value();
					((FactorContext)_localctx).ast =  new EqualNode(_localctx.ast,((FactorContext)_localctx).e.ast);
					}
					break;
				case GE:
					{
					setState(272); match(GE);
					setState(273); ((FactorContext)_localctx).g = value();
					((FactorContext)_localctx).ast =  new GENode(_localctx.ast,((FactorContext)_localctx).g.ast);
					}
					break;
				case LE:
					{
					setState(276); match(LE);
					setState(277); ((FactorContext)_localctx).l = value();
					((FactorContext)_localctx).ast =  new LENode(_localctx.ast,((FactorContext)_localctx).l.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public Node ast;
		public Token in;
		public Token newid;
		public ExpContext e1;
		public ExpContext e2;
		public ExpContext e;
		public ExpContext e3;
		public Token i;
		public ExpContext a;
		public Token im;
		public ExpContext fe;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public TerminalNode ELSE() { return getToken(FOOLParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(FOOLParser.IF, 0); }
		public TerminalNode PRINT() { return getToken(FOOLParser.PRINT, 0); }
		public TerminalNode INTEGER() { return getToken(FOOLParser.INTEGER, 0); }
		public TerminalNode FALSE() { return getToken(FOOLParser.FALSE, 0); }
		public List<TerminalNode> CRPAR() { return getTokens(FOOLParser.CRPAR); }
		public TerminalNode TRUE() { return getToken(FOOLParser.TRUE, 0); }
		public TerminalNode THEN() { return getToken(FOOLParser.THEN, 0); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public List<TerminalNode> CLPAR() { return getTokens(FOOLParser.CLPAR); }
		public TerminalNode NEW() { return getToken(FOOLParser.NEW, 0); }
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public TerminalNode NOT() { return getToken(FOOLParser.NOT, 0); }
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode DOT() { return getToken(FOOLParser.DOT, 0); }
		public TerminalNode CLPAR(int i) {
			return getToken(FOOLParser.CLPAR, i);
		}
		public TerminalNode NULL() { return getToken(FOOLParser.NULL, 0); }
		public TerminalNode CRPAR(int i) {
			return getToken(FOOLParser.CRPAR, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_value);
		int _la;
		try {
			setState(381);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(285); ((ValueContext)_localctx).in = match(INTEGER);
				((ValueContext)_localctx).ast =  new IntNode(Integer.parseInt((((ValueContext)_localctx).in!=null?((ValueContext)_localctx).in.getText():null)));
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(287); match(TRUE);
				((ValueContext)_localctx).ast =  new BoolNode(true);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(289); match(FALSE);
				((ValueContext)_localctx).ast =  new BoolNode(false);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(291); match(NULL);
				((ValueContext)_localctx).ast =  new EmptyNode();
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 5);
				{
				setState(293); match(NEW);
				setState(294); ((ValueContext)_localctx).newid = match(ID);

							if(!classTable.keySet().contains((((ValueContext)_localctx).newid!=null?((ValueContext)_localctx).newid.getText():null))){
								System.out.println("Class id" + (((ValueContext)_localctx).newid!=null?((ValueContext)_localctx).newid.getText():null) + " at line " + (((ValueContext)_localctx).newid!=null?((ValueContext)_localctx).newid.getLine():0) + " not declared.");
								System.exit(0);
							}
							STentry entry = symTable.get(0).get((((ValueContext)_localctx).newid!=null?((ValueContext)_localctx).newid.getText():null));
							NewNode newNode = new NewNode((((ValueContext)_localctx).newid!=null?((ValueContext)_localctx).newid.getText():null),entry);
						
				setState(296); match(LPAR);
				setState(308);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(297); ((ValueContext)_localctx).e1 = exp();
					 newNode.addArg(((ValueContext)_localctx).e1.ast);	
					setState(305);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(299); match(COMMA);
						setState(300); ((ValueContext)_localctx).e2 = exp();
						 newNode.addArg(((ValueContext)_localctx).e2.ast);
						}
						}
						setState(307);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				 ((ValueContext)_localctx).ast =  newNode; 
				setState(311); match(RPAR);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 6);
				{
				setState(312); match(LPAR);
				setState(313); ((ValueContext)_localctx).e = exp();
				setState(314); match(RPAR);
				((ValueContext)_localctx).ast =  ((ValueContext)_localctx).e.ast;
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 7);
				{
				setState(317); match(IF);
				setState(318); ((ValueContext)_localctx).e1 = exp();
				setState(319); match(THEN);
				setState(320); match(CLPAR);
				setState(321); ((ValueContext)_localctx).e2 = exp();
				setState(322); match(CRPAR);
				setState(323); match(ELSE);
				setState(324); match(CLPAR);
				setState(325); ((ValueContext)_localctx).e3 = exp();
				setState(326); match(CRPAR);
				((ValueContext)_localctx).ast =  new IfNode(((ValueContext)_localctx).e1.ast,((ValueContext)_localctx).e2.ast,((ValueContext)_localctx).e3.ast);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 8);
				{
				setState(329); match(NOT);
				setState(330); match(LPAR);
				setState(331); ((ValueContext)_localctx).e = exp();
				((ValueContext)_localctx).ast =  new NotNode(((ValueContext)_localctx).e.ast);
				setState(333); match(RPAR);
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 9);
				{
				setState(335); match(PRINT);
				setState(336); match(LPAR);
				setState(337); ((ValueContext)_localctx).e = exp();
				setState(338); match(RPAR);
				((ValueContext)_localctx).ast =  new PrintNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 10);
				{
				setState(341); ((ValueContext)_localctx).i = match(ID);

						/* cercare la dichiarazione */
						int j = nestingLevel;
						STentry entry = null;
						while (j>=0 && entry==null)
							entry = (symTable.get(j--)).get((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null));
						if (entry==null) {
							System.out.println("Id "+(((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null)+" at line "+(((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getLine():0)+" not declared");
							System.exit(0);
						}
						((ValueContext)_localctx).ast =  new IdNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null),entry,nestingLevel);
					
				setState(379);
				switch (_input.LA(1)) {
				case LPAR:
					{
					setState(343); match(LPAR);
					 ArrayList<Node> arglist = new ArrayList<Node>();	
					setState(356);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(345); ((ValueContext)_localctx).a = exp();
						 arglist.add(((ValueContext)_localctx).a.ast); 
						setState(353);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(347); match(COMMA);
							setState(348); ((ValueContext)_localctx).a = exp();
							 arglist.add(((ValueContext)_localctx).a.ast); 
							}
							}
							setState(355);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(358); match(RPAR);
					 ((ValueContext)_localctx).ast =  new CallNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null),entry,arglist,nestingLevel); 
					}
					break;
				case DOT:
					{
					setState(360); match(DOT);
					setState(361); ((ValueContext)_localctx).im = match(ID);
					setState(362); match(LPAR);

									if (!(entry.getType() instanceof RefTypeNode)) {
										System.out.println((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null)+" at line "+(((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getLine():0)+" is not an object.");
										System.exit(0);
									}
									
									ArrayList<Node> arglist = new ArrayList<Node>();
									String classId = ((RefTypeNode) entry.getType()).getID();
									STentry methodEntry = classTable.get(classId).get((((ValueContext)_localctx).im!=null?((ValueContext)_localctx).im.getText():null));
									if (methodEntry == null) {
										System.out.println("Method Id "+(((ValueContext)_localctx).im!=null?((ValueContext)_localctx).im.getText():null)+" at line "+(((ValueContext)_localctx).im!=null?((ValueContext)_localctx).im.getLine():0)+" not declared");
										System.exit(0);
									}
								
					setState(375);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(364); ((ValueContext)_localctx).fe = exp();
						 arglist.add(((ValueContext)_localctx).fe.ast); 
						setState(372);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(366); match(COMMA);
							setState(367); ((ValueContext)_localctx).e = exp();
							 arglist.add(((ValueContext)_localctx).e.ast); 
							}
							}
							setState(374);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(377); match(RPAR);

								ClassCallNode clCallNode = new ClassCallNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null), (((ValueContext)_localctx).im!=null?((ValueContext)_localctx).im.getText():null), entry, methodEntry, nestingLevel);
								clCallNode.addArgs(arglist);
								((ValueContext)_localctx).ast =  clCallNode;
							
					}
					break;
				case PLUS:
				case MINUS:
				case TIMES:
				case DIV:
				case RPAR:
				case CRPAR:
				case SEMIC:
				case COMMA:
				case OR:
				case AND:
				case GE:
				case LE:
				case EQ:
				case THEN:
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3+\u0182\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2!\n\2\3\2\3\2\3\2\5\2&"+
		"\n\2\3\2\3\2\3\2\3\2\5\2,\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\5\38\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3F\n\3\f\3"+
		"\16\3I\13\3\5\3K\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3`\n\3\f\3\16\3c\13\3\3\3\3\3\5\3g\n\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3u\n\3\f\3\16\3x\13\3"+
		"\3\3\3\3\5\3|\n\3\3\3\3\3\3\3\3\3\7\3\u0082\n\3\f\3\16\3\u0085\13\3\3"+
		"\3\3\3\6\3\u0089\n\3\r\3\16\3\u008a\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\7\4\u00a7\n\4\f\4\16\4\u00aa\13\4\5\4\u00ac\n\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\5\4\u00b5\n\4\3\4\3\4\3\4\5\4\u00ba\n\4\3\4\3\4\3\4\6\4\u00bf\n"+
		"\4\r\4\16\4\u00c0\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00c9\n\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\7\6\u00d3\n\6\f\6\16\6\u00d6\13\6\5\6\u00d8\n\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00e5\n\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00f5\n\b\f\b\16\b\u00f8"+
		"\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u0108"+
		"\n\t\f\t\16\t\u010b\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\7\n\u011b\n\n\f\n\16\n\u011e\13\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13"+
		"\u0132\n\13\f\13\16\13\u0135\13\13\5\13\u0137\n\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0162\n\13\f\13\16"+
		"\13\u0165\13\13\5\13\u0167\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\7\13\u0175\n\13\f\13\16\13\u0178\13\13\5\13\u017a"+
		"\n\13\3\13\3\13\5\13\u017e\n\13\5\13\u0180\n\13\3\13\2\2\f\2\4\6\b\n\f"+
		"\16\20\22\24\2\2\u01a7\2\26\3\2\2\2\4\60\3\2\2\2\6\u008c\3\2\2\2\b\u00c8"+
		"\3\2\2\2\n\u00ca\3\2\2\2\f\u00e4\3\2\2\2\16\u00e6\3\2\2\2\20\u00f9\3\2"+
		"\2\2\22\u010c\3\2\2\2\24\u017f\3\2\2\2\26+\b\2\1\2\27\30\5\16\b\2\30\31"+
		"\b\2\1\2\31,\3\2\2\2\32%\7\34\2\2\33\34\5\4\3\2\34 \b\2\1\2\35\36\5\6"+
		"\4\2\36\37\b\2\1\2\37!\3\2\2\2 \35\3\2\2\2 !\3\2\2\2!&\3\2\2\2\"#\5\6"+
		"\4\2#$\b\2\1\2$&\3\2\2\2%\33\3\2\2\2%\"\3\2\2\2&\'\3\2\2\2\'(\7\35\2\2"+
		"()\5\16\b\2)*\b\2\1\2*,\3\2\2\2+\27\3\2\2\2+\32\3\2\2\2,-\3\2\2\2-.\b"+
		"\2\1\2./\7\13\2\2/\3\3\2\2\2\60\u0088\b\3\1\2\61\62\7 \2\2\62\63\7(\2"+
		"\2\63\67\b\3\1\2\64\65\7!\2\2\65\66\7(\2\2\668\b\3\1\2\67\64\3\2\2\2\67"+
		"8\3\2\2\289\3\2\2\29:\b\3\1\2:J\7\7\2\2;<\7(\2\2<=\7\f\2\2=>\5\f\7\2>"+
		"G\b\3\1\2?@\7\r\2\2@A\7(\2\2AB\7\f\2\2BC\5\f\7\2CD\b\3\1\2DF\3\2\2\2E"+
		"?\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2HK\3\2\2\2IG\3\2\2\2J;\3\2\2\2"+
		"JK\3\2\2\2KL\3\2\2\2LM\7\b\2\2M\u0083\7\t\2\2NO\7\37\2\2OP\7(\2\2PQ\7"+
		"\f\2\2QR\5\f\7\2RS\b\3\1\2ST\7\7\2\2Tf\b\3\1\2UV\7(\2\2VW\7\f\2\2WX\5"+
		"\b\5\2Xa\b\3\1\2YZ\7\r\2\2Z[\7(\2\2[\\\7\f\2\2\\]\5\b\5\2]^\b\3\1\2^`"+
		"\3\2\2\2_Y\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2ca\3\2\2\2d"+
		"e\b\3\1\2eg\3\2\2\2fU\3\2\2\2fg\3\2\2\2gh\3\2\2\2hi\7\b\2\2i{\b\3\1\2"+
		"jv\7\34\2\2kl\7\36\2\2lm\7(\2\2mn\7\f\2\2no\5\f\7\2op\7\25\2\2pq\5\16"+
		"\b\2qr\b\3\1\2rs\7\13\2\2su\3\2\2\2tk\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3"+
		"\2\2\2wy\3\2\2\2xv\3\2\2\2yz\b\3\1\2z|\7\35\2\2{j\3\2\2\2{|\3\2\2\2|}"+
		"\3\2\2\2}~\5\16\b\2~\177\b\3\1\2\177\u0080\7\13\2\2\u0080\u0082\3\2\2"+
		"\2\u0081N\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084"+
		"\3\2\2\2\u0084\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0087\7\n\2\2\u0087"+
		"\u0089\b\3\1\2\u0088\61\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0088\3\2\2"+
		"\2\u008a\u008b\3\2\2\2\u008b\5\3\2\2\2\u008c\u00be\b\4\1\2\u008d\u008e"+
		"\7\36\2\2\u008e\u008f\7(\2\2\u008f\u0090\7\f\2\2\u0090\u0091\5\b\5\2\u0091"+
		"\u0092\7\25\2\2\u0092\u0093\5\16\b\2\u0093\u0094\b\4\1\2\u0094\u00ba\3"+
		"\2\2\2\u0095\u0096\7\37\2\2\u0096\u0097\7(\2\2\u0097\u0098\7\f\2\2\u0098"+
		"\u0099\5\f\7\2\u0099\u009a\b\4\1\2\u009a\u009b\7\7\2\2\u009b\u00ab\b\4"+
		"\1\2\u009c\u009d\7(\2\2\u009d\u009e\7\f\2\2\u009e\u009f\5\b\5\2\u009f"+
		"\u00a8\b\4\1\2\u00a0\u00a1\7\r\2\2\u00a1\u00a2\7(\2\2\u00a2\u00a3\7\f"+
		"\2\2\u00a3\u00a4\5\b\5\2\u00a4\u00a5\b\4\1\2\u00a5\u00a7\3\2\2\2\u00a6"+
		"\u00a0\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2"+
		"\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u009c\3\2\2\2\u00ab"+
		"\u00ac\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\7\b\2\2\u00ae\u00b4\b\4"+
		"\1\2\u00af\u00b0\7\34\2\2\u00b0\u00b1\5\6\4\2\u00b1\u00b2\b\4\1\2\u00b2"+
		"\u00b3\7\35\2\2\u00b3\u00b5\3\2\2\2\u00b4\u00af\3\2\2\2\u00b4\u00b5\3"+
		"\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\5\16\b\2\u00b7\u00b8\b\4\1\2\u00b8"+
		"\u00ba\3\2\2\2\u00b9\u008d\3\2\2\2\u00b9\u0095\3\2\2\2\u00ba\u00bb\3\2"+
		"\2\2\u00bb\u00bc\7\13\2\2\u00bc\u00bd\b\4\1\2\u00bd\u00bf\3\2\2\2\u00be"+
		"\u00b9\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2"+
		"\2\2\u00c1\7\3\2\2\2\u00c2\u00c3\5\f\7\2\u00c3\u00c4\b\5\1\2\u00c4\u00c9"+
		"\3\2\2\2\u00c5\u00c6\5\n\6\2\u00c6\u00c7\b\5\1\2\u00c7\u00c9\3\2\2\2\u00c8"+
		"\u00c2\3\2\2\2\u00c8\u00c5\3\2\2\2\u00c9\t\3\2\2\2\u00ca\u00cb\b\6\1\2"+
		"\u00cb\u00d7\7\7\2\2\u00cc\u00cd\5\b\5\2\u00cd\u00d4\b\6\1\2\u00ce\u00cf"+
		"\7\r\2\2\u00cf\u00d0\5\b\5\2\u00d0\u00d1\b\6\1\2\u00d1\u00d3\3\2\2\2\u00d2"+
		"\u00ce\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2"+
		"\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00cc\3\2\2\2\u00d7"+
		"\u00d8\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da\7\b\2\2\u00da\u00db\7&"+
		"\2\2\u00db\u00dc\5\f\7\2\u00dc\u00dd\b\6\1\2\u00dd\13\3\2\2\2\u00de\u00df"+
		"\7$\2\2\u00df\u00e5\b\7\1\2\u00e0\u00e1\7%\2\2\u00e1\u00e5\b\7\1\2\u00e2"+
		"\u00e3\7(\2\2\u00e3\u00e5\b\7\1\2\u00e4\u00de\3\2\2\2\u00e4\u00e0\3\2"+
		"\2\2\u00e4\u00e2\3\2\2\2\u00e5\r\3\2\2\2\u00e6\u00e7\5\20\t\2\u00e7\u00f6"+
		"\b\b\1\2\u00e8\u00e9\7\3\2\2\u00e9\u00ea\5\20\t\2\u00ea\u00eb\b\b\1\2"+
		"\u00eb\u00f5\3\2\2\2\u00ec\u00ed\7\4\2\2\u00ed\u00ee\5\20\t\2\u00ee\u00ef"+
		"\b\b\1\2\u00ef\u00f5\3\2\2\2\u00f0\u00f1\7\17\2\2\u00f1\u00f2\5\20\t\2"+
		"\u00f2\u00f3\b\b\1\2\u00f3\u00f5\3\2\2\2\u00f4\u00e8\3\2\2\2\u00f4\u00ec"+
		"\3\2\2\2\u00f4\u00f0\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6"+
		"\u00f7\3\2\2\2\u00f7\17\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9\u00fa\5\22\n"+
		"\2\u00fa\u0109\b\t\1\2\u00fb\u00fc\7\5\2\2\u00fc\u00fd\5\22\n\2\u00fd"+
		"\u00fe\b\t\1\2\u00fe\u0108\3\2\2\2\u00ff\u0100\7\6\2\2\u0100\u0101\5\22"+
		"\n\2\u0101\u0102\b\t\1\2\u0102\u0108\3\2\2\2\u0103\u0104\7\20\2\2\u0104"+
		"\u0105\5\22\n\2\u0105\u0106\b\t\1\2\u0106\u0108\3\2\2\2\u0107\u00fb\3"+
		"\2\2\2\u0107\u00ff\3\2\2\2\u0107\u0103\3\2\2\2\u0108\u010b\3\2\2\2\u0109"+
		"\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\21\3\2\2\2\u010b\u0109\3\2\2"+
		"\2\u010c\u010d\5\24\13\2\u010d\u011c\b\n\1\2\u010e\u010f\7\24\2\2\u010f"+
		"\u0110\5\24\13\2\u0110\u0111\b\n\1\2\u0111\u011b\3\2\2\2\u0112\u0113\7"+
		"\22\2\2\u0113\u0114\5\24\13\2\u0114\u0115\b\n\1\2\u0115\u011b\3\2\2\2"+
		"\u0116\u0117\7\23\2\2\u0117\u0118\5\24\13\2\u0118\u0119\b\n\1\2\u0119"+
		"\u011b\3\2\2\2\u011a\u010e\3\2\2\2\u011a\u0112\3\2\2\2\u011a\u0116\3\2"+
		"\2\2\u011b\u011e\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d"+
		"\23\3\2\2\2\u011e\u011c\3\2\2\2\u011f\u0120\7\'\2\2\u0120\u0180\b\13\1"+
		"\2\u0121\u0122\7\26\2\2\u0122\u0180\b\13\1\2\u0123\u0124\7\27\2\2\u0124"+
		"\u0180\b\13\1\2\u0125\u0126\7#\2\2\u0126\u0180\b\13\1\2\u0127\u0128\7"+
		"\"\2\2\u0128\u0129\7(\2\2\u0129\u012a\b\13\1\2\u012a\u0136\7\7\2\2\u012b"+
		"\u012c\5\16\b\2\u012c\u0133\b\13\1\2\u012d\u012e\7\r\2\2\u012e\u012f\5"+
		"\16\b\2\u012f\u0130\b\13\1\2\u0130\u0132\3\2\2\2\u0131\u012d\3\2\2\2\u0132"+
		"\u0135\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0137\3\2"+
		"\2\2\u0135\u0133\3\2\2\2\u0136\u012b\3\2\2\2\u0136\u0137\3\2\2\2\u0137"+
		"\u0138\3\2\2\2\u0138\u0139\b\13\1\2\u0139\u0180\7\b\2\2\u013a\u013b\7"+
		"\7\2\2\u013b\u013c\5\16\b\2\u013c\u013d\7\b\2\2\u013d\u013e\b\13\1\2\u013e"+
		"\u0180\3\2\2\2\u013f\u0140\7\30\2\2\u0140\u0141\5\16\b\2\u0141\u0142\7"+
		"\31\2\2\u0142\u0143\7\t\2\2\u0143\u0144\5\16\b\2\u0144\u0145\7\n\2\2\u0145"+
		"\u0146\7\32\2\2\u0146\u0147\7\t\2\2\u0147\u0148\5\16\b\2\u0148\u0149\7"+
		"\n\2\2\u0149\u014a\b\13\1\2\u014a\u0180\3\2\2\2\u014b\u014c\7\21\2\2\u014c"+
		"\u014d\7\7\2\2\u014d\u014e\5\16\b\2\u014e\u014f\b\13\1\2\u014f\u0150\7"+
		"\b\2\2\u0150\u0180\3\2\2\2\u0151\u0152\7\33\2\2\u0152\u0153\7\7\2\2\u0153"+
		"\u0154\5\16\b\2\u0154\u0155\7\b\2\2\u0155\u0156\b\13\1\2\u0156\u0180\3"+
		"\2\2\2\u0157\u0158\7(\2\2\u0158\u017d\b\13\1\2\u0159\u015a\7\7\2\2\u015a"+
		"\u0166\b\13\1\2\u015b\u015c\5\16\b\2\u015c\u0163\b\13\1\2\u015d\u015e"+
		"\7\r\2\2\u015e\u015f\5\16\b\2\u015f\u0160\b\13\1\2\u0160\u0162\3\2\2\2"+
		"\u0161\u015d\3\2\2\2\u0162\u0165\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0164"+
		"\3\2\2\2\u0164\u0167\3\2\2\2\u0165\u0163\3\2\2\2\u0166\u015b\3\2\2\2\u0166"+
		"\u0167\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u0169\7\b\2\2\u0169\u017e\b\13"+
		"\1\2\u016a\u016b\7\16\2\2\u016b\u016c\7(\2\2\u016c\u016d\7\7\2\2\u016d"+
		"\u0179\b\13\1\2\u016e\u016f\5\16\b\2\u016f\u0176\b\13\1\2\u0170\u0171"+
		"\7\r\2\2\u0171\u0172\5\16\b\2\u0172\u0173\b\13\1\2\u0173\u0175\3\2\2\2"+
		"\u0174\u0170\3\2\2\2\u0175\u0178\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0177"+
		"\3\2\2\2\u0177\u017a\3\2\2\2\u0178\u0176\3\2\2\2\u0179\u016e\3\2\2\2\u0179"+
		"\u017a\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u017c\7\b\2\2\u017c\u017e\b\13"+
		"\1\2\u017d\u0159\3\2\2\2\u017d\u016a\3\2\2\2\u017d\u017e\3\2\2\2\u017e"+
		"\u0180\3\2\2\2\u017f\u011f\3\2\2\2\u017f\u0121\3\2\2\2\u017f\u0123\3\2"+
		"\2\2\u017f\u0125\3\2\2\2\u017f\u0127\3\2\2\2\u017f\u013a\3\2\2\2\u017f"+
		"\u013f\3\2\2\2\u017f\u014b\3\2\2\2\u017f\u0151\3\2\2\2\u017f\u0157\3\2"+
		"\2\2\u0180\25\3\2\2\2% %+\67GJafv{\u0083\u008a\u00a8\u00ab\u00b4\u00b9"+
		"\u00c0\u00c8\u00d4\u00d7\u00e4\u00f4\u00f6\u0107\u0109\u011a\u011c\u0133"+
		"\u0136\u0163\u0166\u0176\u0179\u017d\u017f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}