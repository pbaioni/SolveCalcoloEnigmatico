package app.persistence.services;

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
	
	public void fillDB() {
				
		LOGGER.info("Database virtually filled");
	}

	public Iterable<OperationDo> getAllOperations() {
		return repository.findAll();
	}

	public OperationDo getOperation(String cryptoOperation) {
		return repository.findByCryptoOperation(cryptoOperation);
	}

	public OperationDo createOperation(String cryptoOperation) {
		return repository.save(new OperationDo(cryptoOperation));
	}

	public OperationDo createOperation(String cryptoOperation, List<String> operations) {
		return repository.save(new OperationDo(cryptoOperation, operations));
	}
	
}
