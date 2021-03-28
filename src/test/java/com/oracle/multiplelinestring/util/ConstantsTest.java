package com.oracle.multiplelinestring.util;

import com.oracle.multiplelinestring.AbstractTest;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstantsTest extends AbstractTest {

    @Test(expected = InvocationTargetException.class)
    public void givenTryingToConstantsTestClass_whenInvoking_thenInvocationTargetExceptionOccurs() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        // arrange
        final Constructor<?>[] constructors = Constants.class.getDeclaredConstructors();
        // execute
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
        // assert
        // assertion happens with expected = InvocationTargetException.class
    }
}
