package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.shapes.*;


class ShapeTests {

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
		for(String str: strings) {
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
	void cypherTest() {
	int length = 10;
	int number = 10;
	BaseCipher basecipher = new BaseCipher(length);
	String key = basecipher.key;
	String cipher = basecipher.cipher(number, length, key);
	int decipher = basecipher.decipher(cipher, key);
	System.out.println(key + "	" + cipher + "	" + decipher);
	length = 1;
	number = 1000;
	BaseCipher basecipher2 = new BaseCipher(length);
	key = basecipher2.key;
	cipher = basecipher2.cipher(number, length, key);
	decipher = basecipher2.decipher(cipher, key);
	System.out.println(key + "	" + cipher + "	" + decipher);
	}
	@Test
	void CanvasTest() {
	Shape[] shapes = {new Rectangle(6,6), new Square(6), new SquareLeftTriangle(7), new SquareRightTriangle(8)};
	Canvas canvas = new Canvas(5,5,shapes);
	canvas.setMargin(3);
	canvas.setDirection("row");
	canvas.displayStrings(canvas.presentation(10));
	canvas.setMargin(1);
	canvas.setDirection("column");
	canvas.displayStrings(canvas.presentation(5));
	}
	
}
