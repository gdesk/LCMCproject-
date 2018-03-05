package ast.type;

import java.util.ArrayList;

import ast.Node;

public class ClassTypeNode implements Node {
	
	private ArrayList<Node> allFields;
	private ArrayList<Node> allMethods;
	
	public ClassTypeNode(ArrayList<Node> fields, ArrayList<Node> methods) {
		this.allFields = fields; // incluono quelli ereditati
		this.allMethods = methods;
	}
	
	public void addField(Node field) {
		this.allFields.add(field);
	}
	
	public void addMethod(Node method) {
		this.allMethods.add(method);
	}
	
	@Override
	public Node typeCheck() {
		return null;
	}

	@Override
	public String codeGeneration() {
		return "";
	}

	@Override
	public String toPrint(String indent) {
		// TODO Auto-generated method stub
		return indent + "ClassTypeNode \n";
	}

}
