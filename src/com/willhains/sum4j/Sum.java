package com.willhains.sum4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class Sum<This extends Sum<This>>
{
	private final Case<This> _case;
	private final Object[] _v;
	
	protected Sum(final Case<This> c, final Object[] values)
	{
		_case = c;
		_v = values;
	}
	
	@Override public int hashCode()
	{
		return Objects.hash(_case, _v);
	}
	
	@Override public boolean equals(final Object obj)
	{
		return obj instanceof Sum && eq((Sum<This>)obj);
	}
	
	public boolean eq(final Sum<This> that)
	{
		return this == that
			|| this._case == that._case
			&& Objects.deepEquals(this._v, that._v);
	}
	
	public static final int CONSTANT_MASK = Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL;
	
	// Lazily initialised
	private String _toString;
	@Override public String toString()
	{
		if(_toString != null) return _toString;
		try
		{
			for(final Field field: getClass().getDeclaredFields())
			{
				final boolean isConstant = (field.getModifiers() & CONSTANT_MASK) != 0;
				final Object value = field.get(null);
				final boolean isThis = value == _case;
				if(isConstant && isThis)
				{
					_toString = Arrays.stream(_v)
						.map(Object::toString)
						.collect(Collectors.joining(",", field.getName() + "(", ")"));
					return _toString;
				}
			}
		}
		catch(IllegalAccessException ignored) {}
		return super.toString();
	}
	
	public final Switch<Void> ifCase(final Case0<This> case0, final Case0.Then then)
	{
		if(case0 == _case) { then.handle(); return new Switch<>((This)this, null); }
		return new Switch<>((This)this);
	}
	
	public final <A> Switch<Void> ifCase(final Case1<This, A> case1, final Case1.Then<A> then)
	{
		if(case1 == _case) { then.handle((A)_v[0]); return new Switch<>((This)this, null); }
		return new Switch<>((This)this);
	}
	
	public final <A, B> Switch<Void> ifCase(final Case2<This, A, B> case2, final Case2.Then<A, B> then)
	{
		if(case2 == _case) { then.handle((A)_v[0], (B)_v[1]); return new Switch<>((This)this, null); }
		return new Switch<>((This)this);
	}
	
	public final <A, B, C> This ifCase(final Case3<This, A, B, C> case3, final Case3.Then<A, B, C> then)
	{
		if(case3 == _case) then.handle((A)_v[0], (B)_v[1], (C)_v[2]);
		return (This)this;
	}
	
	public final <A, B, C, D> This ifCase(final Case4<This, A, B, C, D> case4, final Case4.Then<A, B, C, D> then)
	{
		if(case4 == _case) then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3]);
		return (This)this;
	}
	
	public final <A, B, C, D, E> This ifCase(final Case5<This, A, B, C, D, E> case5, final Case5.Then<A, B, C, D, E> then)
	{
		if(case5 == _case) then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4]);
		return (This)this;
	}
	
	public final <A, B, C, D, E, F> This ifCase(final Case6<This, A, B, C, D, E, F> case6, final Case6.Then<A, B, C, D, E, F> then)
	{
		if(case6 == _case) then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4], (F)_v[5]);
		return (This)this;
	}
	
	public final <A, B, C, D, E, F, G> This ifCase(final Case7<This, A, B, C, D, E, F, G> case7, final Case7.Then<A, B, C, D, E, F, G> then)
	{
		if(case7 == _case) then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4], (F)_v[5], (G)_v[6]);
		return (This)this;
	}
	
	public final <A, B, C, D, E, F, G, H> This ifCase(final Case8<This, A, B, C, D, E, F, G, H> case8, final Case8.Then<A, B, C, D, E, F, G, H> then)
	{
		if(case8 == _case) then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4], (F)_v[5], (G)_v[6], (H)_v[7]);
		return (This)this;
	}
	
	public final <A, B, C, D, E, F, G, H, I> This ifCase(final Case9<This, A, B, C, D, E, F, G, H, I> case9, final Case9.Then<A, B, C, D, E, F, G, H, I> then)
	{
		if(case9 == _case) then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4], (F)_v[5], (G)_v[6], (H)_v[7], (I)_v[8]);
		return (This)this;
	}
	
	public final class Switch<Result>
	{
		final This _this;
		final Result _result;
		private Switch(final This t) { _this = t; _result = null; }
		private Switch(final This t, final Result result) { _this = t; _result = result; }
		public Result orElse(final Result def) { return _result == null ? def : _result; }
		public Result orElseNull() { return orElse(null); }
		public Result orElseGet(final Supplier<Result> def) { return _result == null ? def.get() : _result; }
		public Result orElseThrow() { return asOptional().get(); }
		public Optional<Result> asOptional() { return Optional.ofNullable(orElseNull()); }
		
		public final Switch<Void> ifCase(final Case0<This> case0, final Case0.Then then)
		{
			if(case0 == _case) { then.handle(); return new Switch<>((This)_this, null); }
			return (Switch<Void>)this;
		}
		
		public final Switch<Result> mapCase(final Case0<This> case0, final Case0.Return<Result> then)
		{
			// TODO: Duplicate case exception? if(case0 == _case && _result != null)
			if(case0 == _case) return new Switch<>(_this, then.handle());
			return this;
		}
		
		public final <A> Switch<Result> mapCase(final Case1<This, A> case1, final Case1.Return<A, Result> then)
		{
			if(case1 == _case) return new Switch<>(_this, then.handle((A)_v[0]));
			return this;
		}
		
		public final <A, B> Switch<Result> mapCase(final Case2<This, A, B> case2, final Case2.Return<A, B, Result> then)
		{
			if(case2 == _case) return new Switch<>(_this, then.handle((A)_v[0], (B)_v[1]));
			return this;
		}
		
		public final <A, B, C> Switch<Result> mapCase(final Case3<This, A, B, C> case3, final Case3.Return<A, B, C, Result> then)
		{
			if(case3 == _case) return new Switch<>(_this, then.handle((A)_v[0], (B)_v[1], (C)_v[2]));
			return this;
		}
		
		public final <A, B, C, D> Switch<Result> mapCase(final Case4<This, A, B, C, D> case4, final Case4.Return<A, B, C, D, Result> then)
		{
			if(case4 == _case) return new Switch<>(_this, then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3]));
			return this;
		}
		
		public final <A, B, C, D, E> Switch<Result> mapCase(final Case5<This, A, B, C, D, E> case5, final Case5.Return<A, B, C, D, E, Result> then)
		{
			if(case5 == _case) return new Switch<>(_this, then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4]));
			return this;
		}
		
		public final <A, B, C, D, E, F> Switch<Result> mapCase(final Case6<This, A, B, C, D, E, F> case6, final Case6.Return<A, B, C, D, E, F, Result> then)
		{
			if(case6 == _case) return new Switch<>(_this, then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4], (F)_v[5]));
			return this;
		}
		
		public final <A, B, C, D, E, F, G> Switch<Result> mapCase(final Case7<This, A, B, C, D, E, F, G> case7, final Case7.Return<A, B, C, D, E, F, G, Result> then)
		{
			if(case7 == _case) return new Switch<>(_this, then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4], (F)_v[5], (G)_v[6]));
			return this;
		}
		
		public final <A, B, C, D, E, F, G, H> Switch<Result> mapCase(final Case8<This, A, B, C, D, E, F, G, H> case8, final Case8.Return<A, B, C, D, E, F, G, H, Result> then)
		{
			if(case8 == _case) return new Switch<>(_this, then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4], (F)_v[5], (G)_v[6], (H)_v[7]));
			return this;
		}
		
		public final <A, B, C, D, E, F, G, H, I> Switch<Result> mapCase(final Case9<This, A, B, C, D, E, F, G, H, I> case9, final Case9.Return<A, B, C, D, E, F, G, H, I, Result> then)
		{
			if(case9 == _case) return new Switch<>(_this, then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4], (F)_v[5], (G)_v[6], (H)_v[7], (I)_v[8]));
			return this;
		}
	}
	
	public final <Result> Switch<Result> mapCase(final Case0<This> case0, final Case0.Return<Result> then)
	{
		if(case0 == _case) return new Switch<>((This)this, then.handle());
		return new Switch<>((This)this);
	}
	
	public final <A, Result> Switch<Result> mapCase(final Case1<This, A> case1, final Case1.Return<A, Result> then)
	{
		if(case1 == _case) return new Switch<>((This)this, then.handle((A)_v[0]));
		return new Switch<>((This)this);
	}
	
	public final <A, B, Result> Switch<Result> mapCase(final Case2<This, A, B> case2, final Case2.Return<A, B, Result> then)
	{
		if(case2 == _case) return new Switch<>((This)this, then.handle((A)_v[0], (B)_v[1]));
		return new Switch<>((This)this);
	}
	
	public final <A, B, C, Result> Switch<Result> mapCase(final Case3<This, A, B, C> case3, final Case3.Return<A, B, C, Result> then)
	{
		if(case3 == _case) return new Switch<>((This)this, then.handle((A)_v[0], (B)_v[1], (C)_v[2]));
		return new Switch<>((This)this);
	}
	
	public final <A, B, C, D, Result> Switch<Result> mapCase(final Case4<This, A, B, C, D> case4, final Case4.Return<A, B, C, D, Result> then)
	{
		if(case4 == _case) return new Switch<>((This)this, then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3]));
		return new Switch<>((This)this);
	}
	
	public final <A, B, C, D, E, Result> Switch<Result> mapCase(final Case5<This, A, B, C, D, E> case5, final Case5.Return<A, B, C, D, E, Result> then)
	{
		if(case5 == _case) return new Switch<>((This)this, then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4]));
		return new Switch<>((This)this);
	}
	
	public final <A, B, C, D, E, F, Result> Switch<Result> mapCase(final Case6<This, A, B, C, D, E, F> case6, final Case6.Return<A, B, C, D, E, F, Result> then)
	{
		if(case6 == _case) return new Switch<>((This)this, then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4], (F)_v[5]));
		return new Switch<>((This)this);
	}
	
	public final <A, B, C, D, E, F, G, Result> Switch<Result> mapCase(final Case7<This, A, B, C, D, E, F, G> case7, final Case7.Return<A, B, C, D, E, F, G, Result> then)
	{
		if(case7 == _case) return new Switch<>((This)this, then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4], (F)_v[5], (G)_v[6]));
		return new Switch<>((This)this);
	}
	
	public final <A, B, C, D, E, F, G, H, Result> Switch<Result> mapCase(final Case8<This, A, B, C, D, E, F, G, H> case8, final Case8.Return<A, B, C, D, E, F, G, H, Result> then)
	{
		if(case8 == _case) return new Switch<>((This)this, then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4], (F)_v[5], (G)_v[6], (H)_v[7]));
		return new Switch<>((This)this);
	}
	
	public final <A, B, C, D, E, F, G, H, I, Result> Switch<Result> mapCase(final Case9<This, A, B, C, D, E, F, G, H, I> case9, final Case9.Return<A, B, C, D, E, F, G, H, I, Result> then)
	{
		if(case9 == _case) return new Switch<>((This)this, then.handle((A)_v[0], (B)_v[1], (C)_v[2], (D)_v[3], (E)_v[4], (F)_v[5], (G)_v[6], (H)_v[7], (I)_v[8]));
		return new Switch<>((This)this);
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
		static @FunctionalInterface interface Return<Result> { Result handle(); }
	}
	
	public @FunctionalInterface interface Case1<SumType extends Sum<SumType>, A> extends Case<SumType>
	{
		default SumType init(A a) { return construct(a); }
		static @FunctionalInterface interface Then<A> { void handle(A a); }
		static @FunctionalInterface interface Return<A, Result> { Result handle(A a); }
	}
	
	public @FunctionalInterface interface Case2<SumType extends Sum<SumType>, A, B> extends Case<SumType>
	{
		default SumType init(A a, B b) { return construct(a, b); }
		static @FunctionalInterface interface Then<A, B> { void handle(A a, B b); }
		static @FunctionalInterface interface Return<A, B, Result> { Result handle(A a, B b); }
	}
	
	public @FunctionalInterface interface Case3<SumType extends Sum<SumType>, A, B, C> extends Case<SumType>
	{
		default SumType init(A a, B b, C c) { return construct(a, b, c); }
		static @FunctionalInterface interface Then<A, B, C> { void handle(A a, B b, C c); }
		static @FunctionalInterface interface Return<A, B, C, Result> { Result handle(A a, B b, C c); }
	}
	
	public @FunctionalInterface interface Case4<SumType extends Sum<SumType>, A, B, C, D> extends Case<SumType>
	{
		default SumType init(A a, B b, C c, D d) { return construct(a, b, c, d); }
		static @FunctionalInterface interface Then<A, B, C, D> { void handle(A a, B b, C c, D d); }
		static @FunctionalInterface interface Return<A, B, C, D, Result> { Result handle(A a, B b, C c, D d); }
	}
	
	public @FunctionalInterface interface Case5<SumType extends Sum<SumType>, A, B, C, D, E> extends Case<SumType>
	{
		default SumType init(A a, B b, C c, D d, E e) { return construct(a, b, c, d, e); }
		static @FunctionalInterface interface Then<A, B, C, D, E> { void handle(A a, B b, C c, D d, E e); }
		static @FunctionalInterface interface Return<A, B, C, D, E, Result> { Result handle(A a, B b, C c, D d, E e); }
	}
	
	public @FunctionalInterface interface Case6<SumType extends Sum<SumType>, A, B, C, D, E, F> extends Case<SumType>
	{
		default SumType init(A a, B b, C c, D d, E e, F f) { return construct(a, b, c, d, e, f); }
		static @FunctionalInterface interface Then<A, B, C, D, E, F> { void handle(A a, B b, C c, D d, E e, F f); }
		static @FunctionalInterface interface Return<A, B, C, D, E, F, Result> { Result handle(A a, B b, C c, D d, E e, F f); }
	}
	
	public @FunctionalInterface interface Case7<SumType extends Sum<SumType>, A, B, C, D, E, F, G> extends Case<SumType>
	{
		default SumType init(A a, B b, C c, D d, E e, F f, G g) { return construct(a, b, c, d, e, f, g); }
		static @FunctionalInterface interface Then<A, B, C, D, E, F, G> { void handle(A a, B b, C c, D d, E e, F f, G g); }
		static @FunctionalInterface interface Return<A, B, C, D, E, F, G, Result> { Result handle(A a, B b, C c, D d, E e, F f, G g); }
	}
	
	public @FunctionalInterface interface Case8<SumType extends Sum<SumType>, A, B, C, D, E, F, G, H> extends Case<SumType>
	{
		default SumType init(A a, B b, C c, D d, E e, F f, G g, H h) { return construct(a, b, c, d, e, f, g, h); }
		static @FunctionalInterface interface Then<A, B, C, D, E, F, G, H> { void handle(A a, B b, C c, D d, E e, F f, G g, H h); }
		static @FunctionalInterface interface Return<A, B, C, D, E, F, G, H, Result> { Result handle(A a, B b, C c, D d, E e, F f, G g, H h); }
	}
	
	public @FunctionalInterface interface Case9<SumType extends Sum<SumType>, A, B, C, D, E, F, G, H, I> extends Case<SumType>
	{
		default SumType init(A a, B b, C c, D d, E e, F f, G g, H h, I i) { return construct(a, b, c, d, e, f, g, h, i); }
		static @FunctionalInterface interface Then<A, B, C, D, E, F, G, H, I> { void handle(A a, B b, C c, D d, E e, F f, G g, H h, I i); }
		static @FunctionalInterface interface Return<A, B, C, D, E, F, G, H, I, Result> { Result handle(A a, B b, C c, D d, E e, F f, G g, H h, I i); }
	}
}
