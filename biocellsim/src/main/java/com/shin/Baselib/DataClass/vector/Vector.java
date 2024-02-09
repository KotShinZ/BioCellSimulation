package com.shin.Baselib.DataClass.vector;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import com.shin.Baselib.Interface.IInstanceCalcuratable;
import com.shin.Baselib.Interface.ICalcuratable;

public class Vector<T extends Number> implements ICalcuratable<Vector<T>>, IInstanceCalcuratable<Vector<T>>, Cloneable {

	public ArrayList<Wrap<T>> nums;
	public int size;

	public Vector(int n) {
		nums = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			nums.add(new Wrap<T>((double) 0));
		}
		size = n;
	}

	public Vector(int n, int dim, T value) {
		this(n);
		SetIns(dim, value);
	}

	public Vector(T... n) {
		nums = new ArrayList<>();
		for (T t : n) {
			nums.add(new Wrap<T>(t));
			size += 1;
		}
	}

	public Vector(Integer... n) {
		nums = new ArrayList<>();
		for (Integer t : n) {
			nums.add(new Wrap<T>(t));
			size += 1;
		}
	}

	/** ベクトルの大きさを求める */
	public T Magnitude() {
		var sum = (Number) Mul(this).Sum();
		return (T) (Number) Math.sqrt(sum.doubleValue());
	}

	/** ベクトルの大きさを求める */
	public T Sum() {
		double sum = 0;
		for (Wrap<T> f : nums) {
			sum += f.doubleValue();
		}
		return (T) (Number) sum;
	}

	/** ベクトルの内積を求める */
	public T Dot(Vector<T> other) {
		return Mul(other).Sum();
	}

	public static <S extends Number> Vector<S> One(int dim) {
		var vec = new Vector<S>(dim);
		vec.SetIns(dim, (S) (Number) 1);
		return vec;
	}

	/** 全要素に対して計算を行い新しいベクトルを生成して返す */
	public Vector<T> CalcurateVector(Vector<T> other, BiFunction<Wrap<T>, Wrap<T>, Wrap<T>> func) {
		int maxSize = Math.max(nums.size(), other.nums.size());
		Vector<T> vec = new Vector<>(maxSize);

		for (int i = 0; i < maxSize; i++) {
			Wrap<T> out;
			try {
				out = func.apply(nums.get(i), other.nums.get(i));
			} catch (Exception e) {
				if (nums.size() < other.nums.size()) {
					out = other.nums.get(i).clone();
				} else {
					out = nums.get(i).clone();
				}
			}
			vec.nums.set(i, out);
		}

		return vec;
	}

	/**
	 * 現在のインスタンスに対して計算を行う
	 * 新しいベクトルは生成されない
	 */
	public void Calcurate_Instance(Vector<T> other, BiConsumer<Wrap<T>, Wrap<T>> func) {
		int minSize = Math.min(nums.size(), other.nums.size());

		for (int i = 0; i < minSize; i++) {
			func.accept(nums.get(i), other.nums.get(i));
		}
	}

	/** ベクトルをキャストする */
	public <S extends Number> Vector<S> Cast() {
		Vector<S> vec = new Vector<>(nums.size());
		for (int i = 0; i < nums.size(); i++) {
			vec.nums.set(i, nums.get(i).Cast());
		}
		return vec;
	}

	/** ベクトルをキャストする */
	public <S extends Vector<? extends Number>> S Cast(Supplier<S> ctor) {
		S vec = ctor.get();
		for (int i = 0; i < nums.size(); i++) {
			vec.nums.set(i, nums.get(i).Cast());
		}
		return vec;
	}

	/** ベクトルの足し算（新しくベクトルを生成する） */
	/*
	 * public <S extends Number, R extends Number> Vector<R> Add(Vector<S> t) {
	 * int maxSize = Math.max(nums.size(), t.nums.size());
	 * Vector<R> vec = new Vector<>(maxSize);
	 * 
	 * for (int i = 0; i < maxSize; i++) {
	 * float f;
	 * try {
	 * f = t.nums.get(i).floatValue() + nums.get(i).floatValue();
	 * // System.out.println(f);
	 * } catch (Exception e) {
	 * if (nums.size() < t.nums.size()) {
	 * f = nums.get(i).floatValue();
	 * } else {
	 * f = t.nums.get(i).floatValue();
	 * }
	 * }
	 * vec.nums.set(i, new Wrap<R>(f));
	 * }
	 * 
	 * return vec;
	 * }
	 * 
	 */

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < nums.size(); i++) {
			str += nums.get(i).toString();
			if (i != nums.size() - 1) {
				str += ",";
			}
		}
		return str;
	}

	public T Get() {
		return nums.get(0).num;
	}

	public T Get(int n) {
		return nums.get(n).num;
	}

	public void SetIns(int index, T t) {
		nums.get(index).num = t;
	}

	public Vector<T> Add(int index, T t) {
		var c = clone();
		c.AddIns(index, t);
		return c;
	}

	@Override
	public Vector<T> Add(Vector<T> t) {
		return CalcurateVector(t, (i, j) -> i.Add(j));
	}

	@Override
	public Vector<T> Sub(Vector<T> t) {
		return CalcurateVector(t, (i, j) -> i.Sub(j));
	}

	@Override
	public Vector<T> Mul(Vector<T> t) {
		return CalcurateVector(t, (i, j) -> i.Mul(j));
	}

	public Vector<T> Mul(T t) {
		var vec = clone();
		for (int i = 0; i < size; i++) {
			vec.nums.get(i).MulIns(t);
		}
		return vec;
	}

	@Override
	public Vector<T> Div(Vector<T> t) {
		return CalcurateVector(t, (i, j) -> i.Div(j));
	}

	public Vector<T> Div(T t) {
		var vec = clone();
		for (int i = 0; i < size; i++) {
			vec.nums.get(i).DivIns(t);
		}
		return vec;
	}

	public void AddIns(int index, T t) {
		nums.get(index).AddIns(t);
	}

	@Override
	public void AddIns(Vector<T> t) {
		Calcurate_Instance(t, (i, j) -> i.AddIns(j));
	}

	@Override
	public void SubIns(Vector<T> t) {
		Calcurate_Instance(t, (i, j) -> i.SubIns(j));
	}

	@Override
	public void MulIns(Vector<T> t) {
		Calcurate_Instance(t, (i, j) -> i.MulIns(j));
	}

	@Override
	public void DivIns(Vector<T> t) {
		Calcurate_Instance(t, (i, j) -> i.DivIns(j));
	}

	@Override
	public Vector<T> clone() {
		try {
			var ins = new Vector<T>(nums.size());
			var insNums = new ArrayList<Wrap<T>>();
			for (int i = 0; i < nums.size(); i++) {
				insNums.add(nums.get(i).clone());
			}
			ins.nums = insNums;
			return ins;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
