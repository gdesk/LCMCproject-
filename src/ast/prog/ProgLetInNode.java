package ast.prog;
import java.util.ArrayList;

import ast.ClassNode;
import ast.DecNode;
import ast.Node;
import lib.*;

/**
 * This class describes the root node in let-in expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */

public class ProgLetInNode implements Node {

	private ArrayList<DecNode> cllist;
	private ArrayList<DecNode> declist;
	private Node exp;

	public ProgLetInNode(ArrayList<DecNode> c, ArrayList<DecNode> d, Node e) {
		cllist = c;
		declist = d;
		exp = e;
	}

	public String toPrint(String s) {
		String str = "";
		for (Node cl : cllist) {
			str += cl.toPrint(s + "  ");
		}

		for (Node dec : declist) {
			str += dec.toPrint(s + "  ");
		}

		return s + "ProgLetIn\n" +
				str +
				exp.toPrint(s + "  ");
	}

	public Node typeCheck() {
		for (Node cl : cllist) {
			cl.typeCheck();
		}
		for (Node dec : declist) {
			dec.typeCheck();
		}
		return exp.typeCheck();
	}

	public String codeGeneration() {
		String concCode = "";
		for (DecNode cl : cllist) {
			concCode += cl.codeGeneration();
		}

		for (DecNode dec : declist) {
			concCode += dec.codeGeneration();
		}

		return "push 0\n" +
				concCode +
				exp.codeGeneration() +
				"halt\n" +
				FOOLlib.getCode();
	}
	String declCode="";

}