package Heap;

@SuppressWarnings("unchecked")
public class MaxHeap<T extends Comparable<T>> extends Heap<T> {

    public MaxHeap(int capacity) {
	super(capacity);
    }

    ////// You may not change any code above this line //////

    ////// Implement the functions below this line ////// 

    @Override
    public void insert(T elem) {

        //Your code goes here
        if(this.currentSize >= this.capacity){
            //the capacity is full
            return;
        }else{
            this.currentSize++;
            this.mH[this.currentSize] = elem;
            this.heapify( this.mH, this.currentSize );
        }
    }

    public T removeMax() {

        //Your code goes here
        T max = null;
        if(this.currentSize>0){
            max = (T) this.mH[1];
            this.delete(max);
        }
        return max;
    }

    public void delete(T elem) {

	//Your code goes here
        if(this.currentSize<1){
            return;
        }

        int index = 0; boolean found = false;
        int comp;
        for(int i=1;i<=currentSize;i++){
            T item = (T) this.mH[i];

            comp = item.compareTo(elem);
            if(comp == 0){
                found = true;
                index = i;
                break;
            }
        }
        if(!found){
            return;
        }

        this.swap(this.mH, index, this.currentSize);
        this.mH[this.currentSize] = null; //clear
        this.currentSize--;

        this.heapify( this.mH, this.currentSize );

    }


    //Helper functions

    protected void heapify(Comparable<T>[] arr, int len){

        int index = (int) Math.round((new Double(len)/2));

        int comp;

        int largest = index;
        for(int c = index; c > 0; c--){
            int left = this.getLeft(c);
            int right = this.getRight(c);
            if(left <= len){
                comp = arr[left].compareTo((T) arr[largest]);
                if(comp > 0){
                    largest = left;
                }
            }
            if(right <= len){
                comp = arr[right].compareTo((T) arr[largest]);
                if(comp > 0){
                    largest = right;
                }
            }
            comp = arr[c].compareTo((T) arr[largest]);
            if(comp < 0){
                this.swap(arr, c, largest);
            }
        }

    }

}