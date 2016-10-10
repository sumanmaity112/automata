package tuples;

import org.junit.Test;
import static org.junit.Assert.*;

public class TransitionTest {
    @Test
    public void testGetShouldReturnCorrespondingStateUsingAlphabet() throws Exception {
        Transition transition = new Transition();
        Alphabet alphabet0 = new Alphabet("0");
        Alphabet alphabet1 = new Alphabet("1");
        State q0 = new State("q0");
        State q1 = new State("q1");
        transition.put(alphabet0, q0);
        transition.put(alphabet1, q1);
        assertEquals(transition.get(alphabet0), q0);
        assertEquals(transition.get(alphabet1), q1);
    }
}