package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import Modelo.*;

public class PreguntasBloque {

    private JFrame frame;
    private BloqueContenido bloque;
    private List<Pregunta> preguntas;
    private int indiceActual = 0;

    private JLabel lblTipo;
    private JTextArea txtPregunta;
    private JPanel panelRespuesta;
    private JButton btnAnterior, btnSiguiente;
    private JButton btnComprobar;

    public PreguntasBloque(BloqueContenido bloque) {
        this.bloque = bloque;
        this.preguntas = bloque.getPreguntas();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Preguntas - " + bloque.getNombreBloque());
        frame.setBounds(200, 200, 650, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Preguntas del bloque: " + bloque.getNombreBloque(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(0, 255, 0));
        lblTitulo.setPreferredSize(new Dimension(10, 50));
        frame.add(lblTitulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(Color.WHITE);
        frame.add(centro, BorderLayout.CENTER);

        lblTipo = new JLabel("", SwingConstants.CENTER);
        lblTipo.setFont(new Font("SansSerif", Font.BOLD, 14));
        centro.add(lblTipo, BorderLayout.NORTH);

        txtPregunta = new JTextArea();
        txtPregunta.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtPregunta.setWrapStyleWord(true);
        txtPregunta.setLineWrap(true);
        txtPregunta.setEditable(false);
        txtPregunta.setBackground(new Color(240, 255, 240));
        txtPregunta.setMargin(new Insets(10, 10, 10, 10));
        centro.add(new JScrollPane(txtPregunta), BorderLayout.CENTER);

        panelRespuesta = new JPanel();
        panelRespuesta.setLayout(new BoxLayout(panelRespuesta, BoxLayout.Y_AXIS));
        panelRespuesta.setBorder(BorderFactory.createTitledBorder("Tu respuesta"));
        centro.add(panelRespuesta, BorderLayout.SOUTH);

        // Botones abajo
        JPanel abajo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        abajo.setBackground(new Color(128, 255, 128));
        frame.add(abajo, BorderLayout.SOUTH);

        btnAnterior = new JButton("Anterior");
        btnAnterior.addActionListener(e -> mostrarPregunta(indiceActual - 1));
        abajo.add(btnAnterior);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(e -> mostrarPregunta(indiceActual + 1));
        abajo.add(btnSiguiente);

        btnComprobar = new JButton("Comprobar respuesta");
        btnComprobar.addActionListener(e -> comprobarRespuestaActual());
        abajo.add(btnComprobar);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> frame.dispose());
        abajo.add(btnCerrar);

        if (!preguntas.isEmpty()) {
            mostrarPregunta(0);
        } else {
            txtPregunta.setText("Este bloque no contiene preguntas.");
            lblTipo.setText("");
            btnComprobar.setEnabled(false);
        }
    }

    private void mostrarPregunta(int index) {
        if (index < 0 || index >= preguntas.size()) return;

        indiceActual = index;
        Pregunta p = preguntas.get(index);
        txtPregunta.setText(p.getEnunciado());
        lblTipo.setText("Tipo: " + p.getTipo());

        btnAnterior.setEnabled(index > 0);
        btnSiguiente.setEnabled(index < preguntas.size() - 1);

        panelRespuesta.removeAll();

        if (p instanceof PreguntaTest pt) {
            ButtonGroup grupo = new ButtonGroup();
            for (String opcion : pt.getOpciones()) {
                JRadioButton btn = new JRadioButton(opcion);
                btn.setActionCommand(opcion);
                grupo.add(btn);
                panelRespuesta.add(btn);
            }
            panelRespuesta.putClientProperty("tipo", "test");
            panelRespuesta.putClientProperty("grupo", grupo);

        } else if (p instanceof PreguntaRellenarHuecos prh) {
            JTextArea txt = new JTextArea(prh.getTextoConHuecos());
            txt.setEditable(false);
            JTextField campo = new JTextField();
            panelRespuesta.add(txt);
            panelRespuesta.add(new JLabel("Completa el texto:"));
            panelRespuesta.add(campo);
            panelRespuesta.putClientProperty("tipo", "texto");
            panelRespuesta.putClientProperty("campo", campo);

        } else if (p instanceof PreguntaTraduccion pt) {
            JTextField campo = new JTextField();
            panelRespuesta.add(new JLabel("Traduce:"));
            panelRespuesta.add(campo);
            panelRespuesta.putClientProperty("tipo", "texto");
            panelRespuesta.putClientProperty("campo", campo);
        }

        panelRespuesta.revalidate();
        panelRespuesta.repaint();
    }

    private void comprobarRespuestaActual() {
        Pregunta p = preguntas.get(indiceActual);

        if (p instanceof PreguntaTest pt) {
            ButtonGroup grupo = (ButtonGroup) panelRespuesta.getClientProperty("grupo");
            if (grupo.getSelection() != null) {
                String seleccionada = grupo.getSelection().getActionCommand();
                if (pt.isCorrecta(seleccionada)) {
                    mostrarResultado("✅ ¡Correcto!");
                } else {
                    mostrarResultado("❌ Incorrecto. Respuesta correcta: " + pt.getRespuesta());
                }
            } else {
                mostrarResultado("Selecciona una opción.");
            }

        } else if (p instanceof PreguntaRellenarHuecos prh) {
            JTextField campo = (JTextField) panelRespuesta.getClientProperty("campo");
            String entrada = campo.getText().trim();
            if (entrada.isEmpty()) {
                mostrarResultado("Completa el campo.");
            } else if (prh.isCorrecta(entrada)) {
                mostrarResultado("✅ ¡Correcto!");
            } else {
                mostrarResultado("❌ Incorrecto. Respuesta correcta: " + prh.getRespuesta());
            }

        } else if (p instanceof PreguntaTraduccion pt) {
            JTextField campo = (JTextField) panelRespuesta.getClientProperty("campo");
            String entrada = campo.getText().trim();
            if (entrada.isEmpty()) {
                mostrarResultado("Escribe tu traducción.");
            } else if (pt.isCorrecta(entrada)) {
                mostrarResultado("✅ ¡Correcto!");
            } else {
                mostrarResultado("❌ Incorrecto. Respuesta correcta: " + pt.getRespuesta());
            }
        }
    }

    private void mostrarResultado(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}
