package ast;

import java.util.ArrayList;
import ast.type.ArrowTypeNode;
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
		}
		return indent+"Call:" + id + " at nestinglevel " + nestingLevel+"\n"  +
		entry.toPrint(indent+"  ") +  
		parlstr;
	}
	@Override  
	public Node typeCheck() {	 
		ArrowTypeNode t=null;
		if (entry.getType() instanceof ArrowTypeNode) {
			t=(ArrowTypeNode) entry.getType(); 
		}else {
			System.out.println("Invocation of a non-function "+id);
			System.exit(0);
		}
		ArrayList<Node> p = t.getParList();

		if ( !(p.size() == parList.size()) ) {
			System.out.println("CallNode: Wrong number of parameters in the invocation of "+id);
			System.exit(0);
		} 
		for (int i=0; i<parList.size(); i++) {
			Node parType = (parList.get(i)).typeCheck();
			Node decType = p.get(i);

			if ( (decType instanceof ArrowTypeNode && !(parType instanceof ArrowTypeNode))
					||!(FOOLlib.isSubtype( parType, p.get(i)) ) ) {
				System.out.println("CallNode: Wrong type for "+(i+1)+"-th parameter in the invocation of "+id);
				System.exit(0);
			} 
		}
		return t.getRet();
	}

	@Override 
	public String codeGeneration() {
		String parCode = "";
		for (int i = parList.size() - 1; i >= 0; i--) {
			parCode += parList.get(i).codeGeneration();
		}
		String getAR = "";
		
		/* Nel caso sia la call di un metodo, aggiungere 1 alla differenza di nesting level in modo che,
		 * risalendo la catena statica, si raggiunga la dispatch table. */
		int diff = (entry.isMethod())? nestingLevel - entry.getNestinglevel() + 1 : nestingLevel - entry.getNestinglevel();
		for (int i = 0; i < diff; i++) {
			getAR += "lw\n";
		}

		/* Allocazione prima parte dell'AR del METODO che sto chiamando (preso da estensione OO) */
		if (entry.isMethod()) {
			return "lfp\n" +                              // CL: mi salvo sullo stack il frame pointer del chiamante
					parCode +                             // allocazione parametri
					"lfp\n" +                             // parto dal Frame Pointer del chiamante
					getAR +                               // AL: mi salvo sullo stack l'indirizzo della Dispatch Table dove e' dichiarato il metodo risalendo la catena statica (lw, lw, ...)
					
					/* codice per recuperare l'inidirizzo a cui saltare */
					"push " + entry.getOffset() + "\n" +  // metto sullo stack l'offset
					"lfp\n" +                             // metto sullo stack il frame pointer di chi mi ha chiamato
					getAR +                               // risalgo la catena statica e ottengo l'indirizzo della Dispatch Table
					"add\n" +                             // sommo l'offset del metodo all'indirizzo ottenuto
					"lw\n" +                              // carico sullo stack l'indirizzo del codice del metodo
					
					"js\n";                               // effettuo il salto (poppando dallo stack e salvando anche l'ip su ra)

		/* Allocazione prima parte dell'AR della FUNZIONE che sto chiamando (preso da estensione HO) */
		} else {
			return "lfp\n" +                                  // CL: salvo sullo stack il frame pointer del chiamante
					parCode +                                 // allocazione parametri
					
					/* codice per recuperare il frame pointer all'AR piu' recente dove e' dichiarata la funzione (AL) */
					"push " + entry.getOffset() + "\n" +      // metto l'offset sullo stack
					"lfp\n" +                                 // parto dal Frame Pointer del chiamante
					getAR +                                   // risalgo la catena statica
					"add\n" +                                 // sommo l'offset all'indirizzo dell'AR ottenuto
					"lw\n" +                                  // AL: salvo sullo stack l'indirizzo dell'AR piu' recente dove e' dichiarata la funzione
					
					/* codice per recuperare l'inidirizzo a cui saltare */
					"push " + (entry.getOffset()-1) + "\n" +  // metto l'offset-1 sullo stack
					"lfp\n" +                                 // parto dal Frame Pointer del chiamante
					getAR +                                   // risalgo la catena statica
					"add\n" +                                 // sommo l'offset-1 all'indirizzo dell'AR ottenuto
					"lw\n" +                                  // carico sullo stack l'indirizzo del codice della funzione
					
					"js\n";                                   // effettuo il salto (poppando dallo stack e salvando anche l'ip su ra)
		}
	}

}  