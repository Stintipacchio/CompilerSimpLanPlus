package ast;

import parser.SimpLanBaseVisitor;
import parser.SimpLanParser.*;
import antlr.SimpLanPlusParser;
import java.util.ArrayList;

public class SimpLanPlusVisitorImpl extends antlr.SimpLanPlusBaseVisitor<Node> {
	

	public Node visitSingleExp(SimpLanPlusParser.SingleExpContext ctx) {
		
		//simply return the result of the visit to the inner exp
		return new ProgNode(visit(ctx.exp()), "caso1");
		
	}

	public Node visitMultipleExp(SimpLanPlusParser.MultipleExpContext ctx) {

		ArrayList<Node> decList = new ArrayList<Node>();
		ArrayList<Node> stmList = new ArrayList<Node>();
		Node expNode = null;


		for (SimpLanPlusParser.DecContext dc : ctx.dec())
			decList.add(visit(dc));

		for (SimpLanPlusParser.StmContext sc : ctx.stm())
			stmList.add(visit(sc));

		if(ctx.exp() != null)
			expNode = visit(ctx.exp());


		return new ProgNode(expNode, "caso2", decList, stmList);
	}
	
	public Node visitVarDec(SimpLanPlusParser.VarDecContext ctx) {
		//visit the type
		Node typeNode = visit(ctx.type());
		
		//visit the exp
		//Node expNode = visit(ctx.exp()); //la grammatica non permette più di inizializzare la variabile durante la dichiarazione
		
		//build the varNode
		return new DecNode(ctx.ID().getText(), typeNode);
	}
	
	public Node visitFunDec(SimpLanPlusParser.FunDecContext ctx) {
		
		//initialize @res with the visits to the type and its ID
		
		
		//add argument declarations
		//we are getting a shortcut here by constructing directly the ParNode
		//this could be done differently by visiting instead the VardecContext
		ArrayList<ParNode> _param = new ArrayList<ParNode>() ;
				
		for (SimpLanPlusParser.ParamContext vc : ctx.param())
			_param.add( new ParNode(vc.ID().getText(), (Type) visit( vc.type() )) );
		
		//add body
		//create a list for the nested declarations
		ArrayList<Node> innerDec = new ArrayList<Node>();
		ArrayList<Node> innerStatements = new ArrayList<Node>(); //oltre alla lista di dichiarazioni aggihngiamo la linsta di statement (stm)*
		Node innerExp = null;

		if(ctx.body().dec() != null)
			for(SimpLanPlusParser.DecContext dc : ctx.body().dec())
				innerDec.add(visit(dc));


		if(ctx.body().stm() != null)
			for (SimpLanPlusParser.StmContext sm: ctx.body().stm()){
				innerStatements.add(visit(sm));
			}

		if (ctx.body().exp() != null) {
			innerExp = visit(ctx.body().exp());
		} else {
			innerExp = null;
		}
		System.out.println(ctx.ID().getText() + "AAAAAAAAAAAAAAAAAAAAA");
		return new FunNode(ctx.ID().getText(), (Type) visit(ctx.type()), _param, innerDec, innerStatements, innerExp);
	}
	
	public Node visitType(SimpLanPlusParser.TypeContext ctx) {
		if(ctx.getText().equals("int"))
			return new IntType();
		else if(ctx.getText().equals("bool"))
			return new BoolType();
		else return new VoidType();

	}
	//da capire forse si puo togliere il controllo sulla simple expression
	public Node visitExpPlusMinus(SimpLanPlusParser.ExpPlusMinusContext ctx) {
		if(ctx.e2 == null){ //it is a simple expression
			return visit( ctx.e1 );
		} else { //it is a binary expression: visit left and right
			return new PlusMinusNode(visit(ctx.e1), visit(ctx.e2), ctx.op.getText());
		}
	}
	
	public Node visitExpMulDiv(SimpLanPlusParser.ExpMulDivContext ctx) {
		if(ctx.e2 == null){ //it is a simple expression
			return visit( ctx.e1 );
		} else
			//it is a binary expression: visit left and right
			return new MulDivNode(visit(ctx.e1), visit(ctx.e2), ctx.op.getText());
	}

	public Node visitExpAndOr(SimpLanPlusParser.ExpAndOrContext ctx) {					//AndOr     fatta

			return new AndOrNode(visit(ctx.e1), visit(ctx.e2));
	}

	public Node visitExpNotId(SimpLanPlusParser.ExpNotIdContext ctx) {				//Not
		return new NotNode(visit(ctx.exp()));
	}


	public Node visitExpReop(SimpLanPlusParser.ExpReopContext ctx) {
		if(ctx.e2 == null){ //it is a simple expression
			return visit( ctx.e1 );
		}else{ //it is a binary expression, you should visit left and right
			return new RelationalOperatorNode(visit(ctx.e1), visit(ctx.e2), ctx.op.getText());
		}
	}

