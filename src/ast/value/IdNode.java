package ast.value;

import ast.Node;
import ast.STentry;
import ast.type.ArrowTypeNode;
import ast.type.ClassTypeNode;

/**
 * This class describes the declaration of variable.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public class IdNode implements Node {

	private String id;
	private int nestingLevel;
	private STentry entry;

	public IdNode (final String id, final STentry entry, final int nestingLevel) {
		this.id=id;
		this.entry=entry;
		this.nestingLevel=nestingLevel;
	}

	@Override
	public String toPrint(String indent) {
		return indent+"Id:" + id + " at nestinglevel "+nestingLevel+"\n" + 
				entry.toPrint(indent+"  ") ;  
	}

	@Override
	public Node typeCheck() {
		if(this.entry.isMethod()) {
			System.out.println("Incompatible type: it's a method");
			System.exit(0);
		}
		if(this.entry.getType() instanceof ClassTypeNode) {
			System.out.println("Incompatible type of id (ClassTypeNode)");
			System.exit(0);
		}
		
		return entry.getType();
	}

	@Override
	public String codeGeneration() {
//		
//		String getAR=""; //recupero l'AR in cui è dichiarata la variable che sto usando
//		for (int i=0;i<nestingLevel-entry.getNestinglevel();i++) {
//			getAR+="lw\n"; //differenza di nesting level tra dove sono e la dichiarazione di "id"
//		}
//		
//		if(!(entry.getType() instanceof ArrowTypeNode)) {	
//			return 	"push "+entry.getOffset()+"\n"+ //metto l'offset sullo stack
//					"lfp\n"+
//					getAR+ //risalgo la catena statica e ottengo l'indirizzo dell'AR della variabile	 
//					"add\n"+
//					"lw\n"; //carico sullo stack il valore all'indirizzo ottenuto
//		}else {
//
//			return  "push "+entry.getOffset()+"\n"+ 
//					"lfp\n"+
//					getAR+ 	 
//					"add\n"+
//					"lw\n"+
//					"push "+(entry.getOffset()-1)+"\n"+ 
//					"lfp\n"+
//					getAR+ 	 
//					"add\n"+
//					"lw\n"; 
//		}
		String getAR = "";
		for (int i=0; i < nestingLevel - entry.getNestinglevel(); i++) {  // differenza di nesting level tra dove sono e la dichiarazione di "id"
			getAR += "lw\n";
		}
		String ritorno = "push " + entry.getOffset() + "\n" +  // metto l'offset sullo stack
						"lfp\n" +                              // carico il frame pointer sullo stack
						getAR +                                // risalgo la catena statica e ottengo l'indirizzo dell'AR piu' recente dove e' dichiarato "id"
						"add\n" +                              // sommo l'offset all'indirizzo ottenuto
						"lw\n";                                // salvo sullo stack il valore della variabile
						                                       // (oppure l'indirizzo (FramePointer) dell'AR dove e' dichiarata la funzione)
						                                       // (oppure l'indirizzo (ObjectPointer) dell'oggetto sullo heap)
		
		if (entry.getType() instanceof ArrowTypeNode) {
			ritorno += "push " + (entry.getOffset()-1) + "\n" + // metto l'offset-1 sullo stack
						"lfp\n" +                               // carico frame pointer sullo stack
						getAR +                                 // risalgo la catena statica e ottengo l'indirizzo dell'AR piu' recente dove e' dichiarato "id"
						"add\n" +                               // sommo l'offset-1 all'indirizzo ottenuto
						"lw\n";                                 // salvo sullo stack l'indirizzo del codice della funzione
		}
		return ritorno;
	}
}  