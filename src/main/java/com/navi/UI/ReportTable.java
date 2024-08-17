package com.navi.UI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ReportTable extends JFrame{
    private DefaultTableModel model;
    private JTable table;

    public ReportTable() {
        // Configuración básica del JFrame
        setTitle("Report Table");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo el frame, no toda la aplicación

        // Crear el modelo de la tabla
        model = new DefaultTableModel();

        // Crear el JTable con el modelo
        table = new JTable(model);
        table.setBackground(new Color(12, 20, 45));  // Fondo de la tabla
        table.setForeground(new Color(134, 206, 203)); // Texto de la tabla
        table.setSelectionBackground(new Color(21, 70, 90)); // Fondo de selección
        table.setSelectionForeground(Color.WHITE); // Texto de selección
        table.setFont(new Font("Arial", Font.PLAIN, 18)); // Fuente del texto
        table.setRowHeight(35);

        // Encabezado de la tabla
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(21, 70, 90)); // Fondo del encabezado
        header.setForeground(Color.WHITE); // Texto del encabezado
        header.setFont(new Font("Arial", Font.BOLD, 20)); // Fuente del encabezado

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane.getViewport().setBackground(new Color(12, 20, 45));

        // Configurar el layout y agregar el JScrollPane al JFrame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addRow(Object[] rowData) {
        model.addRow(rowData);
    }

    public void addColumn(String column) {
        model.addColumn(column);
    }
}
