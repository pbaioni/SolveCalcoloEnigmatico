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

	public void fillDB() {

		LOGGER.info("Starting filling database");
		
		List<OperationDo> entities = new ArrayList<OperationDo>();
		
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {

				// addition
				int additionResult = j + i;
				String addition = j + "+" + i + "=" + additionResult;
				entities.add(new OperationDo(addition));

				// sustraction
				if (j >= i) {
					int sustractionResult = j - i;
					String sustraction = j + "-" + i + "=" + sustractionResult;
					entities.add(new OperationDo(sustraction));
				}

				// multiplication
				if (i < 100 && j < 100) {
					int multiplicationResult = j * i;
					String multiplication = j + "*" + i + "=" + multiplicationResult;
					entities.add(new OperationDo(multiplication));
				}

				// division
				if (i != 0 && j % i == 0) {
					int divisionResult = j / i;
					String division = j + ":" + i + "=" + divisionResult;
					entities.add(new OperationDo(division));
				}

			}
		}
		
		repository.saveAll(entities);
		
		LOGGER.info("Database filled");
	}

}
