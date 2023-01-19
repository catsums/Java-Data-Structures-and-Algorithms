public class Vertex {
    private String vName;
    private Edge[] edges;
    public Vertex(String vName, int numedges){
        this.vName = vName;
        edges = new Edge[numedges];
    }

    public String getVName(){
        return vName;
    }

    public Edge[] getEdges(){
        return edges;
    }

    public boolean addEdge(Edge e){
        if(e == null)
            return false;

        for(int i=0; i < edges.length; i++)
        {
            if(edges[i] == null)
            {
                edges[i] = e;
                return true;
            }
        }
        return false;
    }

    //Do not change the above functions
    //Implement the functions below 

    public boolean isAccessable(Vertex e){
        return Graph.access(this, e);
    }

    public Edge getEdgeTo(Vertex vert){
        for(Edge edge:edges){
            if(edge==null) continue;
            if(edge.pointB == vert) return edge;
        }
        return null;
    }

    //////HELPERS//////

    public double dist = 0;
    public Vertex prev = null;
    public Vertex pred = null;
    public double num = Double.POSITIVE_INFINITY;
    
    ////remove later
    // @Override
    // public String toString(){
    //     return vName;
    // }

}
