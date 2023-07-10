package semanticanalysis;

import ast.Type;

public class STentry {
	private Type type ;
	private int offset ;
	private int nesting ;
	private String label ;
	private boolean initialized;        //   da verificare

	
	public STentry(Type _type, int _offset, int _nesting) {
		type = _type ;
		offset = _offset ;
		nesting = _nesting ;
	}
	
	public STentry(Type _type, int _offset, int _nesting, String  _label) {
		type = _type ;
		offset = _offset ;
		nesting = _nesting ;
		label = _label ;
	}
	
	public Type gettype() {
		return type ;
	}
	public int getoffset() {
		return offset ;
	}
	public int getnesting() {
		return nesting ;
	}
	public String getlabel() {
		return label ;
	}
	public void setInitialized() {
		initialized = true;
	}
	public void setInitialized(boolean set_initialized) {
		initialized = set_initialized;
	}

	public boolean getInitialized() {return initialized;}
	public void setLabel(String label) {
		this.label = label;
	}


}
