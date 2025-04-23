package Vistas;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import Controlador.Controlador;
import Modelo.Curso;
import Modelo.Estrategia;
import Modelo.Like;

public class CursosEstudiante {

	private JFrame frame;
	private DefaultListModel<Elemento> modeloGeneral, modeloPrivado;
	private JList<Elemento> listaGeneral, listaPrivado;

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
		modeloGeneral = new DefaultListModel<Elemento>();
		listaGeneral = new JList<>(modeloGeneral);
		listaGeneral.setCellRenderer(new ElementoListRenderer());
		agregarDobleClickListener(listaGeneral);

		JPanel panelGeneral = new JPanel(new BorderLayout());
		panelGeneral.setPreferredSize(new Dimension(370, 300));
		panelGeneral.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3), "Cursos Publicos"));
		panelGeneral.setBackground(Color.WHITE);

		JScrollPane scrollGeneral = new JScrollPane(listaGeneral);
		scrollGeneral.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		panelGeneral.add(scrollGeneral, BorderLayout.CENTER);

		JButton btnLike = new JButton("Dar Like");
		btnLike.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		btnLike.setForeground(Color.WHITE);
		btnLike.setBackground(Color.decode("#4CAF50"));
		btnLike.setFocusPainted(false);
		btnLike.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

		btnLike.addActionListener(e -> {
			Elemento elem = listaGeneral.getSelectedValue();
			if (elem != null) {
				Curso curso = elem.getCurso();
				boolean DarLike = Controlador.INSTANCE.darLike(curso);
				if (DarLike) {
					elem.actualizarLikes();
					int index = listaGeneral.getSelectedIndex();
					modeloGeneral.set(index, elem);
				} else {
					System.out.println("Ya dio like");
				}

			}});

		JButton btnTendencias = new JButton("Tendencias");
		btnTendencias.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		btnTendencias.setForeground(Color.WHITE);
		btnTendencias.setBackground(Color.decode("#4CAF50"));
		btnTendencias.setFocusPainted(false);
		btnTendencias.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

		btnTendencias.addActionListener(e -> {
			ordenarCursosPorTendencias();
		});

		JPanel panelBotonCompartir = new JPanel();
		panelBotonCompartir.setBackground(Color.WHITE);
		panelBotonCompartir.add(btnLike);
		panelBotonCompartir.add(btnTendencias);
		panelGeneral.add(panelBotonCompartir, BorderLayout.SOUTH);

		panelBibliotecas.add(panelGeneral, BorderLayout.WEST);

		// -------------------- Panel Privado (MisCursos) --------------------
		modeloPrivado = new DefaultListModel<Elemento>();
		listaPrivado = new JList<>(modeloPrivado);
		listaPrivado.setCellRenderer(new ElementoListRenderer());
		agregarDobleClickListener(listaPrivado);

		JPanel panelPrivado = new JPanel(new BorderLayout());
		panelPrivado.setPreferredSize(new Dimension(370, 300));
		panelPrivado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3), "Cursos Exportados"));
		panelPrivado.setBackground(Color.WHITE);

		JScrollPane scrollPrivado = new JScrollPane(listaPrivado);
		scrollPrivado.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		panelPrivado.add(scrollPrivado, BorderLayout.CENTER);

		JButton btnDetalles = new JButton("Ver detalles");
		btnDetalles.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		btnDetalles.setForeground(Color.WHITE);
		btnDetalles.setBackground(Color.decode("#4CAF50"));
		btnDetalles.setFocusPainted(false);
		btnDetalles.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
		btnDetalles.addActionListener( e -> {

		});

		JButton btnExportar = new JButton("Exportar Curso");
		btnExportar.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		btnExportar.setForeground(Color.WHITE);
		btnExportar.setBackground(Color.decode("#4CAF50"));
		btnExportar.setFocusPainted(false);
		btnExportar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

		btnExportar.addActionListener(e -> {
			Elemento elem = listaGeneral.getSelectedValue();
			exportar(elem);
		});

		JPanel panelBotonImportar = new JPanel();
		panelBotonImportar.setBackground(Color.WHITE);
		panelBotonImportar.add(btnDetalles);
		panelBotonImportar.add(btnExportar);
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
	
	private void exportar(Elemento elem) {
		if (elem != null) {
			if (!Controlador.INSTANCE.getUsuarioActual().getCursos().contains(elem.getCurso())) {
				Controlador.INSTANCE.exportarCurso(elem.getCurso());
				cargarCursos();
			} else {
				JOptionPane.showMessageDialog(frame, "El curso ya ha sido exportado.", "Error",	JOptionPane.ERROR_MESSAGE);	
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Por favor, selecciona un curso para exportar.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void ordenarCursosPorTendencias() {
		List<Curso> cursosOnline = new ArrayList<>();

		for (int i = 0; i < modeloGeneral.size(); i++) {
			cursosOnline.add(modeloGeneral.getElementAt(i).getCurso());
		}

		List<Curso> cursosOrdenados = Controlador.INSTANCE.getCursosEnOrdenLikes(cursosOnline);
		modeloGeneral.clear();

		for (Curso c : cursosOrdenados) {
			Elemento elem = new Elemento(c);
			modeloGeneral.addElement(elem);
		}
	}

	public void Mostrar() {
		frame.setVisible(true);
	}

	public void cargarCursos() {
		modeloGeneral.clear();
		modeloPrivado.clear();

		List<Curso> cursosOnline = Controlador.INSTANCE.getCursosPublicados();
		List<Curso> cursosPrivados = Controlador.INSTANCE.getUsuarioActual().getCursos();
		for (Curso curso : cursosOnline) {
			Elemento elem=new Elemento(curso);
			modeloGeneral.addElement(elem);
		}
		for (Curso curso : cursosPrivados) {
			Elemento elem=new Elemento(curso);
			modeloPrivado.addElement(elem);
		}

	}


	private void agregarDobleClickListener(JList<Elemento> lista) {
		lista.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					Elemento elem = lista.getSelectedValue();
					if (elem != null) {
						Curso cursoSeleccionado = elem.getCurso();
						List<Curso> cursosEmpezados = Controlador.INSTANCE.getCursosEmpezados();
						
						if (!cursosEmpezados.contains(cursoSeleccionado)) {
							String[] estrategias = {"Aleatoria", "Secuencial", "Repetición Espaciada"};
							String seleccion = (String) JOptionPane.showInputDialog(
									frame,
									"Selecciona una estrategia para comenzar el curso:",
									"Estrategia de Aprendizaje",
									JOptionPane.QUESTION_MESSAGE,
									null,
									estrategias,
									estrategias[0]
									);

							if (seleccion != null) {
								switch (seleccion) {
								case "Aleatoria":
									cursoSeleccionado.setEstrategia(Estrategia.ALEATORIA);
									break;
								case "Secuencial":
									cursoSeleccionado.setEstrategia(Estrategia.SECUENCIAL);
									break;
								case "Repetición Espaciada":
									cursoSeleccionado.setEstrategia(Estrategia.REPETICION_ESPACIADA);
									break;
								default:
									throw new IllegalArgumentException("Estrategia no válida: " + seleccion);
								}
								exportar(elem);
								Controlador.INSTANCE.empezarCurso(cursoSeleccionado);
								
							}
						}
						cargarCursos();
						//cursoSeleccionado.aplicarEstrategias();
						new EleccionBloqueContenido(cursoSeleccionado).mostrar();
						frame.dispose();
					}
				}
			}
		});
	}



}
