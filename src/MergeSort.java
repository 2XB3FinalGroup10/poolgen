//MergeSort.java

/**
 * Merge sort works best for this program's sort function
 * because the program's sorting MUST be stable
 */

public class MergeSort {
    private static Competitor[] auxiliary;

    public MergeSort(Competitor[] a) {
        auxiliary = new Competitor[a.length];
        sort(a, 0, a.length -1);
    }

    private void sort(Competitor[] a, int low, int high) {
        if (high <= low) {return;}
        int mid = low +(high-low)/2;
        sort(a, low, mid);
        sort(a, mid+1, high);
        merge(a, low, mid, high);
    }

    private static void merge(Competitor[] a, int low, int mid, int high) {
        int i = low;
        int j = mid+1;

        for (int k = low; k <= high; k++)
        {auxiliary[k] = a[k];}
        for (int k = low; k <= high; k++)
            if (i > mid) 								    {a[k] = auxiliary[j++];}
            else if (j > high ) 						    {a[k] = auxiliary[i++];}
            else if (less(auxiliary[j], auxiliary[i]))	{a[k] = auxiliary[j++];}
            else 										    {a[k] = auxiliary[i++];}
    }

    private static boolean less(Competitor a, Competitor b) {
        return a.getRank() <= b.getRank();
    }

}