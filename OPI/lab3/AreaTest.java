import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import utils.HitChecker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AreaTest {
    private HitChecker checker;

    @BeforeEach
    public void setUp() {
        checker = new HitChecker();
    }

    @Test
    public void checkIfInTriangle() {
        assertTrue(checker.hit(-0.7, -0.17, 2.0));
    }

    @Test
    public void checkIfNotInTriangle() {
        assertFalse(checker.hit(-2.5, -0.9, 2.5));
    }

    @Test
    public void checkIfInSquare() {
        assertTrue(checker.hit(2.5, -1.8, 3.0));
    }

    @Test
    public void checkIfNotInSquare() {
        assertFalse(checker.hit(0.28, -1.26, 1.0));
    }

    @Test
    public void checkIfInCircle() {
        assertTrue(checker.hit(0.12, 0.29, 1.0));
    }

    @Test
    public void checkIfNotInCircle() {
        assertFalse(checker.hit(2.36, 1.34, 2.5));
    }

    @Test
    public void checkIfInArea() {
        assertFalse(checker.hit(4.6, -6.1, 4));
    }

    @Test
    public void checkIfOnCircleBorder() {
        assertFalse(checker.hit(1.11, 1.035, 3));
        assertTrue(checker.hit(0.009, 0.31, 3));
    }

    @Test
    public void checkIfOnTriangleBorder1() {
        assertTrue(checker.hit(-0.5, -0.015, 3.0));
    }

    @Test
    public void checkIfOnTriangleBorder2() {
        assertFalse(checker.hit(-0.53, -0.97, 3.0));
    }

    @Test
    public void checkIfOnSquareBorder1() {
        assertFalse(checker.hit(-0.11, -2.145, 3.0));
    }

    @Test
    public void checkIfOnSquareBorder2() {
        assertTrue(checker.hit(2.94, 2.95, 3.0));
    }

    @Test
    public void checkIfBetweenFigures1() {
        assertTrue(checker.hit(1, 0, 4.0));
    }

    @Test
    public void checkIfBetweenFigures2() {
        assertTrue(checker.hit(0, -1, 4.0));
    }
}
