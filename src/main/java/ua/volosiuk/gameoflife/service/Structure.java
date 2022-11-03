package ua.volosiuk.gameoflife.service;

import java.util.List;

public class Structure {

    private int edgeLength;
    private List<Element> elements;

    public Structure(){}
    public Structure(int edgeLength) {
        this.edgeLength = edgeLength;
    }



    public int getEdgeLength() {
        return edgeLength;
    }

    public void setEdgeLength(int edgeLength) {
        this.edgeLength = edgeLength;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
