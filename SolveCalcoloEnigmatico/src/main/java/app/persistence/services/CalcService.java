package app.persistence.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.model.CalcoloEnigmatico;
import app.model.Key;
import app.persistence.model.OperationDo;
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
		
		List<Key> firstKeys = new ArrayList<Key>();
		String firstRow = calcolo.getRows().get(0);
		for(OperationDo operation : operationRepository.findByCrypto(OperationHelper.ReduceToCrypto(firstRow))) {
			firstKeys.add(new Key(firstRow, operation.getOperation()));
		}
		
		LOGGER.info("FirstKeys:\n"+firstKeys);
		
		List<Key> secondKeys = new ArrayList<Key>();
		String secondRow = calcolo.getRows().get(1);
		for(OperationDo operation : operationRepository.findByCrypto(OperationHelper.ReduceToCrypto(secondRow))) {
			secondKeys.add(new Key(secondRow, operation.getOperation()));
		}
		
		LOGGER.info("SecondKeys:\n"+secondKeys);
		
		List<Key> thirdKeys = new ArrayList<Key>();
		String thirdRow = calcolo.getRows().get(2);
		for(OperationDo operation : operationRepository.findByCrypto(OperationHelper.ReduceToCrypto(thirdRow))) {
			thirdKeys.add(new Key(thirdRow, operation.getOperation()));
		}
		
		LOGGER.info("ThirdKeys:\n"+thirdKeys);
		
		//List<Key> firstMergeKeys = OperationHelper.mergeKeys(firstKeys, secondKeys);
		
		//List<Key> secondMergeKeys = OperationHelper.mergeKeys(firstMergeKeys, thirdKeys);
		
		//LOGGER.info("Final keys:\n" + secondMergeKeys.toString());

		
	}
	

}