	public Node visitExpInt(SimpLanPlusParser.ExpIntContext ctx) {
		// notice that this method is not actually a rule but a named production #intVal		
		//there is no need to perform a check here, the lexer ensures this text is an int
		return new IntNode(Integer.parseInt(ctx.INTEGER().getText()));
	}

	public Node visitExpTrue(SimpLanPlusParser.ExpTrueContext ctx) {

		return new BoolNode(Boolean.parseBoolean("true"));
	}
	public Node visitExpFalse(SimpLanPlusParser.ExpFalseContext ctx) {

		return new BoolNode(Boolean.parseBoolean("false"));
	}

	public Node visitExpBracket(SimpLanPlusParser.ExpBracketContext ctx) {
		return visit (ctx.exp());
	}
	
	public Node visitExpIf(SimpLanPlusParser.ExpIfContext ctx) {
		//visit the conditional, then the then branch, and then the else branch
		//notice once again the need of named terminals in the rule, this is because
		//we need to point to the right expression among the 3 possible ones in the rule
		
		Node condExp = visit (ctx.condition);
		
		Node thenExp = visit (ctx.thenBranch);

		Node elseExp = visit (ctx.elseBranch);

		return new IfNode(condExp, thenExp, elseExp);
	}
	public Node visitExpThenBranch(SimpLanPlusParser.ExpThenBranchContext ctx) {			//Blocco rami dell'ifExp
		ArrayList<Node> stmList = new ArrayList<Node>();
		Node expNode = null;

		for (SimpLanPlusParser.StmContext sc : ctx.stm())
			stmList.add(visit(sc));

		expNode = visit(ctx.exp());


		return new SeqstmNode(stmList, "exp", expNode);
	}
	public Node visitExpElseBranch(SimpLanPlusParser.ExpElseBranchContext ctx) {			//Blocco rami dell'ifExp
		ArrayList<Node> stmList = new ArrayList<Node>();
		Node expNode = null;

		for (SimpLanPlusParser.StmContext sc : ctx.stm())
			stmList.add(visit(sc));

		expNode = visit(ctx.exp());


		return new SeqstmNode(stmList, "exp", expNode);
	}



	public Node visitStmIf(SimpLanPlusParser.StmIfContext ctx) {
		//visit the conditional, then the then branch, and then the else branch
		//notice once again the need of named terminals in the rule, this is because
		//we need to point to the right expression among the 3 possible ones in the rule

		Node condExp = visit (ctx.exp());

		Node thenExp = visit (ctx.thenBranch);

		Node elseExp = null;
		if(ctx.stmElseBranch()!=null)
			elseExp = visit (ctx.elseBranch);

		return new IfNode(condExp, thenExp, elseExp);
	}

	public Node visitStmThenBranch(SimpLanPlusParser.StmThenBranchContext ctx) {					//Blocco rami dell'ifStm
		ArrayList<Node> stmList = new ArrayList<Node>();
		for (SimpLanPlusParser.StmContext sc : ctx.stm())
			stmList.add(visit(sc));

		return new SeqstmNode(stmList, "stm");
	}
	public Node visitStmElseBranch(SimpLanPlusParser.StmElseBranchContext ctx) {					//Blocco rami dell'ifStm
		ArrayList<Node> stmList = new ArrayList<Node>();
		for (SimpLanPlusParser.StmContext sc : ctx.stm())
			stmList.add(visit(sc));

		return new SeqstmNode(stmList, "stm");
	}




	public Node visitExpId(SimpLanPlusParser.ExpIdContext ctx) {
		//this corresponds to a variable access
		return new IdNode(ctx.ID().getText());
	}


	public Node visitStmAsg(SimpLanPlusParser.StmAsgContext ctx) {			//VarStm: assegnamento    da modificare file varstmnode
		//visit the exp
		Node expNode = visit(ctx.exp());


		return new VarStmNode(ctx.ID().getText(), expNode);
	}
	
	public Node visitExpCallFun(SimpLanPlusParser.ExpCallFunContext ctx) {
		//this corresponds to a function invocation
		//declare the result
		Node res;
		
		//get the invocation arguments
		ArrayList<Node> args = new ArrayList<Node>();
		
		for (SimpLanPlusParser.ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		// this is ad-hoc for this project...
		if(ctx.ID().getText().equals("print"))
			res = new PrintNode(args.get(0));
		
		else{
			//instantiate the invocation
			res = new CallNode(ctx.ID().getText(), args);}
		
		return res;
	}

	public Node visitStmCallFun(SimpLanPlusParser.StmCallFunContext ctx) {
		//this corresponds to a function invocation
		//declare the result
		Node res;

		//get the invocation arguments
		ArrayList<Node> args = new ArrayList<Node>();

		for (SimpLanPlusParser.ExpContext exp : ctx.exp())
			args.add(visit(exp));

		// this is ad-hoc for this project...
		if(ctx.ID().getText().equals("print"))
			res = new PrintNode(args.get(0));

		else{
			//instantiate the invocation
			res = new CallNode(ctx.ID().getText(), args);}

		return res;
	}
	
}
