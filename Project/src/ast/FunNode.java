package ast;
import java.util.ArrayList;
import java.util.HashMap;

import evaluator.SimpLanlib;
import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class FunNode implements Node {
	private String ID;
	private Type returntype ; 
	private ArrayList<ParNode> parlist ; 
	private ArrayList<Node> declist ;
	private ArrayList<Node> stmlist ;
	private Node exp;
	private ArrowType type ;
	private int nesting ;
	private String flabel ;

	public FunNode (String _id, Type _type, ArrayList<ParNode> _parlist, ArrayList<Node> _declist, ArrayList<Node> _stmlist, Node _exp) {
		ID = _id ;
		returntype = _type;
		parlist = _parlist ;
		declist = _declist ;
		stmlist = _stmlist;
		exp = _exp;

	}


	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {

		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		nesting = _nesting ;
		
		if (ST.lookup(ID) != null)
			errors.add(new SemanticError("Identifier " + ID + " already declared"));
		else {
			HashMap<String,STentry> HM = new HashMap<String,STentry>() ;
			ArrayList<Type> partypes = new ArrayList<Type>() ;
		
			ST.add(HM);
			
			for (ParNode arg : parlist){
    	  			partypes.add(arg.getType());
    	  			if (ST.top_lookup(arg.getId()))
    	  					errors.add(new SemanticError("Parameter id " + arg.getId() + " already declared")) ;
    	  			else {
						ST.insert(arg.getId(), arg.getType(), nesting + 1, "");
						STentry tmp = ST.lookup(arg.getId());
						tmp.setInitialized();
					}
    	  		}

			type = new ArrowType(partypes, returntype) ;
			
			ST.increaseoffset() ; // aumentiamo di 1 l'offset per far posto al return value
			for (Node dec : declist)
  				errors.addAll(dec.checkSemantics(ST, nesting+1));
			
			flabel = SimpLanlib.freshFunLabel() ;
			ST.insert(ID, type, nesting, flabel) ;

			for (Node stm : stmlist)
				errors.addAll(stm.checkSemantics(ST, nesting+1));

			if(exp!=null)
				errors.addAll(exp.checkSemantics(ST, nesting+1));

		}
		return errors ; // problemi con la generazione di codice!
	}
  
 	public Type typeCheck () {


		if (declist != null)
			for(Node innerD: this.declist)
				innerD.typeCheck();
		if (stmlist != null)
			for(Node innerS: this.stmlist)
				innerS.typeCheck();

		if(returntype instanceof VoidType) {
			if(exp == null)
				return new VoidType();
			else
			{
				System.out.println("Wrong return type for function "+ID);
				return new ErrorType() ;
			}
		}
		if(exp!=null) {
			Type exp_type = exp.typeCheck();
			if ( (exp.typeCheck().getClass()).equals(returntype.getClass())) {
				return exp_type;
			}
		}
		System.out.println("Wrong return type for function "+ID);
		return new ErrorType() ;
	}
  
  public String codeGeneration() {
	  
	    String declCode = "" ;
	    if (declist.size() != 0) {
	    		for (Node dec:declist){
	    			declCode = declCode + dec.codeGeneration();
	    		}
 	    }
	     
	    SimpLanlib.putCode(
	    			flabel + ":\n"
	    			+ "pushr RA \n"
	    			+ declCode

	    			+ "addi SP " + 	declist.size() + "\n"
	    			+ "popr RA \n"
	    			+ "addi SP " + 	parlist.size() + "\n"
	    			+ "pop \n"
	    			+ "store FP 0(FP) \n"
	    			+ "move FP AL \n"
	    			+ "subi AL 1 \n"
	    			+ "pop \n"
	    			+ "rsub RA \n" 
	    		);
	    
		return "push "+ flabel +"\n";
  }
  
  public String toPrint(String s) {
		String parlstr="";
		for (Node par:parlist){
		  parlstr += par.toPrint(s);
		}
		String declstr= "";
		if (declist!=null) 
		  for (Node dec:declist)
		    declstr+=dec.toPrint(s+" ");
	    return s+"Fun:" + ID +"\n"
			   +parlstr 
		   	   +declstr
		   	   + "\n" ;

	  }
	  
}  