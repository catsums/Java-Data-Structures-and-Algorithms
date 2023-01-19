package Sort;

import java.lang.Comparable;

public abstract class Sorting {
    public String name = "Unknown";
    public abstract Comparable[] sortAcs(Comparable[] array); 
    public abstract Comparable[] sortDsc(Comparable[] array);

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


    protected static void swap(Comparable[] arr, int a, int b){
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //increment array dynamically by adding to end of array :. array[array.length - 1]. returns a new copy
    protected static Comparable[] arrayPush(Comparable[] arr, Comparable item){
        Comparable[] newArr = resizeArray(arr,1);
        newArr[newArr.length-1] = item;
        return newArr;
    }
    //decrement array dynamically by removing the end of array :. array[array.length - 1]. returns a new copy
    protected static Comparable[] arrayPop(Comparable[] arr){
        Comparable[] newArr = resizeArray(arr,-1);
        return newArr;
    }
    //increment array dynamically by adding to the front of array :. array[0]. returns a new copy
    protected static Comparable[] arrayUnshift(Comparable[] arr, Comparable item){
        Comparable[] newArr = new Comparable[arr.length+1];

        for(int i=1;i<=arr.length;i++){
            newArr[i] = arr[i-1];
        }

        newArr[0] = item;

        return newArr;
    }
    //returns a copy of the array in reverse order
    protected static Comparable[] reverseArray(Comparable[] arr){
        Comparable[] newArr = new Comparable[arr.length];

        int c = arr.length-1;
        for(int i=0; i<arr.length; i++){
            newArr[i] = arr[c];
            c--;
        }
        return newArr;
    }
    //returns a copy of the array but resized by an amount
    protected static Comparable[] resizeArray(Comparable[] arr, int resize){
        int len = arr.length + resize;
        Comparable[] newArr = new Comparable[len];
        for(int i=0;i<arr.length;i++){
            if(i>=len) break;
            newArr[i] = arr[i];
        }
        return newArr;
    }
    //returns a copy of the array but resized by an amount
    protected static Comparable[] cloneArray(Comparable[] arr){
        return resizeArray(arr,0);
    }
    //linear searches array for an item. this is by reference and it returns true/false
    public static boolean isInArray(Comparable[] arr, Comparable item){
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
    protected static Comparable shiftArrayRight(Comparable[] arr, int start){
        if(arr.length<1) return null;
        if(start<0 || start>=arr.length) return null;

        Comparable excess = null;

        for(int c=arr.length-1;c>=start;c--){
            if(c == arr.length-1){
                excess = arr[c];
            }else{
                arr[c+1] = arr[c];
            }
            arr[c] = null;
        }

        return excess;

    }protected static Comparable shiftArrayRight(Comparable[] arr){
        return shiftArrayRight(arr, 0);
    }
    //shifts array to the left once at a specific position and returns the item that is pushed out of bounds
    //given an array of [a b c x y z] and shiftLeft at index 2, the resulting array will be [b c _ x y z] and it returns 'a'
    //this changes the original array
    protected static Comparable shiftArrayLeft(Comparable[] arr, int end){
        if(arr.length<1) return null;
        if(end<0 || end>=arr.length) return null;

        Comparable excess = null;

        for(int c=0;c<=end;c++){
            if(c == 0){
                excess = arr[c];
            }else{
                arr[c-1] = arr[c];
            }
            arr[c] = null;
        }

        return excess;

    }protected static Comparable shiftArrayLeft(Comparable[] arr){
        return shiftArrayLeft(arr, arr.length-1);
    }
    //shifts array to the right internally once :. moving items towards the index at a specific position and returns the item that's popped out
    //given an array of [a b c x y z] and innerShiftRight at index 2, the resulting array will be [_ a b x y z] and it returns 'c'
    //this changes the original array
    protected static Comparable innerShiftArrayRight(Comparable[] arr, int end){
        if(arr.length<1) return null;
        if(end<0 || end>=arr.length) return null;

        Comparable excess = null;

        for(int c=end;c>=0;c--){
            if(c == end){
                excess = arr[c];
            }else{
                arr[c+1] = arr[c];
            }
            arr[c] = null;
        }
        return excess;
    }protected static Comparable innerShiftArrayRight(Comparable[] arr){
        return innerShiftArrayLeft(arr, arr.length-1);
    }
    //shifts array to the left internally once :. moving items towards the index at a specific position and returns the item that's popped out
    //given an array of [a b c x y z] and innerShiftLeft at index 2, the resulting array will be [a b x y z _] and it returns 'c'
    //this changes the original array
    protected static Comparable innerShiftArrayLeft(Comparable[] arr, int start){
        if(arr.length<1) return null;
        if(start<0 || start>=arr.length) return null;

        Comparable excess = null;

        for(int c=start;c<arr.length;c++){
            if(c == start){
                excess = arr[c];
            }else{
                arr[c-1] = arr[c];
            }
            arr[c] = null;
        }
        return excess;
    }protected static Comparable innerShiftArrayLeft(Comparable[] arr){
        return innerShiftArrayLeft(arr, 0);
    }
}

