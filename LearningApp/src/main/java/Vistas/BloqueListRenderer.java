package Vistas;

import javax.swing.*;
import java.awt.*;

public class BloqueListRenderer implements ListCellRenderer<ElementoBloque> {

    @Override
    public Component getListCellRendererComponent(JList<? extends ElementoBloque> list,
                                                  ElementoBloque value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        // Selección visual
        if (isSelected) {
            value.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        } else {
            value.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        }

        return value;
    }
}
