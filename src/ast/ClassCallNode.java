package ast;

import java.util.ArrayList;

public class ClassCallNode implements Node {
	
	private String idClass;  // cercata per discesa di livelli (come IdNode e CallNode)
	private String idMethod; // cercata in virtual table (raggiunta come class table) della classe RefTypeNode di entry
	private STentry classEntry;
	private STentry methodEntry;
	private int nestingLevel;
	private ArrayList<Node> argList; 
	
	public ClassCallNode(final String idClass, final String idMethod, final STentry classEntry, final STentry methodEntry, final int nestringLevel) {
		this.idClass = idClass;
		this.idMethod = idMethod; 
		this.classEntry = classEntry;
		this.methodEntry = methodEntry;
		this.nestingLevel = nestringLevel;
		this.argList = new ArrayList<>() ; 
	}
	
	public void addArg(Node arg) {
		this.argList.add(arg);
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
		String argstr = "";
		for(Node parType : this.argList) {
			argstr += parType.toPrint(indent + " ");
		}
		
		return indent + "Class call" + this.idClass + "." + this.idMethod + " at nesting level " + this.nestingLevel + "\n" 
				+ this.classEntry.toPrint(indent + " ") 
				+ this.methodEntry.toPrint(indent + " ")
				+ argstr;
	}

}
