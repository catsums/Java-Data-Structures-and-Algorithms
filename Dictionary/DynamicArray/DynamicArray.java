

/**
 * @param <TData> data type of node data
 * 
*/
package Dictionary.DynamicArray;

public class DynamicArray{
	protected Object[] arr;
	private int size = 0;
	private int numOfElements = 0;

	//constructor
	public DynamicArray(){
		this.arr = new Object[0];
		this.size = 0;
		this.numOfElements = 0;
	}public DynamicArray(int ssize){
		if(ssize<=0){
			ssize = 0;
		}
		this.arr = new Object[ssize];
		this.size = ssize;
		this.numOfElements = 0;
	}public DynamicArray(Object[] arr){
		this.arr = new Object[arr.length];
		this.size = arr.length;
		this.numOfElements = 0;

		for(int i=0;i<this.size;i++){
			Object x = arr[i];
			this.arr[i] = x;
			if(x!=null){
				this.numOfElements++;
			}
		}
	}
	//copy constructor
	public DynamicArray(DynamicArray other){
		this.arr = new Object[other.length()];
		this.size = other.length();
		this.numOfElements = 0;

		for(int i=0;i<this.size;i++){
			Object x = other.get(i);
			this.arr[i] = x;
			if(x!=null){
				this.numOfElements++;
			}
		}
	}
	//insertion
	public void insert(int index, Object data){
		if(index<0){
			return;
		}
		if(index>=this.size){
			this.resize(index-this.size+1);
			this.arr[index] = data;
		}else{
			Object tempA = data;
			Object tempB;
			this.resize(1);
			for(int i=index;i<this.size;i++){
				tempB = this.arr[i];
				if(tempA==null){
					break;
				}
				this.arr[i] = tempA;
				tempA = tempB;
			}
			this.arr[this.size-1] = tempA;
		}
		if(data!=null){
			this.numOfElements++;
		}
	}
	//remove
	public Object remove(int index){
		if(index<0||index>=this.size){
			return null;
		}
		Object val = this.arr[index];
		//shift items
		for(int i=index+1;i<this.size;i++){
			this.arr[i-1] = this.arr[i];
		}
		this.arr[this.size-1] = null;
		this.resize(-1);
		if(val!=null){
			this.numOfElements--;
		}
		return val;
	}
	//stack and queue operations
	public void push(Object data){
		this.insert(this.size,data);
	}
	public void unshift(Object data){
		this.insert(0, data);
	}
	public Object pop(){
		return this.remove(this.size-1);
	}
	public Object shift(){
		return this.remove(0);
	}

	//forEach function
	interface forEachFunction1{
		void call(Object elem);
	}public void forEach(forEachFunction1 func){
		for(int i=0;i<this.size;i++){
			func.call( this.arr[i] );
		}
	}
	interface forEachFunction2{
		void call(Object elem, int index);
	}public void forEach(forEachFunction2 func){
		for(int i=0;i<this.size;i++){
			func.call( this.arr[i], i);
		}
	}
	interface forEachFunction3{
		void call(Object elem, int index, DynamicArray array);
	}public void forEach(forEachFunction3 func){
		for(int i=0;i<this.size;i++){
			func.call( this.arr[i], i, this );
		}
	}

	//map function
	interface mapFunction1{
		Object call(Object elem);
	}
	public DynamicArray map(mapFunction1 func){
		DynamicArray newArr = new DynamicArray();
		for(int i=0;i<this.size;i++){
			newArr.push(func.call( this.arr[i] ));
		}
		return newArr;
	}
	interface mapFunction2{
		Object call(Object elem, int index);
	}
	public DynamicArray map(mapFunction2 func){
		DynamicArray newArr = new DynamicArray();
		for(int i=0;i<this.size;i++){
			newArr.push(func.call( this.arr[i], i ));
		}
		return newArr;
	}
	interface mapFunction3{
		Object call(Object elem, int index, DynamicArray array);
	}
	public DynamicArray map(mapFunction3 func){
		DynamicArray newArr = new DynamicArray();
		for(int i=0;i<this.size;i++){
			newArr.push(func.call( this.arr[i], i, this ));
		}
		return newArr;
	}

	//filter function
	interface filterFunction1{
		boolean call(Object elem);
	}public DynamicArray filter(filterFunction1 func){
		DynamicArray newArr = new DynamicArray();
		for(int i=0;i<this.size;i++){
			if(func.call( this.arr[i])){
				newArr.push(this.arr[i]);
			}
		}
		return newArr;
	}
	interface filterFunction2{
		boolean call(Object elem, int index);
	}public DynamicArray filter(filterFunction2 func){
		DynamicArray newArr = new DynamicArray();
		for(int i=0;i<this.size;i++){
			if(func.call( this.arr[i], i)){
				newArr.push(this.arr[i]);
			}
		}
		return newArr;
	}
	interface filterFunction3{
		boolean call(Object elem, int index, DynamicArray array);
	}public DynamicArray filter(filterFunction3 func){
		DynamicArray newArr = new DynamicArray();
		for(int i=0;i<this.size;i++){
			if(func.call( this.arr[i], i, this )){
				newArr.push(this.arr[i]);
			}
		}
		return newArr;
	}
	
