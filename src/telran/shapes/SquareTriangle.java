package telran.shapes;

public class SquareTriangle extends Square {
	protected boolean isLeftDiagonal;
	protected int size;
	public SquareTriangle(int size) {
		super(size);
			}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		 this.size = super.size;
	}
	public String[] presentation(int offset) {
		String res[] = new String[height];
		int space = 0;
		int lastLine = height - 1;
		if (isLeftDiagonal == false) {
		res[0] = getOffset(offset) + symbol;
		res[lastLine] = getLine(offset);
		for (int i = 1; i < lastLine; i++) {
			res[i] = this.getRightDiagonal(offset,space+1);
			space++;
		}
		}else {
			res[0] = getOffset(offset + height - 1) + symbol;
			res[lastLine] = getLine(offset);
			for (int i = 1; i < lastLine; i++) {
				res[i] = this.getLeftDiagonal(offset,space+1);
				space++;
			}
		}
		return res;
	}
	private String getRightDiagonal(int offset, int space) {
		
		return getOffset(offset) + symbol + getOffset(space-1) + symbol;
	}
	private String getLeftDiagonal(int offset, int space) {
			
		return getOffset(offset + height - space - 1) + symbol + getOffset(space-1) + symbol;
	}
	private String getLine(int offset) {
		
		return getOffset(offset) + symbol.repeat(height);
	}
	private String getOffset(int offset) {
		
		return " ".repeat(offset);
	}
}
