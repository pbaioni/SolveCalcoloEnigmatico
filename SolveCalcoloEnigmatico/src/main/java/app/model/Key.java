package app.model;

import java.util.TreeMap;


public class Key {

	

	private TreeMap<Integer, String> keyMap;
	
	private TreeMap<String, String> resultMap = new TreeMap<String, String>();


	public Key() {
		initKeyMap();
	}
	
	public Key(String row, String operation) {
		
		initKeyMap();
		mergeResult(row, operation);
		resultMap.put(row, operation);

	}



	private void initKeyMap() {
		keyMap = new TreeMap<>();
		for (int i = 0; i <= 9; i++) {
			keyMap.put(i, "?");
		}
	}

	public boolean mergeResult(String row, String operation) {

		resultMap.put(row, operation);
		return true;
	}

	public boolean isCompatibleKey(Key otherKey) {
		boolean rval = true;

		return rval;
	}


	public int getMerges() {
		return resultMap.size();
	}
	
	

	public TreeMap<Integer, String> getKeyMap() {
		return keyMap;
	}

	public void setKeyMap(TreeMap<Integer, String> keyMap) {
		this.keyMap = keyMap;
	}

	public TreeMap<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(TreeMap<String, String> resultMap) {
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
