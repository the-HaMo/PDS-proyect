package Vistas;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Elemento extends JPanel {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String descripcion;

    public Elemento(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        initializeComponent();
    }

    private void initializeComponent() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Organizar en columnas (antes era X_AXIS)
        fixSize(this, 230, 75);
        this.setBackground(Color.WHITE);
        this.setBorder(new TitledBorder("Curso"));

        JLabel nombreLabel = new JLabel("Nombre: " + nombre);
        JLabel descripcionLabel = new JLabel("Descripción: " + descripcion);

        // 🔹 Agregamos los labels al panel
        this.add(nombreLabel);
        this.add(descripcionLabel);
    }

    private void fixSize(JComponent c, int x, int y) {
        c.setMinimumSize(new Dimension(x, y));
        c.setMaximumSize(new Dimension(x, y));
        c.setPreferredSize(new Dimension(x, y));
    }
}
