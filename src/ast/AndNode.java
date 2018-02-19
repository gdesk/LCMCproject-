package ast;

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
		if ( ! ( FOOLlib.isSubtype(left.typeCheck(), new IntTypeNode()) &&
				FOOLlib.isSubtype(right.typeCheck(), new IntTypeNode()) ) ) {
			System.out.println("Non integers in And");
			System.exit(0);	
		}
		return new IntTypeNode();
	}

	@Override
	public String codeGeneration() {
		return left.codeGeneration()+
				right.codeGeneration()+
				"sub\n"; //???????
	}

}
