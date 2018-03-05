package ast.exp;

import ast.Node;
import ast.type.IntTypeNode;
import lib.*;

/**
 * This class describes the sum expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class PlusNode implements Node {

	private Node left;
	private Node right;

	public PlusNode (final Node left, final Node right) {
		this.left=left;
		this.right=right;
	}

	@Override
	public String toPrint(String indent) {
		return indent+"Plus\n" + left.toPrint(indent+"  ")  
		+ right.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		if ( ! ( FOOLlib.isSubtype(left.typeCheck(), new IntTypeNode()) &&
				FOOLlib.isSubtype(right.typeCheck(), new IntTypeNode()) ) ) {
			System.out.println("Non integers in sum");
			System.exit(0);	
		}
		return new IntTypeNode();
	}

	@Override
	public String codeGeneration() {
		return 	  left.codeGeneration()
				+ right.codeGeneration()
				+ "add\n";
	}

}  