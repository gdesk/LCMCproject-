package ast.value;

import ast.Node;
import ast.type.BoolTypeNode;

/**
 * The class describes the boolean type of expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class BoolNode implements Node {

	private boolean value;

	public BoolNode (boolean value) {
		this.value=value;
	}

	@Override
	public Node typeCheck() {
		return new BoolTypeNode();
	}

	@Override
	public String codeGeneration() {
		return "push "+(value?1:0)+"\n";
	}

	@Override
	public String toPrint(String indent) {
		if(value) {
			return indent + "Bool:true\n";
		} else { 
			return indent + "Bool:false\n"; 
		}
	}
	
}  