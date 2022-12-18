package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.shapes.*;

class ShapeTests {
	Canvas canvas = new Canvas(10, 20,
			new Shape[] { new Rectangle(10, 3), new Square(10), new SquareLeftTriangle(10) });
	Shape[] shapes = { new Rectangle(10, 3), new Square(10), new SquareLeftTriangle(10), new SquareRightTriangle(10),
			canvas, new Square(10) };

	@Test
	@Disabled
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(10, 5);
		assertEquals(10, rectangle.getWidth());
		assertEquals(5, rectangle.getHeight());
		displayStrings(rectangle.presentation(20));
		Rectangle.setSymbol("#");
		displayStrings(rectangle.presentation(20));
	}

	private void displayStrings(String strings[]) {
		for (String str : strings) {
			System.out.println(str);
		}
	}

	@Test
	@Disabled
	void leftTriangleTest() {
		SquareLeftTriangle triangle = new SquareLeftTriangle(20);
		displayStrings(triangle.presentation(10));
	}

	@Test
	@Disabled
	void rightTriangleTest() {
		SquareRightTriangle triangle = new SquareRightTriangle(20);
		displayStrings(triangle.presentation(10));
	}

	@Test
	@Disabled
	void canvasInRowTest() {
		Canvas canvas = new Canvas(10, 4, shapes);
		canvas.setMargin(3);
		displayStrings(canvas.presentation(2));

	}

	@Test
	 //@Disabled
	void canvasInColumnTest() {
		Canvas canvas = new Canvas(10, 4, shapes);
		canvas.setDirection("column");
		//this.canvas.setDirection("column");
		canvas.setMargin(1);
		displayStrings(canvas.presentation(2));

	}

}