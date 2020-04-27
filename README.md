# Sum Types for Java

This little library makes it easy to create and use sum types (a.k.a. [tagged unions][wiki]) in Java 8+, similar to Swift’s [enums with associated values][swift].

[wiki]: https://en.wikipedia.org/wiki/Tagged_union
[swift]: https://docs.swift.org/swift-book/LanguageGuide/Enumerations.html#ID148

This project aims to provide:

1. A way to define a sum type with as little boilerplate code as possible.
2. Ways to use the values of sum types that are both convenient and idiomatic in Java.
3. Type safety, and other safety features.

## Define a Sum Type

1. Extend the `Sum<This>` class, where `This` is a self-reference to your own class.
2. Add a private constructor that takes a `Case` and an `Object[]` as arguments, calling the superclass constructor.
3. Add a constant for each case of your sum type, of type `Case0`, `Case1`, ...`Case9`, depending on the number of associated values it needs. The constant value is always a method reference to your private constructor.

```java
public final class Shape extends Sum<Shape>
{
	public static final Case0<Shape>                 POINT     = Shape::new;
	public static final Case1<Shape, Double>         CIRCLE    = Shape::new;
	public static final Case2<Shape, Double, Double> RECTANGLE = Shape::new;
	
	private Shape(final Case<Shape> c, final Object[] vals) { super(c, vals); }
}
```

## Use a Sum Type

You use a sum-type value similarly to how you would use an `Optional` value. First, use the `init` method to create an instance:

```java
final Shape shape = Shape.RECTANGLE.init(3.0, 5.5);
```

Then, to test the value of a sum type variable:

```java
shape.ifCase(Shape.CIRCLE, radius -> graphics.drawCircle(radius));
```

If you need to return a value:

```java
final double area1 = shape
    .mapCase(Shape.RECTANGLE, (width, height) -> width * height)
    .mapCase(Shape.CIRCLE,    (radius)        -> Math.PI * radius * radius)
    .orElse(0.0);
```

To ensure all cases are covered:

```java
final double area2 = shape
    .mapCase(Shape.POINT,     ()              -> 0.0)
    .mapCase(Shape.CIRCLE,    (radius)        -> Math.PI * radius * radius)
    .mapCase(Shape.RECTANGLE, (width, height) -> width * height)
    .orElseThrow();
```

## Limitations

While this library gives a reasonable approximation of sum types, some limitations are worth noting.

- The associated values are anonymous. This can make things confusing. In the example above, you don't know whether `Shape.RECTANGLE`'s associated values are `(width, height)` or `(height, width)`. Javadoc on the case constants helps a bit, but the best strategy is to use custom wrappers for your values, and give them meaningful names.
- There is no way for the compiler to check that you have covered all cases. (This is also true for plain Java `enum`s, but most IDEs offer some help for those.)
- The maximum number of associated values per case is nine (`Case0` ~ `Case9`). It would be possible to create more, but I have to draw the line somewhere, and keeping it single-digit helps with IDE code completion.
- Java's compiler errors can be confusing when you get the number or type of associated values wrong. When in doubt, check the declaration of the case constant.
- The `toString()` implementation uses reflection to get the name of the case.
