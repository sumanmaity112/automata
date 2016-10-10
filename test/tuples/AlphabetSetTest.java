package tuples;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlphabetSetTest {

    private AlphabetSet alphabetSet;
    private Alphabet alphabet0;
    private Alphabet alphabet1;
    private Alphabet alphabet2;

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

    }

    @Test
    public void testContainsShouldReturnTrueWhenContainsGivenAlphabet() throws Exception {
        assertTrue(alphabetSet.contains(alphabet0));
        assertTrue(alphabetSet.contains(alphabet1));
        assertTrue(alphabetSet.contains(alphabet2));
    }

    @Test
    public void testContainsShouldReturnFalseWhenDoesNotContainsGivenAlphabet() throws Exception {
        assertFalse(alphabetSet.contains(new Alphabet("3")));
    }
}