package tuples;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class NfaTransitionTest {
    @Test
    public void testGetShouldReturnCorrespondingStatesUsingAlphabet() throws Exception {
        NfaTransition transition = new NfaTransition();
        State q1 = new State("q1");
        State q2 = new State("q2");
        Alphabet alphabet0 = new Alphabet("0");
        Alphabet alphabet1 = new Alphabet("1");
        ArrayList<State> q1Alphabet0 = new ArrayList<>();
        ArrayList<State> q1Alphabet1 = new ArrayList<>();
        q1Alphabet0.add(q1);
        q1Alphabet1.add(q1);
        q1Alphabet1.add(q2);
        States q1Alphabet0states = new States(q1Alphabet0);
        transition.put(alphabet0, q1Alphabet0states);
        States q1Alphabet1states = new States(q1Alphabet1);
        transition.put(alphabet1, q1Alphabet1states);
        assertEquals(transition.get(alphabet0), q1Alphabet0states);
        assertEquals(transition.get(alphabet1), q1Alphabet1states);
    }

}