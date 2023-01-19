/**
 * A B+ tree leaf node
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
class BPTreeLeafNode<TKey extends Comparable<TKey>, TValue> extends BPTreeNode<TKey, TValue> {
	
	protected Object[] values;
	
	public BPTreeLeafNode(int order) {
		this.m = order;
		// The strategy used here first inserts and then checks for overflow. 
		// Thus an extra space is required i.e. m instead of m-1.
		// You can change this if needed.
		this.keys = new Object[m];
		this.values = new Object[m];
	}

	@SuppressWarnings("unchecked")
	public TValue getValue(int index) {
		return (TValue)this.values[index];
	}

	public void setValue(int index, TValue value) {
		this.values[index] = value;
	}
	
	@Override
	public boolean isLeaf() {
		return true;
	}

	////// You should not change any code above this line //////

	////// Implement functions below this line //////

	//returns the values array
	public Object[] getVals(){
		return this.values;
	}
	//sets the values array to a new array (unsafe)
	protected void setVals(Object[] vals){
		this.values = vals;
	}

	//get a specific value with null and boundary safe
	@SuppressWarnings("unchecked")
	public TValue getVal(int index){
		if(index<0 || index>=this.values.length) return null;
		return this.getValue(index);
	}
	//set a specific value with null and boundary safe
	public void setVal(int index, TValue value){
		if(index<0 || index>=this.values.length) return;
		this.setValue(index, value);
		return;
	}

}
