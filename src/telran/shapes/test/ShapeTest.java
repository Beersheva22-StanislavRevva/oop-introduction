package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import telran.shapes.Rectangle;
import telran.shapes.Square;
import telran.shapes.SquareLeftTriangle;
import telran.shapes.SquareRightTriangle;
import telran.shapes.SquareTriangle;

class ShapeTests {

	@Test
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(10, 5);
		assertEquals(10, rectangle.getWidth());
		assertEquals(5, rectangle.getHeight());
		displayStrings(rectangle.presentation(20));
		Rectangle.setSymbol("#");
		displayStrings(rectangle.presentation(20));
		Square square = new Square (rectangle.getWidth());
		displayStrings(square.presentation(20));
		SquareLeftTriangle squareLeftTriangle = new SquareLeftTriangle(rectangle.getWidth());
		displayStrings(squareLeftTriangle.presentation(20));
		SquareRightTriangle squareRightTriangle = new SquareRightTriangle(rectangle.getHeight());
		displayStrings(squareRightTriangle.presentation(20));
	}
	private void displayStrings(String strings[]) {
		for(String str: strings) {
			System.out.println(str);
		}
	}

}
