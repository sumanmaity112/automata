package parsers;

import org.json.JSONArray;
import tuples.*;

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

    protected FinalStates createFinalStates(JSONArray states) {
        return new FinalStates(createStateList(states));
    }

    protected States createStates(JSONArray statesArray) {
        return new States(createStateList(statesArray));
    }

    private ArrayList<State> createStateList(JSONArray statesArray) {
        ArrayList<State> states = new ArrayList<>();
        for (int index = 0; index < statesArray.length(); index++) {
            states.add(new State(statesArray.getString(index)));
        }
        return states;
    }

    protected AlphabetSet createAlphabetSet(JSONArray alphabets) {
        ArrayList<Alphabet> alphabetSet = new ArrayList<>();
        for (int index = 0; index < alphabets.length(); index++) {
            alphabetSet.add(new Alphabet(alphabets.getString(index)));
        }
        return new AlphabetSet(alphabetSet);
    }

    public String parseJSONString(String jsonString) {
        if (jsonString.startsWith("\"") && jsonString.endsWith("\"")) {
            jsonString = jsonString.substring(1, jsonString.length() - 1);
            jsonString = jsonString.replaceAll("\\\\\"", "\"");
        }
        return jsonString;
    }
}
