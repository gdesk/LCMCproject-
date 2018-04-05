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
	
	public void addFields(final ArrayList<FieldNode> fields) {
		this.fields.addAll(fields);
	}

	public void addMethods( final ArrayList<MethodNode> methods) {
		this.methods.addAll(methods);
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
//		if(this.methods !=null) {
//			for(MethodNode meth : this.methods) {
//				meth.typeCheck();
//			}
//		}
//		
//		if(this.superEntry != null) {
//			ArrayList<FieldNode> parentFields = ((ClassTypeNode)this.superEntry.getType()).getFields();
//			ArrayList<MethodNode> parentMethods = ((ClassTypeNode)this.superEntry.getType()).getMethods();
//			
//			for(int i = 0; i < parentFields.size(); i++) {
//				FieldNode field = this.fields.get(i);
//				FieldNode parentField = parentFields.get(i);
//				
//				/* calcolo la posizione in base all'offset*/
//				int position = -parentField.getOffset()-1;
//			
//				/* Controllo di correttezza solo per i campi su cui è fatto overriding */
//				if(position < parentFields.size()) {
//					FOOLlib.isSubtype(field, parentField);
//				}
//				
//			}
//			
//			for (int i = 0; i < parentMethods.size(); i++) {
//				MethodNode method = this.methods.get(i);
//				MethodNode parentMethod = parentMethods.get(i);
//				
//				/* calcolo la posizione in base all'offset*/
//				int position= parentMethod.getOffset();
//				
//				/* Controllo di correttezza solo per i metodi su cui è fatto overriding*/
//				if(position < parentMethods.size()) {
//					FOOLlib.isSubtype(method, parentMethod);
//				}
//			}		
//		}
//		
//		return null;
		for (Node method : methods) {
			method.typeCheck();
		}
		if (superEntry != null) {  // caso con ereditarieta'
			ClassTypeNode fatherType = (ClassTypeNode) superEntry.getType();
			ArrayList<FieldNode> fatherFieldsType = (fatherType).getFields();
			ArrayList<FieldNode> childFieldsType = ((ClassTypeNode)symType).getFields();

			/* Vecchio controllo senza ottimizzazione (scorro tutti i campi del padre)
			for (int i = 0; i < fatherFieldsType.size(); i++) {
				if (!FOOLlib.isSubtype(childFieldsType.get(i), fatherFieldsType.get(i))) {
					System.out.println("Incompatible type for field overriding");
					System.exit(0);
				}
			}
			*/

			/* Ottimizzazione: checking piu' efficiente dei campi (non controllo quelli del padre di cui non sto facendo overriding) */
			for (Node childField: fields) {
				int fieldOffset = ((FieldNode)childField).getOffset();
				int index = -fieldOffset-1;
				if (index < fatherFieldsType.size()) {
					if (!FOOLlib.isSubtype(childFieldsType.get(index), fatherFieldsType.get(index))) {
						System.out.println("Incompatible type for field overriding");
						System.exit(0);
					}
				}
			}

			ArrayList<MethodNode> fatherMethodsType = (fatherType).getMethods();
			ArrayList<MethodNode> childMethodsType = ((ClassTypeNode)symType).getMethods();

			/* Vecchio controllo senza ottimizzazione (scorro tutti i campi del padre)
			for (int i = 0; i < fatherMethodsType.size(); i++) {
				if (!FOOLlib.isSubtype(childMethodsType.get(i), fatherMethodsType.get(i))) {
					System.out.println("Incompatible value for method");
					System.exit(0);
				}
			}
			*/

			/* Ottimizzazione: checking piu' efficiente dei metodi (non controllo quelli del padre di cui non sto facendo overriding) */
			for (Node childMethod: methods) {
				int methodOffset = ((MethodNode)childMethod).getOffset();
				int index = methodOffset;
				if (index < fatherMethodsType.size()) {
					if (!FOOLlib.isSubtype(childMethodsType.get(index), fatherMethodsType.get(index))) {
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
