package ast;

import java.util.ArrayList;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class VarStmNode implements Node {
	private String id;
	private Node type;
	private Node exp;
	private int nesting;
	private int id_offset;
	private int id_nesting;
	private STentry T;
	private boolean initialized;
	
	public VarStmNode(String _id, Node _exp) {
		id = _id ;
		exp = _exp ;
	}
  
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
   		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
  		nesting = _nesting ;
  		T = ST.lookup(id);
		initialized = true;

		if (T == null)
        	errors.add(new SemanticError("Variabile '" + id + "' non è stata dichiarata"));
        
        errors.addAll(exp.checkSemantics(ST, nesting));
        
        if (T!=null){   //non so casa faccia
        	    	
        	id_offset = T.getoffset();
        	id_nesting = T.getnesting();
        	type = T.gettype();
        	
        	//Ad-hoc per l'assegnamento! Caso particolare nell'if da gestire così a causa di copia SymbolTable
        	this.typeCheck();   //perchè fa la typecheck qui?
        }
        return errors ;
	}
  
	public Type typeCheck () {
		Type expvar = exp.typeCheck(); 
		if(expvar instanceof ErrorType) {
			
			return new ErrorType() ;
		}
		if (expvar.getClass().equals(type.getClass() )) {
			
			//T.setInitialized();    // cambiato dalla versione di ga. vedere se T è accessibile da altre parti
			initialized = true;
			return new VoidType() ;
		}
		else {
			System.out.println("Type Error: incompatible type of expression for variable "+id) ;
			return new ErrorType() ;
		}
	}
  
	public String codeGeneration() {
		
		String getAR="";
		for (int i=0; i < nesting - id_nesting; i++) 
	    	 getAR += "store T1 0(T1) \n";
		return exp.codeGeneration()
			+ "move AL T1 \n"
			+ getAR
			+ "subi T1 " + (id_offset)+"\n"
			+ "load A0 0(T1) \n";
			//"pushr A0 \n" ;
	}  
    
	public String toPrint(String s) {
		
        
		return s + "AsgVar:" + id + type.toPrint(" ") + "\n" + exp.toPrint(s+"\t"); 
	}

}  