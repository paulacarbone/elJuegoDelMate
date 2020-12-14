package jueguito;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class PanelImagen extends Elementos {
	private BufferedImage img;

	public PanelImagen() {
		super(0,0, 0, 0, 1058, 650);
		try {
			String path = Paths.get(PanelImagen.class.getClassLoader().getResource("Resources/Imagenes/fondoJuego.jpg").toURI()).toString();
			this.img = ImageIO.read(new File(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	public void dibujar(Graphics graphics) {
		try {
			graphics.drawImage(img, (int)getPosicionX(), (int)getPosicionY(), this.getAncho(), this.getLargo(), null);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}
	public  void destruirse(Graphics graphics) {
		
	}
	@Override
	public boolean hayColision(CaracteristicasPersonajes caracteristicasPersonajes) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
