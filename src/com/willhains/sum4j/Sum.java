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
	
	public final <A, B, C> void ifCase(final Case3<This, A, B, C> case3, final Case3.Then<A, B, C> then)
	{
		if(case3 == _case) then.handle((A)_values[0], (B)_values[1], (C)_values[2]);
	}
	
	public final <A, B, C, D> void ifCase(final Case4<This, A, B, C, D> case4, final Case4.Then<A, B, C, D> then)
	{
		if(case4 == _case) then.handle((A)_values[0], (B)_values[1], (C)_values[2], (D)_values[3]);
	}
	
	public final <A, B, C, D, E> void ifCase(final Case5<This, A, B, C, D, E> case5, final Case5.Then<A, B, C, D, E> then)
	{
		if(case5 == _case) then.handle((A)_values[0], (B)_values[1], (C)_values[2], (D)_values[3], (E)_values[4]);
	}
	
	public final <A, B, C, D, E, F> void ifCase(final Case6<This, A, B, C, D, E, F> case6, final Case6.Then<A, B, C, D, E, F> then)
	{
		if(case6 == _case) then.handle((A)_values[0], (B)_values[1], (C)_values[2], (D)_values[3], (E)_values[4], (F)_values[5]);
	}
	
	public final <A, B, C, D, E, F, G> void ifCase(final Case7<This, A, B, C, D, E, F, G> case7, final Case7.Then<A, B, C, D, E, F, G> then)
	{
		if(case7 == _case) then.handle((A)_values[0], (B)_values[1], (C)_values[2], (D)_values[3], (E)_values[4], (F)_values[5], (G)_values[6]);
	}
	
	public final <A, B, C, D, E, F, G, H> void ifCase(final Case8<This, A, B, C, D, E, F, G, H> case8, final Case8.Then<A, B, C, D, E, F, G, H> then)
	{
		if(case8 == _case) then.handle((A)_values[0], (B)_values[1], (C)_values[2], (D)_values[3], (E)_values[4], (F)_values[5], (G)_values[6], (H)_values[7]);
	}
	
	public final <A, B, C, D, E, F, G, H, I> void ifCase(final Case9<This, A, B, C, D, E, F, G, H, I> case9, final Case9.Then<A, B, C, D, E, F, G, H, I> then)
	{
		if(case9 == _case) then.handle((A)_values[0], (B)_values[1], (C)_values[2], (D)_values[3], (E)_values[4], (F)_values[5], (G)_values[6], (H)_values[7], (I)_values[8]);
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
	
	public @FunctionalInterface interface Case3<SumType extends Sum<SumType>, A, B, C> extends Case<SumType>
	{
		default SumType init(A a, B b, C c) { return construct(a, b, c); }
		static @FunctionalInterface interface Then<A, B, C> { void handle(A a, B b, C c); }
	}
	
	public @FunctionalInterface interface Case4<SumType extends Sum<SumType>, A, B, C, D> extends Case<SumType>
	{
		default SumType init(A a, B b, C c, D d) { return construct(a, b, c, d); }
		static @FunctionalInterface interface Then<A, B, C, D> { void handle(A a, B b, C c, D d); }
	}
	
	public @FunctionalInterface interface Case5<SumType extends Sum<SumType>, A, B, C, D, E> extends Case<SumType>
	{
		default SumType init(A a, B b, C c, D d, E e) { return construct(a, b, c, d, e); }
		static @FunctionalInterface interface Then<A, B, C, D, E> { void handle(A a, B b, C c, D d, E e); }
	}
	
	public @FunctionalInterface interface Case6<SumType extends Sum<SumType>, A, B, C, D, E, F> extends Case<SumType>
	{
		default SumType init(A a, B b, C c, D d, E e, F f) { return construct(a, b, c, d, e, f); }
		static @FunctionalInterface interface Then<A, B, C, D, E, F> { void handle(A a, B b, C c, D d, E e, F f); }
	}
	
	public @FunctionalInterface interface Case7<SumType extends Sum<SumType>, A, B, C, D, E, F, G> extends Case<SumType>
	{
		default SumType init(A a, B b, C c, D d, E e, F f, G g) { return construct(a, b, c, d, e, f, g); }
		static @FunctionalInterface interface Then<A, B, C, D, E, F, G> { void handle(A a, B b, C c, D d, E e, F f, G g); }
	}
	
	public @FunctionalInterface interface Case8<SumType extends Sum<SumType>, A, B, C, D, E, F, G, H> extends Case<SumType>
	{
		default SumType init(A a, B b, C c, D d, E e, F f, G g, H h) { return construct(a, b, c, d, e, f, g, h); }
		static @FunctionalInterface interface Then<A, B, C, D, E, F, G, H> { void handle(A a, B b, C c, D d, E e, F f, G g, H h); }
	}
	
	public @FunctionalInterface interface Case9<SumType extends Sum<SumType>, A, B, C, D, E, F, G, H, I> extends Case<SumType>
	{
		default SumType init(A a, B b, C c, D d, E e, F f, G g, H h, I i) { return construct(a, b, c, d, e, f, g, h, i); }
		static @FunctionalInterface interface Then<A, B, C, D, E, F, G, H, I> { void handle(A a, B b, C c, D d, E e, F f, G g, H h, I i); }
	}
}
