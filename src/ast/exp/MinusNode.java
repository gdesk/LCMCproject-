package ast.exp;

import ast.Node;
import ast.type.IntTypeNode;
import lib.FOOLlib;

/**
 * This class describes a minus expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */

public class MinusNode implements Node {

	private Node left;
	private Node right;

	public MinusNode (final Node left, final Node right) {
		this.left=left;
		this.right=right;
	}

	@Override
	public String toPrint(String indent) {
		return indent+"Minus\n" + left.toPrint(indent+"  ")  
		+ right.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		if ( ! ( FOOLlib.isSubtype(left.typeCheck(), new IntTypeNode()) &&
				FOOLlib.isSubtype(right.typeCheck(), new IntTypeNode()) ) ) {
			System.out.println("Non integers in minus");
			System.exit(0);	
		}
		return new IntTypeNode();
	}

	@Override
	public String codeGeneration() {
		return 	  left.codeGeneration()
				+ right.codeGeneration()
				+ "sub\n";
	}

}
