package app.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import app.main.properties.ApplicationProperties;
import app.persistence.services.OperationService;

@Service
public class Application implements ApplicationRunner, DisposableBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
	ApplicationProperties properties;

	@Autowired
	OperationService operationService;

	public void init() {
		// app property example
		LOGGER.info("App property: " + properties.getAppProperty());

		LOGGER.info("Application initialized");

	}

	public void start() {

		LOGGER.info("Application started");
		
	}

	public void stop() {
		LOGGER.info("Stopping Application");
		LOGGER.info("Application stopped");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		init();
		start();
	}

	@Override
	public void destroy() throws Exception {
		LOGGER.info("Destroying Application");
		stop();
	}

}
