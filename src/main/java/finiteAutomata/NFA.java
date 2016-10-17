package finiteAutomata;

import tuples.*;

import java.util.ArrayList;
import java.util.Arrays;

public class NFA extends FiniteAutomata {
    private NfaTransitions transitions;

    private NFA(States states, State initialState, AlphabetSet alphabetSet, FinalStates finalStates, NfaTransitions transitions) {
        super(alphabetSet, initialState, states, finalStates);
        this.transitions = transitions;
    }

    public static NFA generateNFA(States states, State initialState, AlphabetSet alphabetSet, FinalStates finalStates, NfaTransitions transitions) {
        return new NFA(states, initialState, alphabetSet, finalStates, transitions);
    }

    public boolean isRecognize(Alphabet[] inputs) {
        boolean validInput = isValidInput(inputs);
        return validInput ? isValid(inputs) : generateErrorMessage(inputs);
    }

    private boolean isValid(Alphabet[] inputs) {
        ArrayList<NFA> nfas = new ArrayList<>();
        if (isEmptyInput(inputs)) {
            return isOnFinalState(initialState);
        }
        State currentState = initialState;
        for (int counter = 0; counter < inputs.length; counter++) {
            Alphabet input = inputs[counter];
            States nextStates = findNextState(currentState, input);
            if (nextStates == null) {
                return false;
            }
            for (int index = 0; index < nextStates.size(); index++) {
                State nextState = nextStates.getByIndex(index);
                currentState = nextState;
                NFA nfa = new NFA(states, nextState, alphabetSet, finalStates, transitions);
                nfas.add(nfa);
                if (nfa.isValid(Arrays.copyOfRange(inputs, counter + 1, inputs.length))) {
                    return true;
                }
            }

        }
        return isOnFinalState(currentState);
    }

    private States findNextState(State currentState, Alphabet input) {
        return transitions.get(currentState, input);
    }
}
