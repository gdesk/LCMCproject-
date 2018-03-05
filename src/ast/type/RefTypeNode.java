package ast.type;

import ast.Node;

public class RefTypeNode implements Node {
	
	private Node idClass;
	
	public RefTypeNode() {
		
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
		return indent+"RefTypeNode\n";
	}

}
