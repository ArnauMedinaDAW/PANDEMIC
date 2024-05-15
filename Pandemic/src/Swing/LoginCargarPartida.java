package Swing;

import Controlador.*;
import java.awt.*;

import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import Controlador.ControlDatos;

public class LoginCargarPartida extends JPanel implements ActionListener {

	JLabel usuarioLabel;
	JLabel contrasenaLabel;
	JTextField usuarioField;
	JPasswordField contrasenaField;
	JButton botonIniciar;
	public static boolean loginExitoso=false;


	LoginCargarPartida() {
		setLayout(new GridLayout(3, 2));

		usuarioLabel = new JLabel("Usuario:");
		contrasenaLabel = new JLabel("Contraseña:");
		usuarioField = new JTextField();
		contrasenaField = new JPasswordField();
		botonIniciar = new JButton("Iniciar");

		botonIniciar.addActionListener(this);

		add(usuarioLabel);
		add(usuarioField);
		add(contrasenaLabel);
		add(contrasenaField);
		add(botonIniciar);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonIniciar) {
			
			ControlDatos.user_partida = usuarioField.getText();
			String contrasena = String.valueOf(contrasenaField.getPassword());

			// iniciar sesión
			loginExitoso = ControlDatos.iniciarSesion(ControlDatos.user_partida, contrasena);

			if (loginExitoso) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
				marco.dispose(); // Cerrar la ventana de inicio de sesión
				marco.remove(this);
				
				ControlDatos.cargarJuego();
				
	            Marco Juego = new Marco();
						

			} else {
				JOptionPane.showMessageDialog(this, "Inicio de sesión fallido. Verifique sus credenciales.",
						"Inicio de Sesión Fallido", JOptionPane.ERROR_MESSAGE);
			}
		} 
	}
	
	


}
