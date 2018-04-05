package ast;

import java.util.ArrayList;

import ast.type.ArrowTypeNode;
import ast.type.ClassTypeNode;
import ast.type.RefTypeNode;
import lib.FOOLlib;

public class NewNode implements Node {

	private String id;
	private STentry entry; 
	private ArrayList<Node> argList;



	public NewNode(final String id, STentry entry) {
		this.id = id;
		this.entry = entry;
		this.argList = new ArrayList<>();
	}

	public void addArg(Node node) {
		this.argList.add(node);
	}

	@Override
	public Node typeCheck() {
		ArrayList<FieldNode> p = ((ClassTypeNode)entry.getType()).getFields();
		
		if (!(p.size() == argList.size())) {
			System.out.println("NewNode : Wrong number of parameters in the invocation of " + id);
			System.exit(0);
		}
		for (int i = 0; i < argList.size(); i++)
			if (!(FOOLlib.isSubtype((argList.get(i)).typeCheck(), p.get(i).getSymType()))) {
				System.out.println("Wrong type for " + (i + 1) + "-th argument in the invocation of " + id);
				System.exit(0);
			}
		return new RefTypeNode(id);
	}

	@Override
	public String codeGeneration() {
//		/* Mettiamo il valore di tutti i parametri sullo stack (in ordine di apparizione) */
//		String code = "";
//		if(!(this.argList.size() == 0)) {
//			for (int i = argList.size()-1; i >= 0 ; i--) {
//				code += argList.get(i).codeGeneration();
//			}
//		}
//		
//		/* Prendo i valori dei parametri uno per volta e lo carico sullo heap, incrementando hp ogni volta*/
//		for(Node argNode : this.argList) {
//			code += "lhp\n" +		//carico il valore di hp sullo stack
//					"sw\n"+			//metto all'indirizzo di hp (sullo heap) il parametro caricato sullo stack (dall'ultimo al primo)
//					"lhp\n"+		//carico il valore di hp sullo stack per aggiornarlo
//					"push 1\n" +			
//					"add\n"+		//incremento
//					"shp\n";		//aggiorno hp
//		}
//		
//		code += "push " + (FOOLlib.MEMSIZE+entry.getOffset()) + "\n"+ //carico sullo stack l'indirizzo al quale era stata dichiarata la classe
//				"lw\n" +		//carico sullo stack in dispatch pointer
//				"lhp\n" +		//carico sullo stack il valore di hp (diventerà 'object pointer della classe a cui faccio la new)
//				"sw\n"+			//metto all'indirizzo puntato da hp il dispatch pointer
//				"lhp\n"+		//lascio sullo stack il valore di hp
//				"lhp\n"+		//carico il valore di hp sullo stack per aggiornarlo
//				"push 1\n"+
//				"add\n"+		//incremento
//				"shp\n";		//aggiorno
//
//		return code;
		String code = "";
		for (Node param: argList) {  // mettiamo temporaneamente sullo stack il valore di tutti i parametri (in ordine di apparizione)
			code += param.codeGeneration();
		}

		/* Prendo il valore dei parametri (uno alla volta) e li carico sullo heap, incrementando hp dopo ogni singola copia */
		for (Node param: argList) {
			code += "lhp\n" +     // carico il valore di hp sullo stack
					"sw\n" +      // metto all'indirizzo di hp (sullo heap) il parametro che avevo caricato sullo stack (dall'ultimo al primo quindi)
					"lhp\n" +     // carico il valore di hp sullo stack (per aggiornarlo)
					"push 1\n" +  // 
					"add\n" +     // incremento
					"shp\n";      // aggiorno hp
		}

		code += "push " + (FOOLlib.MEMSIZE + entry.getOffset()) + "\n" +  // carico sullo stack l'indirizzo al quale era stata dichiarata la classe (offset della dichiaraz. della classe a lv. 0 sullo stack)
				"lw\n" +      // carico sullo stack il Dispatch Pointer
				"lhp\n" +     // carico sullo stack il valore di hp (diventera' l'Object Pointer dell'oggetto di cui sto facendo la New)
				"sw\n" +      // metto all'indirizzo puntato da hp (Object Pointer) il Dispatch Pointer
				"lhp\n" +     // lascio sullo stack il valore di hp (Object Pointer)
				"lhp\n" +     // carico il valore di hp sullo stack (per aggiornarlo)
				"push 1\n" +  // 
				"add\n" +     // incremento
				"shp\n";      // aggiorno hp
		return code;
	}

	@Override
	public String toPrint(String indent) {
		String argstr = "";
		for(Node argNode : this.argList) {
			argstr += argNode.toPrint(indent + " ");
		}
		return indent + "New node in class " + this.id + "\n"
		+argstr;
	}
}
