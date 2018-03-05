package ast.exp;

import ast.Node;
import ast.type.BoolTypeNode;
import lib.FOOLlib;

/**
 * This class describes the or operation.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 */
public class OrNode implements Node {

	private Node left;
	private Node right;

	public OrNode (final Node left, final Node right) {
		this.left=left;
		this.right=right;
	}

	@Override
	public String toPrint(String indent) {
		return indent+"Or\n" + left.toPrint(indent+"  ")  
		+ right.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		if ( ! ( FOOLlib.isSubtype(left.typeCheck(), new BoolTypeNode()) &&
				FOOLlib.isSubtype(right.typeCheck(), new BoolTypeNode()) ) ) {
			System.out.println("Non boolean in Or");
			System.exit(0);	
		}
		return new BoolTypeNode();
	}

	@Override
	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel();
		String l2 = FOOLlib.freshLabel();
		String l3 = FOOLlib.freshLabel();

		return 	  left.codeGeneration()
				+ "push 0\n" 
				+ "beq " + l1 + "\n" 
				+ "push 1\n" 
				+ "b " + l3 + "\n" 
				+ l1 + ":\n"
				+ right.codeGeneration()
				+ "push 0\n" 
				+ "beq " + l2 + "\n"
				+ "push 1\n" 
				+ "b " + l3 + "\n"
				+ l2 + ":\n"
				+ "push 0\n"
				+ l3 + ":\n";
	}


}
