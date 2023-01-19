package Sort;

public class CombSort extends Sorting {
    public CombSort(){
        name = "CombSort";
    }

    @Override
    public Comparable[] sortAcs(Comparable[] array) {
        try{
            Comparable[] out = combingSort(array);

            return out;
        }catch(Exception err){
            throw err;
        }
    }

    @Override
    public Comparable[] sortDsc(Comparable[] array) {
        try{
            Comparable[] out = combingSortDsc(array);

            return out;
        }catch(Exception err){
            throw err;
        }
    }

    protected Comparable[] combingSort(Comparable[] array){
        Comparable[] arr = cloneArray(array);

        int gap = arr.length;
        boolean swapped = false;
        while(gap>1 || swapped){
            swapped = false;
            if(gap>1){
                gap = (int) (gap/1.3);
            }
            int i=0;
            while(i+gap < arr.length){
                Comparable curr = (Comparable) arr[i];
                Comparable gapVert = (Comparable) arr[i+gap];
                if( curr.compareTo(gapVert) > 0 ){
                    swap(arr, i, i+gap);
                    swapped = true;
                }
                i++;
            }
        }

        return arr;
    }

    protected Comparable[] combingSortDsc(Comparable[] array){
        Comparable[] arr = cloneArray(array);

        int gap = arr.length;
        boolean swapped = false;
        while(gap>1 || swapped){
            swapped = false;
            if(gap>1){
                gap = (int) (gap/1.3);
            }
            int i=0;
            while(i+gap < arr.length){
                Comparable curr = (Comparable) arr[i];
                Comparable gapVert = (Comparable) arr[i+gap];
                if( curr.compareTo(gapVert) < 0 ){
                    swap(arr, i, i+gap);
                    swapped = true;
                }
                i++;
            }
        }

        return arr;
    }
}
