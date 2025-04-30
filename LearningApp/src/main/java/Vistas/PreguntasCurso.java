
package Vistas;

import javax.swing.*;

import Controlador.Controlador;

import java.awt.*;
import java.util.List;
import Modelo.*;

public class PreguntasCurso {

    private JFrame frame;
    private List<Pregunta> preguntasExamen;
    private Curso curso;
    private int preguntaActual = 0;
    private JTextArea txtPregunta;
    private JPanel panelRespuesta;
    private JButton btnSiguiente;
    private JButton btnAnterior;
    private JLabel lblTipo;

    public PreguntasCurso(Curso curso, List<Pregunta> preguntasExamen) {
        this.curso=curso;
        this.preguntasExamen=preguntasExamen;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Preguntas del Curso: " + curso.getNombre());
        frame.setBounds(200, 200, 650, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Preguntas del curso: " + curso.getNombre(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(0, 255, 0));
        lblTitulo.setPreferredSize(new Dimension(10, 50));
        frame.getContentPane().add(lblTitulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(Color.WHITE);
        frame.getContentPane().add(centro, BorderLayout.CENTER);
        
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
        
        lblTipo = new JLabel("Tipo: <dynamic>", SwingConstants.CENTER);
        lblTipo.setFont(new Font("SansSerif", Font.BOLD, 14));
        centro.add(lblTipo, BorderLayout.NORTH);

        // Botones abajo
        JPanel abajo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        abajo.setBackground(new Color(128, 255, 128));
        frame.getContentPane().add(abajo, BorderLayout.SOUTH);

        btnAnterior = new JButton("Anterior");
        btnAnterior.addActionListener(e -> mostrarPregunta(preguntaActual - 1));
        abajo.add(btnAnterior);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(e -> {
            comprobarRespuestaActual();
            mostrarPregunta(preguntaActual + 1);
        });
        abajo.add(btnSiguiente);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> frame.dispose());
        abajo.add(btnCerrar);
        mostrarPregunta(0);

    }

    private void mostrarPregunta(int index) {
        if (index < 0 || index >= preguntasExamen.size()-1) {
        	new CursosEstudiante().Mostrar();
        	this.frame.dispose();
        	return;
        }

        preguntaActual = index;
        Pregunta p = preguntasExamen.get(index);

        txtPregunta.setText(p.getEnunciado());

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

        btnAnterior.setEnabled(preguntaActual > 0);
        btnSiguiente.setEnabled(preguntaActual < preguntasExamen.size() - 1);
    }

    private void comprobarRespuestaActual() {
        Pregunta p = preguntasExamen.get(preguntaActual);

        if (p instanceof PreguntaTest pt) {
            ButtonGroup grupo = (ButtonGroup) panelRespuesta.getClientProperty("grupo");
            if (grupo.getSelection() != null) {
                String seleccionada = grupo.getSelection().getActionCommand();
                if (pt.isCorrecta(seleccionada)) {
                    mostrarResultado("✅ ¡Correcto!");
                    Controlador.INSTANCE.getStats().respuestaCorrecta();
                } else {
                    mostrarResultado("❌ Incorrecto. Respuesta correcta: " + pt.getRespuesta());
                    Controlador.INSTANCE.getStats().respuestaIncorrecta();
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
                Controlador.INSTANCE.getStats().respuestaCorrecta();
            } else {
                mostrarResultado("❌ Incorrecto. Respuesta correcta: " + prh.getRespuesta());
                Controlador.INSTANCE.getStats().respuestaIncorrecta();
            }

        } else if (p instanceof PreguntaTraduccion pt) {
            JTextField campo = (JTextField) panelRespuesta.getClientProperty("campo");
            String entrada = campo.getText().trim();
            if (entrada.isEmpty()) {
                mostrarResultado("Escribe tu traducción.");
            } else if (pt.isCorrecta(entrada)) {
                mostrarResultado("✅ ¡Correcto!");
                Controlador.INSTANCE.getStats().respuestaCorrecta();
            } else {
                mostrarResultado("❌ Incorrecto. Respuesta correcta: " + pt.getRespuesta());
                Controlador.INSTANCE.getStats().respuestaIncorrecta();
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
