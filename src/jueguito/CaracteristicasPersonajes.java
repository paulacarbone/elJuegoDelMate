package jueguito;

import java.awt.Graphics;

public interface CaracteristicasPersonajes extends Dibujar {

        double getPosicionX();

        double getPosicionY();

        int getAncho();

        int getLargo();

        double getVelocidadX();

        double getVelocidadY();

        @Override
        void dibujar(Graphics graphics);

        void moverse();

        boolean hayColision(CaracteristicasPersonajes caracteristicasPersonajes);


}
