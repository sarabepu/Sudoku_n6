package uniandes.cupi2.sudoku.interfaz;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBanner extends JPanel
{

	public PanelBanner() {
		JLabel labImagen = new JLabel( );
		labImagen.setIcon( new ImageIcon( "./data/imagenes/banner.png" ) );
		add( labImagen, BorderLayout.NORTH );
	}

}