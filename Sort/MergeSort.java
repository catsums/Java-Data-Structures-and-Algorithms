package Sort;

public class MergeSort extends Sorting {

    public MergeSort(){
        name = "MergeSort";
    }

    @Override
    public Comparable[] sortAcs(Comparable[] array){

        try{
            Comparable[] out = new Comparable[array.length];
            for(int i=0;i<out.length;i++){
                out[i] = array[i];
            }
            mergesort(array, 0, array.length-1);

            return out;
        }catch(Exception err){
            throw err;
        }
    }

    @Override
    public Comparable[] sortDsc(Comparable[] array){
        try{
            Comparable[] out = new Comparable[array.length];
            for(int i=0;i<out.length;i++){
                out[i] = array[i];
            }
            mergesortDsc(array, 0, array.length-1);

            return out;
        }catch(Exception err){
            throw err;
        }
        
    }

    protected void mergesort(Comparable[] data, int first, int last){
		if (first < last){
			int middle = (int) Math.floor((first + last) / 2);
			mergesort(data, first, middle);
			mergesort(data, middle + 1, last);
			merge(data, first, last);
		}
	}
    protected void mergesortDsc(Comparable[] data, int first, int last){
		if (first > last){
			int middle = (int) Math.floor((first + last) / 2);
			mergesortDsc(data, first, middle);
			mergesortDsc(data, middle + 1, last);
			merge(data, first, last);
		}
	}

    protected void merge(Comparable[] data, int first, int last){
		int middle = (int) Math.floor((first + last) / 2);
		int iterator1 = 0;
		int iterator2 = first;
		int iterator3 = middle + 1;
		Comparable[] tempArr = new Comparable[data.length];
		Comparable[] transfer = new Comparable[data.length];

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
			data[i] =  transfer[i];
        
	}

}
