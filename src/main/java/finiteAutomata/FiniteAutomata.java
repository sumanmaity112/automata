package finiteAutomata;

import exceptions.InvalidInput;
import tuples.*;

public abstract class FiniteAutomata {
    protected final AlphabetSet alphabetSet;
    protected final States states;
    protected final State initialState;
    protected final FinalStates finalStates;

    public FiniteAutomata(AlphabetSet alphabetSet, State initialState, States states, FinalStates finalStates) {
        this.alphabetSet = alphabetSet;
        this.initialState = initialState;
        this.states = states;
        this.finalStates = finalStates;
    }

    protected boolean generateErrorMessage(Alphabet[] inputs) {
        StringBuilder builder = new StringBuilder();
        for (Alphabet input : inputs) {
            builder.append(input.toString());
        }
        throw new InvalidInput("Please check input " + builder.toString());
    }

    protected boolean isValidInput(Alphabet[] inputs) {
        for (Alphabet input : inputs) {
            if (!input.equals(new Alphabet("")) && !(alphabetSet.contains(input))) {
                return false;
            }
        }
        return true;
    }

    protected boolean isOnFinalState(State state) {
        return finalStates.contains(state);
    }

    protected boolean isEmptyInput(Alphabet[] inputs) {
        return inputs.length == 1 && inputs[0].equals(new Alphabet(""));
    }
}
