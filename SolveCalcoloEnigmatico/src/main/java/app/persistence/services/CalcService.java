package app.persistence.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.model.CalcoloEnigmatico;
import app.model.Key;
import app.persistence.model.OperationDo;
import app.persistence.repo.OperationRepository;

/**
 * Service layer. Specify transactional behavior and mainly delegate calls to
 * Repository.
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

		LOGGER.info("");
		LOGGER.info("Solving calc... [new]");

		// list containing the possible solution keys
		List<Key> keys = new ArrayList<Key>();
		boolean solveInit = true;

		for (String row : calcolo.getRows()) {

			LOGGER.info("Processing crypto row: " + row);

			for (OperationDo operation : operationRepository.findByCrypto(OperationHelper.ReduceToCrypto(row))) {

				List<Key> tempKeys = new ArrayList<Key>();
				String clearOperation = operation.getOperation();

				if (solveInit) {
					// it is the first step, we must add any possible solution
					Key tempKey = new Key(row, clearOperation);
					LOGGER.info("First iteration, adding key: " + tempKey);
					keys.add(tempKey);
				} else {
					// for the further steps, we try to merge the database results into the existing
					// keys
					// the right key (correct solution) should have one valid merge per step
					for (Key key : keys) {
						if (key.isCompatibleResult(row, clearOperation)) {
							LOGGER.info("Compatible result: ["+key.getKeyAsString()+" "+row+" "+clearOperation+"]");
							//cloning key
							Key tempKey = new Key();
							for (Map.Entry<Integer, String> entry : key.getKeyMap().entrySet()) {
								tempKey.getKeyMap().put(entry.getKey(), entry.getValue());
							}
							for (Map.Entry<String, String> entry : key.getResultMap().entrySet()) {
								tempKey.getResultMap().put(entry.getKey(), entry.getValue());
							}
							tempKey.mergeResult(row, clearOperation);
							tempKeys.add(tempKey);
						}
					}
					keys.addAll(tempKeys);
				}

			}

			// putting at the top of the list the key with the highest number of valid
			// merges (most probable solution)
			keys.sort(Comparator.comparing(Key::getMerges).reversed());

			// discarding the keys with lower number of valid merges
			keys = purgeKeys(keys);

			LOGGER.info("Found keys: [" + keys.size() + "]");

			if (solveInit) {
				// after the first step, we don't want to create new possible keys anymore
				solveInit = false;
			}

		}

		if(keys.size()==1) {
			calcolo.setSolutionKey(keys.get(0));
			calcolo.printSolution();
		}
		else {
			LOGGER.info("");
			LOGGER.info("No unique solution found...");
			LOGGER.info("Final keys: [" + keys.size() + "] " + keys);
			LOGGER.info("");
		}
		

	}

	private static List<Key> purgeKeys(List<Key> keys) {

		int bestValidMerges = keys.get(0).getMerges();
		List<Key> purgedKeys = new ArrayList<Key>();

		for (Key key : keys) {
			if (key.getMerges() >= bestValidMerges) {
				purgedKeys.add(key);
			}

		}

		return purgedKeys;

	}

}
