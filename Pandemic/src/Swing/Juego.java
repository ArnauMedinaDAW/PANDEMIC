package Swing;

import Model.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.event.*;
import java.sql.*;
import Controlador.*;

public class Juego extends JPanel implements ActionListener {

	public static List frases;// Llista de frases amb info del la partida
	public static JButton[] ciudades;// Array ordenat amb els 48 botons de ciutats
	// Declarar botons
	JButton SanFranciscoButton;
	JButton ChicagoButton;
	JButton AtlantaButton;
	JButton MontrealButton;
	JButton NuevaYorkButton;
	JButton WashingtonButton;
	JButton LondresButton_;
	JButton MadridButton;
	JButton ParisButton;
	JButton EssenButton;
	JButton MilanButton;
	JButton SanPetersburgoButton;
	JButton LosAngelesButton;
	JButton MiamiButton;
	JButton MexicoButton;
	JButton BogotaButton;
	JButton PeruButton;
	JButton SantiagodeChileButton;
	JButton BuenosAiresButton;
	JButton SaoPauloButton;
	JButton LagosButton;
	JButton KinsanaButton;
	JButton JartumButton;
	JButton JohannesburgoButton;
	JButton ArgelButton;
	JButton ElCairoButton;
	JButton RiadButton;
	JButton EstambulButton;
	JButton BagdadButton;
	JButton MoscuButton;
	JButton TeheranButton;
	JButton KarachiButton;
	JButton BombayButton;
	JButton NuevaDelhiButton;
	JButton CalcutaButton;
	JButton MadrasButton;
	JButton YakartaButton;
	JButton BangKokButton;
	JButton HongKongButton;
	JButton ShangaiButton;
	JButton PekinButton;
	JButton SeulButton;
	JButton TokyoButton;
	JButton OsakaButton;
	JButton TaipeiButton;
	JButton HoChiMinhButton;
	JButton ManilaButton;
	JButton SidneyButton;

	JLabel nomCiudad;// Nom de la ciutat seleccionada
	JLabel nomEnfermedad;// Nom de la enfermetat de la ciutat seleccionada

	JLabel lblNuminfeccion;// Nivell de infecció de la ciutat seleccionada
	JTextArea nomColindantes;// Colindants de la ciutat seleccionada
	public static JLabel lblaccionesRestantes;// Accions restants per ronda
	JButton btnCurar;// Botó de curar
	public static Label Brotes;// Brots que es mostren
	int indexCiudad;// Index de la ciutat seleccionada
	public static Label Infectadas;// Numero de ciutats infectades
	public static JProgressBar progressBarAlfa;
	public static JProgressBar progressBarGama;
	public static JProgressBar progressBarBeta;
	public static JProgressBar progressBarDelta;

	boolean btnCiudadSeleccionada = false;// Condició: Per curar o investigar la vacuna s'ha de seleccionar una ciutat

	private JButton btnAlfa;
	private JButton btnGama;
	private JButton btnBeta;
	private JButton btnDelta;

	private JButton guardar;

