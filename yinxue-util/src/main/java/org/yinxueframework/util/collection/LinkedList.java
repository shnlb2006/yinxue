package org.yinxueframework.util.collection;

import java.util.Collection;

public class LinkedList<E> {
	
//	private transient int size = 0;
//	private Node<E> first;
//	private Node<E> last;
	
	public LinkedList(){
		
	}
	
	public LinkedList(Collection<E> collection){
		this();
		addAll(collection);
	}
	
	private void addAll(Collection<? extends E> c) {
		if (null == c || 0 == c.size()) {
			throw new RuntimeException("collection is Empty");
		}
		Object[] arr = c.toArray();
		Node<E> pre,succ;
	}

	private static class Node<E> {
		E item;
		Node<E> pre; //前一个节点
		Node<E> next; // 后一个节点
		
		@SuppressWarnings("unused")
		Node(E item, Node<E> pre,Node<E> next) {
			this.item = item;
			this.pre = pre;
			this.next = next;
		}
	} 
	
	
	
	
	
	
	
	
}
