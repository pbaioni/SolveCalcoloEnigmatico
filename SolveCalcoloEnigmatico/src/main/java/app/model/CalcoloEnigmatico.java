package app.model;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.utils.AppFiles;

public class CalcoloEnigmatico {

	private static final Logger LOGGER = LoggerFactory.getLogger(CalcoloEnigmatico.class);

	private List<String> rows;

	private Key solutionKey;

	public CalcoloEnigmatico(String filepath) {

		String calcPath = filepath.trim();
		this.rows = AppFiles.getFileAsLines(calcPath);
		this.solutionKey = new Key();

	}

	public List<String> getRows() {
		return rows;
	}

	public void setRows(List<String> rows) {
		this.rows = rows;
	}

	public Key getSolutionKey() {
		return solutionKey;
	}

	public void setSolutionKey(Key solutionKey) {
		this.solutionKey = solutionKey;
	}

	public void printSolution() {
		
		LOGGER.info("");
		LOGGER.info("Solution:");
		for(Map.Entry<String, String> entry : this.solutionKey.getResultMap().entrySet()) {
			LOGGER.info(entry.getValue());
		}
		LOGGER.info("");

	}

	@Override
	public String toString() {
		return "CalcoloEnigmatico [rows=" + rows + ", solutionKey=" + solutionKey + "]";
	}

}
