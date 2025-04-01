package Vistas;

import java.awt.*;
import javax.swing.*;

public class CursosColaborador {

    private JFrame frame;
    private DefaultListModel<String> modeloGeneral, modeloPrivado;
    private JList<String> listaGeneral, listaPrivado;

    public CursosColaborador() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Cursos Colaborador");
        frame.setSize(550, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // --- Panel del título ---
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(0, 255, 0));
        panelTitulo.setPreferredSize(new Dimension(10, 50));
        frame.getContentPane().add(panelTitulo, BorderLayout.NORTH);

        JLabel lblApp = new JLabel("LearningApp", SwingConstants.CENTER);
        lblApp.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelTitulo.add(lblApp, BorderLayout.CENTER);

        JLabel lblUsuario = new JLabel("Hola --- !");
        lblUsuario.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Espaciado izquierdo
        panelTitulo.add(lblUsuario, BorderLayout.SOUTH);

        // --- Panel de Bibliotecas ---
        JPanel panelBibliotecas = new JPanel(new BorderLayout());
        panelBibliotecas.setBackground(new Color(128, 255, 128));
        frame.getContentPane().add(panelBibliotecas, BorderLayout.CENTER);

        // --- Biblioteca General ---
        modeloGeneral = new DefaultListModel<>();
        listaGeneral = new JList<>(modeloGeneral);
        JPanel panelGeneral = crearBibliotecaPanel("CursosOnline", listaGeneral, "Compartir Curso", this::compartirCurso);
        panelBibliotecas.add(panelGeneral, BorderLayout.WEST);

        // --- Biblioteca Privada ---
        modeloPrivado = new DefaultListModel<>();
        listaPrivado = new JList<>(modeloPrivado);
        JPanel panelPrivado = crearBibliotecaPanel("MisCursos", listaPrivado, "Importar Curso", this::importarCurso);
        panelBibliotecas.add(panelPrivado, BorderLayout.EAST);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setPreferredSize(new Dimension(0, 10));
        panelBibliotecas.add(verticalStrut, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 255, 128));
        panel.setPreferredSize(new Dimension(10, 30));
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(128, 255, 128));
        frame.getContentPane().add(panel_1, BorderLayout.WEST);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(128, 255, 128));
        frame.getContentPane().add(panel_2, BorderLayout.EAST);
    }

    private JPanel crearBibliotecaPanel(String titulo, JList<String> lista, String botonTexto, Runnable accion) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3), titulo));
        panel.setPreferredSize(new Dimension(250, 300));
        panel.setBackground(Color.WHITE); // Fondo blanco

        // Lista dentro de un scrollPane con bordes suaves
        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3)); 
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botón con mejor estilo
        JButton boton = new JButton(botonTexto);
        boton.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        boton.setForeground(Color.WHITE);
        boton.setBackground(Color.decode("#4CAF50")); // Verde moderno
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15)); // Mejor espaciado
        boton.addActionListener(e -> accion.run());

        // Contenedor del botón para mayor separación
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(Color.WHITE);
        panelBoton.add(boton);

        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }


    private void importarCurso() {
        String curso = JOptionPane.showInputDialog(frame, "Ingrese el nombre del curso:");

        if (curso != null && !curso.trim().isEmpty()) {
            if (!modeloPrivado.contains(curso)) {
                modeloPrivado.addElement(curso);
            } else {
                JOptionPane.showMessageDialog(frame, "Este curso ya ha sido importado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void compartirCurso() {
        String curso = listaPrivado.getSelectedValue();
        if (curso != null) {
            if (!modeloGeneral.contains(curso)) {
                modeloGeneral.addElement(curso);
            } else {
                JOptionPane.showMessageDialog(frame, "Este curso ya está en la Biblioteca General.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Seleccione un curso para compartir.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showView() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CursosColaborador().showView());
    }
}