	//concat an array to another
	public DynamicArray concat(DynamicArray other){
		DynamicArray newArr = new DynamicArray();
		for(int i=0;i<this.length();i++){
			newArr.push(this.get(i));
		}
		for(int i=0;i<other.length();i++){
			newArr.push(other.get(i));
		}
		return newArr;
	}

	//shifting
	private void shiftLeft(){
		Object[] newArr = new Object[this.size];
		int num = 0;
		for(int i=0;i<this.size;i++){
			newArr[i] = this.arr[i+1];
			if(newArr[i]!=null){
				num++;
			}
		}
		this.arr = newArr;
	}
	private void shiftLeft(int step){
		Object[] newArr = new Object[this.size];
		int num = 0;
		for(int i=0;i<this.size;i++){
			newArr[i] = this.arr[i+step];
			if(newArr[i]!=null){
				num++;
			}
		}
		this.arr = newArr;
	}
	private void shiftRight(){
		Object[] newArr = new Object[this.size];
		int num = 0;
		for(int i=0;i<this.size;i++){
			newArr[i+1] = this.arr[i];
			if(newArr[i]!=null){
				num++;
			}
		}
		this.arr = newArr;
	}
	private void shiftRight(int step){
		Object[] newArr = new Object[this.size];
		int num = 0;
		for(int i=0;i<this.size;i++){
			newArr[i+step] = this.arr[i];
			if(newArr[i]!=null){
				num++;
			}
		}
		this.arr = newArr;
	}

	//setting and getting
	public void set(int index, Object data){
		if(index<0){
			return;
		}
		if(index>=this.size){
			this.insert(index,data);
		}else{
			this.arr[index] = data;
		}
	}

	public Object get(int index){
		if(index<0 || index>=this.size){
			return null;
		}
		return this.arr[index];
	}
	//trimming
	public void trim(){
		if(this.size<1){
			return;
		}
		int start = this.getStartIndex();
		int end = this.getEndIndex();
		int newSize = end-start+1;
		Object[] newArr = new Object[newSize];
		for(int i=0;i<newSize;i++){
			newArr[i] = this.arr[start+i];
		}
		this.size = newSize;
		this.arr = newArr;
	}
	public void trimStart(){
		if(this.size<1){
			return;
		}
		int start = this.getStartIndex();
		
		int newSize = this.size-start;
		Object[] newArr = new Object[newSize];
		for(int i=0;i<newSize;i++){
			newArr[i] = this.arr[start+i];
		}
		this.size = newSize;
		this.arr = newArr;
	}
	public void trimEnd(){
		if(this.size<1){
			return;
		}
		int end = this.getEndIndex();

		int newSize = end+1;
		Object[] newArr = new Object[newSize];
		for(int i=0;i<newSize;i++){
			newArr[i] = this.arr[i];
		}
		this.size = newSize;
		this.arr = newArr;
	}
	//getting values at index
	protected int getStartIndex(){
		int start = 0;
		//set start
		for(int i=0;i<this.size;i++){
			if(this.arr[i]!=null){
				start = i;
				break;
			}
		}
		if(start<0){
			start = 0;
		}
		if(start>=this.size){
			start = this.size-1;
		}
		return start;
	}
	protected int getEndIndex(){
		int end = 0;
		//set end by counting backwards
		for(int i=this.size-1;i>=0;i--){
			if(this.arr[i]!=null){
				end = i;
				break;
			}
		}
		if(end<0){
			end = 0;
		}
		if(end>=this.size){
			end = this.size-1;
		}
		return end;
	}
	//remove all null values
	public void removeEmpty(){
		this.trim();
		Object[] newArr = new Object[numOfElements];
		int c = 0;
		for(int i=0;i<this.size;i++){
			if(this.arr[i]!=null){
				newArr[c] = this.arr[i];
				c++;
			}
		}
		this.size = numOfElements;
		this.arr = newArr;
	}
	//get length of array
	public int length(){
		return this.size;
	}
	//check if empty of elements
	public boolean isEmpty(){
		if(this.numOfElements<1){
			return true;
		}
		return false;
	}
	//set all values to null
	public void clear(){
		for(int i=0;i<this.arr.length;i++){
			this.arr[i] = null;
		}
		this.numOfElements = 0;
	}
	//set array size to 0
	public void empty(){
		this.arr = new Object[0];
		this.size = 0;
		this.numOfElements = 0;
	}
	//resize array
	private void resize(int howMuch){
		int newSize = this.size + howMuch;
		Object[] newList = new Object[newSize];
		int num = 0;
		for(int i=0;i<newSize;i++){
			if(i<this.size){
				newList[i] = this.arr[i];
				if(this.arr[i]!=null){
					num++;
				}
			}
		}
		this.clear();
		this.arr = newList;
		this.size = newSize;
		this.numOfElements = num;
	}
	//create primitive array
	public Object[] asArray(){
		Object[] out = new Object[this.size];
		for(int i=0;i<this.size;i++){
			out[i] = this.arr[i];
		}
		return out;
	}
	//to string
	@Override
	public String toString(){
		String out = "[ ";
		int len = this.length();
		for(int i=0;i<len;i++){
			out += this.get(i);
			if(i<len-1){
				out+=" , ";
			}
		}
		out+=" ]";
		return out;
	}
}