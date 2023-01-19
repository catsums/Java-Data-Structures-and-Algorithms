public class BubbleSort extends Sorting {
    public BubbleSort(){
        name = "BubbleSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) throws CycleException {
        try{
            DFSCycle(array);
            Object[] order = bubblySort(array);

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
        try{
            DFSCycle(array);
            Object[] order = bubblySortDsc(array);

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
    }

    protected Object[] bubblySort(Vertex[] array){
        Object[] arr = Sorting.cloneArray(array);

        for(int i=0;i<arr.length-1;i++){
            for(int j=arr.length-1;j>=i+1;j--){
                Vertex node = (Vertex) arr[j];
                Vertex before = (Vertex) arr[j-1];

                if(node==null || before==null) continue;

                if( node.getVName().compareTo(before.getVName()) < 0){
                    swap(arr, j, j-1);
                }
            }
            printVertexArr(arr);
        }

        return arr;
    }

    protected Object[] bubblySortDsc(Vertex[] array){
        Object[] arr = Sorting.cloneArray(array);

        for(int i=0;i<arr.length-1;i++){
            for(int j=arr.length-1;j>=i+1;j--){
                Vertex node = (Vertex) arr[j];
                Vertex before = (Vertex) arr[j-1];

                if(node==null || before==null) continue;

                if( node.getVName().compareTo(before.getVName()) > 0){
                    swap(arr, j, j-1);
                }
            }
            printVertexArr(arr);
        }

        return arr;
    }
}
