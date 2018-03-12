package ast;

public class NewNode implements Node {
	
	private String id;
	private STentry entry; // contiene STentry della classe id
	// rileggi ciò che è scritto su slide. 
	
	
	public NewNode(final String id) {
		this.id = id;
	}

	@Override
	public Node typeCheck() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toPrint(String indent) {
		return indent + "New node in class " + this.id + "\n";
	}
}
