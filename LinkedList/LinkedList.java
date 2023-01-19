
/**
 * @param <TData>
 * 
*/

package LinkedList;

import java.lang.reflect.Array;

public class LinkedList<TData>{
	private LLNode<TData> head;

	private class LLNode<TData>{
		private TData data;
		public LLNode<TData> next;

		public LLNode(TData data){
			this.data = data;
			this.next = null;
		}

		public TData getData(){
			return (TData) this.data;
		}
		public void setData(TData data){
			this.data = data;
		}
	}

	public LinkedList(){
		this.head = null;
	}
	LinkedList(LinkedList<TData> other){
		this.head = null;
		LLNode<TData> ptr = other.getHead();
		int c = 0;
		while(ptr!=null){
			this.push(other.get(c));
			ptr = ptr.next;
			c++;
		}
	}

	public void insert(TData data, int index){
		int ssize = this.size();
		if(index<0 || index>ssize){
			return;
		}
		LLNode<TData> newNode = new LLNode(data);
		if(head == null){
			head = newNode;
		}else if(index == 0){
			newNode.next = head;
			head = newNode;
		}else{
			LLNode<TData> ptr = this.head;
			int c = 0;
			while(c < ssize){
				if(c == index-1){
					newNode.next = ptr.next;
					ptr.next = newNode;
					break;
				}
				ptr = ptr.next;
				c++;
			}
		}
	}

	public TData remove(int index){
		int ssize = this.size();
		if(index<0 || index>=ssize){
			return null;
		}
		TData val = null;
		LLNode<TData> ptr = this.head;
		if(ssize==1){
			val = ptr.getData();
			this.head = null;
			ptr = null;
		}else{
			if(index==0){
				val = ptr.getData();
				this.head = ptr.next;
				ptr = null;
			}else{
				LLNode<TData> prevPtr = null;
				int c = 0;
				while(c<index){
					prevPtr = ptr;
					ptr = ptr.next;
					c++;
				}
				val = ptr.getData();
				ptr = null;
			}
		}
		return (TData) val;
	}

	public void push(TData data){
		this.insert(data, this.size());
	}
	public void unshift(TData data){
		this.insert(data, 0);
	}
	public TData pop(){
		return this.remove(this.size()-1);
	}
	public TData shift(){
		return this.remove(0);
	}

	public void set(int index, TData data){
		LLNode<TData> node = this.getNode(index);
		if(node!=null){
			node.setData(data);
		}
	}

	public TData get(int index){
		LLNode<TData> node = this.getNode(index);
		return (TData) node.getData();
	}

	private LLNode<TData> getNode(int index){
		int ssize = this.size();
		if(index<0 || index>=ssize){
			return null;
		}
		LLNode<TData> ptr = this.head;
		LLNode<TData> x = null;
		if(index==0){
			x = ptr;
		}else{
			int c = 0;
			while(ptr!=null || c<index){
				if(c==index){
					x = ptr;
					break;
				}
				ptr = ptr.next;
				c++;
			}
		}
		return (LLNode<TData>) x;
	}

	public LLNode<TData> getHead(){
		return (LLNode<TData>) this.head;
	}

	public int size(){
		LLNode<TData> ptr = this.head;
		if(this.head == null){
			return 0;
		}
		int c = 0;
		while(ptr != null){
			ptr = ptr.next;
			c++;
		}
		return c;
	}

	public boolean isEmpty(){
		if(this.head == null){
			return true;
		}
		return false;
	}
	public void clear(){
		this.head = null;
	}

	// @SuppressWarnings("unchecked")
	// public TData[] asArray(TData[] sample){
	// 	int ssize = this.size();
		
	// 	LLNode<TData> ptr = this.head;
	// 	TData[] arr = (TData[]) Array.newInstance(sample.getClass().getComponentType(), ssize);

	// 	int c = 0;
	// 	while(ptr != null || c<ssize){
	// 		arr[c] = ptr.getData();
	// 		ptr = ptr.next;
	// 		c++;
	// 	}
	// 	return (TData[]) arr;
	// }
	@SuppressWarnings("unchecked")
	public Object[] toArray(){
		int ssize = this.size();
		
		LLNode<TData> ptr = this.head;
		Object[] arr = new Object[ssize];

		int c = 0;
		while(ptr != null || c<ssize){
			arr[c] = ptr.getData();
			ptr = ptr.next;
			c++;
		}
		return arr;
	}

}