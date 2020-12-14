package jueguito;

import javax.swing.*;

public class Inicializador extends JComponent {

	private static final long serialVersionUID = 1L;
	public static int anchoVentana = 1058;
	public static int largoVentana = 650;

	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "true");
		JFrame ventana = new JFrame("El Juego del Mate");
		ventana.setIconImage(new ImageIcon("Resources/Imagenes/IconoJuegoMate.jpg").getImage());
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Inicio inicio = new Inicio(anchoVentana, largoVentana, ventana);
		ventana.add(inicio);
		ventana.setVisible(true);		
		ventana.pack();	
		ventana.setLocationRelativeTo(null);
	}
}
