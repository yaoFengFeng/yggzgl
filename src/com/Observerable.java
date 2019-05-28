package com;

public interface Observerable {
    public void addObserver(Observer o);
    public void delObserver(Observer o);
    public void notifyObserver();
}
