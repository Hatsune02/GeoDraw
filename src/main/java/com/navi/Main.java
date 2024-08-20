package com.navi;
import com.navi.UI.DashBoard;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;

public class Main {
    public static void main(String[] args) {
        FlatMaterialDarkerIJTheme.setup();
        DashBoard dashboard = new DashBoard();
        dashboard.setVisible(true);
    }
}