package ast;

import lib.FOOLlib;

/**
 * This class describes the greater and equal operator.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */

public class GENode implements Node {

	private Node left;
	private Node right;

	public GENode (final Node left, final Node right) {
		this.left=left;
		this.right=right;
	}

	@Override
	public String toPrint(String indent) {
		return indent+">=\n" + left.toPrint(indent+"  ")  
		+ right.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		if ( ! ( FOOLlib.isSubtype(left.typeCheck(), new IntTypeNode()) &&
				FOOLlib.isSubtype(right.typeCheck(), new IntTypeNode()) ) ) {
			System.out.println("Non integers in GE");
			System.exit(0);	
		}
		return new IntTypeNode();
	}

	@Override
	public String codeGeneration() {
		return left.codeGeneration()+
				right.codeGeneration()+
				"bleq\n";
	}

}
