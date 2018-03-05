package ast.type;

import ast.Node;

/**
 * This class describes the Integer type.
 *  
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class IntTypeNode implements Node {

	@Override
	public String toPrint(String indent) {
		return indent+"IntType\n";  
	}

	@Override
	public Node typeCheck() {
		return null;
	}

	@Override
	public String codeGeneration() {
		return "";
	}

}  