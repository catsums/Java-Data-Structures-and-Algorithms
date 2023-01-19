package Heap;

@SuppressWarnings("unchecked")
public class MinHeap<T extends Comparable<T>> extends Heap<T> {

    public MinHeap(int capacity) {
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
            // T top = this.peek();
            // if(top!=null){
            //     int comp = top.compareTo(elem);
            //     if(comp > 0){
            //         this.swap(this.mH, 1, this.currentSize-1);
            //     }
            // }
            this.heapify( this.mH, this.currentSize );
        }
    }

    public T removeMin() {

        //Your code goes here
        T min = null;
        if(this.currentSize>0){
            min = (T) this.mH[1];
            this.delete(min);
        }
        return min;
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

        // if(this.currentSize>0){
        //     T top = this.peek();
        //     for(int i=1;i<=this.currentSize;i++){
        //         comp = top.compareTo((T) this.mH[i]);
        //         if(comp > 0){
        //             this.swap(this.mH, 1, i);
        //         }
        //     }
        // }

        this.heapify( this.mH, this.currentSize );
        
    }


    //Helper functions

    protected void heapify(Comparable<T>[] arr, int len){

        int index = (int) Math.round((new Double(len)/2));

        int comp;

        int smallest = index;
        for(int c = index; c > 0; c--){
            int left = this.getLeft(c);
            int right = this.getRight(c);
            if(left <= len){
                comp = arr[left].compareTo((T) arr[smallest]);
                if(comp < 0){
                    smallest = left;
                }
            }
            if(right <= len){
                comp = arr[right].compareTo((T) arr[smallest]);
                if(comp < 0){
                    smallest = right;
                }
            }
            comp = arr[c].compareTo((T) arr[smallest]);
            if(comp > 0){
                this.swap(arr, c, smallest);
            }
        }

    }

}