package tuples;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FinalStatesTest {

    private State q0;
    private State q1;
    private FinalStates finalStates;

    @Before
    public void setUp() throws Exception {
        q0 = new State("q0");
        q1 = new State("q1");

        ArrayList<State> statesList = new ArrayList<>();
        statesList.add(q0);
        statesList.add(q1);

        finalStates = new FinalStates(statesList);
    }

    @Test
    public void testContainsShouldReturnTrueWhenContainsGivenState() throws Exception {
        assertTrue(finalStates.contains(q0));
        assertTrue(finalStates.contains(q1));
    }

    @Test
    public void testContainsShouldReturnFalseWhenDoesNotContainsGivenState() throws Exception {
        assertFalse(finalStates.contains(new State("q2")));
    }
}