package ast;

import java.util.ArrayList;

import ast.type.ArrowTypeNode;
import lib.FOOLlib;

public class ClassCallNode implements Node {

	private String idClass;  // cercata per discesa di livelli (come IdNode e CallNode)
	private String idMethod; // cercata in virtual table (raggiunta come class table) della classe RefTypeNode di entry
	private STentry classEntry;
	private STentry methodEntry;
	private int nestingLevel;
	private ArrayList<Node> argList; 

	public ClassCallNode(final String idClass, final String idMethod, final STentry classEntry, final STentry methodEntry, final int nestringLevel) {
		this.idClass = idClass;
		this.idMethod = idMethod; 
		this.classEntry = classEntry;
		this.methodEntry = methodEntry;
		this.nestingLevel = nestringLevel;
		this.argList = new ArrayList<>() ; 
	}

	public void addArgs(ArrayList<Node> arg) {
		this.argList.addAll(arg);
	}

	@Override
	public Node typeCheck() {
		ArrowTypeNode t = null;
		if (methodEntry.getType() instanceof ArrowTypeNode)
			t = (ArrowTypeNode) methodEntry.getType();
		else {
			System.out.println("ClassCallNode: Invocation of a non-method " + idMethod);
			System.exit(0);
		}
		ArrayList<Node> p = t.getParList();
		if (!(p.size() == argList.size())) {
			System.out.println("ClassCallNode: Wrong number of arguments in the invocation of " + idMethod);
			System.exit(0);
		}
		for (int i = 0; i < argList.size(); i++)
			if (!(FOOLlib.isSubtype((argList.get(i)).typeCheck(), ((ParNode)p.get(i)).getSymType()))) {
				System.out.println(argList.get(i).typeCheck()+"--->"+p.get(i));
				System.out.println("ClassCallNode: Wrong type for " + (i + 1) + "-th argument in the invocation of " + idMethod);
				System.exit(0);
			}
		return t.getRet();
	}

	@Override
	public String codeGeneration() {
		String argCode = "";
		for (int i = argList.size() - 1; i >= 0; i--) {
			argCode += argList.get(i).codeGeneration();
		}

		String getAR = "";
		for (int i = 0; i < nestingLevel - classEntry.getNestinglevel(); i++) {  // differenza di nesting level tra dove sono e dove e' stato dichiarato l'oggetto
			getAR += "lw\n";
		}

		return "lfp\n" +   // CL: mi salvo sullo stack il frame pointer del chiamante
				argCode +  // allocazione parametri
				
				/* AL: recupero valore dell'ID1 (object pointer) dall'AR dove è dichiarato e lo lascio sullo stack */
				"push " + (classEntry.getOffset()) + "\n" +  // metto l'offset dell'oggetto sullo stack
				"lfp\n" +                             // metto fp (indirizzo di questo Frame Pointer) sullo stack
				getAR +                               // risalgo la catena statica ottenendo l'indirizzo dell'AR dove e' dichiarato l'oggetto
				"add\n" +                             // sommo l'offset all'indirizzo ottenuto
				"lw\n" +                              // AL: salvo sullo stack l'object pointer
				
				/* Recupero l'indirizzo del metodo ID2 a cui saltare (sommando il suo offset all'indirizzo della dispatch table riferita dall'object pointer) */
				"push " + (classEntry.getOffset()) + "\n" +        // metto l'offset dell'oggetto sullo stack
				"lfp\n" +                                   // metto fp (indirizzo di questo Frame Pointer) sullo stack
				getAR +                                     // risalgo la catena statica ottenendo l'indirizzo dell'AR dove e' dichiarato l'oggetto
				"add\n" +                                   // sommo l'offset dell'oggetto all'indirizzo ottenuto
				"lw\n" +                                    // metto sullo stack l'object pointer
				"lw\n" +                                    // metto sullo stack il dispatch pointer (NB)
				"push " + (methodEntry.getOffset()) + "\n" +  // metto sullo stack l'offset del metodo
				"add\n" +                                   // sommo l'offset del metodo al dispatch pointer
				"lw\n" +                                    // carico sullo stack l'indirizzo del codice del metodo
				
				"js\n";  // effettuo il salto (poppando dallo stack e salvando anche l'ip su ra)
	}
	
	@Override
	public String toPrint(String indent) {
		String argstr = "";
		for(Node parType : this.argList) {
			argstr += parType.toPrint(indent + " ");
		}

		return indent + "Class call " + this.idClass + "." + this.idMethod + " at nesting level " + this.nestingLevel + "\n" 
		+ this.classEntry.toPrint(indent + " ") 
		+ this.methodEntry.toPrint(indent + " ")
		+ argstr;
	}

}
