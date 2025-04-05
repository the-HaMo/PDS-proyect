package Vistas;

import java.awt.*;
import javax.swing.*;

public class CursosColaborador {

    private JFrame frame;
    private DefaultListModel<String> modeloGeneral, modeloPrivado;
    private JList<String> listaGeneral, listaPrivado;
    private JPanel panelBotones;

    public CursosColaborador() {
        initialize();
        this.frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame("Cursos Colaborador");
        frame.setBounds(100, 100, 800, 600); // Tamaño aumentado
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Panel del título
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(0, 255, 0));
        panelTitulo.setPreferredSize(new Dimension(10, 50));
        frame.getContentPane().add(panelTitulo, BorderLayout.NORTH);

        JLabel lblApp = new JLabel("LearningApp Colaborador", SwingConstants.CENTER);
        lblApp.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelTitulo.add(lblApp, BorderLayout.CENTER);

        // Panel de bibliotecas
        JPanel panelBibliotecas = new JPanel(new BorderLayout());
        panelBibliotecas.setBackground(new Color(128, 255, 128));
        frame.getContentPane().add(panelBibliotecas, BorderLayout.CENTER);

        // -------------------- Panel General (CursosOnline) --------------------
        modeloGeneral = new DefaultListModel<>();
        listaGeneral = new JList<>(modeloGeneral);

        JPanel panelGeneral = new JPanel(new BorderLayout());
        panelGeneral.setPreferredSize(new Dimension(370, 300));
        panelGeneral.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3), "CursosOnline"));
        panelGeneral.setBackground(Color.WHITE);

        JScrollPane scrollGeneral = new JScrollPane(listaGeneral);
        scrollGeneral.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        panelGeneral.add(scrollGeneral, BorderLayout.CENTER);

        JButton btnCompartir = new JButton("Compartir Curso");
        btnCompartir.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        btnCompartir.setForeground(Color.WHITE);
        btnCompartir.setBackground(Color.decode("#4CAF50"));
        btnCompartir.setFocusPainted(false);
        btnCompartir.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btnCompartir.addActionListener(e -> compartirCurso());

        JPanel panelBotonCompartir = new JPanel();
        panelBotonCompartir.setBackground(Color.WHITE);
        panelBotonCompartir.add(btnCompartir);
        panelGeneral.add(panelBotonCompartir, BorderLayout.SOUTH);

        panelBibliotecas.add(panelGeneral, BorderLayout.WEST);

        // -------------------- Panel Privado (MisCursos) --------------------
        modeloPrivado = new DefaultListModel<>();
        listaPrivado = new JList<>(modeloPrivado);

        JPanel panelPrivado = new JPanel(new BorderLayout());
        panelPrivado.setPreferredSize(new Dimension(370, 300));
        panelPrivado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3), "MisCursos"));
        panelPrivado.setBackground(Color.WHITE);

        JScrollPane scrollPrivado = new JScrollPane(listaPrivado);
        scrollPrivado.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        panelPrivado.add(scrollPrivado, BorderLayout.CENTER);

        JButton btnImportar = new JButton("Importar Curso");
        btnImportar.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        btnImportar.setForeground(Color.WHITE);
        btnImportar.setBackground(Color.decode("#4CAF50"));
        btnImportar.setFocusPainted(false);
        btnImportar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btnImportar.addActionListener(e -> importarCurso());

        JPanel panelBotonImportar = new JPanel();
        panelBotonImportar.setBackground(Color.WHITE);
        panelBotonImportar.add(btnImportar);
        panelPrivado.add(panelBotonImportar, BorderLayout.SOUTH);

        panelBibliotecas.add(panelPrivado, BorderLayout.EAST);

        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setPreferredSize(new Dimension(0, 10));
        panelBibliotecas.add(verticalStrut, BorderLayout.NORTH);
        
        panelBotones = new JPanel();
        panelBotones.setBackground(new Color(128, 255, 128));
        panelBibliotecas.add(panelBotones, BorderLayout.NORTH);
        panelBotones.setLayout(new BorderLayout(0, 0));
        
        JButton btnlogout = new JButton("Logout");
        btnlogout.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        btnlogout.setForeground(Color.WHITE);
        btnlogout.setBackground(Color.decode("#4CAF50"));
        btnlogout.setFocusPainted(false);
        btnlogout.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        panelBotones.add(btnlogout, BorderLayout.EAST);
        
        JLabel lblNewLabel = new JLabel("Hola --- !");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        panelBotones.add(lblNewLabel, BorderLayout.WEST);

        JPanel panelSur = new JPanel();
        panelSur.setBackground(new Color(128, 255, 128));
        panelSur.setPreferredSize(new Dimension(10, 30));
        frame.getContentPane().add(panelSur, BorderLayout.SOUTH);

        JPanel panelOeste = new JPanel();
        panelOeste.setBackground(new Color(128, 255, 128));
        frame.getContentPane().add(panelOeste, BorderLayout.WEST);

        JPanel panelEste = new JPanel();
        panelEste.setBackground(new Color(128, 255, 128));
        frame.getContentPane().add(panelEste, BorderLayout.EAST);
    }

    
    // Funcionalidad de los botones 
    
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

    public void Mostrar() {
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CursosColaborador window = new CursosColaborador();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
