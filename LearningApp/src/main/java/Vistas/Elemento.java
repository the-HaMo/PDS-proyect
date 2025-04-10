package Vistas;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import Modelo.Curso;

public class Elemento extends JPanel {

    private static final long serialVersionUID = 1L;
    private Curso curso;

    public Elemento(Curso curso) {
    	this.curso = curso;
        initializeComponent();
    }

    private void initializeComponent() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Organizar en columnas (antes era X_AXIS)
        fixSize(this, 230, 75);
        this.setBackground(Color.WHITE);
        this.setBorder(new TitledBorder("Curso"));

        JLabel nombreLabel = new JLabel("Nombre: " + curso.getNombre());
        JLabel descripcionLabel = new JLabel("Descripción: " + curso.getDescripcion());

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
