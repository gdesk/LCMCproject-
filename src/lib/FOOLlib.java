package lib;

import java.util.ArrayList;
import java.util.HashMap;

import ast.*;
import ast.type.ArrowTypeNode;
import ast.type.BoolTypeNode;
import ast.type.ClassTypeNode;
import ast.type.EmptyTypeNode;
import ast.type.IntTypeNode;
import ast.type.RefTypeNode;

public class FOOLlib {
	
	/* valore iniziale $fp (frame pointer) */
	private final static int MEMSIZE = 0 ;

	private static int labCount=0; 

	private static int funLabCount=0; 

	private static String funCode="";	
	 
	private static ArrayList<ArrayList<String>> dispatchTables = new ArrayList<>();
	private static HashMap<String, String> superType = new HashMap<>(); //mappa l'id di classe nell' id della sua superclasse

	public static void addSuperType( String classId, String superTypeId ) {
		superType.put(classId, superTypeId);
	}
	
	public static void addDispatchTable(ArrayList<String> dispatchtable) {
		dispatchTables.add(dispatchtable);
	}

	public static ArrayList<String> getDispatchTable(int offset) {
		return dispatchTables.get(offset);
	}
	//valuta se il tipo "a" è <= al tipo "b", dove "a" e "b" sono tipi di base: int o bool
	public static boolean isSubtype (Node a, Node b) {
		Node nodeA = a;
		Node nodeB = b;
		Boolean checkPar = true;
		/*Covarianza es : String sottotipo di Object x tipo di ritorno*/
		/* Controvarianza es: Object sottotipo di String  per parametri*/
		if(a instanceof ArrowTypeNode && b instanceof ArrowTypeNode) {
			/* Preparazione per controllo la co-varianza sul tipo di ritorno*/
			nodeA = ((ArrowTypeNode)nodeA).getRet();
			nodeB = ((ArrowTypeNode)nodeB).getRet();
			
			/* Controllo su parametri : stesso numero */
			boolean checksize = checkSizeParameters(nodeA, nodeB);
			
			/* Controllo su parametri : controvarianza */
			boolean parcontrovariance = contravarianceParameters(nodeA, nodeB);
			
			checkPar = checksize && parcontrovariance;
		}
		
		if(nodeA instanceof RefTypeNode && nodeB instanceof RefTypeNode) {
			String refA = ((RefTypeNode)nodeA).getID();
			String refB = ((RefTypeNode)nodeB).getID();
			if(refA.equals(refB)) {
				return true;
			}else {
				isSuperType(refA, refB);
			}
		}
		
		boolean retType =  nodeA.getClass().equals(nodeB.getClass()) ||
						 ((nodeA instanceof EmptyTypeNode) && (nodeB instanceof RefTypeNode)) ||
						 ((nodeA instanceof BoolTypeNode) && (nodeB instanceof IntTypeNode));  
		boolean result = retType && checkPar;
		
		return result;
		
	}

	public static String freshLabel() {
		return "label"+(labCount++);
	}

	public static String freshFunLabel() {
		return "function"+(funLabCount++);
	}

	public static void putCode(String c) {
		funCode+="\n"+c; //aggiunge una linea vuota di separazione prima della funzione
	}

	public static String getCode() {
		return funCode; 
	}
	
	private static boolean checkSizeParameters(Node a, Node b) {
		int sizeA = ((ArrowTypeNode)a).getParList().size();
		int sizeB = ((ArrowTypeNode)b).getParList().size();
		
		return (sizeA == sizeB);
	}
	
	private static boolean contravarianceParameters(Node a, Node b) {
		ArrayList<Node> parListA = ((ArrowTypeNode)a).getParList();
		ArrayList<Node> parListB = ((ArrowTypeNode)b).getParList();
		
		for (int i = 0; i < parListA.size(); i++) {
			if(!isSubtype(parListB.get(i), parListA.get(i) )) {
				return false;
			}
			
		}
		return true;
	}
	
	/* Per raggiungibilità multipla in base al superType*/
	private static boolean isSuperType(String cl, String superCl){
		if(superType.get(cl) != null) {
			if( superType.get(cl).equals(superCl)){
				return true;
			} else {
				return isSuperType(superCl, superType.get(cl));
			}
		}
		return false;
		
	}
}
