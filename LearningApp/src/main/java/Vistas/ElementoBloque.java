package Vistas;

import Modelo.BloqueContenido;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ElementoBloque extends JPanel {

    private static final long serialVersionUID = 1L;
    private BloqueContenido bloque;
    private boolean completado;
    private JLabel lblEstado;
    
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
        
        // Número de preguntas
        JLabel lblPreguntas = new JLabel("Preguntas: " + bloque.getPreguntas().size());
        lblPreguntas.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
        lblPreguntas.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        lblPreguntas.setForeground(Color.GRAY);
        
        // Estado Completado/ No completado
        lblEstado = new JLabel();
        lblEstado.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        lblEstado.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        lblEstado.setForeground(Color.DARK_GRAY);

        

        
        // Panel de contenido con layout vertical
        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false); // Para mantener el fondo blanco del panel padre
        contenido.add(lblTitulo);
        contenido.add(lblPreguntas);
        contenido.add(lblEstado);
        
        this.add(contenido, BorderLayout.CENTER);
    }

    public BloqueContenido getBloque() {
        return bloque;
    }
    
    public boolean isCompletado() {
        return completado;
    }

    public String getEstadoTexto() {
        return completado ? "Completado" : "No completado";
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;

        if (completado) {
            this.setBackground(new Color(198, 239, 206)); // verde claro
            lblEstado.setText("✅ Completado");
        } else {
            this.setBackground(new Color(255, 204, 204)); // rojo claro
            lblEstado.setText("❌ No completado");
        }

        repaint();
    }
   
    
}
