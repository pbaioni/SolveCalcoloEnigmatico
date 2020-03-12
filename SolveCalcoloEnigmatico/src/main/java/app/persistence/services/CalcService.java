package app.persistence.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.model.CalcoloEnigmatico;
import app.persistence.repo.OperationRepository;

/** 
 * Service layer.
 * Specify transactional behavior and mainly
 * delegate calls to Repository.
 */
@Component
public class CalcService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CalcService.class);
	
	@Autowired
	private OperationRepository operationRepository;
	

	public CalcService() {
		

		
	}

	public CalcoloEnigmatico loadCalc(String filename) {
		
		CalcoloEnigmatico calcolo = new CalcoloEnigmatico(filename);
		
		LOGGER.info("Calc loaded:\n" + calcolo.toString());
		
		return calcolo;
		
	}
	
	
	public void solveCalc(CalcoloEnigmatico calcolo) {
		
		
		LOGGER.info("Solving calc...");
		
	}
	

}
