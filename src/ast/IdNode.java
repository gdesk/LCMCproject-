package ast;

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
	public String toPrint(String s) {
		return s+"Id:" + id + " at nestinglevel "+nestingLevel+"\n" + 
				entry.toPrint(s+"  ") ;  
	}

	@Override
	public Node typeCheck() {
		if (entry.getType() instanceof ArrowTypeNode) {
			System.out.println("Wrong usage of function identifier");
			System.exit(0);
		} 
		return entry.getType();
	}

	@Override
	public String codeGeneration() {
		String getAR=""; //recupero l'AR in cui � dichiarata la variable che sto usando
		for (int i=0;i<nestingLevel-entry.getNestinglevel();i++) {
			getAR+="lw\n"; //differenza di nesting level tra dove sono e la dichiarazione di "id"
		}
		return "push "+entry.getOffset()+"\n"+ //metto l'offset sullo stack
		"lfp\n"+getAR+ //risalgo la catena statica e ottengo l'indirizzo dell'AR della variabile	 
		"add\n"+
		"lw\n"; //carico sullo stack il valore all'indirizzo ottenuto
	}
}  