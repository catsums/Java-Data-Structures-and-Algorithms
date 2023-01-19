
/**
 * @param <TData> data type of node data
 * 
*/

package LinkedList;

public class LinkedListNode<TData>{
	private TData data;
	public LinkedListNode<TData> next;

	public LinkedListNode(TData data){
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