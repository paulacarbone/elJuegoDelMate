package jueguito;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Instrucciones extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private int anchoVentana, largoVentana, diapositiva=0;
	private List<Image> listaDeImagenes = new ArrayList<>();
	private List<String> listaDeInstrucciones = new ArrayList<>();
	private Inicio inicio;

	public Instrucciones(int anchoVentana, int largoVentana, Inicio inicio) {
	this.anchoVentana = anchoVentana;
	this.largoVentana = largoVentana;
	this.inicio = inicio;
	cargarImagenes();
	cargarInstrucciones();
	}
	
	protected void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, anchoVentana, largoVentana);
		dibujarInstrucciones(g);
	}
	
    private void cargarImagenes() {
		try {
			listaDeImagenes.add(ImageIO.read(new File("Resources/Imagenes/Instruc1.png")));
			listaDeImagenes.add(ImageIO.read(new File("Resources/Imagenes/Instruc2.png")));
			listaDeImagenes.add(ImageIO.read(new File("Resources/Imagenes/Instruc3.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarInstrucciones() {
		listaDeInstrucciones.add("Sos el mate y tenés que juntar yerba, cada una te dará 25 puntos, juntá 500 para ganar");
		listaDeInstrucciones.add("Cada azúcar te sacará 50 puntos, evitalos para no arruinar el mate");
		listaDeInstrucciones.add("El edulcorante es el peor enemigo del mate, si lo agarrás vas a perder una vida");
	}
	
	private void dibujarInstrucciones(Graphics g) {
		if (diapositiva<listaDeImagenes.size()) {
			int anchoString = anchoVentana/2 - (listaDeInstrucciones.get(diapositiva).length())*4;
			g.setColor(Color.green);
			g.setFont(new Font("Impact", 6, 20));
			g.drawString(listaDeInstrucciones.get(diapositiva), anchoString, largoVentana/2-280);
			g.drawImage(listaDeImagenes.get(diapositiva), anchoVentana/2-450, largoVentana/2-260, 900, 520, null);
			diapositiva++;
		}
	}
	
	private void terminarInstrucciones() {
			this.setVisible(false);
			inicio.setVisible(true);
	}
	
	private void reDibujar(){
		this.repaint();
	}

	@Override
	public void run() {
		while (listaDeImagenes.size()>diapositiva) {
			reDibujar();	
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		terminarInstrucciones();
	}
}
