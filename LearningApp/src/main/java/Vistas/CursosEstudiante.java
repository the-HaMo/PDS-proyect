package Vistas;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Clases.Curso;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class CursosEstudiante {

    private JFrame frame;

    /**
     * Create the application.
     */
    public CursosEstudiante() {
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
        
        JPanel panel_arriba = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel_arriba.setOpaque(false);
        frame.getContentPane().add(panel_arriba, BorderLayout.NORTH);
        
        JButton botonImportar = new JButton("Importar Curso");
        panel_arriba.add(botonImportar);
        
        JPanel panel_medio = new JPanel();
        panel_medio.setOpaque(false);
        frame.getContentPane().add(panel_medio, BorderLayout.CENTER);
        frame.setBounds(100, 100, 500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		botonImportar.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
            int seleccion = fileChooser.showOpenDialog(frame); // Mostrar el diálogo

            if (seleccion == JFileChooser.APPROVE_OPTION) { // Si el usuario seleccionó un archivo
                File curso = fileChooser.getSelectedFile();
				if (curso.getName().endsWith(".json") || curso.getName().endsWith(".yaml")) {
					JOptionPane.showMessageDialog(frame, "El archivo seleccionado es: " + curso.getName());
				} else {
					JOptionPane.showMessageDialog(frame, "El archivo seleccionado no es correcto.");
				}
            }
		});

        DefaultListModel<Elemento> modeloCursos = new DefaultListModel<>();
        Curso c1= new Curso("Curso de Java", "Aprende Java desde cero");
        Curso c2 = new Curso("Curso de Python", "Aprende Python desde cero");
        Curso c3 = new Curso("Curso de C++", "Aprende C++ desde cero");
        Curso c4 = new Curso("Curso de JavaScript", "Aprende JavaScript desde cero");
        modeloCursos.addElement(new Elemento(c1));
        modeloCursos.addElement(new Elemento(c2));
        modeloCursos.addElement(new Elemento(c3));
        modeloCursos.addElement(new Elemento(c4));
        
        JList<Elemento> listaCursos = new JList<>(modeloCursos);
        listaCursos.setCellRenderer(new ElementoListRenderer());
        //listaCursos.setPreferredSize(new Dimension(260,300));
        JScrollPane scroll = new JScrollPane(listaCursos);
        scroll.setPreferredSize(new Dimension(260, 300));
        panel_medio.add(scroll, BorderLayout.CENTER);
        
        frame.getContentPane().add(panel_medio, BorderLayout.CENTER);
        
    }
}
