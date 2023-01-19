public class Graph {
    private Vertex[] vertices;
    public Graph(int numVertex){
        vertices = new Vertex[numVertex];
    }

    public boolean addVertex(String nName, int numVertices){
        int openPos = -1;
        for(int i=0; i < vertices.length; i++)
        {
            if(vertices[i] == null){
                if(openPos == -1)
                    openPos = i;
            } else {
                if(vertices[i].getVName().equals(nName)){
                    return false;
                }
            }
        }
        if(openPos == -1)
            return false;

        vertices[openPos] = new Vertex(nName, numVertices);
        return true;
    }

    public Vertex getVertex(String nName){
        for(int i=0; i < vertices.length; i++){
            if(vertices[i] != null && vertices[i].getVName().equals(nName)){
                return vertices[i];
            }
        }
        return null;
    }

    public boolean addEdge(String pointA, String pointB, float value, String vName){
        Vertex pA = getVertex(pointA);
        Vertex pB = getVertex(pointB);
        if(pA == null || pB == null)
            return false;

        Edge v = new Edge(value, vName);
        v.pointA = pA;
        v.pointB = pB;
        pA.addEdge(v);
        return true;
    }

    //Do not change the above functions
    //Implement the functions below

    public boolean isAccessable(Vertex vertexFrom, Vertex vertexTo){      
        return access(vertexFrom, vertexTo);
    }

    public float shortestDistance(Vertex vertexFrom, Vertex vertexTo){
        float dist = Float.POSITIVE_INFINITY;
        if(!isInArray(vertices, vertexFrom) || !isInArray(vertices, vertexTo)) return dist;
        Vertex[] path = shortestPath(vertexFrom, vertexTo);

        if(path==null || path.length==0) return dist;

        dist = 0;
        for(int i=1;i<path.length;i++){
            Vertex prev = path[i-1];
            Vertex curr = path[i];
            Edge edge = prev.getEdgeTo(curr);
            dist += edge.getValue();
        }

        return dist;

    }

    public Vertex[] shortestPath(Vertex vertexFrom, Vertex vertexTo){
        if(!isInArray(vertices, vertexFrom) || !isInArray(vertices, vertexTo)) return null;

        if(vertexFrom==vertexTo){
            return new Vertex[]{vertexFrom};
        }

        if(!isAccessable(vertexFrom,vertexTo)) return null;

        VertexQueue queue = labelCorrecting(vertexFrom);
        buildPath(vertexTo, vertexFrom, queue);

        Vertex[] arr = new Vertex[queue.size()];
        int c = 0;
        while(!queue.isEmpty()){
            Vertex vert = queue.shift();
            if(c<arr.length){
               arr[c] = vert;
                c++; 
            }else{
                break;
            }
        }

        return arr;

    }

    public boolean containsCycle(Vertex startingVertex){
        if(!isInArray(vertices, startingVertex)) return false;
        VertexCycleList cycleList = DFSCycle();

        Vertex[] cycle = cycleList.getCycle(startingVertex);
        if(cycle!=null){
            return true;
        }
        return false;
    }

    public void listCycles(){
        VertexCycleList cycleList = DFSCycle();

        Vertex[][] cycles = cycleList.getCycles();
        
        for(Vertex[] cycle:cycles){
            if(cycle==null) continue;
            String out = "";

            for(int i=0; i<cycle.length; i++){
                Vertex vert = cycle[i];
                out += vert.getVName();
                if(i<cycle.length-1) out += "-";
            }

            System.out.println(out);
        }
    }

    public int countEdges(){
        Object[] edges = new Object[0];

        for(Vertex vert:vertices){
            if(vert!=null){
                Edge[] vedges = vert.getEdges();
                for(Edge edge:vedges){
                    if(edge==null) continue;
                    edges = arrayPush(edges, edge);
                }
            }
        }

        // My.cout(My.arrayToString(edges));

        return edges.length;
    }

    public String DFT(Vertex startVertex){
        if(!isInArray(vertices, startVertex)) return "";
        Vertex[] list = depthFirst(startVertex);
        String out = "";

        for(int v=0;v<list.length;v++){
            out += list[v].getVName();
            if(v<list.length-1) out += ";";
        }

        return out;
    }

    ///////HELPERS////////

    protected int visit = 1;

    class VertexQueue{
        Vertex[] arr;

        VertexQueue(){
            arr = new Vertex[1];
        }

        public void push(Vertex vert){
            arr[arr.length-1] = vert;
            Vertex[] newArr = new Vertex[arr.length + 1];
            for(int i=0;i<arr.length;i++)
                newArr[i] = arr[i];
            arr = newArr;
        }
        public Vertex pop(){
            if(this.isEmpty()) return null;

            Vertex vert = arr[0];
            Vertex[] newArr = new Vertex[arr.length - 1];
            for(int i=1;i<newArr.length;i++){
                newArr[i-1] = arr[i];
            }
            arr = newArr;

            return vert;
        } 
        public Vertex shift(){
            //[0,1,2,null]
            if(this.isEmpty()) return null;

            Vertex vert;
            if(arr.length>2)
                vert = arr[arr.length-2];
            else
                vert = arr[0];

            Vertex[] newArr = new Vertex[arr.length - 1];
            for(int i=0;i<newArr.length;i++){
                newArr[i] = arr[i];
            }
            arr = newArr;

            return vert;
        } 
        public boolean isEmpty(){
            if(arr.length<1) return true;
            if(arr[0] == null) return true;
            return false;
        }
        public boolean contains(Vertex vert){
            for(Vertex item:arr){
                if(item==null) continue;
                if(item == vert) return true;
            }
            return false;
        }
        public int size(){
            return arr.length - 1;
        }
    }

    class VertexCycleList{
        Vertex[][] cycles;

        VertexCycleList(){
            cycles = new Vertex[0][];
        }

        public void addCycle(Vertex vert){
            if(vert==null) return;
            Vertex[] cycle = new Vertex[0];
            cycle = arrayPush(cycle, vert);
            Vertex curr = vert.pred;
            while(curr!=null){
                if(curr==vert){
                    // My.cout("vert is cur");
                    break;
                }
                cycle = arrayPush(cycle, curr);
                curr = curr.pred;
            }
            // My.cout("cycle push");
            cycle = reverseArray(cycle);
            cycle = arrayUnshift(cycle, vert);
           
            pushCycle(cycle);
        }

        protected void pushCycle(Vertex[] cycle){
            int len = cycles.length+1;
            Vertex[][] newArr = new Vertex[len][];
            for(int i=0;i<cycles.length;i++){
                if(i>=len) break;
                newArr[i] = cycles[i];
            }
            newArr[newArr.length-1] = cycle;
            cycles = newArr;
        }

        public Vertex[] getCycle(Vertex vert){
            if(vert==null) return null;
            for(Vertex[] cycle:cycles){
                for(Vertex item:cycle){
                    if(item==vert) return cycle;
                }
            }
            return null;
        }

        public Vertex[][] getCycles(){
            return cycles;
        }
    }

    protected void buildPath(Vertex back, Vertex front, VertexQueue queue){
        if(back==null) return;
        else{
            queue.push(back);
            if(back != front){
                buildPath(back.prev, front, queue);
            }
        }
    }

    protected VertexQueue labelCorrecting(Vertex start){
        
        for(Vertex vert:vertices){
            if(vert==null) continue;
            vert.dist = Double.POSITIVE_INFINITY;
            vert.prev = null;
        }

        start.dist = 0;
        VertexQueue queue = new VertexQueue();

        queue.push(start);
        Vertex curr = null;
        while(!queue.isEmpty()){
            curr = queue.pop();
            Edge[] edges = curr.getEdges();
            for(Edge edge:edges){
                if(edge==null) continue;
                Vertex vert = edge.pointB;
                double newDist = curr.dist + (double) edge.getValue();
                if(newDist < vert.dist){
                    vert.dist = newDist;
                    vert.prev = curr;
                    if(!queue.contains(vert)){
                        queue.push(vert);
                    }
                }
            }
        }

        return queue;

    }

    protected VertexCycleList DFSCycle(){
        for(Vertex vert:vertices){
            if(vert==null) continue;
            vert.num = 0;
        }
        Vertex zeroVert = getVertexWithZero();
        VertexCycleList cycleList = new VertexCycleList();

        while(zeroVert!=null){
            cycleDetectionDFS(zeroVert, cycleList);
            zeroVert = getVertexWithZero();
            // My.cout("_1");
        }

        return cycleList;
    }

    protected Vertex getVertexWithZero(){
        for(Vertex vert:vertices){
            if(vert==null) continue;
            if(vert.num == 0) return vert;
        }
        return null;
    }

    protected void cycleDetectionDFS(Vertex start, VertexCycleList list){
        start.num ++;
        Edge[] edges = start.getEdges();
        for(Edge edge:edges){
            if(edge==null) continue;
            Vertex vert = edge.pointB;
            if(vert.num == 0){
                vert.pred = start;
                cycleDetectionDFS(vert, list);
            }else if(vert.num != Double.POSITIVE_INFINITY){
                vert.pred = start;
                //cycle detected//
                list.addCycle(vert);
            }
        }
        start.num = Double.POSITIVE_INFINITY;
    }

    protected Vertex[] depthFirst(){
        Vertex[] verts = new Vertex[0];
        for(Vertex vert:vertices){
            if(vert==null) continue;
            vert.num = 0;
        }
        Vertex zeroVert = getVertexWithZero();
        visit = 1;
        while(zeroVert!=null){
            verts = arrayPush(verts, zeroVert);
            verts = DFSTraversal(zeroVert, verts);
            zeroVert = getVertexWithZero();
        }

        return verts;
    }
    protected Vertex[] depthFirst(Vertex start){
        Vertex[] verts = new Vertex[0];
        for(Vertex vert:vertices){
            if(vert==null) continue;
            vert.num = 0;
        }
        Vertex zeroVert = start;
        visit = 1;
        while(zeroVert!=null){
            verts = arrayPush(verts, zeroVert);
            verts = DFSTraversal(zeroVert, verts);
            zeroVert = getVertexWithZero();
        }

        return verts;
    }

    protected Vertex[] DFSTraversal(Vertex start, Vertex[] list){
        start.num = visit;
        visit++;

        Edge[] edges = start.getEdges();

        for(Edge edge:edges){
            if(edge==null) continue;
            Vertex vert = edge.pointB;
            if(vert.num == 0){
                list = arrayPush(list, vert);
                list = DFSTraversal(vert, list);
            }
        }

        return list;
    }

    public static boolean access(Vertex from, Vertex to){
        return access(from, to, new Object[0]);
    }
    public static boolean access(Vertex from, Vertex to, Object[] cache){
        if(from == null) return false;
        if(to == null) return false;

        Edge[] edges = from.getEdges();

        if(from == to){
            return true;
        }
        for(int i=0; i<edges.length;i++){
            if(edges[i] == null) continue;

            Edge edge = edges[i];
            Vertex point = edge.pointB;
            if(point == to){
                return true;
            }
            if(!isInArray(cache, point)){
                cache = arrayPush(cache, point);
                if(access(point, to, cache)){
                    return true;
                }
            }
        }
        return false;
    }

    public static Object[] arrayPush(Object[] arr, Object item){
        Object[] newArr = resizeArray(arr,1);
        newArr[newArr.length-1] = item;
        return newArr;
    }public static Vertex[] arrayPush(Vertex[] arr, Vertex item){
        Vertex[] newArr = resizeArray(arr,1);
        newArr[newArr.length-1] = item;
        return newArr;
    }
    public static Object[] arrayPop(Object[] arr){
        Object[] newArr = resizeArray(arr,-1);
        return newArr;
    }public static Vertex[] arrayPop(Vertex[] arr){
        Vertex[] newArr = resizeArray(arr,-1);
        return newArr;
    }
    public static Vertex[] arrayUnshift(Vertex[] arr, Vertex item){
        Vertex[] newArr = new Vertex[arr.length+1];

        for(int i=1;i<=arr.length;i++){
            newArr[i] = arr[i-1];
        }

        newArr[0] = item;

        return newArr;
    }

    public static Object[] reverseArray(Object[] arr){
        Object[] newArr = new Object[arr.length];

        int c = arr.length-1;
        for(int i=0; i<arr.length; i++){
            newArr[i] = arr[c];
            c--;
        }
        return newArr;
    }public static Vertex[] reverseArray(Vertex[] arr){
        Vertex[] newArr = new Vertex[arr.length];

        int c = arr.length-1;
        for(int i=0; i<arr.length; i++){
            newArr[i] = arr[c];
            c--;
        }
        return newArr;
    }

    public static Object[] resizeArray(Object[] arr, int resize){
        int len = arr.length + resize;
        Object[] newArr = new Object[len];
        for(int i=0;i<arr.length;i++){
            if(i>=len) break;
            newArr[i] = arr[i];
        }
        return newArr;
    }public static Vertex[] resizeArray(Vertex[] arr, int resize){
        int len = arr.length + resize;
        Vertex[] newArr = new Vertex[len];
        for(int i=0;i<arr.length;i++){
            if(i>=len) break;
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public static boolean isInArray(Object[] arr, Object item){
        if(item==null) return false;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == item){
                return true;
            }
        }
        return false;
    }public static boolean isInArray(Vertex[] arr, Vertex item){
        if(item==null) return false;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == item){
                return true;
            }
        }
        return false;
    }
    
}
