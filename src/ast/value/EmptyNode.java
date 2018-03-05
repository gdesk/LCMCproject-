package ast.value;

import ast.Node;
import ast.type.EmptyTypeNode;

public class EmptyNode implements Node {

	@Override
	public Node typeCheck() {
		return new EmptyTypeNode();
	}

	@Override
	public String codeGeneration() {
		return "push -1\n";
	}

	@Override
	public String toPrint(String indent) {
		return indent
				+ "Null\n";
	}

}
