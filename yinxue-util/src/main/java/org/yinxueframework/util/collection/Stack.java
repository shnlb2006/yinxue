package org.yinxueframework.util.collection;

import java.util.LinkedList;

/**
 * 模拟栈数据结构
 * @author zengjian
 *
 * @param <T>
 */
public class Stack<T> {
	
	/**
	 * 数据结构链表
	 */
	private final LinkedList<T> stackList;
	
	/**
	 *  默认初始化构造
	 */
	public Stack() {
		stackList = new LinkedList<T>();
	}
	
	/**
	 * 传入一个链表初始构造
	 * @param stackList
	 */
	public Stack(LinkedList<T> stackList) {
		this.stackList = stackList;
	}
	
	/**
	 *  只能从头位置插入
	 * @param element
	 */
	public void push(T element) {
		stackList.add(0, element);
	}
	
	/**
	 * 只能从头位置删除
	 * @return
	 */
	public T pop() {
		return stackList.remove(0);
	}
	
	
}
