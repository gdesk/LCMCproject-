package ast.declist;

import ast.DecNode;
import ast.Node;
import lib.FOOLlib;

/**
 * This class describes the variable declaration.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */

public class VarNode implements Node, DecNode {

	private String id;
	private Node type;
	private Node exp;

	public VarNode (final String id, final Node type, final Node exp){
		this.id=id;
		this.type=type;
		this.exp=exp;
	}

	@Override
	public String toPrint(String indent) {
		return indent+"Var:" + id +"\n"
				+type.toPrint(indent+"  ")  
				+exp.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		if (! FOOLlib.isSubtype(exp.typeCheck(),type)) {
			System.out.println("Incompatible value for variable: "+id);
			System.exit(0);
		}
		return null;
	}

	@Override    
	public String codeGeneration() {
		return exp.codeGeneration();
	}

	@Override
	public Node getSymType() {
		return this.type;
	}

}  