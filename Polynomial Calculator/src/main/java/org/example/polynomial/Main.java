package org.example.polynomial;

import org.example.controller.Controller;
import org.example.view.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        View view = new View("Polynomial operations");
        Controller controller = new Controller(view);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.pack();
        view.setVisible(true);
    }
}
