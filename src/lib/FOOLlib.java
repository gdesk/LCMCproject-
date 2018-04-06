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
	public static int MEMSIZE = 10000;

	private static int labCount=0; 
	private static int funLabCount=0; 
	private static int methodCount =0;

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
	
	public static boolean isSubtype (Node a, Node b) {
		Node nodeA = a;
		Node nodeB = b;
		Boolean checkPar = true;
		
		/* Covarianza esempio : String sottotipo di Object x tipo di ritorno*/
		/* Controvarianza esempio: Object sottotipo di String  per parametri*/
		if(a instanceof ArrowTypeNode && b instanceof ArrowTypeNode) {
			/* Preparazione per controllo la co-varianza sul tipo di ritorno*/
			nodeA = ((ArrowTypeNode)nodeA).getRet();
			nodeB = ((ArrowTypeNode)nodeB).getRet();

			/* Controllo su parametri : stesso numero */
			boolean checksize = checkSizeParameters(a, b);

			/* Controllo su parametri : controvarianza */
			boolean parcontrovariance = contravarianceParameters(a, b);

			checkPar = checksize && parcontrovariance;
		}

		if(nodeA instanceof RefTypeNode && nodeB instanceof RefTypeNode) {
			String refA = ((RefTypeNode)nodeA).getID();
			String refB = ((RefTypeNode)nodeB).getID();
			if(refA.equals(refB)) {
				return true;
			}else {
				isSuperType(refB, refA);
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
		funCode+="\n"+c; 
	}

	public static String getCode() {
		return funCode; 
	}

	public static Node lowestCommonAncestor(Node a, Node b) {
		if(a instanceof EmptyTypeNode) {
			return b;
		}

		if(b instanceof EmptyTypeNode) {
			return a;
		}

		/* verifico ereditarietà multipla*/
		if(a instanceof RefTypeNode && b instanceof RefTypeNode) {
			String sa = ((RefTypeNode)a).getID();
			while(sa != null) {
				RefTypeNode ancestor = new RefTypeNode(sa); 
				if(FOOLlib.isSubtype(b, ancestor)) {
					return ancestor;
				}
				sa = superType.get(sa);
			}
		}

		if(a instanceof IntTypeNode || b instanceof IntTypeNode) {
			return new IntTypeNode();
		}

		if(a instanceof BoolTypeNode && b instanceof BoolTypeNode) {
			return new BoolTypeNode();
		}
		
		if(a instanceof ArrowTypeNode && b instanceof ArrowTypeNode && checkSizeParameters(a, b)) {
			ArrowTypeNode functionalA = (ArrowTypeNode) a;
			ArrowTypeNode functionalB = (ArrowTypeNode) b;
			
			ArrayList<Node> parlistA = functionalA.getParList();
			ArrayList<Node> parlistB = functionalB.getParList();
			
			Node ret = lowestCommonAncestor(functionalA.getRet(), functionalB.getRet());
			 if(ret != null) {
				 ArrayList<Node> parlist = new ArrayList<Node>();
				 
					//Controllo sul tipo dei parametri
					for(int i=0; i<parlistA.size(); i++) {
						if(isSubtype(parlistA.get(i),parlistB.get(i))) {
							parlist.add(parlistA.get(i));
						} else {
							if(isSubtype(parlistB.get(i),parlistA.get(i))) {
								parlist.add(parlistB.get(i));
							} else {
								return null;
							}
						}
					}
					return new ArrowTypeNode(parlist, ret);
			 }
		}
	
		return null;
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
	public static String freshMethodLabel() {
		return "method" + (methodCount++);
	}

}

