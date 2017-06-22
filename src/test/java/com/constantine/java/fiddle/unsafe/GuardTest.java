package com.constantine.java.fiddle.unsafe;


import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuardTest {

    @Test
    public void testUnsafeObjects() throws NoSuchFieldException, IllegalAccessException {
        Guard guard = new Guard();
        assertFalse(guard.giveAccess());

        Unsafe unsafe = getUnsafe();

        Field accessAllowed = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        unsafe.putInt(guard, unsafe.objectFieldOffset(accessAllowed), 42);

        assertTrue(guard.giveAccess());
    }

    private Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        return (Unsafe) theUnsafe.get(null);
    }
}