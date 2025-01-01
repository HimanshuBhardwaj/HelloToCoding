package Y2024.feb3;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/3/2024
 */
public class AllPermutuation {
    public static void main(String[] args) {
        int a[] = {0,0,1};
        AllPerm allPerm = new AllPermWithoutRepetation();
        allPerm.allPermutuation(a);
    }
}

class AllPermWithoutRepetation implements AllPerm {

    @Override
    public void allPermutuation(int[] arr) {
        allPermutuationHelper(0,arr);
    }

    private void allPermutuationHelper(int index, int[] arr) {
        if (index==arr.length) {
            for (int x:arr) {
                System.out.print(x+", ");
            }
            System.out.println();
            return;
        }

        for (int i=index;i<arr.length;i++) {
            if (isLastOccurence(i,arr)) {
                swap(i,index,arr);
                allPermutuationHelper(index+1,arr);
                swap(i,index,arr);
            }
        }
    }

    private boolean isLastOccurence(int in, int[] arr) {
        for (int i=in+1;i<arr.length;i++) {
            if (arr[i] == arr[in]) {
                return false;
            }
        }

        return true;
    }

    private boolean isFirstOccurence(int in, int[] arr) {
        for (int i=0;i<in-1;i++) {
            if (arr[i] == arr[in]) {
                return false;
            }
        }
        return true;
    }

    private void swap(int s, int e, int[] arr) {
        int t = arr[s];
        arr[s] = arr[e];
        arr[e] = t;
    }
}

interface AllPerm {
    void allPermutuation(int [] arr);
}
