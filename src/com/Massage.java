package com;

import java.util.ArrayList;
import java.util.List;

public class Massage implements Observerable{
    private List<Observer> list;
    private String message;
    private String title;

    public Massage(){
        list = new ArrayList<Observer>();
    }

    @Override
    public void addObserver(Observer o) {
        this.list.add(o);
    }

    @Override
    public void delObserver(Observer o) {
        if(!list.isEmpty())
            list.remove(o);
    }

    @Override
    public void notifyObserver() {
        for(int i = 0; i < list.size(); i++) {
            Observer receiver = list.get(i);
            receiver.update(message,title);
        }
    }

    public void setInfomation(String s,String title) {
        this.message = s;
        this.title = title;
        notifyObserver();
    }
}
