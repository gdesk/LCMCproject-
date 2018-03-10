package ast.cllist;

import java.util.ArrayList;

import ast.DecNode;
import ast.Node;

public class ClassNode implements DecNode {

	private ArrayList<MethodNode> methods;
	private ArrayList<FieldNode> fields;
	private String id;
	private DecNode symType;
	
	public ClassNode(final String id) {
		this.methods = null;
		this.fields = null;
		this.id=id;
	}
	
	public void addFields(final ArrayList<FieldNode> fields) {
		this.fields = fields;
	}
	
	public void addMethods( final ArrayList<MethodNode> methods) {
		this.methods = methods;
	}
	
	
	@Override
	public Node typeCheck() {
		return null;
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toPrint(String indent) {
		return null;
	}

	@Override
	public Node getSymType() {
		return this.symType;
	}

}
