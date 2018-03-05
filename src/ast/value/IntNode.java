package ast.value;

import ast.Node;
import ast.type.IntTypeNode;

/**
 * This class describes the integer expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class IntNode implements Node {

	private Integer value;

	public IntNode (final Integer value) {
		this.value=value;
	}
	
	@Override
	public String toPrint(String s) {
		return s+"Int:" + Integer.toString(value) +"\n";  
	}
	
	@Override
	public Node typeCheck() {
		return new IntTypeNode(); 
	}
	
	@Override
	public String codeGeneration() {
		return "push "+value+"\n";
	}

}  