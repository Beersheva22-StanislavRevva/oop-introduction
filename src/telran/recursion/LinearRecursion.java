package telran.recursion;

public class LinearRecursion {
	static public long factorial(int n) {
		long res = 0;
		if (n < 0) {
			res = factorial(-n);
		} else if (n < 2) {
			res = 1;
		} else {
			res = n * factorial(n - 1);
		}
		return res;
	}

	static public int power(int a, int b) {

		// your code cannot use cycles and *, / operators
		if (b < 0) {
			throw new IllegalArgumentException();
		}
		int res = 1;
		if (b > 0) {
			res = multiply(a, power(a, b - 1));
		}
		return res;
	}

	private static int multiply(int a, int b) {

		int res = 0;
		if (b < 0) {
			res = multiply(-a, -b);
		}
		if (b > 0) {
			res = a + multiply(a, b - 1);
		}

		return res;
	}

	static public long sum(int ar[]) {

		return sum(0, ar);
	}

	private static long sum(int firstIndex, int[] ar) {
		long res = 0;
		if (firstIndex < ar.length) {
			res = ar[firstIndex] + sum(firstIndex + 1, ar);
		}
		return res;
	}

	public static long square(int x) {
		// no cycles
		// no * , / operators
		// no additional functions
		// no static fields
		// (x-1)^2 = x^2 -2x + 1; => x^2 = (x-1)^2 + 2x -1
		long res = 0;
		if (x < 0) {
			res = square(-x);
		} else if (x > 0) {
			res = x + x - 1 + square(x - 1);
		}
		return res;
	}

	public static void reverse(int ar[]) {
		// no cycles
		// no static fields
		// TODO reversing elements of the source array
		reverse(0, ar.length - 1, ar);
	}

	private static void reverse(int firstIndex, int lastIndex, int[] ar) {
		if (firstIndex < lastIndex) {
			swap(ar, firstIndex, lastIndex);
			reverse(firstIndex + 1, lastIndex - 1, ar);
		}

	}

	private static void swap(int[] ar, int firstIndex, int lastIndex) {
		int tmp = ar[firstIndex];
		ar[firstIndex] = ar[lastIndex];
		ar[lastIndex] = tmp;

	}

	public static boolean isSubstring(String str, String substr) {
		// TODO write function
		// returns true if a given 'substr' is indeed the substring of a given
		// 'string'.
		// Challenges: 1. To apply only following methods of the class String:
		// charAt(int ind); String substring(int ind); int length();
		// 2. No cycles;
		boolean res = false;
		if (str.length() >= substr.length()) {
			res = isEqual(str, substr) ? true : isSubstring(str.substring(1), substr);
		} 

		return res;

	}

	private static boolean isEqual(String str, String substr) {
		boolean res = false;
		if (substr.length() == 0) {
			res = true;
		} else if (str.charAt(0) == substr.charAt(0)) {
			res = isEqual(str.substring(1), substr.substring(1));
		}
		
		return res;
	}
}