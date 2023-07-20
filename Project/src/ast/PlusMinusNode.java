package ast;

import java.util.ArrayList;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class PlusMinusNode implements Node {
	private Node left;
	private Node right;
	private String Op;
  
	public PlusMinusNode(Node _left, Node _right, String _op) {
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
		  if ((left.typeCheck() instanceof IntType) && (right.typeCheck() instanceof IntType) ) 
			  return new IntType() ;
		  else {
			  System.out.println("Type Error: Non integers in addition") ;
			  return new ErrorType() ;
		  }
	}  
	  
  
	public String codeGeneration() {
		return left.codeGeneration()
			+ "pushr A0 \n"
			+ right.codeGeneration()
			+ "popr T1 \n"
			+ (Op.equals("+") ? "add T1 A0 \n" : "sub T1 A0 \n")
			+ "popr A0 \n" ;
}
   
	public String toPrint(String s) {
	    return s+"Plus\n" + left.toPrint(s+"  ") + right.toPrint(s+"  ") ; 
	}
	  
}  