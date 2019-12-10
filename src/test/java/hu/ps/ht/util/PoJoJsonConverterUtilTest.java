package hu.ps.ht.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PoJoJsonConverterUtilTest {

    public PoJoJsonConverterUtilTest() {
    }


    @Test
    public void testConvertArrayToSet() {

        NullPointerException result = Assertions.assertThrows(NullPointerException.class, () -> {
            PoJoJsonConverterUtil.convertArrayToSet(null);
        });
        
        Assertions.assertTrue(result instanceof NullPointerException);

    }

}
