package ast.value;
import ast.Node;
import ast.type.BoolTypeNode;
import lib.FOOLlib;

/**
 * This class describes the if expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class IfNode implements Node {

	private Node cond;
	private Node then;
	private Node el;

	public IfNode (final Node cond, final Node then, final Node el) {
		this.cond=cond;
		this.then=then;
		this.el=el;
	}

	@Override
	public String toPrint(String indent) {
		return indent+"If\n" + cond.toPrint(indent+"  ") 
		+ then.toPrint(indent+"  ")   
		+ el.toPrint(indent+"  ") ; 
	}
	
	@Override
	public Node typeCheck() {
		if ( !(FOOLlib.isSubtype(cond.typeCheck(), new BoolTypeNode())) ) {
			System.out.println("non boolean condition in if");
			System.exit(0);		
		}
		Node t= then.typeCheck();  
		Node e= el.typeCheck();  
		if (FOOLlib.isSubtype(t, e)) {
			return e;
		}
		if (FOOLlib.isSubtype(e, t)) {
			return t;
		}
		System.out.println("Incompatible types in then-else branches");
		System.exit(0);
		return null;
	}

	@Override
	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel();
		String l2 = FOOLlib.freshLabel();
		return cond.codeGeneration()+
				"push 1\n"+
				"beq "+l1+"\n"+
				el.codeGeneration()+
				"b "+l2+"\n"+
				l1 + ": \n"+
				then.codeGeneration()+
				l2 + ": \n";		  
	}

}  