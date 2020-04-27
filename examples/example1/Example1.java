package example1;

import example1.Shape;

public class Example1
{
	public static void main(final String[] args)
	{
		final Shape rect1 = Shape.RECTANGLE.init(3.0, 4.5);
		final Shape rect2 = Shape.RECTANGLE.init(3.0, 4.5);
		final Shape rect3 = Shape.RECTANGLE.init(1.0, 2.0);
		final Shape circ1 = Shape.CIRCLE.init(3.0);
		final Shape point = Shape.POINT.init();
		
		circ1.ifCase(Shape.CIRCLE, radius -> drawCircle(radius));
		
		final double area1 = rect1
			.mapCase(Shape.RECTANGLE, (width, height) -> width * height)
			.mapCase(Shape.CIRCLE,    (radius)        -> Math.PI * radius * radius)
			.orElse(0.0);
		System.out.println("area1 = " + area1);
		
		final double area2 = circ1
			.mapCase(Shape.POINT,     ()              -> 0.0)
			.mapCase(Shape.CIRCLE,    (radius)        -> Math.PI * radius * radius)
			.mapCase(Shape.RECTANGLE, (width, height) -> width * height)
			.orElseThrow();
		System.out.println("area2 = " + area2);
		
		System.out.println(Shape.RECTANGLE.init(3.0, 4.5));
		System.out.println(Shape.CIRCLE.init(5.1));
		System.out.println(Shape.POINT.init());
		
		System.out.println(rect1 + " " + (rect1.equals(rect1)?"==":"!=") + " " + rect1);
		System.out.println(rect1 + " " + (rect1.equals(rect2)?"==":"!=") + " " + rect2);
		System.out.println(rect1 + " " + (rect1.equals(rect3)?"==":"!=") + " " + rect3);
		System.out.println(rect1 + " " + (rect1.equals(circ1)?"==":"!=") + " " + circ1);
	}
	
	static void drawCircle(final double radius)
	{
		System.out.println("Drawing circle with radius " + radius);
	}
}
