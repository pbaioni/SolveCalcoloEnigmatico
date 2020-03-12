package app.persistence.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperationHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OperationHelper.class);

	public static String ClearToCrypto(String clearOperation) {
		String compare = "";
		String rel_crypto = clearOperation;
		int count = 1;

		for (int i = 0; i < clearOperation.length(); i++) {

			String character = clearOperation.substring(i, i + 1);

			if (compare.lastIndexOf(character) == -1) {

				compare += character;
				String crypto = Character.toString((char) (96 + count));
				if ("+-*:=".lastIndexOf(character) == -1) {
					rel_crypto = rel_crypto.replaceAll(character, crypto);
					count++;
				}

			}
		}
		
		LOGGER.debug(clearOperation + " => " + rel_crypto);

		return rel_crypto;
	}

}
