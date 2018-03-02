package ast;

import lib.FOOLlib;

public class NotNode implements Node {
	
	Node node;
	
	public NotNode(Node node) {
		this.node = node;
	}

	@Override
	public Node typeCheck() {
		if(!FOOLlib.isSubtype(node.typeCheck(), new BoolTypeNode())) {
			System.out.println("Incompatible type in not.");
			System.exit(0);
		}
		return new BoolTypeNode();
	}

	@Override
	public String codeGeneration(){
		String l1 = FOOLlib.freshLabel(); 
    	String l2 = FOOLlib.freshLabel();
    	
    	return 	  node.codeGeneration()
    			+ "push 0\n" 
    			+ "beq " + l1 + "\n" 
    			+ "push 0\n"
    			+ "b " + l2 + "\n"
    			+ l1 + ":\n"
    			+ "push 1\n"
    			+ l2 + ":\n";
	}

	@Override
	public String toPrint(String indent) {
		return indent
				+"Not\n"
				+ node.toPrint(indent+"  "); 
	
	}

}
