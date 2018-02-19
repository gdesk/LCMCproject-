package ast;
import java.util.ArrayList;
import lib.*;

/**
 * This class describes the root node in let-in expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */

public class ProgLetInNode implements Node {

	private ArrayList<Node> declist;
	private Node exp;

	public ProgLetInNode( final ArrayList<Node> declist, final Node exp) {
		this.declist=declist;
		this.exp=exp;
	}

	@Override
	public String toPrint(String indent) {
		String declstr="";
		for (Node dec:declist){
			declstr+=dec.toPrint(indent+"  ");
		}
		return indent+"ProgLetIn\n" + declstr + exp.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		for (Node dec:declist){
			dec.typeCheck();
		}
		return exp.typeCheck();
	}

	@Override
	public String codeGeneration() {
		String declCode="";
		for (Node dec:declist){
			declCode+=dec.codeGeneration();
		}
		return "push 0\n"+
		declCode+
		exp.codeGeneration()+"halt\n"+ FOOLlib.getCode();
	}
}  