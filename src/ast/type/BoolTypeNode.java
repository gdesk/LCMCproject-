package ast.type;

import ast.Node;

/**
 * This class describes the Boolean type of declaration.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 * 
 */
public class BoolTypeNode implements Node {

	@Override
	public Node typeCheck() {
		return null;
	}

	@Override
	public String codeGeneration() {
		return "";
	}

	@Override
	public String toPrint(String indent) {
		return indent + "BoolType\n";  
	}
}  