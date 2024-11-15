package org.example.controller;

import org.example.polynomial.Operation;
import org.example.polynomial.OperationType;
import org.example.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private View view;

    public Controller(View view) {
        this.view = view;
        view.getAddButton().addActionListener(this);
        view.getSubtractButton().addActionListener(this);
        view.getMultiplyButton().addActionListener(this);
        view.getDivideButton().addActionListener(this);
        view.getDerivateButton().addActionListener(this);
        view.getIntegrateButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getAddButton()) {
            performOperation(OperationType.ADD);
        } else if (e.getSource() == view.getSubtractButton()) {
            performOperation(OperationType.SUBTRACT);
        }else if(e.getSource()==view.getMultiplyButton()){
            performOperation(OperationType.MULTIPLY);
        }else if(e.getSource()==view.getDivideButton()){
            performOperation(OperationType.DIVIDE);
        }else if(e.getSource()==view.getDerivateButton()){
            performOperation(OperationType.DERIVATE);
        }else if(e.getSource()==view.getIntegrateButton()){
            performOperation(OperationType.INTEGRATE);
        }
    }
    private void performOperation(OperationType operationType) {
        String p1 = view.getFirstInput().getText();
        String p2 = view.getSecondInput().getText();
        String result;
        switch (operationType) {
            case ADD:
                result = Operation.performOperation(OperationType.ADD, p1, p2);
                break;
            case SUBTRACT:
                result = Operation.performOperation(OperationType.SUBTRACT, p1, p2);
                break;
            case MULTIPLY:
                result = Operation.performOperation(OperationType.MULTIPLY, p1, p2);
                break;
            case DIVIDE:
                result = Operation.performOperation(OperationType.DIVIDE, p1, p2);
                break;
            case DERIVATE:
                result = Operation.performOperation(OperationType.DERIVATE,p1, p2);
                break;
            case INTEGRATE:
                result = Operation.performOperation(OperationType.INTEGRATE,p1,p2);
                break;
            default:
                result = "error!";
                break;
        }
        view.getResult().setText(result);
    }
}
