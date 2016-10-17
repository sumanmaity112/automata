package finiteAutomata;

import exceptions.InvalidInput;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tuples.*;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NFATest {

    private Alphabet alphabet0;
    private Alphabet alphabet1;
    private AlphabetSet alphabetSet;
    private State q1;
    private State q2;
    private State initialState;
    private ArrayList<State> states;
    private ArrayList<State> finalStates;
    private NfaTransitions transitions;
    private State q4;
    private State q3;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ArrayList<Alphabet> alphabets = new ArrayList<>();
        alphabet0 = new Alphabet("0");
        alphabet1 = new Alphabet("1");
        alphabets.add(alphabet0);
        alphabets.add(alphabet1);
        alphabetSet = new AlphabetSet(alphabets);
        q1 = new State("q1");
        q2 = new State("q2");
        q3 = new State("q3");
        q4 = new State("q4");

        initialState = q1;

        states = new ArrayList<>();
        states.add(q1);
        states.add(q2);
        states.add(q3);
        states.add(q4);

        finalStates = new ArrayList<>();
        finalStates.add(q4);

        transitions = new NfaTransitions();
        NfaTransition q1Transition = new NfaTransition();
        NfaTransition q2Transition = new NfaTransition();
        NfaTransition q3Transition = new NfaTransition();

        ArrayList<State> q1Alphabet0 = new ArrayList<>();
        ArrayList<State> q1Alphabet1 = new ArrayList<>();
        ArrayList<State> q2Alphabet0 = new ArrayList<>();
        ArrayList<State> q2Alphabet1 = new ArrayList<>();
        ArrayList<State> q3Alphabet0 = new ArrayList<>();
        ArrayList<State> q3Alphabet1 = new ArrayList<>();

        q1Alphabet0.add(q1);
        q1Alphabet1.add(q1);
        q1Alphabet1.add(q2);
        q1Transition.put(alphabet0, new States(q1Alphabet0));
        q1Transition.put(alphabet1, new States(q1Alphabet1));

        q2Alphabet0.add(q3);
        q2Alphabet1.add(q3);
        q2Transition.put(alphabet0, new States(q2Alphabet0));
        q2Transition.put(alphabet1, new States(q2Alphabet1));

        q3Alphabet0.add(q4);
        q3Alphabet1.add(q4);
        q3Transition.put(alphabet0, new States(q3Alphabet0));
        q3Transition.put(alphabet1, new States(q3Alphabet1));

        transitions.put(q1, q1Transition);
        transitions.put(q2, q2Transition);
        transitions.put(q3, q3Transition);
    }

    @Test
    public void isRecognizeShouldReturnTrueForARecognizedInput() throws Exception {
        NFA nfa = NFA.generateNFA(new States(states), initialState, alphabetSet, new FinalStates(finalStates), transitions);
        assertTrue(nfa.isRecognize(new Alphabet[]{alphabet1, alphabet0, alphabet0}));
        assertTrue(nfa.isRecognize(new Alphabet[]{alphabet0, alphabet0, alphabet0, alphabet1, alphabet0, alphabet0}));
        assertTrue(nfa.isRecognize(new Alphabet[]{alphabet1, alphabet1, alphabet0}));
        assertTrue(nfa.isRecognize(new Alphabet[]{alphabet1, alphabet0, alphabet1, alphabet1, alphabet0}));
    }

    @Test
    public void isRecognizeShouldReturnFalseForANotRecognizedInput() throws Exception {
        NFA nfa = NFA.generateNFA(new States(states), initialState, alphabetSet, new FinalStates(finalStates), transitions);
        assertFalse(nfa.isRecognize(new Alphabet[]{alphabet0, alphabet0, alphabet0}));
        assertFalse(nfa.isRecognize(new Alphabet[]{alphabet0, alphabet0, alphabet0, alphabet0}));
        assertFalse(nfa.isRecognize(new Alphabet[]{alphabet0, alphabet0, alphabet1}));
    }

    @Test
    public void isRecognizeShouldReturnTrueWhenInitialStateIsFinalState() throws Exception {
        NFA nfa = NFA.generateNFA(new States(states), q4, alphabetSet, new FinalStates(finalStates), transitions);

        Alphabet inputs[] = {new Alphabet("")};
        assertTrue(nfa.isRecognize(inputs));
    }

    @Test
    public void isRecognizeShouldReturnFalseWhenInitialStateIsNotFinalState() throws Exception {
        State initialState = new State("q1");
        NFA nfa = NFA.generateNFA(new States(states), initialState, alphabetSet, new FinalStates(finalStates), transitions);
        Alphabet inputs[] = {new Alphabet("")};
        assertFalse(nfa.isRecognize(inputs));
    }

    @Test
    public void testIsRecognizeShouldThrowARuntimeExceptionForInvalidAlphabetInGivenInput() throws Exception {
        NFA nfa = NFA.generateNFA(new States(states), initialState, alphabetSet, new FinalStates(finalStates), transitions);
        Alphabet alphabet3 = new Alphabet("3");
        Alphabet inputs[] = {alphabet1, alphabet1, alphabet1, alphabet1, alphabet0, alphabet3};
        thrown.expect(InvalidInput.class);
        thrown.expectMessage("Please check input 111103");
        nfa.isRecognize(inputs);
    }
}