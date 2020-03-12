package app.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.utils.AppFiles;

public class CalcoloEnigmatico {

	private static final Logger LOGGER = LoggerFactory.getLogger(CalcoloEnigmatico.class);

	String path = "./src/main/resources/calcs/";
	
	private List<String> rows;

	private Key solutionKey;

	public CalcoloEnigmatico(String filename) {
		
		String calcPath = path + filename.trim();
		this.rows = AppFiles.getFileAsLines(calcPath);
		this.solutionKey = new Key();

	}

	@Override
	public String toString() {
		return "CalcoloEnigmatico [rows=" + rows + ", solutionKey=" + solutionKey + "]";
	}
	
	

}
