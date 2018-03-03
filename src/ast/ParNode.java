package ast;

/**
 * This class describes the parameter declaration.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class ParNode implements Node, DecNode {

	private String id;
	private Node type;

	public ParNode (final String id, final Node type) {
		this.id=id;
		this.type=type;
	}

	@Override
	public String toPrint(String s) {
		return s+"Par:" + id +"\n"
				+type.toPrint(s+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		return null;
	}

	@Override
	public String codeGeneration() {
		return "";
	}

	@Override
	public Node getSymType() {
		return this.type;
	}

}  