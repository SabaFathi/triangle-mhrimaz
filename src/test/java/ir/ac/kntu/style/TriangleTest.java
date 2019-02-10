/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu.style;

import ir.ac.kntu.Triangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mhrimaz
 */
public class TriangleTest {

    private static final String INVALID = "INVALID";
    private static final String EQUILATERAL = "EQUILATERAL";
    private static final String ISOSCELES = "ISOSCELES";
    private static final String SCALENE = "SCALENE";
    private static int count = 1;

    public boolean detectTriangle(int a, int b, int c, String type) {
        count++;
        InputStream in = null;
        PrintStream printStream = null;
        try {
            File input = new File("input.txt");
            File output = new File("output.txt");
            in = new FileInputStream(input);
            printStream = new PrintStream(output);
            try (PrintWriter w = new PrintWriter(input);) {
                w.println(a);
                w.println(b);
                w.println(c);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(output))) {
                System.setIn(in);
                System.setOut(printStream);
                Triangle.main(new String[]{});
                return reader.readLine().equalsIgnoreCase(type);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                in.close();
                printStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Test
    public void testInvalidTriangle() {
        System.err.println("$$$GRADER$$$ | ADDSCORE | AMOUNT | REASON");
        assertTrue("Unable to detect invalid trinagle",
                detectTriangle(1, 3, 5, INVALID));
        assertTrue("Unable to detect invalid trinagle [CHECK OVERFLOW]",
                detectTriangle(Integer.MAX_VALUE, 10, Integer.MAX_VALUE - 10, INVALID));
    }

    @Test
    public void testValidTriangle() {
        System.err.println("$$$GRADER$$$ | ADDSCORE | AMOUNT | REASON");
        assertFalse("Unable to detect valid trinagle",
                detectTriangle(4, 3, 5, INVALID));
        assertFalse("Unable to detect valid trinagle [CHECK OVERFLOW]",
                detectTriangle(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, INVALID));
        assertFalse("Unable to detect valid trinagle [CHECK OVERFLOW]",
                detectTriangle(Integer.MAX_VALUE, 1, Integer.MAX_VALUE, INVALID));
    }

    @Test
    public void testScaleneTriangle() {
        System.err.println("$$$GRADER$$$ | ADDSCORE | AMOUNT | REASON");
        assertTrue("Unable to detect valid SCALENE trinagle",
                detectTriangle(4, 3, 5, SCALENE));
        assertTrue("Unable to detect valid SCALENE trinagle",
                detectTriangle(5, 3, 7, SCALENE));
        assertTrue("Unable to detect valid SCALENE trinagle [CHECK OVERFLOW]",
                detectTriangle(2147483640, 2147483644, 7, SCALENE));
    }

    @Test
    public void testIsoscelesTriangle() {
        System.err.println("$$$GRADER$$$ | ADDSCORE | AMOUNT | REASON");
        assertTrue("Unable to detect ISOSCELES trinagle",
                detectTriangle(4, 3, 3,
                        ISOSCELES));
        assertFalse("Unable to detect invalid trinagle",
                detectTriangle(3, 3, 7, ISOSCELES));
        assertTrue("Unable to detect ISOSCELES trinagle [CHECK OVERFLOW]",
                detectTriangle(Integer.MAX_VALUE, 1, Integer.MAX_VALUE,
                        ISOSCELES));
    }

    @Test
    public void testEquilateralTriangle() {
        System.err.println("$$$GRADER$$$ | ADDSCORE | AMOUNT | REASON");
        assertTrue("Unable to detect EQUILATERAL trinagle",
                detectTriangle(3, 3, 3,
                        EQUILATERAL));
        assertTrue("Unable to detect EQUILATERAL trinagle [CHECK OVERFLOW]",
                detectTriangle(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, EQUILATERAL));
    }

}
