package Vistas;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		frame = new JFrame("Curso Estudiante");
		frame.setBounds(100, 100, 800, 600); // Tamaño aumentado
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		ImageIcon icono = new ImageIcon(getClass().getResource("/titulo.png"));	
		frame.setIconImage(icono.getImage());
        
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
					//elem.actualizarLikes();
					//int index = listaGeneral.getSelectedIndex();
					//modeloGeneral.set(index, elem);
					//exportar(curso);
					cargarCursos();
				} else {
					JOptionPane.showMessageDialog(null, "Ya dio like", "Advertencia", JOptionPane.ERROR_MESSAGE);
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

		JButton btnDescargar = new JButton("Descargar Curso");
		btnDescargar.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		btnDescargar.setForeground(Color.WHITE);
		btnDescargar.setBackground(Color.decode("#4CAF50"));
		btnDescargar.setFocusPainted(false);
		btnDescargar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

		btnDescargar.addActionListener(e -> {
			Elemento elem = listaGeneral.getSelectedValue();
			if(elem!=null) {
				Curso curso = elem.getCurso();
				List<Curso> cursosExportados = Controlador.INSTANCE.getUsuarioActual().getCursos();
				boolean yaExportado = cursosExportados.stream()
						.anyMatch(c -> c.getId().equals(curso.getId()));
				if (!yaExportado) {
					exportar(curso);
				} else {
					JOptionPane.showMessageDialog(frame, "Este curso ya ha sido descargado.", "Información", JOptionPane.INFORMATION_MESSAGE);
				}
	
			}
		});

		JPanel panelBotonImportar = new JPanel();
		panelBotonImportar.setBackground(Color.WHITE);
		panelBotonImportar.add(btnDescargar);
		panelPrivado.add(panelBotonImportar, BorderLayout.SOUTH);

		panelBibliotecas.add(panelPrivado, BorderLayout.EAST);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 255, 128));
		panelBibliotecas.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout());
		
		JLabel lblNewLabel = new JLabel("Hola "+ Controlador.INSTANCE.getUsuarioActual().getNombre() + "!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel, BorderLayout.WEST);
		
		JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botonesPanel.setBackground(new Color(128, 255, 128));
		
		JButton btnStats = new JButton("Stats");
		btnStats.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		btnStats.setForeground(Color.WHITE);
		btnStats.setBackground(Color.decode("#4CAF50"));
		btnStats.setFocusPainted(false);
		btnStats.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
		panel.add(btnStats);
		btnStats.addActionListener(e -> {
			new VistasEstadisticas(Controlador.INSTANCE.getStats()).setVisible(true);
		});
		
		JButton btnlogout = new JButton("Logout");
		btnlogout.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		btnlogout.setForeground(Color.WHITE);
		btnlogout.setBackground(Color.decode("#4CAF50"));
		btnlogout.setFocusPainted(false);
		btnlogout.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
		panel.add(btnlogout);
		btnlogout.addActionListener(e -> {
			// GUARDAR EL ESTADO ACTUAL 
			Controlador.INSTANCE.cerrarSesion();
			frame.dispose();
			Login login = new Login();
			login.show();
		});

		botonesPanel.add(btnStats);
		botonesPanel.add(btnlogout);
		panel.add(botonesPanel, BorderLayout.EAST);
		
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

	private void exportar(Curso c) {
		List<Curso> cursos = Controlador.INSTANCE.getUsuarioActual().getCursos();
		boolean encontrado = false;
		for (int i = 0; i < cursos.size(); i++) {
		    Curso cursoExistente = cursos.get(i);
		    if (cursoExistente.getId().equals(c.getId())) { // o por ID si quieres
		        cursos.set(i, c); // reemplazar el viejo por el nuevo
		        encontrado = true;
		        break;
		    }
		}
		if (!encontrado) {
		    cursos.add(c);
		}
		Controlador.INSTANCE.actualizarCursosUsuario(cursos);
		c.addNumDescargas();
		Controlador.INSTANCE.actualizarCurso(c);
		cargarCursos();

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
			   modeloGeneral.addElement(new Elemento(curso));
		}
		for (Curso curso : cursosPrivados) {
			   modeloPrivado.addElement(new Elemento(curso));
		}

	}



	private void agregarDobleClickListener(JList<Elemento> lista) {
		lista.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					Elemento elem = lista.getSelectedValue();
					if (elem != null) {
						Curso cursoSeleccionado = elem.getCurso();
						Controlador.INSTANCE.setCursoActual(cursoSeleccionado);
						List<Curso> cursosExportados = Controlador.INSTANCE.getUsuarioActual().getCursos();
						Map<Curso, Estrategia> cursosEmpezados = Controlador.INSTANCE.getCursosEmpezados();
						Estrategia estrategia=null;
						if (!cursosExportados.contains(cursoSeleccionado)) {
							exportar(cursoSeleccionado); //Aqui se guarda el curso actualizado
						}
						if (!cursosEmpezados.containsKey(cursoSeleccionado)) {	//si no lo he empezado
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
									//cursoSeleccionado.setEstrategia(Estrategia.ALEATORIA);
									estrategia = Estrategia.ALEATORIA;
									break;
								case "Secuencial":
									//cursoSeleccionado.setEstrategia(Estrategia.SECUENCIAL);
									estrategia = Estrategia.SECUENCIAL;
									break;
								case "Repetición Espaciada":
									//cursoSeleccionado.setEstrategia(Estrategia.REPETICION_ESPACIADA);
									estrategia = Estrategia.REPETICION_ESPACIADA;
									break;
								default:
									throw new IllegalArgumentException("Estrategia no válida: " + seleccion);
								}
								//cursoSeleccionado.aplicarEstrategias();
								Controlador.INSTANCE.empezarCurso(cursoSeleccionado, estrategia);
							} else {
								return;
							}
						}
						cargarCursos();
						new EleccionBloqueContenido(cursoSeleccionado, Controlador.INSTANCE.getEstrategiaCurso(cursoSeleccionado)).mostrar();
						frame.dispose();
					}
				}
			}
		});
	}

}