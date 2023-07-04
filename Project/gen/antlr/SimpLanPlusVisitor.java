// Generated from C:/Users/stint/Desktop/CompilerSimpLanPlus/Project/src\SimpLanPlus.g4 by ANTLR 4.12.0
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimpLanPlusParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SimpLanPlusVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleExp(SimpLanPlusParser.SingleExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multipleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleExp(SimpLanPlusParser.MultipleExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(SimpLanPlusParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDec(SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(SimpLanPlusParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(SimpLanPlusParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(SimpLanPlusParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmAsg}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmAsg(SimpLanPlusParser.StmAsgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmCallFun}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmCallFun(SimpLanPlusParser.StmCallFunContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmIf}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmIf(SimpLanPlusParser.StmIfContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#stmThenBranch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmThenBranch(SimpLanPlusParser.StmThenBranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#stmElseBranch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmElseBranch(SimpLanPlusParser.StmElseBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expNotId}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpNotId(SimpLanPlusParser.ExpNotIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expBracket}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpBracket(SimpLanPlusParser.ExpBracketContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expMulDiv}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpMulDiv(SimpLanPlusParser.ExpMulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expAndOr}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpAndOr(SimpLanPlusParser.ExpAndOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expTrue}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpTrue(SimpLanPlusParser.ExpTrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expCallFun}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpCallFun(SimpLanPlusParser.ExpCallFunContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expInt}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpInt(SimpLanPlusParser.ExpIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expPlusMinus}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpPlusMinus(SimpLanPlusParser.ExpPlusMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expIf}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpIf(SimpLanPlusParser.ExpIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expReop}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpReop(SimpLanPlusParser.ExpReopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expFalse}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpFalse(SimpLanPlusParser.ExpFalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expId}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpId(SimpLanPlusParser.ExpIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#expThenBranch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpThenBranch(SimpLanPlusParser.ExpThenBranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#expElseBranch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpElseBranch(SimpLanPlusParser.ExpElseBranchContext ctx);
}