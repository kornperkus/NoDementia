package com.kornperkus.nodementia.Data;

public class Card {
    private int index;
    private int imgeResource;
    private int value;
    private boolean isOpen;
    private boolean isCorrect;

    public Card() { }

    public Card(int index, int imgeResource, int value, boolean isOpen, boolean isCorrect) {
        this.index = index;
        this.imgeResource = imgeResource;
        this.value = value;
        this.isOpen = isOpen;
        this.isCorrect = isCorrect;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setImgeResource(int imgeResource) {
        this.imgeResource = imgeResource;
    }

    public int getImgeResource() {
        return imgeResource;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
