package ast.prog;
import java.util.ArrayList;

import ast.DecNode;
import ast.Node;
import ast.cllist.ClassNode;
import lib.*;

/**
 * This class describes the root node in let-in expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */

public class ProgLetInNode implements Node {

	private ArrayList<ClassNode> cllist;
	private ArrayList<DecNode> declist;
	private Node exp;

	public ProgLetInNode( final ArrayList<DecNode> declist, final Node exp) {
		this.declist=declist;
		this.exp=exp;
	}

	public ProgLetInNode( final ArrayList<ClassNode> cllist, final ArrayList<DecNode> declist, final Node exp){
		this.cllist = cllist;
		this.declist = declist;
		this.exp = exp;
	}

	@Override
	public String toPrint(String indent) {

		String cllstr="";
		String declstr="";
		
		if(cllist != null) {
			for (ClassNode cl:cllist){
				cllstr+=cl.toPrint(indent+"  ");
			}
		}

		if(declist != null) {
			for (Node dec:declist){
				declstr+=dec.toPrint(indent+"  ");
			}
		}

		return indent+"ProgLetIn\n" + cllstr + declstr + exp.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {

		if(cllist != null) {
			for (DecNode cl:cllist){
				cl.typeCheck();
			}
		}
		for (DecNode dec:declist){
			dec.typeCheck();
		}
		return exp.typeCheck();
	}

	@Override
	public String codeGeneration() {
		String declCode="";
		for (DecNode dec:declist){
			declCode+=dec.codeGeneration();
		}
		return "push 0\n"+
		declCode+
		exp.codeGeneration()+
		"halt\n"+ 
		FOOLlib.getCode();
	}
}  