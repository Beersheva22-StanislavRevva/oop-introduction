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
		long tmp = ((long) left + (long) right) / 2;
		middle = (int) tmp;
	}
	return (int) (left - 1) ;
	
}

}