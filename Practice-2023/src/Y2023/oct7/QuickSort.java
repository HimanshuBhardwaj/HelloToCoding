package Y2023.oct7;
/**
 * @author Himanshu Bhardwaj
 * @Date 10/7/2023
 */
public class QuickSort  {
    public static void main(String[] args) {
        int [] num = {};

        QSImpl qs = new QSImpl();
        qs.sort(num);

        for (int x: num) {
            System.out.print(x+", ");
        }
        System.out.println();

    }
}

class QSImpl implements Sort {

    @Override
    public void sort(int[] arr) {
        sortInternal(arr, 0, arr.length-1);
    }

    private void sortInternal(int[] arr, int start, int end) {
        if (start>=end){
            return;
        }

        int index = partition(arr,start,end);
        sortInternal(arr,start,index-1);
        sortInternal(arr,index+1,end);
    }

    public int partition(int []arr, int s, int e) {
        if (s==e) {
            return s;
        }

        if (s == (e-1) ) {
            int min = Math.min(arr[s],arr[e]);
            int max = Math.max(arr[s],arr[e]);
            arr[s] = min;
            arr[e] = max;
            return s;
        }

        // or select any element and replace;
        int pivot = arr[e];

        int i = s-1;

        for (int j=s;j<e;j++) {
            if (arr[j] < pivot) {
                i++;
                swap(i,j,arr);
            }
        }

        swap(i+1,e,arr);

        return i+1;
    }

    private void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

interface Sort {
    void sort(int [] arr);
}