import java.util.Arrays;
import java.util.Random;
public class myMain
{
	public static void main(String[] args)
	{

		//Unsorted array
		// Integer[] arraynum = { 2, 6, 3, 5, 1 };

		//generate random integers, you may use floats or doubles for this too
		int size = 10;	//controls how many numbers you want to put in the heap
		Random rand = new Random();
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Integer[] arr = new Integer[size];
	
		for(int c=0;c<arr.length;c++) {
			arr[c] = rand.nextInt(size);
		}

		Integer[] arraynum = arr;     

		int first = 0;
		int last = arraynum.length - 1;

		//Call merge sort with integers
		Sort.mergesort(arraynum, first, last, true);
         
		//Print sorted array
		System.out.println("Sorted : " + Arrays.toString(arraynum));

		//Unsorted array
		// String[] arraystr = { "presence", "threshold", "download", "chemicals", "basics" };

		String[] arraystr = new String[size];

		int maxlen = 8;

		for(int c=0;c<arr.length;c++) {
			String str = "";
			int len = rand.nextInt(maxlen)+1;
			for(int d=0; d<len;d++){
				str += chars.charAt(rand.nextInt(chars.length()));
			}
			arraystr[c] = str;
		}
        	 
		first = 0;
		last = arraystr.length - 1;

		//Call merge sort with strings
		Sort.mergesort(arraystr, first, last, true);
         
		//Print sorted array
		System.out.println("Sorted : " + Arrays.toString(arraystr));

		//Unsorted array
		// int[] arrayint = { 2, 6, 3, 5, 1 };
		int[] arrayint = new int[size];

		for(int c=0;c<arrayint.length;c++) {
			arrayint[c] = rand.nextInt(99);
		}
        	 
		//Call counting sort with integers
		Sort.countingsort(arrayint, true);
         
		//Print sorted array
		System.out.println("Sorted : " + Arrays.toString(arrayint));

		/* Expected output
		[2, 6, 3, 5, 1]
		[2, 3, 6, 5, 1]
		[2, 3, 6, 1, 5]
		[1, 2, 3, 5, 6]
		Sorted : [1, 2, 3, 5, 6]
		[presence, threshold, download, chemicals, basics]
		[download, presence, threshold, chemicals, basics]
		[download, presence, threshold, basics, chemicals]
		[basics, chemicals, download, presence, threshold]
		Sorted : [basics, chemicals, download, presence, threshold]
		[1, 2, 3, 5, 6]
		Sorted : [1, 2, 3, 5, 6]
		*/
	}
}
