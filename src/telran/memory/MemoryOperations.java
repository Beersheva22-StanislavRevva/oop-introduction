	package telran.memory;

public class MemoryOperations {
public static int getMaxAvaibleMemory() {
	int res = Integer.MAX_VALUE;
	boolean running = true;
	byte ar[] = null;
	while(running) {
		try {
			ar = new byte[res];
			running = false;
			
		} catch (Throwable e) {
			res /= 2;
			
		}
	}
	return res;
}
}