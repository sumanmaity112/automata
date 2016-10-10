package tuples;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StatesTest {

    private State q0;
    private State q1;
    private State q2;
    private States states;

    @Before
    public void setUp() throws Exception {
        q0 = new State("q0");
        q1 = new State("q1");
        q2 = new State("q2");
        ArrayList<State> statesList = new ArrayList<>();
        statesList.add(q0);
        statesList.add(q1);
        statesList.add(q2);
        states = new States(statesList);
    }

    @Test
    public void testContainsShouldReturnTrueWhenContainsGivenState() throws Exception {
        assertTrue(states.contains(q0));
        assertTrue(states.contains(q1));
        assertTrue(states.contains(q2));
    }

    @Test
    public void testContainsShouldReturnFalseWhenDoesNotContainsGivenState() throws Exception {
        assertFalse(states.contains(new State("q3")));
    }
}