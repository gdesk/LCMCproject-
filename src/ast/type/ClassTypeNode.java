package ast.type;

import java.util.ArrayList;

import ast.Node;
import ast.cllist.FieldNode;
import ast.cllist.MethodNode;

public class ClassTypeNode implements Node {
	
	private ArrayList<FieldNode> allFields;
	private ArrayList<MethodNode> allMethods;
	
	public ClassTypeNode(ArrayList<FieldNode> fields, ArrayList<MethodNode> methods) {
		this.allFields = fields; // incluono quelli ereditati
		this.allMethods = methods;
	}
	
	public void addField(FieldNode field) {
		this.allFields.add(field);
	}
	
	public void addMethod(MethodNode method) {
		this.allMethods.add(method);
	}
	
	public ArrayList<FieldNode> getFields(){
		return allFields;
	}
	
	public ArrayList<MethodNode> getMethods(){
		return allMethods;
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
