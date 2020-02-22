package com.company;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormTwo extends JFrame {
    // Заголовки столбцов
    private Object[] columnsHeader = new String[]{"Нижняя граница интегрирования", "Верхняя граница интегрирования", "Шаг интегрирования", "Результат"};
    // Данные для таблиц
    // Таблица
    private JTable tableMain;

    //
    private ArrayList<RecIntegral> dataTable = new ArrayList();

    private JTextField inputField1;
    private JTextField inputField2;
    private JTextField inputField3;

    public FormTwo() throws HeadlessException {
        // Наименование формы
        super("Задание - 1 (в.7)");
        // Размер формы
        this.setBounds(100, 100, 750, 250);
        // Призакрытие операции - закрывается форма
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание стандартной модели
        DefaultTableModel tableModel = new DefaultTableModel(null, columnsHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Разрешить редактирование только для столбцов: 0, 1, 2
                return column == 0 || column == 1 || column == 2;
            }
        };
        // Определение столбцов
        tableModel.setColumnIdentifiers(columnsHeader);
        // Создание таблицы на основании модели данных
        tableMain = new JTable(tableModel);

        // Создание кнопки добавления строки таблицы
        // Кнопки
        JButton buttonAdd = new JButton("Добавить");
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String a = inputField1.getText();
                String b = inputField2.getText();
                String n = inputField3.getText();

                RecIntegral recIntegral = new RecIntegral(a, b, n);
                dataTable.add(recIntegral);

                // Номер выделенной строки
                int idx = tableMain.getSelectedRow();
                // Вставка новой строки после выделенной
                inputField1.getText();
                tableModel.insertRow(idx + 1, new String[]{inputField1.getText(), inputField2.getText(), inputField3.getText(), ""});
            }
        });

        // Создание кнопки удаления строки таблицы
        JButton buttonDelete = new JButton("Удалить");
        buttonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                int idx = tableMain.getSelectedRow();
                // Удаление выделенной строки
                tableModel.removeRow(idx);
            }
        });

        // Создание кнопки расчета
        JButton buttonRun = new JButton("Вычислить");
        buttonRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    double a = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                    double b = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
                    int n = (Integer.parseInt(tableModel.getValueAt(i, 2).toString()));
                    double res = Integral.CalcIntegral(a, b, n);
                    tableModel.setValueAt(Double.toString(res), i, 3);
                }
                // JOptionPane.showMessageDialog(null, "Результат сохранен", "Внимание", JOptionPane.PLAIN_MESSAGE);
            }
        });

        // Создание кнопки очищения
        JButton buttonClean = new JButton("Очистить");
        buttonClean.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                while (tableModel.getRowCount() > 0) {
                    tableModel.removeRow(0);
                }
            }
        });

        // Создание кнопки заполнения
        JButton buttonFill = new JButton("Заполнить");
        buttonFill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Проверка данных для заполнения
                if (dataTable.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Нет данных для заполнения", "Внимание", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                // Очищение таблицы
                while (tableModel.getRowCount() > 0) {
                    tableModel.removeRow(0);
                }
                // Заполнение таблицы
                for (int i = 0; i < dataTable.size(); i++) {
                    String a = dataTable.get(i).getA();
                    String b = dataTable.get(i).getB();
                    String n = dataTable.get(i).getN();
                    tableModel.insertRow(i, new String[]{a, b, n, ""});
                }
            }
        });


        // Формирование интерфейса
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(tableMain));
        getContentPane().add(contents);

        // Создание меток для полей ввода
        JLabel labelField1 = new JLabel("Нижняя граница интегрирования:");
        labelField1.setPreferredSize(new Dimension(210, 0));
        JLabel labelField2 = new JLabel("Верхняя граница интегрирования:");
        labelField2.setPreferredSize(new Dimension(210, 0));
        JLabel labelField3 = new JLabel("Шаг интегрирования:");
        labelField3.setPreferredSize(new Dimension(210, 0));

        // Контейнер для строки ввода 1
        Box boxInputField1 = new Box(BoxLayout.X_AXIS);
        boxInputField1.add(labelField1);
        // Поля ввода
        inputField1 = new JTextField("", 5);
        boxInputField1.add(inputField1);
        // Контейнер для строки ввода 2
        Box boxInputField2 = new Box(BoxLayout.X_AXIS);
        boxInputField2.add(labelField2);
        inputField2 = new JTextField("", 5);
        boxInputField2.add(inputField2);
        // Контейнер для строки ввода 3
        Box boxInputField3 = new Box(BoxLayout.X_AXIS);
        boxInputField3.add(labelField3);
        inputField3 = new JTextField("", 5);
        boxInputField3.add(inputField3);
        // Контейнер для всех срок ввода
        Box boxInputFields = new Box(BoxLayout.Y_AXIS);
        boxInputFields.add(boxInputField1);
        boxInputFields.add(boxInputField2);
        boxInputFields.add(boxInputField3);
        // Контейнер для кнопок

        JPanel panelButtonsLeft = new JPanel();
        panelButtonsLeft.setLayout(new GridLayout(3, 1));
        panelButtonsLeft.add(buttonAdd);
        panelButtonsLeft.add(buttonDelete);
        panelButtonsLeft.add(buttonRun);

        JPanel panelButtonsRight = new JPanel();
        panelButtonsRight.setLayout(new GridLayout(2, 1));
        panelButtonsRight.add(buttonClean);
        panelButtonsRight.add(buttonFill);
        Box boxButtons = new Box(BoxLayout.X_AXIS);
        boxButtons.add(panelButtonsLeft);
        boxButtons.add(panelButtonsRight);
        // Контайнер для всех элементов управления
        Box boxControl = new Box(BoxLayout.X_AXIS);
        boxControl.add(boxInputFields);
        boxControl.add(boxButtons);


        // Заголовок для футера
        JLabel labelFooter = new JLabel("N * Sin(x2)");

        // Контейнер для нижней части приложения
        Box boxFooter = new Box(BoxLayout.Y_AXIS);
        boxFooter.add(labelFooter);
        boxFooter.add(boxControl);


        getContentPane().add(boxFooter, "South");
    }


}
