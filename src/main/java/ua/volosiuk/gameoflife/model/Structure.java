package ua.volosiuk.gameoflife.model;

import java.util.ArrayList;
import java.util.List;
public class Structure {
    private int edgeLength;
    private List<List<Integer>> nextStructure = new ArrayList<>();
    public Structure() {
    }
    public Structure(int edgeLength) {
        this.edgeLength = edgeLength;
    }

    public Structure(int edgeLength, List<List<Integer>> currentGeneration) {
        this.edgeLength = edgeLength;
        this.nextStructure = currentGeneration;
    }
    public int getEdgeLength() {
        return edgeLength;
    }

    public void setEdgeLength(int edgeLength) {
        this.edgeLength = edgeLength;
    }
    public List<List<Integer>> getNextStructure() {
        return nextStructure;
    }
    public void setNextStructure(List<List<Integer>> nextStructure) {
        this.nextStructure = nextStructure;
    }
}