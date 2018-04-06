package ast;

import java.util.ArrayList;

import ast.type.ClassTypeNode;
import lib.FOOLlib;

public class ClassNode implements DecNode {

	private ArrayList<MethodNode> methods;
	private ArrayList<FieldNode> fields;
	private String id;
	private ClassTypeNode symType;
	private STentry superEntry; 

	public ClassNode(final String id) {
		this.methods = new ArrayList<MethodNode>();
		this.fields = new ArrayList<FieldNode>();
		this.id=id;
	}

	public void addField(final FieldNode field) {
		this.fields.add(field);
	}

	public void addMethod(final MethodNode method) {
		this.methods.add(method);
	}
	
	public void setSuperEntry(STentry superEntry) {
		this.superEntry = superEntry;
	}
	
	public ArrayList<FieldNode> getFields(){
		return this.fields;
	}
	
	public ArrayList<MethodNode> getMethods(){
		return this.methods;
	}


	@Override
	public Node typeCheck() {
		for (Node method : methods) {
			method.typeCheck();
		}
		
		if (superEntry != null) {  // caso con ereditarieta'
			ClassTypeNode superType = (ClassTypeNode) superEntry.getType();
			ArrayList<Node> superFields = (superType).getFields();
			ArrayList<Node> fields = ((ClassTypeNode)symType).getFields();

			/* Ottimizzazione: checking piu' efficiente dei campi (non controllo quelli del padre di cui non sto facendo overriding) */
			for (Node field: this.fields) {
				int fieldOffset = ((FieldNode)field).getOffset();
				int index = -fieldOffset-1;
				
				if (index < superFields.size()) {
					if (!FOOLlib.isSubtype(fields.get(index), superFields.get(index))) {
						System.out.println("Incompatible type for field overriding");
						System.exit(0);
					}
				}
			}

			ArrayList<Node> superMethods = (superType).getMethods();
			ArrayList<Node> methods = ((ClassTypeNode)symType).getMethods();

			/* Ottimizzazione: checking piu' efficiente dei metodi (non controllo quelli del padre di cui non sto facendo overriding) */
			for (Node method: this.methods) {
				int methodOffset = ((MethodNode)method).getOffset();
				int index = methodOffset;
				
				if (index < superMethods.size()) {
					if (!FOOLlib.isSubtype(methods.get(index), superMethods.get(index))) {
						System.out.println("Incompatible type for method overriding");
						System.exit(0);
					}
				}
			}
		}
		return null;
	}

	@Override
	public String codeGeneration() {
		
		/* Creazione della Dispatch Table della classe */
		ArrayList<String> dispatchTable = new ArrayList<String>();
		if(!(superEntry == null)) {
			int seo = superEntry.getOffset();
			for(String method : FOOLlib.getDispatchTable(-seo-2)) {  // scorro la dispatch table del padre, copiandomi le label dei suoi metodi
				dispatchTable.add(method);
			}
		}
		for(Node m : methods) {
			MethodNode curMethod = ((MethodNode)m); 
			curMethod.codeGeneration();  // NB: non ritorna nulla, ma mette il codice del metodo nel segmento di memoria "code" (come accadeva per funzioni)
			if (curMethod.getOffset() < dispatchTable.size()) {
				// Overriding
				dispatchTable.set(curMethod.getOffset(), curMethod.getLabel());
			} else {
				// No overriding
				dispatchTable.add(curMethod.getLabel());
			}
		}
		FOOLlib.addDispatchTable(dispatchTable);

		String code = "lhp\n";            // salvo il valore di hp sullo stack (Dispatch Pointer della classe)
		
		/* Codice per mettere la Dispatch Table appena creata sullo Heap */
		for (String s: dispatchTable) {   // scorro tutti i metodi della Dispatch Table (per mettere le loro label sullo heap)
			code += "push " + s + "\n" +  // metto la label del metodo sullo stack
					"lhp\n" +             // carico il valore di hp sullo stack
					"sw\n" +              // metto all'indirizzo puntato da hp la label del metodo
					"lhp\n" +             // carico il valore di hp sullo stack (per incrementarlo di 1)
					"push 1\n" +          // 
					"add\n" +             // 
					"shp\n";              // aggiorno hp
		}
		return code;
	}

	@Override
	public String toPrint(String indent) {
		String fieldstr = "";
		String methstr = "";

		if(fields !=null) {
			for(FieldNode f: fields) {
				fieldstr += f.toPrint(indent + "  ");
			}
		}

		if(methods != null) 
			for(MethodNode m: methods) {
				methstr += m.toPrint(indent + "  ");
			}
		return indent + "Class:" + id + "\n" + fieldstr+ methstr+"\n"; 
	}

	@Override
	public ClassTypeNode getSymType() {
		return this.symType;
	}
	
	public void setSymType(ClassTypeNode node) {
		this.symType = node;
	}

}
