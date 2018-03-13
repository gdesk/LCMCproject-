package ast.type;
import java.util.ArrayList;

import ast.Node;

/**
 * This class is used to describe the functional type.
 * The created object involves the type of parameters' list and return type.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class ArrowTypeNode implements Node {
	
	private ArrayList<Node> parList;
	private Node returnType;
	
	public ArrowTypeNode (final ArrayList<Node> parList, final Node returnType) {
		this.parList=parList;
		this.returnType=returnType;
	}
	
	public Node getRet () {
		return returnType;
	}
	  
	public ArrayList<Node> getParList () {
		return parList;
	}

	@Override
	public Node typeCheck() {
		return null;
	}

	@Override
	public String codeGeneration() {
		return null;
	}

	@Override
	public String toPrint(String indent) {
		String parlstr="";
		for (Node par:parList){
			parlstr+=par.toPrint(indent+"  ");
		};
		return indent+"ArrowTypeNode\n" + parlstr + returnType.toPrint(indent+"  ->") ; 
	}	
}