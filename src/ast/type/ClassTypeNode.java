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
	
	public void refreshFields() {
		ArrayList<FieldNode> refrFields = new ArrayList<>();
		refrFields.addAll(this.allFields);
		
		for(FieldNode field: this.allFields) {
			System.out.println("da  "+ field.toString()+" "+field.getOffset());
			refrFields.add(-field.getOffset()-1, field);
		}
		this.allFields.removeAll(this.allFields);
		this.allFields.addAll(refrFields);
	}
	
	public void refreshMethods() {
		ArrayList<MethodNode> refrMethod = new ArrayList<>();
		refrMethod.addAll(this.allMethods);
		
		for(MethodNode method: this.allMethods) {
			refrMethod.add(method.getOffset(), method);
		}
		this.allMethods.removeAll(allMethods);
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
