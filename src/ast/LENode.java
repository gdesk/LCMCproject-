package ast;

import lib.FOOLlib;

public class LENode implements Node {
	 private Node left;
		private Node right;

		public LENode (final Node left, final Node right) {
			this.left=left;
			this.right=right;
		}

		@Override
		public String toPrint(String indent) {
			return indent+"<=\n" + left.toPrint(indent+"  ")  
			+ right.toPrint(indent+"  ") ; 
		}

		@Override
		public Node typeCheck() {
			if ( ! ( FOOLlib.isSubtype(left.typeCheck(), new IntTypeNode()) &&
					FOOLlib.isSubtype(right.typeCheck(), new IntTypeNode()) ) ) {
				System.out.println("Non integers in LE");
				System.exit(0);	
			}
			return new IntTypeNode();
		}

		@Override
		public String codeGeneration() {
			return right.codeGeneration()+
					left.codeGeneration()+
					"bleq\n";
		}
}
