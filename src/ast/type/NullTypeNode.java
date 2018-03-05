package ast.type;

import ast.Node;

public class NullTypeNode implements Node{

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
		return indent
				+ "NullType\n";
	}

}
