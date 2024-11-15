package org.example.view;
import org.example.controller.Controller;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.Serial;

public class View extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints c = new GridBagConstraints();
    private JButton addButton = new JButton("Add");
    private JButton subtractButton = new JButton("Subtract");
    private JButton multiplyButton = new JButton("Multiply");
    private JButton divideButton = new JButton("Divide");
    private JButton derivateButton = new JButton("Derivate");
    private JButton integrateButton = new JButton("Integrate");
    private JTextField firstInput = new JTextField(20);
    private JTextField secondInput = new JTextField(20);
    private JLabel resultLabel = new JLabel("Result: ");
    private JLabel result = new JLabel();
    Controller controller = new Controller(this);

    public View(String name) {
        super(name);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(firstInput, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(secondInput, c);

        c.gridy = 2;
        panel.add(addButton, c);
        addButton.addActionListener(controller);

        c.gridy = 3;
        panel.add(subtractButton, c);
        subtractButton.addActionListener(controller);

        c.gridy = 4;
        panel.add(multiplyButton, c);
        multiplyButton.addActionListener(controller);

        c.gridy = 5;
        panel.add(divideButton, c);
        divideButton.addActionListener(controller);

        c.gridy = 6;
        panel.add(derivateButton, c);
        derivateButton.addActionListener(controller);

        c.gridy = 7;
        panel.add(integrateButton, c);
        integrateButton.addActionListener(controller);

        c.gridy = 8;
        c.anchor = GridBagConstraints.WEST;
        panel.add(resultLabel, c);

        c.gridx = 1;
        panel.add(result, c);

        controller = new Controller(this);

        this.add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getSubtractButton() {
        return subtractButton;
    }

    public JButton getMultiplyButton() {
        return multiplyButton;
    }

    public JButton getDivideButton() {
        return divideButton;
    }

    public JButton getDerivateButton() {
        return derivateButton;
    }

    public JButton getIntegrateButton() {
        return integrateButton;
    }

    public JTextField getFirstInput() {
        return firstInput;
    }

    public JTextField getSecondInput() {
        return secondInput;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public JLabel getResult() {
        return result;
    }

    public static void main(String args[]) {
        JFrame frame = new View("Polynomial Operations");
        frame.setVisible(true);
    }
}
