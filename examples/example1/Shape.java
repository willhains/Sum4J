package example1;

import com.willhains.sum4j.Sum;

public class Shape extends Sum<Shape>
{
	public static final Case2<Shape, Double, Double> RECTANGLE = Shape::new;
	public static final Case1<Shape, Double> CIRCLE = Shape::new;
	public static final Case0<Shape> POINT = Shape::new;
	
	private Shape(final Case<Shape> c, final Object[] values) { super(c, values); }
}
