package io.nicky.translator.protocol.units;

public final class Pair<First, Last> {

    private First first;
    private Last last;

    public Pair(First first, Last last) {
        this.first = first;
        this.last = last;
    }

    public Pair() {
    }

    @Override
    public String toString() {
        return first.toString() + ", " + last.toString();
    }

    public First getFirst() {
        return first;
    }

    public Last getLast() {
        return last;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public void setLast(Last last) {
        this.last = last;
    }
}
