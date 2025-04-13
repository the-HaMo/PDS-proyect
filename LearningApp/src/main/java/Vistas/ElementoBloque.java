package Vistas;

import Modelo.BloqueContenido;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ElementoBloque extends JPanel {

    private static final long serialVersionUID = 1L;
    private BloqueContenido bloque;

    public ElementoBloque(BloqueContenido bloque) {
        this.bloque = bloque;
        initializeComponent();
    }

    private void initializeComponent() {
        this.setLayout(new BorderLayout(10, 5));
        this.setBackground(Color.WHITE);
        this.setBorder(new TitledBorder("Bloque"));

        // Título del bloque
        JLabel lblTitulo = new JLabel(bloque.getNombreBloque());
        lblTitulo.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //Dificultad del Bloque
        JLabel lblDificultad = new JLabel("Dificultad: " + bloque.getDificultad());
        lblDificultad.setFont(new Font("Sans-Serif", Font.ITALIC, 12));
        lblDificultad.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        lblDificultad.setForeground(Color.GRAY);
        
        // Número de preguntas
        JLabel lblPreguntas = new JLabel("Preguntas: " + bloque.getPreguntas().size());
        lblPreguntas.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
        lblPreguntas.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        lblPreguntas.setForeground(Color.GRAY);
        
        // Panel de contenido con layout vertical
        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false); // Para mantener el fondo blanco del panel padre
        contenido.add(lblTitulo);
        contenido.add(lblDificultad);
        contenido.add(lblPreguntas);

        this.add(contenido, BorderLayout.CENTER);
    }

    public BloqueContenido getBloque() {
        return bloque;
    }
}
