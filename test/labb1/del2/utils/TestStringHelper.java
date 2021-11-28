package labb1.del2.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestStringHelper {
    @Test
    public void testConcatenateString() {
        String[] arr1 = {
                "Blåbär",
                "Äpple"
        };
        String[] arr2 = {
                "Banan",
                "Melon"
        };
        String[] res = StringHelper.concatenateStrArr(arr1, arr2);
        assertEquals(res.length, arr1.length + arr2.length);
        int i = 0;
        for (; i < arr1.length; i++) {
            assertEquals(arr1[i], res[i]);
        }
        for(; i < arr2.length + arr1.length; i++) {
            assertEquals(arr2[i - arr1.length], res[i]);
        }
    }
}
