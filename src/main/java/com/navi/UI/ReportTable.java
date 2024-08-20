package com.navi.UI;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
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

    public void addScroll(){
        for(int i = 0; i < table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(new ScrollableCellRenderer());
        }
    }
}

class ScrollableCellRenderer extends JTextArea implements TableCellRenderer {

    public ScrollableCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
        setFont(new Font("Arial", Font.PLAIN, 18));
        setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());

        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }

        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);

        if (table.getRowHeight(row) != getPreferredSize().height) {
            table.setRowHeight(row, getPreferredSize().height);
        }

        return this;
    }
}
