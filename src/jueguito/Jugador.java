package jueguito;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Jugador extends Elementos {

    private BufferedImage img;

    public Jugador(int posicionX, int posicionY, int velocidadX, int velocidadY) {
        super(posicionX, posicionY, velocidadX, velocidadY, 70, 70);
        String path = Paths.get(Jugador.getResource().getPath())
                .toString();
        try {
            this.img = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File getResource() {
        return new File("Resources/Imagenes/mate.png");
    }


    @Override
    public void dibujar(Graphics graphics) {
        try {
            graphics.drawImage(img, (int)getPosicionX(), (int)getPosicionY(), this.getAncho(), this.getLargo(), null);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

    // Metodo que detecta colisiones
    @Override
	public boolean hayColision(CaracteristicasPersonajes elemento) {
		if (posicionX < elemento.getPosicionX() + elemento.getAncho() &&
			posicionX + ancho > elemento.getPosicionX() &&
			posicionY < elemento.getPosicionY() + elemento.getLargo() &&
			posicionY + largo > elemento.getPosicionY()) {
			return true;
		}
		
		return false;
	}

    @Override
    public void destruirse(Graphics graphics) {

    }

}
