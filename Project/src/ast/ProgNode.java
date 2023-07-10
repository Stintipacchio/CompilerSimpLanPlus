package ast;

import java.util.ArrayList;
import java.util.HashMap;

import semanticanalysis.SemanticError ;
import semanticanalysis.SymbolTable ;
import semanticanalysis.STentry;

public class ProgNode implements Node {

	private String Tipo_prog;
	private Node exp;
	private ArrayList<Node> listDec;
	private ArrayList<Node> listStm;
	private int nesting ;
  
	public ProgNode (Node _exp, String _tipo_prog) {
		exp = _exp ;
		Tipo_prog = _tipo_prog;
	}

	public ProgNode(Node exp, String _tipo_prog, ArrayList<Node> _listDec,  ArrayList<Node> _listStm ) {
		this.listDec = _listDec;
		this.listStm = _listStm;
		this.exp = exp;
		Tipo_prog = _tipo_prog;
	}
  
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {		
		if(Tipo_prog.equals("caso1"))
			return exp.checkSemantics(ST, _nesting);
		else  {
			ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
			HashMap<String,STentry> Scope = new HashMap<String,STentry>();
			ST.add(Scope);
			nesting = _nesting ;

			for (Node dec : listDec)
				errors.addAll(dec.checkSemantics(ST, nesting));

			for (Node stm : listStm)
				errors.addAll(stm.checkSemantics(ST, nesting));

			/*if(this.listStm != null && this.listStm.size() > 0) {
				for (Node stm : this.listStm)
					errors.addAll(stm.checkSemantics(ST, nesting));
			}*/
			if (exp!=null)
				errors.addAll(exp.checkSemantics(ST, nesting));

			return errors;
		}
	}

	public Type typeCheck() {
		if(this.Tipo_prog.equals("caso1")){
			return exp.typeCheck();
		} else if (this.Tipo_prog.equals("caso2")) {

			if (listDec!=null)
				for (Node dec:listDec)
					if (dec.typeCheck() instanceof ErrorType)
						return new ErrorType();
			if (listStm!=null)
				for (Node stm:listStm) {
					if (stm.typeCheck() instanceof ErrorType) {
						return new ErrorType();
					}
				}
			if (exp!=null)
				return exp.typeCheck();
			return new VoidType();
		}
		return exp.typeCheck();
	}  
  
	public String codeGeneration() {
		return exp.codeGeneration()+"halt\n";
	}  
  
	public String toPrint(String s) {
		return "Prog\n" + exp.toPrint("  ") ;
	}

}  