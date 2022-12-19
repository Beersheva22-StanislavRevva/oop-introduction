	package telran.memory;

public class MemoryOperations {
public static int getMaxAvaibleMemory() {
	int res = Integer.MAX_VALUE;
	boolean running = true;
	byte ar[] = null;
	int left = 0;
	int right = res;
	int middle = res/2;
	while(running) {
		ar = null;
		try {
			ar = new byte[middle];
			left = middle + 1;
				if(left >= right) {
					running = false;
				}	
			
			} catch (Throwable e) {
				right = middle - 1;
			}
		middle = left + (right-left)/2;
	}
	res = left - 1;
	return res;	
}

}