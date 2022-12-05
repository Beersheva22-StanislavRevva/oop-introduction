package telran.shapes;

public class Square extends Rectangle {
	
	protected int size;
	
	public Square(int size) {
		super(size, size);
	}
	
	public int getSize() {
		return size;
	}
	
	public void setWidth(int width) {
		 this.size = super.width;
	}

	public void setHeight(int height) {
		this.size = super.height;
	}
}
