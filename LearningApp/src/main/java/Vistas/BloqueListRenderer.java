package Vistas;

import javax.swing.*;

import Modelo.BloqueContenido;

import java.awt.*;

public class BloqueListRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getListCellRendererComponent(JList<?> list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {

        if (value instanceof BloqueContenido bloque) {
            ElementoBloque panel = new ElementoBloque(bloque);
            if (isSelected) {
                panel.setBackground(new Color(182, 255, 149));
            } else {
                panel.setBackground(Color.WHITE);
            }
            return panel;
        }

        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}
