package tuples;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransitionsTest {
    private Transitions transitions;
    private State q0;
    private State q1;
    private Alphabet alphabet0;
    private Alphabet alphabet1;
    private Transition q0Transition;
    private Transition q1Transition;
    private ArrayList<State> states;
    private ArrayList<Alphabet> alphabets;

    @Before
    public void setUp() throws Exception {
        transitions = new Transitions();
        q0Transition = new Transition();
        q1Transition = new Transition();
        alphabet0 = new Alphabet("0");
        alphabet1 = new Alphabet("1");
        q0 = new State("q0");
        q1 = new State("q1");
        q0Transition.put(alphabet0, q0);
        q0Transition.put(alphabet1, q1);
        q1Transition.put(alphabet0, q1);
        q1Transition.put(alphabet1, q1);

        states = new ArrayList<>();
        states.add(q0);
        states.add(q1);
        alphabets = new ArrayList<>();
        alphabets.add(alphabet0);
        alphabets.add(alphabet1);
    }

    @Test
    public void testGetShouldReturnTransitionUsingStateAndAlphabet() throws Exception {
        transitions.put(q0, q0Transition);
        transitions.put(q1, q1Transition);
        assertEquals(transitions.get(q0, alphabet0), q0);
        assertEquals(transitions.get(q0, alphabet1), q1);
        assertEquals(transitions.get(q1, alphabet0), q1);
        assertEquals(transitions.get(q1, alphabet1), q1);
    }


    @Test
    public void testIsValidDFATransitionsShouldReturnTrueIfTransitionTableDoesNotContainsAnyInvalidStateOrInvalidAlphabet() throws Exception {
        transitions.put(q0, q0Transition);
        transitions.put(q1, q1Transition);
        assertTrue(transitions.isValidDFATransitions(new AlphabetSet(alphabets), new States(states)));
    }

    @Test
    public void testIsValidDFATransitionsShouldReturnFalseIfTransitionTableDoesNotHaveAllStates() throws Exception {
        transitions.put(q0, q0Transition);
        transitions.put(q1, q1Transition);
        states.add(new State("q2"));
        assertFalse(transitions.isValidDFATransitions(new AlphabetSet(alphabets), new States(states)));
    }

    @Test
    public void testIsValidDFATransitionShouldReturnFalseIfTransitionTableHasInvalidState() throws Exception {
        transitions.put(q0, q0Transition);
        transitions.put(q1, q1Transition);
        transitions.put(new State("q2"), new Transition());
        assertFalse(transitions.isValidDFATransitions(new AlphabetSet(alphabets), new States(states)));
    }

    @Test
    public void testIsValidDFATransitionShouldReturnFalseIfTransitionTableHasInvalidDestinationState() throws Exception {
        q0Transition.put(alphabet0, new State("q2"));
        transitions.put(q0, q0Transition);
        transitions.put(q1, q1Transition);
        assertFalse(transitions.isValidDFATransitions(new AlphabetSet(alphabets), new States(states)));
    }

    @Test
    public void testIsValidDFATransitionShouldReturnFalseIfTransitionTableHasInvalidAlphabet() throws Exception {
        q0Transition.put(new Alphabet("2"), q1);
        transitions.put(q0, q0Transition);
        transitions.put(q1, q1Transition);
        assertFalse(transitions.isValidDFATransitions(new AlphabetSet(alphabets), new States(states)));
    }
}