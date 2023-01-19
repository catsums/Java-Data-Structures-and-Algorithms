import java.util.ArrayList;

public class myMain
{
	public static void main(String[] args){
		// example1();
		example2();
	}

	public static void example2(){
		int arraySize = 10;
		int cellarSize = 10;
		
		int[] keys = {2,4,5,8,10,30,28,16,17,19,11};
		String[] vals = {"A","B","C","Deez nuts","E","F","G","H","I","J","K"};

		cout("------Map 1 (10,10)---------");
		HashMap<Integer,String> map = new HashMap<>(arraySize,cellarSize);

		for(int i=0;i<keys.length;i++){
			Integer key = keys[i];
			String val = vals[i];
			map.put(key,val);
		}

		cout(arrayToString(map.getKeyArray()));
		cout(arrayToString(map.getKeyCellar()));
		cout("Count: "+map.count());

		for(int i=0;i<keys.length;i++){
			Integer key = keys[i];
			String val = map.get(key);
			cout("Key: "+key+" value: "+val);
		}

		cout("------Map 2-Decrease Array (9,10)--------");
		HashMap<Integer,String> map2 = map.rehash(9,10);
		cout(arrayToString(map2.getKeyArray()));
		cout(arrayToString(map2.getKeyCellar()));
		cout("Count: "+map2.count());

		cout("------Map 3-Decrease Cellar (9,9)--------");
		HashMap<Integer,String> map3 = map2.rehash(9,9);
		cout(arrayToString(map3.getKeyArray()));
		cout(arrayToString(map3.getKeyCellar()));
		cout("Count: "+map3.count());

		cout("------Map 4-Increase Array (10,9)--------");
		HashMap<Integer,String> map4 = map3.rehash(10,9);
		cout(arrayToString(map4.getKeyArray()));
		cout(arrayToString(map4.getKeyCellar()));
		cout("Count: "+map4.count());

		cout("------Map 5-Decrease Array (9,9)--------");
		HashMap<Integer,String> map5 = map4.rehash(9,9);
		cout(arrayToString(map5.getKeyArray()));
		cout(arrayToString(map5.getKeyCellar()));
		cout("Count: "+map5.count());
	}

	public static void example1(){

		int arraySize = 11;
		int cellarSize = 4;
		
		int[] keys = {20,50,53,75,100,67,105,3,36};
		String[] vals = {"A","B","C","Deez nuts","E","F","G","H","I","J"};

		cout("------Map 1---------");
		HashMap<Integer,String> map = new HashMap<>(arraySize,cellarSize);

		for(int i=0;i<keys.length;i++){
			Integer key = keys[i];
			String val = vals[i];
			map.put(key,val);
		}

		cout(arrayToString(map.getKeyArray()));
		cout(arrayToString(map.getKeyCellar()));
		cout("Count: "+map.count());

		cout("------Map 2---------");
		HashMap<Integer,String> map2 = map.rehash(11,11);
		map2.put(1,"K");
		map2.put(50,"L");
		cout(arrayToString(map2.getKeyArray()));
		cout(arrayToString(map2.getKeyCellar()));
		cout("Count: "+map2.count());

		cout("------Map 3---------");
		HashMap<Integer,String> map3 = map2.rehash(5,6);
		map3.put(69,"N");
		map3.put(420,"O");
		cout(arrayToString(map3.getKeyArray()));
		cout(arrayToString(map3.getKeyCellar()));
		cout("Count: "+map3.count());

		cout("------Map 4---------");
		HashMap<Integer,String> map4 = map3.rehash(1,11);
		map3.put(69,"P");
		map3.put(420,"Q");
		cout(arrayToString(map4.getKeyArray()));
		cout(arrayToString(map4.getKeyCellar()));
		cout("Count: "+map4.count());

		cout("------check map 4 for existing key-value pairs---------");
		for(int i=0;i<keys.length;i++){
			Integer key = keys[i];
			String val = vals[i];
			boolean contains = map4.isContained(key, val);

			cout("key: "+key+"\tval: "+val+"\tinside?: "+contains);
		}

	}

	class Tuple<T,U>{
	    public T t;
	    public U u;

	    public Tuple(T t, U u){
	        this.t = t;
	        this.u = u;
	    }
	}

	public static void cout(Object x){
		System.out.println(x);
	}
	public static String arrayToString(Object[] arr){
		String out = "[";
		for(int c=0;c<arr.length;c++){
			out += arr[c];
			if(c<arr.length-1){
				out+=", ";
			}
		}
		out+="]";
		return out;
	}
}

/* EXPECTED OUTPUT

------Map 1---------
[null, 100, 67, 3, null, null, 50, 105, null, 20, null]
[75, 36, 53, null]
Count: 9
------Map 2---------
[null, 100, null, 3, null, null, 50, null, null, 20, 53]
[null, 67, null, 36, null, null, 105, null, null, 75, 1]
Count: 10
------Map 3---------
[100, 20, 67, 3, 75]
[53, 36, 69, 50, 105, 1]
Count: 11
------Map 4---------
[100]
[67, 1, 3, 36, 69, 50, 105, null, 20, 75, 53]
Count: 11
------check map 4 for existing key-value pairs---------
key: 20 val: A  inside?: true
key: 50 val: B  inside?: true
key: 53 val: C  inside?: true
key: 75 val: Deez nuts  inside?: true
key: 100        val: E  inside?: true
key: 67 val: F  inside?: true
key: 105        val: G  inside?: true
key: 3  val: H  inside?: true
key: 36 val: I  inside?: true

*/