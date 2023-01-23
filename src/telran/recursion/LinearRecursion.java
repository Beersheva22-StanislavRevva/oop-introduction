package telran.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearRecursion {
static public long factorial(int n) {
	long res = 0;
	if (n < 0) {
		res = factorial(-n);
	} else if (n < 2){
		res = 1;
	} else {
		res = n * factorial(n - 1);
	}
	return res;
}

static public int power(int a, int b) {
	int res = 0;
	res = power (a, b, res);
	if (res < 0 && b%2 == 0) {
		res = - res;
	}
	return res;
}

static private int power(int a, int b, int res) {
	if (b < 0) {
		throw new IllegalArgumentException();
	}
	if (b == 0) {
		return res = 1;
	}
	else if (b == 1) {
		return res = a;
	}
	else {res = power (a, b - 1, res);
	res = countRes(res, a);
	return res; }

}

	private static int countRes(int res, int a) {
	if (res == 0) {
		res = a;
	}
	if (a > 1) {
		res = res + countRes(res, a - 1);
	}
	if (a < -1) {
		res = res + countRes(res, a + 1);
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
public static long square(long res, int x) {
	if (res == 0) {
		res = x;
	}
	if (x > 1) {
		res = res + square(res, x - 1);
	}
	if (x < -1) {
		res = - res + square(res, x + 1);
	}
	if (res < 0) {
		return - res;
	} else {
		return res;
		}
}
public static void reverse(int ar[]) {
	//no cycles
	//no static fields
	//TODO reversing elements of the source array
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

public static boolean isSubstring(String string, String substr) {
	if (substr.length() > string.length()) {
		throw new IllegalArgumentException();
	}
	boolean res = false;
	int ind = 0;
	return res = isSubstring(string, substr, ind, res);
}

private static boolean	isSubstring(String string, String substr,int ind, boolean res){
	String compare;
	int compareMaxIndex = ind + substr.length();
	int stringLength =  string.length();
	if (ind < stringLength) {
		if (substr.charAt(0) == string.charAt(ind) && (compareMaxIndex <= stringLength)) {
			compare = (string.substring(ind, compareMaxIndex));
			if (compare.equals(substr)) {
				ind = stringLength - 1;
				res = true;
			}
		}
			ind ++;
			res = isSubstring(string, substr, ind, res);
		}
	return res;
}
}
