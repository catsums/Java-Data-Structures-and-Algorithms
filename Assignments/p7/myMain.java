public class myMain {
    public static void main(String[] args) throws Exception {
        
        // Graph graph = new Graph(5);

        // graph.addVertex("A",5);
        // graph.addVertex("B",5);
        // graph.addVertex("C",5);
        // graph.addVertex("D",5);
        // graph.addVertex("E",5);

        // graph.addEdge("A","C", 1, "AC");
        // graph.addEdge("B","A", 1, "BA");
        // graph.addEdge("B","D", 1, "BD");
        // // graph.addEdge("D","B", 1, "DB");
        // graph.addEdge("C","B", 1, "CB");
        // graph.addEdge("D","C", 1, "DC");
        // graph.addEdge("D","E", 1, "DE");
        // graph.addEdge("C","E", 1, "CE");

        // Vertex vA = graph.getVertex("A");
        // Vertex vB = graph.getVertex("B");
        // Vertex vC = graph.getVertex("C");
        // Vertex vD = graph.getVertex("D");
        // Vertex vE = graph.getVertex("E");

        // My.cout("A access D:");
        // My.cout(graph.access(vA,vD));

        // Vertex[] shortestPath = graph.shortestPath(vA, vD);

        // My.cout("shortestPath A to D:");
        // My.cout(My.arrayToString(shortestPath));

        // My.cout("shortestDist A to D:");
        // My.cout(graph.shortestDistance(vA, vD));

        // My.cout("is A in cycle?:");
        // My.cout(graph.containsCycle(vA));

        // My.cout("All cycles be like");
        // graph.listCycles();

        // My.cout("Number of edges:");
        // My.cout(graph.countEdges());

        // My.cout("DFT from A:"+graph.DFT(vA));
        // My.cout("DFT from B:"+graph.DFT(vB));
        // My.cout("DFT from C:"+graph.DFT(vC));
        // My.cout("DFT from D:"+graph.DFT(vD));
        // My.cout("DFT from E:"+graph.DFT(vE));

        ////////////

        ExampleA();
        ExampleB();

        ////////////

        My.cout("Main end.");
    }

    public static void ExampleA() {
        Graph g = new Graph(5);
        g.addVertex("0", 2);
        g.addVertex("1", 1);
        g.addVertex("2", 1);
        g.addVertex("3", 1);
        g.addVertex("4", 0);

        g.addEdge("0", "1", 1, "01");
        g.addEdge("0", "2", 10, "02");
        g.addEdge("1", "3", 2, "13");
        g.addEdge("2", "3", -10, "23");
        g.addEdge("3", "4", 3, "34");

        System.out.println(g.shortestDistance(g.getVertex("0"), g.getVertex("4")));
        for (Vertex v : g.shortestPath(g.getVertex("0"), g.getVertex("4"))) {
            System.out.print(v + ";");
        }
        System.out.println();
    }

    public static void ExampleB() {
        Graph g = new Graph(5);
        g.addVertex("0", 2);
        g.addVertex("1", 1);
        g.addVertex("2", 1);
        g.addVertex("3", 1);
        g.addVertex("4", 0);
        g.addEdge("0", "2", 3, "02");
        g.addEdge("1", "0", 3, "10");
        g.addEdge("2", "3", 2, "23");
        g.addEdge("2", "4", 3, "24");
        g.addEdge("3", "1", 3, "31");
        g.addEdge("4", "3", 3, "43");

        g.listCycles();

        System.out.println(g.DFT(g.getVertex("0")));
    }

    /*
    Expected output:
    3.0
    0;2;3;4;
    0-2-3-1-0
    0;2;3;1;4
    */
}
