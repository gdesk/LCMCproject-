package ast;

import java.util.ArrayList;

import ast.type.ArrowTypeNode;
import lib.FOOLlib;

/**
 * This class describes the function expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class FunNode implements Node, DecNode {

	private String id;
	private Node type; 
	private ArrayList<ParNode> parlist; 
	private ArrayList<DecNode> declist;
	private Node exp;
	private Node symType;

	public FunNode (final String i, final Node t) {
		this.id = i;
		this.type = t; 
		this.parlist = new ArrayList<>();
	}

	public void setSymType(Node symType) {
		this.symType = symType;
	}
	public void addDec (ArrayList<DecNode> d) {
		declist=d;
	}  

	public void addBody (Node b) {
		exp=b;
	}  

	public void addPar (ParNode p) { 
		parlist.add(p);  
	}  

	@Override
	public String toPrint(String indent) {
		String parlstr="";
		if(parlist != null) {
			for (ParNode par:parlist){
				parlstr+=par.toPrint(indent+"  ");
			}
		}
		String declstr="";
		if(declist != null) {
			for (Node dec:declist){
				declstr+=dec.toPrint(indent+"  ");
			}
		}
		return indent+"Fun:" + id +"\n"
				+type.toPrint(indent+"  ")
				+parlstr
				+declstr
				+exp.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		if(declist != null) {
			for (Node dec:declist){
				dec.typeCheck();
			};
		}
		if (! FOOLlib.isSubtype(exp.typeCheck(),type)) {
			System.out.println("Incompatible value for function");
			System.exit(0);
		}
		return null;
	}

	@Override
	public String codeGeneration() {
		String funl = FOOLlib.freshFunLabel();

		String declCode="";
		if(declist != null) {
			for (Node dec:declist){
				declCode+=dec.codeGeneration();
			};
		}

		String popDecl="";
		if(declist != null) {
			for (DecNode dec:declist){
				if(dec.getSymType() instanceof ArrowTypeNode) {
					popDecl+="pop\n";
				}
				popDecl+="pop\n";
			};
		}

		String popParl="";
		if(parlist != null) {
			for (ParNode par:parlist){
				if(par.getSymType() instanceof ArrowTypeNode) {
					popParl+="pop\n";
				}
				popParl+="pop\n";
			};
		}

		FOOLlib.putCode(funl+":\n"+
				"cfp\n"+ //setta $fp allo $sp
				"lra\n"+ //inserimento Return Address
				declCode+
				exp.codeGeneration()+
				"srv\n"+ //pop del return value e memorizzazione in $rv
				popDecl+ //una pop per ogni dichiarazione
				"sra\n"+ //pop del Return Address e memorizzazione in $ra
				"pop\n" + //pop di AL
				popParl + 
				"sfp\n" + // ripristino il $fp al valore del CL 
				"lrv\n" + // risultato della funzione sullo stack
				"lra\n" + 
				"js\n" // salta a $ra
				);	  	  

		return "lfp\n"+"push "+funl+"\n"; // la chiamata di una funzione e' una qualsiasi espressione, quindi deve valere l'invarianza
	}

	@Override
	public Node getSymType() {
		return this.symType;
	}

}  