package ast;
import java.util.ArrayList;

import lib.FOOLlib;

/**
 * This class describes the call of function.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class CallNode implements Node {

	private String id;
	private int nestingLevel;
	private STentry entry;
	private ArrayList<Node> parList = new ArrayList<Node>(); 

	public CallNode (final String id, final STentry entry, final ArrayList<Node> parList, final int nestingLevel) {
		this.id=id;
		this.entry=entry;
		this.parList=parList;
		this.nestingLevel=nestingLevel;
	}

	@Override
	public String toPrint(String indent) {
		String parlstr="";
		for (Node par:parList){
			parlstr+=par.toPrint(indent+"  ");
		};
		return indent+"Call:" + id + " at nestinglevel " + nestingLevel+"\n"  +
		entry.toPrint(indent+"  ") +  
		parlstr;
	}
	@Override  
	public Node typeCheck() {	 
		ArrowTypeNode t=null;
		if (entry.getType() instanceof ArrowTypeNode) t=(ArrowTypeNode) entry.getType(); 
		else {
			System.out.println("Invocation of a non-function "+id);
			System.exit(0);
		}
		ArrayList<Node> p = t.getParList();
		if ( !(p.size() == parList.size()) ) {
			System.out.println("Wrong number of parameters in the invocation of "+id);
			System.exit(0);
		} 
		for (int i=0; i<parList.size(); i++) {
			if ( !(FOOLlib.isSubtype( (parList.get(i)).typeCheck(), p.get(i)) ) ) {
				System.out.println("Wrong type for "+(i+1)+"-th parameter in the invocation of "+id);
				System.exit(0);
			} 
		}
		return t.getRet();
	}

	@Override 
	public String codeGeneration() {

		String parCode=""; 
		for (int i=parList.size()-1; i>=0; i--) {
			parCode+=parList.get(i).codeGeneration();
		}
		String getAR=""; //recupero l'AR in cui è dichiarata la funzione che sto usando
		for (int i=0;i<nestingLevel-entry.getNestinglevel();i++) {
			//differenza di nesting level tra dove sono e la dichiarazione di "id"
			getAR+="lw\n";
		}
		return //allocazione della mia parte dell'AR della funzione che sto chiamando
				"lfp\n"+ //CL
				parCode + //allocazione valori parametri
				//Recupera FP ad AR dichiarazione funzione (Per settare l'access link)
				"lfp\n"+
				getAR+ //AL
				//codice per recuperare l'inidirizzo a cui saltare (stesso delle variabili)
				"push "+entry.getOffset()+"\n"+ //metto l'offset sullo stack
				"add\n"+
				"lw\n"+ 
				
 				//Recupera indir funzione (Per saltare al codice della funzione)
				"lfp\n"+
 				getAR+ //risalgo la catena statica e ottengo l'indirizzo dell'AR della variabile
				"push "+(entry.getOffset()-1)+"\n"+ //metto l'offset sullo stack
				"add\n"+
				"lw\n"+ //carico sullo stack l'indirizzo a cui saltare
				//effettuo il salto
				"js\n";
	}

}  