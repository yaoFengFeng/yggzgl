package com;

import dao.MsgDao;

public class Receiver implements Observer{
    private String id;
    public Receiver(String id){
        this.id = id;
    }
    @Override
    public void update(String message,String title) {
        MsgDao msgDao = new MsgDao();
        msgDao.insert(this.id,message,title);
    }
}
