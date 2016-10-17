package tuples;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NfaTransitionsTest {

    private State q1;
    private State q2;
    private NfaTransitions transitions;
    private States q1Alphabet0States;
    private States q1Alphabet1States;
    private States q2Alphabet0States;
    private States q2Alphabet1States;

    @Before
    public void setUp() throws Exception {
        ArrayList<Alphabet> alphabets = new ArrayList<>();
        Alphabet alphabet0 = new Alphabet("0");
        Alphabet alphabet1 = new Alphabet("1");
        alphabets.add(alphabet0);
        alphabets.add(alphabet1);
        AlphabetSet alphabetSet = new AlphabetSet(alphabets);
        q1 = new State("q1");
        q2 = new State("q2");

        ArrayList<State> q2Alphabet0 = new ArrayList<>();
        ArrayList<State> q2Alphabet1 = new ArrayList<>();

        NfaTransition q1Transition = new NfaTransition();

        ArrayList<State> q1Alphabet0 = new ArrayList<>();
        ArrayList<State> q1Alphabet1 = new ArrayList<>();

        q1Alphabet0.add(q1);
        q1Alphabet1.add(q1);
        q1Alphabet0States = new States(q1Alphabet0);
        q1Transition.put(alphabet0, q1Alphabet0States);
        q1Alphabet1States = new States(q1Alphabet1);
        q1Transition.put(alphabet1, q1Alphabet1States);
        NfaTransition q2Transition = new NfaTransition();
        q2Alphabet0States = new States(q2Alphabet0);
        q2Transition.put(alphabet0, q2Alphabet0States);
        q2Alphabet1States = new States(q2Alphabet1);
        q2Transition.put(alphabet1, q2Alphabet1States);

        transitions = new NfaTransitions();
        transitions.put(q1, q1Transition);
        transitions.put(q2, q2Transition);
    }

    @Test
    public void getShouldReturnTransitionUsingStateAndAlphabet() throws Exception {
        assertEquals(transitions.get(q1, new Alphabet("0")), q1Alphabet0States);
        assertEquals(transitions.get(q1, new Alphabet("1")), q1Alphabet1States);
        assertEquals(transitions.get(q2, new Alphabet("0")), q2Alphabet0States);
        assertEquals(transitions.get(q2, new Alphabet("1")), q2Alphabet1States);
        assertEquals(transitions.get(new State("q3"), new Alphabet("1")), null);
        assertEquals(transitions.get(q2, new Alphabet("3")), null);
    }
}