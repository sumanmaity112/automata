package finiteAutomata;

import exceptions.InvalidInput;
import exceptions.InvalidTransition;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tuples.*;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DFATest {
    private Alphabet alphabet0, alphabet1, alphabet2;
    private State q0, q1, q2;
    private State initialState;
    private ArrayList<State> states, finalStates;
    private Transitions transitions;
    private AlphabetSet alphabetSet;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ArrayList<Alphabet> alphabets = new ArrayList<>();
        alphabet0 = new Alphabet("0");
        alphabet1 = new Alphabet("1");
        alphabet2 = new Alphabet("2");
        alphabets.add(alphabet0);
        alphabets.add(alphabet1);
        alphabets.add(alphabet2);
        alphabetSet = new AlphabetSet(alphabets);
        q0 = new State("q0");
        q1 = new State("q1");
        q2 = new State("q2");

        initialState = q0;

        states = new ArrayList<>();
        states.add(q0);
        states.add(q1);
        states.add(q2);

        finalStates = new ArrayList<>();
        finalStates.add(q0);

        transitions = new Transitions();

        Transition q0Transition = new Transition();
        Transition q1Transition = new Transition();
        Transition q2Transition = new Transition();
        q0Transition.put(alphabet0, q0);
        q0Transition.put(alphabet1, q1);
        q0Transition.put(alphabet2, q2);

        q1Transition.put(alphabet0, q1);
        q1Transition.put(alphabet1, q2);
        q1Transition.put(alphabet2, q0);

        q2Transition.put(alphabet0, q2);
        q2Transition.put(alphabet1, q0);
        q2Transition.put(alphabet2, q1);

        transitions.put(q0, q0Transition);
        transitions.put(q1, q1Transition);
        transitions.put(q2, q2Transition);

    }

    @Test
    public void testIsRecognizeShouldReturnTrueForARecognizedInput() throws Exception {
        DFA dfa = DFA.generateDFA(new States(states), initialState, alphabetSet, new FinalStates(finalStates), transitions);
        Alphabet inputs[] = {alphabet1, alphabet1, alphabet1, alphabet1, alphabet0, alphabet2};
        assertTrue(dfa.isRecognize(inputs));
    }

    @Test
    public void testIsRecognizeShouldReturnFalseForANotRecognizedInput() throws Exception {
        DFA dfa = DFA.generateDFA(new States(states), initialState, alphabetSet, new FinalStates(finalStates), transitions);
        Alphabet inputs[] = {alphabet1, alphabet1, alphabet1, alphabet1, alphabet0, alphabet2, alphabet2};
        assertFalse(dfa.isRecognize(inputs));
    }

    @Test
    public void testIsRecognizeShouldThrowARuntimeExceptionForInvalidAlphabetInGivenInput() throws Exception {
        DFA dfa = DFA.generateDFA(new States(states), initialState, alphabetSet, new FinalStates(finalStates), transitions);
        Alphabet alphabet3 = new Alphabet("3");
        Alphabet inputs[] = {alphabet1, alphabet1, alphabet1, alphabet1, alphabet0, alphabet2, alphabet2, alphabet3};
        thrown.expect(InvalidInput.class);
        thrown.expectMessage("Please check input 11110223");
        dfa.isRecognize(inputs);
    }

    @Test
    public void testShouldThrowARuntimeExceptionForInvalidStateInGivenTransitionTable() throws Exception {
        Transition q3Transition = new Transition();
        q3Transition.put(alphabet0, q0);
        q3Transition.put(alphabet1, q1);
        q3Transition.put(alphabet2, q2);

        State q1State = transitions.get(q1, alphabet0);
        Transition transition = new Transition();

        State q3 = new State("q3");
        transition.put(alphabet0, q3);
        transitions.put(q1State, transition);
        transitions.put(q3, q3Transition);

        thrown.expect(InvalidTransition.class);
        thrown.expectMessage("Please check your transitions table");
        DFA dfa = DFA.generateDFA(new States(states), initialState, alphabetSet, new FinalStates(finalStates), transitions);
    }
}