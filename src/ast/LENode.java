package ast;

import lib.FOOLlib;

public class LENode implements Node {
	private Node left;
	private Node right;

	public LENode (final Node left, final Node right) {
		this.left=left;
		this.right=right;
	}

	@Override
	public String toPrint(String indent) {
		return indent+"LowerEqual\n" + left.toPrint(indent+"  ")  
		+ right.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		Node l= left.typeCheck();  
		Node r= right.typeCheck();  
		if ( !(FOOLlib.isSubtype(l, r) || FOOLlib.isSubtype(r, l)) ) {
			System.out.println("Incompatible types in lower equal");
			System.exit(0);	
		}  
		return new BoolTypeNode();
	}

	@Override
	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel(); 
		String l2 = FOOLlib.freshLabel();
		return 	 left.codeGeneration()
				+ right.codeGeneration()
				+ "bleq "+ l1 +"\n"
				+ "push 0\n"
				+ "b " + l2 + "\n"
				+ l1 + ":\n"
				+ "push 1\n"
				+ l2 + ":\n";
	}
}
