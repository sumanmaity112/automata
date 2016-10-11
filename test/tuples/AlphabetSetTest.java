package tuples;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlphabetSetTest {

    private AlphabetSet alphabetSet;
    private Alphabet alphabet0;
    private Alphabet alphabet1;
    private Alphabet alphabet2;
    private ArrayList<Alphabet> alphabets;

    @Before
    public void setUp() throws Exception {
        alphabets = new ArrayList<>();
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
        assertTrue(alphabetSet.contains(new Alphabet("1")));
    }

    @Test
    public void testContainsShouldReturnFalseWhenDoesNotContainsGivenAlphabet() throws Exception {
        assertFalse(alphabetSet.contains(new Alphabet("3")));
    }

    @Test
    public void testSizeShouldReturnTheNumberOfAlphabetInAlphabetSet() throws Exception {
        assertEquals(alphabetSet.size(), 3);
        alphabets.add(new Alphabet("3"));
        AlphabetSet alphabetSet = new AlphabetSet(alphabets);
        assertEquals(alphabetSet.size(), 4);

    }
}