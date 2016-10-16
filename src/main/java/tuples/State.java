package tuples;

public class State {
    private String state;

    public State(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State another = (State) o;

        return state != null ? state.equals(another.state) : another.state == null;

    }

    @Override
    public int hashCode() {
        return state != null ? state.hashCode() : 0;
    }
}
