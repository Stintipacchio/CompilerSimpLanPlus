package ast;

import java.util.ArrayList;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class AndOrNode implements Node {
	private Node left;
	private Node right;

	private String Op;
  
	public AndOrNode(Node _left, Node _right, String _op) {
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
		  if ((left.typeCheck() instanceof BoolType) && (right.typeCheck() instanceof BoolType) ) 
			  return new BoolType() ;
		  else {
			  throw new Error("Type Error: Non boolean in an AND/OR compare") ;
			  //return new ErrorType() ;
		  }
	}  
  
	public String codeGeneration() {     //cambia in base a ses è and o or
		String code = "";

		if (Op.equals("&&")) {
			String ltrue = SimpLanlib.freshLabel();
			String lend = SimpLanlib.freshLabel();
			code = left.codeGeneration()
					+ "push 0 \n"
					+ "popr T1 \n"
					+ "beq A0 T1 "+lend+"\n"
					+ right.codeGeneration()
					+ lend+ ":\n";
		} else {
			String ltrue = SimpLanlib.freshLabel();
			String lend = SimpLanlib.freshLabel();
			code = left.codeGeneration()
					+ "pushr A0 \n"
					+ right.codeGeneration()
					+ "popr T1 \n"
					+ "add A0 T1 \n"
					+ "popr A0 \n"
					+ "push 1 \n"
					+ "popr T1 \n"
					+ "bgte A0 T1 "+ltrue+"\n"
					+ "push 0 \n"
					+ "popr A0 \n"
					+ "b "+lend+"\n"
					+ ltrue+ ":\n"
					+ "push 1 \n"
					+ "popr A0 \n"
					+ lend+ ":\n";
		}

		return code;
	}
   
	public String toPrint(String s) {
	    return s+"And\n" + left.toPrint(s+"  ") + right.toPrint(s+"  ") ; 
	}
	  
}  