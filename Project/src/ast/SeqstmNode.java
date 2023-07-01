package ast;

import java.util.ArrayList;
import java.util.HashMap;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError ;
import semanticanalysis.SymbolTable ;

public class SeqstmNode implements Node {

	private String Tipo_if;
	private ArrayList<Node> listStm;
	private Node exp;
	private int nesting ;
  
	public SeqstmNode (ArrayList<Node> _stmlist, String _tipo_if) {
		listStm = _stmlist;
		Tipo_if = _tipo_if;
	}

	public SeqstmNode (ArrayList<Node> _stmlist, String _tipo_if, Node _exp) {
		listStm = _stmlist;
		Tipo_if = _tipo_if;
		exp = _exp ;
	}
  
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {		
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		nesting = _nesting ;
		
		for (Node stm : listStm)
			errors.addAll(stm.checkSemantics(ST, nesting));
		if(Tipo_if.equals("exp")) {
			if (exp != null)
				errors.addAll(exp.checkSemantics(ST, nesting));
		}
		return errors;
	}

	public Type typeCheck() {
		Type stmNode = null;
		if (listStm!=null)
			for (Node stm:listStm) {
				stmNode= stm.typeCheck();
				if (stmNode instanceof ErrorType)
					return new ErrorType();
			}
		return stmNode;
	}  
  
	public String codeGeneration() {
		String stmlCode="";
		for (Node s: listStm)
		    stmlCode += s.codeGeneration();
		return stmlCode;
	}  
  
	public String toPrint(String s) {
		String stmlstr= "";
		
		if (listStm!=null) {
			for (Node stm:listStm)
			    stmlstr+=stm.toPrint(s+" ");
			stmlstr+="\n";
		}
			  
		return s+"" + "\n  "
	   	   +stmlstr;
	}
	
	public int getStmLenght() {
		return listStm.size();
	}
	
	public ArrayList<Node> getStmList(){
		return listStm;
	}

}  