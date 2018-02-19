package ast;

/**
 * This class describes the root node. 
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */

public class ProgNode implements Node {

	private Node exp;

	public ProgNode (final Node exp) {
		this.exp=exp;
	}

	@Override
	public String toPrint(String indent) {

		return indent+"Prog\n" + exp.toPrint(indent+"  ") ;
	}

	@Override
	public Node typeCheck() {
		return exp.typeCheck();
	}

	@Override 
	public String codeGeneration() {
		return exp.codeGeneration()+"halt\n";
	}

}  