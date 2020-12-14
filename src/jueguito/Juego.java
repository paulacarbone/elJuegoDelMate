package jueguito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Juego extends JPanel implements KeyListener, Runnable {

	private static final long serialVersionUID = 1L;
	private Vidas vidas;
	private Puntaje puntaje;
	private Jugador jugador;
	private Elementos yerba;	
	private Sonido sonido;
	private PanelImagen panelImagen;
	private boolean juegoCorriendo, pararJuego, ganarJuego;
	private int puntos, altoJuego, anchoJuego, apariciones=150;
	private List<Chuker> enemigosDulces = new ArrayList<>();
	private List<Azucar> enemigosAzucarados = new ArrayList<>();
	private static final int CANTIDAD_EDULCORANTE = 3;
	private static final int CANTIDAD_AZUCAR = 4;

	public Juego(int anchoVentana, int altoVentana) {
		this.anchoJuego = anchoVentana;
		this.altoJuego = altoVentana;
		this.vidas = new Vidas((anchoVentana / 2) - 50, 25, new Font("Rubik", 15, 20), Color.green, 3);
		this.puntaje = new Puntaje(50, 25, new Font("Rubik", 15, 20), Color.red, 0);
		this.jugador = new Jugador(anchoVentana - 70, altoVentana - 70, 0, 0);		
		this.yerba = new Yerba(500, 500);
		this.panelImagen = new PanelImagen();
		this.pararJuego = false;
		this.juegoCorriendo = true;		
		cargarEnemigos();
		cargarSonidos();
	}
	
	private void cargarEnemigos() {
		for(int i=0; i < CANTIDAD_EDULCORANTE; i++) {
			if(Math.random()*5 <= 2.5) {
			    enemigosDulces.add(new Chuker(anchoJuego+(jugador.getAncho()*2), Math.random()*altoJuego, Math.random()*7*-1, Math.random()*5));
			} else {
				enemigosDulces.add(new Chuker(Math.random()*anchoJuego, altoJuego+(jugador.getLargo()*2), Math.random()*5, Math.random()*7*-1));
			}
		}
		for(int i=0; i < CANTIDAD_AZUCAR; i++) {
			if(Math.random()*5 <= 2.5) { 
			    enemigosAzucarados.add(new Azucar(anchoJuego+(jugador.getAncho()*2), Math.random()*altoJuego,  Math.random()*7*-1, Math.random()*5));
			} else {
				enemigosAzucarados.add(new Azucar(Math.random()*anchoJuego, altoJuego+(jugador.getLargo()*2),  Math.random()*5, Math.random()*7*-1));
			}
		}
	}
	
	private void cargarSonidos() {
		try {
			sonido = new Sonido();
			sonido.agregarSonido("ruidomate", "Resources/Sonidos/ruidomate.wav");
			sonido.agregarSonido("saturno", "Resources/Sonidos/saturno.wav"); 
			sonido.agregarSonido("azucar", "Resources/Sonidos/asco.wav");
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(anchoJuego, altoJuego);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == 39) {
			jugador.setVelocidadX(5);
		} 
		if (e.getKeyCode() == 37) {
			jugador.setVelocidadX(-5);
		}
		if (e.getKeyCode() == 40) {
			jugador.setVelocidadY(5);
		} 
		if (e.getKeyCode() == 38) {
			jugador.setVelocidadY(-5);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 39) {
			jugador.setVelocidadX(5);
		} 
		if (e.getKeyCode() == 37) {
			jugador.setVelocidadX(-5);
		}
		if (e.getKeyCode() == 40) {
			jugador.setVelocidadY(5);
		} 
		if (e.getKeyCode() == 38) {
			jugador.setVelocidadY(-5);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 39) {
			jugador.setVelocidadX(0);
		} else if (e.getKeyCode() == 37) {
			jugador.setVelocidadX(0);
		} else if (e.getKeyCode() == 40) {
			jugador.setVelocidadY(0);
		} else if (e.getKeyCode() == 38) {
			jugador.setVelocidadY(0);
		}
	}

	@Override
	public void run() {
		while (juegoCorriendo)  {
			actualizarJuego();
			dibujar();
			try {
				Thread.sleep(10);
			} catch (Exception e1) {
				throw new RuntimeException(e1);
			}
		}
	}

	private void dibujar() {
		this.repaint();
	}
	
	private void mostrarMensaje(Graphics g, String mensaje, int x, int y) {
		this.limpiarPantalla(g);
		g.setColor(Color.cyan);
		g.setFont(new Font("Impact", 8, 30));
		g.drawString(mensaje, x, y);
	}
	
	private void dibujarFinJuego(Graphics g) {
		if(ganarJuego == false) {
			mostrarMensaje(g, "GAME OVER ", 480, 325);
			g.setColor(Color.white);
			drawString(g, ("Obtuviste " + puntaje.getPuntaje() + " puntos"), 420, 325);
		}
		if(ganarJuego) {
			mostrarMensaje(g, "Ganaste, salvaste al mate de ser dulce!", 300, 325);
		}
	}

	
	private String drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());

		return text;
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (!pararJuego && !ganarJuego) {
		    super.paintComponent(g);
		    panelImagen.dibujar(g);
		    jugador.dibujar(g);
		    vidas.dibujar(g);
		    puntaje.dibujar(g);
		    yerba.dibujar(g);
		    dibujarEnemigos(g);		
	    } else { 
		    dibujarFinJuego(g);
		    juegoCorriendo = false;	
		}

	}
	
	private void limpiarPantalla(Graphics graphics) {
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, anchoJuego, altoJuego);
	}
	
	private void dibujarEnemigos(Graphics g) {				
		for(int i=0; i < puntos/apariciones; i++) {
			if (i<enemigosDulces.size()) {
		enemigosDulces.get(i).dibujar(g);
			}
		}
		for(int i=0; i < puntos/apariciones*0.8; i++) {
			if (i<enemigosAzucarados.size()) {
			enemigosAzucarados.get(i).dibujar(g);
			}
		}
	}

	private void actualizacionEnemigos() {
		for(int i=0; i < puntos/apariciones; i++) {
			if (i<enemigosDulces.size()) {
		    enemigosDulces.get(i).moverse();
		    choquesEnemigos(enemigosDulces.get(i));
			} 			
		}
		for(int i=0; i < puntos/apariciones*0.8; i++) {
			if (i<enemigosAzucarados.size()) {
			enemigosAzucarados.get(i).moverse();
			choquesEnemigos(enemigosAzucarados.get(i));
			}
		}
		
	}
	
	private void choquesEnemigos(Elementos enemigo) {
		if(enemigo.getPosicionX() < -enemigo.getAncho()) {
			enemigo.setVelocidadX(Math.random()*6);
			enemigo.setVelocidadY(Math.random()*6*-1);
		} 
		if(enemigo.getPosicionX() > enemigo.getAncho()+anchoJuego) {
			enemigo.setVelocidadX(Math.random()*6*-1);
		} 
		if(enemigo.getPosicionY() < -enemigo.getLargo()) {
			enemigo.setVelocidadY(Math.random()*6);
			enemigo.setVelocidadX(Math.random()*6);
		} 
		if(enemigo.getPosicionY() > enemigo.getLargo()+altoJuego) {
			enemigo.setVelocidadY(Math.random()*6*-1);
			enemigo.setVelocidadX(Math.random()*6);
		} 
	}

	private void actualizarJuego() {
		jugador.moverse();
		yerba.moverse();
		actualizacionEnemigos();
		verificarColisiones();
		verificarFinDeJuego();
		verificacionPersonaje();
	}

	private void verificarColisiones() {
		colisionYerba();
		colisionEdulcorante();
		colisionAzucar();
	}
			
	private void verificacionPersonaje() {
		if(jugador.getPosicionY() > altoJuego) {
			jugador.setPosicionY(-jugador.getLargo());
		}
		if(jugador.getPosicionY() < -jugador.getLargo()) {
			jugador.setPosicionY(altoJuego);
		}
		if(jugador.getPosicionX() > anchoJuego) {
			jugador.setPosicionX(-jugador.getAncho());
		}
		if(jugador.getPosicionX() < -jugador.getAncho()) {
			jugador.setPosicionX(anchoJuego);
		}
	}
	
	private void colisionEdulcorante() {
		for(int i=0; i < enemigosDulces.size(); i++) {			
			if (jugador.hayColision(enemigosDulces.get(i))) {	
			vidas.perderVida();
			enemigosDulces.remove(i);
			sonido.tocarSonido("saturno");
			}
		}
		if (vidas.getVidas() == 2) {
			vidas.setColor(Color.yellow);
		} else if (vidas.getVidas() == 1) {
			vidas.setColor(Color.red);
		}
	}
	
	private void colisionYerba() {

		if (jugador.hayColision(yerba)) {
			yerba = new Yerba((int) (Math.random() * (anchoJuego) - 20), (int) (Math.random() * (altoJuego) - 20));
			puntaje.ganarPuntaje();
			sonido.tocarSonido("ruidomate");
			puntos+=25;
		}

	}

	private void colisionAzucar() {
		for(int i=0; i < enemigosAzucarados.size(); i++) {			
			if (jugador.hayColision(enemigosAzucarados.get(i))) {
			puntaje.perderPuntaje();
			enemigosAzucarados.remove(i);
			sonido.tocarSonido("azucar");
			}
		}
	}
	
	private void verificarFinDeJuego() {
		if (puntaje.getPuntaje() == 500) {
			ganarJuego = true;
		}
		if (vidas.getVidas() == 0) {
			pararJuego = true;			
		}	
    }
}