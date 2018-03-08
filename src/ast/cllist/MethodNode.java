package ast.cllist;

import ast.DecNode;
import ast.Node;
import ast.declist.VarNode;
import ast.*;

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
