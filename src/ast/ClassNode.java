package ast;

import java.util.ArrayList;

public class ClassNode implements DecNode {

	private ArrayList<MethodNode> methods;
	private ArrayList<FieldNode> fields;
	private String id;
	private DecNode symType;
	private STentry superEntry; // STentry della classe ID2 (extends ID2) - istanziarlo in altri modo

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
	public Node getSymType() {
		return this.symType;
	}

}