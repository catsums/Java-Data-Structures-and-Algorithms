public class SelectionSort extends Sorting {
    
    public SelectionSort(){
        name = "SelectionSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) throws CycleException{
        try{
            DFSCycle(array);
            Object[] order = selectionSort(array);

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
    public String[] sortDsc(Vertex[] array) throws CycleException{
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
            Object[] order = selectionSortDsc(array);

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

    protected Object[] selectionSort(Vertex[] array){
        Object[] arr = Sorting.cloneArray(array);

        for(int i=0;i<arr.length;i++){
            //get smallest from i to end
            int smallest = i;
            for(int s=i;s<arr.length;s++){
                if(arr[s]==null) continue;
                Vertex node = (Vertex) arr[s];
                Vertex small = (Vertex) arr[smallest];
                if(small==null) smallest = s;
                else{
                    if( node.getVName().compareTo(small.getVName()) < 0 ){
                        smallest = s;
                    }
                }
            }
            //swap smallest with curr
            swap(arr, smallest, i);

            printVertexArr(arr);

        }

        return arr;
    }

    protected Object[] selectionSortDsc(Vertex[] array){
        Object[] arr = Sorting.cloneArray(array);

        for(int i=0;i<arr.length;i++){
            //get smallest from i to end
            int smallest = i;
            for(int s=i;s<arr.length;s++){
                if(arr[s]==null) continue;
                Vertex node = (Vertex) arr[s];
                Vertex small = (Vertex) arr[smallest];
                if(small==null) smallest = s;
                else{
                    if( node.getVName().compareTo(small.getVName()) > 0 ){
                        smallest = s;
                    }
                }
            }
            //swap smallest with curr
            swap(arr, smallest, i);

            printVertexArr(arr);

        }

        return arr;
    }
}
