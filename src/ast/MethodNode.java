package ast;


import lib.FOOLlib;
import java.util.ArrayList;

import ast.type.ArrowTypeNode;


public class MethodNode implements DecNode {

	private String id;
	private Node retType;
	private ArrayList<ParNode> parList;
	private ArrayList<VarNode> varList;
	private Node exp; 
	private Node symType;
	private String label;
	private int offset;
	
	public MethodNode(String id, Node retType) {
		this.id = id;
		this.retType = retType;
		this.parList = new ArrayList<>();
		this.varList = new ArrayList<>();
		this.label = FOOLlib.freshFunLabel();
		this.offset = 0;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public void setOffset( int offset) {
		this.offset = offset;
	}
	
	public int getOffset() {
		return this.offset;
	}
	
	public String getID() {
		return this.id;
	}
	
	public int getParSize() {
		return parList.size();
	}
	
	public void addParList(ArrayList<ParNode> parList){
		this.parList.addAll(parList);
	}

	public void addVarList(ArrayList<VarNode> varList) {
		this.varList.addAll(varList);
	}

	public void addExp(Node node) {
		this.exp = node;
	}
	
	public void setSymType(Node ast) {
		this.symType = ast;
	}

	@Override
	public Node typeCheck() {
		if(varList != null) {
			for (Node var:varList){
				var.typeCheck();
			};
		}
			
		if(!(FOOLlib.isSubtype(symType,retType))) {
			System.out.println("Wrong return type for method "+id);
		    System.exit(0);
		}  
		return null;
	}

	@Override
	public String codeGeneration() {
		String varCode="";
		if(varList != null) {
			for (Node var:varList){
				varCode+=var.codeGeneration();
			};
		}

		String popVar="";
		if(varList != null) {
			for (DecNode var:varList){
				if(var.getSymType() instanceof ArrowTypeNode) {
					popVar+="pop\n";
				}
				popVar+="pop\n";
			};
		}

		String popParl="";
		if(parList != null) {
			for (ParNode par:parList){
				if(par.getSymType() instanceof ArrowTypeNode) {
					popParl+="pop\n";
				}
				popParl+="pop\n";
			};
		}

		FOOLlib.putCode(
				label+":\n"+
				"cfp\n"+ //setta $fp allo $sp
				"lra\n"+ //inserimento Return Address
				varCode+
				exp.codeGeneration()+
				"srv\n"+ //pop del return value e memorizzazione in $rv
				popVar+ //una pop per ogni dichiarazione
				"sra\n"+ //pop del Return Address e memorizzazione in $ra
				"pop\n" + //pop di AL
				popParl + 
				"sfp\n" + // ripristino il $fp al valore del CL 
				"lrv\n" + // risultato della funzione sullo stack
				"lra\n" + 
				"js\n" // salta a $ra
				);	  	  
	
		return "";
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
