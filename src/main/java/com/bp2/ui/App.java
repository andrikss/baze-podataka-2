package com.bp2.ui;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1522/XEPDB1", "andrea", "andrea")) {
            // start UI
            new UIHandler().start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
