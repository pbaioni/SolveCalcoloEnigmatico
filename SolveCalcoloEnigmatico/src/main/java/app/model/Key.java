package app.model;

import java.util.TreeMap;


public class Key {


	private TreeMap<Integer, String> keyMap;

	private int validMerges = 0;


	public Key() {
		initKeyMap();
	}



	private void initKeyMap() {
		keyMap = new TreeMap<>();
		for (int i = 0; i <= 9; i++) {
			keyMap.put(i, "?");
		}
	}

	public boolean mergeResult(String numericalRelativeCrypto, String wordResult) {


		return false;
	}

	public boolean isCompatibleResult(String nrc, String wordResult) {
		boolean rval = true;

		return rval;
	}


	public int getValidMerges() {
		return validMerges;
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
		return "Key [keyMap=" + keyMap + ", validMerges=" + validMerges + "]";
	}

}
