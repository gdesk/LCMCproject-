package ast.factor;

import ast.Node;
import ast.type.BoolTypeNode;
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
		return indent+"GreaterEqual\n" + left.toPrint(indent+"  ")  
		+ right.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		Node l= left.typeCheck();  
		Node r= right.typeCheck();  
		if ( !(FOOLlib.isSubtype(l, r) || FOOLlib.isSubtype(r, l)) ) {
			System.out.println("Incompatible types in greater equal");
			System.exit(0);	
		}  
		return new BoolTypeNode();
	}

	@Override
	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel();
		String l2 = FOOLlib.freshLabel();
		return right.codeGeneration() +
				left.codeGeneration() +
				"bleq " + l1 + "\n" +
				"push 0\n" +
				"b " + l2 + "\n" +
				l1 + ": \n" +
				"push 1\n" +
				l2 + ": \n";

	}

}
