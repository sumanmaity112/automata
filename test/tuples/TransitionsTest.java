package tuples;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransitionsTest {

    @Test
    public void testGetShouldReturnTransitionUsingStateAndAlphabet() throws Exception {
        Transition q0Transition = new Transition();
        Transition q1Transition = new Transition();
        Alphabet alphabet0 = new Alphabet("0");
        Alphabet alphabet1 = new Alphabet("1");
        State q0 = new State("q0");
        State q1 = new State("q1");
        q0Transition.put(alphabet0, q0);
        q0Transition.put(alphabet1, q1);
        q1Transition.put(alphabet0, q1);
        q1Transition.put(alphabet1, q1);
        Transitions transitions = new Transitions();
        transitions.put(q0, q0Transition);
        transitions.put(q1, q1Transition);
        assertEquals(transitions.get(q0, alphabet0), q0);
        assertEquals(transitions.get(q0, alphabet1), q1);
        assertEquals(transitions.get(q1, alphabet0), q1);
        assertEquals(transitions.get(q1, alphabet1), q1);
    }
}