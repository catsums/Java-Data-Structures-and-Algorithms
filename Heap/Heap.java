package Heap;

@SuppressWarnings("unchecked")
public abstract class Heap<T extends Comparable<T>> {

    int capacity;
    Comparable<T> mH[];
    int currentSize;

    public Heap(int capacity) {
        this.capacity = capacity;
        mH = new Comparable[capacity+1]; //Use index positions 1 to capacity 
        currentSize = 0;
    }

    protected int getCapacity(){
        return capacity;
    }

    protected int getCurrentSize(){
        return currentSize;
    }

    public void display() {
        for(int i = 1; i <= currentSize; i++) {
            System.out.print(mH[i] + " ");
        }
        System.out.println("");
    }

    ////// You may not change any code above this line //////

    ////// Implement the functions below this line //////

    protected boolean isEmpty() {

        //Your code goes here
        if(this.currentSize>0){
            return false;
        }
        return true;
    }

    public abstract void insert(T elem);


    //Helper functions
    public Object[] toArray(){
        Object[] arr = new Object[this.mH.length];

        for(int i=1; i<arr.length; i++){
            arr[i] = this.mH[i];
        }

        return arr;

    }
    public int currSize(){
        return currentSize;
    }

    public T peek(){
        T top = null;
        if(this.currentSize>0){
            top = (T) this.mH[1];
        }
        return top;
    }

    protected void updateCurrent(){
        int c = 0;
        for(int i=this.capacity; i>0; i--){
            if(this.mH[i]!=null){
                c = i;
                break;
            }
        }
        currentSize = c;
    }

    protected void updateCapacity(int newCapacity){
        Comparable<T>[] newArr = new Comparable[newCapacity];
        for(int i=0; i<this.mH.length-1; i++){
            if(i>newCapacity) break;
            newArr[i] = this.mH[i];
        }

        this.mH = newArr;

        updateCurrent();
    }

    protected static int getLeft(int i){
        return (2*i);
    }
    protected static int getRight(int i){
        return (2*i)+1;
    }
    public static <T> void swap(Comparable<T>[] arr, int i, int j){
        Comparable<T> temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}