package jueguito;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

public class Azucar extends Elementos {
	private BufferedImage img;
	
	
	public Azucar(double posicionX, double posicionY, double velocidadX, double velocidadY) {
		super(posicionX, posicionY, velocidadX, velocidadY, 80, 80);

		String path = Paths.get(Azucar.getResource().getPath()).toString();
		try {
			this.img = ImageIO.read(new File(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static File getResource() {
		return new File("Resources/Imagenes/azucar.png");
	}

	@Override
	public boolean hayColision(CaracteristicasPersonajes caracteristicasPersonajes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void destruirse(Graphics graphics) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dibujar(Graphics graphics) {
		try {
			graphics.drawImage(img, (int)getPosicionX(), (int)getPosicionY(), this.getAncho(), this.getLargo(), null);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}
	@Override
    public void moverse() {
		posicionX = posicionX + velocidadX;
	    posicionY = posicionY + velocidadY;
	}
}
