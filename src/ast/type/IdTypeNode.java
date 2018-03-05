package ast.type;

import ast.Node;

public class IdTypeNode implements Node {

	@Override
	public Node typeCheck() {
		return null;
	}

	@Override
	public String codeGeneration() {
		return null;
	}

	@Override
	public String toPrint(String indent) {
		return indent + "IDType\n";  
	}
}
