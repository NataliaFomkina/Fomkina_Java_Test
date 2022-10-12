package org.FomkinaNatalia;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringJoiner;

public class UserInterface extends JFrame {
    public JButton launchButton = new JButton("Пуск");
    public JButton chooseFileButton = new JButton("Выбрать файл");
    public JTextField input = new JTextField("", 2);
    public JTextField output = new JTextField("", 10);
    public JLabel inputLabel = new JLabel("Путь к csv-файлу c разделителем ',' : ");
    public JLabel outputLabel = new JLabel("Элементы, кратные 3: ");
    public JFileChooser fileOpener = new JFileChooser();
    public UserInterface() {
        super("Third task Fomkina");
        this.setBounds(20, 20, 500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        container.add(launchButton);
        container.add(inputLabel);
        container.add(input);
        container.add(chooseFileButton);
        container.add(outputLabel);
        container.add(output);
        fileOpener.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileOpener.addChoosableFileFilter(new FileNameExtensionFilter("Comma-separated values files", "csv"));
        fileOpener.setAcceptAllFileFilterUsed(false);
        launchButton.addActionListener(new EventListenerLaunchButton());
        chooseFileButton.addActionListener(new EventListenerChooseButton());
        container.add(launchButton);
    }

    public class EventListenerLaunchButton implements ActionListener {
        public void actionPerformed(ActionEvent d) {
            if (d.getSource() == launchButton) {
                String resultString = getResult(input.getText());
                if(!resultString.equals(null)) output.setText(resultString);
            }
        }
    }

    public class EventListenerChooseButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == chooseFileButton) {
                int returnVal = fileOpener.showDialog(null, "Выбрать csv-файл");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileOpener.getSelectedFile();
                    input.setText(file.getAbsolutePath());
                }
            }
        }
    }

    public String getResult(String inputString) {
        BufferedReader csvReader = null;
        StringJoiner resultOutput = new StringJoiner(", ");
        try {
            csvReader = new BufferedReader(new FileReader(inputString));
            String row;
            String[] data;
            ArrayList<Integer> resultArray = new ArrayList<>();
            while (((row = csvReader.readLine()) != null) && !row.equals("") ) {
                    data = row.split(",");
                    for (String arrayElementString : data) {
                            double arrayElement = Double.parseDouble(arrayElementString);
                            if (arrayElement % 3 == 0.0) {
                                resultArray.add((int) arrayElement);
                            }
                    }
            }
            if(resultArray.size()>0) {
                for (Integer elem : resultArray) {
                    resultOutput.add(elem.toString());
                }
            }else{
                resultOutput.add("Таких элементов не найдено");
            }
        } catch (FileNotFoundException ex) {
            resultOutput.add("Файл не найден");
        } catch (NumberFormatException ex) {
            resultOutput.add("В файле содержатся некорректные данные");
        } catch (IOException ex) {
            resultOutput.add("Ошибка обработки файла");
        } finally {
            try {
                csvReader.close();
            } catch (Exception n) {
                resultOutput.add("Ошибка закрытия файла");
            }
        }
        return resultOutput.toString();
    }
}
