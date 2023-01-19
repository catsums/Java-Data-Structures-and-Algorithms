/**
 * A B+ tree internal node
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
class BPTreeInnerNode<TKey extends Comparable<TKey>, TValue> extends BPTreeNode<TKey, TValue> {
	
	protected Object[] references; 
	
	public BPTreeInnerNode(int order) {
		this.m = order;
		// The strategy used here first inserts and then checks for overflow. 
		// Thus an extra space is required i.e. m instead of m-1/m+1 instead of m.
		// You can change this if needed. 
		this.keys = new Object[m];
		this.references = new Object[m + 1];
	}
	
	@SuppressWarnings("unchecked")
	public BPTreeNode<TKey, TValue> getChild(int index) {
		return (BPTreeNode<TKey, TValue>)this.references[index];
	}

	public void setChild(int index, BPTreeNode<TKey, TValue> child) {
		this.references[index] = child;
		if (child != null)
			child.setParent(this);
	}
	
	@Override
	public boolean isLeaf() {
		return false;
	}

	////// You should not change any code above this line //////

	////// Implement functions below this line //////
	//get a child at index with null and boundary safe
	@SuppressWarnings("unchecked")
	public BPTreeNode<TKey, TValue> getRef(int index) {
		if(index<0 || index>=this.references.length) return null;
		return this.getChild(index);
	}
	//set a child at index with null and boundary safe
	public void setRef(int index, BPTreeNode<TKey,TValue> child) {
		if(index<0 || index>=this.references.length) return;
		this.setChild(index, child);
		return;
	}
	//get the references array
	public Object[] getRefs(){
		return references;
	}
	//set the references array to a new array and updates the parent of each child in the array
	protected void setRefs(Object[] refs){
		this.references = refs;
		for(int c=0;c<refs.length;c++){
			this.setRef(c, (BPTreeNode<TKey,TValue>) refs[c]);
		}
	}
	//finds a child and retruns the index it is at, if not found it returns -1
	protected int getIndexForChild(BPTreeNode<TKey,TValue> child){
		int index = -1;
		if(child==null) return -1;
		for(int c=0;c<this.references.length;c++){
			if(this.references[c]==null) break;
			BPTreeNode<TKey,TValue> ref = this.getRef(c);
			if(child == ref){
				index = c;
				break;
			}
		}
		return index;
	}

}