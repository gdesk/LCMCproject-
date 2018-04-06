package ast.type;

import java.util.ArrayList;

import ast.FieldNode;
import ast.MethodNode;
import ast.Node;

public class ClassTypeNode implements Node {
	
	private ArrayList<Node> allFields;
	private ArrayList<Node> allMethods;
	
	public ClassTypeNode(ArrayList<Node> fields, ArrayList<Node> methods) {
		this.allFields = new ArrayList<>(fields); // incluono quelli ereditati
		this.allMethods = new ArrayList<>(methods);
	}
	
	public ArrayList<String> getIDsMethod(){
		ArrayList<String> idMethods = new ArrayList<>();
		for(Node f : this.allMethods) {
			idMethods.add(((MethodNode)f).getID());
		}
		return idMethods;
	}
	
	public void setMethod(int index, Node method) {
			this.allMethods.set(index, method);
		
	}
	public void setField(int index, Node field) {
			this.allFields.set(index, field);
	}
	
	public void addField(Node field) {
		this.allFields.add(field);
	}
	
	public void addMethod(Node method) {
		this.allMethods.add(method);
	}
	
	public ArrayList<Node> getFields(){
		return allFields;
	}
	
	public ArrayList<Node> getMethods(){
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
