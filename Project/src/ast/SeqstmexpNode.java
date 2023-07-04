package ast;

import java.util.ArrayList;
import java.util.HashMap;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError ;
import semanticanalysis.SymbolTable ;

public class SeqstmexpNode implements Node {
	private ArrayList<Node> liststm;
	private Node exp;
	private int nesting ;
  
	public SeqstmexpNode (ArrayList<Node> _stmlist, Node _exp) {
		liststm = _stmlist;
		exp = _exp ;
	}
  
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {		
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		nesting = _nesting ;
		
		for (Node stm : liststm)
			errors.addAll(stm.checkSemantics(ST, nesting));
		if (exp!=null)
			errors.addAll(exp.checkSemantics(ST, nesting));
		
		return errors;
	}

	public Type typeCheck() {
		Type stmNode = null;
		if (liststm!=null)
			for (Node stm:liststm) {
				stmNode= stm.typeCheck();
				if (stmNode instanceof ErrorType)
				{
					return new ErrorType();
				}
					
			}
		
		return exp.typeCheck();

		
	}  
  
	public String codeGeneration() {
		String stmlCode="";
		for (Node s: liststm)
		    stmlCode += s.codeGeneration();
		return stmlCode +
				exp.codeGeneration();
	}  
  
	public String toPrint(String s) {
		String stmlstr= "";
		String explstr="";
		
		if (liststm!=null) {
			for (Node stm:liststm)
			    stmlstr+=stm.toPrint(s+" ");
			stmlstr+="\n";
		}
			  
		explstr+=exp.toPrint(s+" ");
		return s+"" + "\n  "
	   	   +stmlstr 
	   	   +explstr;
	}

}  