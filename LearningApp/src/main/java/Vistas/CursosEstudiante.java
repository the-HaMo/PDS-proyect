package Vistas;

import java.awt.*;
import javax.swing.*;

public class CursosEstudiante {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CursosEstudiante window = new CursosEstudiante();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CursosEstudiante() {
        initialize();
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

        JLabel lblApp = new JLabel("LearningApp Estudiante", SwingConstants.CENTER);
        lblApp.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelTitulo.add(lblApp, BorderLayout.CENTER);

        JLabel lblUsuario = new JLabel("Hola --- !");
        lblUsuario.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        panelTitulo.add(lblUsuario, BorderLayout.SOUTH);

        // Panel de bibliotecas
        JPanel panelBibliotecas = new JPanel(new BorderLayout());
        panelBibliotecas.setBackground(new Color(128, 255, 128));
        frame.getContentPane().add(panelBibliotecas, BorderLayout.CENTER);

        // -------------------- Panel General (CursosOnline) --------------------
        DefaultListModel<String> modeloGeneral = new DefaultListModel<>();
        JList<String> listaGeneral = new JList<>(modeloGeneral);

        JPanel panelGeneral = new JPanel(new BorderLayout());
        panelGeneral.setPreferredSize(new Dimension(370, 300));
        panelGeneral.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3), "CursosOnline"));
        panelGeneral.setBackground(Color.WHITE);

        JScrollPane scrollGeneral = new JScrollPane(listaGeneral);
        scrollGeneral.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        panelGeneral.add(scrollGeneral, BorderLayout.CENTER);

        JButton btnCompartir = new JButton("Importar Curso");
        btnCompartir.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        btnCompartir.setForeground(Color.WHITE);
        btnCompartir.setBackground(Color.decode("#4CAF50"));
        btnCompartir.setFocusPainted(false);
        btnCompartir.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        JButton btnTendencias = new JButton("Tendencias");
        btnTendencias.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        btnTendencias.setForeground(Color.WHITE);
        btnTendencias.setBackground(Color.decode("#4CAF50"));
        btnTendencias.setFocusPainted(false);
        btnTendencias.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
       
        JPanel panelBotonCompartir = new JPanel();
        panelBotonCompartir.setBackground(Color.WHITE);
        panelBotonCompartir.add(btnCompartir);
        panelBotonCompartir.add(btnTendencias);
        panelGeneral.add(panelBotonCompartir, BorderLayout.SOUTH);

        panelBibliotecas.add(panelGeneral, BorderLayout.WEST);

        // -------------------- Panel Privado (MisCursos) --------------------
        DefaultListModel<String> modeloPrivado = new DefaultListModel<>();
        JList<String> listaPrivado = new JList<>(modeloPrivado);

        JPanel panelPrivado = new JPanel(new BorderLayout());
        panelPrivado.setPreferredSize(new Dimension(370, 300));
        panelPrivado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3), "MisCursos"));
        panelPrivado.setBackground(Color.WHITE);

        JScrollPane scrollPrivado = new JScrollPane(listaPrivado);
        scrollPrivado.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        panelPrivado.add(scrollPrivado, BorderLayout.CENTER);

        JButton btnImportar = new JButton("Tendencias");
        btnImportar.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        btnImportar.setForeground(Color.WHITE);
        btnImportar.setBackground(Color.decode("#4CAF50"));
        btnImportar.setFocusPainted(false);
        btnImportar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
       
        JPanel panelBotonImportar = new JPanel();
        panelBotonImportar.setBackground(Color.WHITE);
        panelBotonImportar.add(btnImportar);
        panelPrivado.add(panelBotonImportar, BorderLayout.SOUTH);

        panelBibliotecas.add(panelPrivado, BorderLayout.EAST);

        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setPreferredSize(new Dimension(0, 10));
        panelBibliotecas.add(verticalStrut, BorderLayout.NORTH);

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
    
    public void Mostrar() {
    	frame.setVisible(true);
    }
}
