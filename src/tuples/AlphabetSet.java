package tuples;

import java.util.ArrayList;
import java.util.HashSet;

public class AlphabetSet {
    private HashSet<Alphabet> alphabets;

    public AlphabetSet(ArrayList<Alphabet> alphabets) {
        this.alphabets = new HashSet<>(alphabets);
    }

    public boolean contains(Alphabet input) {
        return alphabets.contains(input);
    }

    public int size() {
        return alphabets.size();
    }
}
