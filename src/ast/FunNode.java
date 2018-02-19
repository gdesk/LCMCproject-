package ast;

import java.util.ArrayList;
import lib.FOOLlib;

/**
 * This class describes the function expression.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class FunNode implements Node {

	private String id;
	private Node type; 
	private ArrayList<Node> parlist = new ArrayList<Node>(); 
	private ArrayList<Node> declist = new ArrayList<Node>(); 
	private Node exp;

	public FunNode (final String i, final Node t) {
		id=i;
		type=t;
	}

	public void addDec (ArrayList<Node> d) {
		declist=d;
	}  

	public void addBody (Node b) {
		exp=b;
	}  

	public void addPar (Node p) { 
		parlist.add(p);  
	}  

	@Override
	public String toPrint(String indent) {
		String parlstr="";
		for (Node par:parlist){
			parlstr+=par.toPrint(indent+"  ");
		};
		String declstr="";
		for (Node dec:declist){
			declstr+=dec.toPrint(indent+"  ");
		};
		return indent+"Fun:" + id +"\n"
		+type.toPrint(indent+"  ")
		+parlstr
		+declstr
		+exp.toPrint(indent+"  ") ; 
	}

	@Override
	public Node typeCheck() {
		for (Node dec:declist){
			dec.typeCheck();
		};
		if (! FOOLlib.isSubtype(exp.typeCheck(),type)) {
			System.out.println("Incompatible value for variable");
			System.exit(0);
		}
		return null;
	}

	@Override
	public String codeGeneration() {
		String funl = FOOLlib.freshFunLabel();

		String declCode="";
		for (Node dec:declist){
			declCode+=dec.codeGeneration();
		};

		String popDecl="";
		for (Node dec:declist){
			popDecl+="pop\n";
		};

		String popParl="";
		for (Node par:parlist){popParl+="pop\n";};

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
				"lra\n" + "js\n" // salta a $ra
				);	  	  

		return "push "+funl+"\n";
	}

}  