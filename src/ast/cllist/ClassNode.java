package ast.cllist;

import java.util.ArrayList;

import ast.DecNode;
import ast.Node;

public class ClassNode implements DecNode {

	private ArrayList<MethodNode> methods;
	private ArrayList<FieldNode> fields;
	private String id;
	private DecNode symType;
	
	public ClassNode(String id) {
		this.methods = new ArrayList<>();
		this.fields = new ArrayList<>();
		this.id=id;
	}
	
	private void addField(FieldNode field) {
		this.fields.add(field);
	}
	
	private void addMethod(MethodNode method) {
		this.methods.add(method);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getSymType() {
		return this.symType;
	}

}
