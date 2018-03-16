package ast;

import java.util.ArrayList;

import ast.type.ArrowTypeNode;
import ast.type.ClassTypeNode;
import lib.FOOLlib;

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
	
	public void addArgs(ArrayList<Node> arg) {
		this.argList.addAll(arg);
	}

	@Override
	public Node typeCheck() {
		ArrayList<MethodNode> p = ((ClassTypeNode) classEntry.getType()).getMethods();
		int sizeOriginalMethod = -1;
		for(MethodNode met : p) {
			if(met.getID().equals(idMethod)) {
				sizeOriginalMethod = met.getParSize();
			}
		}
		if ( !(sizeOriginalMethod == argList.size()) ) {
			System.out.println("ClassCallNode: Wrong number of parameters in the invocation of "+idClass);
			System.exit(0);
		} 
		for (int i=0; i<argList.size(); i++) {
			Node parType = (argList.get(i)).typeCheck();
	    	Node decType = p.get(i);
			if ( (decType instanceof ArrowTypeNode && !(parType instanceof ArrowTypeNode))||!(FOOLlib.isSubtype( (argList.get(i)).typeCheck(), p.get(i)) ) ) {
				System.out.println("ClassCallNode: Wrong type for "+(i+1)+"-th parameter in the invocation of "+idClass);
				System.exit(0);
			} 
		}
		
		return methodEntry.getType();
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
