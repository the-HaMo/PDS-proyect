package Vistas;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Modelo.Curso;
import Modelo.Estrategia;
import Modelo.BloqueContenido;
import Modelo.Estudiante;
import Modelo.Pregunta;
import Repositorio.RepositorioProgresoCurso;
import Controlador.*;

public class EleccionBloqueContenido {

    private JFrame frame;
    private Curso curso;
    private Estrategia estrategia;
    private DefaultListModel<ElementoBloque> modeloBloques;
    private JList<ElementoBloque> listaBloques;

    public EleccionBloqueContenido(Curso curso, Estrategia estrategia) {
        this.curso = curso;
        this.estrategia = estrategia;
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


        Estudiante estudianteActual = (Estudiante) Controlador.INSTANCE.getUsuarioActual();

        for (BloqueContenido bloque : curso.getBloquesContenidos()) {
            ElementoBloque elemento = new ElementoBloque(bloque);

            boolean completado = RepositorioProgresoCurso.estaCompletado(estudianteActual, curso, bloque);
            elemento.setCompletado(completado);

            modeloBloques.addElement(elemento);
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
                	ElementoBloque elementoSeleccionado = listaBloques.getSelectedValue();
                	if (elementoSeleccionado != null) {
                	    BloqueContenido bloqueSeleccionado = elementoSeleccionado.getBloque();
                	    new PreguntasBloque(bloqueSeleccionado).mostrar();
                	    frame.dispose();
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

        btnVolver.addActionListener(e -> {
	        CursosEstudiante window = new CursosEstudiante();
			window.Mostrar();
        	frame.dispose();
        });
        
        JButton btnExamen = new JButton("Examinarse");
        btnExamen.setForeground(Color.WHITE);
        btnExamen.setFont(new Font("Dialog", Font.BOLD, 12));
        btnExamen.setFocusPainted(false);
        btnExamen.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btnExamen.setBackground(new Color(76, 175, 80));
        panelInferior.add(btnExamen);
        btnExamen.addActionListener(e -> {
        	List<Pregunta> examen = aplicarEstrategia(curso, estrategia);
        	new PreguntasCurso(curso,examen).mostrar();
        	this.frame.dispose();
        });
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalStrut.setPreferredSize(new Dimension(400, 0));
        panelInferior.add(horizontalStrut);
        panelInferior.add(btnVolver);
        frame.getContentPane().add(panelInferior, BorderLayout.SOUTH);
    }
    
    public List<Pregunta> aplicarEstrategia(Curso curso, Estrategia estrategia) {
    	List<Pregunta> examen = new ArrayList<>();
    	switch (estrategia) {
		case SECUENCIAL:
			examen = curso.getBloquesContenidos().stream()
										.flatMap(b -> b.getPreguntas().stream())
										.collect(Collectors.toList());
			break;
		case ALEATORIA:
			examen = curso.getBloquesContenidos().stream()
										.flatMap(b -> b.getPreguntas().stream())
										.collect(Collectors.toList());
			Collections.shuffle(examen);
		case REPETICION_ESPACIADA:
			List<Pregunta> original = curso.getBloquesContenidos().stream()
					.flatMap(b -> b.getPreguntas().stream())
					.collect(Collectors.toList());
			

			Random rand = new Random();
			int intervalos = rand.nextInt(4) + 2; 

			for (int i = 0; i < original.size(); i++) {
                Pregunta p = original.get(i);
                examen.add(p);
                if ((i + 1) % intervalos == 0) {
                    examen.addAll(original.subList(0, i + 1));
                }
            }
			break;
    	}
		for (Pregunta p : examen) {
			System.out.println(p.toString());
		}
    	return examen;
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}
