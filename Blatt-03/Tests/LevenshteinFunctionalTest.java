import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import levenshtein.Levenshtein;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class LevenshteinFunctionalTest {
    String one;
    String two;
    int expected;

    public LevenshteinFunctionalTest(String one, String two, int expected) {
        this.one = one;
        this.two = two;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {"nähmlich", "dämlich", 1},
            {"programmieren", "studieren", 8},
            {"verifikation", "verifikation", 0},
            {"dasgleiche", "dasselbe", 5},
            {"sandy", "mandy", 1},
            {"horst", "hubertus", 6},
            {"", "", 0},
            {"", "donaudampfschiffahrtsgesellschaftskapitän", 40},
            {"donaudampfschiffahrtsgesellschaftskapitän", "", 40},
            {"donaudampfschiffahrtsgesellschaftskapitän", "donaudampfschiffahrtsgesellschaftskapitän", 0},
            {"donaudampfschiffahrtsgesellschaftskapitän", "donaudampfschiffartsgesellschaftskapitän", 0},
            {"A", "a", 0},
            {"ä", "ä", 0},
            {"Ä", "ä", 0},
            {"x", "", 1},
            {"", "x", 1},
            {"ah", "a", 0},
            {"bh","b", 1},
            {"a", "", 1},
            {"", "a", 1},
            {"abcdefghijklmnopqrstuvwxyz", "", 26},
            {"", "abcdefghijklmnopqrstuvwxyz", 26},
            {"fahrt", "", 4},
            {"", "fahrt", 4}
        });
    }

    @Test(timeout = 10000)
    public void programmierenStudierenTest() {
        Levenshtein levenshtein = new Levenshtein(one, two);
        String message = "d(" + one + "," + two + ")";
        int result = 0;
        try {
            result = levenshtein.getDistance();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            fail("Exception thrown for " + message);
        }
        assertEquals(message, expected, result);
    }

    public static void main(String[] args) throws Exception {
        JUnitCore.main(LevenshteinFunctionalTest.class.getName());
    }
}
