package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class CursosColaborador {

	private JFrame frame;

    /**
     * Create the application.
     */
    public CursosColaborador() {
        initialize();
        this.frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(209, 250, 208));

        JPanel panel_abajo = new JPanel();
        panel_abajo.setOpaque(false);
        frame.getContentPane().add(panel_abajo, BorderLayout.SOUTH);

        // Panel superior con FlowLayout alineado a la derecha
        JPanel panel_arriba = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel_arriba.setOpaque(false);
        frame.getContentPane().add(panel_arriba, BorderLayout.NORTH);

        JButton botonCrearCurso = new JButton("Crear Curso");
        panel_arriba.add(botonCrearCurso);

        JPanel panel_medio = new JPanel();
        panel_medio.setOpaque(false);
        frame.getContentPane().add(panel_medio, BorderLayout.CENTER);

        frame.setBounds(100, 100, 480, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*DefaultListModel<Elemento> modeloListaCreado = new DefaultListModel<>();
        JList<Elemento> listaCreado = new JList<>(modeloListaCreado);
        JScrollPane scrollCreado = new JScrollPane(listaCreado);

        panel_medio.add(scrollCreado, BorderLayout.CENTER);
        frame.getContentPane().add(panel_medio, BorderLayout.CENTER);
        */
    }
}
