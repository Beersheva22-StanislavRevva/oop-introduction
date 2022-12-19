	package telran.memory;

public class MemoryOperations {
public static int getMaxAvaibleMemory() {
	int res = Integer.MAX_VALUE;
	boolean running = true;
	byte ar[] = null;
	int left = 0;
	int right = res - 1;
	int middle = res/2;
	while(running) {
		try {
				ar = new byte[middle];
				left = middle + 1;
				if (left >= right) {
				running = false;
				}
				
		} catch (Throwable e) {
				right = middle - 1;
				}
		middle = (left + right) / 2;
		}
	return left;
	
}

public static int binarySearch(int arraySorted[], int number) {
	int  left = 0;
	int right = arraySorted.length - 1;
	int middle = arraySorted.length / 2;
	while(left <= right && arraySorted[left] != number) {
		if (number <= arraySorted[middle]) {
			right = middle - 1;
		} else {
			left = middle + 1;
		}
		middle = (left + right) / 2;
	}
	return  left < arraySorted.length && arraySorted[left] == number ? left : -left - 1;
}
}