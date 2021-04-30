package app.main;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import app.commands.controller.CommandController;
import app.main.properties.ApplicationProperties;
import app.model.CalcoloEnigmatico;
import app.persistence.services.CalcService;
import app.persistence.services.OperationService;

@Service
public class Application implements ApplicationRunner, DisposableBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
	ApplicationProperties properties;

	@Autowired
	OperationService operationService;

	@Autowired
	CalcService calcService;
	
	@Autowired
	CommandController commands;

	Boolean solveAllCalcs;

	List<String> calcFilter;

	public void init() {

		LOGGER.info("Application initialized");
		solveAllCalcs = properties.getSolveAllCalcs();
		calcFilter = new ArrayList<String>();
		calcFilter.add("calc4.dat");
	}

	public void start() {

		LOGGER.info("Application started");

		if (solveAllCalcs) {
			LOGGER.info("Solving all calcs");
		}

		// looking for calc files
		File dir = new File(properties.getCalcPath());
		File[] calcFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".dat");
			}
		});

		// load and solve problems
		for (File calcFile : calcFiles) {
			if (solveAllCalcs || calcFilter.contains(calcFile.getName())) {
				CalcoloEnigmatico calcolo = calcService.loadCalc(calcFile.getName());
				calcService.solveCalc(calcolo);
			}
		}
		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		init();
		start();
		commands.manageCommands("q");

	}

	public void stop() {

		LOGGER.info("Stopping Application");
		LOGGER.info("Application stopped");

	}
	
	@Override
	public void destroy() throws Exception {
		LOGGER.info("Destroying Application");
		stop();
	}

}
