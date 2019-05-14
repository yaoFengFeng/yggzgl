package com.yggzgl;

import java.sql.Connection;

public class Test {
    public static void main(String[] args) {
        MySQLConnection open = new MySQLConnection();
        Connection conn = open.getConnection();
        System.out.println(conn);
    }
}
