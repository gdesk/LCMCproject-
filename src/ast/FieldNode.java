package ast;

public class FieldNode implements DecNode{
	
	private String fieldID;
	private Node type;
	
	public FieldNode(String id, Node node) {
		this.fieldID = id;
		this.type = node;
	}
	
	public String getID() {
		return fieldID;
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
		return    indent
				+ "Field: " + this.fieldID + "\n"
				+ this.type.toPrint(indent + "  ");
	}

	@Override
	public Node getSymType() {
		return this.type;
	}

}