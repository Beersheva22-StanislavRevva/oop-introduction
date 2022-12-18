package telran.shapes;

public class Canvas extends Shape {
	protected Shape[] shapes;
	String direction;
	int margin;
	
	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
	}
	public void setDirection(String direction) {
			this.direction = direction;		
	}
	public void setMargin(int margin) {
			this.margin = margin;
			}
	@Override
	public String[] presentation(int offset) {
		String[] res = new String[height];
		if (direction == "column") {
			res =  presentationInColumn(offset);
		} else {
			res = presentationInRow(offset);
		}
		return res;
	}
	private String[] presentationInRow(int offset) {
		String[] res;
		singleHeight();
		res = shapes[0].presentation(offset);
		for (int i = 1; i < shapes.length; i++) {
			res = joinArrays(res, shapes[i].presentation(margin));
		}
		return res;
	}
	private Shape[] singleHeight() {
		for (int i = 0; i < shapes.length; i++) {
			shapes[i].setHeight(height);
		}
		return shapes;
	}
	private String[] joinArrays(String[] array1, String[] array2) {
		String result[] = new String[array1.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = array1[i] + array2[i];
		}
		return result;
	}
	private String[] presentationInColumn(int offset) {
		shapes = singleWidth(shapes);
		int height = shapes[0].height;
		height = calculateHeight(height);
		String[] res = new String[height];
		String[] tmp = new String[shapes[0].height];
		int tmpHeight = 0;
		for (int i = 0; i < shapes.length; i++) {
		tmp = shapes[i].presentation(offset);
			for (int j = 0; j < shapes[i].height; j++) {
				res[j + tmpHeight] = tmp[j];
			}
			tmpHeight = tmpHeight + shapes[i].height;
			int j=0;
			while(j < margin && i < (shapes.length - 1)){
				res[j + tmpHeight] = spaces (offset, width);
				j++;
			}
			tmpHeight = tmpHeight + margin;
			
		}
		return res;
	}
	private int calculateHeight(int height) {
		for (int i=1; i<shapes.length; i++) {
			height = height + shapes[i].height + margin;	
		}
		return height;
	}
	private String spaces(int offset, int width) {
		int tmp = offset + width;
		return " ".repeat(tmp);
	}
	private Shape[] singleWidth(Shape[] shapes) {
		for (int i = 0; i < shapes.length; i++) {
			shapes[i].setWidth(width);
		}
		return shapes;
	}
	public void displayStrings(String[] strings) {
		for(int i = 0; i < strings.length; i++) {
				System.out.println(strings[i]);
		}
			
	}
	

}