package org.NataliaFomkina;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class UserInterface extends JFrame {
    public JButton launchButton = new JButton("Пуск");
    public JTextField input = new JTextField("", 2);
    public JTextField output = new JTextField("", 2);
    public JLabel inputLabel = new JLabel("Ввод");
    public JLabel outputLabel = new JLabel("Вывод");

    public UserInterface() {
        super("First task Fomkina");
        this.setBounds(20, 20, 500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        container.add(launchButton);
        container.add(inputLabel);
        container.add(input);
        container.add(outputLabel);
        container.add(output);

        launchButton.addActionListener(new ButtonEventListener());
        container.add(launchButton);
    }

    public class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == launchButton) {
                try {
                    output.setText(getResult(Double.parseDouble(input.getText())));
                } catch (NumberFormatException ex) {
                    output.setText("Некорректный ввод");
                }
                input.setText(null);
            }

        }
    }
    public static String getResult(double number) {
        String str = "";
        if (number > 7) {
            str = "Привет";
        }
        return str;
    }
}