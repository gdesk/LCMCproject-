package ast;

public class NullNode implements Node {

	@Override
	public Node typeCheck() {
		return new NullTypeNode();
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
