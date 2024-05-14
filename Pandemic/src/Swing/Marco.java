package Swing;

import java.awt.*;

import javax.swing.*;

import Model.DatosPartida;

public class Marco extends JFrame {

	PanelPrincipal lamina1;
	Juego lamina2;
	Dimension screenSize;

	public Marco() {

		if (!Login.loginExitoso && !Login.registroExitoso) {
			generarMenu();
		}
		if (Login.loginExitoso || Login.registroExitoso) {
			generarJuego();
		}

	}

	public void generarMenu() {
		// Establecer el tamaño de la ventana
		setSize(800, 600);
		// Centrar en pantalla después de establecer el tamaño
		setLocationRelativeTo(null);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pandemic");

		lamina1 = new PanelPrincipal();
		add(lamina1);
		setVisible(true);

	}

	public void generarJuego() {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pandemic");

		lamina2 = new Juego();
		add(lamina2);

		// Establecer el tamaño de la ventana
		setSize(1250, 687);
		// Centrar en pantalla después de establecer el tamaño
		setLocationRelativeTo(null);

		setVisible(true);
		//lamina1.setVisible(false);

	}
}
