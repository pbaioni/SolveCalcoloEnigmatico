package app.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.model.helper.OperationHelper;

public class Key {

	private static final Logger LOGGER = LoggerFactory.getLogger(Key.class);

	private TreeMap<Integer, String> keyMap;

	private LinkedHashMap<String, String> resultMap = new LinkedHashMap<String, String>();

	public Key() {
		initKeyMap();
	}

	public Key(String row, String operation) {

		initKeyMap();
		mergeResult(row, operation);

	}

	private void initKeyMap() {
		keyMap = new TreeMap<>();
		for (int i = 0; i <= 9; i++) {
			keyMap.put(i, "?");
		}
	}

	public boolean mergeResult(String row, String operation) {

		String purgedRow = OperationHelper.purgeSpecialCharacters(row);
		String purgedOperation = OperationHelper.purgeSpecialCharacters(operation);

		if (isCompatibleResult(purgedRow, purgedOperation)) {
			LOGGER.debug("Merging [" + row + "=" + operation + "],  starting key:");
			LOGGER.debug(getKeyAsString());
			for (int i = 0; i < purgedOperation.length(); i++) {
				keyMap.put(Integer.parseInt(String.valueOf(purgedOperation.charAt(i))),
						String.valueOf(purgedRow.charAt(i)));
			}

			resultMap.put(row, operation);

			LOGGER.debug("End key:");
			LOGGER.debug(getKeyAsString());

			return true;
		}
		return false;
	}

	public boolean isCompatibleResult(String crypto, String clear) {
		boolean rval = true;
		String purgedCrypto = OperationHelper.purgeSpecialCharacters(crypto);
		String purgedClear = OperationHelper.purgeSpecialCharacters(clear);

		if (purgedCrypto.length() == purgedClear.length()) {
			for (int i = 0; i < purgedClear.length(); i++) {
				String letter = String.valueOf(purgedCrypto.charAt(i));
				String mapValue = keyMap.get(Integer.parseInt(String.valueOf(purgedClear.charAt(i))));

				if ((!mapValue.equals(letter) && !mapValue.equals("?"))
						|| (!mapValue.equals(letter) && keyMap.containsValue(letter))) {
					if (clear.startsWith("109")) {
						LOGGER.debug(
								"Incompatible result, merge aborted [" + getKeyAsString() + " " + crypto + " " + clear);
					}
					rval = false;
					break;
				}
			}
		} else {
			LOGGER.error("Incompatible result: length error");
			rval = false;
		}
		return rval;
	}

	public int getMerges() {
		return resultMap.size();
	}
	
	public Key cloneKey() {
		
		Key clonedKey = new Key();
		for (Map.Entry<Integer, String> entry : this.getKeyMap().entrySet()) {
			clonedKey.getKeyMap().put(entry.getKey(), entry.getValue());
		}
		for (Map.Entry<String, String> entry : this.getResultMap().entrySet()) {
			clonedKey.getResultMap().put(entry.getKey(), entry.getValue());
		}
		
		return clonedKey;
	}

	public TreeMap<Integer, String> getKeyMap() {
		return keyMap;
	}

	public void setKeyMap(TreeMap<Integer, String> keyMap) {
		this.keyMap = keyMap;
	}

	public LinkedHashMap<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(LinkedHashMap<String, String> resultMap) {
		this.resultMap = resultMap;
	}

	public String getKeyAsString() {
		StringBuilder builder = new StringBuilder();
		for (String value : keyMap.values()) {
			builder.append(value);
		}

		return builder.toString();
	}

	@Override
	public String toString() {
		return "Key [keyMap=" + keyMap + ", resultMap=" + resultMap + "]";
	}

}
