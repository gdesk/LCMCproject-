package ast.cllist;

import ast.DecNode;
import ast.Node;

public class MethodNode implements DecNode {
	
	private DecNode symType;

	@Override
	public Node typeCheck() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toPrint(String indent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getSymType() {
		return this.symType;
	}

}
