package Vistas;

import javax.swing.*;

import Controlador.Controlador;
import Modelo.EstadisticaUsuario;

import java.awt.*;

public class VistasEstadisticas extends JFrame {

	private static final long serialVersionUID = 1L;

	public VistasEstadisticas(EstadisticaUsuario stats) {
        setTitle("Estadísticas del Estudiante");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icono = new ImageIcon(getClass().getResource("/titulo.png"));	
        this.setIconImage(icono.getImage());

        // Panel principal
        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 255, 128));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiquetas con la información
        JLabel lblRacha = new JLabel("Racha actual de preguntas correctas: " + Controlador.INSTANCE.getStats().getRacha());
        JLabel lblMejorRacha = new JLabel("Mejor racha de preguntas correctas: " + Controlador.INSTANCE.getStats().getMejorRacha());
        JLabel lblTiempo = new JLabel("Tiempo de uso ultima sesión: "+ Controlador.INSTANCE.getStats().getTiempoUso() + " segundos");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setMaximumSize(new Dimension(32767, 10));
        verticalStrut.setMinimumSize(new Dimension(0, 10));
        verticalStrut.setPreferredSize(new Dimension(0, 10));
        panel.add(verticalStrut);

        // Añadir componentes
        panel.add(lblRacha);
        panel.add(lblMejorRacha);
        panel.add(lblTiempo);

        getContentPane().add(panel, BorderLayout.CENTER);
        
        JPanel Titulo = new JPanel();
        Titulo.setPreferredSize(new Dimension(10, 30));
        Titulo.setBackground(new Color(0, 255, 0));
        getContentPane().add(Titulo, BorderLayout.NORTH);
        Titulo.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel = new JLabel("STATS");
        lblNewLabel.setBackground(new Color(0, 255, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Titulo.add(lblNewLabel, BorderLayout.CENTER);
        
        JPanel panelSur = new JPanel();
        panelSur.setBackground(new Color(128, 255, 128));
        getContentPane().add(panelSur, BorderLayout.SOUTH);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Dialog", Font.BOLD, 12));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btnCerrar.setBackground(new Color(76, 175, 80));
        panelSur.add(btnCerrar);
        btnCerrar.addActionListener(e -> dispose());
    }
}
