package Sort;

public class SelectionSort extends Sorting {
    
    public SelectionSort(){
        name = "SelectionSort";
    }

    @Override
    public Comparable[] sortAcs(Comparable[] array){
        try{
            Comparable[] out = selectionSort(array);

            return out;
        }catch(Exception err){
            throw err;
        }
    }

    @Override
    public Comparable[] sortDsc(Comparable[] array){
        try{
            Comparable[] out = selectionSortDsc(array);

            return out;
        }catch(Exception err){
            throw err;
        }
    }

    protected Comparable[] selectionSort(Comparable[] array){
        Comparable[] arr = Sorting.cloneArray(array);

        for(int i=0;i<arr.length;i++){
            //get smallest from i to end
            int smallest = i;
            for(int s=i;s<arr.length;s++){
                if(arr[s]==null) continue;
                Comparable node = (Comparable) arr[s];
                Comparable small = (Comparable) arr[smallest];
                if(small==null) smallest = s;
                else{
                    if( node.compareTo(small) < 0 ){
                        smallest = s;
                    }
                }
            }
            //swap smallest with curr
            swap(arr, smallest, i);

        }

        return arr;
    }

    protected Comparable[] selectionSortDsc(Comparable[] array){
        Comparable[] arr = Sorting.cloneArray(array);

        for(int i=0;i<arr.length;i++){
            //get smallest from i to end
            int smallest = i;
            for(int s=i;s<arr.length;s++){
                if(arr[s]==null) continue;
                Comparable node = (Comparable) arr[s];
                Comparable small = (Comparable) arr[smallest];
                if(small==null) smallest = s;
                else{
                    if( node.compareTo(small) > 0 ){
                        smallest = s;
                    }
                }
            }
            //swap smallest with curr
            swap(arr, smallest, i);

        }

        return arr;
    }
}
