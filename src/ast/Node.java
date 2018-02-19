package ast;

/**
 * Interface for node of Abstract Syntact Tree.
 * 
 * @author Chiara Volonnino
 * @author Giulia Lucchi
 *
 */
public interface Node {
	
	/**
	 * make a type checking of Node.
	 * 
	 * @return Node	type of expression or "null" if the node is a declaration
	 */
	Node typeCheck(); 
	
	/**
	 * make a code generation.
	 * 
	 * @return String	code related to the node.
	 */
	String codeGeneration();
	
	/**
	 * AST's print
	 * 
	 * @param indent indent of AST's print
	 * @return String	print of a AST's part	
	 */
	String toPrint(String indent);
  
}  	
