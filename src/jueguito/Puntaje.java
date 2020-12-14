package jueguito;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Puntaje {

	private int posicionX;
	private int posicionY;
	private Font font;
	private Color color;
	private int puntaje;

	public Puntaje(int posicionX, int posicionY, Font font, Color color, int puntaje) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.font = font;
		this.color = color;
		this.puntaje = puntaje;
	}

	public void dibujar(Graphics g) {
		g.setColor(color);
		g.setFont(font);
		if (puntaje <= 999) {
			g.drawString("Tenés: " + String.valueOf(puntaje) + "g de yerba", posicionX, posicionY);

		} else {
			g.drawString("Tenés: " + (Double.valueOf(puntaje) / 1000) + "kg de yerba", posicionX, posicionY);
		}

	}

	public void perderPuntaje() {
		puntaje -= 50;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void ganarPuntaje() {
		puntaje += 25;
	}
}
