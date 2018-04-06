package ast.type;

import ast.Node;

public class RefTypeNode implements Node {
	
	private String idClass;
	
	public RefTypeNode(String idClass) {
		this.idClass = idClass;
	}
	
	public String getID() {
		return this.idClass;
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
		return indent+"RefTypeNode: "+ this.idClass+"\n";
	}
}
