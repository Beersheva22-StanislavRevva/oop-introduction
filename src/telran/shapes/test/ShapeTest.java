package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import telran.shapes.BaseCipher;
import telran.shapes.Rectangle;
import telran.shapes.Square;
import telran.shapes.SquareLeftTriangle;
import telran.shapes.SquareRightTriangle;
import telran.shapes.SquareTriangle;

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
	
	@Test
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
	
}
