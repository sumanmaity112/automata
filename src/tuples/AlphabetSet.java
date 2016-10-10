package tuples;

import java.util.ArrayList;
import java.util.HashSet;

public class AlphabetSet {
    private HashSet<Alphabet> alphabets;

    public AlphabetSet(ArrayList<Alphabet> alphabets) {
        this.alphabets = new HashSet<>(alphabets);
    }

    public HashSet<Alphabet> getAlphabets() {
        return alphabets;
    }
}
