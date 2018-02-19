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
			case TRUE:
			case FALSE:
			case IF:
			case PRINT:
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
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode RPAR(int i) {
			return getToken(FOOLParser.RPAR, i);
		}
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
	}

	public final CllistContext cllist() throws RecognitionException {
		CllistContext _localctx = new CllistContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_cllist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(41); match(CLASS);
				setState(42); match(ID);
				setState(45);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(43); match(EXTENDS);
					setState(44); match(ID);
					}
				}

				setState(47); match(LPAR);
				setState(60);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(48); match(ID);
					setState(49); match(COLON);
					setState(50); type();
					setState(57);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(51); match(COMMA);
						setState(52); match(ID);
						setState(53); match(COLON);
						setState(54); type();
						}
						}
						setState(59);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(62); match(RPAR);
				setState(63); match(CLPAR);
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FUN) {
					{
					{
					setState(64); match(FUN);
					setState(65); match(ID);
					setState(66); match(COLON);
					setState(67); type();
					setState(68); match(LPAR);
					setState(81);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(69); match(ID);
						setState(70); match(COLON);
						setState(71); hotype();
						setState(78);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(72); match(COMMA);
							setState(73); match(ID);
							setState(74); match(COLON);
							setState(75); hotype();
							}
							}
							setState(80);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(83); match(RPAR);
					setState(99);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(84); match(LET);
						setState(95);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==VAR) {
							{
							{
							setState(85); match(VAR);
							setState(86); match(ID);
							setState(87); match(COLON);
							setState(88); type();
							setState(89); match(ASS);
							setState(90); exp();
							setState(91); match(SEMIC);
							}
							}
							setState(97);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(98); match(IN);
						}
					}

					setState(101); exp();
					setState(102); match(SEMIC);
					}
					}
					setState(108);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(109); match(CRPAR);
				}
				}
				setState(112); 
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
		enterRule(_localctx, 4, RULE_declist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				((DeclistContext)_localctx).astlist =  new ArrayList<Node>();
					int offset = -2; /* Indice di convenzione di inizio (che viene decrementato) */ 
				
			setState(163); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(159);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(115); match(VAR);
					setState(116); ((DeclistContext)_localctx).i = match(ID);
					setState(117); match(COLON);
					setState(118); ((DeclistContext)_localctx).t = type();
					setState(119); match(ASS);
					setState(120); ((DeclistContext)_localctx).e = exp();
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
					setState(123); match(FUN);
					setState(124); ((DeclistContext)_localctx).i = match(ID);
					setState(125); match(COLON);
					setState(126); ((DeclistContext)_localctx).t = type();
						
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
								
					setState(128); match(LPAR);
						ArrayList<Node> parTypes = new ArrayList<Node>();
										int parOffset = 1;
									
					setState(145);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(130); ((DeclistContext)_localctx).i = match(ID);
						setState(131); match(COLON);
						setState(132); ((DeclistContext)_localctx).fty = type();
						 /* Creare il ParNode, lo attacco al FunNode invocando addPar, aggiungo una STentry alla hashmap hmn*/
												parTypes.add(((DeclistContext)_localctx).fty.ast);
												ParNode p1 = new ParNode((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),((DeclistContext)_localctx).fty.ast);
												f.addPar(p1);
												if (hmn.put((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null), new STentry(nestingLevel,((DeclistContext)_localctx).fty.ast,parOffset++)) != null) {
													/* Errore identificatore (parametro) gi� dichiarato*/
													System.out.println("Par ID: " + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null) + " at line " + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getLine():0) + " already declared");
													System.exit(0);
												}
											
						setState(142);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(134); match(COMMA);
							setState(135); ((DeclistContext)_localctx).i = match(ID);
							setState(136); match(COLON);
							setState(137); ((DeclistContext)_localctx).ty = type();
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
							setState(144);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(147); match(RPAR);
					 entry.addType(new ArrowTypeNode(parTypes,((DeclistContext)_localctx).t.ast)); 
					setState(154);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(149); match(LET);
						setState(150); ((DeclistContext)_localctx).d = declist();
						setState(151); match(IN);
						f.addDec(((DeclistContext)_localctx).d.astlist);
						}
					}

					setState(156); ((DeclistContext)_localctx).e = exp();
						f.addBody(((DeclistContext)_localctx).e.ast);
										symTable.remove(nestingLevel--); /* Diminuisco nestingLevel perch� esco dallo scope della funzione */
									
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(161); match(SEMIC);
				}
				}
				setState(165); 
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
	}

	public final HotypeContext hotype() throws RecognitionException {
		HotypeContext _localctx = new HotypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_hotype);
		try {
			setState(169);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
				enterOuterAlt(_localctx, 1);
				{
				setState(167); type();
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(168); arrow();
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
	}

	public final ArrowContext arrow() throws RecognitionException {
		ArrowContext _localctx = new ArrowContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arrow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); match(LPAR);
			setState(180);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << INT) | (1L << BOOL))) != 0)) {
				{
				setState(172); hotype();
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(173); match(COMMA);
					setState(174); hotype();
					}
					}
					setState(179);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(182); match(RPAR);
			setState(183); match(ARROW);
			setState(184); type();
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
		enterRule(_localctx, 10, RULE_type);
		try {
			setState(190);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(186); match(INT);
				((TypeContext)_localctx).ast =  new IntTypeNode();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(188); match(BOOL);
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
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192); ((ExpContext)_localctx).t = term();
			((ExpContext)_localctx).ast =  ((ExpContext)_localctx).t.ast;
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0)) {
				{
				setState(206);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(194); match(PLUS);
					setState(195); ((ExpContext)_localctx).t = term();
					((ExpContext)_localctx).ast =  new PlusNode(_localctx.ast,((ExpContext)_localctx).t.ast);
					}
					break;
				case MINUS:
					{
					setState(198); match(MINUS);
					setState(199); ((ExpContext)_localctx).t = term();
					((ExpContext)_localctx).ast =  new MinusNode(_localctx.ast, ((ExpContext)_localctx).t.ast);
					}
					break;
				case OR:
					{
					setState(202); match(OR);
					setState(203); ((ExpContext)_localctx).t = term();
					((ExpContext)_localctx).ast =  new OrNode(_localctx.ast, ((ExpContext)_localctx).t.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(210);
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
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211); ((TermContext)_localctx).f = factor();
			((TermContext)_localctx).ast =  ((TermContext)_localctx).f.ast;
			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TIMES) | (1L << DIV) | (1L << AND))) != 0)) {
				{
				setState(225);
				switch (_input.LA(1)) {
				case TIMES:
					{
					setState(213); match(TIMES);
					setState(214); ((TermContext)_localctx).f = factor();
					((TermContext)_localctx).ast =  new MultNode(_localctx.ast,((TermContext)_localctx).f.ast);
					}
					break;
				case DIV:
					{
					setState(217); match(DIV);
					setState(218); ((TermContext)_localctx).f = factor();
					_localctx.ast = new DivNode(_localctx.ast,((TermContext)_localctx).f.ast)
					}
					break;
				case AND:
					{
					setState(221); match(AND);
					setState(222); ((TermContext)_localctx).f = factor();
					 _localctx.ast = new AndNode(_localctx.ast,((TermContext)_localctx).f.ast)
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(229);
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
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230); ((FactorContext)_localctx).v = value();
			((FactorContext)_localctx).ast =  ((FactorContext)_localctx).v.ast;
			setState(246);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << LE) | (1L << EQ))) != 0)) {
				{
				setState(244);
				switch (_input.LA(1)) {
				case EQ:
					{
					setState(232); match(EQ);
					setState(233); ((FactorContext)_localctx).v = value();
					((FactorContext)_localctx).ast =  new EqualNode(_localctx.ast,((FactorContext)_localctx).v.ast);
					}
					break;
				case GE:
					{
					setState(236); match(GE);
					setState(237); ((FactorContext)_localctx).v = value();
					((FactorContext)_localctx).ast =  new GENode(_localctx.ast,((FactorContext)_localctx).v.ast);
					}
					break;
				case LE:
					{
					setState(240); match(LE);
					setState(241); ((FactorContext)_localctx).v = value();
					((FactorContext)_localctx).ast =  new LENode(_localctx.ast,((FactorContext)_localctx).v.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(248);
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
		enterRule(_localctx, 18, RULE_value);
		int _la;
		try {
			setState(299);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(249); ((ValueContext)_localctx).i = match(INTEGER);
				((ValueContext)_localctx).ast =  new IntNode(Integer.parseInt((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null)));
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(251); match(TRUE);
				((ValueContext)_localctx).ast =  new BoolNode(true);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(253); match(FALSE);
				((ValueContext)_localctx).ast =  new BoolNode(false);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(255); match(LPAR);
				setState(256); ((ValueContext)_localctx).e = exp();
				setState(257); match(RPAR);
				((ValueContext)_localctx).ast =  ((ValueContext)_localctx).e.ast;
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 5);
				{
				setState(260); match(IF);
				setState(261); ((ValueContext)_localctx).e1 = exp();
				setState(262); match(THEN);
				setState(263); match(CLPAR);
				setState(264); ((ValueContext)_localctx).e2 = exp();
				setState(265); match(CRPAR);
				setState(266); match(ELSE);
				setState(267); match(CLPAR);
				setState(268); ((ValueContext)_localctx).e3 = exp();
				setState(269); match(CRPAR);
				((ValueContext)_localctx).ast =  new IfNode(((ValueContext)_localctx).e1.ast,((ValueContext)_localctx).e2.ast,((ValueContext)_localctx).e3.ast);
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 6);
				{
				setState(272); match(PRINT);
				setState(273); match(LPAR);
				setState(274); ((ValueContext)_localctx).e = exp();
				setState(275); match(RPAR);
				((ValueContext)_localctx).ast =  new PrintNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 7);
				{
				setState(278); ((ValueContext)_localctx).i = match(ID);
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
						
				setState(297);
				_la = _input.LA(1);
				if (_la==LPAR) {
					{
					setState(280); match(LPAR);
					 ArrayList<Node> arglist = new ArrayList<Node>(); 
					setState(293);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(282); ((ValueContext)_localctx).a = exp();
						 arglist.add(((ValueContext)_localctx).a.ast); 
						setState(290);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(284); match(COMMA);
							setState(285); ((ValueContext)_localctx).a = exp();
							 arglist.add(((ValueContext)_localctx).a.ast); 
							}
							}
							setState(292);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(295); match(RPAR);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3+\u0130\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\36\n\2\3\2\5\2!\n\2\3\2\3\2\3\2\3"+
		"\2\5\2\'\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3\60\n\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\7\3:\n\3\f\3\16\3=\13\3\5\3?\n\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3O\n\3\f\3\16\3R\13\3\5\3T\n\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3`\n\3\f\3\16\3c\13\3\3\3\5\3"+
		"f\n\3\3\3\3\3\3\3\7\3k\n\3\f\3\16\3n\13\3\3\3\6\3q\n\3\r\3\16\3r\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u008f\n\4\f\4\16\4\u0092\13\4\5\4\u0094"+
		"\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u009d\n\4\3\4\3\4\3\4\5\4\u00a2\n"+
		"\4\3\4\3\4\6\4\u00a6\n\4\r\4\16\4\u00a7\3\5\3\5\5\5\u00ac\n\5\3\6\3\6"+
		"\3\6\3\6\7\6\u00b2\n\6\f\6\16\6\u00b5\13\6\5\6\u00b7\n\6\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\5\7\u00c1\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\b\u00d1\n\b\f\b\16\b\u00d4\13\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00e4\n\t\f\t\16\t\u00e7\13"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00f7\n"+
		"\n\f\n\16\n\u00fa\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\7\13\u0123\n\13\f\13\16\13\u0126\13\13\5\13\u0128\n\13\3\13\3"+
		"\13\5\13\u012c\n\13\5\13\u012e\n\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24"+
		"\2\2\u014c\2\26\3\2\2\2\4p\3\2\2\2\6t\3\2\2\2\b\u00ab\3\2\2\2\n\u00ad"+
		"\3\2\2\2\f\u00c0\3\2\2\2\16\u00c2\3\2\2\2\20\u00d5\3\2\2\2\22\u00e8\3"+
		"\2\2\2\24\u012d\3\2\2\2\26&\b\2\1\2\27\30\5\16\b\2\30\31\b\2\1\2\31\'"+
		"\3\2\2\2\32 \7\34\2\2\33\35\5\4\3\2\34\36\5\6\4\2\35\34\3\2\2\2\35\36"+
		"\3\2\2\2\36!\3\2\2\2\37!\5\6\4\2 \33\3\2\2\2 \37\3\2\2\2!\"\3\2\2\2\""+
		"#\7\35\2\2#$\5\16\b\2$%\b\2\1\2%\'\3\2\2\2&\27\3\2\2\2&\32\3\2\2\2\'("+
		"\3\2\2\2()\b\2\1\2)*\7\13\2\2*\3\3\2\2\2+,\7 \2\2,/\7(\2\2-.\7!\2\2.\60"+
		"\7(\2\2/-\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61>\7\7\2\2\62\63\7(\2\2\63"+
		"\64\7\f\2\2\64;\5\f\7\2\65\66\7\r\2\2\66\67\7(\2\2\678\7\f\2\28:\5\f\7"+
		"\29\65\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<?\3\2\2\2=;\3\2\2\2>\62\3"+
		"\2\2\2>?\3\2\2\2?@\3\2\2\2@A\7\b\2\2Al\7\t\2\2BC\7\37\2\2CD\7(\2\2DE\7"+
		"\f\2\2EF\5\f\7\2FS\7\7\2\2GH\7(\2\2HI\7\f\2\2IP\5\b\5\2JK\7\r\2\2KL\7"+
		"(\2\2LM\7\f\2\2MO\5\b\5\2NJ\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QT\3"+
		"\2\2\2RP\3\2\2\2SG\3\2\2\2ST\3\2\2\2TU\3\2\2\2Ue\7\b\2\2Va\7\34\2\2WX"+
		"\7\36\2\2XY\7(\2\2YZ\7\f\2\2Z[\5\f\7\2[\\\7\25\2\2\\]\5\16\b\2]^\7\13"+
		"\2\2^`\3\2\2\2_W\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2ca\3\2"+
		"\2\2df\7\35\2\2eV\3\2\2\2ef\3\2\2\2fg\3\2\2\2gh\5\16\b\2hi\7\13\2\2ik"+
		"\3\2\2\2jB\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2\2o"+
		"q\7\n\2\2p+\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2s\5\3\2\2\2t\u00a5\b"+
		"\4\1\2uv\7\36\2\2vw\7(\2\2wx\7\f\2\2xy\5\f\7\2yz\7\25\2\2z{\5\16\b\2{"+
		"|\b\4\1\2|\u00a2\3\2\2\2}~\7\37\2\2~\177\7(\2\2\177\u0080\7\f\2\2\u0080"+
		"\u0081\5\f\7\2\u0081\u0082\b\4\1\2\u0082\u0083\7\7\2\2\u0083\u0093\b\4"+
		"\1\2\u0084\u0085\7(\2\2\u0085\u0086\7\f\2\2\u0086\u0087\5\f\7\2\u0087"+
		"\u0090\b\4\1\2\u0088\u0089\7\r\2\2\u0089\u008a\7(\2\2\u008a\u008b\7\f"+
		"\2\2\u008b\u008c\5\f\7\2\u008c\u008d\b\4\1\2\u008d\u008f\3\2\2\2\u008e"+
		"\u0088\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2"+
		"\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0084\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\7\b\2\2\u0096\u009c\b\4"+
		"\1\2\u0097\u0098\7\34\2\2\u0098\u0099\5\6\4\2\u0099\u009a\7\35\2\2\u009a"+
		"\u009b\b\4\1\2\u009b\u009d\3\2\2\2\u009c\u0097\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\u009e\3\2\2\2\u009e\u009f\5\16\b\2\u009f\u00a0\b\4\1\2\u00a0"+
		"\u00a2\3\2\2\2\u00a1u\3\2\2\2\u00a1}\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3"+
		"\u00a4\7\13\2\2\u00a4\u00a6\3\2\2\2\u00a5\u00a1\3\2\2\2\u00a6\u00a7\3"+
		"\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\7\3\2\2\2\u00a9\u00ac"+
		"\5\f\7\2\u00aa\u00ac\5\n\6\2\u00ab\u00a9\3\2\2\2\u00ab\u00aa\3\2\2\2\u00ac"+
		"\t\3\2\2\2\u00ad\u00b6\7\7\2\2\u00ae\u00b3\5\b\5\2\u00af\u00b0\7\r\2\2"+
		"\u00b0\u00b2\5\b\5\2\u00b1\u00af\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1"+
		"\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6"+
		"\u00ae\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\7\b"+
		"\2\2\u00b9\u00ba\7&\2\2\u00ba\u00bb\5\f\7\2\u00bb\13\3\2\2\2\u00bc\u00bd"+
		"\7$\2\2\u00bd\u00c1\b\7\1\2\u00be\u00bf\7%\2\2\u00bf\u00c1\b\7\1\2\u00c0"+
		"\u00bc\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\r\3\2\2\2\u00c2\u00c3\5\20\t"+
		"\2\u00c3\u00d2\b\b\1\2\u00c4\u00c5\7\3\2\2\u00c5\u00c6\5\20\t\2\u00c6"+
		"\u00c7\b\b\1\2\u00c7\u00d1\3\2\2\2\u00c8\u00c9\7\4\2\2\u00c9\u00ca\5\20"+
		"\t\2\u00ca\u00cb\b\b\1\2\u00cb\u00d1\3\2\2\2\u00cc\u00cd\7\17\2\2\u00cd"+
		"\u00ce\5\20\t\2\u00ce\u00cf\b\b\1\2\u00cf\u00d1\3\2\2\2\u00d0\u00c4\3"+
		"\2\2\2\u00d0\u00c8\3\2\2\2\u00d0\u00cc\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2"+
		"\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\17\3\2\2\2\u00d4\u00d2\3\2\2"+
		"\2\u00d5\u00d6\5\22\n\2\u00d6\u00e5\b\t\1\2\u00d7\u00d8\7\5\2\2\u00d8"+
		"\u00d9\5\22\n\2\u00d9\u00da\b\t\1\2\u00da\u00e4\3\2\2\2\u00db\u00dc\7"+
		"\6\2\2\u00dc\u00dd\5\22\n\2\u00dd\u00de\b\t\1\2\u00de\u00e4\3\2\2\2\u00df"+
		"\u00e0\7\20\2\2\u00e0\u00e1\5\22\n\2\u00e1\u00e2\b\t\1\2\u00e2\u00e4\3"+
		"\2\2\2\u00e3\u00d7\3\2\2\2\u00e3\u00db\3\2\2\2\u00e3\u00df\3\2\2\2\u00e4"+
		"\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\21\3\2\2"+
		"\2\u00e7\u00e5\3\2\2\2\u00e8\u00e9\5\24\13\2\u00e9\u00f8\b\n\1\2\u00ea"+
		"\u00eb\7\24\2\2\u00eb\u00ec\5\24\13\2\u00ec\u00ed\b\n\1\2\u00ed\u00f7"+
		"\3\2\2\2\u00ee\u00ef\7\22\2\2\u00ef\u00f0\5\24\13\2\u00f0\u00f1\b\n\1"+
		"\2\u00f1\u00f7\3\2\2\2\u00f2\u00f3\7\23\2\2\u00f3\u00f4\5\24\13\2\u00f4"+
		"\u00f5\b\n\1\2\u00f5\u00f7\3\2\2\2\u00f6\u00ea\3\2\2\2\u00f6\u00ee\3\2"+
		"\2\2\u00f6\u00f2\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8"+
		"\u00f9\3\2\2\2\u00f9\23\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00fc\7\'\2"+
		"\2\u00fc\u012e\b\13\1\2\u00fd\u00fe\7\26\2\2\u00fe\u012e\b\13\1\2\u00ff"+
		"\u0100\7\27\2\2\u0100\u012e\b\13\1\2\u0101\u0102\7\7\2\2\u0102\u0103\5"+
		"\16\b\2\u0103\u0104\7\b\2\2\u0104\u0105\b\13\1\2\u0105\u012e\3\2\2\2\u0106"+
		"\u0107\7\30\2\2\u0107\u0108\5\16\b\2\u0108\u0109\7\31\2\2\u0109\u010a"+
		"\7\t\2\2\u010a\u010b\5\16\b\2\u010b\u010c\7\n\2\2\u010c\u010d\7\32\2\2"+
		"\u010d\u010e\7\t\2\2\u010e\u010f\5\16\b\2\u010f\u0110\7\n\2\2\u0110\u0111"+
		"\b\13\1\2\u0111\u012e\3\2\2\2\u0112\u0113\7\33\2\2\u0113\u0114\7\7\2\2"+
		"\u0114\u0115\5\16\b\2\u0115\u0116\7\b\2\2\u0116\u0117\b\13\1\2\u0117\u012e"+
		"\3\2\2\2\u0118\u0119\7(\2\2\u0119\u012b\b\13\1\2\u011a\u011b\7\7\2\2\u011b"+
		"\u0127\b\13\1\2\u011c\u011d\5\16\b\2\u011d\u0124\b\13\1\2\u011e\u011f"+
		"\7\r\2\2\u011f\u0120\5\16\b\2\u0120\u0121\b\13\1\2\u0121\u0123\3\2\2\2"+
		"\u0122\u011e\3\2\2\2\u0123\u0126\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125"+
		"\3\2\2\2\u0125\u0128\3\2\2\2\u0126\u0124\3\2\2\2\u0127\u011c\3\2\2\2\u0127"+
		"\u0128\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012a\7\b\2\2\u012a\u012c\b\13"+
		"\1\2\u012b\u011a\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012e\3\2\2\2\u012d"+
		"\u00fb\3\2\2\2\u012d\u00fd\3\2\2\2\u012d\u00ff\3\2\2\2\u012d\u0101\3\2"+
		"\2\2\u012d\u0106\3\2\2\2\u012d\u0112\3\2\2\2\u012d\u0118\3\2\2\2\u012e"+
		"\25\3\2\2\2!\35 &/;>PSaelr\u0090\u0093\u009c\u00a1\u00a7\u00ab\u00b3\u00b6"+
		"\u00c0\u00d0\u00d2\u00e3\u00e5\u00f6\u00f8\u0124\u0127\u012b\u012d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}