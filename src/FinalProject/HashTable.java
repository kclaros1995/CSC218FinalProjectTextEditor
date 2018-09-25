 package FinalProject;

import java.math.BigInteger;

public class HashTable {

	String[] dictArray;
	int step;

	public HashTable(int initialize) {
		dictArray = new String[nextPrime(initialize * 2)];
		step = dictArray.length / 100;
	}

	public void insert(String s) {
		int index = Math.abs(s.hashCode()) % dictArray.length;
		while (true) {
			if (dictArray[index] == null) {
				dictArray[index] = s;
				break;
			} else {
				index += stepSize(s);
				index %= dictArray.length;
			}
		}
	}

	public int stepSize(String s) {
		return step - (Math.abs(s.hashCode()) % step);
	}

	public boolean contains(String s) {
		int index = Math.abs(s.hashCode()) % dictArray.length;
		while (true) {
			if (dictArray[index] == null) {
				return false;
			} else if (dictArray[index].equals(s)) {
				return true;
			} else {
				index += stepSize(s);
				index %= dictArray.length;
			}
		}
	}

	private int nextPrime(int i) {
		BigInteger b = new BigInteger(String.valueOf(i));
		return Integer.parseInt(b.nextProbablePrime().toString());
	}

}
