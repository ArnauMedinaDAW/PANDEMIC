package Swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import Controlador.*;
import Model.DatosPartida;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.sql.*;
import java.util.ArrayList;

public class PanelPrincipal extends JPanel implements ActionListener {

	JButton boton1;
	JButton boton2;
	JButton boton3;
	JButton boton4;
	JButton boton5;
	JButton boton7;
	JButton boton8;

	PanelPrincipal() {

		// screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Cargamos la imagen desde un archivo
		ImageIcon imagen = new ImageIcon("logo1.png");

		// Escalamos la imagen para que coincida con el tamaño de la pantalla
		Image img = imagen.getImage();
		Image imgEscalada = img.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);

		ImageIcon imagenEscalada = new ImageIcon(imgEscalada);

		// Creamos un JLabel para la imagen de fondo
		JLabel fondoLabel = new JLabel(imagenEscalada);
		fondoLabel.setLayout(new BorderLayout());

		// Creamos un contenedor JPanel para los botones
		JPanel botonPanel = new JPanel();
		botonPanel.setOpaque(false);

		boton1 = new JButton("NUEVA PARTIDA");
		boton1.setForeground(Color.WHITE);
		boton1.setFont(new Font("Tahoma", Font.BOLD, 20));
		boton1.setOpaque(false);
		boton1.setContentAreaFilled(false);
		boton1.setBorder(null);
		boton1.setHorizontalAlignment(SwingConstants.LEFT);
		boton1.addActionListener(this);

		boton2 = new JButton("CARGAR PARTIDA");
		boton2.setForeground(Color.WHITE);
		boton2.setFont(new Font("Tahoma", Font.BOLD, 20));
		boton2.setOpaque(false);
		boton2.setContentAreaFilled(false);
		boton2.setBorder(null);
		boton2.setHorizontalAlignment(SwingConstants.LEFT);
		boton2.addActionListener(this);

		boton3 = new JButton("INFORMACIÓN");
		boton3.setFont(new Font("Tahoma", Font.BOLD, 20));
		boton3.setForeground(Color.WHITE);
		boton3.setOpaque(false);
		boton3.setContentAreaFilled(false);
		boton3.setBorder(null);
		boton3.setHorizontalAlignment(SwingConstants.LEFT);
		boton3.addActionListener(this);

		boton4 = new JButton("PUNTUACIONES");
		boton4.setForeground(Color.WHITE);
		boton4.setFont(new Font("Tahoma", Font.BOLD, 20));
		boton4.setOpaque(false);
		boton4.setContentAreaFilled(false);
		boton4.setBorder(null);
		boton4.setHorizontalAlignment(SwingConstants.LEFT);
		boton4.addActionListener(this);

		boton5 = new JButton("AUTORES");
		boton5.setToolTipText("AAAAA");
		boton5.setFont(new Font("Tahoma", Font.BOLD, 20));
		boton5.setForeground(Color.WHITE);
		boton5.setOpaque(false);
		boton5.setContentAreaFilled(false);
		boton5.setBorder(null);
		boton5.setHorizontalAlignment(SwingConstants.LEFT);
		boton5.addActionListener(this);

		boton7 = new JButton("DIFICULTAD");
		boton7.setForeground(Color.WHITE);
		boton7.setFont(new Font("Tahoma", Font.BOLD, 20));
		boton7.setOpaque(false);
		boton7.setContentAreaFilled(false);
		boton7.setBorder(null);
		boton7.setHorizontalAlignment(SwingConstants.LEFT);
		boton7.addActionListener(this);

		boton8 = new JButton("SALIR");
		boton8.setFont(new Font("Tahoma", Font.BOLD, 20));
		boton8.setForeground(Color.WHITE);
		boton8.setOpaque(false);
		boton8.setContentAreaFilled(false);
		boton8.setBorder(null);
		boton8.setHorizontalAlignment(SwingConstants.LEFT);
		boton8.addActionListener(this);

		// Añadimos el contenedor JPanel de los botones al centro del JLabel de fondo
		fondoLabel.add(botonPanel, BorderLayout.CENTER);

		// Version del proyecto
		JLabel lblNewLabel = new JLabel("VERSION 1.0");
		lblNewLabel.setFont(new Font("Calisto MT", Font.BOLD, 15));
		lblNewLabel.setForeground(new Color(255, 255, 255));

