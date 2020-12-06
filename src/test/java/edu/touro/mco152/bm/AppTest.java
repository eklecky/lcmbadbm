package edu.touro.mco152.bm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    /**
     * 'R' in Right Bicep
     * simply want to check the bp version
     */
    @Test
    void getVersionTest() {
        String expected = "0.4";
        String version = App.getVersion();
        assertEquals(expected, version);
    }

    /**
     * E in bicep
     * this method 'forces' an error condition which would break the test if I 'assertedEquals' instead
     */
    @Test
    void getVersionTestForcedFail() {
        String expected = "0.0";
        String version = App.getVersion();
        assertNotEquals(expected, version);
    }

    @Test
    void getConfigStringTest() {
        String expected = "Config for Java Disk Mark 0.4\n" +
                "readTest: false\n" +
                "writeTest: true\n" +
                "locationDir: null\n" +
                "multiFile: true\n" +
                "autoRemoveData: false\n" +
                "autoReset: true\n" +
                "blockSequence: SEQUENTIAL\n" +
                "showMaxMin: true\n" +
                "numOfFiles: 25\n" +
                "numOfBlocks: 32\n" +
                "blockSizeKb: 512\n";


        String configString = App.getConfigString();

        assertEquals(expected, configString);
    }

    /**
     * simply test the target mark size
     */
    @Test
    void targetMarkSizeKbTest(){
        long expected = 16384;
        long actual = App.targetMarkSizeKb();
         assertEquals(expected, actual);
    }

    /**
     * A simple demo of Parameterized test methods
     * @param nums
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void targetMarkSizeKbParameterizedTestDemo(int nums){
        //long expected = 16384;
        long actual = App.targetMarkSizeKb();
        assertTrue(nums != actual);
    }

}