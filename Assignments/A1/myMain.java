public class myMain {
    public static void main(String[] args) {
        Interface interface1 = new Interface();
        for(int i=-1; i <= 1; i++)
        {
            for(int j=1; j >= -1; j--)
            {
                interface1.addPoint(new ExampleFunction1(), i, j);
            }
        }
        System.out.println(interface1.printFunctionValues((new ExampleFunction1()).getFunctionName()));
        for(int i=-2; i <= 2; i++)
        {
            for(int j=-2; j <= 2; j++)
            {
                interface1.addPoint(new ExampleFunction2(), i, j);
            }
        }
        System.out.println(interface1.printFunctionValues((new ExampleFunction2()).getFunctionName()));
        Node n1 = interface1.getPoint(1, 1);
        for(int i=0; i < 6; i++)
        {
            System.out.print(n1.getNodeLinks()[i]);
        }
        System.out.println();
        Node n2 = interface1.getPoint(2, 2);
        for(int i=0; i < 6; i++)
        {
            System.out.print(n2.getNodeLinks()[i]);
        }
        System.out.println();
        for(int i=0; i < 4; i++)
        {
            System.out.println("Count in Q" + i + ": " + interface1.numPointsPerQuadrant()[i]);
        }
        System.out.println("Number of nodes/points: " + interface1.countNumberOfPoints());
        System.out.println(n1.getFunction().getFunctionName());
        System.out.println(n1.nextVal.getFunction().getFunctionName());
        // System.out.println(n1.prevVal.getFunction().getFunctionName());
        System.out.println(n2.getFunction().getFunctionName());
        Node n3 = interface1.removePoint(1, 1);
        if(n3 == n1)
            System.out.println("Correct");
        else 
            System.out.println("Problem");
        Node n4 = interface1.getPoint(1, 1);
        if(n4 != n1)
            System.out.println("Correct");
        else 
            System.out.println("Problem");
        System.out.println(n4.getFunction().getFunctionName());
        System.out.println(n4.getValue() + " == " + interface1.calculateValue((new ExampleFunction2()), 1, 1));
    }
}

class ExampleFunction1 extends Function{
    public ExampleFunction1(){
        functionName = "Example function 1";
    }

    public float calculate(int v1, int v2){
        return Math.abs(v1+v2);
    }

    public Function clone(){
        return new ExampleFunction1();
    }
}

class ExampleFunction2 extends Function{
    public ExampleFunction2(){
        functionName = "Example function 2";
    }

    public float calculate(int v1, int v2){
        return Math.max(v1, v2);
    }

    public Function clone(){
        return new ExampleFunction2();
    }
}

/*
Expected output:
2;0;0;2
-2;-1;1;2;-1;-1;1;2;1;1;1;2;2;2;2;2
U[1][2]{2}D[1][0]{0}R[2][1]{2}L[0][1]{0}P[1][1]{1}N[][]{}
U[][]{}D[2][1]{2}R[][]{}L[1][2]{2}P[][]{}N[][]{}
Count in Q0: 5
Count in Q1: 5
Count in Q2: 5
Count in Q3: 5
Number of nodes/points: 20
Example function 1
Example function 2
Example function 2
Correct
Correct
Example function 2
1.0 == 1.0
*/

// public class myMain {
//     public static void main(String[] args) {

//         My.cout("\n|\tMain Start\t|\n");

//         // String[] arr = {
//         //     "Xyz","Abc", "abcd", "123", "231", "oop", "ooq", "ooP", "10", "1", "100", "101", "102"
//         // };

//         // String[] newArr = bubbleSortStrings(arr);

//         // My.cout("Arr:");
//         // My.cout(My.arrayToString(arr));
//         // My.cout("Sorted Arr:");
//         // My.cout(My.arrayToString(newArr));

//         //////////////////////////////////////
        
//         // Interface yum = new Interface();

//         // ExampleFunction1 func = new ExampleFunction1();
//         // ExampleFunction2 ExampleFunction2 = new ExampleFunction2();

//         // Node[] nodes = new Node[9];

//         // int c = 0;
//         // for(int y=1;y<=3;y++){
//         //     for(int x=1;x<=3;x++){
//         //         nodes[c] = new Node(func,x,y);
//         //         c++;
//         //     }
//         // }
        
//         // yum.addPoints(nodes);
        
//         // yum.removePoint(2,2);

//         // Node[] arr = yum.toArray();

//         // for(Node node:arr){
//         //     My.cout(node);
//         //     String[] links = node.getNodeLinks();
//         //     for(String link:links){
//         //         My.cout(">>\t"+link);
//         //     }
//         // }
//         // My.cout("get(1,1)");
//         // My.cout(yum.getPoint(1,1));

//         // yum.addPoint(func,1,1);
//         // yum.addPoint(func,-2,-1);
//         // yum.addPoint(func,2,1);

//         // yum.addPoint(func,-3,-2);
//         // yum.addPoint(func,-1,-1);
//         // yum.addPoint(func,3,2);

