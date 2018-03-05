// Generated from FOOL.g4 by ANTLR 4.4

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
				
			setState(36);
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
				setState(30);
				switch (_input.LA(1)) {
				case CLASS:
					{
					setState(25); cllist();
					setState(27);
					_la = _input.LA(1);
					if (_la==VAR || _la==FUN) {
						{
						setState(26); declist();
						}
					}

					}
					break;
				case VAR:
				case FUN:
					{
					setState(29); ((ProgContext)_localctx).d = declist();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(32); match(IN);
				setState(33); ((ProgContext)_localctx).e = exp();
				 
							((ProgContext)_localctx).ast =  new ProgLetInNode(((ProgContext)_localctx).d.astlist,((ProgContext)_localctx).e.ast);
						
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
				
					symTable.remove(nestingLevel);
				
			setState(39); match(SEMIC);
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
		public Token ic;
		public Token ic1;
		public Token campo;
		public TypeContext t;
		public Token campo1;
		public TypeContext t1;
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
					int offset = -2; /* Indice di convenzione di inizio (che viene decrementato) */
			setState(117); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42); match(CLASS);
				setState(43); ((CllistContext)_localctx).ic = match(ID);
				ClassNode classNode = new ClassNode((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null));
					 		 boolean isExtends=false;	
					 		 offset--; 		
					 		
				setState(48);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(45); match(EXTENDS);
					setState(46); ((CllistContext)_localctx).ic1 = match(ID);
					isExtends = true;
					}
				}


					 		if(isExtends == false){
					 			HashMap<String,STentry> sym = symTable.get(nestingLevel);
					 			STentry cstentry = new STentry(nestingLevel, new ClassTypeNode(new ArrayList<Node>(),new ArrayList<Node>()),offset);
					 		 	if(sym.put((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null),cstentry) != null) {
									System.out.println("Class id" + (((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null) + " at line " + (((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getLine():0) + " already created.");
									System.exit(0);
								}; 
								nestingLevel++;
								HashMap<String, STentry> virtualTable = new HashMap<String, STentry>();
								symTable.add(nestingLevel,virtualTable);
								  if(classTable.put((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null), virtualTable) != null) {
				                   System.out.println("Class "+(((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)+" at line "+(((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getLine():0)+" already declared");
				                   System.exit(0); 
				                } 
					 		 	
					 		}else{
					 			HashMap<String,STentry> hm1 = classTable.get((((CllistContext)_localctx).ic1!=null?((CllistContext)_localctx).ic1.getText():null));
								symTable.add(hm1);
								classTable.put((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null), classTable.get((((CllistContext)_localctx).ic1!=null?((CllistContext)_localctx).ic1.getText():null))); /* copio vtable della classe ereditata*/
					 		}
					 	
				setState(51); match(LPAR);
				setState(67);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(52); ((CllistContext)_localctx).campo = match(ID);
					setState(53); match(COLON);
					setState(54); ((CllistContext)_localctx).t = type();
						
						 	  		int offsetCampo=0;
						 	  		STentry entry = new STentry(nestingLevel, ((CllistContext)_localctx).t.ast, offsetCampo--);
						 	  		if( classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).put((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null),entry) != null){
						 	  			STentry oldEntry = classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).get((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null));
						 	  			oldEntry.addType(((CllistContext)_localctx).t.ast);
						 	  		}
						 	  		
						 	  	
					setState(64);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(56); match(COMMA);
						setState(57); ((CllistContext)_localctx).campo1 = match(ID);
						setState(58); match(COLON);
						setState(59); ((CllistContext)_localctx).t1 = type();
						 STentry entry1 = new STentry(nestingLevel, ((CllistContext)_localctx).t1.ast, offsetCampo--);
							 	  		if( classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).put((((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getText():null),entry) != null){
							 	  			STentry oldEntry = classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).get((((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getText():null));
							 	  			oldEntry.addType(((CllistContext)_localctx).t1.ast);
							 	  		}
						}
						}
						setState(66);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(69); match(RPAR);
				setState(70); match(CLPAR);
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FUN) {
					{
					{
					setState(71); match(FUN);
					setState(72); match(ID);
					setState(73); match(COLON);
					setState(74); type();
					setState(75); match(LPAR);
					setState(88);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(76); match(ID);
						setState(77); match(COLON);
						setState(78); hotype();
						setState(85);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(79); match(COMMA);
							setState(80); match(ID);
							setState(81); match(COLON);
							setState(82); hotype();
							}
							}
							setState(87);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(90); match(RPAR);
					setState(106);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(91); match(LET);
						setState(102);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==VAR) {
							{
							{
							setState(92); match(VAR);
							setState(93); match(ID);
							setState(94); match(COLON);
							setState(95); type();
							setState(96); match(ASS);
							setState(97); exp();
							setState(98); match(SEMIC);
							}
							}
							setState(104);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(105); match(IN);
						}
					}

					setState(108); exp();
					setState(109); match(SEMIC);
					}
					}
					setState(115);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(116); match(CRPAR);
				}
				}
				setState(119); 
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
					int offset = -2; /* Indice di convenzione di inizio (che viene decrementato) */ 
				
			setState(170); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(166);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(122); match(VAR);
					setState(123); ((DeclistContext)_localctx).i = match(ID);
					setState(124); match(COLON);
					setState(125); ((DeclistContext)_localctx).h = hotype();
					setState(126); match(ASS);
					setState(127); ((DeclistContext)_localctx).e = exp();
						VarNode v = new VarNode((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),((DeclistContext)_localctx).h.ast,((DeclistContext)_localctx).e.ast);
									_localctx.astlist.add(v);
									HashMap<String,STentry> hm = symTable.get(nestingLevel); /* Tabella del livello corrente (detta tabella del fronte) */
									/* Verificare che nello scope attuale (il fronte della tabella), la variabile sia gi� stata dichiarata. "put" sostituisce, ma se la chiave era gi� occupata restituisce la coppia vecchia, altrimenti null.*/ 
									if(hm.put((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null), new STentry(nestingLevel,((DeclistContext)_localctx).h.ast,offset--)) != null) {
										/*Errore identificatore (variabile) gi� dichiarata*/
										System.out.println("Var id" + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null) + " at line " + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getLine():0) + " already declared.");
										System.exit(0);
									}; 
									{if(((DeclistContext)_localctx).h.ast instanceof ArrowTypeNode) offset--;} /*offset doppio per tipi funzionali*/
								
					}
					break;
				case FUN:
					{
					setState(130); match(FUN);
					setState(131); ((DeclistContext)_localctx).i = match(ID);
					setState(132); match(COLON);
					setState(133); ((DeclistContext)_localctx).t = type();
						
									FunNode f = new FunNode((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),((DeclistContext)_localctx).t.ast);
									_localctx.astlist.add(f);
									HashMap<String,STentry> hm = symTable.get(nestingLevel);
									/* Verificare che nello scope attuale (il fronte della tabella), la funzione sia gi� stata dichiarata. "put" sostituisce, ma se la chiave era gi� occupata restituisce la coppia vecchia, altrimenti null.*/
									STentry entry = new STentry(nestingLevel,offset--);
									offset--;
									if(hm.put((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null), entry) != null) {
										System.out.println("Fun id" + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null) + " at line " + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getLine():0) + " already declared.");
										System.exit(0);
									}
									/* Entro dentro un nuovo scope. */
									nestingLevel++;  /* Aumento il livello perch� sono all'interno di una funzione (anche i parametri passati alla funzione rientrano nel livello interno)*/
									HashMap<String,STentry> hmn = new HashMap<String,STentry>();
									symTable.add(hmn);
								
					setState(135); match(LPAR);
						ArrayList<Node> parTypes = new ArrayList<Node>();
										int parOffset = 1;
									
					setState(152);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(137); ((DeclistContext)_localctx).i1 = match(ID);
						setState(138); match(COLON);
						setState(139); ((DeclistContext)_localctx).fty = hotype();
						 /* Creare il ParNode, lo attacco al FunNode invocando addPar, aggiungo una STentry alla hashmap hmn*/
												parTypes.add(((DeclistContext)_localctx).fty.ast);
												ParNode p1 = new ParNode((((DeclistContext)_localctx).i1!=null?((DeclistContext)_localctx).i1.getText():null),((DeclistContext)_localctx).fty.ast);
												f.addPar(p1);
												{if(((DeclistContext)_localctx).fty.ast instanceof ArrowTypeNode) parOffset++;}
												if (hmn.put((((DeclistContext)_localctx).i1!=null?((DeclistContext)_localctx).i1.getText():null), new STentry(nestingLevel,((DeclistContext)_localctx).fty.ast,parOffset++)) != null) {
													/* Errore identificatore (parametro) gi� dichiarato*/
													System.out.println("Par ID: " + (((DeclistContext)_localctx).i1!=null?((DeclistContext)_localctx).i1.getText():null) + " at line " + (((DeclistContext)_localctx).i1!=null?((DeclistContext)_localctx).i1.getLine():0) + " already declared");
													System.exit(0);
												}
												
											
						setState(149);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(141); match(COMMA);
							setState(142); ((DeclistContext)_localctx).i2 = match(ID);
							setState(143); match(COLON);
							setState(144); ((DeclistContext)_localctx).ty = hotype();
							/* Creare il ParNode, lo attacco al FunNode invocando addPar, aggiungo una STentry alla hashmap hmn */
													parTypes.add(((DeclistContext)_localctx).ty.ast);
													ParNode p2 = new ParNode((((DeclistContext)_localctx).i2!=null?((DeclistContext)_localctx).i2.getText():null),((DeclistContext)_localctx).ty.ast);
													f.addPar(p2);
													{if(((DeclistContext)_localctx).ty.ast instanceof ArrowTypeNode) parOffset++;}
													if (hmn.put((((DeclistContext)_localctx).i2!=null?((DeclistContext)_localctx).i2.getText():null), new STentry(nestingLevel,((DeclistContext)_localctx).ty.ast,parOffset++)) != null){
														/* Errore identificatore (parametro) gi� dichiarato */
														System.out.println("Par ID: " + (((DeclistContext)_localctx).i2!=null?((DeclistContext)_localctx).i2.getText():null) + " at line " + (((DeclistContext)_localctx).i2!=null?((DeclistContext)_localctx).i2.getLine():0) + " already declared");
														System.exit(0);
													}
													
												
							}
							}
							setState(151);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(154); match(RPAR);
					 entry.addType(new ArrowTypeNode(parTypes,((DeclistContext)_localctx).t.ast)); 
					setState(161);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(156); match(LET);
						setState(157); ((DeclistContext)_localctx).d = declist();
						setState(158); match(IN);
						f.addDec(((DeclistContext)_localctx).d.astlist);
						}
					}

					setState(163); ((DeclistContext)_localctx).e = exp();
						
										symTable.remove(nestingLevel--); /* Diminuisco nestingLevel perch� esco dallo scope della funzione */
										f.addBody(((DeclistContext)_localctx).e.ast);
									
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(168); match(SEMIC);
				}
				}
				setState(172); 
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
			setState(180);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(174); ((HotypeContext)_localctx).t = type();
				((HotypeContext)_localctx).ast =  ((HotypeContext)_localctx).t.ast;
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(177); ((HotypeContext)_localctx).a = arrow();
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
			setState(183); match(LPAR);
			setState(195);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(184); ((ArrowContext)_localctx).h = hotype();
				hotypeList.add(((ArrowContext)_localctx).h.ast);
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(186); match(COMMA);
					setState(187); ((ArrowContext)_localctx).h1 = hotype();
					hotypeList.add(((ArrowContext)_localctx).h1.ast);
					}
					}
					setState(194);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(197); match(RPAR);
			setState(198); match(ARROW);
			setState(199); ((ArrowContext)_localctx).t = type();
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
			setState(208);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(202); match(INT);
				((TypeContext)_localctx).ast =  new IntTypeNode();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(204); match(BOOL);
				((TypeContext)_localctx).ast =  new BoolTypeNode();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(206); match(ID);
				((TypeContext)_localctx).ast =  new IdTypeNode();
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
			setState(210); ((ExpContext)_localctx).t = term();
			((ExpContext)_localctx).ast =  ((ExpContext)_localctx).t.ast;
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0)) {
				{
				setState(224);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(212); match(PLUS);
					setState(213); ((ExpContext)_localctx).p = term();
					((ExpContext)_localctx).ast =  new PlusNode(_localctx.ast,((ExpContext)_localctx).p.ast);
					}
					break;
				case MINUS:
					{
					setState(216); match(MINUS);
					setState(217); ((ExpContext)_localctx).m = term();
					((ExpContext)_localctx).ast =  new MinusNode(_localctx.ast, ((ExpContext)_localctx).m.ast);
					}
					break;
				case OR:
					{
					setState(220); match(OR);
					setState(221); ((ExpContext)_localctx).o = term();
					((ExpContext)_localctx).ast =  new OrNode(_localctx.ast, ((ExpContext)_localctx).o.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(228);
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
			setState(229); ((TermContext)_localctx).f = factor();
			((TermContext)_localctx).ast =  ((TermContext)_localctx).f.ast;
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TIMES) | (1L << DIV) | (1L << AND))) != 0)) {
				{
				setState(243);
				switch (_input.LA(1)) {
				case TIMES:
					{
					setState(231); match(TIMES);
					setState(232); ((TermContext)_localctx).t = factor();
					((TermContext)_localctx).ast =  new TimesNode(_localctx.ast,((TermContext)_localctx).t.ast);
					}
					break;
				case DIV:
					{
					setState(235); match(DIV);
					setState(236); ((TermContext)_localctx).d = factor();
					((TermContext)_localctx).ast =  new DivNode(_localctx.ast,((TermContext)_localctx).d.ast);
					}
					break;
				case AND:
					{
					setState(239); match(AND);
					setState(240); ((TermContext)_localctx).a = factor();
					 ((TermContext)_localctx).ast =  new AndNode(_localctx.ast,((TermContext)_localctx).a.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(247);
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
			setState(248); ((FactorContext)_localctx).v = value();
			((FactorContext)_localctx).ast =  ((FactorContext)_localctx).v.ast;
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << LE) | (1L << EQ))) != 0)) {
				{
				setState(262);
				switch (_input.LA(1)) {
				case EQ:
					{
					setState(250); match(EQ);
					setState(251); ((FactorContext)_localctx).e = value();
					((FactorContext)_localctx).ast =  new EqualNode(_localctx.ast,((FactorContext)_localctx).e.ast);
					}
					break;
				case GE:
					{
					setState(254); match(GE);
					setState(255); ((FactorContext)_localctx).g = value();
					((FactorContext)_localctx).ast =  new GENode(_localctx.ast,((FactorContext)_localctx).g.ast);
					}
					break;
				case LE:
					{
					setState(258); match(LE);
					setState(259); ((FactorContext)_localctx).l = value();
					((FactorContext)_localctx).ast =  new LENode(_localctx.ast,((FactorContext)_localctx).l.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(266);
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
		public ExpContext e;
		public ExpContext e1;
		public ExpContext e2;
		public ExpContext e3;
		public Token i;
		public ExpContext a;
		public ExpContext a1;
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
			setState(353);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(267); ((ValueContext)_localctx).in = match(INTEGER);
				((ValueContext)_localctx).ast =  new IntNode(Integer.parseInt((((ValueContext)_localctx).in!=null?((ValueContext)_localctx).in.getText():null)));
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(269); match(TRUE);
				((ValueContext)_localctx).ast =  new BoolNode(true);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(271); match(FALSE);
				((ValueContext)_localctx).ast =  new BoolNode(false);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(273); match(NULL);
				((ValueContext)_localctx).ast =  new EmptyNode();
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 5);
				{
				setState(275); match(NEW);
				setState(276); match(ID);
				setState(277); match(LPAR);
				setState(286);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(278); exp();
					setState(283);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(279); match(COMMA);
						setState(280); exp();
						}
						}
						setState(285);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(288); match(RPAR);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 6);
				{
				setState(289); match(LPAR);
				setState(290); ((ValueContext)_localctx).e = exp();
				setState(291); match(RPAR);
				((ValueContext)_localctx).ast =  ((ValueContext)_localctx).e.ast;
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 7);
				{
				setState(294); match(IF);
				setState(295); ((ValueContext)_localctx).e1 = exp();
				setState(296); match(THEN);
				setState(297); match(CLPAR);
				setState(298); ((ValueContext)_localctx).e2 = exp();
				setState(299); match(CRPAR);
				setState(300); match(ELSE);
				setState(301); match(CLPAR);
				setState(302); ((ValueContext)_localctx).e3 = exp();
				setState(303); match(CRPAR);
				((ValueContext)_localctx).ast =  new IfNode(((ValueContext)_localctx).e1.ast,((ValueContext)_localctx).e2.ast,((ValueContext)_localctx).e3.ast);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 8);
				{
				setState(306); match(NOT);
				setState(307); match(LPAR);
				setState(308); ((ValueContext)_localctx).e = exp();
				((ValueContext)_localctx).ast =  new NotNode(((ValueContext)_localctx).e.ast);
				setState(310); match(RPAR);
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 9);
				{
				setState(312); match(PRINT);
				setState(313); match(LPAR);
				setState(314); ((ValueContext)_localctx).e = exp();
				setState(315); match(RPAR);
				((ValueContext)_localctx).ast =  new PrintNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 10);
				{
				setState(318); ((ValueContext)_localctx).i = match(ID);
					/* Cerco la dichiarazione dentro la symbol table e il livello di scope corrente fino allo scope globale (level = 0)*/
							int j = nestingLevel;
							STentry entry = null;
							while(j>=0 && entry==null) {
								entry = symTable.get(j--).get((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null));
							}
							if(entry==null) { /* Dichiarazione non presente nella symbol table quindi variabile non dichiarata*/
								System.out.println("Id" + (((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null) + " at line " + (((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getLine():0) + " not declared.");
								System.exit(0);
							}
							((ValueContext)_localctx).ast =  new IdNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null), entry, nestingLevel); /* Inserito il nestinglevel per verifiche sullo scope della variabile */
						
				setState(351);
				switch (_input.LA(1)) {
				case LPAR:
					{
					setState(320); match(LPAR);
					 ArrayList<Node> arglist = new ArrayList<Node>(); 
					setState(333);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(322); ((ValueContext)_localctx).a = exp();
						 arglist.add(((ValueContext)_localctx).a.ast); 
						setState(330);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(324); match(COMMA);
							setState(325); ((ValueContext)_localctx).a1 = exp();
							 arglist.add(((ValueContext)_localctx).a1.ast); 
							}
							}
							setState(332);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(335); match(RPAR);
					 ((ValueContext)_localctx).ast =  new CallNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null),entry,arglist,nestingLevel); 
					}
					break;
				case DOT:
					{
					setState(337); match(DOT);
					setState(338); match(ID);
					setState(339); match(LPAR);
					setState(348);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(340); exp();
						setState(345);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(341); match(COMMA);
							setState(342); exp();
							}
							}
							setState(347);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(350); match(RPAR);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3+\u0166\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\36\n\2\3\2\5\2!\n\2\3\2\3\2\3\2\3"+
		"\2\5\2\'\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\63\n\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3A\n\3\f\3\16\3D\13\3\5\3"+
		"F\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3V\n\3"+
		"\f\3\16\3Y\13\3\5\3[\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3g"+
		"\n\3\f\3\16\3j\13\3\3\3\5\3m\n\3\3\3\3\3\3\3\7\3r\n\3\f\3\16\3u\13\3\3"+
		"\3\6\3x\n\3\r\3\16\3y\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u0096\n\4"+
		"\f\4\16\4\u0099\13\4\5\4\u009b\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u00a4"+
		"\n\4\3\4\3\4\3\4\5\4\u00a9\n\4\3\4\3\4\6\4\u00ad\n\4\r\4\16\4\u00ae\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\5\5\u00b7\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7"+
		"\6\u00c1\n\6\f\6\16\6\u00c4\13\6\5\6\u00c6\n\6\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\5\7\u00d3\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\7\b\u00e3\n\b\f\b\16\b\u00e6\13\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00f6\n\t\f\t\16\t\u00f9"+
		"\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0109"+
		"\n\n\f\n\16\n\u010c\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\7\13\u011c\n\13\f\13\16\13\u011f\13\13\5\13"+
		"\u0121\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\7\13\u014b\n\13\f\13\16\13\u014e\13\13\5\13\u0150\n\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\7\13\u015a\n\13\f\13\16\13\u015d\13\13\5\13"+
		"\u015f\n\13\3\13\5\13\u0162\n\13\5\13\u0164\n\13\3\13\2\2\f\2\4\6\b\n"+
		"\f\16\20\22\24\2\2\u018b\2\26\3\2\2\2\4+\3\2\2\2\6{\3\2\2\2\b\u00b6\3"+
		"\2\2\2\n\u00b8\3\2\2\2\f\u00d2\3\2\2\2\16\u00d4\3\2\2\2\20\u00e7\3\2\2"+
		"\2\22\u00fa\3\2\2\2\24\u0163\3\2\2\2\26&\b\2\1\2\27\30\5\16\b\2\30\31"+
		"\b\2\1\2\31\'\3\2\2\2\32 \7\34\2\2\33\35\5\4\3\2\34\36\5\6\4\2\35\34\3"+
		"\2\2\2\35\36\3\2\2\2\36!\3\2\2\2\37!\5\6\4\2 \33\3\2\2\2 \37\3\2\2\2!"+
		"\"\3\2\2\2\"#\7\35\2\2#$\5\16\b\2$%\b\2\1\2%\'\3\2\2\2&\27\3\2\2\2&\32"+
		"\3\2\2\2\'(\3\2\2\2()\b\2\1\2)*\7\13\2\2*\3\3\2\2\2+w\b\3\1\2,-\7 \2\2"+
		"-.\7(\2\2.\62\b\3\1\2/\60\7!\2\2\60\61\7(\2\2\61\63\b\3\1\2\62/\3\2\2"+
		"\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65\b\3\1\2\65E\7\7\2\2\66\67\7(\2\2"+
		"\678\7\f\2\289\5\f\7\29B\b\3\1\2:;\7\r\2\2;<\7(\2\2<=\7\f\2\2=>\5\f\7"+
		"\2>?\b\3\1\2?A\3\2\2\2@:\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2CF\3\2\2"+
		"\2DB\3\2\2\2E\66\3\2\2\2EF\3\2\2\2FG\3\2\2\2GH\7\b\2\2Hs\7\t\2\2IJ\7\37"+
		"\2\2JK\7(\2\2KL\7\f\2\2LM\5\f\7\2MZ\7\7\2\2NO\7(\2\2OP\7\f\2\2PW\5\b\5"+
		"\2QR\7\r\2\2RS\7(\2\2ST\7\f\2\2TV\5\b\5\2UQ\3\2\2\2VY\3\2\2\2WU\3\2\2"+
		"\2WX\3\2\2\2X[\3\2\2\2YW\3\2\2\2ZN\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2\\l\7\b"+
		"\2\2]h\7\34\2\2^_\7\36\2\2_`\7(\2\2`a\7\f\2\2ab\5\f\7\2bc\7\25\2\2cd\5"+
		"\16\b\2de\7\13\2\2eg\3\2\2\2f^\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2i"+
		"k\3\2\2\2jh\3\2\2\2km\7\35\2\2l]\3\2\2\2lm\3\2\2\2mn\3\2\2\2no\5\16\b"+
		"\2op\7\13\2\2pr\3\2\2\2qI\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tv\3\2"+
		"\2\2us\3\2\2\2vx\7\n\2\2w,\3\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2z\5\3"+
		"\2\2\2{\u00ac\b\4\1\2|}\7\36\2\2}~\7(\2\2~\177\7\f\2\2\177\u0080\5\b\5"+
		"\2\u0080\u0081\7\25\2\2\u0081\u0082\5\16\b\2\u0082\u0083\b\4\1\2\u0083"+
		"\u00a9\3\2\2\2\u0084\u0085\7\37\2\2\u0085\u0086\7(\2\2\u0086\u0087\7\f"+
		"\2\2\u0087\u0088\5\f\7\2\u0088\u0089\b\4\1\2\u0089\u008a\7\7\2\2\u008a"+
		"\u009a\b\4\1\2\u008b\u008c\7(\2\2\u008c\u008d\7\f\2\2\u008d\u008e\5\b"+
		"\5\2\u008e\u0097\b\4\1\2\u008f\u0090\7\r\2\2\u0090\u0091\7(\2\2\u0091"+
		"\u0092\7\f\2\2\u0092\u0093\5\b\5\2\u0093\u0094\b\4\1\2\u0094\u0096\3\2"+
		"\2\2\u0095\u008f\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u008b\3\2"+
		"\2\2\u009a\u009b\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\7\b\2\2\u009d"+
		"\u00a3\b\4\1\2\u009e\u009f\7\34\2\2\u009f\u00a0\5\6\4\2\u00a0\u00a1\7"+
		"\35\2\2\u00a1\u00a2\b\4\1\2\u00a2\u00a4\3\2\2\2\u00a3\u009e\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\5\16\b\2\u00a6\u00a7\b"+
		"\4\1\2\u00a7\u00a9\3\2\2\2\u00a8|\3\2\2\2\u00a8\u0084\3\2\2\2\u00a9\u00aa"+
		"\3\2\2\2\u00aa\u00ab\7\13\2\2\u00ab\u00ad\3\2\2\2\u00ac\u00a8\3\2\2\2"+
		"\u00ad\u00ae\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\7\3"+
		"\2\2\2\u00b0\u00b1\5\f\7\2\u00b1\u00b2\b\5\1\2\u00b2\u00b7\3\2\2\2\u00b3"+
		"\u00b4\5\n\6\2\u00b4\u00b5\b\5\1\2\u00b5\u00b7\3\2\2\2\u00b6\u00b0\3\2"+
		"\2\2\u00b6\u00b3\3\2\2\2\u00b7\t\3\2\2\2\u00b8\u00b9\b\6\1\2\u00b9\u00c5"+
		"\7\7\2\2\u00ba\u00bb\5\b\5\2\u00bb\u00c2\b\6\1\2\u00bc\u00bd\7\r\2\2\u00bd"+
		"\u00be\5\b\5\2\u00be\u00bf\b\6\1\2\u00bf\u00c1\3\2\2\2\u00c0\u00bc\3\2"+
		"\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3"+
		"\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00ba\3\2\2\2\u00c5\u00c6\3\2"+
		"\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\7\b\2\2\u00c8\u00c9\7&\2\2\u00c9"+
		"\u00ca\5\f\7\2\u00ca\u00cb\b\6\1\2\u00cb\13\3\2\2\2\u00cc\u00cd\7$\2\2"+
		"\u00cd\u00d3\b\7\1\2\u00ce\u00cf\7%\2\2\u00cf\u00d3\b\7\1\2\u00d0\u00d1"+
		"\7(\2\2\u00d1\u00d3\b\7\1\2\u00d2\u00cc\3\2\2\2\u00d2\u00ce\3\2\2\2\u00d2"+
		"\u00d0\3\2\2\2\u00d3\r\3\2\2\2\u00d4\u00d5\5\20\t\2\u00d5\u00e4\b\b\1"+
		"\2\u00d6\u00d7\7\3\2\2\u00d7\u00d8\5\20\t\2\u00d8\u00d9\b\b\1\2\u00d9"+
		"\u00e3\3\2\2\2\u00da\u00db\7\4\2\2\u00db\u00dc\5\20\t\2\u00dc\u00dd\b"+
		"\b\1\2\u00dd\u00e3\3\2\2\2\u00de\u00df\7\17\2\2\u00df\u00e0\5\20\t\2\u00e0"+
		"\u00e1\b\b\1\2\u00e1\u00e3\3\2\2\2\u00e2\u00d6\3\2\2\2\u00e2\u00da\3\2"+
		"\2\2\u00e2\u00de\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4"+
		"\u00e5\3\2\2\2\u00e5\17\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e8\5\22\n"+
		"\2\u00e8\u00f7\b\t\1\2\u00e9\u00ea\7\5\2\2\u00ea\u00eb\5\22\n\2\u00eb"+
		"\u00ec\b\t\1\2\u00ec\u00f6\3\2\2\2\u00ed\u00ee\7\6\2\2\u00ee\u00ef\5\22"+
		"\n\2\u00ef\u00f0\b\t\1\2\u00f0\u00f6\3\2\2\2\u00f1\u00f2\7\20\2\2\u00f2"+
		"\u00f3\5\22\n\2\u00f3\u00f4\b\t\1\2\u00f4\u00f6\3\2\2\2\u00f5\u00e9\3"+
		"\2\2\2\u00f5\u00ed\3\2\2\2\u00f5\u00f1\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7"+
		"\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\21\3\2\2\2\u00f9\u00f7\3\2\2"+
		"\2\u00fa\u00fb\5\24\13\2\u00fb\u010a\b\n\1\2\u00fc\u00fd\7\24\2\2\u00fd"+
		"\u00fe\5\24\13\2\u00fe\u00ff\b\n\1\2\u00ff\u0109\3\2\2\2\u0100\u0101\7"+
		"\22\2\2\u0101\u0102\5\24\13\2\u0102\u0103\b\n\1\2\u0103\u0109\3\2\2\2"+
		"\u0104\u0105\7\23\2\2\u0105\u0106\5\24\13\2\u0106\u0107\b\n\1\2\u0107"+
		"\u0109\3\2\2\2\u0108\u00fc\3\2\2\2\u0108\u0100\3\2\2\2\u0108\u0104\3\2"+
		"\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		"\23\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u010e\7\'\2\2\u010e\u0164\b\13\1"+
		"\2\u010f\u0110\7\26\2\2\u0110\u0164\b\13\1\2\u0111\u0112\7\27\2\2\u0112"+
		"\u0164\b\13\1\2\u0113\u0114\7#\2\2\u0114\u0164\b\13\1\2\u0115\u0116\7"+
		"\"\2\2\u0116\u0117\7(\2\2\u0117\u0120\7\7\2\2\u0118\u011d\5\16\b\2\u0119"+
		"\u011a\7\r\2\2\u011a\u011c\5\16\b\2\u011b\u0119\3\2\2\2\u011c\u011f\3"+
		"\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u0121\3\2\2\2\u011f"+
		"\u011d\3\2\2\2\u0120\u0118\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0122\3\2"+
		"\2\2\u0122\u0164\7\b\2\2\u0123\u0124\7\7\2\2\u0124\u0125\5\16\b\2\u0125"+
		"\u0126\7\b\2\2\u0126\u0127\b\13\1\2\u0127\u0164\3\2\2\2\u0128\u0129\7"+
		"\30\2\2\u0129\u012a\5\16\b\2\u012a\u012b\7\31\2\2\u012b\u012c\7\t\2\2"+
		"\u012c\u012d\5\16\b\2\u012d\u012e\7\n\2\2\u012e\u012f\7\32\2\2\u012f\u0130"+
		"\7\t\2\2\u0130\u0131\5\16\b\2\u0131\u0132\7\n\2\2\u0132\u0133\b\13\1\2"+
		"\u0133\u0164\3\2\2\2\u0134\u0135\7\21\2\2\u0135\u0136\7\7\2\2\u0136\u0137"+
		"\5\16\b\2\u0137\u0138\b\13\1\2\u0138\u0139\7\b\2\2\u0139\u0164\3\2\2\2"+
		"\u013a\u013b\7\33\2\2\u013b\u013c\7\7\2\2\u013c\u013d\5\16\b\2\u013d\u013e"+
		"\7\b\2\2\u013e\u013f\b\13\1\2\u013f\u0164\3\2\2\2\u0140\u0141\7(\2\2\u0141"+
		"\u0161\b\13\1\2\u0142\u0143\7\7\2\2\u0143\u014f\b\13\1\2\u0144\u0145\5"+
		"\16\b\2\u0145\u014c\b\13\1\2\u0146\u0147\7\r\2\2\u0147\u0148\5\16\b\2"+
		"\u0148\u0149\b\13\1\2\u0149\u014b\3\2\2\2\u014a\u0146\3\2\2\2\u014b\u014e"+
		"\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u0150\3\2\2\2\u014e"+
		"\u014c\3\2\2\2\u014f\u0144\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151\3\2"+
		"\2\2\u0151\u0152\7\b\2\2\u0152\u0162\b\13\1\2\u0153\u0154\7\16\2\2\u0154"+
		"\u0155\7(\2\2\u0155\u015e\7\7\2\2\u0156\u015b\5\16\b\2\u0157\u0158\7\r"+
		"\2\2\u0158\u015a\5\16\b\2\u0159\u0157\3\2\2\2\u015a\u015d\3\2\2\2\u015b"+
		"\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015f\3\2\2\2\u015d\u015b\3\2"+
		"\2\2\u015e\u0156\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"\u0162\7\b\2\2\u0161\u0142\3\2\2\2\u0161\u0153\3\2\2\2\u0161\u0162\3\2"+
		"\2\2\u0162\u0164\3\2\2\2\u0163\u010d\3\2\2\2\u0163\u010f\3\2\2\2\u0163"+
		"\u0111\3\2\2\2\u0163\u0113\3\2\2\2\u0163\u0115\3\2\2\2\u0163\u0123\3\2"+
		"\2\2\u0163\u0128\3\2\2\2\u0163\u0134\3\2\2\2\u0163\u013a\3\2\2\2\u0163"+
		"\u0140\3\2\2\2\u0164\25\3\2\2\2%\35 &\62BEWZhlsy\u0097\u009a\u00a3\u00a8"+
		"\u00ae\u00b6\u00c2\u00c5\u00d2\u00e2\u00e4\u00f5\u00f7\u0108\u010a\u011d"+
		"\u0120\u014c\u014f\u015b\u015e\u0161\u0163";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}