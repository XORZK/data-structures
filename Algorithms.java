import java.util.Arrays;

public class Algorithms {
  public static void swap(int[] a, int i, int k) {
    int tmp = a[i];
    a[i] = a[k];
    a[k] = tmp;
  }

  public static void bubbleSort(int[] a) {
    for (int i = 0; i < a.length; i++) {
      boolean swapped = false;
      for (int k = 0; k < a.length-1-i; k++) {
        if (a[k] > a[k+1]) { swap(a, k, k+1); swapped = true; }
      }
      if (!swapped) break;  
    }
  }

  public static void selectionSort(int[] a) {
    for (int i = 0; i < a.length; i++) {
      int min = i;
      for (int k = i; k < a.length; k++) {
        min = (a[k] < a[min] ? k : min);
      }
      swap(a, min, i);
    }
  }

  public static void insertionSort(int[] a) {
    for (int i = 0; i < a.length; i++) {
      int k = i;
      while (k > 0 && a[k-1] > a[k]) {
        swap(a, k, k-1);
        k--;
      }
    }
  }

  public static void quickSort(int[] a, int L, int H) {
    if (L >= H || L < 0) return;
    int p = partition(a, L, H);
    quickSort(a, p+1, H);
    quickSort(a, 0, p-1);
  }

  public static int partition(int[] a, int L, int H) {
    int pivot = a[H], i = L-1;

    for (int k = L; k < H; k++) {
      if (a[k] < pivot) {
        swap(a, k, ++i);
      }
    }
    swap(a, H, ++i);
    return i;
  }

  public static void main(String[] args) {
    int[] a = {5,9,1,3,4,6,6,3,2};
    quickSort(a, 0, a.length-1);
    System.out.println(Arrays.toString(a));
  }
}
