package ast;

import java.util.ArrayList;

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
		ArrayList<Node> p = ((ClassTypeNode)entry.getType()).getFields();
		
		if (!(p.size() == argList.size())) {
			System.out.println("NewNode : Wrong number of parameters in the invocation of " + id);
			System.exit(0);
		}
		for (int i = 0; i < argList.size(); i++)
			if (!(FOOLlib.isSubtype((argList.get(i)).typeCheck(),(p.get(i))))) {
				System.out.println((argList.get(i)).typeCheck() + "---->"+ p.get(i) );
				System.out.println("NewNode: Wrong type for " + (i + 1) + "-th argument in the invocation of " + id);
				System.exit(0);
			}
		return new RefTypeNode(id);
	}

	@Override
	public String codeGeneration() {

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
