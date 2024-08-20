package com.navi.UI;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import com.navi.backend.parameters.*;
import com.navi.backend.parser_lexer.*;
import com.navi.backend.parser_lexer.errors_lp.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import java.io.*;

public class DashBoard extends javax.swing.JFrame {

    public DashBoard() {
        initComponents();
        initStyles();
    }

    private void initStyles(){
        setTitle("GeoDraw");

        NumeroLinea numberLine = new NumeroLinea(textPane);
        scrollTextPane.setRowHeaderView(numberLine);

        styleMikuButton(compileButton);
        styleMikuButton(moveButton);
        styleMikuTextPane(textPane);
        styleMikuLabel(lineLabel);
        styleMikuLabel(columnLabel);
        styleMikuMenu(file);
        styleMikuMenu(canvasMenu);
        styleMikuMenu(reportsMenu);
        canvas.clearCanvas();
    }

    private void styleMikuMenu(JMenu menu){
        // Colores y fuentes inspirados en Hatsune Miku
        Color mikuBaseColor = new Color(57, 197, 187); // Color base de Miku
        Font menuFont = new Font("Arial", Font.BOLD, 14); // Fuente para los menús y sus ítems

        // Configurar el menú
        menu.setForeground(mikuBaseColor); // Color del texto del menú
        menu.setFont(menuFont); // Fuente del menú

        styleMenuItems(menu);
    }
    private void styleMenuItems(JMenu menu) {
        Color mikuBaseColor = new Color(57, 197, 187); // Color base de Miku
        Color menuBackgroundColor = new Color(40, 56, 56); // Fondo del menú
        Color menuItemHoverColor = new Color(47, 156, 149); // Color de hover
        Font menuFont = new Font("Arial", Font.BOLD, 14); // Fuente para los menús y sus ítems

        for (int i = 0; i < menu.getMenuComponentCount(); i++) {
            Component component = menu.getMenuComponent(i);
            if (component instanceof JMenuItem) {
                JMenuItem item = (JMenuItem) component;
                item.setBackground(menuBackgroundColor); // Fondo de ítem
                item.setForeground(mikuBaseColor); // Color del texto
                item.setFont(menuFont); // Fuente del ítem
                item.setOpaque(true); // Necesario para aplicar el fondo
                // Agregar efecto hover
                item.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        item.setBackground(menuItemHoverColor); // Color de fondo al pasar el ratón
                    }

                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        item.setBackground(menuBackgroundColor); // Color de fondo normal
                    }
                });
            }
            if (component instanceof JMenu) {
                // Llamada recursiva para encontrar ítems en submenús
                styleMenuItems((JMenu) component);
            }
        }
    }
    private void styleMikuLabel(JLabel label){
        Color mikuBaseColor = new Color(57, 197, 187); // #39c5bb

        label.setForeground(mikuBaseColor);
        label.setFont(new Font("Arial", Font.BOLD, 16));
    }
    private void styleMikuTextPane(JTextPane textPane){
        Color mikuBaseColor = new Color(57, 197, 187); // #39c5bb
        Color customBorderColor = new Color(112, 169, 166); // Color turquesa claro, similar al de Hatsune Miku
        int borderThickness = 2;

        textPane.setFont(new Font("Arial", Font.BOLD, 16));
        textPane.setForeground(mikuBaseColor);

        // Aplicar el borde personalizado
        textPane.setBorder(new LineBorder(customBorderColor, borderThickness));
        scrollTextPane.setBorder(new LineBorder(customBorderColor, 1));
        addCaretToTextPane();
    }
    private void styleButton(JButton button){
        // Definir colores oscuros
        Color darkBackground = new Color(60, 63, 65);
        Color darkForeground = new Color(169, 183, 198); // Color hueso oscuro
        Color darkBorder = new Color(87, 90, 92);
        Color hoverBackground = new Color(75, 78, 80);

        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(darkForeground);
        button.setBackground(darkBackground);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(darkBorder, 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto al pasar el mouse sobre el botón
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverBackground); // Color de fondo al pasar el ratón
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(darkBackground);  // Color de fondo normal
            }
        });
    }
    private void styleMikuButton(JButton button){
        // Colores inspirados en Hatsune Miku
        Color mikuBaseColor = new Color(57, 197, 187); // #39c5bb
        Color backgroundColor = new Color(28, 28, 28); // #1c1c1c
        Color textColor = new Color(224, 224, 224); // #e0e0e0
        Color borderColor = new Color(39, 111, 107); // #276f6b
        Color hoverBackground = new Color(47, 156, 149); // #2f9c95
        Color buttonBackground = new Color(40, 56, 56); // #283838
        Color buttonText = new Color(161, 244, 240); // #a1f4f0

        // Estilizar el botón
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Cambiar la fuente
        button.setForeground(buttonText);                 // Texto inspirado en Miku
        button.setBackground(buttonBackground);           // Fondo oscuro derivado del color base
        button.setFocusPainted(false);                    // Quitar borde de enfoque
        button.setBorder(BorderFactory.createLineBorder(borderColor, 2)); // Borde inspirado en Miku
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar el cursor al pasar por encima

        // Efecto al pasar el mouse sobre el botón
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverBackground); // Color de fondo al pasar el ratón
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(buttonBackground);  // Color de fondo normal
            }
        });

    }
    private void styleLukaButton(JButton button){
        // Colores inspirados en Megurine Luka
        Color lukaBaseColor = new Color(232, 154, 199); // #e89ac7
        Color backgroundColor = new Color(26, 26, 26); // #1a1a1a
        Color textColor = new Color(240, 240, 240); // #f0f0f0
        Color borderColor = new Color(192, 163, 110); // #c0a36e
        Color hoverBackground = new Color(186, 126, 160); // #ba7ea0
        Color buttonBackground = new Color(52, 47, 46); // #342f2e
        Color buttonText = new Color(255, 179, 222); // #ffb3de

        // Estilizar el botón
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Cambiar la fuente
        button.setForeground(buttonText);                 // Texto inspirado en Luka
        button.setBackground(buttonBackground);           // Fondo oscuro derivado del color base
        button.setFocusPainted(false);                    // Quitar borde de enfoque
        button.setBorder(BorderFactory.createLineBorder(borderColor, 2)); // Borde dorado inspirado en Luka
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar el cursor al pasar por encima

        // Efecto al pasar el mouse sobre el botón
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverBackground); // Color de fondo al pasar el ratón
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(buttonBackground);  // Color de fondo normal
            }
        });
    }

    private void addCaretToTextPane(){
        textPane.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try {
                    int caretPosition = textPane.getCaretPosition();
                    int line = getLineAtCaret(textPane, caretPosition);
                    int column = getColumnAtCaret(textPane, caretPosition);
                    lineLabel.setText("Línea: " + line);
                    columnLabel.setText("Columna: " + column);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private int getLineAtCaret(JTextPane textPane, int caretPosition) throws BadLocationException {
        Element root = textPane.getDocument().getDefaultRootElement();
        return root.getElementIndex(caretPosition) + 1;
    }
    private int getColumnAtCaret(JTextPane textPane, int caretPosition) throws BadLocationException {
        Element root = textPane.getDocument().getDefaultRootElement();
        int line = root.getElementIndex(caretPosition);
        int startOfLine = root.getElement(line).getStartOffset();
        return caretPosition - startOfLine + 1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        background = new javax.swing.JPanel();
        panelScroll = new javax.swing.JPanel();
        scrollTextPane = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        compileButton = new javax.swing.JButton();
        moveButton = new javax.swing.JButton();
        lineLabel = new javax.swing.JLabel();
        columnLabel = new javax.swing.JLabel();
        scrollCanvas = new javax.swing.JScrollPane();
        canvas = new Canvas();
        menu = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        openFile = new javax.swing.JMenuItem();
        saveFile = new javax.swing.JMenuItem();
        newFile = new javax.swing.JMenuItem();
        exportFigures = new javax.swing.JMenu();
        exportPdf = new javax.swing.JMenuItem();
        exportPng = new javax.swing.JMenuItem();
        canvasMenu = new javax.swing.JMenu();
        lightTheme = new javax.swing.JMenuItem();
        darkTheme = new javax.swing.JMenuItem();
        cleanCanvas = new javax.swing.JMenuItem();
        reportsMenu = new javax.swing.JMenu();
        operationReportI = new javax.swing.JMenuItem();
        colorsReportI = new javax.swing.JMenuItem();
        objectsReportI = new javax.swing.JMenuItem();
        animationsReportI = new javax.swing.JMenuItem();
        errorsReportI = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        scrollTextPane.setViewportView(textPane);

        compileButton.setText("Compilar");
        compileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileButtonActionPerformed(evt);
            }
        });

        moveButton.setText("Ejecutar");
        moveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveButtonActionPerformed(evt);
            }
        });

        lineLabel.setText("Línea: ");

        columnLabel.setText("Columna:");

        javax.swing.GroupLayout panelScrollLayout = new javax.swing.GroupLayout(panelScroll);
        panelScroll.setLayout(panelScrollLayout);
        panelScrollLayout.setHorizontalGroup(
                panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelScrollLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollTextPane)
                                        .addGroup(panelScrollLayout.createSequentialGroup()
                                                .addComponent(compileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(columnLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(moveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        panelScrollLayout.setVerticalGroup(
                panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelScrollLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(compileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(moveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lineLabel)
                                        .addComponent(columnLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollTextPane, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout canvasLayout = new javax.swing.GroupLayout(canvas);
        canvas.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(
                canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 925, Short.MAX_VALUE)
        );
        canvasLayout.setVerticalGroup(
                canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 399, Short.MAX_VALUE)
        );

        scrollCanvas.setViewportView(canvas);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
                backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrollCanvas)
                                        .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        backgroundLayout.setVerticalGroup(
                backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollCanvas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        file.setText("  Archivo  ");

        openFile.setText("Abrir archivo... ");
        openFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        file.add(openFile);

        saveFile.setText("Guardar");
        saveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileActionPerformed(evt);
            }
        });
        file.add(saveFile);

        newFile.setText("Nuevo archivo");
        newFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileActionPerformed(evt);
            }
        });
        file.add(newFile);

        exportFigures.setText("Exportar");

        exportPdf.setText("PDF...");
        exportPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportPdfActionPerformed(evt);
            }
        });
        exportFigures.add(exportPdf);

        exportPng.setText("PNG...");
        exportPng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportPngActionPerformed(evt);
            }
        });
        exportFigures.add(exportPng);

        file.add(exportFigures);

        menu.add(file);

        canvasMenu.setText("Lienzo");

        lightTheme.setText("Claro");
        lightTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lightThemeActionPerformed(evt);
            }
        });
        canvasMenu.add(lightTheme);

        darkTheme.setText("Oscuro");
        darkTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darkThemeActionPerformed(evt);
            }
        });
        canvasMenu.add(darkTheme);

        cleanCanvas.setText("Limpiar");
        cleanCanvas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanCanvasActionPerformed(evt);
            }
        });
        canvasMenu.add(cleanCanvas);

        menu.add(canvasMenu);

        reportsMenu.setText("  Reportes  ");

        operationReportI.setText("Operaciones  ");
        operationReportI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operationReportIActionPerformed(evt);
            }
        });
        reportsMenu.add(operationReportI);

        colorsReportI.setText("Colores");
        colorsReportI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorsReportIActionPerformed(evt);
            }
        });
        reportsMenu.add(colorsReportI);

        objectsReportI.setText("Objetos");
        objectsReportI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                objectsReportIActionPerformed(evt);
            }
        });
        reportsMenu.add(objectsReportI);

        animationsReportI.setText("Animaciones");
        animationsReportI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animationsReportIActionPerformed(evt);
            }
        });
        reportsMenu.add(animationsReportI);

        errorsReportI.setText("Errores");
        errorsReportI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                errorsReportIActionPerformed(evt);
            }
        });
        reportsMenu.add(errorsReportI);

        menu.add(reportsMenu);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void compileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(!textPane.getText().isEmpty()){
            lexer = new GeoLexer(new StringReader(textPane.getText()));
            parser = new GeoParser(lexer);
            try {
                ErrorsLP.clearErrors();
                parser.parse();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(this,"Se detectaron errores al momento de compilar","Error",JOptionPane.ERROR_MESSAGE);
            }

            if(ErrorsLP.getErrors().isEmpty()){
                JOptionPane.showMessageDialog(this,"La compilación ha sido exitosa","Listo!",JOptionPane.INFORMATION_MESSAGE);
                //parser.getParameters().forEach(param -> System.out.println(param.toString()));
                if(!parser.getParameters().isEmpty()) canvas.setParameters(parser.getParameters());
            }
            else{
                JOptionPane.showMessageDialog(this,"Se detectaron errores al momento de compilar","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void moveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        canvas.initMotions();
    }

    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {
        textPane.setText(loadFile());
    }

    private String loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            currentFile = selectedFile;
            return content.toString();
        }

        return "";
    }

    private void saveFileActionPerformed(java.awt.event.ActionEvent evt) {
        if (currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(textPane.getText());
                    JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (currentFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                writer.write(textPane.getText());
                JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private File currentFile;
    private void newFileActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                writer.write(""); // Escribe un archivo vacío
                textPane.setText(""); // Limpia el JTextPane
                JOptionPane.showMessageDialog(this, "Archivo creado exitosamente");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al crear el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void operationReportIActionPerformed(java.awt.event.ActionEvent evt) {
        if(parser.getOperations().isEmpty()){
            JOptionPane.showMessageDialog(this,"No se encontro ninguna operacion.","No hay datos",JOptionPane.ERROR_MESSAGE);
        }
        else if(!ErrorsLP.getErrors().isEmpty()){
            JOptionPane.showMessageDialog(this,"Existen errores en el texto, revise que este todo correcto","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            ReportTable table = new ReportTable();
            String[] columns = {"Operador", "Línea","Columna","Ocurrencia"};
            initReport(table,"Reporte de ocurrencias de operadores matemáticos",columns);
            countOperations(table);
            table.addScroll();
        }
    }
    private void countOperations(ReportTable table) {
        for(Operation op: parser.getOperations()){
            table.addRow(new Object[]{op.getSign(), op.getLine(), op.getColumn(), op.getEx()});
        }
    }

    private void colorsReportIActionPerformed(java.awt.event.ActionEvent evt) {
        if(parser.getParameters().isEmpty()){
            JOptionPane.showMessageDialog(this,"No se encontro ningun color.","No hay datos",JOptionPane.ERROR_MESSAGE);
        }
        else if(!ErrorsLP.getErrors().isEmpty()){
            JOptionPane.showMessageDialog(this,"Existen errores en el texto, revise que este todo correcto","Error",JOptionPane.ERROR_MESSAGE);
        }
        else {
            ReportTable table = new ReportTable();
            String[] columns = {"Color", "Cantidad de uso"};
            initReport(table, "Reporte de colores usados", columns);
            countForColors(table);
            table.addScroll();
        }
    }
    private void countForColors(ReportTable table){
        int countBlue = 0;
        int countRed = 0;
        int countYellow = 0;
        int countGreen = 0;
        int countSky = 0;
        int countCyan = 0;
        int countBlack = 0;
        int countPink = 0;
        int countPurple = 0;

        for(Parameter p: parser.getParameters()){
            switch (p.getColor()){
                case "azul":
                    countBlue++;
                    break;
                case "rojo":
                    countRed++;
                    break;
                case "amarillo":
                    countYellow++;
                    break;
                case "verde":
                    countGreen++;
                    break;
                case "celeste":
                    countSky++;
                    break;
                case "cyan":
                    countCyan++;
                    break;
                case "negro":
                    countBlack++;
                    break;
                case "rosado":
                    countPink++;
                    break;
                case "morado":
                    countPurple++;
                    break;
                default:
                    break;
            }
        }

        table.addRow(new Object[]{"Azul", countBlue});
        table.addRow(new Object[]{"Rojo", countRed});
        table.addRow(new Object[]{"Amarillo", countYellow});
        table.addRow(new Object[]{"Verde", countGreen});
        table.addRow(new Object[]{"Celeste", countSky});
        table.addRow(new Object[]{"Cyan", countCyan});
        table.addRow(new Object[]{"Negro", countBlack});
        table.addRow(new Object[]{"Rosado", countPink});
        table.addRow(new Object[]{"Morado", countPurple});


    }

    private void objectsReportIActionPerformed(java.awt.event.ActionEvent evt) {
        if(parser.getParameters().isEmpty()){
            JOptionPane.showMessageDialog(this,"No se encontro ningun objeto.","No hay datos",JOptionPane.ERROR_MESSAGE);
        }
        else if(!ErrorsLP.getErrors().isEmpty()){
            JOptionPane.showMessageDialog(this,"Existen errores en el texto, revise que este todo correcto","Error",JOptionPane.ERROR_MESSAGE);
        }
        else {
            ReportTable table = new ReportTable();
            String[] columns = {"Objeto", "Cantidad de uso"};
            initReport(table, "Reporte de objetos usados", columns);
            countObjects(table);
            table.addScroll();
        }
    }
    private void countObjects(ReportTable table){
        int countCircle = 0;
        int countSquare = 0;
        int countRectangle = 0;
        int countLine = 0;
        int countPolygon = 0;

        for(Parameter p: parser.getParameters()){
            switch (p.getType()){
                case Parameter.CIRCLE:
                    countCircle++;
                    break;
                case Parameter.SQUARE:
                    countSquare++;
                    break;
                case Parameter.RECTANGLE:
                    countRectangle++;
                    break;
                case Parameter.LINE:
                    countLine++;
                    break;
                case Parameter.POLYGON:
                    countPolygon++;
                    break;
                default:
                    break;
            }
        }

        table.addRow(new Object[]{"Círculo", countCircle});
        table.addRow(new Object[]{"Cuadrado", countSquare});
        table.addRow(new Object[]{"Rectángulo", countRectangle});
        table.addRow(new Object[]{"Línea", countLine});
        table.addRow(new Object[]{"Poligono", countPolygon});
    }

    private void animationsReportIActionPerformed(java.awt.event.ActionEvent evt) {
        if(parser.getParameters().isEmpty()){
            JOptionPane.showMessageDialog(this,"No se encontro ninguna animación.","No hay datos",JOptionPane.ERROR_MESSAGE);
        }
        else if(!ErrorsLP.getErrors().isEmpty()){
            JOptionPane.showMessageDialog(this,"Existen errores en el texto, revise que este todo correcto","Error",JOptionPane.ERROR_MESSAGE);
        }
        else {
            ReportTable table = new ReportTable();
            String[] columns = {"Animación", "Cantidad de uso"};
            initReport(table, "Reporte de animaciones usados", columns);
            countAnimation(table);
            table.addScroll();
        }
    }
    private void countAnimation(ReportTable table){
        int countLine = 0;
        int countCurve = 0;

        for(Parameter p: parser.getParameters()){
            switch (p.getType()){
                case Parameter.ANIMATE:

                    if(p.getId().equals("line"))countLine++;
                    else if(p.getId().equals("curve")) countCurve++;

                    break;
                default:
                    break;
            }
        }

        table.addRow(new Object[]{"Línea", countLine});
        table.addRow(new Object[]{"Curva", countCurve});
    }

    private void errorsReportIActionPerformed(java.awt.event.ActionEvent evt) {
        if(ErrorsLP.getErrors().isEmpty()){
            JOptionPane.showMessageDialog(this,"No hay ningun error :)","Error",JOptionPane.ERROR_MESSAGE);
        }
        else {
            ReportTable table = new ReportTable();
            String[] columns = {"Lexema", "Línea", "Columna", "Tipo", "Descripción"};
            initReport(table, "Reporte de Errores", columns);
            addErrorsReport(table);
            table.addScroll();
        }
    }
    private void addErrorsReport(ReportTable table){
        for(TError e: ErrorsLP.getErrors()){
            table.addRow(new Object[]{e.getLexeme(), e.getLine(), e.getColumn(), e.getType(), e.getDescription()});
        }
    }

    private void initReport(ReportTable table, String menu, String[] columns){
        table.setTitle(menu);

        for (String column : columns) {
            table.addColumn(column);
        }

        table.setVisible(true);
    }

    private void exportPdfActionPerformed(java.awt.event.ActionEvent evt) {
        canvas.exportToPDF();
    }

    private void exportPngActionPerformed(java.awt.event.ActionEvent evt) {
        canvas.exportToPNG();
    }

    private void cleanCanvasActionPerformed(java.awt.event.ActionEvent evt) {
        canvas.clearCanvas();
    }

    private void darkThemeActionPerformed(java.awt.event.ActionEvent evt) {
        canvas.setBackground(new Color(33,33,33));
    }

    private void lightThemeActionPerformed(java.awt.event.ActionEvent evt) {
        canvas.setBackground(new Color(196, 199, 211));
    }

    public static void main(String args[]) {
        FlatMaterialDarkerIJTheme.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashBoard().setVisible(true);
            }
        });
    }

    private GeoLexer lexer;
    private GeoParser parser;
    // Variables declaration
    private javax.swing.JMenuItem animationsReportI;
    private javax.swing.JPanel background;
    private Canvas canvas;
    private javax.swing.JMenu canvasMenu;
    private javax.swing.JMenuItem cleanCanvas;
    private javax.swing.JMenuItem colorsReportI;
    private javax.swing.JLabel columnLabel;
    private javax.swing.JButton compileButton;
    private javax.swing.JMenuItem darkTheme;
    private javax.swing.JMenuItem errorsReportI;
    private javax.swing.JMenu exportFigures;
    private javax.swing.JMenuItem exportPdf;
    private javax.swing.JMenuItem exportPng;
    private javax.swing.JMenu file;
    private javax.swing.JMenuItem lightTheme;
    private javax.swing.JLabel lineLabel;
    private javax.swing.JMenuBar menu;
    private javax.swing.JButton moveButton;
    private javax.swing.JMenuItem newFile;
    private javax.swing.JMenuItem objectsReportI;
    private javax.swing.JMenuItem openFile;
    private javax.swing.JMenuItem operationReportI;
    private javax.swing.JPanel panelScroll;
    private javax.swing.JMenu reportsMenu;
    private javax.swing.JMenuItem saveFile;
    private javax.swing.JScrollPane scrollCanvas;
    private javax.swing.JScrollPane scrollTextPane;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables
}
