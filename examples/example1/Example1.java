package example1;

import example1.Shape;

public class Example1
{
	public static void main(final String[] args)
	{
		final Shape shape = Shape.RECTANGLE.init(3.0, 4.5);
		
		shape.ifCase(Shape.CIRCLE, radius -> drawCircle(radius));
		
		final double area1 = shape
			.mapCase(Shape.RECTANGLE, (width, height) -> width * height)
			.mapCase(Shape.CIRCLE,    (radius)        -> Math.PI * radius * radius)
			.orElse(0.0);
		System.out.println("area1 = " + area1);
		
		final double area2 = shape
			.mapCase(Shape.RECTANGLE, (width, height) -> width * height)
			.mapCase(Shape.CIRCLE,    (radius)        -> Math.PI * radius * radius)
			.mapCase(Shape.POINT,     ()              -> 0.0)
			.orElseThrow();
		System.out.println("area2 = " + area2);
	}
	
	static void drawCircle(final double radius)
	{
		System.out.println("Drawing circle with radius " + radius);
	}
}
