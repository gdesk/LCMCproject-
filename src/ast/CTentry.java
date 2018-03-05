package ast;

import java.util.HashMap;

public class CTentry {
	
	private HashMap<String, HashMap<String,STentry>> classTable;
	
	public CTentry(HashMap<String, HashMap<String,STentry>> classTable) {
		this.classTable=classTable;
	}

}

