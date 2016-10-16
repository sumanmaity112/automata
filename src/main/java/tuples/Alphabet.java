package tuples;

public class Alphabet {
    private String alphabet;

    public Alphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    @Override
    public String toString() {
        return alphabet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alphabet another = (Alphabet) o;

        return alphabet != null ? alphabet.equals(another.alphabet) : another.alphabet == null;

    }

    @Override
    public int hashCode() {
        return alphabet != null ? alphabet.hashCode() : 0;
    }
}
