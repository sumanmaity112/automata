package finiteAutomata;

import exceptions.InvalidInput;
import exceptions.InvalidTransition;
import tuples.*;

import java.util.HashMap;

public class DFA {
    private final States states;
    private final State initialState;
    private final AlphabetSet alphabetSet;
    private final FinalStates finalStates;
    private Transitions transitions;

    private DFA(States states, State initialState, AlphabetSet alphabetSet, FinalStates finalStates, Transitions transitions) {
        this.states = states;
        this.initialState = initialState;
        this.alphabetSet = alphabetSet;
        this.finalStates = finalStates;
        this.transitions = transitions;
    }

    public static DFA generateDFA(States states, State initialState, AlphabetSet alphabetSet, FinalStates finalStates, Transitions transitions) {
        if (!transitions.isValidDFATransitions(alphabetSet, states)) {
            throw new InvalidTransition("Please check your transitions table");
        }
        return new DFA(states, initialState, alphabetSet, finalStates, transitions);
    }

    public boolean isRecognize(Alphabet[] inputs) {
        boolean validInput = isValidInput(inputs);
        return validInput ? isValid(inputs) : generateErrorMessage(inputs);
    }

    private boolean isValid(Alphabet[] inputs) {
        State currentState = initialState;
        for (Alphabet input : inputs) {
            currentState = findNextState(currentState, input);
        }
        return finalStates.contains(currentState);
    }

    private boolean generateErrorMessage(Alphabet[] inputs) {
        StringBuilder builder = new StringBuilder();
        for (Alphabet input : inputs) {
            builder.append(input.toString());
        }
        throw new InvalidInput("Please check input " + builder.toString());
    }

    private State findNextState(State currentState, Alphabet input) {
        return transitions.get(currentState, input);
    }

    private boolean isValidInput(Alphabet[] inputs) {
        for (Alphabet input : inputs) {
            if (!(alphabetSet.contains(input))) {
                return false;
            }
        }
        return true;
    }
}
