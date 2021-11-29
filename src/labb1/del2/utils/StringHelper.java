package labb1.del2.utils;

public class StringHelper {
    /**
     * Concatenates two string arrays together.
     * @param arr1 The first array.
     * @param arr2 The second array.
     * @return The concatenated array.
     */
    public static String[] concatenateStrArr(String[] arr1, String[] arr2) {
        String[] res = new String[arr1.length + arr2.length];
        int i = 0;
        for (String s : arr1) {
            res[i] = s;
            i++;
        }
        for (String s : arr2) {
            res[i] = s;
            i++;
        }
        return res;
    }
}
