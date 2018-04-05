package ast.type;

import java.util.ArrayList;

import ast.FieldNode;
import ast.MethodNode;
import ast.Node;

public class ClassTypeNode implements Node {
	
	private ArrayList<FieldNode> allFields;
	private ArrayList<MethodNode> allMethods;
	
	public ClassTypeNode(ArrayList<FieldNode> fields, ArrayList<MethodNode> methods) {
		this.allFields = new ArrayList<>(fields); // incluono quelli ereditati
		this.allMethods = new ArrayList<>(methods);
	}
	
	public ArrayList<String> getIDsMethod(){
		ArrayList<String> idMethods = new ArrayList<>();
		for(MethodNode f : this.allMethods) {
			idMethods.add(f.getID());
		}
		return idMethods;
	}
	
	public void setMethod(int index, MethodNode method) {
			this.allMethods.set(index, method);
		
	}
	public void setField(int index, FieldNode field) {
			this.allFields.set(index, field);
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
