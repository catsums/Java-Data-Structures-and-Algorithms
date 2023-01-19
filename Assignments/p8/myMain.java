public class myMain {

    public static void main(String[] args){
        cout("\n--------Main Start----------------\n");
        /////////////////////

        myExample();

        //////////////////////

        cout("\n------------Main End-------------\n");
    }

    ///output but lazier
    public static void cout(Object x){
        System.out.println(x);
    }
    public static void coutf(Object x){
        System.out.print(x);
    }
    public static void cout(){
        System.out.println();
    }
    public static void coutf(){
        System.out.print("");
    }

    public static void myExample(){
        Graph graph = new Graph(9);

        String[] verts = {"A","B","C","D","E","F","G","H","I"};

        graph.addVertex("A",5); 
        graph.addVertex("G",5);
        graph.addVertex("D",5);
        graph.addVertex("C",5);
        graph.addVertex("F",5);
        graph.addVertex("E",5);
        graph.addVertex("I",5);
        graph.addVertex("H",5);
        graph.addVertex("B",5);

        graph.addEdge("A","B", 7, "AB");
        graph.addEdge("A","I", 7, "AI");

        graph.addEdge("B","C", 7, "BC");
        graph.addEdge("B","D", 7, "BD");
        graph.addEdge("B","G", 7, "BG");

        graph.addEdge("C","D", 7, "CD");
        graph.addEdge("C","H", 7, "CH");

        graph.addEdge("D","E", 7, "DE");
        graph.addEdge("D","F", 7, "DF");
        graph.addEdge("D","G", 7, "DG");

        graph.addEdge("E","H", 7, "EH");

        graph.addEdge("F","E", 7, "FE");

        graph.addEdge("I","C", 7, "CI");

        //add cycle
        // graph.addEdge("C","A",0,"CA");


        try{
            coutf("Ascending - ");
            graph.printSort(new InsertSort(), graph, true);
            coutf("Descending - ");
            graph.printSort(new InsertSort(), graph, false);
        }catch(CycleException err){
            System.out.println(err.message);
            err.printStackTrace();
        }
        try{
            coutf("Ascending - ");
            graph.printSort(new BubbleSort(), graph, true);
            coutf("Descending - ");
            graph.printSort(new BubbleSort(), graph, false);
        }catch(CycleException err){
            System.out.println(err.message);
            err.printStackTrace();
        }
        try{
            coutf("Ascending - ");
            graph.printSort(new SelectionSort(), graph, true);
            coutf("Descending - ");
            graph.printSort(new SelectionSort(), graph, false);
        }catch(CycleException err){
            System.out.println(err.message);
            err.printStackTrace();
        }
        try{
            coutf("Ascending - ");
            graph.printSort(new CombSort(), graph, true);
            coutf("Descending - ");
            graph.printSort(new CombSort(), graph, false);
        }catch(CycleException err){
            System.out.println(err.message);
            err.printStackTrace();
        }
        try{
            coutf("Ascending - ");
            graph.printSort(new TopologicalSort(), graph, true);
            coutf("Descending - ");
            graph.printSort(new TopologicalSort(), graph, false);
        }catch(CycleException err){
            System.out.println(err.message);
            err.printStackTrace();
        }

        //add cycle
        graph.addEdge("C","A",0,"CA");

        try{
            coutf("Ascending - ");
            graph.printSort(new TopologicalSort(), graph, true);
            coutf("Descending - ");
            graph.printSort(new TopologicalSort(), graph, false);
        }catch(CycleException err){
            System.out.println(err.message);
            err.printStackTrace();
        }

    }
}

/* EXPECTED OUTPUT

Ascending - Sorted using: InsertSort
A;G;D;C;F;E;I;H;B
A;D;G;C;F;E;I;H;B
A;C;D;G;F;E;I;H;B
A;C;D;F;G;E;I;H;B
A;C;D;E;F;G;I;H;B
A;C;D;E;F;G;I;H;B
A;C;D;E;F;G;H;I;B
A;B;C;D;E;F;G;H;I
A|B|C|D|E|F|G|H|I|
Descending - Sorted using: InsertSort
G;A;D;C;F;E;I;H;B
G;D;A;C;F;E;I;H;B
G;D;C;A;F;E;I;H;B
G;F;D;C;A;E;I;H;B
G;F;E;D;C;A;I;H;B
I;G;F;E;D;C;A;H;B
I;H;G;F;E;D;C;A;B
I;H;G;F;E;D;C;B;A
I|H|G|F|E|D|C|B|A|
Ascending - Sorted using: BubbleSort
A;B;G;D;C;F;E;I;H
A;B;C;G;D;E;F;H;I
A;B;C;D;G;E;F;H;I
A;B;C;D;E;G;F;H;I
A;B;C;D;E;F;G;H;I
A;B;C;D;E;F;G;H;I
A;B;C;D;E;F;G;H;I
A;B;C;D;E;F;G;H;I
A|B|C|D|E|F|G|H|I|
Descending - Sorted using: BubbleSort
I;A;G;D;C;F;E;H;B
I;H;A;G;D;C;F;E;B
I;H;G;A;F;D;C;E;B
I;H;G;F;A;E;D;C;B
I;H;G;F;E;A;D;C;B
I;H;G;F;E;D;A;C;B
I;H;G;F;E;D;C;A;B
I;H;G;F;E;D;C;B;A
I|H|G|F|E|D|C|B|A|
Ascending - Sorted using: SelectionSort
A;G;D;C;F;E;I;H;B
A;B;D;C;F;E;I;H;G
A;B;C;D;F;E;I;H;G
A;B;C;D;F;E;I;H;G
A;B;C;D;E;F;I;H;G
A;B;C;D;E;F;I;H;G
A;B;C;D;E;F;G;H;I
A;B;C;D;E;F;G;H;I
A;B;C;D;E;F;G;H;I
A|B|C|D|E|F|G|H|I|
Descending - Sorted using: SelectionSort
I;G;D;C;F;E;A;H;B
I;H;D;C;F;E;A;G;B
I;H;G;C;F;E;A;D;B
I;H;G;F;C;E;A;D;B
I;H;G;F;E;C;A;D;B
I;H;G;F;E;D;A;C;B
I;H;G;F;E;D;C;A;B
I;H;G;F;E;D;C;B;A
I;H;G;F;E;D;C;B;A
I|H|G|F|E|D|C|B|A|
Ascending - Sorted using: CombSort
A;G;B;C;F;E;I;H;D
Gap: 6
A;E;B;C;D;G;I;H;F
Gap: 4
A;D;B;C;E;F;I;H;G
Gap: 3
A;C;B;D;E;F;G;H;I
Gap: 2
A;B;C;D;E;F;G;H;I
Gap: 1
A;B;C;D;E;F;G;H;I
Gap: 1
A|B|C|D|E|F|G|H|I|
Descending - Sorted using: CombSort
I;H;D;C;F;E;A;G;B
Gap: 6
I;H;D;G;F;E;A;C;B
Gap: 4
I;H;E;G;F;D;A;C;B
Gap: 3
I;H;F;G;E;D;B;C;A
Gap: 2
I;H;G;F;E;D;C;B;A
Gap: 1
I;H;G;F;E;D;C;B;A
Gap: 1
I|H|G|F|E|D|C|B|A|
Ascending - Sorted using: TopologicalSort
A|I|B|C|D|G|F|E|H|
Descending - Sorted using: TopologicalSort
H|E|F|G|D|C|B|I|A|
Ascending - Sorted using: TopologicalSort
Cycle has been detected
CycleException
        at ... (Any line of code that pops here doesnt matter)

------------Main End-------------

*/