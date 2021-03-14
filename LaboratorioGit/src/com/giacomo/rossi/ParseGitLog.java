package com.giacomo.rossi;

public class ParseGitLog {
	
	private String getLogString(String grep){
		// esegui il comando git log --grep=grep
		return "no string";
	}
	
	public void parseCommitID() {
		String log = getLogString("added");
		System.out.println("5ab6b2f");
	}
}
