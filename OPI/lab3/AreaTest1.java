import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.HitChecker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AreaTest1 {

    private HitChecker checker;

    @BeforeEach
    public void setUp() {
        checker = new HitChecker();
    }


    @Test
    public void checkIfInTheSpot() {
        assertTrue(checker.hit(2.289, -1.636, 3));
    }


    @Test
    public void checkIfNotInTheSpot() {
        assertFalse(checker.hit(-2.511, -3.345, 3));
    }
}

