// Generated from C:/Users/lucag/OneDrive/Documenti/GitHub/CompilerSimpLanPlus/Project/src\SimpLanPlus.g4 by ANTLR 4.12.0
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpLanPlusParser}.
 */
public interface SimpLanPlusListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterSingleExp(SimpLanPlusParser.SingleExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitSingleExp(SimpLanPlusParser.SingleExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multipleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterMultipleExp(SimpLanPlusParser.MultipleExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multipleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitMultipleExp(SimpLanPlusParser.MultipleExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(SimpLanPlusParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(SimpLanPlusParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterFunDec(SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitFunDec(SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(SimpLanPlusParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(SimpLanPlusParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(SimpLanPlusParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(SimpLanPlusParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(SimpLanPlusParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(SimpLanPlusParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmAsg}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterStmAsg(SimpLanPlusParser.StmAsgContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmAsg}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitStmAsg(SimpLanPlusParser.StmAsgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmCallFun}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterStmCallFun(SimpLanPlusParser.StmCallFunContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmCallFun}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitStmCallFun(SimpLanPlusParser.StmCallFunContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmIf}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterStmIf(SimpLanPlusParser.StmIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmIf}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitStmIf(SimpLanPlusParser.StmIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#stmThenBranch}.
	 * @param ctx the parse tree
	 */
	void enterStmThenBranch(SimpLanPlusParser.StmThenBranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#stmThenBranch}.
	 * @param ctx the parse tree
	 */
	void exitStmThenBranch(SimpLanPlusParser.StmThenBranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#stmElseBranch}.
	 * @param ctx the parse tree
	 */
	void enterStmElseBranch(SimpLanPlusParser.StmElseBranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#stmElseBranch}.
	 * @param ctx the parse tree
	 */
	void exitStmElseBranch(SimpLanPlusParser.StmElseBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expNotId}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpNotId(SimpLanPlusParser.ExpNotIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expNotId}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpNotId(SimpLanPlusParser.ExpNotIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expBracket}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpBracket(SimpLanPlusParser.ExpBracketContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expBracket}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpBracket(SimpLanPlusParser.ExpBracketContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expMulDiv}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpMulDiv(SimpLanPlusParser.ExpMulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expMulDiv}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpMulDiv(SimpLanPlusParser.ExpMulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expAndOr}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpAndOr(SimpLanPlusParser.ExpAndOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expAndOr}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpAndOr(SimpLanPlusParser.ExpAndOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expTrue}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpTrue(SimpLanPlusParser.ExpTrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expTrue}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpTrue(SimpLanPlusParser.ExpTrueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expCallFun}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpCallFun(SimpLanPlusParser.ExpCallFunContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expCallFun}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpCallFun(SimpLanPlusParser.ExpCallFunContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expInt}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpInt(SimpLanPlusParser.ExpIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expInt}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpInt(SimpLanPlusParser.ExpIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expPlusMinus}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpPlusMinus(SimpLanPlusParser.ExpPlusMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expPlusMinus}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpPlusMinus(SimpLanPlusParser.ExpPlusMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expIf}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpIf(SimpLanPlusParser.ExpIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expIf}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpIf(SimpLanPlusParser.ExpIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expReop}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpReop(SimpLanPlusParser.ExpReopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expReop}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpReop(SimpLanPlusParser.ExpReopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expFalse}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpFalse(SimpLanPlusParser.ExpFalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expFalse}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpFalse(SimpLanPlusParser.ExpFalseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expId}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpId(SimpLanPlusParser.ExpIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expId}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpId(SimpLanPlusParser.ExpIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#expThenBranch}.
	 * @param ctx the parse tree
	 */
	void enterExpThenBranch(SimpLanPlusParser.ExpThenBranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#expThenBranch}.
	 * @param ctx the parse tree
	 */
	void exitExpThenBranch(SimpLanPlusParser.ExpThenBranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#expElseBranch}.
	 * @param ctx the parse tree
	 */
	void enterExpElseBranch(SimpLanPlusParser.ExpElseBranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#expElseBranch}.
	 * @param ctx the parse tree
	 */
	void exitExpElseBranch(SimpLanPlusParser.ExpElseBranchContext ctx);
}