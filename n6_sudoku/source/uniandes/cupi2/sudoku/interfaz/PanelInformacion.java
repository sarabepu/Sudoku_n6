package uniandes.cupi2.sudoku.interfaz;

import java.awt.*; 
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelInformacion extends JPanel 
{
	private InterfazSudoku principal;
	private JTextField txtNombreSudoku;

	private JTextField txtNumeroPistasIniciales;

	private JTextField txtNumeroCasillasBlanco;

	private JTextField txtNumeroMovimientos;

	public PanelInformacion (InterfazSudoku pPrincipal)
	{
		principal = pPrincipal;

		setLayout(new GridLayout(4, 2));
		TitledBorder border = new TitledBorder( "Información" );
		border.setTitleColor( Color.BLUE );
		setBorder( border );

		JLabel labNombre = new JLabel ("Nombre");

		JLabel labNumeroPistasIniciales = new JLabel ("Numero pistas iniciales");

		JLabel labNumeroCasillasBlanco = new JLabel ("Numero casillas en blanco");
		
		JLabel labNumeroMovimientos = new JLabel ("Numero movimientos");
		
		txtNombreSudoku = new JTextField();
		txtNombreSudoku.setEditable(false);
		
		txtNumeroPistasIniciales = new JTextField();
		txtNumeroPistasIniciales.setEditable(false);

		txtNumeroCasillasBlanco = new JTextField();
		txtNumeroCasillasBlanco.setEditable(false);

		txtNumeroMovimientos = new JTextField();
		txtNumeroMovimientos.setEditable(false);
		

		add(labNombre);
		add (txtNombreSudoku);
		add(labNumeroPistasIniciales);
		add (txtNumeroPistasIniciales);
		add (labNumeroCasillasBlanco);
		add (txtNumeroCasillasBlanco);
		add (labNumeroMovimientos);
		add (txtNumeroMovimientos);
	}

	public void actualizar(String nombre, String pistasiniciales, String casillasBlanco, String movimientos) {
		txtNombreSudoku.setText(nombre);
		txtNumeroPistasIniciales.setText(pistasiniciales);
		txtNumeroCasillasBlanco.setText(casillasBlanco);
		txtNumeroMovimientos.setText(movimientos);
	}

	public void cambiarEstado(boolean pEstado)
	{
		txtNombreSudoku.setEnabled(pEstado);
		txtNumeroCasillasBlanco.setEnabled(pEstado);
		txtNumeroMovimientos.setEnabled(pEstado);
		txtNumeroPistasIniciales.setEnabled(pEstado);
	}



}
