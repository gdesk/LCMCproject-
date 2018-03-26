package ast.type;

import java.util.ArrayList;

import ast.FieldNode;
import ast.MethodNode;
import ast.Node;

public class ClassTypeNode implements Node {
	
	private ArrayList<FieldNode> allFields;
	private ArrayList<MethodNode> allMethods;
	
	public ClassTypeNode(ArrayList<FieldNode> fields, ArrayList<MethodNode> methods) {
		this.allFields = fields; // incluono quelli ereditati
		this.allMethods = methods;
	}
	public ArrayList<String> getIDs() {
		ArrayList<String> idFields = new ArrayList<>();
		for(FieldNode f : this.allFields) {
			idFields.add(f.getID());
		}
		return idFields;
		
	}
	public void refreshFields() {
		ArrayList<FieldNode> refrFields = new ArrayList<>();
		refrFields.addAll(allFields);
		
		for(FieldNode field: this.allFields) {
			System.out.println("field ref prima : " + refrFields);
			refrFields.remove(-field.getOffset()-1);
			refrFields.add(-field.getOffset()-1, field);
		}
		
		this.allFields.removeAll(this.allFields);
		System.out.println("field dopo remove: " + this.allFields);
		this.allFields.addAll(refrFields);
	}
	
	public void refreshMethods() {
		ArrayList<MethodNode> refrMethod = new ArrayList<>(this.allMethods.size());
		
		for(MethodNode method: this.allMethods) {
			refrMethod.add(method.getOffset(), method);
		}
		
		this.allMethods.removeAll(this.allMethods);
		this.allMethods.addAll(refrMethod);
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
