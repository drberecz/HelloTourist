package hu.ps.ht.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class RegistrationHashPasswordTest {
    
    public RegistrationHashPasswordTest() {
    }

    @Test
    public void CheckHashing_GivenNonNullInput_ReturnStandardHashed() throws Exception {
  
    String result = RegistrationHashPassword.encodeSHA256("steve");
    Assertions.assertTrue("f148389d080cfe85952998a8a367e2f7eaf35f2d72d2599a5b0412fe4094d65c".equals(result));
    }

    
    @Test
    public void CheckHashing_GivenNullInput_ExpectException() throws Exception {
  
    Object result = Assertions.assertThrows(NullPointerException.class, ()->{RegistrationHashPassword.encodeSHA256(null);});
    Assertions.assertTrue(result instanceof NullPointerException);
    }
    
}



