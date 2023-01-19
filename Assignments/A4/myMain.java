import java.util.ArrayList;

public class myMain {
    public static void main(String[] args) throws Exception {
        cout("MAIN START");
        cout("\n-------myExample()-----------\n");
        myExample();
        cout("\n-------myExample2()-----------\n");
        myExample2();
        cout("\n-------myExample3()-----------\n");
        myExample3();
        cout("\n-------StudentExample()-----------\n");
        StudentExample();
        cout("MAIN END");
        return;
    }

    public static void myExample(){
        GraphDB graph = new GraphDB();

        String[] userNames = new String[]{"A", "B", "C", "D", "E", "F", "G"};
        for(int i=0; i < userNames.length; i++){
            graph.addUser(userNames[i], i);
        }

        Tuple<String, String, Integer>[] arr = new Tuple[]{
            new Tuple<>("A", "B", 2),
            new Tuple<>("D", "B", 1),
            new Tuple<>("F", "G", 1),
            new Tuple<>("D", "E", 2),
            new Tuple<>("E", "B", 3),
            new Tuple<>("G", "B", 4),
            new Tuple<>("A", "D", 4),
            new Tuple<>("F", "A", 5),
            new Tuple<>("C", "G", 6),
            new Tuple<>("B", "C", 7),
            new Tuple<>("B", "F", 8),
            new Tuple<>("C", "E", 10)
        };

        for(Tuple<String, String, Integer> tuple: arr){
            graph.addFriendship(getUserId(tuple.t, graph), getUserId(tuple.u, graph), tuple.s);
        }
cout("\nminSpanningTree:");
        Object[] result = sort(graph.minSpanningTree());
        for(Object relationship: result){
            System.out.println((Relationship)relationship);
        }
cout("\ncluster:");
        User[][] res = graph.clusterUsers();
        for(int i=0; i < res.length; i++){
            String temp = "";
            for(int j=0; j < res[i].length; j++){
                temp += res[i][j].toString() + ";";
            }
            System.out.println(temp);
        }
cout("\nGet users at distance 2 from A:");
        Object[] userArr = sort(graph.getUsersAtDistance(graph.getUser("A"), 2));

        for(Object user: userArr){
            System.out.println((User)user);
        }

        // coloring();

    }

    public static void myExample2(){
        GraphDB graph = new GraphDB();

        String[] userNames = new String[]{"A", "B", "C", "D", "E", "F"};
        for(int i=0; i < userNames.length; i++){
            graph.addUser(userNames[i], i);
        }

        Tuple<String, String, Integer>[] arr = new Tuple[]{
            new Tuple<>("A", "D", 1),
            new Tuple<>("A", "F", 2),
            new Tuple<>("B", "C", 3),
            new Tuple<>("F", "C", 4),
            new Tuple<>("B", "E", 5),
            new Tuple<>("E", "D", 6)
        };

        for(Tuple<String, String, Integer> tuple: arr){
            graph.addFriendship(getUserId(tuple.t, graph), getUserId(tuple.u, graph), tuple.s);
        }
cout("\nminSpanningTree:");
        Object[] result = sort(graph.minSpanningTree());
        for(Object relationship: result){
            System.out.println((Relationship)relationship);
        }
cout("\ncluster:");
        User[][] res = graph.clusterUsers();
        for(int i=0; i < res.length; i++){
            String temp = "";
            for(int j=0; j < res[i].length; j++){
                temp += res[i][j].toString() + ";";
            }
            System.out.println(temp);
        }
cout("\nGet users at distance 2 from D:");
        Object[] userArr = sort(graph.getUsersAtDistance(graph.getUser("D"), 3));

        for(Object user: userArr){
            System.out.println((User)user);
        }

        // coloring();

    }
    @SuppressWarnings("unchecked")
    public static void myExample3(){
        GraphDB graphDB = new GraphDB();
        String[] userNames = new String[]{"1", "2", "3", "4", "5"};
        for(int i=0; i < userNames.length; i++){
            graphDB.addUser(userNames[i], i);
        }

        Tuple<String, String, Integer>[] arr = new Tuple[]{
            new Tuple<>("1", "2", 1), 
            new Tuple<>("1", "3", 5),
            new Tuple<>("2", "3", 1),
            new Tuple<>("4", "3", 11),
            new Tuple<>("2", "5", 40),
            new Tuple<>("4", "5", 1),
        };

        for(Tuple<String, String, Integer> tuple: arr){
            graphDB.addFriendship(getUserId(tuple.t, graphDB), getUserId(tuple.u, graphDB), tuple.s);
        }

cout("\nminSpanningTree:");
        Object[] result = sort(graphDB.minSpanningTree());
        for(Object relationship: result){
            System.out.println((Relationship)relationship);
        }
cout("\ncluster:");
        User[][] res = graphDB.clusterUsers();
        for(int i=0; i < res.length; i++){
            String temp = "";
            for(int j=0; j < res[i].length; j++){
                temp += res[i][j].toString() + ";";
            }
            System.out.println(temp);
        }
cout("\nGet users at distance 2 from 1:");
        Object[] userArr = sort(graphDB.getUsersAtDistance(graphDB.getUser("1"), 2));

        for(Object user: userArr){
            System.out.println((User)user);
        }

        // coloring();
    }

