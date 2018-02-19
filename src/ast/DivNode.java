package ast;

import lib.FOOLlib;

/**
 * This class describes the division expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 */
public class DivNode implements Node {
	
	private Node left;
	private Node right;

	public DivNode (final Node left, final Node right) {
		this.left=left;
		this.right=right;
	}

	@Override
	public String toPrint(String indent) {
		return indent+"Div\n" + left.toPrint(indent+"  ")  
		+ right.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		if ( ! ( FOOLlib.isSubtype(left.typeCheck(), new IntTypeNode()) &&
				FOOLlib.isSubtype(right.typeCheck(), new IntTypeNode()) ) ) {
			System.out.println("Non integers in div");
			System.exit(0);	
		}
		return new IntTypeNode();
	}

	@Override
	public String codeGeneration() {
		return left.codeGeneration()+
				right.codeGeneration()+
				"div\n";
	}

}
