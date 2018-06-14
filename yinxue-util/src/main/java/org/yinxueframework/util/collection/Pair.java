package org.yinxueframework.util.collection;


/**
 * 
 *	二元组类	
 *
 * @author zengjian
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Pair<K, V> {

	private K first;
	private V second;

	public Pair() {
		this.first = null;
		this.second = null;
	}

	public Pair(K first, V second) {
		this.first = first;
		this.second = second;
	}

	public K getFirst() {
		return first;
	}

	public V getSecond() {
		return second;
	}

	public void setFirst(K first) {
		this.first = first;
	}

	public void setSecond(V second) {
		this.second = second;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair<?, ?> other = (Pair<?, ?>) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Pair [first=" + first + ", second=" + second + "]";
	}
}
