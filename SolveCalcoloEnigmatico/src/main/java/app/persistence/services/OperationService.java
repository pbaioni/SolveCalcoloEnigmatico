package app.persistence.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.persistence.model.OperationDo;
import app.persistence.properties.OperationProperties;
import app.persistence.repo.OperationRepository;

@Service
public class OperationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperationService.class);

	@Autowired
	OperationRepository repository;

	@Autowired
	OperationProperties properties;

	public Iterable<OperationDo> getAllOperations() {
		return repository.findAll();
	}

	public Iterable<OperationDo> searchCrypto(String crypto) {
		return repository.findByCrypto(crypto);
	}

	public OperationDo getOperation(String operation) {
		return repository.findById(operation).get();
	}

	public OperationDo addOperation(String operation) {

		OperationDo operationToAdd = new OperationDo(operation);
		repository.save(operationToAdd);
		return operationToAdd;
	}

	public void fillDB(int threshold) {

		LOGGER.info("Calculating operations...");

		List<OperationDo> entities = new ArrayList<OperationDo>();
		int progression = 1;
		int additionResult;
		String addition;
		int sustractionResult;
		String sustraction;
		int multiplicationResult;
		String multiplication;
		int divisionResult;
		String division;

		for (int i = 0; i <= threshold; i++) {

			//filling database at each 1% of calculation
			if (i > threshold * progression / 100) {
				repository.saveAll(entities);
				entities.clear();
				LOGGER.info("Progression: " + progression + "%");
				progression += 1;
			}

			for (int j = 0; j <= threshold; j++) {

				// addition
				additionResult = j + i;
				addition = j + "+" + i + "=" + additionResult;
				entities.add(new OperationDo(addition));

				// sustraction
				if (j >= i) {
					sustractionResult = j - i;
					sustraction = j + "-" + i + "=" + sustractionResult;
					entities.add(new OperationDo(sustraction));
				}

				// multiplication
				multiplicationResult = j * i;
				if (multiplicationResult <= threshold) {
					multiplication = j + "*" + i + "=" + multiplicationResult;
					entities.add(new OperationDo(multiplication));
				}

				// division
				if (i != 0 && j % i == 0) {
					divisionResult = j / i;
					division = j + ":" + i + "=" + divisionResult;
					entities.add(new OperationDo(division));
				}
			}
		}

		LOGGER.info("Database filled");
	}

	public void dropDB() {
		repository.deleteAll();
		LOGGER.info("Database deleted");
	}
}
