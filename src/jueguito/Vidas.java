package jueguito;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Vidas {

	private int posicionX;
	private int posicionY;
	private Font font;
	private Color color;
	private int vidas;

	public Vidas(int posicionX, int posicionY, Font font, Color color, int vidas) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.font = font;
		this.color = color;
		this.vidas = vidas;
	}

	public void dibujar(Graphics v) {
		v.fillRect(469, 5, 101, 25);
		v.setColor(color);
		v.setFont(font);
		v.drawString("Vidas: " + String.valueOf(vidas), posicionX, posicionY);
	}

	public void perderVida() {
		vidas--;
	}

	public int getVidas() {
		return vidas;
	}

	public void setColor(Color colorRecibido) {
		color = colorRecibido;
	}
	    
}
