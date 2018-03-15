package ast;

import java.util.ArrayList;

import ast.type.ClassTypeNode;
import lib.FOOLlib;

public class ClassNode implements DecNode {

	private ArrayList<MethodNode> methods;
	private ArrayList<FieldNode> fields;
	private String id;
	private ClassTypeNode symType;
	private STentry superEntry; 

	public ClassNode(final String id) {
		this.methods = new ArrayList<MethodNode>();
		this.fields = new ArrayList<FieldNode>();
		this.id=id;
	}
	
	public void addFields(final ArrayList<FieldNode> fields) {
		this.fields.addAll(fields);
	}

	public void addMethods( final ArrayList<MethodNode> methods) {
		this.methods.addAll(methods);
	}
	
	public void setSuperEntry(STentry superEntry) {
		this.superEntry = superEntry;
	}
	
	public ArrayList<FieldNode> getFields(){
		return this.fields;
	}
	
	public ArrayList<MethodNode> getMethods(){
		return this.methods;
	}


	@Override
	public Node typeCheck() {
		if(this.methods !=null) {
			for(MethodNode meth : this.methods) {
				meth.typeCheck();
			}
		}
		
		if(this.superEntry != null) {
			ArrayList<FieldNode> parentFields = ((ClassNode)this.superEntry.getType()).getFields();
			ArrayList<MethodNode> parentMethods = ((ClassNode)this.superEntry.getType()).getMethods();
			
			for (int i = 0; i < parentFields.size(); i++) {
				FieldNode field = this.fields.get(i);
				FieldNode parentField = parentFields.get(i);
				
				FOOLlib.isSubtype(field, parentField);
			}
			
			for (int i = 0; i < parentFields.size(); i++) {
				MethodNode method = this.methods.get(i);
				MethodNode parentMethod = parentMethods.get(i);
				
				FOOLlib.isSubtype(method, parentMethod);
			}
		}
		
		return null;
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toPrint(String indent) {
		String fieldstr = "";
		String methstr = "";

		if(fields !=null) {
			for(FieldNode f: fields) {
				fieldstr += f.toPrint(indent + "  ");
			}
		}

		if(methods != null) 
			for(MethodNode m: methods) {
				methstr += m.toPrint(indent + "  ");
			}
		return indent + "Class:" + id + "\n" + fieldstr+ methstr+"\n"; 
	}

	@Override
	public ClassTypeNode getSymType() {
		return this.symType;
	}
	
	public void setSymType(ClassTypeNode node) {
		this.symType = node;
	}

}
