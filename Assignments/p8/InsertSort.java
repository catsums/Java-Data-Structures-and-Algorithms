public class InsertSort extends Sorting {

    public InsertSort(){
        name = "InsertSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) throws CycleException{

        try{
            DFSCycle(array);
            Object[] order = insertionSort(array);

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
        try{
            DFSCycle(array);
            Object[] order = insertionSortDsc(array);

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

    protected Object[] insertionSort(Vertex[] array){
        Object[] arr = Sorting.cloneArray(array);

        for(int i=1;i<arr.length;i++){
            Vertex vert = (Vertex) arr[i];
            int j = i-1;
            Vertex node = (Vertex) arr[j];

            while( j>=0 ){
                node = (Vertex) arr[j];
                if(node.getVName().compareTo(vert.getVName()) <= 0) break;

                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = vert;
            printVertexArr(arr);
        }

        return arr;
    }

    protected Object[] insertionSortDsc(Vertex[] array){
        Object[] arr = Sorting.cloneArray(array);

        for(int i=1;i<arr.length;i++){
            Vertex vert = (Vertex) arr[i];
            int j = i-1;
            Vertex node = (Vertex) arr[j];

            while( j>=0 ){
                node = (Vertex) arr[j];
                if(node.getVName().compareTo(vert.getVName()) >= 0) break;

                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = vert;
            printVertexArr(arr);
        }

        return arr;
    }
}
