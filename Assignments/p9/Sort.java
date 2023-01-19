import java.util.Arrays;
public class Sort
{
	public static <T extends Comparable<? super T>> void mergesort(T[] data, int first, int last, boolean debug)
	{
		if (first < last)
		{
			int middle = (int) Math.floor((first + last) / 2);
			mergesort(data, first, middle, debug);
			mergesort(data, middle + 1, last, debug);
			merge(data, first, last, debug);
		}
	}
     
	private static <T extends Comparable<? super T>> void merge(T[] data, int first, int last, boolean debug)
	{
		int middle = (int) Math.floor((first + last) / 2);
		int iterator1 = 0;
		int iterator2 = first;
		int iterator3 = middle + 1;
		Object[] tempArr = new Object[data.length];
		Object[] transfer = new Object[data.length];

		for (int i = 0; i < data.length; i++)
			transfer[i] = data[i];
		
		while (iterator2 <= middle && iterator3 <= last)
		{
			if (data[iterator2].compareTo(data[iterator3]) < 0)
				tempArr[iterator1++] = data[iterator2++];
			else tempArr[iterator1++] = data[iterator3++];
		}

		while (iterator2 <= middle)
			tempArr[iterator1++] = data[iterator2++];
		
		while (iterator3 <= last)
			tempArr[iterator1++] = data[iterator3++];
		
		int counter = 0;
		int index = first;
		while (index <= last && counter < iterator1)
			transfer[index++] = tempArr[counter++];

		for (int i = 0; i < data.length; i++)
			data[i] = (T) transfer[i];
        
		if (debug)
			System.out.println(Arrays.toString(data));
	}
     
	public static void countingsort(int[] data, boolean debug)
	{
		int largest = data[0];
		for (int i = 1; i < data.length; i++)
		{
			if (largest < data[i])
				largest = data[i];
		}
			
		int[] count = new int[largest + 1];
		for (int i = 0; i < data.length; i++)
			count[data[i]] += 1;

		for (int i = 1; i < count.length; i++)
			count[i] = count[i - 1] + count[i];

		int[] tmp = new int[data.length];
		for (int i = data.length - 1; i >= 0; i--)
		{
			tmp[count[data[i]] - 1] = data[i];
			count[data[i]] -= 1;
		}

		for (int i = 0; i < data.length; i++)
			data[i] = tmp[i];

		if (debug)
			System.out.println(Arrays.toString(data));
	}

	public static void cout(Object[] arr)
	{
		for (int i = 0; i < arr.length; i++)
		{
			if (arr[i] != null)
				System.out.print("[" + i + " | " + arr[i] + "]");
		}		
		System.out.println("");
	}

	public static void cout(int[] arr)
	{
		for (int i = 0; i < arr.length; i++)
		{
			System.out.print("[" + arr[i] + "]");
		}		
		System.out.println("");
	}
}