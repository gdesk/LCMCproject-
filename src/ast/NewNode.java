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
		if ( !(p.size() == argList.size()) ) {
			System.out.println("Wrong number of parameters in the invocation of "+id);
			System.exit(0);
		} 
		for (int i=0; i<argList.size(); i++) {
			Node parType = (argList.get(i)).typeCheck();
			Node decType = p.get(i);

			if ( (decType instanceof ArrowTypeNode && !(parType instanceof ArrowTypeNode))||!(FOOLlib.isSubtype(parType, p.get(i).getSymType()))) {
				System.out.println("NewNode :Wrong type for "+(i+1)+"-th parameter in the new "+id+"()");
				System.exit(0);
			} 
		}
		return new RefTypeNode(id);
	}

	@Override
	public String codeGeneration() {
		String code = "";
		if(this.argList != null) {

			for(Node argNode : this.argList) {
				code += "push "+ argNode.codeGeneration() + "\n";
			}

			for(Node argNode : this.argList) {
				code = "pop\n"+
						"shp\n" +
						"lhp\n" +				
						"push 1\n" +			
						"add\n"+
						//Recupera indir funzione (Per saltare al codice della funzione)
						"lfp\n"+
						(FOOLlib.MEMSIZE +entry.getOffset())+ //risalgo la catena statica e ottengo l'indirizzo dell'AR della variabile
						"lhp\n"+ //metto l'offset sullo stack
						"push 1\n" +			
						"add\n";

			}
		}

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
