package app.persistence.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.model.Key;

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

		LOGGER.info(clearOperation + " => " + rel_crypto);

		return rel_crypto;
	}
	

	public static List<Key> mergeKeys(List<Key> Keys, List<Key> KeysToMerge) {
		
		List<Key> mergedKeys = new ArrayList<Key>();

		for (Key key1 : Keys) {
			for (Key key2 : Keys) {
				if (key1.isCompatibleKey(key2)) {
					String row = key1.getResultMap().firstKey();
					String operation = key1.getResultMap().firstEntry().getValue();
					Key tempKey = new Key(row, operation);
					String rowToMerge = key2.getResultMap().firstKey();
					String operationToMerge = key2.getResultMap().firstEntry().getValue();
					tempKey.mergeResult(rowToMerge, operationToMerge);
					mergedKeys.add(tempKey);
				}
			}
		}
		
		return mergedKeys;
	}

}
