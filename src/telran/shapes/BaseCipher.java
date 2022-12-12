package telran.shapes;

public class BaseCipher {
	public int length;
	public String key;
	static protected int minAscii = 33;
	static protected int maxAscii = 126;
	
	public BaseCipher(int length) {
		this.length = length;
		key = keyGen(length);
	}

	public String cipher(int number, int length, String key) {
		String res = "";
		while (number > 0) {
			int tmp = number % key.length();
			char tmpChar = key.charAt(tmp);
			res = res + tmpChar;
			number = number / key.length();
		}
		res = reverseString(res);
		return res;
	}
	public int decipher(String res, String key) {
		int AppropriateNumber = 0;
		for (int i = res.length() - 1; i > -1; i--) {
			char tmpChar = res.charAt(i);
			int j = 0;
			while (key.charAt(j) != tmpChar) {
			j++;
			}
			AppropriateNumber = (int) (AppropriateNumber + j * Math.pow(key.length(), (res.length() - i - 1))); //key.length() ^ (res.length() - i)	
		}
		return AppropriateNumber;
	}
	private String reverseString(String res) {
		 String result = "";
		  for (int i = 0; i < res.length(); i++) {
		     result = res.charAt(i) + result;
		  }
		  return result;
	}

	public String keyGen(int length) {
		
		if (length < 2) {
			length = 2;
		}
		if (length > 94) {
			length = 94;
		}
		int Array[] = new int [length];
		Array = fillArray(length);
		String key = ArrayToString(Array);
		return key;
	}
		
	private String ArrayToString(int[] Array) {
		String key = Character.toString(Array[0]);
		for (int i = 1; i < Array.length; i++) {
		String tmp = Character.toString(Array[i]);
		key = key + tmp;	
		}
		return key;
	}

	private int[] fillArray(int length) {
		int res[] = new int [length];
		for (int i = 0; i < length; i++) {
			res[i] = getUniqueRandomInt(minAscii, maxAscii);
	}
		return res;
	}

	private int getUniqueRandomInt(int minAscii, int maxAscii) {
		int res = 0;
		long mask = 0;
		do {
			res = (int)(minAscii + Math.random() * (maxAscii - minAscii + 1));
		} while(getBitValue(mask, res) == 1);
		mask = setBitValue(mask, res, true);
		return res;
	}
	
	private int getBitValue(long number, int nBit) {
		int res = -1;
		if (checkNbit(nBit)) {
			long mask = getMask(nBit);
			res = (number & mask) == 0 ? 0 : 1;
		}
		return res;
	}

	private long getMask(int nBit) {
		return 1l << nBit;
	}
	
	private boolean checkNbit(int nBit) {
		int N_BITS = maxAscii - minAscii;
		return nBit < N_BITS && nBit > -1;
	}

	private long setBitValue(long number, int nBit, boolean value) {
		long res = -1;
		if (checkNbit(nBit)) {
			long mask = getMask(nBit);
			res = value ? number | mask : number & ~mask;
		}
		return res;
	}
	
}
