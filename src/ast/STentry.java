package ast;

/**
 * This class describes the entry of symbol table.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */

public class STentry {

	private int nestingLevel;
	private Node type;
	private int offset;
	private boolean isMethod;

	public STentry (int nestingLevel, int offset) {
		this.nestingLevel=nestingLevel;
		this.offset=offset;
		this.isMethod=false; 
	} 

	public STentry (int nestingLevel, Node type, int offset) {
		this.nestingLevel=nestingLevel;
		this.type=type;
		this.offset=offset;
		this.isMethod=false; 
	} 
	
	public void setIsMethod() {
		this.isMethod = true;
	}
	
	public boolean isMethod() {
		return this.isMethod;
	}

	public void addType(Node type) {
		this.type=type;
	}

	public Node getType() {
		return type;
	}

	public int getOffset() {
		return offset;
	}
	
	public int getNestinglevel() {
		return nestingLevel;
	}   
	
	public void setNestingLevel(int n) {
		this.nestingLevel = n;
	}

	public String toPrint(String indent) {
		return indent+"STentry: nestlev " + Integer.toString(nestingLevel) +"\n"+
				indent+"STentry: type " + type.toPrint("") +
				indent+"STentry: offset " + Integer.toString(offset) +"\n";  
	}

}  