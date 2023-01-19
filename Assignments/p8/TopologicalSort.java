public class TopologicalSort extends Sorting {
    protected int visit = 0;
    public TopologicalSort(){
        name = "TopologicalSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) throws CycleException {
        try{
            Object[] order = depthFirst(array, true);

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
            Object[] arr = reverseArray(sortAcs(array));
            String[] out = new String[arr.length];

            for(int c=0;c<out.length;c++){
                out[c] = (String) arr[c];
            }

            return out;
        }catch(CycleException err){
            throw err;
        }
        // try{
        //     Object[] order = depthFirst(array, false);

        //     String[] out = new String[order.length];

        //     for(int c=0;c<order.length;c++){
        //         Vertex vert = (Vertex) order[c];
        //         if(vert!=null)
        //             out[c] = vert.getVName();
        //     }

        //     return out;
        // }catch(CycleException err){
        //     throw err;
        // }
    }

    protected Object[] depthFirst(Vertex[] arr, boolean asc) throws CycleException{
        for(Vertex vert:arr){
            if(vert==null) continue;
            vert.num = 0;
            vert.pred = null;
        }
        try{
            Object[] order = new Object[0];
            Vertex unvisited = getUnvistedVertex(arr);

            visit = 1;

            while(unvisited!=null){
                order = topTraversal(unvisited, order, asc);
                unvisited = getUnvistedVertex(arr);
            }
            return order;
        }catch(CycleException err){
            throw err;
        }

    }

    protected Object[] topTraversal(Vertex node, Object[] order, boolean asc)throws CycleException{
        try{
            node.num = visit;
            visit++;
            if(!asc){
                order = Sorting.arrayUnshift(order, node);
            }

            Edge[] edges = node.getEdges();

            for(Edge edge:edges){
                if(edge==null) continue;
                Vertex vert = edge.pointB;
                if(vert.num == 0){
                    vert.pred = node;
                    order = topTraversal(vert, order, asc);
                }else if(vert.num != Double.POSITIVE_INFINITY){
                    //cycle detected
                    throw new CycleException();
                }
            }
            if(asc){
                order = Sorting.arrayUnshift(order, node);
            }
            node.num = Double.POSITIVE_INFINITY;

            return order;
        }catch(CycleException err){
            throw err;
        }
        
    }

}

// class CycleException extends Exception{
//     public String message = "Cycle has been detected";
// }
