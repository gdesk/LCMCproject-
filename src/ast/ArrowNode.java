package ast;

import java.util.ArrayList;

public class ArrowNode implements Node {
	
	private Node node;
	private ArrayList<Node> arrowList;

	public ArrowNode(final Node node) {
		this.node = node;
		this.arrowList = new ArrayList<>();
	}
	
	public void addType(final Node node) {
		this.arrowList.add(node);
	}

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
}
