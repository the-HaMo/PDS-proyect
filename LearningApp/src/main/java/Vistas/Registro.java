package Vistas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro extends JFrame {

    public Registro() {
        super("La Mejor Ventana de Tu Vida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centrar la ventana

        // Usamos un panel personalizado con fondo degradado
        GradientPanel panel = new GradientPanel();
        panel.setLayout(new BorderLayout());

        // Título en la parte superior
        JLabel titleLabel = new JLabel("Bienvenido a la Mejor Ventana", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Panel central para componentes interactivos
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // Transparente para ver el degradado
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Botón con estilo y efecto
        JButton actionButton = new JButton("¡Haz clic aquí!");
        actionButton.setFont(new Font("Arial", Font.PLAIN, 20));
        actionButton.setFocusPainted(false);
        actionButton.setBackground(new Color(255, 102, 0)); // Color llamativo
        actionButton.setForeground(Color.WHITE);
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Registro.this,
                        "¡Has hecho clic en el botón!\nDisfruta de esta experiencia única.",
                        "Mensaje Especial",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(actionButton, gbc);

        // Campo de texto con estilo
        JTextField textField = new JTextField("Escribe algo inspirador...", 20);
        textField.setFont(new Font("Arial", Font.ITALIC, 18));
        gbc.gridy = 1;
        centerPanel.add(textField, gbc);

        panel.add(centerPanel, BorderLayout.CENTER);

        // Pie de ventana con un mensaje
        JLabel footerLabel = new JLabel("Creado con pasión y código", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
        footerLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(footerLabel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    // Clase interna para crear un JPanel con fondo degradado
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            // Degradado vertical: desde un azul vibrante hasta uno más oscuro
            Color colorInicio = new Color(0, 102, 204);
            Color colorFin = new Color(0, 51, 102);
            GradientPaint gp = new GradientPaint(0, 0, colorInicio, 0, height, colorFin);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    public static void main(String[] args) {
        // Aplicar el look and feel del sistema para mejorar la apariencia
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            // En caso de error, se ignora y se sigue con el look por defecto
        }
        SwingUtilities.invokeLater(() -> new Registro());
    }
}
