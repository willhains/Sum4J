package com.willhains.sum4j;

import java.util.function.BiFunction;

public abstract class Sum<This extends Sum<This>>
{
	private final Case<This> _case;
	private final Object[] _values;
	
	protected Sum(final Case<This> c, final Object[] values)
	{
		_case = c;
		_values = values;
	}
	
	public final void ifCase(final Case0<This> case0, final Case0.Then then)
	{
		if(case0 == _case) then.handle();
	}
	
	public final <A> void ifCase(final Case1<This, A> case1, final Case1.Then<A> then)
	{
		if(case1 == _case) then.handle((A)_values[0]);
	}
	
	public final <A, B> void ifCase(final Case2<This, A, B> case2, final Case2.Then<A, B> then)
	{
		if(case2 == _case) then.handle((A)_values[0], (B)_values[1]);
	}
	
	protected @FunctionalInterface interface Case<SumType extends Sum<SumType>>
		extends BiFunction<Case<SumType>, Object[], SumType>
	{
		default SumType construct(final Object... associatedValues)
		{
			return this.apply(this, associatedValues);
		}
	}
	
	public @FunctionalInterface interface Case0<SumType extends Sum<SumType>> extends Case<SumType>
	{
		default SumType init() { return construct(); }
		static @FunctionalInterface interface Then { void handle(); }
	}
	
	public @FunctionalInterface interface Case1<SumType extends Sum<SumType>, A> extends Case<SumType>
	{
		default SumType init(A a) { return construct(a); }
		static @FunctionalInterface interface Then<A> { void handle(A a); }
	}
	
	public @FunctionalInterface interface Case2<SumType extends Sum<SumType>, A, B> extends Case<SumType>
	{
		default SumType init(A a, B b) { return construct(a, b); }
		static @FunctionalInterface interface Then<A, B> { void handle(A a, B b); }
	}
}
