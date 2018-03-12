package ast;

import java.util.ArrayList;

public class ClassCallNode implements Node {
	
	private String idClass;  // cercata per discesa di livelli (come IdNode e CallNode)
	private String idMethod; // cercata in virtual table (raggiunta come class table) della classe RefTypeNode di entry
	private STentry entry;
	private STentry methodEntry;
	private int nestingLevel;
	private ArrayList<Node> parList = new ArrayList<Node>(); 
	
	public ClassCallNode(final String idClass, final String idMethod, final STentry entry, final STentry methodEntry, final int nestringLevel, final ArrayList<Node> parList) {
		this.idClass = idClass;
		this.idMethod = idMethod; 
		this.entry = entry;
		this.methodEntry = methodEntry;
		this.nestingLevel = nestringLevel;
		this.parList = parList;
	}

	@Override
	public Node typeCheck() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toPrint(String indent) {
		String parlstr = "";
		for(Node parType : this.parList) {
			parlstr += parType.toPrint(indent + " ");
		}
		
		return indent + "Class call" + this.idClass + "." + this.idMethod + " at nestlev " + this.nestingLevel + "\n" 
				+ this.entry.toPrint(indent + " ") 
				+ this.methodEntry.toPrint(indent + " ")
				+ parlstr;
	}

}
