package parsers;

import tuples.Alphabet;

import java.util.ArrayList;

public abstract class InputParser {
    public Alphabet[] parseInputString(String inputs, String delimiter) {
        ArrayList<Alphabet> alphabets = new ArrayList<>();
        String[] inputsString = inputs.split(delimiter);

        for (String input : inputsString) {
            alphabets.add(new Alphabet(input));
        }
        return alphabets.toArray(new Alphabet[alphabets.size()]);
    }
}