//         // yum.addPoint(ExampleFunction2,3,2);

//         // yum.addPoint(func,2,-2);
//         // yum.addPoint(func,3,1);
//         // yum.addPoint(func,3,-2);

//         // Node _o = yum.getOrigin();

//         // Node[] arr = yum.toArray();

//         // for(Node node:arr){
//         //     My.cout(node);
//         // }
//         // My.cout("get(1,1)");
//         // My.cout(yum.getPoint(1,1));

//         ///////////////////////////////////////////////////

//         Interface interface1 = new Interface();
//         for(int i=-1; i <= 1; i++)
//         {
//             for(int j=1; j >= -1; j--)
//             {
//                 interface1.addPoint(new ExampleFunction1(), i, j);
//             }
//         }
//         System.out.println(interface1.printFunctionValues((new ExampleFunction1()).getFunctionName()));
//         for(int i=-2; i <= 2; i++)
//         {
//             for(int j=-2; j <= 2; j++)
//             {
//                 interface1.addPoint(new ExampleFunction2(), i, j);
//             }
//         }
//         System.out.println(interface1.printFunctionValues((new ExampleFunction2()).getFunctionName()));
//         Node n1 = interface1.getPoint(1, 1);
//         for(int i=0; i < 6; i++)
//         {
//             System.out.print(n1.getNodeLinks()[i]);
//         }
//         System.out.println();
//         Node n2 = interface1.getPoint(2, 2);
//         for(int i=0; i < 6; i++)
//         {
//             System.out.print(n2.getNodeLinks()[i]);
//         }
//         System.out.println();
//         for(int i=0; i < 4; i++)
//         {
//             System.out.println("Count in Q" + i + ": " + interface1.numPointsPerQuadrant()[i]);
//         }
//         System.out.println("Number of nodes/points: " + interface1.countNumberOfPoints());
//         System.out.println(n1.getFunction().getFunctionName());
//         System.out.println(n1.prevVal.getFunction().getFunctionName());
//         System.out.println(n2.getFunction().getFunctionName());
//         Node n3 = interface1.removePoint(1, 1);
//         if(n3 == n1)
//             System.out.println("Correct");
//         else 
//             System.out.println("Problem");
//         Node n4 = interface1.getPoint(1, 1);
//         if(n4 != n1)
//             System.out.println("Correct");
//         else 
//             System.out.println("Problem");
//         System.out.println(n4.getFunction().getFunctionName());
//         System.out.println(n4.getValue() + " == " + interface1.calculateValue((new ExampleFunction2()), 1, 1));

//         My.cout("\n|\tMain End\t|\n");
//     }

//     protected static String[] bubbleSortStrings(String[] nodes){

//         //shallow copy array
//         String[] newArr = new String[nodes.length];
//         for(int i=0;i<nodes.length;i++){
//             newArr[i] = nodes[i];
//         }

//         boolean isComplete = false;
//         while(!isComplete){
//             isComplete = true;

//             for(int i=0;i<newArr.length;i++){
//                 // My.cout(i);
//                 String nodeA = null;
//                 String nodeB = null;

//                 nodeA = (String) newArr[i];

//                 if( (i+1)<newArr.length){
//                     nodeB = (String) newArr[i+1];
//                 }else{
//                     continue;
//                 }

//                 // My.cout("NodeA:\t"+nodeA);
//                 // My.cout("NodeB:\t"+nodeB);

//                 int comp = nodeA.compareToIgnoreCase(nodeB);

//                 if(comp > 0){
//                     // My.cout("resort");
//                     isComplete = false;

//                     String temp = null;

//                     temp = newArr[i];
//                     newArr[i] = newArr[i+1];
//                     newArr[i+1] = temp;
//                 }

//             }
//         }

//         return newArr;

//     }
// }

// class ExampleFunction1 extends Function{
//     public ExampleFunction1(){
//         functionName = "Example Function 1";
//     }

//     public float calculate(int v1, int v2){
//         return Math.abs(v1+v2);
//     }

//     public Function clone(){
//         return new ExampleFunction1();
//     }
// }

// class ExampleFunction2 extends Function{
//     public ExampleFunction2(){
//         functionName = "Example Function 2";
//     }

//     public float calculate(int v1, int v2){
//         return Math.max(v1, v2);
//     }

//     public Function clone(){
//         return new ExampleFunction2();
//     }
// }

// /*
// Expected output:
// 2;0;0;2
// -2;-1;1;2;-1;-1;1;2;1;1;1;2;2;2;2;2
// U[1][2]{2}D[1][0]{0}R[2][1]{2}L[0][1]{0}P[1][1]{1}N[][]{}
// U[][]{}D[2][1]{2}R[][]{}L[1][2]{2}P[][]{}N[][]{}
// Count in Q0: 5
// Count in Q1: 5
// Count in Q2: 5
// Count in Q3: 5
// Number of nodes/points: 20
// Example function 1
// Example function 2
// Example function 2
// Correct
// Correct
// Example Function 2
// 1.0 == 1.0
// */
