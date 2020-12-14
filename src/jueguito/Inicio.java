package jueguito;

import java.awt.*;
import javax.swing.*;

public class Inicio extends JPanel {

	private static final long serialVersionUID = 1L;
	private int anchoVentana;
	private int largoVentana;
	private Sonido sonido;
	private JFrame ventana;


	public Inicio(int anchoVentana, int largoVentana, JFrame ventana) {
		this.anchoVentana = anchoVentana;
		this.largoVentana = largoVentana;
		this.ventana = ventana;
		cargarSonidos();
		cargarOpciones();
		sonido.tocarSonido("sound");		
	}

	private void cargarSonidos() {
		try {
			sonido = new Sonido();
			sonido.agregarSonido("sound", "Resources/Sonidos/sound.wav");
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(anchoVentana, largoVentana);
	}

	Image img = Toolkit.getDefaultToolkit().getImage("Resources/Imagenes/background.jpg");

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
	
	private void cargarOpciones() {
	
	JButton button = new JButton("Button1");
	button.setText("START");
	button.setForeground(Color.white);
	button.setBackground(Color.green);
	this.add(button);

	// Boton Instrucciones:
	JButton button2 = new JButton("Button2");
	button2.setText("INSTRUCCIONES");
	button2.setForeground(Color.white);
	button2.setBackground(Color.green);
	this.add(button2);

	JButton button3 = new JButton("Button3");
	button3.setText("SALIR");
	button3.setForeground(Color.white);
	button3.setBackground(Color.green);
	this.add(button3);
	ventana.add(this);
	ventana.pack();
	
	// Accion al apretar start
	button.addActionListener(e -> {
		Juego juego = new Juego(anchoVentana, largoVentana);		
		this.setVisible(false);	
		juego.setVisible(true);		
		ventana.add(juego);
		ventana.addKeyListener(juego);
		ventana.setState(Frame.ICONIFIED);
		ventana.setState(Frame.NORMAL);
		Thread thread = new Thread(juego);
		thread.start(); 
			
	});

	// Accion al apretar instrucciones
	button2.addActionListener(e -> {
		Instrucciones instrucciones = new Instrucciones(anchoVentana, largoVentana, this);
		ventana.add(instrucciones);
		this.setVisible(false);
		Thread thread = new Thread(instrucciones);
		thread.start();
	});	
	button3.addActionListener(e -> {
		ventana.dispose();
	});
	}	
}