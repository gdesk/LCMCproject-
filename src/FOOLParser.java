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
					boolean isCl = false;
					boolean isDec = false;
					boolean isOnlyDec = false;
				
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
						setState(27); ((ProgContext)_localctx).cd = declist();
						isDec = true;
						}
					}

					}
					break;
				case VAR:
				case FUN:
					{
					setState(32); ((ProgContext)_localctx).d = declist();
					isOnlyDec = true;
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(37); match(IN);
				setState(38); ((ProgContext)_localctx).e = exp();
				 
							if(isOnlyDec){
								((ProgContext)_localctx).ast =  new ProgLetInNode(((ProgContext)_localctx).d.astlist,((ProgContext)_localctx).e.ast);
							}
							if(isCl && isDec){
								((ProgContext)_localctx).ast =  new ProgLetInNode(((ProgContext)_localctx).c.astlist, ((ProgContext)_localctx).cd.astlist, ((ProgContext)_localctx).e.ast);
							}
							if(isCl && !isDec){
								((ProgContext)_localctx).ast =  new ProgLetInNode(((ProgContext)_localctx).c.astlist, new ArrayList<DecNode>(), ((ProgContext)_localctx).e.ast);
							}
								
						
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
				
					
					isCl = false;
					isDec = false;
					isOnlyDec = false;
				
			setState(44); match(SEMIC);
			symTable.remove(nestingLevel--); /* A che serve? */
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
		public ArrayList<ClassNode> astlist;
		public Token ic;
		public Token ic1;
		public Token campo;
		public TypeContext t;
		public Token campo1;
		public TypeContext t1;
		public Token fid;
		public TypeContext ret;
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
	}

	public final CllistContext cllist() throws RecognitionException {
		CllistContext _localctx = new CllistContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_cllist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				
					((CllistContext)_localctx).astlist =  new ArrayList<ClassNode>();
					/* Indice di convenzione di inizio  per le dichiarazioni delle classi (che viene decrementato) */
					int offset = -2; 
					boolean isExtends=false;
					/* Variabile locale che tiene contro della ridefinizione erronea di cammpi e metodi (ottimizzazione) */
					HashSet<String> localVariable; 
				
			setState(138); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48); match(CLASS);
				setState(49); ((CllistContext)_localctx).ic = match(ID);

					 			ClassNode classNode = new ClassNode((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null));	
					 		 	offset--; 	
					 		 	localVariable = new HashSet<String>();
					 		
				setState(54);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(51); match(EXTENDS);
					setState(52); ((CllistContext)_localctx).ic1 = match(ID);

						 			isExtends = true;
						 			FOOLlib.addSuperType((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null), (((CllistContext)_localctx).ic1!=null?((CllistContext)_localctx).ic1.getText():null));
						 		
					}
				}


					 		if(isExtends == false){
					 			HashMap<String,STentry> sym = symTable.get(nestingLevel);
					 			ClassTypeNode symType = new ClassTypeNode(new ArrayList<FieldNode>(),new ArrayList<MethodNode>());
					 			STentry cstentry = new STentry(nestingLevel, symType, offset);
					 		 	if(sym.put((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null),cstentry) != null) {
									System.out.println("Class id" + (((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null) + " at line " + (((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getLine():0) + " already created.");
									System.exit(0);
								}; 
								classNode.setSymType(symType);
								nestingLevel++;
								HashMap<String, STentry> virtualTable = new HashMap<String, STentry>();
								symTable.add(nestingLevel,virtualTable); 
								if(classTable.put((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null), virtualTable) != null) {
				                   System.out.println("Class id  "+(((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)+" at line "+(((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getLine():0)+" already declared");
				                   System.exit(0); 
				                } 
					 		 	
					 		} else {
					 			/* Controllo che la classe ereditata sia stata dichiarata */
					 			if(!classTable.keySet().contains((((CllistContext)_localctx).ic1!=null?((CllistContext)_localctx).ic1.getText():null))){
					 			 	System.out.println("Extended class id" + (((CllistContext)_localctx).ic1!=null?((CllistContext)_localctx).ic1.getText():null) + " at line " + (((CllistContext)_localctx).ic1!=null?((CllistContext)_localctx).ic1.getLine():0) + " never declarated.");
									System.exit(0);
					 			 }
						 		HashMap<String,STentry> sym = symTable.get(nestingLevel);
						 		/* STetry della classe ereditata*/
						 		STentry erhm1 = sym.get((((CllistContext)_localctx).ic1!=null?((CllistContext)_localctx).ic1.getText():null));
					 			/* Setto STentry della classe ereditata */
						 		classNode.setSuperEntry(erhm1);
						 		ClassTypeNode erClassTypeNode = (ClassTypeNode) erhm1.getType();
						 		ClassTypeNode symType = new ClassTypeNode(erClassTypeNode.getFields(),erClassTypeNode.getMethods()); 	
						 		/* STentry della classe corrente */
						 		STentry cstentry1 = new STentry(nestingLevel, symType ,offset);
						 		if(sym.put((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null), cstentry1) != null) {
									System.out.println("Class id" + (((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null) + " at line " + (((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getLine():0) + " already created.");
									System.exit(0);
								}; 
								classNode.setSymType(symType);
								/* Copio la virtual table della classe ereditata */
								classTable.put((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null), classTable.get((((CllistContext)_localctx).ic1!=null?((CllistContext)_localctx).ic1.getText():null)));
						 		nestingLevel++; 
					 			HashMap<String,STentry> chm = new HashMap<String,STentry>();
								symTable.add(chm);
					 		}
					 	
				ClassTypeNode cTypeNode = (ClassTypeNode)symTable.get(nestingLevel-1).get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).getType();
				setState(58); match(LPAR);
				setState(76);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(59); ((CllistContext)_localctx).campo = match(ID);
					setState(60); match(COLON);
					setState(61); ((CllistContext)_localctx).t = type();

						 	  		/* Offset dei campi settato a 0, perch� decrementato al primo utilizzo. (Primo campo offset = -1) */
						 	  		int offsetCampo=0;
						 	  		if(isExtends) {
						 	  			offsetCampo = -cTypeNode.getFields().size()-1;
						 	  		}
						 	  		
						 	  		/* Controllo che il field non � presente all'interno dei localVariable */
						 	  		if(localVariable.contains((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null))) {
						 	  			System.out.println("Field" + (((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null) + " at line " + (((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getLine():0) + " already created in localVariable(HashSet<String>).");
										System.exit(0);
						 	  		}
						 	  		localVariable.add((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null));
						 	  		
						 	  		FieldNode field = new FieldNode((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null),((CllistContext)_localctx).t.ast);
						 	  		STentry entry = new STentry(nestingLevel, ((CllistContext)_localctx).t.ast, offsetCampo--);
						 	  		symTable.get(nestingLevel).put((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null),entry);
						 	  		field.setOffset(offsetCampo); 		 
						 	  		if( classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).put((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null),entry) != null) {
						 	  			/* Overriding: sostituisco il nuovo STentry alla vecchia STentry, preservando l'offset */
						 	  			STentry oldEntry = classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).get((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null));
						 	  			oldEntry.addType(((CllistContext)_localctx).t.ast);
						 	  			oldEntry.setNestingLevel(nestingLevel);
						 	  		}
						 	  		if(!(cTypeNode.getIDs().contains((((CllistContext)_localctx).campo!=null?((CllistContext)_localctx).campo.getText():null)))){
						 	  			cTypeNode.addField(field);
						 	  		}
						 	  		 		
						 	  	
					setState(71);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(63); match(COMMA);
						setState(64); ((CllistContext)_localctx).campo1 = match(ID);
						setState(65); match(COLON);
						setState(66); ((CllistContext)_localctx).t1 = type();
						 
							 	  			/* Controllo che il campo non � presente all'interno dei localVariable */
							 	  			if(localVariable.contains((((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getText():null))) {
							 	  				System.out.println("Field" + (((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getText():null) + " at line " + (((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getLine():0) + " already created in localVariable(HashSet<String>).");
												System.exit(0);
							 	  			}
							 	  			localVariable.add((((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getText():null));
							 	  			
							 	  			FieldNode field1 = new FieldNode((((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getText():null),((CllistContext)_localctx).t1.ast);
							 	  			STentry entry1 = new STentry(nestingLevel, ((CllistContext)_localctx).t1.ast, offsetCampo--);
							 	  			symTable.get(nestingLevel).put((((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getText():null),entry1);
							 	  			field1.setOffset(offsetCampo);
								 	  		if( classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).put((((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getText():null),entry1) != null) {
								 	  			/* Overriding: sostituisco il nuovo STentry alla vecchia STentry, preservando l'offset */
								 	  			STentry oldEntry = classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).get((((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getText():null));
								 	  			oldEntry.addType(((CllistContext)_localctx).t1.ast);
								 	  			oldEntry.setNestingLevel(nestingLevel);
							 	  			}
							 	  			if(!(cTypeNode.getIDs().contains((((CllistContext)_localctx).campo1!=null?((CllistContext)_localctx).campo1.getText():null)))){
							 	  				cTypeNode.addField(field1);
							 	  			}
							 	  		
						}
						}
						setState(73);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}

						 	  		/* Aggiornamento classTypeNode: posizione = -offset-1 */
						 	  		/*cTypeNode.refreshFields(); */
						 	  	
					}
				}

				setState(78); match(RPAR);
				setState(79); match(CLPAR);
				 
					              	int methodOffset = 0;
					              	if(isExtends) {
						 	  			methodOffset = (cTypeNode.getMethods().size());
						 	  		}
				              
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FUN) {
					{
					{
					setState(81); match(FUN);
					setState(82); ((CllistContext)_localctx).fid = match(ID);
					setState(83); match(COLON);
					setState(84); ((CllistContext)_localctx).ret = type();

						 	  			/* Controllo che il metodo non � presente all'interno dei localVariable */
						 	  			if(localVariable.contains((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null))) {
						 	  				System.out.println("Method" + (((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null) + " at line " + (((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getLine():0) + " already created in localVariable(HashSet<String>).");
											System.exit(0);
						 	  			}
						 	  			localVariable.add((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null));
						 	  			
					                	MethodNode method = new MethodNode((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null), ((CllistContext)_localctx).ret.ast);
					                	method.setOffset(methodOffset);
					                	cTypeNode.addMethod(method);
					                	STentry mentry = new STentry(nestingLevel, ((CllistContext)_localctx).ret.ast, methodOffset);
					                	method.setSymType(((CllistContext)_localctx).ret.ast);
					                	mentry.setIsMethod();
					                	
					                	HashMap<String, STentry> msym = symTable.get(nestingLevel);
					                	msym.put((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null), mentry);
					                	       	 
					                	if(classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).put((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null), mentry) != null ) {
					                	 	/* Overriding: sostituisco il nuovo STentry alla vecchia STentry, preservando l'offset */
					                	 	STentry oldEntry = classTable.get((((CllistContext)_localctx).ic!=null?((CllistContext)_localctx).ic.getText():null)).get((((CllistContext)_localctx).fid!=null?((CllistContext)_localctx).fid.getText():null));
					                	 	oldEntry.addType(((CllistContext)_localctx).ret.ast);
					                	 	oldEntry.setNestingLevel(nestingLevel);
					                	}
					                	
					                	methodOffset++;
										/* Aumento il livello perch� sono all'interno di una metodo (anche i parametri passati alla funzione rientrano nel livello interno) */
										nestingLevel++;
										HashMap<String,STentry> mhm = new HashMap<String,STentry>();
										symTable.add(mhm);
					                	 
					                
					setState(86); match(LPAR);
					setState(104);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(87); ((CllistContext)_localctx).parid = match(ID);
						setState(88); match(COLON);
						setState(89); ((CllistContext)_localctx).fh = hotype();

						                 			int parOffset = 1;

						                 			ArrayList<ParNode> parlist = new ArrayList<>();
						                 			ParNode par = new ParNode((((CllistContext)_localctx).parid!=null?((CllistContext)_localctx).parid.getText():null), ((CllistContext)_localctx).fh.ast);
						                 			parlist.add(par);
						                 			if(((CllistContext)_localctx).fh.ast instanceof ArrowTypeNode) parOffset++; 
						                 			if(mhm.put((((CllistContext)_localctx).parid!=null?((CllistContext)_localctx).parid.getText():null), new STentry(nestingLevel, ((CllistContext)_localctx).fh.ast, parOffset++)) != null){
														System.out.println("Par ID: " + (((CllistContext)_localctx).parid!=null?((CllistContext)_localctx).parid.getText():null) + " at line " + (((CllistContext)_localctx).parid!=null?((CllistContext)_localctx).parid.getLine():0) + " already declared");
														System.exit(0);
							                 		}
						                 		
						setState(99);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(91); match(COMMA);
							setState(92); ((CllistContext)_localctx).parid1 = match(ID);
							setState(93); match(COLON);
							setState(94); ((CllistContext)_localctx).fh1 = hotype();

							                 	   		ParNode par1 = new ParNode((((CllistContext)_localctx).parid1!=null?((CllistContext)_localctx).parid1.getText():null), ((CllistContext)_localctx).fh1.ast);
							                 			parlist.add(par1);
							                 			if(((CllistContext)_localctx).fh1.ast instanceof ArrowTypeNode) parOffset++; 
							                 			if(mhm.put((((CllistContext)_localctx).parid1!=null?((CllistContext)_localctx).parid1.getText():null), new STentry(nestingLevel, ((CllistContext)_localctx).fh1.ast, parOffset++)) != null){
															System.out.println("Par ID: " + (((CllistContext)_localctx).parid1!=null?((CllistContext)_localctx).parid1.getText():null) + " at line " + (((CllistContext)_localctx).parid1!=null?((CllistContext)_localctx).parid1.getLine():0) + " already declared");
															System.exit(0);
								                 		}
							                 	   	
							}
							}
							setState(101);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}

						                 	   	method.addParList(parlist);
						                 	   
						}
					}

					setState(106); match(RPAR);

					                 		 	/* Aggiornamento classTypeNode: posizione = offset */
					                 		 	/*cTypeNode.refreshMethods();*/
					                 		 	ArrayList<VarNode> varlist = new ArrayList<>();
					                 		 
					setState(125);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(108); match(LET);
						setState(120);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==VAR) {
							{
							{
							setState(109); match(VAR);
							setState(110); ((CllistContext)_localctx).vid = match(ID);
							setState(111); match(COLON);
							setState(112); ((CllistContext)_localctx).vt = type();
							setState(113); match(ASS);
							setState(114); ((CllistContext)_localctx).ex = exp();

								                     		VarNode var = new VarNode((((CllistContext)_localctx).vid!=null?((CllistContext)_localctx).vid.getText():null), ((CllistContext)_localctx).vt.ast, ((CllistContext)_localctx).ex.ast);
								                     		varlist.add(var);
															if(mhm.put((((CllistContext)_localctx).vid!=null?((CllistContext)_localctx).vid.getText():null),new STentry(nestingLevel, ((CllistContext)_localctx).vt.ast, offset--)) != null) {
																System.out.println("Var id" + (((CllistContext)_localctx).vid!=null?((CllistContext)_localctx).vid.getText():null) + " at line " + (((CllistContext)_localctx).vid!=null?((CllistContext)_localctx).vid.getLine():0) + " already declared.");
																System.exit(0);
								                     		}
								                     		if(((CllistContext)_localctx).vt.ast instanceof ArrowTypeNode) offset--;
								                     	
							setState(116); match(SEMIC);
							}
							}
							setState(122);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}

							                     	method.addVarList(varlist);
							                     
						setState(124); match(IN);
						}
					}

					setState(127); ((CllistContext)_localctx).exp1 = exp();

						                     	method.addExp(((CllistContext)_localctx).exp1.ast);
					                    		symTable.remove(nestingLevel--);
						                     
					setState(129); match(SEMIC);
					}
					}
					setState(135);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(136); match(CRPAR);
					
				         	/* Inserisco i campi e metodi dentro l'istanza di ClassNode, prendendoli dal ClassTypeNode corrente */
				         	classNode.addFields(cTypeNode.getFields());
				         	classNode.addMethods(cTypeNode.getMethods());
				    
				         	isExtends = false;
				         	_localctx.astlist.add(classNode); 
				         	symTable.remove(nestingLevel--);
				         
				}
				}
				setState(140); 
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
					int offset = -2; 
				
			setState(191); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(187);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(143); match(VAR);
					setState(144); ((DeclistContext)_localctx).i = match(ID);
					setState(145); match(COLON);
					setState(146); ((DeclistContext)_localctx).h = hotype();
					setState(147); match(ASS);
					setState(148); ((DeclistContext)_localctx).e = exp();
						
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
					setState(151); match(FUN);
					setState(152); ((DeclistContext)_localctx).i = match(ID);
					setState(153); match(COLON);
					setState(154); ((DeclistContext)_localctx).t = type();
						
									FunNode f = new FunNode((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),((DeclistContext)_localctx).t.ast);
									_localctx.astlist.add(f);
									HashMap<String,STentry> hm = symTable.get(nestingLevel);
									STentry entry = new STentry(nestingLevel,offset--);
									offset--;
									if(hm.put((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null), entry) != null) {
										System.out.println("Fun id" + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null) + " at line " + (((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getLine():0) + " already declared.");
										System.exit(0);
									}
									nestingLevel++;  
									HashMap<String,STentry> hmn = new HashMap<String,STentry>();
									symTable.add(hmn);
								
					setState(156); match(LPAR);
						
									ArrayList<Node> parTypes = new ArrayList<Node>();
									int parOffset = 1;
								
					setState(173);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(158); ((DeclistContext)_localctx).i1 = match(ID);
						setState(159); match(COLON);
						setState(160); ((DeclistContext)_localctx).fty = hotype();
						 
												parTypes.add(((DeclistContext)_localctx).fty.ast);
												ParNode p1 = new ParNode((((DeclistContext)_localctx).i1!=null?((DeclistContext)_localctx).i1.getText():null),((DeclistContext)_localctx).fty.ast);
												f.addPar(p1);
												/* Offset doppio per tipi funzionali */
												if(((DeclistContext)_localctx).fty.ast instanceof ArrowTypeNode) parOffset++;
												if (hmn.put((((DeclistContext)_localctx).i1!=null?((DeclistContext)_localctx).i1.getText():null), new STentry(nestingLevel,((DeclistContext)_localctx).fty.ast,parOffset++)) != null) {
													System.out.println("Par ID: " + (((DeclistContext)_localctx).i1!=null?((DeclistContext)_localctx).i1.getText():null) + " at line " + (((DeclistContext)_localctx).i1!=null?((DeclistContext)_localctx).i1.getLine():0) + " already declared");
													System.exit(0);
												}
											
						setState(170);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(162); match(COMMA);
							setState(163); ((DeclistContext)_localctx).i2 = match(ID);
							setState(164); match(COLON);
							setState(165); ((DeclistContext)_localctx).ty = hotype();

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
							setState(172);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(175); match(RPAR);
					 
										entry.addType(new ArrowTypeNode(parTypes,((DeclistContext)_localctx).t.ast));
									
					setState(182);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(177); match(LET);
						setState(178); ((DeclistContext)_localctx).d = declist();
						f.addDec(((DeclistContext)_localctx).d.astlist);
						setState(180); match(IN);
						}
					}

					setState(184); ((DeclistContext)_localctx).e = exp();
						
										symTable.remove(nestingLevel--); 
										f.addBody(((DeclistContext)_localctx).e.ast);
									
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(189); match(SEMIC);
				}
				}
				setState(193); 
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
			setState(201);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(195); ((HotypeContext)_localctx).t = type();
				((HotypeContext)_localctx).ast =  ((HotypeContext)_localctx).t.ast;
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(198); ((HotypeContext)_localctx).a = arrow();
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
							
			setState(204); match(LPAR);
			setState(216);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(205); ((ArrowContext)_localctx).h = hotype();

						  			hotypeList.add(((ArrowContext)_localctx).h.ast);
						  		
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(207); match(COMMA);
					setState(208); ((ArrowContext)_localctx).h1 = hotype();

							  			hotypeList.add(((ArrowContext)_localctx).h1.ast);
							  		
					}
					}
					setState(215);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(218); match(RPAR);
			setState(219); match(ARROW);
			setState(220); ((ArrowContext)_localctx).t = type();

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
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		try {
			setState(229);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(223); match(INT);
				((TypeContext)_localctx).ast =  new IntTypeNode();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(225); match(BOOL);
				((TypeContext)_localctx).ast =  new BoolTypeNode();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(227); ((TypeContext)_localctx).id = match(ID);
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
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231); ((ExpContext)_localctx).t = term();
			((ExpContext)_localctx).ast =  ((ExpContext)_localctx).t.ast;
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0)) {
				{
				setState(245);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(233); match(PLUS);
					setState(234); ((ExpContext)_localctx).p = term();
					((ExpContext)_localctx).ast =  new PlusNode(_localctx.ast,((ExpContext)_localctx).p.ast);
					}
					break;
				case MINUS:
					{
					setState(237); match(MINUS);
					setState(238); ((ExpContext)_localctx).m = term();
					((ExpContext)_localctx).ast =  new MinusNode(_localctx.ast, ((ExpContext)_localctx).m.ast);
					}
					break;
				case OR:
					{
					setState(241); match(OR);
					setState(242); ((ExpContext)_localctx).o = term();
					((ExpContext)_localctx).ast =  new OrNode(_localctx.ast, ((ExpContext)_localctx).o.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(249);
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
			setState(250); ((TermContext)_localctx).f = factor();
			((TermContext)_localctx).ast =  ((TermContext)_localctx).f.ast;
			setState(266);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TIMES) | (1L << DIV) | (1L << AND))) != 0)) {
				{
				setState(264);
				switch (_input.LA(1)) {
				case TIMES:
					{
					setState(252); match(TIMES);
					setState(253); ((TermContext)_localctx).t = factor();
					((TermContext)_localctx).ast =  new TimesNode(_localctx.ast,((TermContext)_localctx).t.ast);
					}
					break;
				case DIV:
					{
					setState(256); match(DIV);
					setState(257); ((TermContext)_localctx).d = factor();
					((TermContext)_localctx).ast =  new DivNode(_localctx.ast,((TermContext)_localctx).d.ast);
					}
					break;
				case AND:
					{
					setState(260); match(AND);
					setState(261); ((TermContext)_localctx).a = factor();
					((TermContext)_localctx).ast =  new AndNode(_localctx.ast,((TermContext)_localctx).a.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(268);
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
			setState(269); ((FactorContext)_localctx).v = value();
			((FactorContext)_localctx).ast =  ((FactorContext)_localctx).v.ast;
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << LE) | (1L << EQ))) != 0)) {
				{
				setState(283);
				switch (_input.LA(1)) {
				case EQ:
					{
					setState(271); match(EQ);
					setState(272); ((FactorContext)_localctx).e = value();
					((FactorContext)_localctx).ast =  new EqualNode(_localctx.ast,((FactorContext)_localctx).e.ast);
					}
					break;
				case GE:
					{
					setState(275); match(GE);
					setState(276); ((FactorContext)_localctx).g = value();
					((FactorContext)_localctx).ast =  new GENode(_localctx.ast,((FactorContext)_localctx).g.ast);
					}
					break;
				case LE:
					{
					setState(279); match(LE);
					setState(280); ((FactorContext)_localctx).l = value();
					((FactorContext)_localctx).ast =  new LENode(_localctx.ast,((FactorContext)_localctx).l.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(287);
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
		public Token nid;
		public ExpContext e1;
		public ExpContext e2;
		public ExpContext e;
		public ExpContext e3;
		public Token i;
		public ExpContext a;
		public ExpContext a1;
		public Token mid;
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
			setState(384);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(288); ((ValueContext)_localctx).in = match(INTEGER);
				((ValueContext)_localctx).ast =  new IntNode(Integer.parseInt((((ValueContext)_localctx).in!=null?((ValueContext)_localctx).in.getText():null)));
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(290); match(TRUE);
				((ValueContext)_localctx).ast =  new BoolNode(true);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(292); match(FALSE);
				((ValueContext)_localctx).ast =  new BoolNode(false);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(294); match(NULL);
				((ValueContext)_localctx).ast =  new EmptyNode();
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 5);
				{
				setState(296); match(NEW);
				setState(297); ((ValueContext)_localctx).nid = match(ID);

							if(!classTable.keySet().contains((((ValueContext)_localctx).nid!=null?((ValueContext)_localctx).nid.getText():null))){
								System.out.println("Class id" + (((ValueContext)_localctx).nid!=null?((ValueContext)_localctx).nid.getText():null) + " at line " + (((ValueContext)_localctx).nid!=null?((ValueContext)_localctx).nid.getLine():0) + " not declared.");
								System.exit(0);
							}
							STentry entry = symTable.get(0).get((((ValueContext)_localctx).nid!=null?((ValueContext)_localctx).nid.getText():null));
							
							System.out.println("size "+ ((ClassTypeNode)entry.getType()).getFields().size());
							for(FieldNode field: ((ClassTypeNode)entry.getType()).getFields()){
							System.out.println(field.getID()+" \n");	
							}
						
							NewNode newNode = new NewNode((((ValueContext)_localctx).nid!=null?((ValueContext)_localctx).nid.getText():null),entry);
						
				setState(299); match(LPAR);
				setState(311);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(300); ((ValueContext)_localctx).e1 = exp();

									newNode.addArg(((ValueContext)_localctx).e1.ast);
								
					setState(308);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(302); match(COMMA);
						setState(303); ((ValueContext)_localctx).e2 = exp();

											newNode.addArg(((ValueContext)_localctx).e2.ast);
										
						}
						}
						setState(310);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}


							((ValueContext)_localctx).ast =  newNode;
							
						
				setState(314); match(RPAR);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 6);
				{
				setState(315); match(LPAR);
				setState(316); ((ValueContext)_localctx).e = exp();
				setState(317); match(RPAR);
				((ValueContext)_localctx).ast =  ((ValueContext)_localctx).e.ast;
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 7);
				{
				setState(320); match(IF);
				setState(321); ((ValueContext)_localctx).e1 = exp();
				setState(322); match(THEN);
				setState(323); match(CLPAR);
				setState(324); ((ValueContext)_localctx).e2 = exp();
				setState(325); match(CRPAR);
				setState(326); match(ELSE);
				setState(327); match(CLPAR);
				setState(328); ((ValueContext)_localctx).e3 = exp();
				setState(329); match(CRPAR);
				((ValueContext)_localctx).ast =  new IfNode(((ValueContext)_localctx).e1.ast,((ValueContext)_localctx).e2.ast,((ValueContext)_localctx).e3.ast);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 8);
				{
				setState(332); match(NOT);
				setState(333); match(LPAR);
				setState(334); ((ValueContext)_localctx).e = exp();
				((ValueContext)_localctx).ast =  new NotNode(((ValueContext)_localctx).e.ast);
				setState(336); match(RPAR);
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 9);
				{
				setState(338); match(PRINT);
				setState(339); match(LPAR);
				setState(340); ((ValueContext)_localctx).e = exp();
				setState(341); match(RPAR);
				((ValueContext)_localctx).ast =  new PrintNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 10);
				{
				setState(344); ((ValueContext)_localctx).i = match(ID);
					/* Cerco la dichiarazione dentro la symbol table e il livello di scope da scope corrente fino allo scope globale (level = 0)*/
							int j = nestingLevel;
							STentry entry = null;
							while(j >= 0 && entry == null) {
								entry = symTable.get(j--).get((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null));
							}
							
							if(entry==null) {
								System.out.println("Var id " + (((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null) + " at line " + (((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getLine():0) + " not declared.");
								System.exit(0);
							}
							/* Inserito il nestinglevel per verifiche sullo scope della variabile */
							((ValueContext)_localctx).ast =  new IdNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null), entry, nestingLevel); 
						
				setState(382);
				switch (_input.LA(1)) {
				case LPAR:
					{
					setState(346); match(LPAR);
					 
									ArrayList<Node> arglist = new ArrayList<Node>();
								
					setState(359);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(348); ((ValueContext)_localctx).a = exp();

											arglist.add(((ValueContext)_localctx).a.ast);
										
						setState(356);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(350); match(COMMA);
							setState(351); ((ValueContext)_localctx).a1 = exp();

													arglist.add(((ValueContext)_localctx).a1.ast);
												
							}
							}
							setState(358);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(361); match(RPAR);
					 
									((ValueContext)_localctx).ast =  new CallNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null), entry, arglist, nestingLevel);
								
					}
					break;
				case DOT:
					{
					setState(363); match(DOT);
					setState(364); ((ValueContext)_localctx).mid = match(ID);
					 
								  	if (!(entry.getType() instanceof RefTypeNode)) {
					               		System.out.println("id " + (((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null) + " is not a objects ");
					               		System.exit(0);
					             	}
								  	
								  	ArrayList<Node> argslist = new ArrayList<>();           
									RefTypeNode ref = (RefTypeNode)entry.getType();
					             	String objectID = ref.getID();
									if(!classTable.get(objectID).containsKey((((ValueContext)_localctx).mid!=null?((ValueContext)_localctx).mid.getText():null))){
										System.out.println("Method id" + (((ValueContext)_localctx).mid!=null?((ValueContext)_localctx).mid.getText():null) + " at line " + (((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getLine():0) + " not declared.");
										System.exit(0);
									}
									STentry classEntry = symTable.get(0).get(ref.getID());
									STentry methodEntry = classTable.get(ref.getID()).get((((ValueContext)_localctx).mid!=null?((ValueContext)_localctx).mid.getText():null));
												
								
					setState(366); match(LPAR);
					setState(378);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(367); ((ValueContext)_localctx).e = exp();
						 
									 		argslist.add(((ValueContext)_localctx).e.ast);
									 	
						setState(375);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(369); match(COMMA);
							setState(370); ((ValueContext)_localctx).e1 = exp();
							 
											 		argslist.add(((ValueContext)_localctx).e1.ast);
											 	
							}
							}
							setState(377);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(380); match(RPAR);

								     	ClassCallNode clCallNode = new ClassCallNode(ref.getID(), (((ValueContext)_localctx).mid!=null?((ValueContext)_localctx).mid.getText():null), classEntry, methodEntry, nestingLevel);
								     	clCallNode.addArgs(argslist);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3+\u0185\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2!\n\2\3\2\3\2\3\2\5\2&"+
		"\n\2\3\2\3\2\3\2\3\2\5\2,\n\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\5\39\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3"+
		"H\n\3\f\3\16\3K\13\3\3\3\3\3\5\3O\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3d\n\3\f\3\16\3g\13\3\3"+
		"\3\3\3\5\3k\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3y\n"+
		"\3\f\3\16\3|\13\3\3\3\3\3\5\3\u0080\n\3\3\3\3\3\3\3\3\3\7\3\u0086\n\3"+
		"\f\3\16\3\u0089\13\3\3\3\3\3\6\3\u008d\n\3\r\3\16\3\u008e\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\7\4\u00ab\n\4\f\4\16\4\u00ae\13\4\5\4\u00b0\n\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u00b9\n\4\3\4\3\4\3\4\5\4\u00be\n\4\3"+
		"\4\3\4\6\4\u00c2\n\4\r\4\16\4\u00c3\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00cc"+
		"\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00d6\n\6\f\6\16\6\u00d9\13\6"+
		"\5\6\u00db\n\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00e8\n"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00f8\n"+
		"\b\f\b\16\b\u00fb\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\7\t\u010b\n\t\f\t\16\t\u010e\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u011e\n\n\f\n\16\n\u0121\13\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\7\13\u0135\n\13\f\13\16\13\u0138\13\13\5\13\u013a\n\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0165"+
		"\n\13\f\13\16\13\u0168\13\13\5\13\u016a\n\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0178\n\13\f\13\16\13\u017b\13"+
		"\13\5\13\u017d\n\13\3\13\3\13\5\13\u0181\n\13\5\13\u0183\n\13\3\13\2\2"+
		"\f\2\4\6\b\n\f\16\20\22\24\2\2\u01aa\2\26\3\2\2\2\4\61\3\2\2\2\6\u0090"+
		"\3\2\2\2\b\u00cb\3\2\2\2\n\u00cd\3\2\2\2\f\u00e7\3\2\2\2\16\u00e9\3\2"+
		"\2\2\20\u00fc\3\2\2\2\22\u010f\3\2\2\2\24\u0182\3\2\2\2\26+\b\2\1\2\27"+
		"\30\5\16\b\2\30\31\b\2\1\2\31,\3\2\2\2\32%\7\34\2\2\33\34\5\4\3\2\34 "+
		"\b\2\1\2\35\36\5\6\4\2\36\37\b\2\1\2\37!\3\2\2\2 \35\3\2\2\2 !\3\2\2\2"+
		"!&\3\2\2\2\"#\5\6\4\2#$\b\2\1\2$&\3\2\2\2%\33\3\2\2\2%\"\3\2\2\2&\'\3"+
		"\2\2\2\'(\7\35\2\2()\5\16\b\2)*\b\2\1\2*,\3\2\2\2+\27\3\2\2\2+\32\3\2"+
		"\2\2,-\3\2\2\2-.\b\2\1\2./\7\13\2\2/\60\b\2\1\2\60\3\3\2\2\2\61\u008c"+
		"\b\3\1\2\62\63\7 \2\2\63\64\7(\2\2\648\b\3\1\2\65\66\7!\2\2\66\67\7(\2"+
		"\2\679\b\3\1\28\65\3\2\2\289\3\2\2\29:\3\2\2\2:;\b\3\1\2;<\b\3\1\2<N\7"+
		"\7\2\2=>\7(\2\2>?\7\f\2\2?@\5\f\7\2@I\b\3\1\2AB\7\r\2\2BC\7(\2\2CD\7\f"+
		"\2\2DE\5\f\7\2EF\b\3\1\2FH\3\2\2\2GA\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2"+
		"\2\2JL\3\2\2\2KI\3\2\2\2LM\b\3\1\2MO\3\2\2\2N=\3\2\2\2NO\3\2\2\2OP\3\2"+
		"\2\2PQ\7\b\2\2QR\7\t\2\2R\u0087\b\3\1\2ST\7\37\2\2TU\7(\2\2UV\7\f\2\2"+
		"VW\5\f\7\2WX\b\3\1\2Xj\7\7\2\2YZ\7(\2\2Z[\7\f\2\2[\\\5\b\5\2\\e\b\3\1"+
		"\2]^\7\r\2\2^_\7(\2\2_`\7\f\2\2`a\5\b\5\2ab\b\3\1\2bd\3\2\2\2c]\3\2\2"+
		"\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2hi\b\3\1\2ik\3\2\2"+
		"\2jY\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\7\b\2\2m\177\b\3\1\2nz\7\34\2\2op\7"+
		"\36\2\2pq\7(\2\2qr\7\f\2\2rs\5\f\7\2st\7\25\2\2tu\5\16\b\2uv\b\3\1\2v"+
		"w\7\13\2\2wy\3\2\2\2xo\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{}\3\2\2\2"+
		"|z\3\2\2\2}~\b\3\1\2~\u0080\7\35\2\2\177n\3\2\2\2\177\u0080\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0082\5\16\b\2\u0082\u0083\b\3\1\2\u0083\u0084\7"+
		"\13\2\2\u0084\u0086\3\2\2\2\u0085S\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085"+
		"\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\3\2\2\2\u0089\u0087\3\2\2\2\u008a"+
		"\u008b\7\n\2\2\u008b\u008d\b\3\1\2\u008c\62\3\2\2\2\u008d\u008e\3\2\2"+
		"\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\5\3\2\2\2\u0090\u00c1"+
		"\b\4\1\2\u0091\u0092\7\36\2\2\u0092\u0093\7(\2\2\u0093\u0094\7\f\2\2\u0094"+
		"\u0095\5\b\5\2\u0095\u0096\7\25\2\2\u0096\u0097\5\16\b\2\u0097\u0098\b"+
		"\4\1\2\u0098\u00be\3\2\2\2\u0099\u009a\7\37\2\2\u009a\u009b\7(\2\2\u009b"+
		"\u009c\7\f\2\2\u009c\u009d\5\f\7\2\u009d\u009e\b\4\1\2\u009e\u009f\7\7"+
		"\2\2\u009f\u00af\b\4\1\2\u00a0\u00a1\7(\2\2\u00a1\u00a2\7\f\2\2\u00a2"+
		"\u00a3\5\b\5\2\u00a3\u00ac\b\4\1\2\u00a4\u00a5\7\r\2\2\u00a5\u00a6\7("+
		"\2\2\u00a6\u00a7\7\f\2\2\u00a7\u00a8\5\b\5\2\u00a8\u00a9\b\4\1\2\u00a9"+
		"\u00ab\3\2\2\2\u00aa\u00a4\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2"+
		"\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af"+
		"\u00a0\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\7\b"+
		"\2\2\u00b2\u00b8\b\4\1\2\u00b3\u00b4\7\34\2\2\u00b4\u00b5\5\6\4\2\u00b5"+
		"\u00b6\b\4\1\2\u00b6\u00b7\7\35\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b3\3"+
		"\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\5\16\b\2\u00bb"+
		"\u00bc\b\4\1\2\u00bc\u00be\3\2\2\2\u00bd\u0091\3\2\2\2\u00bd\u0099\3\2"+
		"\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\7\13\2\2\u00c0\u00c2\3\2\2\2\u00c1"+
		"\u00bd\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2"+
		"\2\2\u00c4\7\3\2\2\2\u00c5\u00c6\5\f\7\2\u00c6\u00c7\b\5\1\2\u00c7\u00cc"+
		"\3\2\2\2\u00c8\u00c9\5\n\6\2\u00c9\u00ca\b\5\1\2\u00ca\u00cc\3\2\2\2\u00cb"+
		"\u00c5\3\2\2\2\u00cb\u00c8\3\2\2\2\u00cc\t\3\2\2\2\u00cd\u00ce\b\6\1\2"+
		"\u00ce\u00da\7\7\2\2\u00cf\u00d0\5\b\5\2\u00d0\u00d7\b\6\1\2\u00d1\u00d2"+
		"\7\r\2\2\u00d2\u00d3\5\b\5\2\u00d3\u00d4\b\6\1\2\u00d4\u00d6\3\2\2\2\u00d5"+
		"\u00d1\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2"+
		"\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00cf\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\7\b\2\2\u00dd\u00de\7&"+
		"\2\2\u00de\u00df\5\f\7\2\u00df\u00e0\b\6\1\2\u00e0\13\3\2\2\2\u00e1\u00e2"+
		"\7$\2\2\u00e2\u00e8\b\7\1\2\u00e3\u00e4\7%\2\2\u00e4\u00e8\b\7\1\2\u00e5"+
		"\u00e6\7(\2\2\u00e6\u00e8\b\7\1\2\u00e7\u00e1\3\2\2\2\u00e7\u00e3\3\2"+
		"\2\2\u00e7\u00e5\3\2\2\2\u00e8\r\3\2\2\2\u00e9\u00ea\5\20\t\2\u00ea\u00f9"+
		"\b\b\1\2\u00eb\u00ec\7\3\2\2\u00ec\u00ed\5\20\t\2\u00ed\u00ee\b\b\1\2"+
		"\u00ee\u00f8\3\2\2\2\u00ef\u00f0\7\4\2\2\u00f0\u00f1\5\20\t\2\u00f1\u00f2"+
		"\b\b\1\2\u00f2\u00f8\3\2\2\2\u00f3\u00f4\7\17\2\2\u00f4\u00f5\5\20\t\2"+
		"\u00f5\u00f6\b\b\1\2\u00f6\u00f8\3\2\2\2\u00f7\u00eb\3\2\2\2\u00f7\u00ef"+
		"\3\2\2\2\u00f7\u00f3\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9"+
		"\u00fa\3\2\2\2\u00fa\17\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u00fd\5\22\n"+
		"\2\u00fd\u010c\b\t\1\2\u00fe\u00ff\7\5\2\2\u00ff\u0100\5\22\n\2\u0100"+
		"\u0101\b\t\1\2\u0101\u010b\3\2\2\2\u0102\u0103\7\6\2\2\u0103\u0104\5\22"+
		"\n\2\u0104\u0105\b\t\1\2\u0105\u010b\3\2\2\2\u0106\u0107\7\20\2\2\u0107"+
		"\u0108\5\22\n\2\u0108\u0109\b\t\1\2\u0109\u010b\3\2\2\2\u010a\u00fe\3"+
		"\2\2\2\u010a\u0102\3\2\2\2\u010a\u0106\3\2\2\2\u010b\u010e\3\2\2\2\u010c"+
		"\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\21\3\2\2\2\u010e\u010c\3\2\2"+
		"\2\u010f\u0110\5\24\13\2\u0110\u011f\b\n\1\2\u0111\u0112\7\24\2\2\u0112"+
		"\u0113\5\24\13\2\u0113\u0114\b\n\1\2\u0114\u011e\3\2\2\2\u0115\u0116\7"+
		"\22\2\2\u0116\u0117\5\24\13\2\u0117\u0118\b\n\1\2\u0118\u011e\3\2\2\2"+
		"\u0119\u011a\7\23\2\2\u011a\u011b\5\24\13\2\u011b\u011c\b\n\1\2\u011c"+
		"\u011e\3\2\2\2\u011d\u0111\3\2\2\2\u011d\u0115\3\2\2\2\u011d\u0119\3\2"+
		"\2\2\u011e\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120"+
		"\23\3\2\2\2\u0121\u011f\3\2\2\2\u0122\u0123\7\'\2\2\u0123\u0183\b\13\1"+
		"\2\u0124\u0125\7\26\2\2\u0125\u0183\b\13\1\2\u0126\u0127\7\27\2\2\u0127"+
		"\u0183\b\13\1\2\u0128\u0129\7#\2\2\u0129\u0183\b\13\1\2\u012a\u012b\7"+
		"\"\2\2\u012b\u012c\7(\2\2\u012c\u012d\b\13\1\2\u012d\u0139\7\7\2\2\u012e"+
		"\u012f\5\16\b\2\u012f\u0136\b\13\1\2\u0130\u0131\7\r\2\2\u0131\u0132\5"+
		"\16\b\2\u0132\u0133\b\13\1\2\u0133\u0135\3\2\2\2\u0134\u0130\3\2\2\2\u0135"+
		"\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u013a\3\2"+
		"\2\2\u0138\u0136\3\2\2\2\u0139\u012e\3\2\2\2\u0139\u013a\3\2\2\2\u013a"+
		"\u013b\3\2\2\2\u013b\u013c\b\13\1\2\u013c\u0183\7\b\2\2\u013d\u013e\7"+
		"\7\2\2\u013e\u013f\5\16\b\2\u013f\u0140\7\b\2\2\u0140\u0141\b\13\1\2\u0141"+
		"\u0183\3\2\2\2\u0142\u0143\7\30\2\2\u0143\u0144\5\16\b\2\u0144\u0145\7"+
		"\31\2\2\u0145\u0146\7\t\2\2\u0146\u0147\5\16\b\2\u0147\u0148\7\n\2\2\u0148"+
		"\u0149\7\32\2\2\u0149\u014a\7\t\2\2\u014a\u014b\5\16\b\2\u014b\u014c\7"+
		"\n\2\2\u014c\u014d\b\13\1\2\u014d\u0183\3\2\2\2\u014e\u014f\7\21\2\2\u014f"+
		"\u0150\7\7\2\2\u0150\u0151\5\16\b\2\u0151\u0152\b\13\1\2\u0152\u0153\7"+
		"\b\2\2\u0153\u0183\3\2\2\2\u0154\u0155\7\33\2\2\u0155\u0156\7\7\2\2\u0156"+
		"\u0157\5\16\b\2\u0157\u0158\7\b\2\2\u0158\u0159\b\13\1\2\u0159\u0183\3"+
		"\2\2\2\u015a\u015b\7(\2\2\u015b\u0180\b\13\1\2\u015c\u015d\7\7\2\2\u015d"+
		"\u0169\b\13\1\2\u015e\u015f\5\16\b\2\u015f\u0166\b\13\1\2\u0160\u0161"+
		"\7\r\2\2\u0161\u0162\5\16\b\2\u0162\u0163\b\13\1\2\u0163\u0165\3\2\2\2"+
		"\u0164\u0160\3\2\2\2\u0165\u0168\3\2\2\2\u0166\u0164\3\2\2\2\u0166\u0167"+
		"\3\2\2\2\u0167\u016a\3\2\2\2\u0168\u0166\3\2\2\2\u0169\u015e\3\2\2\2\u0169"+
		"\u016a\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016c\7\b\2\2\u016c\u0181\b\13"+
		"\1\2\u016d\u016e\7\16\2\2\u016e\u016f\7(\2\2\u016f\u0170\b\13\1\2\u0170"+
		"\u017c\7\7\2\2\u0171\u0172\5\16\b\2\u0172\u0179\b\13\1\2\u0173\u0174\7"+
		"\r\2\2\u0174\u0175\5\16\b\2\u0175\u0176\b\13\1\2\u0176\u0178\3\2\2\2\u0177"+
		"\u0173\3\2\2\2\u0178\u017b\3\2\2\2\u0179\u0177\3\2\2\2\u0179\u017a\3\2"+
		"\2\2\u017a\u017d\3\2\2\2\u017b\u0179\3\2\2\2\u017c\u0171\3\2\2\2\u017c"+
		"\u017d\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u017f\7\b\2\2\u017f\u0181\b\13"+
		"\1\2\u0180\u015c\3\2\2\2\u0180\u016d\3\2\2\2\u0180\u0181\3\2\2\2\u0181"+
		"\u0183\3\2\2\2\u0182\u0122\3\2\2\2\u0182\u0124\3\2\2\2\u0182\u0126\3\2"+
		"\2\2\u0182\u0128\3\2\2\2\u0182\u012a\3\2\2\2\u0182\u013d\3\2\2\2\u0182"+
		"\u0142\3\2\2\2\u0182\u014e\3\2\2\2\u0182\u0154\3\2\2\2\u0182\u015a\3\2"+
		"\2\2\u0183\25\3\2\2\2% %+8INejz\177\u0087\u008e\u00ac\u00af\u00b8\u00bd"+
		"\u00c3\u00cb\u00d7\u00da\u00e7\u00f7\u00f9\u010a\u010c\u011d\u011f\u0136"+
		"\u0139\u0166\u0169\u0179\u017c\u0180\u0182";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}