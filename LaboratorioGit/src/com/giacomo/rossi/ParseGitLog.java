package com.giacomo.rossi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ParseGitLog {

	private static String executeGitLogCommand(String grep) {
		// esegui il comando git log --grep=grep
		String lineOutput = null;
		StringBuilder bOutput = new StringBuilder();
		System.out.println("Messaggi di commit che contengono la parola " + grep);
		try {
			// Execute command
			Runtime rt = Runtime.getRuntime();
			String[] commands = { "git", "log", "--grep=" + grep, "--pretty=oneline" };
			Process proc = rt.exec(commands);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

			// Read the output from the command
			while ((lineOutput = stdInput.readLine()) != null) {
				bOutput.append(lineOutput).append('\n');
			}

			// Read any errors from the attempted command
			while ((lineOutput = stdError.readLine()) != null) {
				System.out.println(lineOutput);
			}
		} catch (IOException io) {
			io.printStackTrace();
		}
		return bOutput.toString();
	}

	public static void parseCommitID(String grep) {
		String log = executeGitLogCommand(grep);
		for (String linea : log.lines().collect(Collectors.toList())) {
			System.out.println(linea.split(" ")[0]);
		}
	}
}