		// Establacer posiciones de los botones con GroupLayout desde el editor
		// WindowsBuilder
		GroupLayout gl_botonPanel = new GroupLayout(botonPanel);
		gl_botonPanel.setHorizontalGroup(gl_botonPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_botonPanel.createSequentialGroup().addContainerGap(65, Short.MAX_VALUE)
						.addGroup(gl_botonPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_botonPanel
										.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 118,
												GroupLayout.PREFERRED_SIZE)
										.addGap(101))
								.addGroup(gl_botonPanel.createSequentialGroup()
										.addGroup(gl_botonPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(boton3, GroupLayout.PREFERRED_SIZE, 173,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(boton1, GroupLayout.PREFERRED_SIZE, 203,
														GroupLayout.PREFERRED_SIZE))
										.addGap(48)
										.addGroup(gl_botonPanel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_botonPanel.createSequentialGroup().addComponent(boton2)
														.addGap(86))
												.addGroup(gl_botonPanel.createSequentialGroup()
														.addGroup(gl_botonPanel.createParallelGroup(Alignment.TRAILING)
																.addComponent(boton4, GroupLayout.PREFERRED_SIZE, 184,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(boton8, GroupLayout.PREFERRED_SIZE, 145,
																		GroupLayout.PREFERRED_SIZE))
														.addGap(87)))
										.addGroup(gl_botonPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(boton5, GroupLayout.PREFERRED_SIZE, 145,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(boton7))
										.addGap(166)))));
		gl_botonPanel.setVerticalGroup(gl_botonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_botonPanel.createSequentialGroup().addGap(337)
						.addGroup(gl_botonPanel.createParallelGroup(Alignment.BASELINE).addComponent(boton2)
								.addComponent(boton7).addComponent(boton1))
						.addGap(42)
						.addGroup(gl_botonPanel.createParallelGroup(Alignment.BASELINE).addComponent(boton5)
								.addComponent(boton4).addComponent(boton3))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_botonPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_botonPanel.createSequentialGroup().addGap(111).addComponent(lblNewLabel,
										GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_botonPanel.createSequentialGroup().addGap(46).addComponent(boton8)))
						.addGap(31)));
		botonPanel.setLayout(gl_botonPanel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(fondoLabel,
				GroupLayout.PREFERRED_SIZE, 898, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(fondoLabel,
				GroupLayout.PREFERRED_SIZE, 484, Short.MAX_VALUE));
		setLayout(groupLayout);

		// Implementar Audio
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("PZMainTheme.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error al reproducir el sonido.");
		}
	}

	// Método que muestra un popUp para modificar la dificultad.
	public String mostrarPopupDificultad() {
		String[] opcionesDificultad = { "Facil", "Normal", "Dificil" };
		String dificultadSeleccionada = (String) JOptionPane.showInputDialog(this, "Seleccione la dificultad:",
				"Selección de Dificultad", JOptionPane.QUESTION_MESSAGE, null, opcionesDificultad,
				opcionesDificultad[0]);
		if (dificultadSeleccionada != null) {
			System.out.println("Dificultad seleccionada: " + dificultadSeleccionada);
		}
		return dificultadSeleccionada;
	}

	// Método que muestra un popUp para modificar la dificultad.Actualmente no esta
	// en uso
	public String mostrarPopupIdioma() {
		String[] opcionesIdioma = { "Español", "Catalan", "Ingles" };
		String IdiomaSeleccionado = (String) JOptionPane.showInputDialog(this, "Seleccione el Idioma:",
				"Selección de Idioma", JOptionPane.QUESTION_MESSAGE, null, opcionesIdioma, opcionesIdioma[0]);
		if (IdiomaSeleccionado != null) {
			System.out.println("Idioma seleccionada: " + IdiomaSeleccionado);
		}
		return IdiomaSeleccionado;
	}

	// Implementación de codigo al presionar un botón.
	@Override
	public void actionPerformed(ActionEvent e) {

		// NUEVA PARTIDA
		if (e.getSource() == boton1) {
			if (!Login.loginExitoso && !Login.registroExitoso) {
				// Crear un nuevo marco para el Login
				JFrame marcoLogin = new JFrame("PANDEMIC - Iniciar Sesión");
				marcoLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				marcoLogin.setSize(400, 200);
				marcoLogin.setResizable(false);
				marcoLogin.setLocationRelativeTo(null); // Centrar en pantalla

				// Agregar el panel de inicio de sesión al marco
				marcoLogin.getContentPane().add(new Login());

				// Mostrar el marco
				marcoLogin.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(this, "No puedes iniciar mas de una partida", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}
		// CARGAR PARTIDA

		if (e.getSource() == boton2) {

			if (!LoginCargarPartida.loginExitoso && !Login.registroExitoso && !LoginCargarPartida.loginExitoso) {
				// Crear un nuevo marco para el Login
				JFrame marcoLogin = new JFrame("PANDEMIC - Iniciar Sesión");
				marcoLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				marcoLogin.setSize(400, 200);
				marcoLogin.setResizable(false);
				marcoLogin.setLocationRelativeTo(null); // Centrar en pantalla

				// Agregar el panel de inicio de sesión al marco
				marcoLogin.getContentPane().add(new LoginCargarPartida());
				// Mostrar el marco
				marcoLogin.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(this, "No puedes iniciar mas de una partida", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			}
			;
		}
		// INFORMACIÓN
		if (e.getSource() == boton3) {
			JOptionPane.showMessageDialog(this, "Reglas del juego:\n" + "1. El juego es para un jugador.\n"
					+ "2. Cada jugador tiene 4 acciones por ronda.\n"
					+ "3. Las infecciones se inicializan aleatoriamente en diferentes ciudades al empezar la partida.\n"
					+ "4. Se puede investigar una vacuna utilizando 4 acciones.\n"
					+ "5. Se reduce 1 el nivel de infección si se cura una ciudad sin tener la vacuna desarrollada.\n"
					+ "6. El juego no tiene límite de tiempo, finaliza cuando el jugador gana o pierde.\n\n"
					+ "Información sobre el PANDEMIC:\n"
					+ "Objetivo del juego: Desarrollar vacunas para cuatro enfermedades antes de que se produzca un\n"
					+ "brote global y la humanidad sea aniquilada.\n\n"
					+ "Mecánica del juego: \"Pandemic\" es un juego individual donde el jugador tiene que tomar decisiones\n"
					+ "sobre si curar ciudades o desarrollar vacunas.\n\n"
					+ "Tablero y componentes: El juego se desarrolla en un tablero que representa un mapa del mundo dividido en\n"
					+ "ciudades y regiones. Los jugadores seleccionan ciudades al tablero para curar infecciones o desarrollar vacunas.\n\n"
					+ "Dificultad escalable: \"Pandemic\" ofrece diferentes niveles de dificultad que los jugadores pueden\n"
					+ "ajustar según su experiencia y preferencias.", "Información", JOptionPane.INFORMATION_MESSAGE);

		}
		// AUTORES
		if (e.getSource() == boton5) {
			JOptionPane.showMessageDialog(this,
					"Arnau Medina:  medina.arnau@alumnes.ilerna.com \n"
							+ "David Oltra:  davidoltra1@alumnes.ilerna.com",

					"Autores", JOptionPane.INFORMATION_MESSAGE);
		}
		// RANKING
		if (e.getSource() == boton4) {
		    // Mostrar el ranking
		    String[] b = null; // Declarar b aquí para que esté accesible fuera del bloque try
		    ArrayList<String> puntuaciones = new ArrayList<>(); // Creamos el ArrayList aquí
		    
		    													
		    try (Connection con = DriverManager.getConnection(ControlDatos.URL,
		            ControlDatos.user,  ControlDatos.password)) {
		    	String query = "SELECT PR.* " +
		                "FROM PANDEMIC_RECORDS PR " +
		                "JOIN ( " +
		                "    SELECT MIN(rondas) AS rondas_minimas, dificultad " +
		                "    FROM PANDEMIC_RECORDS " +
		                "    GROUP BY dificultad " +
		                ") min_rondas ON PR.dificultad = min_rondas.dificultad AND PR.rondas = min_rondas.rondas_minimas";



		        String[] a = { "USUARIO", "RONDAS", "FECHA", "RESULTADO", "DIFICULTAD" };

		        b = BBDD.selectArrayList(con, query, a);

		        for (String linea : b) {
		            puntuaciones.add(linea); // Agregar cada línea al ArrayList
		        }

		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(this, "Error al cargar el ranking desde la base de datos", "Error",
		                JOptionPane.ERROR_MESSAGE);
		    }

		    JTextArea textArea = new JTextArea();
		    for (String linea : puntuaciones) {
		        textArea.append(linea + "\n");
		    }
		    textArea.setEditable(false);
		    // Colocar el JTextArea en un JScrollPane para permitir el desplazamiento
		    JScrollPane scrollPane = new JScrollPane(textArea);
		    // Mostrar el mensaje de diálogo con el ranking
		    JOptionPane.showMessageDialog(this, scrollPane, "Ranking", JOptionPane.INFORMATION_MESSAGE);
		}


		// DIFICULTAD
		if (e.getSource() == boton7) {

			if (!LoginCargarPartida.loginExitoso && !Login.registroExitoso && !LoginCargarPartida.loginExitoso) {
				String dificultad = mostrarPopupDificultad();

				ControlDatos.dificulty = dificultad;

				for (int i = 0; i < DatosPartida.ciutats.size(); i++) {

					DatosPartida.ciutats.get(i).infeccion = 0;
				}

				ControlDatos.cargarParametrosInicioCiudades();
				DatosPartida.numEnfermedadesActivas = ControlDatos.numCiudadesInfectadasInicio;
				DatosPartida.randomitzarCiutatsIniciInfectades();
			} else {
				JOptionPane.showMessageDialog(this, "No puedes modificar las opciones una vez la partida ha empezado",
						"Error", JOptionPane.INFORMATION_MESSAGE);
			}

		}

		// SALIR DEL JUEGO
		if (e.getSource() == boton8) {

			System.exit(0);
		}

	}
}
