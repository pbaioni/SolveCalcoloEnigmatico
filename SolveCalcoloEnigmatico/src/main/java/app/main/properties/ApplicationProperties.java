package app.main.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="application")
public class ApplicationProperties {
	
	private boolean solveAllCalcs;
	private String calcPath;
	

	
	public ApplicationProperties() {
		
	}

	public boolean getSolveAllCalcs() {
		return solveAllCalcs;
	}


	public void setSolveAllCalcs(boolean solveAllCalcs) {
		this.solveAllCalcs = solveAllCalcs;
	}

	public String getCalcPath() {
		return calcPath;
	}

	public void setCalcPath(String calcPath) {
		this.calcPath = calcPath;
	}

}
