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

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FOOLParser}.
 */
public interface FOOLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FOOLParser#cllist}.
	 * @param ctx the parse tree
	 */
	void enterCllist(@NotNull FOOLParser.CllistContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#cllist}.
	 * @param ctx the parse tree
	 */
	void exitCllist(@NotNull FOOLParser.CllistContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#arrow}.
	 * @param ctx the parse tree
	 */
	void enterArrow(@NotNull FOOLParser.ArrowContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#arrow}.
	 * @param ctx the parse tree
	 */
	void exitArrow(@NotNull FOOLParser.ArrowContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull FOOLParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull FOOLParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#hotype}.
	 * @param ctx the parse tree
	 */
	void enterHotype(@NotNull FOOLParser.HotypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#hotype}.
	 * @param ctx the parse tree
	 */
	void exitHotype(@NotNull FOOLParser.HotypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull FOOLParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull FOOLParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(@NotNull FOOLParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(@NotNull FOOLParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(@NotNull FOOLParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(@NotNull FOOLParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull FOOLParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull FOOLParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull FOOLParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull FOOLParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#declist}.
	 * @param ctx the parse tree
	 */
	void enterDeclist(@NotNull FOOLParser.DeclistContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#declist}.
	 * @param ctx the parse tree
	 */
	void exitDeclist(@NotNull FOOLParser.DeclistContext ctx);
}