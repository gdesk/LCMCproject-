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
		this.symType = new ArrowTypeNode(new ArrayList<Node>(), this.retType);
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


	@Override
	public Node typeCheck() {
		for (Node dec : varList) {
			dec.typeCheck();
		}
		if (!FOOLlib.isSubtype(exp.typeCheck(), retType)) {
			System.out.println("Incompatible value for method "+id);
			System.exit(0);
		}
		return null;
	}

	@Override
	public String codeGeneration() {
//		String varCode="";
//		if(varList != null) {
//			for (Node var:varList){
//				varCode+=var.codeGeneration();
//			};
//		}
//
//		String popVar="";
//		if(varList != null) {
//			for (DecNode var:varList){
//				if(var.getSymType() instanceof ArrowTypeNode) {
//					popVar+="pop\n";
//				}
//				popVar+="pop\n";
//			};
//		}
//
//		String popParl="";
//		if(parList != null) {
//			for (ParNode par:parList){
//				if(par.getSymType() instanceof ArrowTypeNode) {
//					popParl+="pop\n";
//				}
//				popParl+="pop\n";
//			};
//		}
//
//		FOOLlib.putCode(
//				label+":\n"+
//				"cfp\n"+ //setta $fp allo $sp
//				"lra\n"+ //inserimento Return Address
//				varCode+
//				exp.codeGeneration()+
//				"srv\n"+ //pop del return value e memorizzazione in $rv
//				popVar+ //una pop per ogni dichiarazione
//				"sra\n"+ //pop del Return Address e memorizzazione in $ra
//				"pop\n" + //pop di AL
//				popParl + 
//				"sfp\n" + // ripristino il $fp al valore del CL 
//				"lrv\n" + // risultato della funzione sullo stack
//				"lra\n" + 
//				"js\n" // salta a $ra
//				);	  	  
//	
//		return "";
		label = FOOLlib.freshMethodLabel();
		String declCode = "";
		for (Node dec : varList) {
			declCode += dec.codeGeneration();
		}
		
		String popDecl = "";
		for (Node dec : varList) {
			if(((DecNode)dec).getSymType() instanceof ArrowTypeNode)
				popDecl += "pop\n";
			popDecl += "pop\n";
		}
		String popParl = "";
		for (Node par : parList) {
			if(((ParNode)par).getSymType() instanceof ArrowTypeNode)
				popParl += "pop\n";
			popParl += "pop\n";
		}
		
		FOOLlib.putCode(label + ":\n" +         // etichetta "fresh" del metodo
						"cfp\n" +               // fp=sp - setta $fp allo $sp
						"lra\n" +               // push(ra) - inserimento Return Address sullo stack
						declCode +              // generazione codice dichiarazioni interne al metodo
						exp.codeGeneration() +  // generazione codice corpo del metodo
						"srv\n" +               // pop del return value e memorizzazione in $rv
						popDecl +               // pulisco lo stack da quello che mi lasciano le dichiarazioni interne al metodo
						"sra\n" +               // pop del Return Address e memorizzazione in $ra
						"pop\n" +               // pop di AL
						popParl +               // pulisco lo stack da quello che mi lasciano i parametri attuali
						"sfp\n" +               // ripristino il $fp al CL del chiamante
						"lrv\n" +               // lascio il risultato del metodo sullo stack
						"lra\n" +               //
						"js\n");                // salto a $ra (che ho aggiornato poche righe sopra)
		
		/* Ritorno stringa vuota, a differenza del FunNode (che ritorna indirizzo di questo AR ed indirizzo codice della funzione) */
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
