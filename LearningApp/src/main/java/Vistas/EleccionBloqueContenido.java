package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Modelo.Curso;
import Modelo.BloqueContenido;

public class EleccionBloqueContenido {

    private JFrame frame;
    private Curso curso;
    private DefaultListModel<BloqueContenido> modeloBloques;
    private JList<BloqueContenido> listaBloques;

    public EleccionBloqueContenido(Curso curso) {
        this.curso = curso;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Bloques de Contenido - " + curso.getNombre());
        frame.setBounds(150, 150, 600, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Panel superior
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(0, 255, 0));
        panelTitulo.setPreferredSize(new Dimension(10, 50));
        frame.getContentPane().add(panelTitulo, BorderLayout.NORTH);

        JLabel lblTitulo = new JLabel("Bloques del curso: " + curso.getNombre(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);

        // Panel central con los bloques
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(new Color(128, 255, 128));
        frame.getContentPane().add(panelCentro, BorderLayout.CENTER);

        modeloBloques = new DefaultListModel<>();
        listaBloques = new JList<>(modeloBloques);
        listaBloques.setCellRenderer(new BloqueListRenderer());
        listaBloques.setFixedCellHeight(-1); // Altura dinámica
        listaBloques.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaBloques.setBackground(Color.WHITE);


        for (BloqueContenido bloque : curso.getBloquesContenidos()) {
            modeloBloques.addElement(bloque);
        }

        JScrollPane scrollPane = new JScrollPane(listaBloques,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Bloques disponibles"));
        panelCentro.add(scrollPane, BorderLayout.CENTER);


        // Doble clic para abrir preguntas
        listaBloques.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    BloqueContenido seleccionado = listaBloques.getSelectedValue();
                    if (seleccionado != null) {
                        new PreguntasBloque(seleccionado).mostrar();
                    }
                }
            }
        });

        // Botón volver
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBackground(new Color(128, 255, 128));
        JButton btnVolver = new JButton("Cerrar");
        btnVolver.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        btnVolver.setBackground(Color.decode("#4CAF50"));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        btnVolver.addActionListener(e -> frame.dispose());
        panelInferior.add(btnVolver);
        frame.getContentPane().add(panelInferior, BorderLayout.SOUTH);
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}
