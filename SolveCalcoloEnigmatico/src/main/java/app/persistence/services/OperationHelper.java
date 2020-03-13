package app.persistence.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperationHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperationHelper.class);

	public static String ReduceToCrypto(String clearOperation) {
		
		String compare = "";
		
		//starting from an upper case string is mandatory because the algorithm 
		//can't successfully substitute to and from the same set of letters
		String operation = clearOperation.toUpperCase();
		
		String rel_crypto = operation;
		int count = 1;

		for (int i = 0; i < operation.length(); i++) {

			String character = operation.substring(i, i + 1);

			if (compare.lastIndexOf(character) == -1) {

				compare += character;
				String crypto = Character.toString((char) (96 + count));
				if ("+-*:=".lastIndexOf(character) == -1) {
					rel_crypto = rel_crypto.replaceAll(character, crypto);
					count++;
				}

			}
		}

		LOGGER.debug("Crypto reduction: " + clearOperation + " => " + rel_crypto);

		return rel_crypto;
	}

	public static String purgeSpecialCharacters(String chainToPurge) {
		
		String purged = "";

		int count = 1;

		for (int i = 0; i < chainToPurge.length(); i++) {

			String character = chainToPurge.substring(i, i + 1);

			if ("+-*:=".lastIndexOf(character) == -1) {
				purged += character;
			}
		}

		LOGGER.debug("Purge: " + chainToPurge + " => " + purged);

		return purged;
	}

}
