package parsers;

import finiteAutomata.NFA;
import org.json.JSONArray;
import org.json.JSONObject;
import tuples.*;

import java.util.Set;

public class NfaInputParser extends InputParser {
    public NFA generateNfa(JSONObject jsonObject) {
        JSONObject tuple = jsonObject.getJSONObject("tuple");
        AlphabetSet alphabetSet = createAlphabetSet(tuple.getJSONArray("alphabets"));
        States states = createStates(tuple.getJSONArray("states"));
        FinalStates finalStates = createFinalStates(tuple.getJSONArray("final-states"));
        State initialState = new State(tuple.get("start-state").toString());
        NfaTransitions transitions = createTransitionTable(tuple.getJSONObject("delta"));

        return NFA.generateNFA(states, initialState, alphabetSet, finalStates, transitions);
    }

    private NfaTransitions createTransitionTable(JSONObject delta) {
        NfaTransitions transitions = new NfaTransitions();
        Set keySet = delta.keySet();
        for (Object next : keySet) {
            NfaTransition transition = createTransition(delta.getJSONObject(next.toString()));
            transitions.put(new State(next.toString()), transition);
        }

        return transitions;
    }

    private NfaTransition createTransition(JSONObject jsonObject) {
        Set<String> nextStates = jsonObject.keySet();
        NfaTransition transition = new NfaTransition();
        for(String nextState: nextStates){
            JSONArray jsonArray = jsonObject.getJSONArray(nextState);
            transition.put(new Alphabet(nextState), createStates(jsonArray));
        }
        return transition;
    }
}
