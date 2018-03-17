package ast;

public class FieldNode implements DecNode{
	
	private String fieldID;
	private Node type;
	private int offset;
	
	public FieldNode(String id, Node node) {
		this.fieldID = id;
		this.type = node;
		this.offset = 0;
	}
	
	public String getID() {
		return this.fieldID;
	}
	
	public int getOffset() {
		return this.offset;
	}
	
	public void setOffset(final int offset) {
		this.offset = offset;
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
