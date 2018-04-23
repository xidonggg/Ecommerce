package com.tools;

public class pair<E extends Object, F extends Object> {
	private E first;
	private F second;
	
	public pair(){}
	public void setFirst(E first) {
		this.first = first;
	}
	public E getFirst() {
		return first;
	}
	public F getSecond() {
		return second;
	}
	public void setSecond(F second) {
		this.second = second;
	}

}