import java.util.Stack;
public class Sorter implements GodricsHat {
    @Override
    public void insertion(int[] array) {
        for(int j = 1 ; j < array.length ; j++){
            int key = array[j];
            int i = j - 1;
            while(i > -1 && array[i]>key){
                array[i + 1] = array[i];
                i = i - 1;
            }
            array[i + 1] = key;
        }
    }
    @Override
    public void counting(int[] array) {
        int i = 0, j = 0, k = 0, max = Integer.MIN_VALUE; for (i = 0; i < array.length; i++)
            max = array[i] > max ? array[i] : max; int[] counts = new int[max + 1];
        for (i = 0; i < array.length; i++)
            counts[array[i]]++;
        for (i = 0; i < counts.length; i++)
            for (j = 0; j < counts[i]; j++) array[k++] = i;
    }
    @Override
    public void merge(int[] array) {
        if(array.length<=1){
            return;
        }
        int[] left = new int[array.length/2];
        int[] right = new int[array.length - array.length/2];
        System.arraycopy(array,0,left,0,left.length);
        System.arraycopy(array,array.length-right.length,right,0,right.length);
        merge(left);
        merge(right);
        int k = 0,i = 0, j = 0;
        while(k<left.length+right.length){
            if(i>=left.length||j>=right.length){
                break;
            }
            if(left[i]<right[j]){
                array[k]=left[i];
                i++;
                k++;
            }else {
                array[k]=right[j];
                j++;
                k++;
            }
        }
        while(j<right.length){
            array[k]=right[j];
            k++;
            j++;
        }
        while(i<left.length){
            array[k]=left[i];
            k++;
            i++;
        }

    }
    @Override
    public void quickLoopy(int[] array) {
        Stack<Integer>stack = new Stack<>();
        int startIndex = 0;
        int endIndex = array.length-1;
        stack.push(startIndex);
        stack.push(endIndex);
        while(!stack.isEmpty()){
            endIndex = stack.peek();
            stack.pop();
            startIndex = stack.peek();
            stack.pop();

            int pivotIndex = partition(array,startIndex, endIndex);
            if(pivotIndex-1>startIndex){
                stack.push(startIndex);
                stack.push(pivotIndex-1);
            }
            if(pivotIndex+1<endIndex){
                stack.push(pivotIndex+1);
                stack.push(endIndex);
            }

        }
    }
    @Override
    public void quick(int[] array, int low, int high) {
        if(low < high){
            int pi = partition(array, low, high);

            quick(array, low, pi-1);
            quick(array, pi+1, high);
        }
    }
    public int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
