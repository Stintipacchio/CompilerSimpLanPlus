package ast;

import java.util.ArrayList;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class IfNode implements Node {
	private Node guard;
	private Node thenbranch;
	private Node elsebranch;
	private String tipo_if;

	public IfNode(Node _guard, Node _thenbranch, Node _elsebranch, String _tipo_if) {
		guard = _guard;
		thenbranch = _thenbranch;
		elsebranch = _elsebranch;
		tipo_if = _tipo_if;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();


		SymbolTable ST1 = ST.copy();
		SymbolTable ST2 = ST.copy();

		errors.addAll(guard.checkSemantics(ST, _nesting));
		errors.addAll(thenbranch.checkSemantics(ST1, _nesting));
		errors.addAll(elsebranch.checkSemantics(ST2, _nesting));

		return errors;
	}

	public Type typeCheck() {
		if (guard.typeCheck() instanceof BoolType) {
			if (tipo_if.equals("caso1")) {
				Type thenexp = thenbranch.typeCheck();
				Type elseexp = elsebranch.typeCheck();
				if (thenexp.getClass().equals(elseexp.getClass()))
					return thenexp;
				else {
					throw new Error("Type Error: incompatible types in then and else branches");
					//return new ErrorType();
				}
			} else if (tipo_if.equals("caso2")) {
				Type thenexp = thenbranch.typeCheck();
				if (elsebranch != null) {
					Type elseexp = elsebranch.typeCheck();
					if (thenexp.getClass().equals(elseexp.getClass()))
						return thenexp;
					else {
						throw new Error("Type Error: incompatible types in then and else branches");
						//return new ErrorType();
					}
				}
				return thenexp;
			}
		}
		throw new Error("Type Error: non boolean condition in if");
		//return new ErrorType();
	}

  
  	public String codeGeneration() {
  		String lthen = SimpLanlib.freshLabel(); 
  		String lend = SimpLanlib.freshLabel();
  		return guard.codeGeneration()
			 + "storei T1 1 \n"
			 + "beq A0 T1 "+ lthen + "\n"
			 + elsebranch.codeGeneration()
			 + "b " + lend + "\n"
			 + lthen + ":\n"
			 + thenbranch.codeGeneration()
	         + lend + ":\n" ;
  	}

  	public String toPrint(String s) {
	    return s+"If\n" + guard.toPrint(s+"  ") + thenbranch.toPrint(s+"  ")  + elsebranch.toPrint(s+"  ") ; 
	}
	  
}  