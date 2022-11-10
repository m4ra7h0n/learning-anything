package com.xjjlearning.database.db2;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Swing extends JFrame {
    public static void main(String[] args) {
        new Swing();
    }
    public Swing() {
        super();
        this.setBounds(100, 100, 200, 200);
        JPanel jPanel = new JPanel();
        jPanel.setSize(100, 100);
        jPanel.setBackground(Color.blue);
        this.getContentPane().setLayout(null);
        this.getContentPane().add(jPanel);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu 1");
        MenuItem menuItem = new MenuItem("item 1");
        MenuItem menuItem2 = new MenuItem("item 2");
        MenuItem menuItem3 = new MenuItem("item 3");
        menu.add(menuItem);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menuBar.add(menu);

        this.setMenuBar(menuBar);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