	public Juego() {

		// Fondo de la interfaç
		JLabel fondoLabel = new JLabel("");
		fondoLabel.setIcon(new ImageIcon("mapaMundo.png"));
		fondoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(fondoLabel);
		fondoLabel.setLayout(new BorderLayout());

		// Creamos un contenedor JPanel para los botones
		JPanel botonPanel = new JPanel();
		botonPanel.setOpaque(false);
		fondoLabel.add(botonPanel, BorderLayout.CENTER);
		botonPanel.setLayout(null);

		// Editar els botons
		SanFranciscoButton = new JButton("");
		SanFranciscoButton.setBounds(195, 182, 20, 20);
		SanFranciscoButton.setBackground(new Color(0, 128, 255));
		SanFranciscoButton.setForeground(Color.BLACK);
		botonPanel.add(SanFranciscoButton);
		SanFranciscoButton.setToolTipText("San Francisco");
		SanFranciscoButton.setVisible(false);
		SanFranciscoButton.addActionListener(this);

		ChicagoButton = new JButton("");
		ChicagoButton.setBounds(308, 153, 20, 20);
		ChicagoButton.setForeground(Color.BLACK);
		ChicagoButton.setBackground(new Color(0, 128, 255));
		ChicagoButton.setToolTipText("Chicago");
		ChicagoButton.addActionListener(this);
		ChicagoButton.setVisible(false);
		botonPanel.add(ChicagoButton);

		AtlantaButton = new JButton("");
		AtlantaButton.setBounds(318, 182, 20, 20);
		AtlantaButton.setForeground(Color.BLACK);
		AtlantaButton.setBackground(new Color(0, 128, 255));
		AtlantaButton.setToolTipText("Atlanta");
		AtlantaButton.addActionListener(this);
		AtlantaButton.setVisible(false);
		botonPanel.add(AtlantaButton);

		MontrealButton = new JButton("");
		MontrealButton.setBounds(351, 130, 20, 20);
		MontrealButton.setForeground(Color.BLACK);
		MontrealButton.setBackground(new Color(0, 128, 255));
		MontrealButton.setToolTipText("Montreal");
		MontrealButton.addActionListener(this);
		MontrealButton.setVisible(false);
		botonPanel.add(MontrealButton);

		NuevaYorkButton = new JButton("");
		NuevaYorkButton.setBounds(377, 163, 20, 20);
		NuevaYorkButton.setForeground(Color.BLACK);
		NuevaYorkButton.setBackground(new Color(0, 128, 255));
		NuevaYorkButton.setToolTipText("Nueva York");
		NuevaYorkButton.addActionListener(this);
		NuevaYorkButton.setVisible(false);
		botonPanel.add(NuevaYorkButton);

		WashingtonButton = new JButton("");
		WashingtonButton.setForeground(Color.BLACK);
		WashingtonButton.setBackground(new Color(0, 128, 255));
		WashingtonButton.setBounds(350, 182, 20, 20);
		WashingtonButton.setToolTipText("Washington");
		WashingtonButton.addActionListener(this);
		WashingtonButton.setVisible(false);
		botonPanel.add(WashingtonButton);

		LondresButton_ = new JButton("");
		LondresButton_.setForeground(Color.BLACK);
		LondresButton_.setBackground(new Color(0, 128, 255));
		LondresButton_.setBounds(626, 116, 20, 20);
		LondresButton_.setToolTipText("Londres");
		LondresButton_.addActionListener(this);
		LondresButton_.setVisible(false);
		botonPanel.add(LondresButton_);

		MadridButton = new JButton("");
		MadridButton.setForeground(Color.BLACK);
		MadridButton.setBackground(new Color(0, 128, 255));
		MadridButton.setBounds(613, 163, 20, 20);
		MadridButton.setToolTipText("Madrid");
		MadridButton.addActionListener(this);
		MadridButton.setVisible(false);
		botonPanel.add(MadridButton);

		ParisButton = new JButton("");
		ParisButton.setForeground(Color.BLACK);
		ParisButton.setBackground(new Color(0, 128, 255));
		ParisButton.setBounds(636, 139, 20, 20);
		ParisButton.setToolTipText("París");
		ParisButton.addActionListener(this);
		ParisButton.setVisible(false);
		botonPanel.add(ParisButton);

		EssenButton = new JButton("");
		EssenButton.setForeground(Color.BLACK);
		EssenButton.setBackground(new Color(0, 128, 255));
		EssenButton.setBounds(662, 130, 20, 20);
		EssenButton.setToolTipText("Essen");
		EssenButton.addActionListener(this);
		EssenButton.setVisible(false);
		botonPanel.add(EssenButton);

		MilanButton = new JButton("");
		MilanButton.setForeground(Color.BLACK);
		MilanButton.setBackground(new Color(0, 128, 255));
		MilanButton.setBounds(672, 153, 20, 20);
		MilanButton.setToolTipText("Milán");
		MilanButton.addActionListener(this);
		MilanButton.setVisible(false);
		botonPanel.add(MilanButton);

		SanPetersburgoButton = new JButton("");
		SanPetersburgoButton.setForeground(Color.BLACK);
		SanPetersburgoButton.setBackground(new Color(0, 128, 255));
		SanPetersburgoButton.setBounds(700, 116, 20, 20);
		SanPetersburgoButton.setToolTipText("San Petersburgo");
		SanPetersburgoButton.addActionListener(this);
		SanPetersburgoButton.setVisible(false);
		botonPanel.add(SanPetersburgoButton);

		LosAngelesButton = new JButton("");
		LosAngelesButton.setForeground(Color.BLACK);
		LosAngelesButton.setBackground(Color.YELLOW);
		LosAngelesButton.setBounds(215, 204, 20, 20);
		LosAngelesButton.setToolTipText("Los Angeles");
		LosAngelesButton.addActionListener(this);
		LosAngelesButton.setVisible(false);
		botonPanel.add(LosAngelesButton);

		MiamiButton = new JButton("");
		MiamiButton.setFont(new Font("Tahoma", Font.PLAIN, 6));
		MiamiButton.setForeground(Color.BLACK);
		MiamiButton.setBackground(Color.YELLOW);
		MiamiButton.setBounds(351, 209, 20, 20);
		MiamiButton.setToolTipText("Miami");
		MiamiButton.addActionListener(this);
		MiamiButton.setVisible(false);
		botonPanel.add(MiamiButton);

		MexicoButton = new JButton("");
		MexicoButton.setForeground(Color.BLACK);
		MexicoButton.setBackground(Color.YELLOW);
		MexicoButton.setBounds(284, 244, 20, 20);
		MexicoButton.setToolTipText("Mexico");
		MexicoButton.addActionListener(this);
		MexicoButton.setVisible(false);
		botonPanel.add(MexicoButton);

		BogotaButton = new JButton("");
		BogotaButton.setForeground(Color.BLACK);
		BogotaButton.setBackground(Color.YELLOW);
		BogotaButton.setBounds(363, 290, 20, 20);
		BogotaButton.setToolTipText("Bogota");
		BogotaButton.setVisible(false);
		botonPanel.add(BogotaButton);
		BogotaButton.addActionListener(this);

		PeruButton = new JButton("");
		PeruButton.setForeground(Color.BLACK);
		PeruButton.setBackground(Color.YELLOW);
		PeruButton.setBounds(351, 336, 20, 20);
		PeruButton.setToolTipText("Peru");
		botonPanel.add(PeruButton);
		PeruButton.setVisible(false);
		PeruButton.addActionListener(this);

		SantiagodeChileButton = new JButton("");
		SantiagodeChileButton.setForeground(Color.BLACK);
		SantiagodeChileButton.setBackground(Color.YELLOW);
		SantiagodeChileButton.setBounds(377, 415, 20, 20);
		SantiagodeChileButton.setToolTipText("Santiago de Chile");
		botonPanel.add(SantiagodeChileButton);
		SantiagodeChileButton.setVisible(false);
		SantiagodeChileButton.addActionListener(this);

		BuenosAiresButton = new JButton("");
		BuenosAiresButton.setForeground(Color.BLACK);
		BuenosAiresButton.setBackground(Color.YELLOW);
		BuenosAiresButton.setBounds(420, 431, 20, 20);
		BuenosAiresButton.setToolTipText("Buenos Aires");
		botonPanel.add(BuenosAiresButton);
		BuenosAiresButton.setVisible(false);
		BuenosAiresButton.addActionListener(this);

		SaoPauloButton = new JButton("");
		SaoPauloButton.setForeground(Color.BLACK);
		SaoPauloButton.setBackground(Color.YELLOW);
		SaoPauloButton.setBounds(458, 400, 20, 20);
		SaoPauloButton.setToolTipText("Sao Paulo");
		botonPanel.add(SaoPauloButton);
		SaoPauloButton.setVisible(false);
		SaoPauloButton.addActionListener(this);

		LagosButton = new JButton("");
		LagosButton.setForeground(Color.BLACK);
		LagosButton.setBackground(Color.YELLOW);
		LagosButton.setBounds(636, 290, 20, 20);
		LagosButton.setToolTipText("Lagos");
		botonPanel.add(LagosButton);
		LagosButton.setVisible(false);
		LagosButton.addActionListener(this);

		KinsanaButton = new JButton("");
		KinsanaButton.setForeground(Color.BLACK);
		KinsanaButton.setBackground(Color.YELLOW);
		KinsanaButton.setBounds(687, 336, 20, 20);
		KinsanaButton.setToolTipText("Kinsana");
		botonPanel.add(KinsanaButton);
		KinsanaButton.setVisible(false);
		KinsanaButton.addActionListener(this);

		JartumButton = new JButton("");
		JartumButton.setForeground(Color.BLACK);
		JartumButton.setBackground(Color.YELLOW);
		JartumButton.setBounds(748, 255, 20, 20);
		JartumButton.setToolTipText("Jartum");
		botonPanel.add(JartumButton);
		JartumButton.setVisible(false);
		JartumButton.addActionListener(this);

		JohannesburgoButton = new JButton("");
		JohannesburgoButton.setForeground(Color.BLACK);
		JohannesburgoButton.setBackground(Color.YELLOW);
		JohannesburgoButton.setBounds(732, 381, 20, 20);
		JohannesburgoButton.setToolTipText("Johannesburgo");
		botonPanel.add(JohannesburgoButton);
		JohannesburgoButton.setVisible(false);
		JohannesburgoButton.addActionListener(this);

		ArgelButton = new JButton("");
		ArgelButton.setForeground(Color.BLACK);
		ArgelButton.setBackground(Color.GREEN);
		ArgelButton.setBounds(650, 182, 20, 20);
		ArgelButton.setToolTipText("Argel");
		botonPanel.add(ArgelButton);
		ArgelButton.setVisible(false);
		ArgelButton.addActionListener(this);

		ElCairoButton = new JButton("");
		ElCairoButton.setForeground(Color.BLACK);
		ElCairoButton.setBackground(Color.GREEN);
		ElCairoButton.setBounds(732, 204, 20, 20);
		ElCairoButton.setToolTipText("El Cairo");
		botonPanel.add(ElCairoButton);
		ElCairoButton.setVisible(false);
		ElCairoButton.addActionListener(this);

		RiadButton = new JButton("");
		RiadButton.setForeground(Color.BLACK);
		RiadButton.setBackground(Color.GREEN);
		RiadButton.setBounds(794, 219, 20, 20);
		RiadButton.setToolTipText("Riad");
		botonPanel.add(RiadButton);
		RiadButton.setVisible(false);
		RiadButton.addActionListener(this);

		EstambulButton = new JButton("");
		EstambulButton.setForeground(Color.BLACK);
		EstambulButton.setBackground(Color.GREEN);
		EstambulButton.setBounds(732, 163, 20, 20);
		EstambulButton.setToolTipText("Estambul");
		botonPanel.add(EstambulButton);
		EstambulButton.setVisible(false);
		EstambulButton.addActionListener(this);

		BagdadButton = new JButton("");
		BagdadButton.setForeground(Color.BLACK);
		BagdadButton.setBackground(Color.GREEN);
		BagdadButton.setBounds(783, 182, 20, 20);
		BagdadButton.setToolTipText("Bagdad");
		botonPanel.add(BagdadButton);
		BagdadButton.setVisible(false);
		BagdadButton.addActionListener(this);

		MoscuButton = new JButton("");
		MoscuButton.setForeground(Color.BLACK);
		MoscuButton.setBackground(Color.GREEN);
		MoscuButton.setBounds(759, 116, 20, 20);
		MoscuButton.setToolTipText("Moscu");
		botonPanel.add(MoscuButton);
		MoscuButton.setVisible(false);
		MoscuButton.addActionListener(this);

		TeheranButton = new JButton("");
		TeheranButton.setForeground(Color.BLACK);
		TeheranButton.setBackground(Color.GREEN);
		TeheranButton.setBounds(823, 182, 20, 20);
		TeheranButton.setToolTipText("Teheran");
		botonPanel.add(TeheranButton);
		TeheranButton.setVisible(false);
		TeheranButton.addActionListener(this);

		KarachiButton = new JButton("");
		KarachiButton.setForeground(Color.BLACK);
		KarachiButton.setBackground(Color.GREEN);
		KarachiButton.setBounds(860, 219, 20, 20);
		KarachiButton.setToolTipText("Karachi");
		botonPanel.add(KarachiButton);
		KarachiButton.setVisible(false);
		KarachiButton.addActionListener(this);

		BombayButton = new JButton("");
		BombayButton.setForeground(Color.BLACK);
		BombayButton.setBackground(Color.GREEN);
		BombayButton.setBounds(887, 244, 20, 20);
		BombayButton.setToolTipText("Bombay");
		botonPanel.add(BombayButton);
		BombayButton.setVisible(false);
		BombayButton.addActionListener(this);

		NuevaDelhiButton = new JButton("");
		NuevaDelhiButton.setForeground(Color.BLACK);
		NuevaDelhiButton.setBackground(Color.GREEN);
		NuevaDelhiButton.setBounds(902, 204, 20, 20);
		NuevaDelhiButton.setToolTipText("Nueva Delhi");
		botonPanel.add(NuevaDelhiButton);
		NuevaDelhiButton.setVisible(false);
		NuevaDelhiButton.addActionListener(this);

		CalcutaButton = new JButton("");
		CalcutaButton.setForeground(Color.BLACK);
		CalcutaButton.setBackground(Color.GREEN);
		CalcutaButton.setBounds(940, 231, 20, 20);
		CalcutaButton.setToolTipText("Calcuta");
		botonPanel.add(CalcutaButton);
		CalcutaButton.setVisible(false);
		CalcutaButton.addActionListener(this);

		MadrasButton = new JButton("");
		MadrasButton.setForeground(Color.BLACK);
		MadrasButton.setBackground(Color.GREEN);
		MadrasButton.setBounds(920, 265, 20, 20);
		MadrasButton.setToolTipText("Madras");
		botonPanel.add(MadrasButton);
		MadrasButton.setVisible(false);
		MadrasButton.addActionListener(this);

		YakartaButton = new JButton("");
		YakartaButton.setForeground(Color.BLACK);
		YakartaButton.setBackground(Color.RED);
		YakartaButton.setBounds(1005, 325, 20, 20);
		YakartaButton.setToolTipText("Yakarta");
		botonPanel.add(YakartaButton);
		YakartaButton.setVisible(false);
		YakartaButton.addActionListener(this);

		BangKokButton = new JButton("");
		BangKokButton.setForeground(Color.BLACK);
		BangKokButton.setBackground(Color.RED);
		BangKokButton.setBounds(988, 265, 20, 20);
		BangKokButton.setToolTipText("Bangkok");
		botonPanel.add(BangKokButton);
		BangKokButton.setVisible(false);
		BangKokButton.addActionListener(this);

		HongKongButton = new JButton("");
		HongKongButton.setForeground(Color.BLACK);
		HongKongButton.setBackground(Color.RED);
		HongKongButton.setBounds(1029, 231, 20, 20);
		HongKongButton.setToolTipText("Hong Kong");
		botonPanel.add(HongKongButton);
		HongKongButton.setVisible(false);
		HongKongButton.addActionListener(this);

		ShangaiButton = new JButton("");
		ShangaiButton.setForeground(Color.BLACK);
		ShangaiButton.setBackground(Color.RED);
		ShangaiButton.setBounds(1062, 204, 20, 20);
		ShangaiButton.setToolTipText("Shangai");
		botonPanel.add(ShangaiButton);
		ShangaiButton.setVisible(false);
		ShangaiButton.addActionListener(this);

		PekinButton = new JButton("");
		PekinButton.setForeground(Color.BLACK);
		PekinButton.setBackground(Color.RED);
		PekinButton.setBounds(1049, 182, 20, 20);
		PekinButton.setToolTipText("Pekin");
		botonPanel.add(PekinButton);
		PekinButton.setVisible(false);
		PekinButton.addActionListener(this);

		SeulButton = new JButton("");
		SeulButton.setForeground(Color.BLACK);
		SeulButton.setBackground(Color.RED);
		SeulButton.setBounds(1083, 182, 20, 20);
		SeulButton.setToolTipText("Seul");
		botonPanel.add(SeulButton);
		SeulButton.setVisible(false);
		SeulButton.addActionListener(this);

		TokyoButton = new JButton("");
		TokyoButton.setForeground(Color.BLACK);
		TokyoButton.setBackground(Color.RED);
		TokyoButton.setBounds(1134, 182, 20, 20);
		TokyoButton.setToolTipText("Tokyo");
		botonPanel.add(TokyoButton);
		TokyoButton.setVisible(false);
		TokyoButton.addActionListener(this);

		OsakaButton = new JButton("");
		OsakaButton.setForeground(Color.BLACK);
		OsakaButton.setBackground(Color.RED);
		OsakaButton.setBounds(1112, 192, 20, 20);
		OsakaButton.setToolTipText("Osaka");
		botonPanel.add(OsakaButton);
		OsakaButton.setVisible(false);
		OsakaButton.addActionListener(this);

		TaipeiButton = new JButton("");
		TaipeiButton.setForeground(Color.BLACK);
		TaipeiButton.setBackground(Color.RED);
		TaipeiButton.setBounds(1072, 231, 20, 20);
		TaipeiButton.setToolTipText("Taipei");
		botonPanel.add(TaipeiButton);
		TaipeiButton.setVisible(false);
		TaipeiButton.addActionListener(this);

		HoChiMinhButton = new JButton("");
		HoChiMinhButton.setForeground(Color.BLACK);
		HoChiMinhButton.setBackground(Color.RED);
		HoChiMinhButton.setBounds(1018, 278, 20, 20);
		HoChiMinhButton.setToolTipText("Ho Chi Minh");
		botonPanel.add(HoChiMinhButton);
		HoChiMinhButton.setVisible(false);
		HoChiMinhButton.addActionListener(this);

		ManilaButton = new JButton("");
		ManilaButton.setForeground(Color.BLACK);
		ManilaButton.setBackground(Color.RED);
		ManilaButton.setBounds(1062, 265, 20, 20);
		ManilaButton.setToolTipText("Manila");
		botonPanel.add(ManilaButton);
		ManilaButton.setVisible(false);
		ManilaButton.addActionListener(this);

		SidneyButton = new JButton("");
		SidneyButton.setForeground(Color.BLACK);
		SidneyButton.setBackground(Color.RED);
		SidneyButton.setBounds(1169, 431, 20, 20);
		SidneyButton.setToolTipText("Sidney");
		botonPanel.add(SidneyButton);
		SidneyButton.setVisible(false);
		SidneyButton.addActionListener(this);

		// Afegir els botons editats al array per indexar-los
		ciudades = new JButton[48];
		ciudades[0] = SanFranciscoButton;
		ciudades[1] = ChicagoButton;
		ciudades[2] = AtlantaButton;
		ciudades[3] = MontrealButton;
		ciudades[4] = NuevaYorkButton;
		ciudades[5] = WashingtonButton;
		ciudades[6] = LondresButton_;
		ciudades[7] = MadridButton;
		ciudades[8] = ParisButton;
		ciudades[9] = EssenButton;
		ciudades[10] = MilanButton;
		ciudades[11] = SanPetersburgoButton;
		ciudades[12] = LosAngelesButton;
		ciudades[13] = MiamiButton;
		ciudades[14] = MexicoButton;
		ciudades[15] = BogotaButton;
		ciudades[16] = PeruButton;
		ciudades[17] = SantiagodeChileButton;
		ciudades[18] = BuenosAiresButton;
		ciudades[19] = SaoPauloButton;
		ciudades[20] = LagosButton;
		ciudades[21] = KinsanaButton;
		ciudades[22] = JartumButton;
		ciudades[23] = JohannesburgoButton;
		ciudades[24] = ArgelButton;
		ciudades[25] = ElCairoButton;
		ciudades[26] = RiadButton;
		ciudades[27] = EstambulButton;
		ciudades[28] = BagdadButton;
		ciudades[29] = MoscuButton;
		ciudades[30] = TeheranButton;
		ciudades[31] = KarachiButton;
		ciudades[32] = BombayButton;
		ciudades[33] = NuevaDelhiButton;
		ciudades[34] = CalcutaButton;
		ciudades[35] = MadrasButton;
		ciudades[36] = YakartaButton;
		ciudades[37] = BangKokButton;
		ciudades[38] = HongKongButton;
		ciudades[39] = ShangaiButton;
		ciudades[40] = PekinButton;
		ciudades[41] = SeulButton;
		ciudades[42] = TokyoButton;
		ciudades[43] = OsakaButton;
		ciudades[44] = TaipeiButton;
		ciudades[45] = HoChiMinhButton;
		ciudades[46] = ManilaButton;
		ciudades[47] = SidneyButton;

		// JPanel on es mostra info
		JPanel panel = new JPanel();
		panel.setBounds(702, 431, 384, 205);
		botonPanel.add(panel);
		panel.setLayout(null);
		panel.setOpaque(false);

		JLabel lblNewLabel = new JLabel("Ciudad: ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 16, 50, 24);
		panel.add(lblNewLabel);

		JLabel lblEnfermedad = new JLabel("Enfermedad: ");
		lblEnfermedad.setForeground(Color.WHITE);
		lblEnfermedad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnfermedad.setBounds(10, 46, 89, 24);
		panel.add(lblEnfermedad);

		JLabel lblInfeccion = new JLabel("Infeccion: ");
		lblInfeccion.setForeground(Color.WHITE);
		lblInfeccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInfeccion.setBounds(10, 81, 66, 24);
		panel.add(lblInfeccion);

		JLabel lblColindantes = new JLabel("Colindantes: ");
		lblColindantes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblColindantes.setForeground(Color.WHITE);
		lblColindantes.setBounds(10, 116, 89, 30);
		panel.add(lblColindantes);

		nomCiudad = new JLabel("");
		nomCiudad.setBackground(new Color(255, 255, 255));
		nomCiudad.setFont(new Font("Tahoma", Font.BOLD, 11));
		nomCiudad.setForeground(Color.WHITE);
		nomCiudad.setBounds(139, 16, 118, 14);
		panel.add(nomCiudad);

		nomEnfermedad = new JLabel("");
		nomEnfermedad.setFont(new Font("Tahoma", Font.BOLD, 11));
		nomEnfermedad.setForeground(Color.WHITE);
		nomEnfermedad.setBounds(139, 51, 118, 14);
		panel.add(nomEnfermedad);

		lblNuminfeccion = new JLabel("");
		lblNuminfeccion.setForeground(Color.WHITE);
		lblNuminfeccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNuminfeccion.setBounds(139, 86, 98, 14);
		panel.add(lblNuminfeccion);

		nomColindantes = new JTextArea("");
		nomColindantes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		nomColindantes.setForeground(Color.WHITE);
		nomColindantes.setEditable(false);
		nomColindantes.setBounds(139, 108, 140, 86);
		panel.add(nomColindantes);
		nomColindantes.setOpaque(false);

		btnCurar = new JButton("CURAR");
		btnCurar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCurar.setForeground(new Color(255, 255, 255));
		btnCurar.setBackground(new Color(0, 0, 205));
		// btnCurar.setEnabled(false); // Desactivado inicialmente
		btnCurar.setBounds(267, 47, 113, 23);
		btnCurar.addActionListener(this);
		panel.add(btnCurar);

		JLabel lblNewLabel_1 = new JLabel("ACCIONES");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(289, 116, 66, 14);
		panel.add(lblNewLabel_1);

		lblaccionesRestantes = new JLabel(String.valueOf(DatosPartida.acciones));
		lblaccionesRestantes.setForeground(Color.WHITE);
		lblaccionesRestantes.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblaccionesRestantes.setBounds(309, 134, 50, 28);
		panel.add(lblaccionesRestantes);

		Brotes = new Label(String.valueOf(DatosPartida.brotes));
		Brotes.setBackground(new Color(0, 0, 128));
		Brotes.setForeground(new Color(255, 255, 255));
		Brotes.setBounds(198, 51, 20, 22);
		botonPanel.add(Brotes);
		Brotes.setFont(new Font("Tahoma", Font.BOLD, 20));

		Label lblBrotes = new Label("BROTES");
		lblBrotes.setBackground(new Color(0, 0, 128));
		lblBrotes.setForeground(new Color(255, 255, 255));
		lblBrotes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBrotes.setBounds(65, 51, 95, 22);
		botonPanel.add(lblBrotes);

		Label lblInfectadas = new Label("INFECTADAS");
		lblInfectadas.setBackground(new Color(0, 0, 128));
		lblInfectadas.setForeground(new Color(255, 255, 255));
		lblInfectadas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblInfectadas.setBounds(64, 89, 117, 22);
		botonPanel.add(lblInfectadas);

		Infectadas = new Label(String.valueOf(DatosPartida.numEnfermedadesActivas));
		Infectadas.setBackground(new Color(0, 0, 128));
		Infectadas.setForeground(new Color(255, 255, 255));
		Infectadas.setFont(new Font("Tahoma", Font.BOLD, 20));
		Infectadas.setBounds(198, 89, 37, 22);
		botonPanel.add(Infectadas);

		frases = new List();
		frases.setForeground(new Color(255, 255, 255));
		frases.setFont(new Font("Arial", Font.BOLD, 12));
		frases.setBackground(new Color(0, 0, 139));
		botonPanel.add(frases);

		frases.setBounds(24, 494, 373, 153);
		frases.add("RONDA " + DatosPartida.rondas);

		// Barres de progrés de les vacunes
		progressBarAlfa = new JProgressBar();
		progressBarAlfa.setBounds(37, 314, 146, 14);
		progressBarAlfa.setMinimum(0); // Valor mínimo de la barra de progreso
		progressBarAlfa.setMaximum(100); // Valor máximo de la barra de progreso
		progressBarAlfa.setValue(0); // Valor inicial de la barra de progreso
		progressBarAlfa.setForeground(Color.BLUE); // Color de la barra de progreso
		botonPanel.add(progressBarAlfa);

		progressBarBeta = new JProgressBar();
		progressBarBeta.setForeground(new Color(255, 0, 0));
		progressBarBeta.setMinimum(0); // Valor mínimo de la barra de progreso
		progressBarBeta.setMaximum(100); // Valor máximo de la barra de progreso
		progressBarBeta.setValue(0); // Valor inicial de la barra de progreso
		progressBarBeta.setForeground(Color.RED); // Color de la barra de progreso
		progressBarBeta.setBounds(37, 349, 146, 14);
		botonPanel.add(progressBarBeta);

		progressBarGama = new JProgressBar();
		progressBarGama.setForeground(new Color(128, 255, 128));
		progressBarGama.setBounds(37, 384, 146, 14);
		progressBarGama.setMinimum(0); // Valor mínimo de la barra de progreso
		progressBarGama.setMaximum(100); // Valor máximo de la barra de progreso
		progressBarGama.setValue(0); // Valor inicial de la barra de progreso
		progressBarGama.setForeground(Color.GREEN); // Color de la barra de progreso
		botonPanel.add(progressBarGama);

		progressBarDelta = new JProgressBar();
		progressBarDelta.setForeground(new Color(255, 255, 128));
		progressBarDelta.setBounds(37, 419, 146, 14);
		progressBarDelta.setMinimum(0); // Valor mínimo de la barra de progreso
		progressBarDelta.setMaximum(100); // Valor máximo de la barra de progreso
		progressBarDelta.setValue(0); // Valor inicial de la barra de progreso
		progressBarDelta.setForeground(Color.YELLOW); // Color de la barra de progreso
		botonPanel.add(progressBarDelta);

		btnAlfa = new JButton("Alfa");
		btnAlfa.setBackground(new Color(0, 128, 255));
		btnAlfa.setForeground(new Color(255, 255, 255));
		btnAlfa.setBounds(195, 305, 76, 23);
		btnAlfa.addActionListener(this);
		botonPanel.add(btnAlfa);

		btnBeta = new JButton("Beta");
		btnBeta.setForeground(new Color(255, 255, 255));
		btnBeta.setBackground(Color.RED);
		btnBeta.setBounds(195, 340, 76, 23);
		btnBeta.addActionListener(this);
		botonPanel.add(btnBeta);

		btnGama = new JButton("Gama");
		btnGama.setForeground(new Color(255, 255, 255));
		btnGama.setBackground(Color.GREEN);
		btnGama.setBounds(195, 378, 76, 23);
		btnGama.addActionListener(this);
		botonPanel.add(btnGama);

		btnDelta = new JButton("Delta");
		btnDelta.setBackground(new Color(255, 204, 0));
		btnDelta.setForeground(new Color(255, 255, 255));
		btnDelta.setBounds(195, 412, 76, 23);
		btnDelta.addActionListener(this);
		botonPanel.add(btnDelta);

		guardar = new JButton("Guardar y Salir");
		guardar.setForeground(new Color(255, 255, 255));
		guardar.setBackground(new Color(0, 0, 128));
		guardar.setBounds(1112, 11, 123, 23);
		guardar.addActionListener(this);
		botonPanel.add(guardar);

		// HACER VISIBLES LAS CIUDADES INFECTADAS EN UN INICIO
		ciudadesInfectadasInicio();

		// Audio
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

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(guardar)) {

			ControlDatos.guardarEstadoCiudades();
			System.exit(0);

		}
		if (ControlPartida.seguirJuego) {
			int indexVacuna = 0;
			// Trobar l'objecte ciutat que s'ha seleccionat
			for (int i = 0; i < ciudades.length; i++) {

				if (e.getSource().equals(ciudades[i])) {

					nomEnfermedad.setText(DatosPartida.ciutats.get(i).enfermedad);
					nomCiudad.setText(DatosPartida.ciutats.get(i).nombre);
					lblNuminfeccion.setText(String.valueOf(DatosPartida.ciutats.get(i).infeccion));
					lblaccionesRestantes.setText(String.valueOf(DatosPartida.acciones));
					nomColindantes.setText("");
					for (int j = 0; j < DatosPartida.ciutats.get(i).ciudadesColindantes.length; j++) {

						nomColindantes.append(DatosPartida.ciutats.get(i).ciudadesColindantes[j] + "\n");
					}
					btnCiudadSeleccionada = true;
					indexCiudad = i;
					// Trobar l'objecte vacuna de la enfermetat
					indexVacuna = trobarObjecteVacuna();
				}
			}
			// Verificar si se ha seleccionado un botón de ciudad antes de permitir curar
			if (btnCiudadSeleccionada) {

				if (e.getSource().equals(btnCurar)) {// CURAR
					curarCiudad(indexVacuna);
				}
			}
			if (e.getSource().equals(btnAlfa)) {
				indexVacuna = 0;
				desarrollarVacuna(indexVacuna);
			}
			if (e.getSource().equals(btnBeta)) {
				indexVacuna = 1;
				desarrollarVacuna(indexVacuna);
			}
			if (e.getSource().equals(btnGama)) {
				indexVacuna = 2;
				desarrollarVacuna(indexVacuna);
			}
			if (e.getSource().equals(btnDelta)) {
				indexVacuna = 3;
				desarrollarVacuna(indexVacuna);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Has perdido", "Resumen", JOptionPane.INFORMATION_MESSAGE);
		}
		if (ControlPartida.victoria) {
			JOptionPane.showMessageDialog(this, "Has ganado!", "Resumen", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	// Funció per trobar l'Objecte Vacuna
	public int trobarObjecteVacuna() {

		int index = 0;
		String textoNomEnfermedad = nomEnfermedad.getText();
		for (int i = 0; i < DatosPartida.vacunas.size(); i++) {
			// Trobar la vacuna a modificar
			if (textoNomEnfermedad.equals(DatosPartida.vacunas.get(i).nombre)) {

				index = i;
			}
		}
		return index;
	}

	public static void numRonda() {
		frases.add("RONDA " + DatosPartida.rondas);
	}

	public static void ciudadesInfectadasInicio() {

		for (int i = 0; i < DatosPartida.ciutats.size(); i++) {

			if (DatosPartida.ciutats.get(i).infeccion == 1) {

				Juego.ciudades[i].setVisible(true);
				frases.add(DatosPartida.ciutats.get(i).getNombre() + " nivel " + DatosPartida.ciutats.get(i).getInfeccion() );
			}
		
//			ciudades[i].setText("1");
//			ciudades[i].setForeground(Color.BLACK);
////			int tamañoFuente = 3; 
////			Font fuente = new Font("Arial", Font.PLAIN, tamañoFuente); 
////			ciudades[i].setFont(fuente);
			
		
		
		}
	}

	public void curarCiudad(int indexVacuna) {
		if (DatosPartida.ciutats.get(indexCiudad).infeccion != 0) {// Curar valid si la ciutat esta infectada

			// Si la vacuna no esta completada
			if (DatosPartida.vacunas.get(indexVacuna).porcentaje < 100) {

				DatosPartida.ciutats.get(indexCiudad).infeccion--;
				lblNuminfeccion.setText(String.valueOf(DatosPartida.ciutats.get(indexCiudad).infeccion));
				
				
				// Si la infecció es redueix a 0
				if (DatosPartida.ciutats.get(indexCiudad).infeccion == 0) {

					DatosPartida.numEnfermedadesActivas--;
					Infectadas.setText(String.valueOf(DatosPartida.numEnfermedadesActivas));
					ControlPartida.ciudadesVisibles();

				}
				DatosPartida.acciones--;
				lblaccionesRestantes.setText(String.valueOf(DatosPartida.acciones));

				frases.add("Ciudad " + DatosPartida.ciutats.get(indexCiudad).nombre + " curada");
				// Si la vacuna esta completada
			} else if (DatosPartida.vacunas.get(indexVacuna).porcentaje == 100) {

				DatosPartida.ciutats.get(indexCiudad).infeccion = 0;
				lblNuminfeccion.setText(String.valueOf(DatosPartida.ciutats.get(indexCiudad).infeccion));

				DatosPartida.numEnfermedadesActivas--;
				Infectadas.setText(String.valueOf(DatosPartida.numEnfermedadesActivas));

				DatosPartida.acciones--;
				lblaccionesRestantes.setText(String.valueOf(DatosPartida.acciones));

				frases.add("Ciudad " + DatosPartida.ciutats.get(indexCiudad).nombre + " curada");
				ControlPartida.ciudadesVisibles();
			}
			// GESTIONAR FIN DE TURNO------------------------------------------
			if (DatosPartida.acciones == 0) {
				DatosPartida.rondas++;
				numRonda();
				ControlPartida.gestionarTurno();
			}
		} else {// Misatge de error, la ciutat no esta infectada
			JOptionPane.showMessageDialog(this,
					"La ciudad " + DatosPartida.ciutats.get(indexCiudad).nombre + " no está infectada", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
		btnCiudadSeleccionada = false;

	}

	public void desarrollarVacuna(int indexVacuna) {

		if (DatosPartida.acciones == 4) {// DESARROLLAR
			if (DatosPartida.vacunas.get(indexVacuna).porcentaje != 100) {
				// Accio de "Desarrollar"
				DatosPartida.acciones = 0;
				lblaccionesRestantes.setText(String.valueOf(DatosPartida.acciones));
				// Aumentar percentatge vacuna
				DatosPartida.vacunas.get(indexVacuna).desarrollarVacuna(ControlDatos.pDesarrollo);

				frases.add("Vacuna " + DatosPartida.vacunas.get(indexVacuna).nombre + " "
						+ DatosPartida.vacunas.get(indexVacuna).porcentaje + "%");
				// GESTIONAR FIN DE TURNO------------------------------------------
				if (DatosPartida.acciones == 0) {
					DatosPartida.rondas++;
					numRonda();
					ControlPartida.gestionarTurno();
				}
			} else {// Misatge de error, ja s'ha obtingut la cura
				JOptionPane.showMessageDialog(this,
						"Ya tienes la cura para la enfermedad " + DatosPartida.vacunas.get(indexVacuna).nombre, "Error",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {// ERROR, no hi han suficients Accions
			JOptionPane.showMessageDialog(this, "No tienes suficientes acciones", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
