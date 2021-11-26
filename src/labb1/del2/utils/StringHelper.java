package labb1.del2.utils;

public class StringHelper {
    public static String[] concatenateStrArr(String[] arr1, String[] arr2) {
        String[] res = new String[arr1.length + arr2.length];
        int i = 0;
        for (; i < arr1.length; i++) {
            res[i] = arr1[i];
        }
        for (String s : arr2) {
            res[i] = s;
            i++;
        }
        return res;
    }
}
