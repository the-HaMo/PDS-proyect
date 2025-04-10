package Vistas;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import Controlador.Controlador;
import Modelo.Curso;

public class CursosEstudiante {

    private JFrame frame;
    private DefaultListModel<Curso> modeloGeneral, modeloPrivado;
    private JList<Curso> listaGeneral, listaPrivado;

    public CursosEstudiante() {
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

        JLabel lblApp = new JLabel("LearningApp Estudiante", SwingConstants.CENTER);
        lblApp.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelTitulo.add(lblApp, BorderLayout.CENTER);

        // Panel de bibliotecas
        JPanel panelBibliotecas = new JPanel(new BorderLayout());
        panelBibliotecas.setBackground(new Color(128, 255, 128));
        frame.getContentPane().add(panelBibliotecas, BorderLayout.CENTER);

        // -------------------- Panel General (CursosOnline) --------------------
        modeloGeneral = new DefaultListModel<Curso>();
        listaGeneral = new JList<>(modeloGeneral);
        listaGeneral.setCellRenderer(new ElementoListRenderer());

        JPanel panelGeneral = new JPanel(new BorderLayout());
        panelGeneral.setPreferredSize(new Dimension(370, 300));
        panelGeneral.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3), "CursosOnline"));
        panelGeneral.setBackground(Color.WHITE);

        JScrollPane scrollGeneral = new JScrollPane(listaGeneral);
        scrollGeneral.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        panelGeneral.add(scrollGeneral, BorderLayout.CENTER);

        JButton btnCompartir = new JButton("Exportar Curso");
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
        modeloPrivado = new DefaultListModel<Curso>();
        listaPrivado = new JList<>(modeloPrivado);
        listaPrivado.setCellRenderer(new ElementoListRenderer());
        
        JPanel panelPrivado = new JPanel(new BorderLayout());
        panelPrivado.setPreferredSize(new Dimension(370, 300));
        panelPrivado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3), "MisCursos"));
        panelPrivado.setBackground(Color.WHITE);

        JScrollPane scrollPrivado = new JScrollPane(listaPrivado);
        scrollPrivado.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        panelPrivado.add(scrollPrivado, BorderLayout.CENTER);

        JButton btnImportar = new JButton("Ver detalles");
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
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 255, 128));
        panelBibliotecas.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));
        
        JButton btnlogout = new JButton("Logout");
        btnlogout.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        btnlogout.setForeground(Color.WHITE);
        btnlogout.setBackground(Color.decode("#4CAF50"));
        btnlogout.setFocusPainted(false);
        btnlogout.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        panel.add(btnlogout, BorderLayout.EAST);
        btnlogout.addActionListener(e -> {
        	// GUARDAR EL ESTADO ACTUAL 
        	Controlador.INSTANCE.cerrarSesion();
        	frame.dispose();
        	Login login = new Login();
        	login.show();
        });
        
        JLabel lblNewLabel = new JLabel("Hola "+ Controlador.INSTANCE.getUsuarioActual().getNombre() + "!");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(lblNewLabel, BorderLayout.WEST);

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
        
        cargarCursos();
    }
    
    public void Mostrar() {
    	frame.setVisible(true);
    }
    
    public void cargarCursos() {
    	modeloGeneral.clear();
    	modeloPrivado.clear();
    	
    	List<Curso> cursosOnline = Controlador.INSTANCE.getCursosPublicados();
		for (Curso curso : cursosOnline) {
			modeloGeneral.addElement(curso);
		}
    	
    	
    }
}
