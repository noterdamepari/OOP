package ru.nsu.graphs;

import ru.nsu.misc.Edge;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractGraph<V> implements Graph<V> {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Graph<?> other)) return false;
        return getVertices().equals(other.getVertices())
                && getEdges().equals(other.getEdges());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVertices(), getEdges());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<V> hasEdges = new HashSet<>();

        for (Edge<V> e : getEdges()) {
            sb.append(e.from()).append(" -> ").append(e.to()).append('\n');
            hasEdges.add(e.from());
            hasEdges.add(e.to());
        }
        for (V v : getVertices()) {
            if (!hasEdges.contains(v)) {
                sb.append(v).append('\n');
            }
        }
        return sb.toString();
    }
}
