package ast;

import lib.FOOLlib;

/**
 * This class describes the equal expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class EqualNode implements Node {

	private Node left;
	private Node right;

	public EqualNode (final Node left, final Node right) {
		this.left=left;
		this.right=right;
	}
	@Override
	public String toPrint(String indent) {
		return indent+"Equal\n" + left.toPrint(indent+"  ")   
		+ right.toPrint(indent+"  ") ; 
	}
	@Override  
	public Node typeCheck() {
		Node l= left.typeCheck();  
		Node r= right.typeCheck();
		if(( l instanceof ArrowTypeNode) || ( r instanceof ArrowTypeNode)) {
			System.out.println(" not ArrowTypeNode in equal");
			System.exit(0);	
		}
		if ( !(FOOLlib.isSubtype(l, r) || FOOLlib.isSubtype(r, l)) ) {
			System.out.println("Incompatible types in equal");
			System.exit(0);	
		}
		return new BoolTypeNode();
	}
	@Override
	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel();
		String l2 = FOOLlib.freshLabel();
		return left.codeGeneration()+
				right.codeGeneration()+
				"beq "+l1+"\n"+
				"push 0\n"+
				"b "+l2+"\n"+
				l1 + ": \n"+
				"push 1\n"+
				l2 + ": \n";
	}

}  