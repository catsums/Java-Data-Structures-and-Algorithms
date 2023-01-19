package Sort;

public class InsertSort extends Sorting {

    public InsertSort(){
        name = "InsertSort";
    }

    @Override
    public Comparable[] sortAcs(Comparable[] array){

        try{
            Comparable[] out = insertionSort(array);

            return out;
        }catch(Exception err){
            throw err;
        }
    }

    @Override
    public Comparable[] sortDsc(Comparable[] array){
        try{
            Comparable[] out = insertionSortDsc(array);

            return out;
        }catch(Exception err){
            throw err;
        }
        
    }

    protected Comparable[] insertionSort(Comparable[] array){
        Comparable[] arr = Sorting.cloneArray(array);

        for(int i=1;i<arr.length;i++){
            Comparable vert = (Comparable) arr[i];
            int j = i-1;
            Comparable node = (Comparable) arr[j];

            while( j>=0 ){
                node = (Comparable) arr[j];
                if(node.compareTo(vert) <= 0) break;

                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = vert;
        }

        return arr;
    }

    protected Comparable[] insertionSortDsc(Comparable[] array){
        Comparable[] arr = Sorting.cloneArray(array);

        for(int i=1;i<arr.length;i++){
            Comparable vert = (Comparable) arr[i];
            int j = i-1;
            Comparable node = (Comparable) arr[j];

            while( j>=0 ){
                node = (Comparable) arr[j];
                if(node.compareTo(vert) >= 0) break;

                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = vert;
        }

        return arr;
    }
}
