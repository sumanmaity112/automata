package finiteAutomata;

import tuples.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DFA {
    private final States states;
    private final State initialState;
    private final AlphabetSet alphabetSet;
    private final FinalStates finalStates;
    private HashMap<State, HashMap<Alphabet, State>> transitions;

    public DFA(States states, State initialState, AlphabetSet alphabetSet, FinalStates finalStates, HashMap<State, HashMap<Alphabet, State>> transitions) {
        this.states = states;
        this.initialState = initialState;
        this.alphabetSet = alphabetSet;
        this.finalStates = finalStates;
        this.transitions = transitions;
    }

    public boolean isRecognize(Alphabet[] inputs) {
        boolean validInput = isValidInput(inputs);
        return validInput ? isValid(inputs) : generateErrorMessage(inputs);
    }

    private boolean isValid(Alphabet[] inputs) {
        State currentState = initialState;
            ArrayList<State> allStates = this.states.getStates();
        for (Alphabet input : inputs) {
            currentState = findNextState(currentState, input);
            if(!allStates.contains(currentState)){
                throw new RuntimeException("Please check your transitions table");
            }
        }
        return finalStates.getFinalStates().contains(currentState);
    }

    private boolean generateErrorMessage(Alphabet[] inputs) {
        StringBuilder builder = new StringBuilder();
        for (Alphabet input : inputs) {
            builder.append(input.toString());
        }
        throw new RuntimeException("Please check input " + builder.toString());
    }

    private State findNextState(State currentState, Alphabet input) {
        return transitions.get(currentState).get(input);
    }

    private boolean isValidInput(Alphabet[] inputs) {
        HashSet<Alphabet> alphabets = alphabetSet.getAlphabets();
        for (Alphabet input : inputs) {
            if (!(alphabets.contains(input))) {
                return false;
            }
        }
        return true;
    }
}
