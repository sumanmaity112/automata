package parsers;

import finiteAutomata.DFA;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tuples.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class DfaInput {

    private DfaInput() {

    }

    public static DFA generateDfa(JSONObject jsonObject) throws JSONException {
        JSONObject tuple = jsonObject.getJSONObject("tuple");
        AlphabetSet alphabetSet = createAlphabetSet(tuple.getJSONArray("alphabets"));
        States states = createStates(tuple.getJSONArray("states"));
        FinalStates finalStates = createFinalStates(tuple.getJSONArray("final-states"));
        State initialState = new State(tuple.get("start-state").toString());
        Transitions transitions = createTransitionTable(tuple.getJSONObject("delta"));

        return DFA.generateDFA(states, initialState, alphabetSet, finalStates, transitions);
    }

    private static FinalStates createFinalStates(JSONArray states) {
        return new FinalStates(createStateList(states));
    }

    private static States createStates(JSONArray statesArray) {
        return new States(createStateList(statesArray));
    }

    private static ArrayList<State> createStateList(JSONArray statesArray) {
        ArrayList<State> states = new ArrayList<>();
        for (int index = 0; index < statesArray.length(); index++) {
            states.add(new State(statesArray.getString(index)));
        }
        return states;
    }

    private static AlphabetSet createAlphabetSet(JSONArray alphabets) {
        ArrayList<Alphabet> alphabetSet = new ArrayList<>();
        for (int index = 0; index < alphabets.length(); index++) {
            alphabetSet.add(new Alphabet(alphabets.getString(index)));
        }
        return new AlphabetSet(alphabetSet);
    }

    private static Transitions createTransitionTable(JSONObject delta) {
        Transitions transitions = new Transitions();
        Set keySet = delta.keySet();
        for (Object next : keySet) {
            Transition transition = createTransition(delta.getJSONObject(next.toString()));
            transitions.put(new State(next.toString()), transition);
        }

        return transitions;
    }

    private static Transition createTransition(JSONObject jsonObject) {
        Iterator keys = jsonObject.keys();
        Transition transition = new Transition();
        while (keys.hasNext()) {
            Object next = keys.next();
            transition.put(new Alphabet(next.toString()), new State(jsonObject.getString(next.toString())));
        }
        return transition;
    }

    public static Alphabet[] parseInputString(String inputs, String delimiter) {
        ArrayList<Alphabet> alphabets = new ArrayList<>();
        String[] inputsString = inputs.split(delimiter);

        for (String input : inputsString) {
            alphabets.add(new Alphabet(input));
        }
        return alphabets.toArray(new Alphabet[alphabets.size()]);
    }

    ;
}
