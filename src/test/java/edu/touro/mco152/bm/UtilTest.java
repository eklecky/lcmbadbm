package edu.touro.mco152.bm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


class UtilTest {
    /**
     * B in Bicep
     * tests boundary condition by making sure the int generated is within the specified range
     */
    @Test
    void randIntTest() {
        int min = 1;
        int max = 5;
        int test1 = Util.randInt(min, max);

        assertTrue(test1 <=5);
        assertTrue(test1>=1);
    }

    //Boundary condition 2
    @Test
    void randIntTest2(){

        int min2= 0;
        int max2= 100;
        int test2 = Util.randInt(min2, max2);

        assertTrue(test2 <=100);
        assertTrue(test2 >= 0);
    }

    //Boundary condition 3
    @Test
    void randIntTest3(){

        int min3= 2;
        int max3= 3;
        int test3 = Util.randInt(min3, max3);

        assertTrue(test3 <=3);
        assertTrue(test3 >= 2);

    }

    /**
     * C in Bicep
     * cross checks randInt result by using a different way of generating a random number
     */

    @Test
    void randIntCrossCheckTest() {
        int min = 1;
        int max = 5;
        int usingMethod = Util.randInt(1, 5);
       int usingMath = (int)(Math.random() * ((max - min) + 1)) + min;

        assertTrue(usingMethod <=5 && usingMath <=5);
        assertTrue(usingMethod >=1 && usingMath >=1);



    }



}