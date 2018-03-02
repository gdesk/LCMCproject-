package ast;
import lib.*;

/**
 * This class describes the multiplication expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class TimesNode implements Node {

	private Node left;
	private Node right;

	public TimesNode (final Node left, final Node right) {
		this.left=left;
		this.right=right;
	}

	@Override
	public String toPrint(String indent) {
		return indent+"Mult\n" + left.toPrint(indent+"  ")  
		+ right.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		if ( ! ( FOOLlib.isSubtype(left.typeCheck(), new IntTypeNode()) &&
				FOOLlib.isSubtype(right.typeCheck(), new IntTypeNode()) ) ) {
			System.out.println("Non integers in multiplication");
			System.exit(0);	
		}
		return new IntTypeNode();
	}

	@Override
	public String codeGeneration() {
		return 	  left.codeGeneration()
				+ right.codeGeneration()
				+ "mult\n";
	}

}  