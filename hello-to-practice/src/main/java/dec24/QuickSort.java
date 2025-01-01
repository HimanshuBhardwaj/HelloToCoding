package dec24;

// quick sort 11:51 - 12:08
public class QuickSort {
    public static void main(String[] args) {
        SortI sortI = new QuickSortImpl();
        int [] arr = {1,-1};
        sortI.sort(arr);

        for (int c:arr) {
            System.out.print(c+"\t");
        }
    }
}


class QuickSortImpl implements SortI {

    @Override
    public void sort(int[] arr) {
        sortHelper(arr,0,arr.length-1);
    }

    private void sortHelper(int[] arr, int s, int e) {
        if (s==e || s<0 || e>=arr.length || s>e) {
            return;
        }

        int pivot = partitionArray(arr,s,e);
        sortHelper(arr,s,pivot-1);
        sortHelper(arr,pivot+1,e);
    }

    private int partitionArray(int[] arr, int s, int e) {
        if (s==e) {
            return s;
        }

        int pivot=arr[e];
        int j=s;
        for (int i=s;i<e;i++) {
            if (arr[i]<pivot) {
                swap(arr,i,j);
                j++;
            }
        }
        swap(arr,e,j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}

interface SortI {
    void sort(int [] arr);
}
