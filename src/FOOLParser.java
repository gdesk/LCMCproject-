// Generated from FOOL.g4 by ANTLR 4.4
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
		RULE_prog = 0, RULE_cllist = 1, RULE_declist = 2, RULE_exp = 3, RULE_term = 4, 
		RULE_factor = 5, RULE_value = 6, RULE_hotype = 7, RULE_type = 8, RULE_arrow = 9;
	public static final String[] ruleNames = {
		"prog", "cllist", "declist", "exp", "term", "factor", "value", "hotype", 
		"type", "arrow"
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

	public FOOLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
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
			setState(32);
			switch (_input.LA(1)) {
			case LET:
				{
				setState(20); match(LET);
				setState(26);
				switch (_input.LA(1)) {
				case CLASS:
					{
					setState(21); cllist();
					setState(23);
					_la = _input.LA(1);
					if (_la==VAR || _la==FUN) {
						{
						setState(22); declist();
						}
					}

					}
					break;
				case VAR:
				case FUN:
					{
					setState(25); declist();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(28); match(IN);
				setState(29); exp();
				}
				break;
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
				setState(31); exp();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(34); match(SEMIC);
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
			setState(105); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(36); match(CLASS);
				setState(37); match(ID);
				setState(40);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(38); match(EXTENDS);
					setState(39); match(ID);
					}
				}

				setState(42); match(LPAR);
				setState(55);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(43); match(ID);
					setState(44); match(COLON);
					setState(45); type();
					setState(52);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(46); match(COMMA);
						setState(47); match(ID);
						setState(48); match(COLON);
						setState(49); type();
						}
						}
						setState(54);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(57); match(RPAR);
				setState(58); match(CLPAR);
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FUN) {
					{
					{
					setState(59); match(FUN);
					setState(60); match(ID);
					setState(61); match(COLON);
					setState(62); type();
					setState(63); match(LPAR);
					setState(76);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(64); match(ID);
						setState(65); match(COLON);
						setState(66); hotype();
						setState(73);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(67); match(COMMA);
							setState(68); match(ID);
							setState(69); match(COLON);
							setState(70); hotype();
							}
							}
							setState(75);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(78); match(RPAR);
					setState(94);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(79); match(LET);
						setState(90);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==VAR) {
							{
							{
							setState(80); match(VAR);
							setState(81); match(ID);
							setState(82); match(COLON);
							setState(83); type();
							setState(84); match(ASS);
							setState(85); exp();
							setState(86); match(SEMIC);
							}
							}
							setState(92);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(93); match(IN);
						}
					}

					setState(96); exp();
					setState(97); match(SEMIC);
					}
					}
					setState(103);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(104); match(CRPAR);
				}
				}
				setState(107); 
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
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode RPAR(int i) {
			return getToken(FOOLParser.RPAR, i);
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
		public List<HotypeContext> hotype() {
			return getRuleContexts(HotypeContext.class);
		}
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
			setState(148); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(144);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(109); match(VAR);
					setState(110); match(ID);
					setState(111); match(COLON);
					setState(112); hotype();
					setState(113); match(ASS);
					setState(114); exp();
					}
					break;
				case FUN:
					{
					setState(116); match(FUN);
					setState(117); match(ID);
					setState(118); match(COLON);
					setState(119); type();
					setState(120); match(LPAR);
					setState(133);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(121); match(ID);
						setState(122); match(COLON);
						setState(123); hotype();
						setState(130);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(124); match(COMMA);
							setState(125); match(ID);
							setState(126); match(COLON);
							setState(127); hotype();
							}
							}
							setState(132);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(135); match(RPAR);
					setState(140);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(136); match(LET);
						setState(137); declist();
						setState(138); match(IN);
						}
					}

					setState(142); exp();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(146); match(SEMIC);
				}
				}
				setState(150); 
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

	public static class ExpContext extends ParserRuleContext {
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
		enterRule(_localctx, 6, RULE_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152); term();
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0)) {
				{
				setState(159);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(153); match(PLUS);
					setState(154); term();
					}
					break;
				case MINUS:
					{
					setState(155); match(MINUS);
					setState(156); term();
					}
					break;
				case OR:
					{
					setState(157); match(OR);
					setState(158); term();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(163);
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
		enterRule(_localctx, 8, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); factor();
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TIMES) | (1L << DIV) | (1L << AND))) != 0)) {
				{
				setState(171);
				switch (_input.LA(1)) {
				case TIMES:
					{
					setState(165); match(TIMES);
					setState(166); factor();
					}
					break;
				case DIV:
					{
					setState(167); match(DIV);
					setState(168); factor();
					}
					break;
				case AND:
					{
					setState(169); match(AND);
					setState(170); factor();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(175);
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
		enterRule(_localctx, 10, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176); value();
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << LE) | (1L << EQ))) != 0)) {
				{
				setState(183);
				switch (_input.LA(1)) {
				case EQ:
					{
					setState(177); match(EQ);
					setState(178); value();
					}
					break;
				case GE:
					{
					setState(179); match(GE);
					setState(180); value();
					}
					break;
				case LE:
					{
					setState(181); match(LE);
					setState(182); value();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(187);
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
		enterRule(_localctx, 12, RULE_value);
		int _la;
		try {
			setState(260);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(188); match(INTEGER);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(189); match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(190); match(FALSE);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(191); match(NULL);
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 5);
				{
				setState(192); match(NEW);
				setState(193); match(ID);
				setState(194); match(LPAR);
				setState(203);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(195); exp();
					setState(200);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(196); match(COMMA);
						setState(197); exp();
						}
						}
						setState(202);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(205); match(RPAR);
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 6);
				{
				setState(206); match(IF);
				setState(207); exp();
				setState(208); match(THEN);
				setState(209); match(CLPAR);
				setState(210); exp();
				setState(211); match(CRPAR);
				setState(212); match(ELSE);
				setState(213); match(CLPAR);
				setState(214); exp();
				setState(215); match(CRPAR);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 7);
				{
				setState(217); match(NOT);
				setState(218); match(LPAR);
				setState(219); exp();
				setState(220); match(RPAR);
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 8);
				{
				setState(222); match(PRINT);
				setState(223); match(LPAR);
				setState(224); exp();
				setState(225); match(RPAR);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 9);
				{
				setState(227); match(LPAR);
				setState(228); exp();
				setState(229); match(RPAR);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 10);
				{
				setState(231); match(ID);
				setState(258);
				switch (_input.LA(1)) {
				case LPAR:
					{
					setState(232); match(LPAR);
					setState(241);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(233); exp();
						setState(238);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(234); match(COMMA);
							setState(235); exp();
							}
							}
							setState(240);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(243); match(RPAR);
					}
					break;
				case DOT:
					{
					setState(244); match(DOT);
					setState(245); match(ID);
					setState(246); match(LPAR);
					setState(255);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(247); exp();
						setState(252);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(248); match(COMMA);
							setState(249); exp();
							}
							}
							setState(254);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(257); match(RPAR);
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
		enterRule(_localctx, 14, RULE_hotype);
		try {
			setState(264);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(262); type();
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(263); arrow();
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

	public static class TypeContext extends ParserRuleContext {
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
		enterRule(_localctx, 16, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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
		enterRule(_localctx, 18, RULE_arrow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268); match(LPAR);
			setState(277);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(269); hotype();
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(270); match(COMMA);
					setState(271); hotype();
					}
					}
					setState(276);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(279); match(RPAR);
			setState(280); match(ARROW);
			setState(281); type();
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3+\u011e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\5\2\32\n\2\3\2\5\2\35\n\2\3\2\3\2\3\2\3\2\5\2#\n\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\5\3+\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\65\n"+
		"\3\f\3\16\38\13\3\5\3:\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\7\3J\n\3\f\3\16\3M\13\3\5\3O\n\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\7\3[\n\3\f\3\16\3^\13\3\3\3\5\3a\n\3\3\3\3\3\3\3\7\3"+
		"f\n\3\f\3\16\3i\13\3\3\3\6\3l\n\3\r\3\16\3m\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u0083\n\4\f\4\16"+
		"\4\u0086\13\4\5\4\u0088\n\4\3\4\3\4\3\4\3\4\3\4\5\4\u008f\n\4\3\4\3\4"+
		"\5\4\u0093\n\4\3\4\3\4\6\4\u0097\n\4\r\4\16\4\u0098\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\7\5\u00a2\n\5\f\5\16\5\u00a5\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\7\6\u00ae\n\6\f\6\16\6\u00b1\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u00ba"+
		"\n\7\f\7\16\7\u00bd\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00c9"+
		"\n\b\f\b\16\b\u00cc\13\b\5\b\u00ce\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\7\b\u00ef\n\b\f\b\16\b\u00f2\13\b\5\b\u00f4\n\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00fd\n\b\f\b\16\b\u0100\13\b\5\b\u0102"+
		"\n\b\3\b\5\b\u0105\n\b\5\b\u0107\n\b\3\t\3\t\5\t\u010b\n\t\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\7\13\u0113\n\13\f\13\16\13\u0116\13\13\5\13\u0118\n\13"+
		"\3\13\3\13\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\3\4\2$%((\u0141"+
		"\2\"\3\2\2\2\4k\3\2\2\2\6\u0096\3\2\2\2\b\u009a\3\2\2\2\n\u00a6\3\2\2"+
		"\2\f\u00b2\3\2\2\2\16\u0106\3\2\2\2\20\u010a\3\2\2\2\22\u010c\3\2\2\2"+
		"\24\u010e\3\2\2\2\26\34\7\34\2\2\27\31\5\4\3\2\30\32\5\6\4\2\31\30\3\2"+
		"\2\2\31\32\3\2\2\2\32\35\3\2\2\2\33\35\5\6\4\2\34\27\3\2\2\2\34\33\3\2"+
		"\2\2\35\36\3\2\2\2\36\37\7\35\2\2\37 \5\b\5\2 #\3\2\2\2!#\5\b\5\2\"\26"+
		"\3\2\2\2\"!\3\2\2\2#$\3\2\2\2$%\7\13\2\2%\3\3\2\2\2&\'\7 \2\2\'*\7(\2"+
		"\2()\7!\2\2)+\7(\2\2*(\3\2\2\2*+\3\2\2\2+,\3\2\2\2,9\7\7\2\2-.\7(\2\2"+
		"./\7\f\2\2/\66\5\22\n\2\60\61\7\r\2\2\61\62\7(\2\2\62\63\7\f\2\2\63\65"+
		"\5\22\n\2\64\60\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67:\3"+
		"\2\2\28\66\3\2\2\29-\3\2\2\29:\3\2\2\2:;\3\2\2\2;<\7\b\2\2<g\7\t\2\2="+
		">\7\37\2\2>?\7(\2\2?@\7\f\2\2@A\5\22\n\2AN\7\7\2\2BC\7(\2\2CD\7\f\2\2"+
		"DK\5\20\t\2EF\7\r\2\2FG\7(\2\2GH\7\f\2\2HJ\5\20\t\2IE\3\2\2\2JM\3\2\2"+
		"\2KI\3\2\2\2KL\3\2\2\2LO\3\2\2\2MK\3\2\2\2NB\3\2\2\2NO\3\2\2\2OP\3\2\2"+
		"\2P`\7\b\2\2Q\\\7\34\2\2RS\7\36\2\2ST\7(\2\2TU\7\f\2\2UV\5\22\n\2VW\7"+
		"\25\2\2WX\5\b\5\2XY\7\13\2\2Y[\3\2\2\2ZR\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2"+
		"\\]\3\2\2\2]_\3\2\2\2^\\\3\2\2\2_a\7\35\2\2`Q\3\2\2\2`a\3\2\2\2ab\3\2"+
		"\2\2bc\5\b\5\2cd\7\13\2\2df\3\2\2\2e=\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3"+
		"\2\2\2hj\3\2\2\2ig\3\2\2\2jl\7\n\2\2k&\3\2\2\2lm\3\2\2\2mk\3\2\2\2mn\3"+
		"\2\2\2n\5\3\2\2\2op\7\36\2\2pq\7(\2\2qr\7\f\2\2rs\5\20\t\2st\7\25\2\2"+
		"tu\5\b\5\2u\u0093\3\2\2\2vw\7\37\2\2wx\7(\2\2xy\7\f\2\2yz\5\22\n\2z\u0087"+
		"\7\7\2\2{|\7(\2\2|}\7\f\2\2}\u0084\5\20\t\2~\177\7\r\2\2\177\u0080\7("+
		"\2\2\u0080\u0081\7\f\2\2\u0081\u0083\5\20\t\2\u0082~\3\2\2\2\u0083\u0086"+
		"\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0088\3\2\2\2\u0086"+
		"\u0084\3\2\2\2\u0087{\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2"+
		"\u0089\u008e\7\b\2\2\u008a\u008b\7\34\2\2\u008b\u008c\5\6\4\2\u008c\u008d"+
		"\7\35\2\2\u008d\u008f\3\2\2\2\u008e\u008a\3\2\2\2\u008e\u008f\3\2\2\2"+
		"\u008f\u0090\3\2\2\2\u0090\u0091\5\b\5\2\u0091\u0093\3\2\2\2\u0092o\3"+
		"\2\2\2\u0092v\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\7\13\2\2\u0095\u0097"+
		"\3\2\2\2\u0096\u0092\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0096\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099\7\3\2\2\2\u009a\u00a3\5\n\6\2\u009b\u009c\7\3\2\2"+
		"\u009c\u00a2\5\n\6\2\u009d\u009e\7\4\2\2\u009e\u00a2\5\n\6\2\u009f\u00a0"+
		"\7\17\2\2\u00a0\u00a2\5\n\6\2\u00a1\u009b\3\2\2\2\u00a1\u009d\3\2\2\2"+
		"\u00a1\u009f\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4"+
		"\3\2\2\2\u00a4\t\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00af\5\f\7\2\u00a7"+
		"\u00a8\7\5\2\2\u00a8\u00ae\5\f\7\2\u00a9\u00aa\7\6\2\2\u00aa\u00ae\5\f"+
		"\7\2\u00ab\u00ac\7\20\2\2\u00ac\u00ae\5\f\7\2\u00ad\u00a7\3\2\2\2\u00ad"+
		"\u00a9\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2"+
		"\2\2\u00af\u00b0\3\2\2\2\u00b0\13\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00bb"+
		"\5\16\b\2\u00b3\u00b4\7\24\2\2\u00b4\u00ba\5\16\b\2\u00b5\u00b6\7\22\2"+
		"\2\u00b6\u00ba\5\16\b\2\u00b7\u00b8\7\23\2\2\u00b8\u00ba\5\16\b\2\u00b9"+
		"\u00b3\3\2\2\2\u00b9\u00b5\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bd\3\2"+
		"\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\r\3\2\2\2\u00bd\u00bb"+
		"\3\2\2\2\u00be\u0107\7\'\2\2\u00bf\u0107\7\26\2\2\u00c0\u0107\7\27\2\2"+
		"\u00c1\u0107\7#\2\2\u00c2\u00c3\7\"\2\2\u00c3\u00c4\7(\2\2\u00c4\u00cd"+
		"\7\7\2\2\u00c5\u00ca\5\b\5\2\u00c6\u00c7\7\r\2\2\u00c7\u00c9\5\b\5\2\u00c8"+
		"\u00c6\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2"+
		"\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd\u00c5\3\2\2\2\u00cd"+
		"\u00ce\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u0107\7\b\2\2\u00d0\u00d1\7\30"+
		"\2\2\u00d1\u00d2\5\b\5\2\u00d2\u00d3\7\31\2\2\u00d3\u00d4\7\t\2\2\u00d4"+
		"\u00d5\5\b\5\2\u00d5\u00d6\7\n\2\2\u00d6\u00d7\7\32\2\2\u00d7\u00d8\7"+
		"\t\2\2\u00d8\u00d9\5\b\5\2\u00d9\u00da\7\n\2\2\u00da\u0107\3\2\2\2\u00db"+
		"\u00dc\7\21\2\2\u00dc\u00dd\7\7\2\2\u00dd\u00de\5\b\5\2\u00de\u00df\7"+
		"\b\2\2\u00df\u0107\3\2\2\2\u00e0\u00e1\7\33\2\2\u00e1\u00e2\7\7\2\2\u00e2"+
		"\u00e3\5\b\5\2\u00e3\u00e4\7\b\2\2\u00e4\u0107\3\2\2\2\u00e5\u00e6\7\7"+
		"\2\2\u00e6\u00e7\5\b\5\2\u00e7\u00e8\7\b\2\2\u00e8\u0107\3\2\2\2\u00e9"+
		"\u0104\7(\2\2\u00ea\u00f3\7\7\2\2\u00eb\u00f0\5\b\5\2\u00ec\u00ed\7\r"+
		"\2\2\u00ed\u00ef\5\b\5\2\u00ee\u00ec\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0"+
		"\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2"+
		"\2\2\u00f3\u00eb\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5"+
		"\u0105\7\b\2\2\u00f6\u00f7\7\16\2\2\u00f7\u00f8\7(\2\2\u00f8\u0101\7\7"+
		"\2\2\u00f9\u00fe\5\b\5\2\u00fa\u00fb\7\r\2\2\u00fb\u00fd\5\b\5\2\u00fc"+
		"\u00fa\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2"+
		"\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u00f9\3\2\2\2\u0101"+
		"\u0102\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0105\7\b\2\2\u0104\u00ea\3\2"+
		"\2\2\u0104\u00f6\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0107\3\2\2\2\u0106"+
		"\u00be\3\2\2\2\u0106\u00bf\3\2\2\2\u0106\u00c0\3\2\2\2\u0106\u00c1\3\2"+
		"\2\2\u0106\u00c2\3\2\2\2\u0106\u00d0\3\2\2\2\u0106\u00db\3\2\2\2\u0106"+
		"\u00e0\3\2\2\2\u0106\u00e5\3\2\2\2\u0106\u00e9\3\2\2\2\u0107\17\3\2\2"+
		"\2\u0108\u010b\5\22\n\2\u0109\u010b\5\24\13\2\u010a\u0108\3\2\2\2\u010a"+
		"\u0109\3\2\2\2\u010b\21\3\2\2\2\u010c\u010d\t\2\2\2\u010d\23\3\2\2\2\u010e"+
		"\u0117\7\7\2\2\u010f\u0114\5\20\t\2\u0110\u0111\7\r\2\2\u0111\u0113\5"+
		"\20\t\2\u0112\u0110\3\2\2\2\u0113\u0116\3\2\2\2\u0114\u0112\3\2\2\2\u0114"+
		"\u0115\3\2\2\2\u0115\u0118\3\2\2\2\u0116\u0114\3\2\2\2\u0117\u010f\3\2"+
		"\2\2\u0117\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\7\b\2\2\u011a"+
		"\u011b\7&\2\2\u011b\u011c\5\22\n\2\u011c\25\3\2\2\2$\31\34\"*\669KN\\"+
		"`gm\u0084\u0087\u008e\u0092\u0098\u00a1\u00a3\u00ad\u00af\u00b9\u00bb"+
		"\u00ca\u00cd\u00f0\u00f3\u00fe\u0101\u0104\u0106\u010a\u0114\u0117";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}