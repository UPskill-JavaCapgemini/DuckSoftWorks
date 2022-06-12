package LanguageDetection.Domain.ValueObjects;

import LanguageDetection.domain.ValueObjects.TimeOut;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


class TimeOutTest {
    @Test
    void shouldCreateATimeOutWithValidTimeOut() {
        //Arrange
        int validTimeOutInput = 1;
        //Act
        TimeOut inputTimeOut = new TimeOut(validTimeOutInput);

        //Assert
        Assert.assertNotNull(inputTimeOut);
    }

    @Test
    void shouldThrowExceptionWhenBelowAcceptedInterval() {
        //Arrange
        int invalidTimeOutInput = 0;
        //Act
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            TimeOut inputTimeOut= new TimeOut(invalidTimeOutInput);
        });
        //Assert
        Assert.assertEquals(illegalArgumentException.getMessage(),"This TimeOut is not in the proper range");
    }

    @Test
    void shouldThrowExceptionWhenAboveAcceptedInterval() {
        //Arrange
        int invalidTimeOutInput = 6;
        //Act
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            TimeOut inputTimeOut= new TimeOut(invalidTimeOutInput);
        });
        //Assert
        Assert.assertEquals(illegalArgumentException.getMessage(),"This TimeOut is not in the proper range");
    }
}