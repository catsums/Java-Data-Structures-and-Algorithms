public abstract class Sorting {
    public String name = "Unknown";
    public abstract String[] sortAcs(Vertex[] array) throws CycleException; 
    public abstract String[] sortDsc(Vertex[] array) throws CycleException;
    protected String[] vertexToString(Vertex[] array){
        String[] res = new String[array.length];
        for(int i=0; i < res.length; i++)
        {
            res[i] = array[i].getVName();
        }
        return res;
    }

    protected void printArr(String[] arr)
    {
        String line = "";
        for(int i=0; i < arr.length; i++)
        {
            line += arr[i] + ";";
        }
        line = line.substring(0, line.length()-1);
        System.out.println(line);
    }

    ////////////MY FUNCTIONS///////////

    protected void printVertexArr(Object[] arr){
        String[] names = new String[arr.length];

        for(int c=0;c<arr.length;c++){
            names[c] = ((Vertex)arr[c]).getVName();
        }
        printArr(names);
    }

    protected Vertex getUnvistedVertex(Vertex[] arr){
        for(Vertex vert:arr){
            if(vert==null) continue;
            if(vert.num == 0) return vert;
        }
        return null;
    }

    protected void DFSCycle(Vertex[] vertices)throws CycleException{
        try{
            for(Vertex vert:vertices){
                if(vert==null) continue;
                vert.num = 0;
            }
            Vertex zeroVert = getUnvistedVertex(vertices);

            while(zeroVert!=null){
                cycleDetectionDFS(zeroVert);
                zeroVert = getUnvistedVertex(vertices);
            }
        }catch(CycleException err){
            throw err;
        }
    }
    protected void cycleDetectionDFS(Vertex start)throws CycleException{
        try{
            start.num ++;
            Edge[] edges = start.getEdges();
            for(Edge edge:edges){
                if(edge==null) continue;
                Vertex vert = edge.pointB;
                if(vert.num == 0){
                    vert.pred = start;
                    cycleDetectionDFS(vert);
                }else if(vert.num != Double.POSITIVE_INFINITY){
                    //cycle detected//
                    throw new CycleException();
                }
            }
            start.num = Double.POSITIVE_INFINITY;
        }catch(CycleException err){
            throw err;
        }
    }

    protected static void swap(Object[] arr, int a, int b){
        Object temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    protected static void swap(Vertex[] arr, int a, int b){
        Vertex temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //increment array dynamically by adding to end of array :. array[array.length - 1]. returns a new copy
    protected static Object[] arrayPush(Object[] arr, Object item){
        Object[] newArr = resizeArray(arr,1);
        newArr[newArr.length-1] = item;
        return newArr;
    }
    //decrement array dynamically by removing the end of array :. array[array.length - 1]. returns a new copy
    protected static Object[] arrayPop(Object[] arr){
        Object[] newArr = resizeArray(arr,-1);
        return newArr;
    }
    //increment array dynamically by adding to the front of array :. array[0]. returns a new copy
    protected static Object[] arrayUnshift(Object[] arr, Object item){
        Object[] newArr = new Object[arr.length+1];

        for(int i=1;i<=arr.length;i++){
            newArr[i] = arr[i-1];
        }

        newArr[0] = item;

        return newArr;
    }
    //returns a copy of the array in reverse order
    protected static Object[] reverseArray(Object[] arr){
        Object[] newArr = new Object[arr.length];

        int c = arr.length-1;
        for(int i=0; i<arr.length; i++){
            newArr[i] = arr[c];
            c--;
        }
        return newArr;
    }
    //returns a copy of the array but resized by an amount
    protected static Object[] resizeArray(Object[] arr, int resize){
        int len = arr.length + resize;
        Object[] newArr = new Object[len];
        for(int i=0;i<arr.length;i++){
            if(i>=len) break;
            newArr[i] = arr[i];
        }
        return newArr;
    }
    //returns a copy of the array but resized by an amount
    protected static Object[] cloneArray(Object[] arr){
        return resizeArray(arr,0);
    }
    //linear searches array for an item. this is by reference and it returns true/false
    public static boolean isInArray(Object[] arr, Object item){
        if(item==null) return false;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == item){
                return true;
            }
        }
        return false;
    }
    //shifts array to the right once at a specific position and returns the item that is pushed out of bounds
    //given an array of [a b c x y z] and shiftRight at index 2, the resulting array will be [a b _ c x y] and it returns 'z'
    //this changes the original array
    protected static Object shiftArrayRight(Object[] arr, int start){
        if(arr.length<1) return null;
        if(start<0 || start>=arr.length) return null;

        Object excess = null;

        for(int c=arr.length-1;c>=start;c--){
            if(c == arr.length-1){
                excess = arr[c];
            }else{
                arr[c+1] = arr[c];
            }
            arr[c] = null;
        }

        return excess;

    }protected static Object shiftArrayRight(Object[] arr){
        return shiftArrayRight(arr, 0);
    }
    //shifts array to the left once at a specific position and returns the item that is pushed out of bounds
    //given an array of [a b c x y z] and shiftLeft at index 2, the resulting array will be [b c _ x y z] and it returns 'a'
    //this changes the original array
    protected static Object shiftArrayLeft(Object[] arr, int end){
        if(arr.length<1) return null;
        if(end<0 || end>=arr.length) return null;

        Object excess = null;

        for(int c=0;c<=end;c++){
            if(c == 0){
                excess = arr[c];
            }else{
                arr[c-1] = arr[c];
            }
            arr[c] = null;
        }

        return excess;

    }protected static Object shiftArrayLeft(Object[] arr){
        return shiftArrayLeft(arr, arr.length-1);
    }
    //shifts array to the right internally once :. moving items towards the index at a specific position and returns the item that's popped out
    //given an array of [a b c x y z] and innerShiftRight at index 2, the resulting array will be [_ a b x y z] and it returns 'c'
    //this changes the original array
    protected static Object innerShiftArrayRight(Object[] arr, int end){
        if(arr.length<1) return null;
        if(end<0 || end>=arr.length) return null;

        Object excess = null;

        for(int c=end;c>=0;c--){
            if(c == end){
                excess = arr[c];
            }else{
                arr[c+1] = arr[c];
            }
            arr[c] = null;
        }
        return excess;
    }protected static Object innerShiftArrayRight(Object[] arr){
        return innerShiftArrayLeft(arr, arr.length-1);
    }
    //shifts array to the left internally once :. moving items towards the index at a specific position and returns the item that's popped out
    //given an array of [a b c x y z] and innerShiftLeft at index 2, the resulting array will be [a b x y z _] and it returns 'c'
    //this changes the original array
    protected static Object innerShiftArrayLeft(Object[] arr, int start){
        if(arr.length<1) return null;
        if(start<0 || start>=arr.length) return null;

        Object excess = null;

        for(int c=start;c<arr.length;c++){
            if(c == start){
                excess = arr[c];
            }else{
                arr[c-1] = arr[c];
            }
            arr[c] = null;
        }
        return excess;
    }protected static Object innerShiftArrayLeft(Object[] arr){
        return innerShiftArrayLeft(arr, 0);
    }
}

class CycleException extends Exception{
    public String message = "Cycle has been detected";
}
