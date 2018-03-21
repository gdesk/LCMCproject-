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
		if(this.methods !=null) {
			for(MethodNode meth : this.methods) {
				meth.typeCheck();
			}
		}
		
		if(this.superEntry != null) {
			ArrayList<FieldNode> parentFields = ((ClassNode)this.superEntry.getType()).getFields();
			ArrayList<MethodNode> parentMethods = ((ClassNode)this.superEntry.getType()).getMethods();
			
			for(int i = 0; i < parentFields.size(); i++) {
				FieldNode field = this.fields.get(i);
				FieldNode parentField = parentFields.get(i);
				
				/* calcolo la posizione in base all'offset*/
				int position = -parentField.getOffset()-1;
			
				/* Controllo di correttezza solo per i campi su cui è fatto overriding */
				if(position < parentFields.size()) {
					FOOLlib.isSubtype(field, parentField);
				}
				
			}
			
			for (int i = 0; i < parentMethods.size(); i++) {
				MethodNode method = this.methods.get(i);
				MethodNode parentMethod = parentMethods.get(i);
				
				/* calcolo la posizione in base all'offset*/
				int position= parentMethod.getOffset();
				
				/* Controllo di correttezza solo per i metodi su cui è fatto overriding*/
				if(position < parentMethods.size()) {
					FOOLlib.isSubtype(method, parentMethod);
				}
			}		
		}
		
		return null;
	}

	@Override
	public String codeGeneration() {
		
		/* Creazione della dispatch table della classe*/
		ArrayList<String> dispatchTable = new ArrayList<>();
		if(superEntry != null) {
			int classOffset = superEntry.getOffset();
			dispatchTable.addAll(FOOLlib.getDispatchTable(-classOffset-2));
		}
		
		for(MethodNode met : this.methods) {
			met.codeGeneration(); //non ritorna nulla, ma mette il codice del metodo nel segmento code.
			if(met.getOffset() < dispatchTable.size()) {
				//Override
				dispatchTable.set(met.getOffset(), met.getLabel());
			}else {
				//Non Override
				dispatchTable.add(met.getLabel());
			}
		}
		FOOLlib.addDispatchTable(dispatchTable);
		
		String code = "lhp\n"; //salvo il valore di $hp sullo stack (Dispatch Pointer della classe)
		
		//Codice per mettere la dispatch table appena creata sullo heap
		for(String s : dispatchTable) { 	//scorro per mettere label sullo heap
			code += "push " + s + "\n" + 	//metto la label del metodo sullo stack
					"lhp\n" + 				//carico il valore di $hp sullo stack
					"sw\n" +				//metto all'indirizzo puntato da hp la label del metodo
					"lhp" +					//carico il valore di hp sullo stack per incrementarlo di 1
					"push 1\n" +			
					"add\n"+
					"shp\n";				//aggiorno hp
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
