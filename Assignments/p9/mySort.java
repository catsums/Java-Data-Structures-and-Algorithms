// Name: Cassim
// Student number: u19024895
import java.util.Arrays;
public class mySort
{
	
	////// Implement the functions below this line //////
	
	/********** MERGE **********/
	public static <T extends Comparable<? super T>> void mergesort(T[] data, int first, int last, boolean debug)
	{

		// Your code here

		if(first < last && data.length>1){
			int mid = (int) (first + last)/2;

			mergesort(data, first, mid, debug);
			mergesort(data, mid+1, last, debug);
			merge(data, first, last, debug);
		}

	}
     
	private static <T extends Comparable<? super T>> void merge(T[] data, int first, int last, boolean debug)
	{

		// Your code here
		int comp;

		int mid = (int) (first + last)/2;

		int i1 = mid - first + 1, i2 = last - mid;

		// T[] tempL = (T[]) new Object[i1];
		// T[] tempR = (T[]) new Object[i2];

		myArrayList<T> tempL = new myArrayList<T>();
		myArrayList<T> tempR = new myArrayList<T>();

		for(int i=first; i<=mid; i++){
			tempL.add(data[i]);
		}
		for(int i=mid+1; i<=last; i++){
			tempR.add(data[i]);
		}

		int i = 0; int j = 0; int k = first;

		while( i < i1 && j < i2){
			comp = tempL.get(i).compareTo(tempR.get(j));
			if(comp <= 0){
				data[k] = tempL.get(i); i++;
			}else{
				data[k] = tempR.get(j); j++;
			}
			k++;
		}

		while(i < i1){
			data[k] = tempL.get(i);
			i++; k++;
		}
		while(j < i2){
			data[k] = tempR.get(j);
			j++; k++;
		}
		
        
		//DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}
     
	/********** COUNTING **********/
	public static void countingsort(int[] data, boolean debug)
	{

		// Your code here
		int largest = data[0];
		int[] temp = new int[data.length];

		for(int i=1; i<data.length; i++){
			if(largest < data[i]){
				largest = data[i];
			}
		}
		int[] count = new int[largest+1];
		for(int i=0;i<=largest;i++){
			count[i] = 0;
		}
		for(int i=0;i<data.length;i++){
			count[data[i]]++;
		}
		for(int i=1;i<=largest;i++){
			count[i] = count[i-1] + count[i];
		}
		for(int i=data.length-1;i>=0;i--){
			temp[count[data[i]]-1] = data[i];
			count[data[i]]--;
		}

		for(int i=0; i<data.length; i++){
			data[i] = temp[i];
		}

		//DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}

	///HELPERS////

	static class myArrayList<T extends Comparable<? super T>>{
		private Object[] arr;

		myArrayList(){
			this.arr = new Object[0];
		}

		public T[] toArray(){
			Object[] newArr = resizeArray(this.arr, 0);

			return (T[]) newArr;

		}

		public void add(T item){
			this.arr = arrayPush(this.arr, item);
		}

		public T remove(int index){
			if(index<0 || index>=this.arr.length){
				return null;
			}

			T item = (T) this.arr[index];
			this.arr[index] = null;

			return item;
		}

		public void set(int index, T item){
			if(index<0) return;

			if(index >= this.arr.length){
				this.arr = resizeArray(this.arr, index-this.arr.length+1);
			}

			this.arr[index] = item;
		}

		public T get(int index){
			if(index >= 0 && index < this.arr.length){
				return (T) this.arr[index];
			}
			return null;
		}

		public int size(){
			return this.arr.length;
		}

		public void push(T item){
			this.arr = arrayPush(this.arr, item);
		}
		public void unshift(T item){
			this.arr = arrayUnshift(this.arr, item);
		}
		public T pop(){
			T item = (T) this.arr[this.arr.length-1];
			this.arr = arrayPop(this.arr);
			return item;
		}
		public T shift(){
			T item = (T) this.arr[0];
			this.arr = arrayShift(this.arr);
			return item;
		}


		protected static Object[] arrayPush(Object[] arr, Object item){
	        Object[] newArr = resizeArray(arr,1);
	        newArr[newArr.length-1] = item;
	        return newArr;
	    }
	    protected static Object[] arrayPop(Object[] arr){
	        Object[] newArr = resizeArray(arr,-1);
	        return newArr;
	    }
	    protected static Object[] arrayUnshift(Object[] arr, Object item){
	        Object[] newArr = new Object[arr.length+1];

	        for(int i=1;i<=arr.length;i++){
	            newArr[i] = arr[i-1];
	        }

	        newArr[0] = item;

	        return newArr;
	    }
	    protected static Object[] arrayShift(Object[] arr){
	    	shiftArrayLeft(arr);
	        Object[] newArr = resizeArray(arr,-1);
	        return newArr;
	    }

	    protected static Object[] reverseArray(Object[] arr){
	        Object[] newArr = new Object[arr.length];

	        int c = arr.length-1;
	        for(int i=0; i<arr.length; i++){
	            newArr[i] = arr[c];
	            c--;
	        }
	        return newArr;
	    }

	    protected static Object[] resizeArray(Object[] arr, int resize){
	        int len = arr.length + resize;
	        Object[] newArr = new Object[len];
	        for(int i=0;i<arr.length;i++){
	            if(i>=len) break;
	            newArr[i] = arr[i];
	        }
	        return newArr;
	    }

	    protected static boolean isInArray(Object[] arr, Object item){
	        if(item==null) return false;
	        for(int i=0; i<arr.length; i++){
	            if(arr[i] == item){
	                return true;
	            }
	        }
	        return false;
	    }
	    protected static Object shiftArrayRight(Object[] arr, int start){
			if(arr.length<1) return null;
			if(start<0 || start>=arr.length) return null;

			Object excess = null;

			for(int c=arr.length-1;c>=start;c--){
				if(c == arr.length-1){
					excess = arr[c];
				}else{
					arr[c+1] = arr[c];
				}
				arr[c] = null;
			}

			return excess;

		}protected static Object shiftArrayRight(Object[] arr){
			return shiftArrayRight(arr, 0);
		}

		protected static Object shiftArrayLeft(Object[] arr, int end){
			if(arr.length<1) return null;
			if(end<0 || end>=arr.length) return null;

			Object excess = null;

			for(int c=0;c<=end;c++){
				if(c == 0){
					excess = arr[c];
				}else{
					arr[c-1] = arr[c];
				}
				arr[c] = null;
			}

			return excess;

		}protected static Object shiftArrayLeft(Object[] arr){
			return shiftArrayLeft(arr, arr.length-1);
		}

	}

}