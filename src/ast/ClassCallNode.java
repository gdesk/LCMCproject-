package ast;

import java.util.ArrayList;

import ast.type.ArrowTypeNode;
import ast.type.ClassTypeNode;
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
		ArrayList<MethodNode> p = ((ClassTypeNode) classEntry.getType()).getMethods();
		int sizeOriginalMethod = -1;
		for(MethodNode met : p) {
			if(met.getID().equals(idMethod)) {
				sizeOriginalMethod = met.getParSize();
			}
		}
		if ( !(sizeOriginalMethod == argList.size()) ) {
			System.out.println("ClassCallNode: Wrong number of parameters in the invocation of "+idClass);
			System.exit(0);
		} 
		for (int i=0; i<argList.size(); i++) {
			Node parType = (argList.get(i)).typeCheck();
			Node decType = p.get(i);
			if ( (decType instanceof ArrowTypeNode && !(parType instanceof ArrowTypeNode))||!(FOOLlib.isSubtype( (argList.get(i)).typeCheck(), p.get(i)) ) ) {
				System.out.println("ClassCallNode: Wrong type for "+(i+1)+"-th parameter in the invocation of "+idClass);
				System.exit(0);
			} 
		}

		return methodEntry.getType();
	}

	@Override
	public String codeGeneration() {

		String argstr = "";
		if(argList!=null) {
			for (int i=argList.size()-1; i>=0; i--) {
				argstr += argList.get(i).codeGeneration();
			}
		}

		/* recupera id della classe  dall'AR*/
		String getAR=""; //recupero l'AR in cui è dichiarata la variable che sto usando
		for (int i=0;i<nestingLevel-classEntry.getNestinglevel();i++) {
			getAR+="lw\n"; //differenza di nesting level tra dove sono e la dichiarazione di "id"
		}
		return //allocazione della mia parte dell'AR della funzione che sto chiamando
				"lfp\n"+ //CL
				argstr + //allocazione valori parametri
				//Recupera FP ad AR dichiarazione funzione (Per settare l'access link)
				"lfp\n"+
				getAR+ //AL
				//codice per recuperare l'inidirizzo a cui saltare (stesso delle variabili)
				"push "+classEntry.getOffset()+"\n"+ //metto l'offset sullo stack
				"add\n"+
				"lw\n"+ 

 				//Recupera indir funzione (Per saltare al codice della funzione)
 				"lfp\n"+
 				getAR+ //risalgo la catena statica e ottengo l'indirizzo dell'AR della variabile
 				"push "+(methodEntry.getOffset()-1)+"\n"+ //metto l'offset sullo stack
 				"add\n"+
 				"lw\n"+ //carico sullo stack l'indirizzo a cui saltare
 				//effettuo il salto
 				"js\n";
	}

	@Override
	public String toPrint(String indent) {
		String argstr = "";
		for(Node parType : this.argList) {
			argstr += parType.toPrint(indent + " ");
		}

		return indent + "Class call" + this.idClass + "." + this.idMethod + " at nesting level " + this.nestingLevel + "\n" 
		+ this.classEntry.toPrint(indent + " ") 
		+ this.methodEntry.toPrint(indent + " ")
		+ argstr;
	}

}
