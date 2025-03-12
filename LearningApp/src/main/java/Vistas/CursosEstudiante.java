package Vistas;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;

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
        
        JPanel panel_arriba = new JPanel();
        panel_arriba.setOpaque(false);
        frame.getContentPane().add(panel_arriba, BorderLayout.NORTH);
        panel_arriba.setLayout(new BoxLayout(panel_arriba, BoxLayout.X_AXIS));
        
        
        JPanel panel_medio = new JPanel();
        panel_medio.setOpaque(false);
        frame.getContentPane().add(panel_medio, BorderLayout.CENTER);
        frame.setBounds(100, 100, 480, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
