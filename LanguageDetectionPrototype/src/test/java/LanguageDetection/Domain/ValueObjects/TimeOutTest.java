package LanguageDetection.Domain.ValueObjects;

import LanguageDetection.domain.ValueObjects.TimeOut;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


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
}