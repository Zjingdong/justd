package org.equals;

import com.sun.org.apache.bcel.internal.util.Objects;

public class equalsTest {
    public static void main(String[] args) {
        Long a = 101L;
        Long b = 101L;
        Long c = new Long(10);
        Long d = new Long(10);
        System.out.println(a==b);
        System.out.println(a.equals(b));
        System.out.println(Objects.equals(a,b));
        System.out.println(c==d);
        System.out.println(c.equals(d));
        System.out.println(Objects.equals(c,d));

    }
}
