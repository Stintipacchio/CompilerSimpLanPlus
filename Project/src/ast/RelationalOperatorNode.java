package ast;

import java.util.ArrayList;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class RelationalOperatorNode implements Node {
	private Node left ;
	private Node right ;
	private String Op;

	public RelationalOperatorNode (Node _left, Node _right, String _op) {
		left = _left ;
		right = _right ;
		Op = _op;
	}

	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

		errors.addAll(left.checkSemantics(ST, _nesting));
		errors.addAll(right.checkSemantics(ST, _nesting));
		  
		return errors;
	}
	  
	public Type typeCheck() {
		Type tl = left.typeCheck() ;
		Type tr = right.typeCheck();
		if (tl.getClass().equals(tr.getClass())) 
			  return new BoolType() ;
		else {
			throw new Error("Type Error: Different types in equality") ;
			  //return new ErrorType() ;
		  }
	}  

	public String codeGeneration() {
		String ltrue = SimpLanlib.freshLabel();
		String lend = SimpLanlib.freshLabel();

		String command = switch (this.Op) {
			case ">" -> "bgt T1 A0 "+ltrue+"\n";
			case "<" -> "blt T1 A0 "+ltrue+"\n";
			case ">=" -> "bgte T1 A0 "+ltrue+"\n";
			case "<=" -> "bleq T1 A0 "+ltrue+"\n";
			case "==" -> "beq T1 A0 "+ltrue+"\n";
			default -> "";
		};

		return this.left.codeGeneration()
				+ "pushr A0 \n"
				+ this.right.codeGeneration()
				+ "popr T1 \n"
				+ command
				+ "storei A0 0 \n"
				+ "b " + lend + "\n"
				+ ltrue + ":\n"
				+ "storei A0 1 \n"
				+ lend + ":\n";

	}

	public String toPrint(String s) {
		return s+"Equal\n" + left.toPrint(s+"  ") + right.toPrint(s+"  ") ; 
	}
}  