// Generated from FOOL.g4 by ANTLR 4.4

	import java.util.HashMap;
	import ast.*;

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
		SEMIC=1, COLON=2, COMMA=3, EQ=4, ASS=5, PLUS=6, TIMES=7, INTEGER=8, TRUE=9, 
		FALSE=10, LPAR=11, RPAR=12, CLPAR=13, CRPAR=14, IF=15, THEN=16, ELSE=17, 
		PRINT=18, LET=19, IN=20, VAR=21, FUN=22, INT=23, BOOL=24, ID=25, WHITESP=26, 
		COMMENT=27, ERR=28;
	public static final String[] tokenNames = {
		"<INVALID>", "';'", "':'", "','", "'=='", "'='", "'+'", "'*'", "INTEGER", 
		"'true'", "'false'", "'('", "')'", "'{'", "'}'", "'if'", "'then'", "'else'", 
		"'print'", "'let'", "'in'", "'var'", "'fun'", "'int'", "'bool'", "ID", 
		"WHITESP", "COMMENT", "ERR"
	};
	public static final int
		RULE_prog = 0, RULE_declist = 1, RULE_type = 2, RULE_exp = 3, RULE_term = 4, 
		RULE_factor = 5, RULE_value = 6;
	public static final String[] ruleNames = {
		"prog", "declist", "type", "exp", "term", "factor", "value"
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
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
				HashMap<String,STentry> hm = new HashMap<String,STentry> ();
					symTable.add(hm);
				
			setState(24);
			switch (_input.LA(1)) {
			case INTEGER:
			case TRUE:
			case FALSE:
			case LPAR:
			case IF:
			case PRINT:
			case ID:
				{
				setState(15); ((ProgContext)_localctx).e = exp();
				((ProgContext)_localctx).ast =  new ProgNode(((ProgContext)_localctx).e.ast);
				}
				break;
			case LET:
				{
				setState(18); match(LET);
				setState(19); ((ProgContext)_localctx).d = declist();
				setState(20); match(IN);
				setState(21); ((ProgContext)_localctx).e = exp();
				 ((ProgContext)_localctx).ast =  new ProgLetInNode(((ProgContext)_localctx).d.astlist,((ProgContext)_localctx).e.ast); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
				symTable.remove(nestingLevel); 
			setState(27); match(SEMIC);
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
		public ArrayList<Node> astlist;
		public Token i;
		public TypeContext t;
		public ExpContext e;
		public TypeContext fty;
		public TypeContext ty;
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
	}

	public final DeclistContext declist() throws RecognitionException {
		DeclistContext _localctx = new DeclistContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				((DeclistContext)_localctx).astlist =  new ArrayList<Node>();
					int offset = -2; /* Indice di convenzione di inizio (che viene decrementato) */ 
				
			setState(78); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(74);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(30); match(VAR);
					setState(31); ((DeclistContext)_localctx).i = match(ID);
					setState(32); match(COLON);
					setState(33); ((DeclistContext)_localctx).t = type();
					setState(34); match(ASS);
					setState(35); ((DeclistContext)_localctx).e = exp();
						VarNode v = new VarNode((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),((DeclistContext)_localctx).t.ast,((DeclistContext)_localctx).e.ast);
									_localctx.astlist.add(v);
									HashMap<String,STentry> hm = symTable.get(nestingLevel); /* Tabella del livello corrente (detta tabella del fronte) */
									/* Verificare che nello scope attuale (il fronte della tabella), la variabile sia gi� stata dichiarata. "put" sostituisce, ma se la chiave era gi� occupata restituisce la coppia vecchia, altrimenti null.*/ 
									if(hm.put((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null), new STentry(nestingLevel,((DeclistContext)_localctx).t.ast,offset--)) != null) {
										/*Errore identificatore (variabile) gi� dichiarata*/
										System.out.println("Var id" + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null) + " at line " + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getLine():0) + " already declared.");
										System.exit(0);
									};
								
					}
					break;
				case FUN:
					{
					setState(38); match(FUN);
					setState(39); ((DeclistContext)_localctx).i = match(ID);
					setState(40); match(COLON);
					setState(41); ((DeclistContext)_localctx).t = type();
						
									FunNode f = new FunNode((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),((DeclistContext)_localctx).t.ast);
									_localctx.astlist.add(f);
									HashMap<String,STentry> hm = symTable.get(nestingLevel);
									/* Verificare che nello scope attuale (il fronte della tabella), la funzione sia gi� stata dichiarata. "put" sostituisce, ma se la chiave era gi� occupata restituisce la coppia vecchia, altrimenti null.*/
									STentry entry = new STentry(nestingLevel,offset--);
									if(hm.put((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null), entry) != null) {
										System.out.println("Fun id" + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null) + " at line " + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getLine():0) + " already declared.");
										System.exit(0);
									};
									/* Entro dentro un nuovo scope. */
									nestingLevel++;  /* Aumento il livello perch� sono all'interno di una funzione (anche i parametri passati alla funzione rientrano nel livello interno)*/
									HashMap<String,STentry> hmn = new HashMap<String,STentry>();
									symTable.add(hmn);
								
					setState(43); match(LPAR);
						ArrayList<Node> parTypes = new ArrayList<Node>();
										int parOffset = 1;
									
					setState(60);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(45); ((DeclistContext)_localctx).i = match(ID);
						setState(46); match(COLON);
						setState(47); ((DeclistContext)_localctx).fty = type();
						 /* Creare il ParNode, lo attacco al FunNode invocando addPar, aggiungo una STentry alla hashmap hmn*/
												parTypes.add(((DeclistContext)_localctx).fty.ast);
												ParNode p1 = new ParNode((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),((DeclistContext)_localctx).fty.ast);
												f.addPar(p1);
												if (hmn.put((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null), new STentry(nestingLevel,((DeclistContext)_localctx).fty.ast,parOffset++)) != null) {
													/* Errore identificatore (parametro) gi� dichiarato*/
													System.out.println("Par ID: " + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null) + " at line " + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getLine():0) + " already declared");
													System.exit(0);
												}
											
						setState(57);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(49); match(COMMA);
							setState(50); ((DeclistContext)_localctx).i = match(ID);
							setState(51); match(COLON);
							setState(52); ((DeclistContext)_localctx).ty = type();
							/* Creare il ParNode, lo attacco al FunNode invocando addPar, aggiungo una STentry alla hashmap hmn */
													parTypes.add(((DeclistContext)_localctx).ty.ast);
													ParNode p2 = new ParNode((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),((DeclistContext)_localctx).ty.ast);
													f.addPar(p2);
													if (hmn.put((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null), new STentry(nestingLevel,((DeclistContext)_localctx).ty.ast,parOffset++)) != null){
														/* Errore identificatore (parametro) gi� dichiarato */
														System.out.println("Par ID: " + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null) + " at line " + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getLine():0) + " already declared");
														System.exit(0);
													}
												
							}
							}
							setState(59);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(62); match(RPAR);
					 entry.addType(new ArrowTypeNode(parTypes,((DeclistContext)_localctx).t.ast)); 
					setState(69);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(64); match(LET);
						setState(65); ((DeclistContext)_localctx).d = declist();
						setState(66); match(IN);
						f.addDec(((DeclistContext)_localctx).d.astlist);
						}
					}

					setState(71); ((DeclistContext)_localctx).e = exp();
						f.addBody(((DeclistContext)_localctx).e.ast);
										symTable.remove(nestingLevel--); /* Diminuisco nestingLevel perch� esco dallo scope della funzione */
									
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(76); match(SEMIC);
				}
				}
				setState(80); 
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

	public static class TypeContext extends ParserRuleContext {
		public Node ast;
		public TerminalNode BOOL() { return getToken(FOOLParser.BOOL, 0); }
		public TerminalNode INT() { return getToken(FOOLParser.INT, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		try {
			setState(86);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(82); match(INT);
				((TypeContext)_localctx).ast =  new IntTypeNode();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(84); match(BOOL);
				((TypeContext)_localctx).ast =  new BoolTypeNode();
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
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(FOOLParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(FOOLParser.PLUS, i);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88); ((ExpContext)_localctx).t = term();
			((ExpContext)_localctx).ast =  ((ExpContext)_localctx).t.ast;
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS) {
				{
				{
				setState(90); match(PLUS);
				setState(91); ((ExpContext)_localctx).t = term();
				((ExpContext)_localctx).ast =  new PlusNode(_localctx.ast,((ExpContext)_localctx).t.ast);
				}
				}
				setState(98);
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
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public TerminalNode TIMES(int i) {
			return getToken(FOOLParser.TIMES, i);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> TIMES() { return getTokens(FOOLParser.TIMES); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99); ((TermContext)_localctx).f = factor();
			((TermContext)_localctx).ast =  ((TermContext)_localctx).f.ast;
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TIMES) {
				{
				{
				setState(101); match(TIMES);
				setState(102); ((TermContext)_localctx).f = factor();
				((TermContext)_localctx).ast =  new MultNode(_localctx.ast,((TermContext)_localctx).f.ast);
				}
				}
				setState(109);
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
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode EQ(int i) {
			return getToken(FOOLParser.EQ, i);
		}
		public List<TerminalNode> EQ() { return getTokens(FOOLParser.EQ); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); ((FactorContext)_localctx).v = value();
			((FactorContext)_localctx).ast =  ((FactorContext)_localctx).v.ast;
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EQ) {
				{
				{
				setState(112); match(EQ);
				setState(113); ((FactorContext)_localctx).v = value();
				((FactorContext)_localctx).ast =  new EqualNode(_localctx.ast,((FactorContext)_localctx).v.ast);
				}
				}
				setState(120);
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
		public Token i;
		public ExpContext e;
		public ExpContext e1;
		public ExpContext e2;
		public ExpContext e3;
		public ExpContext a;
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
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public List<TerminalNode> CLPAR() { return getTokens(FOOLParser.CLPAR); }
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TerminalNode CLPAR(int i) {
			return getToken(FOOLParser.CLPAR, i);
		}
		public TerminalNode CRPAR(int i) {
			return getToken(FOOLParser.CRPAR, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_value);
		int _la;
		try {
			setState(171);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(121); ((ValueContext)_localctx).i = match(INTEGER);
				((ValueContext)_localctx).ast =  new IntNode(Integer.parseInt((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null)));
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(123); match(TRUE);
				((ValueContext)_localctx).ast =  new BoolNode(true);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(125); match(FALSE);
				((ValueContext)_localctx).ast =  new BoolNode(false);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(127); match(LPAR);
				setState(128); ((ValueContext)_localctx).e = exp();
				setState(129); match(RPAR);
				((ValueContext)_localctx).ast =  ((ValueContext)_localctx).e.ast;
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 5);
				{
				setState(132); match(IF);
				setState(133); ((ValueContext)_localctx).e1 = exp();
				setState(134); match(THEN);
				setState(135); match(CLPAR);
				setState(136); ((ValueContext)_localctx).e2 = exp();
				setState(137); match(CRPAR);
				setState(138); match(ELSE);
				setState(139); match(CLPAR);
				setState(140); ((ValueContext)_localctx).e3 = exp();
				setState(141); match(CRPAR);
				((ValueContext)_localctx).ast =  new IfNode(((ValueContext)_localctx).e1.ast,((ValueContext)_localctx).e2.ast,((ValueContext)_localctx).e3.ast);
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 6);
				{
				setState(144); match(PRINT);
				setState(145); match(LPAR);
				setState(146); ((ValueContext)_localctx).e = exp();
				setState(147); match(RPAR);
				((ValueContext)_localctx).ast =  new PrintNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 7);
				{
				setState(150); ((ValueContext)_localctx).i = match(ID);
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
						
				setState(169);
				_la = _input.LA(1);
				if (_la==LPAR) {
					{
					setState(152); match(LPAR);
					 ArrayList<Node> arglist = new ArrayList<Node>(); 
					setState(165);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << IF) | (1L << PRINT) | (1L << ID))) != 0)) {
						{
						setState(154); ((ValueContext)_localctx).a = exp();
						 arglist.add(((ValueContext)_localctx).a.ast); 
						setState(162);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(156); match(COMMA);
							setState(157); ((ValueContext)_localctx).a = exp();
							 arglist.add(((ValueContext)_localctx).a.ast); 
							}
							}
							setState(164);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(167); match(RPAR);
					 ((ValueContext)_localctx).ast =  new CallNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null),entry,arglist,nestingLevel); 
					}
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\36\u00b0\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\5\2\33\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\7\3:\n\3\f\3\16\3=\13\3\5\3?\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5"+
		"\3H\n\3\3\3\3\3\3\3\5\3M\n\3\3\3\3\3\6\3Q\n\3\r\3\16\3R\3\4\3\4\3\4\3"+
		"\4\5\4Y\n\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5a\n\5\f\5\16\5d\13\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\7\6l\n\6\f\6\16\6o\13\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7w\n"+
		"\7\f\7\16\7z\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00a3\n\b\f\b\16\b\u00a6\13\b\5"+
		"\b\u00a8\n\b\3\b\3\b\5\b\u00ac\n\b\5\b\u00ae\n\b\3\b\2\2\t\2\4\6\b\n\f"+
		"\16\2\2\u00bb\2\20\3\2\2\2\4\37\3\2\2\2\6X\3\2\2\2\bZ\3\2\2\2\ne\3\2\2"+
		"\2\fp\3\2\2\2\16\u00ad\3\2\2\2\20\32\b\2\1\2\21\22\5\b\5\2\22\23\b\2\1"+
		"\2\23\33\3\2\2\2\24\25\7\25\2\2\25\26\5\4\3\2\26\27\7\26\2\2\27\30\5\b"+
		"\5\2\30\31\b\2\1\2\31\33\3\2\2\2\32\21\3\2\2\2\32\24\3\2\2\2\33\34\3\2"+
		"\2\2\34\35\b\2\1\2\35\36\7\3\2\2\36\3\3\2\2\2\37P\b\3\1\2 !\7\27\2\2!"+
		"\"\7\33\2\2\"#\7\4\2\2#$\5\6\4\2$%\7\7\2\2%&\5\b\5\2&\'\b\3\1\2\'M\3\2"+
		"\2\2()\7\30\2\2)*\7\33\2\2*+\7\4\2\2+,\5\6\4\2,-\b\3\1\2-.\7\r\2\2.>\b"+
		"\3\1\2/\60\7\33\2\2\60\61\7\4\2\2\61\62\5\6\4\2\62;\b\3\1\2\63\64\7\5"+
		"\2\2\64\65\7\33\2\2\65\66\7\4\2\2\66\67\5\6\4\2\678\b\3\1\28:\3\2\2\2"+
		"9\63\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<?\3\2\2\2=;\3\2\2\2>/\3\2\2"+
		"\2>?\3\2\2\2?@\3\2\2\2@A\7\16\2\2AG\b\3\1\2BC\7\25\2\2CD\5\4\3\2DE\7\26"+
		"\2\2EF\b\3\1\2FH\3\2\2\2GB\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\5\b\5\2JK\b\3"+
		"\1\2KM\3\2\2\2L \3\2\2\2L(\3\2\2\2MN\3\2\2\2NO\7\3\2\2OQ\3\2\2\2PL\3\2"+
		"\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2S\5\3\2\2\2TU\7\31\2\2UY\b\4\1\2VW\7"+
		"\32\2\2WY\b\4\1\2XT\3\2\2\2XV\3\2\2\2Y\7\3\2\2\2Z[\5\n\6\2[b\b\5\1\2\\"+
		"]\7\b\2\2]^\5\n\6\2^_\b\5\1\2_a\3\2\2\2`\\\3\2\2\2ad\3\2\2\2b`\3\2\2\2"+
		"bc\3\2\2\2c\t\3\2\2\2db\3\2\2\2ef\5\f\7\2fm\b\6\1\2gh\7\t\2\2hi\5\f\7"+
		"\2ij\b\6\1\2jl\3\2\2\2kg\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2n\13\3\2"+
		"\2\2om\3\2\2\2pq\5\16\b\2qx\b\7\1\2rs\7\6\2\2st\5\16\b\2tu\b\7\1\2uw\3"+
		"\2\2\2vr\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\r\3\2\2\2zx\3\2\2\2{|"+
		"\7\n\2\2|\u00ae\b\b\1\2}~\7\13\2\2~\u00ae\b\b\1\2\177\u0080\7\f\2\2\u0080"+
		"\u00ae\b\b\1\2\u0081\u0082\7\r\2\2\u0082\u0083\5\b\5\2\u0083\u0084\7\16"+
		"\2\2\u0084\u0085\b\b\1\2\u0085\u00ae\3\2\2\2\u0086\u0087\7\21\2\2\u0087"+
		"\u0088\5\b\5\2\u0088\u0089\7\22\2\2\u0089\u008a\7\17\2\2\u008a\u008b\5"+
		"\b\5\2\u008b\u008c\7\20\2\2\u008c\u008d\7\23\2\2\u008d\u008e\7\17\2\2"+
		"\u008e\u008f\5\b\5\2\u008f\u0090\7\20\2\2\u0090\u0091\b\b\1\2\u0091\u00ae"+
		"\3\2\2\2\u0092\u0093\7\24\2\2\u0093\u0094\7\r\2\2\u0094\u0095\5\b\5\2"+
		"\u0095\u0096\7\16\2\2\u0096\u0097\b\b\1\2\u0097\u00ae\3\2\2\2\u0098\u0099"+
		"\7\33\2\2\u0099\u00ab\b\b\1\2\u009a\u009b\7\r\2\2\u009b\u00a7\b\b\1\2"+
		"\u009c\u009d\5\b\5\2\u009d\u00a4\b\b\1\2\u009e\u009f\7\5\2\2\u009f\u00a0"+
		"\5\b\5\2\u00a0\u00a1\b\b\1\2\u00a1\u00a3\3\2\2\2\u00a2\u009e\3\2\2\2\u00a3"+
		"\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a8\3\2"+
		"\2\2\u00a6\u00a4\3\2\2\2\u00a7\u009c\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8"+
		"\u00a9\3\2\2\2\u00a9\u00aa\7\16\2\2\u00aa\u00ac\b\b\1\2\u00ab\u009a\3"+
		"\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad{\3\2\2\2\u00ad}\3"+
		"\2\2\2\u00ad\177\3\2\2\2\u00ad\u0081\3\2\2\2\u00ad\u0086\3\2\2\2\u00ad"+
		"\u0092\3\2\2\2\u00ad\u0098\3\2\2\2\u00ae\17\3\2\2\2\20\32;>GLRXbmx\u00a4"+
		"\u00a7\u00ab\u00ad";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}