package Sort;

public class BubbleSort extends Sorting {
    public BubbleSort(){
        name = "BubbleSort";
    }

    @Override
    public Comparable[] sortAcs(Comparable[] array)  {
        try{
            Comparable[] out = bubblySort(array);

            return out;
        }catch(Exception err){
            throw err;
        }
    }

    @Override
    public Comparable[] sortDsc(Comparable[] array)  {
        try{
            Comparable[] out = bubblySortDsc(array);

            return out;
        }catch(Exception err){
            throw err;
        }
    }

    protected Comparable[] bubblySort(Comparable[] array){
        Comparable[] arr = Sorting.cloneArray(array);

        for(int i=0;i<arr.length-1;i++){
            for(int j=arr.length-1;j>=i+1;j--){
                Comparable x = (Comparable) arr[j];
                Comparable y = (Comparable) arr[j-1];

                if(x==null || y==null) continue;

                if( x.compareTo(y) < 0){
                    swap(arr, j, j-1);
                }
            }
        }

        return arr;
    }

    protected Comparable[] bubblySortDsc(Comparable[] array){
        Comparable[] arr = Sorting.cloneArray(array);

        for(int i=0;i<arr.length-1;i++){
            for(int j=arr.length-1;j>=i+1;j--){
                Comparable x = (Comparable) arr[j];
                Comparable y = (Comparable) arr[j-1];

                if(x==null || y==null) continue;

                if( x.compareTo(y) > 0){
                    swap(arr, j, j-1);
                }
            }
        }

        return arr;
    }
}
