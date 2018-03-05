package ast.term;

import ast.Node;
import ast.type.BoolTypeNode;
import lib.FOOLlib;

public class AndNode implements Node {
	private Node left;
	private Node right;

	public AndNode (final Node left, final Node right) {
		this.left=left;
		this.right=right;
	}

	@Override
	public String toPrint(String indent) {
		return indent+"And\n" + left.toPrint(indent+"  ")  
		+ right.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		if ( ! ( FOOLlib.isSubtype(left.typeCheck(), new BoolTypeNode()) &&
				FOOLlib.isSubtype(right.typeCheck(), new BoolTypeNode()) ) ) {
			System.out.println("Non Boolean in AND");
			System.exit(0);	
		}
		return new BoolTypeNode();
	}

	@Override
	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel(); 
		String l2 = FOOLlib.freshLabel();
		return    left.codeGeneration()
				+ "push 0\n" 
				+ "beq " + l1 + "\n" 
				+ right.codeGeneration()
				+ "push 0\n"
				+ "beq " + l1 + "\n" 
				+ "push 1\n" 
				+ "b " + l2 + "\n" 
				+ l1 + ":\n"
				+ "push 0\n"
				+ l2 + ":\n";
	}

}
