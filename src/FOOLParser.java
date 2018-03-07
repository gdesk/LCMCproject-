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
		public CllistContext c;
		public DeclistContext cd;
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
					setState(25); ((ProgContext)_localctx).c = cllist();
					setState(27);
					_la = _input.LA(1);
					if (_la==VAR || _la==FUN) {
						{
						setState(26); ((ProgContext)_localctx).cd = declist();
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
				 
							if(((ProgContext)_localctx).c.astlist == null){
								((ProgContext)_localctx).ast =  new ProgLetInNode(((ProgContext)_localctx).d.astlist,((ProgContext)_localctx).e.ast);
							}else{
								((ProgContext)_localctx).ast =  new ProgLetInNode(((ProgContext)_localctx).c.astlist, ((ProgContext)_localctx).cd.astlist, ((ProgContext)_localctx).e.ast);
							}
							
						
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
		public Token fid;
		public TypeContext ret;
		public HotypeContext fh;
		public Token pid;
		public HotypeContext fh1;
		public Token vid;
		public TypeContext vt;
		public ExpContext ex;
		public ExpContext ex1;
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
					boolean isExtends=false;
				
			setState(121); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42); match(CLASS);
				setState(43); ((CllistContext)_localctx).ic = match(ID);
				ClassNode classNode = new ClassNode((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null));	/* a cosa ci serve? per ritornare? */
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
					 			STentry cstentry = new STentry(nestingLevel, new ClassTypeNode(new ArrayList<FieldNode>(),new ArrayList<MethodNode>()),offset);
					 		 	if(sym.put((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null),cstentry) != null) {
									System.out.println("Class id" + (((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null) + " at line " + (((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getLine():0) + " already created.");
									System.exit(0);
								}; 
								nestingLevel++;
								HashMap<String, STentry> virtualTable = new HashMap<String, STentry>();
								symTable.add(nestingLevel,virtualTable); /* � vuota */
								  if(classTable.put((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null), virtualTable) != null) {
				                   System.out.println("Class "+(((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)+" at line "+(((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getLine():0)+" already declared");
				                   System.exit(0); 
				                } 
					 		 	
					 		}else{
					 			HashMap<String,STentry> sym = symTable.get(nestingLevel);
					 			STentry erhm1 = sym.get((((CllistContext)_localctx).ic1!=null?((CllistContext)_localctx).ic1.getText():null)); /*stetry della classe ereditata*/
					 			ClassTypeNode erClassTypeNode = (ClassTypeNode) erhm1.getType();
					 			STentry cstentry1 = new STentry(nestingLevel, new ClassTypeNode(erClassTypeNode.getFields(),erClassTypeNode.getMethods()),offset);/* mappa classe corrente */
					 			if(sym.put((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null),cstentry1) != null) {
									System.out.println("Class id" + (((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null) + " at line " + (((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getLine():0) + " already created.");
									System.exit(0);
								}; 
								classTable.put((((CllistContext)_localctx).ic1!=null?((CllistContext)_localctx).ic1.getText():null), classTable.get((((CllistContext)_localctx).ic1!=null?((CllistContext)_localctx).ic1.getText():null))); /* copio vtable della classe ereditata*/
					 			nestingLevel++; /*perch� finita la dichiarazione classe*/
					 		}
					 	
				ClassTypeNode cTypeNode = (ClassTypeNode)symTable.get(nestingLevel-1).get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).getType();
				setState(52); match(LPAR);
				setState(68);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(53); ((CllistContext)_localctx).campo = match(ID);
					setState(54); match(COLON);
					setState(55); ((CllistContext)_localctx).t = type();

						 	  		FieldNode field = new FieldNode((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null),((CllistContext)_localctx).t.ast);
						 	  		cTypeNode.addField(field);
						 	  		int offsetCampo=0;
						 	  		if(isExtends){
						 	  			offsetCampo = (cTypeNode.getFields().size()+1)*(-1); /* sistemato offset per ereditariet� */
						 	  		}
						 	  		STentry entry = new STentry(nestingLevel, ((CllistContext)_localctx).t.ast, offsetCampo--);
						 	  		/* inserimento in symbol table */
						 	  		symTable.get(nestingLevel).put((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null),entry);
						 	  		/* inserimento in classTable*/
						 	  		if( classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).put((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null),entry) != null){ /* overriding */
						 	  			STentry oldEntry = classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).get((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null));
						 	  			oldEntry.addType(((CllistContext)_localctx).t.ast);
						 	  			oldEntry.setNestingLevel(nestingLevel);
						 	  		}	 	  		
						 	  		
						 	  	
					setState(65);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(57); match(COMMA);
						setState(58); ((CllistContext)_localctx).campo1 = match(ID);
						setState(59); match(COLON);
						setState(60); ((CllistContext)_localctx).t1 = type();
						 
							 	  			FieldNode field1 = new FieldNode((((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getText():null),((CllistContext)_localctx).t1.ast);
							 	  			cTypeNode.addField(field1);	
							 	  			STentry entry1 = new STentry(nestingLevel, ((CllistContext)_localctx).t1.ast, offsetCampo--);
							 	  			/* inserimento in symbol table */
							 	  			symTable.get(nestingLevel).put((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null),entry1);
								 	  		/* inserimento in classTable*/
								 	  		if( classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).put((((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getText():null),entry1) != null){/* overriding */
								 	  			STentry oldEntry = classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).get((((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getText():null));
								 	  			oldEntry.addType(((CllistContext)_localctx).t1.ast);
								 	  			oldEntry.setNestingLevel(nestingLevel);
							 	  			}
							 	  		
						}
						}
						setState(67);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(70); match(RPAR);
				setState(71); match(CLPAR);
				int methodOffset = 0;
				              	if(isExtends){
					 	  			methodOffset = (cTypeNode.getMethods().size()); /* sistemato offset per ereditariet� */
					 	  		}
				              
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FUN) {
					{
					{
					setState(73); match(FUN);
					setState(74); ((CllistContext)_localctx).fid = match(ID);
					setState(75); match(COLON);
					setState(76); ((CllistContext)_localctx).ret = type();

					                	 MethodNode method = new MethodNode((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null), ((CllistContext)_localctx).ret.ast);
					                	 cTypeNode.addMethod(method); /*Ricordati che vanno da m-1 a 0 */
					                	 STentry mentry = new STentry(nestingLevel, ((CllistContext)_localctx).ret.ast, methodOffset);
					                	 HashMap<String, STentry> msym = symTable.get(nestingLevel);
					                	 msym.put((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null), mentry);
					                	              	 
					                	 if(classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).put((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null), mentry) != null ) {
					                	 	STentry oldEntry = classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).get((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null));
					                	 	oldEntry.addType(((CllistContext)_localctx).ret.ast);
					                	 	oldEntry.setNestingLevel(nestingLevel);
					                	 }
					                	 methodOffset++;
					                	 
					                
					setState(78); match(LPAR);
					setState(91);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(79); match(ID);
						setState(80); match(COLON);
						setState(81); ((CllistContext)_localctx).fh = hotype();
						setState(88);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(82); match(COMMA);
							setState(83); ((CllistContext)_localctx).pid = match(ID);
							setState(84); match(COLON);
							setState(85); ((CllistContext)_localctx).fh1 = hotype();
							}
							}
							setState(90);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(93); match(RPAR);
					setState(109);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(94); match(LET);
						setState(105);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==VAR) {
							{
							{
							setState(95); match(VAR);
							setState(96); ((CllistContext)_localctx).vid = match(ID);
							setState(97); match(COLON);
							setState(98); ((CllistContext)_localctx).vt = type();
							setState(99); match(ASS);
							setState(100); ((CllistContext)_localctx).ex = exp();
							setState(101); match(SEMIC);
							}
							}
							setState(107);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(108); match(IN);
						}
					}

					setState(111); ((CllistContext)_localctx).ex1 = exp();
					setState(112); match(SEMIC);
					}
					}
					setState(118);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(119); match(CRPAR);
					isExtends = false;
				         	nestingLevel = 0;
				         
				}
				}
				setState(123); 
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
				
			setState(174); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(170);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(126); match(VAR);
					setState(127); ((DeclistContext)_localctx).i = match(ID);
					setState(128); match(COLON);
					setState(129); ((DeclistContext)_localctx).h = hotype();
					setState(130); match(ASS);
					setState(131); ((DeclistContext)_localctx).e = exp();
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
					setState(134); match(FUN);
					setState(135); ((DeclistContext)_localctx).i = match(ID);
					setState(136); match(COLON);
					setState(137); ((DeclistContext)_localctx).t = type();
						
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
								
					setState(139); match(LPAR);
						ArrayList<Node> parTypes = new ArrayList<Node>();
										int parOffset = 1;
									
					setState(156);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(141); ((DeclistContext)_localctx).i1 = match(ID);
						setState(142); match(COLON);
						setState(143); ((DeclistContext)_localctx).fty = hotype();
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
												
											
						setState(153);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(145); match(COMMA);
							setState(146); ((DeclistContext)_localctx).i2 = match(ID);
							setState(147); match(COLON);
							setState(148); ((DeclistContext)_localctx).ty = hotype();
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
							setState(155);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(158); match(RPAR);
					 entry.addType(new ArrowTypeNode(parTypes,((DeclistContext)_localctx).t.ast)); 
					setState(165);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(160); match(LET);
						setState(161); ((DeclistContext)_localctx).d = declist();
						setState(162); match(IN);
						f.addDec(((DeclistContext)_localctx).d.astlist);
						}
					}

					setState(167); ((DeclistContext)_localctx).e = exp();
						
										symTable.remove(nestingLevel--); /* Diminuisco nestingLevel perch� esco dallo scope della funzione */
										f.addBody(((DeclistContext)_localctx).e.ast);
									
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(172); match(SEMIC);
				}
				}
				setState(176); 
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
	}

	public final HotypeContext hotype() throws RecognitionException {
		HotypeContext _localctx = new HotypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_hotype);
		try {
			setState(184);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(178); ((HotypeContext)_localctx).t = type();
				((HotypeContext)_localctx).ast =  ((HotypeContext)_localctx).t.ast;
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(181); ((HotypeContext)_localctx).a = arrow();
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
	}

	public final ArrowContext arrow() throws RecognitionException {
		ArrowContext _localctx = new ArrowContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arrow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			ArrayList<Node> hotypeList = new ArrayList<>();
			setState(187); match(LPAR);
			setState(199);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(188); ((ArrowContext)_localctx).h = hotype();
				hotypeList.add(((ArrowContext)_localctx).h.ast);
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(190); match(COMMA);
					setState(191); ((ArrowContext)_localctx).h1 = hotype();
					hotypeList.add(((ArrowContext)_localctx).h1.ast);
					}
					}
					setState(198);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(201); match(RPAR);
			setState(202); match(ARROW);
			setState(203); ((ArrowContext)_localctx).t = type();
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
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		try {
			setState(212);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(206); match(INT);
				((TypeContext)_localctx).ast =  new IntTypeNode();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(208); match(BOOL);
				((TypeContext)_localctx).ast =  new BoolTypeNode();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(210); match(ID);
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
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214); ((ExpContext)_localctx).t = term();
			((ExpContext)_localctx).ast =  ((ExpContext)_localctx).t.ast;
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0)) {
				{
				setState(228);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(216); match(PLUS);
					setState(217); ((ExpContext)_localctx).p = term();
					((ExpContext)_localctx).ast =  new PlusNode(_localctx.ast,((ExpContext)_localctx).p.ast);
					}
					break;
				case MINUS:
					{
					setState(220); match(MINUS);
					setState(221); ((ExpContext)_localctx).m = term();
					((ExpContext)_localctx).ast =  new MinusNode(_localctx.ast, ((ExpContext)_localctx).m.ast);
					}
					break;
				case OR:
					{
					setState(224); match(OR);
					setState(225); ((ExpContext)_localctx).o = term();
					((ExpContext)_localctx).ast =  new OrNode(_localctx.ast, ((ExpContext)_localctx).o.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(232);
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
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233); ((TermContext)_localctx).f = factor();
			((TermContext)_localctx).ast =  ((TermContext)_localctx).f.ast;
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TIMES) | (1L << DIV) | (1L << AND))) != 0)) {
				{
				setState(247);
				switch (_input.LA(1)) {
				case TIMES:
					{
					setState(235); match(TIMES);
					setState(236); ((TermContext)_localctx).t = factor();
					((TermContext)_localctx).ast =  new TimesNode(_localctx.ast,((TermContext)_localctx).t.ast);
					}
					break;
				case DIV:
					{
					setState(239); match(DIV);
					setState(240); ((TermContext)_localctx).d = factor();
					((TermContext)_localctx).ast =  new DivNode(_localctx.ast,((TermContext)_localctx).d.ast);
					}
					break;
				case AND:
					{
					setState(243); match(AND);
					setState(244); ((TermContext)_localctx).a = factor();
					 ((TermContext)_localctx).ast =  new AndNode(_localctx.ast,((TermContext)_localctx).a.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(251);
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
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252); ((FactorContext)_localctx).v = value();
			((FactorContext)_localctx).ast =  ((FactorContext)_localctx).v.ast;
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << LE) | (1L << EQ))) != 0)) {
				{
				setState(266);
				switch (_input.LA(1)) {
				case EQ:
					{
					setState(254); match(EQ);
					setState(255); ((FactorContext)_localctx).e = value();
					((FactorContext)_localctx).ast =  new EqualNode(_localctx.ast,((FactorContext)_localctx).e.ast);
					}
					break;
				case GE:
					{
					setState(258); match(GE);
					setState(259); ((FactorContext)_localctx).g = value();
					((FactorContext)_localctx).ast =  new GENode(_localctx.ast,((FactorContext)_localctx).g.ast);
					}
					break;
				case LE:
					{
					setState(262); match(LE);
					setState(263); ((FactorContext)_localctx).l = value();
					((FactorContext)_localctx).ast =  new LENode(_localctx.ast,((FactorContext)_localctx).l.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(270);
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
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_value);
		int _la;
		try {
			setState(357);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(271); ((ValueContext)_localctx).in = match(INTEGER);
				((ValueContext)_localctx).ast =  new IntNode(Integer.parseInt((((ValueContext)_localctx).in!=null?((ValueContext)_localctx).in.getText():null)));
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(273); match(TRUE);
				((ValueContext)_localctx).ast =  new BoolNode(true);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(275); match(FALSE);
				((ValueContext)_localctx).ast =  new BoolNode(false);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(277); match(NULL);
				((ValueContext)_localctx).ast =  new EmptyNode();
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 5);
				{
				setState(279); match(NEW);
				setState(280); match(ID);
				setState(281); match(LPAR);
				setState(290);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(282); exp();
					setState(287);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(283); match(COMMA);
						setState(284); exp();
						}
						}
						setState(289);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(292); match(RPAR);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 6);
				{
				setState(293); match(LPAR);
				setState(294); ((ValueContext)_localctx).e = exp();
				setState(295); match(RPAR);
				((ValueContext)_localctx).ast =  ((ValueContext)_localctx).e.ast;
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 7);
				{
				setState(298); match(IF);
				setState(299); ((ValueContext)_localctx).e1 = exp();
				setState(300); match(THEN);
				setState(301); match(CLPAR);
				setState(302); ((ValueContext)_localctx).e2 = exp();
				setState(303); match(CRPAR);
				setState(304); match(ELSE);
				setState(305); match(CLPAR);
				setState(306); ((ValueContext)_localctx).e3 = exp();
				setState(307); match(CRPAR);
				((ValueContext)_localctx).ast =  new IfNode(((ValueContext)_localctx).e1.ast,((ValueContext)_localctx).e2.ast,((ValueContext)_localctx).e3.ast);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 8);
				{
				setState(310); match(NOT);
				setState(311); match(LPAR);
				setState(312); ((ValueContext)_localctx).e = exp();
				((ValueContext)_localctx).ast =  new NotNode(((ValueContext)_localctx).e.ast);
				setState(314); match(RPAR);
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 9);
				{
				setState(316); match(PRINT);
				setState(317); match(LPAR);
				setState(318); ((ValueContext)_localctx).e = exp();
				setState(319); match(RPAR);
				((ValueContext)_localctx).ast =  new PrintNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 10);
				{
				setState(322); ((ValueContext)_localctx).i = match(ID);
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
						
				setState(355);
				switch (_input.LA(1)) {
				case LPAR:
					{
					setState(324); match(LPAR);
					 ArrayList<Node> arglist = new ArrayList<Node>(); 
					setState(337);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(326); ((ValueContext)_localctx).a = exp();
						 arglist.add(((ValueContext)_localctx).a.ast); 
						setState(334);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(328); match(COMMA);
							setState(329); ((ValueContext)_localctx).a1 = exp();
							 arglist.add(((ValueContext)_localctx).a1.ast); 
							}
							}
							setState(336);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(339); match(RPAR);
					 ((ValueContext)_localctx).ast =  new CallNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null),entry,arglist,nestingLevel); 
					}
					break;
				case DOT:
					{
					setState(341); match(DOT);
					setState(342); match(ID);
					setState(343); match(LPAR);
					setState(352);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(344); exp();
						setState(349);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(345); match(COMMA);
							setState(346); exp();
							}
							}
							setState(351);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(354); match(RPAR);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3+\u016a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\36\n\2\3\2\5\2!\n\2\3\2\3\2\3\2\3"+
		"\2\5\2\'\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\63\n\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3B\n\3\f\3\16\3E\13\3"+
		"\5\3G\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\7\3Y\n\3\f\3\16\3\\\13\3\5\3^\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\7\3j\n\3\f\3\16\3m\13\3\3\3\5\3p\n\3\3\3\3\3\3\3\7\3u\n\3\f\3"+
		"\16\3x\13\3\3\3\3\3\6\3|\n\3\r\3\16\3}\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\7\4\u009a\n\4\f\4\16\4\u009d\13\4\5\4\u009f\n\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\5\4\u00a8\n\4\3\4\3\4\3\4\5\4\u00ad\n\4\3\4\3\4\6\4\u00b1\n\4"+
		"\r\4\16\4\u00b2\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00bb\n\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\7\6\u00c5\n\6\f\6\16\6\u00c8\13\6\5\6\u00ca\n\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00d7\n\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00e7\n\b\f\b\16\b\u00ea\13"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00fa\n"+
		"\t\f\t\16\t\u00fd\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\7\n\u010d\n\n\f\n\16\n\u0110\13\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0120\n\13\f\13\16\13"+
		"\u0123\13\13\5\13\u0125\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\7\13\u014f\n\13\f\13\16\13\u0152\13\13\5\13\u0154\n"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u015e\n\13\f\13\16\13"+
		"\u0161\13\13\5\13\u0163\n\13\3\13\5\13\u0166\n\13\5\13\u0168\n\13\3\13"+
		"\2\2\f\2\4\6\b\n\f\16\20\22\24\2\2\u018f\2\26\3\2\2\2\4+\3\2\2\2\6\177"+
		"\3\2\2\2\b\u00ba\3\2\2\2\n\u00bc\3\2\2\2\f\u00d6\3\2\2\2\16\u00d8\3\2"+
		"\2\2\20\u00eb\3\2\2\2\22\u00fe\3\2\2\2\24\u0167\3\2\2\2\26&\b\2\1\2\27"+
		"\30\5\16\b\2\30\31\b\2\1\2\31\'\3\2\2\2\32 \7\34\2\2\33\35\5\4\3\2\34"+
		"\36\5\6\4\2\35\34\3\2\2\2\35\36\3\2\2\2\36!\3\2\2\2\37!\5\6\4\2 \33\3"+
		"\2\2\2 \37\3\2\2\2!\"\3\2\2\2\"#\7\35\2\2#$\5\16\b\2$%\b\2\1\2%\'\3\2"+
		"\2\2&\27\3\2\2\2&\32\3\2\2\2\'(\3\2\2\2()\b\2\1\2)*\7\13\2\2*\3\3\2\2"+
		"\2+{\b\3\1\2,-\7 \2\2-.\7(\2\2.\62\b\3\1\2/\60\7!\2\2\60\61\7(\2\2\61"+
		"\63\b\3\1\2\62/\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65\b\3\1\2\65\66"+
		"\b\3\1\2\66F\7\7\2\2\678\7(\2\289\7\f\2\29:\5\f\7\2:C\b\3\1\2;<\7\r\2"+
		"\2<=\7(\2\2=>\7\f\2\2>?\5\f\7\2?@\b\3\1\2@B\3\2\2\2A;\3\2\2\2BE\3\2\2"+
		"\2CA\3\2\2\2CD\3\2\2\2DG\3\2\2\2EC\3\2\2\2F\67\3\2\2\2FG\3\2\2\2GH\3\2"+
		"\2\2HI\7\b\2\2IJ\7\t\2\2Jv\b\3\1\2KL\7\37\2\2LM\7(\2\2MN\7\f\2\2NO\5\f"+
		"\7\2OP\b\3\1\2P]\7\7\2\2QR\7(\2\2RS\7\f\2\2SZ\5\b\5\2TU\7\r\2\2UV\7(\2"+
		"\2VW\7\f\2\2WY\5\b\5\2XT\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[^\3\2"+
		"\2\2\\Z\3\2\2\2]Q\3\2\2\2]^\3\2\2\2^_\3\2\2\2_o\7\b\2\2`k\7\34\2\2ab\7"+
		"\36\2\2bc\7(\2\2cd\7\f\2\2de\5\f\7\2ef\7\25\2\2fg\5\16\b\2gh\7\13\2\2"+
		"hj\3\2\2\2ia\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2ln\3\2\2\2mk\3\2\2\2"+
		"np\7\35\2\2o`\3\2\2\2op\3\2\2\2pq\3\2\2\2qr\5\16\b\2rs\7\13\2\2su\3\2"+
		"\2\2tK\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2wy\3\2\2\2xv\3\2\2\2yz\7\n"+
		"\2\2z|\b\3\1\2{,\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\5\3\2\2\2\177"+
		"\u00b0\b\4\1\2\u0080\u0081\7\36\2\2\u0081\u0082\7(\2\2\u0082\u0083\7\f"+
		"\2\2\u0083\u0084\5\b\5\2\u0084\u0085\7\25\2\2\u0085\u0086\5\16\b\2\u0086"+
		"\u0087\b\4\1\2\u0087\u00ad\3\2\2\2\u0088\u0089\7\37\2\2\u0089\u008a\7"+
		"(\2\2\u008a\u008b\7\f\2\2\u008b\u008c\5\f\7\2\u008c\u008d\b\4\1\2\u008d"+
		"\u008e\7\7\2\2\u008e\u009e\b\4\1\2\u008f\u0090\7(\2\2\u0090\u0091\7\f"+
		"\2\2\u0091\u0092\5\b\5\2\u0092\u009b\b\4\1\2\u0093\u0094\7\r\2\2\u0094"+
		"\u0095\7(\2\2\u0095\u0096\7\f\2\2\u0096\u0097\5\b\5\2\u0097\u0098\b\4"+
		"\1\2\u0098\u009a\3\2\2\2\u0099\u0093\3\2\2\2\u009a\u009d\3\2\2\2\u009b"+
		"\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2"+
		"\2\2\u009e\u008f\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\u00a1\7\b\2\2\u00a1\u00a7\b\4\1\2\u00a2\u00a3\7\34\2\2\u00a3\u00a4\5"+
		"\6\4\2\u00a4\u00a5\7\35\2\2\u00a5\u00a6\b\4\1\2\u00a6\u00a8\3\2\2\2\u00a7"+
		"\u00a2\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\5\16"+
		"\b\2\u00aa\u00ab\b\4\1\2\u00ab\u00ad\3\2\2\2\u00ac\u0080\3\2\2\2\u00ac"+
		"\u0088\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\7\13\2\2\u00af\u00b1\3"+
		"\2\2\2\u00b0\u00ac\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2"+
		"\u00b3\3\2\2\2\u00b3\7\3\2\2\2\u00b4\u00b5\5\f\7\2\u00b5\u00b6\b\5\1\2"+
		"\u00b6\u00bb\3\2\2\2\u00b7\u00b8\5\n\6\2\u00b8\u00b9\b\5\1\2\u00b9\u00bb"+
		"\3\2\2\2\u00ba\u00b4\3\2\2\2\u00ba\u00b7\3\2\2\2\u00bb\t\3\2\2\2\u00bc"+
		"\u00bd\b\6\1\2\u00bd\u00c9\7\7\2\2\u00be\u00bf\5\b\5\2\u00bf\u00c6\b\6"+
		"\1\2\u00c0\u00c1\7\r\2\2\u00c1\u00c2\5\b\5\2\u00c2\u00c3\b\6\1\2\u00c3"+
		"\u00c5\3\2\2\2\u00c4\u00c0\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2"+
		"\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9"+
		"\u00be\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\7\b"+
		"\2\2\u00cc\u00cd\7&\2\2\u00cd\u00ce\5\f\7\2\u00ce\u00cf\b\6\1\2\u00cf"+
		"\13\3\2\2\2\u00d0\u00d1\7$\2\2\u00d1\u00d7\b\7\1\2\u00d2\u00d3\7%\2\2"+
		"\u00d3\u00d7\b\7\1\2\u00d4\u00d5\7(\2\2\u00d5\u00d7\b\7\1\2\u00d6\u00d0"+
		"\3\2\2\2\u00d6\u00d2\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\r\3\2\2\2\u00d8"+
		"\u00d9\5\20\t\2\u00d9\u00e8\b\b\1\2\u00da\u00db\7\3\2\2\u00db\u00dc\5"+
		"\20\t\2\u00dc\u00dd\b\b\1\2\u00dd\u00e7\3\2\2\2\u00de\u00df\7\4\2\2\u00df"+
		"\u00e0\5\20\t\2\u00e0\u00e1\b\b\1\2\u00e1\u00e7\3\2\2\2\u00e2\u00e3\7"+
		"\17\2\2\u00e3\u00e4\5\20\t\2\u00e4\u00e5\b\b\1\2\u00e5\u00e7\3\2\2\2\u00e6"+
		"\u00da\3\2\2\2\u00e6\u00de\3\2\2\2\u00e6\u00e2\3\2\2\2\u00e7\u00ea\3\2"+
		"\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\17\3\2\2\2\u00ea\u00e8"+
		"\3\2\2\2\u00eb\u00ec\5\22\n\2\u00ec\u00fb\b\t\1\2\u00ed\u00ee\7\5\2\2"+
		"\u00ee\u00ef\5\22\n\2\u00ef\u00f0\b\t\1\2\u00f0\u00fa\3\2\2\2\u00f1\u00f2"+
		"\7\6\2\2\u00f2\u00f3\5\22\n\2\u00f3\u00f4\b\t\1\2\u00f4\u00fa\3\2\2\2"+
		"\u00f5\u00f6\7\20\2\2\u00f6\u00f7\5\22\n\2\u00f7\u00f8\b\t\1\2\u00f8\u00fa"+
		"\3\2\2\2\u00f9\u00ed\3\2\2\2\u00f9\u00f1\3\2\2\2\u00f9\u00f5\3\2\2\2\u00fa"+
		"\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\21\3\2\2"+
		"\2\u00fd\u00fb\3\2\2\2\u00fe\u00ff\5\24\13\2\u00ff\u010e\b\n\1\2\u0100"+
		"\u0101\7\24\2\2\u0101\u0102\5\24\13\2\u0102\u0103\b\n\1\2\u0103\u010d"+
		"\3\2\2\2\u0104\u0105\7\22\2\2\u0105\u0106\5\24\13\2\u0106\u0107\b\n\1"+
		"\2\u0107\u010d\3\2\2\2\u0108\u0109\7\23\2\2\u0109\u010a\5\24\13\2\u010a"+
		"\u010b\b\n\1\2\u010b\u010d\3\2\2\2\u010c\u0100\3\2\2\2\u010c\u0104\3\2"+
		"\2\2\u010c\u0108\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e"+
		"\u010f\3\2\2\2\u010f\23\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0112\7\'\2"+
		"\2\u0112\u0168\b\13\1\2\u0113\u0114\7\26\2\2\u0114\u0168\b\13\1\2\u0115"+
		"\u0116\7\27\2\2\u0116\u0168\b\13\1\2\u0117\u0118\7#\2\2\u0118\u0168\b"+
		"\13\1\2\u0119\u011a\7\"\2\2\u011a\u011b\7(\2\2\u011b\u0124\7\7\2\2\u011c"+
		"\u0121\5\16\b\2\u011d\u011e\7\r\2\2\u011e\u0120\5\16\b\2\u011f\u011d\3"+
		"\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122"+
		"\u0125\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u011c\3\2\2\2\u0124\u0125\3\2"+
		"\2\2\u0125\u0126\3\2\2\2\u0126\u0168\7\b\2\2\u0127\u0128\7\7\2\2\u0128"+
		"\u0129\5\16\b\2\u0129\u012a\7\b\2\2\u012a\u012b\b\13\1\2\u012b\u0168\3"+
		"\2\2\2\u012c\u012d\7\30\2\2\u012d\u012e\5\16\b\2\u012e\u012f\7\31\2\2"+
		"\u012f\u0130\7\t\2\2\u0130\u0131\5\16\b\2\u0131\u0132\7\n\2\2\u0132\u0133"+
		"\7\32\2\2\u0133\u0134\7\t\2\2\u0134\u0135\5\16\b\2\u0135\u0136\7\n\2\2"+
		"\u0136\u0137\b\13\1\2\u0137\u0168\3\2\2\2\u0138\u0139\7\21\2\2\u0139\u013a"+
		"\7\7\2\2\u013a\u013b\5\16\b\2\u013b\u013c\b\13\1\2\u013c\u013d\7\b\2\2"+
		"\u013d\u0168\3\2\2\2\u013e\u013f\7\33\2\2\u013f\u0140\7\7\2\2\u0140\u0141"+
		"\5\16\b\2\u0141\u0142\7\b\2\2\u0142\u0143\b\13\1\2\u0143\u0168\3\2\2\2"+
		"\u0144\u0145\7(\2\2\u0145\u0165\b\13\1\2\u0146\u0147\7\7\2\2\u0147\u0153"+
		"\b\13\1\2\u0148\u0149\5\16\b\2\u0149\u0150\b\13\1\2\u014a\u014b\7\r\2"+
		"\2\u014b\u014c\5\16\b\2\u014c\u014d\b\13\1\2\u014d\u014f\3\2\2\2\u014e"+
		"\u014a\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2"+
		"\2\2\u0151\u0154\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u0148\3\2\2\2\u0153"+
		"\u0154\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0156\7\b\2\2\u0156\u0166\b\13"+
		"\1\2\u0157\u0158\7\16\2\2\u0158\u0159\7(\2\2\u0159\u0162\7\7\2\2\u015a"+
		"\u015f\5\16\b\2\u015b\u015c\7\r\2\2\u015c\u015e\5\16\b\2\u015d\u015b\3"+
		"\2\2\2\u015e\u0161\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"\u0163\3\2\2\2\u0161\u015f\3\2\2\2\u0162\u015a\3\2\2\2\u0162\u0163\3\2"+
		"\2\2\u0163\u0164\3\2\2\2\u0164\u0166\7\b\2\2\u0165\u0146\3\2\2\2\u0165"+
		"\u0157\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0168\3\2\2\2\u0167\u0111\3\2"+
		"\2\2\u0167\u0113\3\2\2\2\u0167\u0115\3\2\2\2\u0167\u0117\3\2\2\2\u0167"+
		"\u0119\3\2\2\2\u0167\u0127\3\2\2\2\u0167\u012c\3\2\2\2\u0167\u0138\3\2"+
		"\2\2\u0167\u013e\3\2\2\2\u0167\u0144\3\2\2\2\u0168\25\3\2\2\2%\35 &\62"+
		"CFZ]kov}\u009b\u009e\u00a7\u00ac\u00b2\u00ba\u00c6\u00c9\u00d6\u00e6\u00e8"+
		"\u00f9\u00fb\u010c\u010e\u0121\u0124\u0150\u0153\u015f\u0162\u0165\u0167";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}