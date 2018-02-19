package ast;

/**
 * This class describes print expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class PrintNode implements Node {

	private Node exp;

	public PrintNode (final Node exp) {
		this.exp=exp;
	}

	@Override
	public String toPrint(String indent) {
		return indent+"Print\n" + exp.toPrint(indent+"  ") ;
	}

	@Override
	public Node typeCheck() {
		return exp.typeCheck();
	}

	@Override
	public String codeGeneration() {
		return exp.codeGeneration()+"print\n";
	}

}  