package Modelo;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class ElementoListRenderer extends DefaultListCellRenderer {
    private static final long serialVersionUID = 1L;

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, 
            int index, boolean isSelected, boolean cellHasFocus) {
        
        System.out.println("[DEBUG] Valor recibido en renderizador: " + value);
        System.out.println("[DEBUG] Tipo del valor: " + (value != null ? value.getClass() : "null"));
        
        if (value instanceof Curso) {
            Curso curso = (Curso) value;
            System.out.println("[DEBUG] Curso válido: " + curso.getNombre());
            Elemento ele = new Elemento(curso);
            ele.setBackground(isSelected ? Color.GRAY : list.getBackground());
            return ele;
        } else {
            System.err.println("[ERROR] El valor no es un Curso. Se mostrará un componente por defecto.");
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }
}
