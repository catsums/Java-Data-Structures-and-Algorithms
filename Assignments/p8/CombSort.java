public class CombSort extends Sorting {
    public CombSort(){
        name = "CombSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) throws CycleException {
        try{
            DFSCycle(array);
            Object[] order = combingSort(array);

            String[] out = new String[order.length];

            for(int c=0;c<order.length;c++){
                Vertex vert = (Vertex) order[c];
                if(vert!=null)
                    out[c] = vert.getVName();
            }

            return out;
        }catch(CycleException err){
            throw err;
        }
    }

    @Override
    public String[] sortDsc(Vertex[] array) throws CycleException {
        // try{
        //     Object[] arr = reverseArray(sortAcs(array));
        //     String[] out = new String[arr.length];

        //     for(int c=0;c<out.length;c++){
        //         out[c] = (String) arr[c];
        //     }

        //     return out;
        // }catch(CycleException err){
        //     throw err;
        // }
        try{
            DFSCycle(array);
            Object[] order = combingSortDsc(array);

            String[] out = new String[order.length];

            for(int c=0;c<order.length;c++){
                Vertex vert = (Vertex) order[c];
                if(vert!=null)
                    out[c] = vert.getVName();
            }

            return out;
        }catch(CycleException err){
            throw err;
        }
    }

    protected Object[] combingSort(Vertex[] array){
        Object[] arr = cloneArray(array);

        int gap = arr.length;
        boolean swapped = false;
        while(gap>1 || swapped){
            swapped = false;
            if(gap>1){
                gap = (int) (gap/1.3);
            }
            int i=0;
            while(i+gap < arr.length){
                Vertex curr = (Vertex) arr[i];
                Vertex gapVert = (Vertex) arr[i+gap];
                if( curr.getVName().compareTo(gapVert.getVName()) > 0 ){
                    swap(arr, i, i+gap);
                    swapped = true;
                }
                i++;
            }
            printVertexArr(arr);
            System.out.println("Gap: "+gap);
        }

        return arr;
    }

    protected Object[] combingSortDsc(Vertex[] array){
        Object[] arr = cloneArray(array);

        int gap = arr.length;
        boolean swapped = false;
        while(gap>1 || swapped){
            swapped = false;
            if(gap>1){
                gap = (int) (gap/1.3);
            }
            int i=0;
            while(i+gap < arr.length){
                Vertex curr = (Vertex) arr[i];
                Vertex gapVert = (Vertex) arr[i+gap];
                if( curr.getVName().compareTo(gapVert.getVName()) < 0 ){
                    swap(arr, i, i+gap);
                    swapped = true;
                }
                i++;
            }
            printVertexArr(arr);
            System.out.println("Gap: "+gap);
        }

        return arr;
    }
}