    @SuppressWarnings("unchecked")
    public static void StudentExample(){
        GraphDB graphDB = new GraphDB();
        String[] userNames = new String[]{"A", "B", "C", "D", "E", "F", "G"};
        for(int i=0; i < userNames.length; i++){
            graphDB.addUser(userNames[i], i);
        }

        Tuple<String, String, Integer>[] arr = new Tuple[]{
            new Tuple<>("F", "G", 1), 
            new Tuple<>("A", "B", 2),
            new Tuple<>("G", "C", 3),
            new Tuple<>("E", "B", 6),
            new Tuple<>("C", "F", 7),
            new Tuple<>("D", "E", 8),
            new Tuple<>("B", "F", 9),
            new Tuple<>("A", "D", 15),
            new Tuple<>("E", "F", 15),
            new Tuple<>("A", "C", 23)
        };

        for(Tuple<String, String, Integer> tuple: arr){
            graphDB.addFriendship(getUserId(tuple.t, graphDB), getUserId(tuple.u, graphDB), tuple.s);
        }

        // My.cout(graphDB);
cout("\nminSpanningTree:");
        Object[] result = sort(graphDB.minSpanningTree());
        for(Object relationship: result){
            System.out.println((Relationship)relationship);
        }
cout("\ncluster:");
        User[][] res = graphDB.clusterUsers();
        for(int i=0; i < res.length; i++){
            String temp = "";
            for(int j=0; j < res[i].length; j++){
                temp += res[i][j].toString() + ";";
            }
            System.out.println(temp);
        }
cout("\nGet users at distance 2 from A:");
        Object[] userArr = sort(graphDB.getUsersAtDistance(graphDB.getUser("A"), 2));

        for(Object user: userArr){
            System.out.println((User)user);
        }

        coloring();
    }

    public static int getUserId(String userName, GraphDB graphDB){
        return graphDB.getUser(userName).userID;
    }

    public static void coloring(){
        cout("coloring()");
        GraphDB graphDB = new GraphDB();
        String[] uName = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        for(int i=0; i < uName.length; i++){
            graphDB.addUser(uName[i], i);
        }

        String[][] edges = new String[][]{{"a","e"}, {"a", "f"}, {"a", "g"}, {"b", "e"}, {"b", "c"}, {"b", "h"}, {"c", "g"}, {"d", "f"}, {"d", "g"}, {"f", "g"}, {"f", "h"}, {"g", "h"}};
        for(String[] edge : edges){
            graphDB.addFriendship(getUserId(edge[0], graphDB), getUserId(edge[1], graphDB), 0);
        }

        User[][] res = graphDB.clusterUsers();
        for(int i=0; i < res.length; i++){
            String temp = "";
            for(int j=0; j < res[i].length; j++){
                temp += res[i][j].toString() + ";";
            }
            System.out.println(temp);
        }    
    }

    private static <T> Object[] sort(T[] sort){
        ArrayList<T> result = new ArrayList<>();
        for(T relationship: sort){
            result.add(relationship);
        }

        ArrayList<T> temp = new ArrayList<>();
        while(!result.isEmpty()){
            int maxVal = Integer.MIN_VALUE;
            T maxRelationship = result.get(0);
            for(T relationship: result){
                if(relationship.toString().hashCode() > maxVal){
                    maxVal = relationship.toString().hashCode();
                    maxRelationship = relationship;
                }
            }
            temp.add(maxRelationship);
            result.remove(maxRelationship);
        }

        return temp.toArray();
    }
    public static void cout(Object x){
        System.out.println(x);
    }
}

class Tuple<T,U,S>{
    public T t;
    public U u;
    public S s;

    public Tuple(T t, U u, S s){
        this.s = s;
        this.t = t;
        this.u = u;
    }
}

/* EXPECTED OUTPUT

MAIN START

-------myExample()-----------


minSpanningTree:
A[0]-(2.0)->B[1]
F[5]-(1.0)->G[6]
B[1]-(1.0)->D[3]
B[1]-(4.0)->G[6]
C[2]-(6.0)->G[6]
D[3]-(2.0)->E[4]

cluster:
B[1];
A[0];E[4];G[6];
C[2];D[3];F[5];

Get users at distance 2 from A:
G[6]
E[4]
C[2]

-------myExample2()-----------


minSpanningTree:
A[0]-(1.0)->D[3]
A[0]-(2.0)->F[5]
B[1]-(3.0)->C[2]
B[1]-(5.0)->E[4]
C[2]-(4.0)->F[5]

cluster:
A[0];C[2];E[4];
B[1];D[3];F[5];

Get users at distance 2 from D:
C[2]

-------myExample3()-----------


minSpanningTree:
1[0]-(1.0)->2[1]
2[1]-(1.0)->3[2]
3[2]-(11.0)->4[3]
4[3]-(1.0)->5[4]

cluster:
2[1];4[3];
3[2];5[4];
1[0];

Get users at distance 2 from 1:
5[4]
4[3]

-------StudentExample()-----------


minSpanningTree:
D[3]-(8.0)->E[4]
A[0]-(2.0)->B[1]
F[5]-(1.0)->G[6]
B[1]-(6.0)->E[4]
C[2]-(3.0)->G[6]
B[1]-(9.0)->F[5]

cluster:
A[0];F[5];
B[1];C[2];D[3];
E[4];G[6];

Get users at distance 2 from A:
G[6]
F[5]
E[4]
coloring()
b[1];g[6];
c[2];e[4];f[5];
a[0];d[3];h[7];
MAIN END

*/
