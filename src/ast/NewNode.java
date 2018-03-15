package ast;

import java.util.ArrayList;

import ast.type.ClassTypeNode;
import ast.type.RefTypeNode;

public class NewNode implements Node {
	
	private String id;
	private STentry entry; 
	private ArrayList<Node> argList;
	
	
	
	public NewNode(final String id, STentry entry) {
		this.id = id;
		this.entry = entry;
		this.argList = new ArrayList<>();
	}
	
	public void addArg(Node node) {
		this.argList.add(node);
	}

	@Override
	public Node typeCheck() {
		return new RefTypeNode(id);
	}

	@Override
	public String codeGeneration() {
		return null;
	}

	@Override
	public String toPrint(String indent) {
		String argstr = "";
		for(Node argNode : this.argList) {
			argstr += argNode.toPrint(indent + " ");
		}
		return indent + "New node in class " + this.id + "\n"
				+argstr;
	}
}
