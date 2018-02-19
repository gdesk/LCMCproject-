// Generated from FOOLold.g4 by ANTLR 4.4

import java.util.ArrayList;
import java.util.HashMap;
import ast.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FOOLoldParser}.
 */
public interface FOOLoldListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FOOLoldParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull FOOLoldParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLoldParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull FOOLoldParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLoldParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull FOOLoldParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLoldParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull FOOLoldParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLoldParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(@NotNull FOOLoldParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLoldParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(@NotNull FOOLoldParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLoldParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(@NotNull FOOLoldParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLoldParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(@NotNull FOOLoldParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLoldParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull FOOLoldParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLoldParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull FOOLoldParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLoldParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull FOOLoldParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLoldParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull FOOLoldParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLoldParser#declist}.
	 * @param ctx the parse tree
	 */
	void enterDeclist(@NotNull FOOLoldParser.DeclistContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLoldParser#declist}.
	 * @param ctx the parse tree
	 */
	void exitDeclist(@NotNull FOOLoldParser.DeclistContext ctx);
}