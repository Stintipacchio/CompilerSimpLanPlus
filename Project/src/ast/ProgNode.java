package ast;

import java.util.ArrayList;
import java.util.HashMap;

import semanticanalysis.SemanticError ;
import semanticanalysis.SymbolTable ;
import semanticanalysis.STentry;

public class ProgNode implements Node {

	private String Tipo_prog;
	private Node exp;
	private ArrayList<Node> listStm;
	private ArrayList<Node> listDec;
	private int nesting ;
  
	public ProgNode (Node _exp, String _tipo_prog) {
		exp = _exp ;
		Tipo_prog = _tipo_prog;
	}

	public ProgNode(ArrayList<Node> _listDec, String _tipo_prog, ArrayList<Node> _listStm, Node exp) {
		this.listDec = _listDec;
		this.listStm= _listStm;
		this.exp = exp;
		Tipo_prog = _tipo_prog;
	}
  
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {		
		if(Tipo_prog.equals("caso1"))
			return exp.checkSemantics(ST, _nesting);
		else {
			ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
			HashMap<String,STentry> Scope = new HashMap<String,STentry>();
			ST.add(Scope);
			nesting = _nesting ;

			for (Node dec : listDec)
				errors.addAll(dec.checkSemantics(ST, nesting));
			for (Node stm : listStm)
				errors.addAll(stm.checkSemantics(ST, nesting));
			if (exp!=null)
				errors.addAll(exp.checkSemantics(ST, nesting));

			return errors;

		}
	}

	public Type typeCheck() {
		return exp.typeCheck();
	}  
  
	public String codeGeneration() {
		return exp.codeGeneration()+"halt\n";
	}  
  
	public String toPrint(String s) {
		return "Prog\n" + exp.toPrint("  ") ;
	}

}  