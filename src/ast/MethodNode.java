package ast;

import ast.*;
import lib.FOOLlib;

import java.util.ArrayList;


public class MethodNode implements DecNode {

	private String id;
	private Node retType;
	private ArrayList<ParNode> parList;
	private ArrayList<VarNode> varList;
	private Node exp; 
	private DecNode symType;

	public MethodNode(String id, Node retType) {
		this.id = id;
		this.retType = retType;
	}

	public void addParList(ArrayList<ParNode> parList){
		this.parList = parList;
	}

	public void addVarList(ArrayList<VarNode> varList) {
		this.varList = varList;
	}

	public void addExp(Node node) {
		this.exp = node;
	}

	@Override
	public Node typeCheck() {
		if(varList != null) {
			for (Node var:varList){
				var.typeCheck();
			};
		}
		System.out.println("aaaaaaaaaaaaaaaaa"+exp.typeCheck()+" "+ retType);
		
		if ( !FOOLlib.isSubtype(exp.typeCheck(), retType)) {
			System.out.println("Incompatible value for method");
			System.exit(0);
		}
		return null;
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toPrint(String indent) {
		String parstr = "";
		String varstr = "";

		if (parList != null) {
			for(ParNode par: parList) {
				parstr += par.toPrint(indent+"  ");
			}
		}

		if (varList != null) 
			for(DecNode dec: varList) {
				varstr += dec.toPrint(indent+"  ");
			}
		return indent + "Method:" + id + "\n"
		+ retType.toPrint(indent + "  ")
		+ parstr
		+ varstr
		+ exp.toPrint(indent + "  ") ; 
	}

	@Override
	public Node getSymType() {
		return this.symType;
	}

}
