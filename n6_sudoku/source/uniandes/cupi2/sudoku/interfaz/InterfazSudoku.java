package uniandes.cupi2.sudoku.interfaz;

import java.awt.*; 
import java.io.File;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.sudoku.mundo.Sudoku;

public class InterfazSudoku extends JFrame {

	private PanelBanner panelBanner;

	private PanelInformacion panelInformacion;

	private PanelOpciones panelOpciones;

	private PanelMovimientos panelMovimientos;

	private PanelPista panelPista;

	private PanelTablero panelTablero;

	private Sudoku mundo;

	public InterfazSudoku () throws Exception
	{
		setTitle("CupiSudoku");
		setSize ( 1000, 550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout( new BorderLayout( ) );

		panelBanner = new PanelBanner();
		add(panelBanner, BorderLayout.NORTH);

		panelInformacion = new PanelInformacion(this);
		add (panelInformacion, BorderLayout.WEST);

		panelOpciones = new PanelOpciones(this);
		add (panelOpciones, BorderLayout.SOUTH);
		
		panelOpciones.cambiarEstado(false);

		panelTablero = new PanelTablero();
		add (panelTablero, BorderLayout.CENTER);

		JPanel panelAuxEast = new JPanel();
		panelAuxEast.setLayout(new BorderLayout());
		add (panelAuxEast, BorderLayout.EAST);

		TitledBorder borderAux = new TitledBorder( "Movimientos" );
		borderAux.setTitleColor( Color.BLUE );
		panelAuxEast.setBorder(borderAux );

		panelPista = new PanelPista(this);
		panelAuxEast.add(panelPista, BorderLayout.SOUTH);

		panelMovimientos = new PanelMovimientos(this);
		panelAuxEast.add(panelMovimientos, BorderLayout.NORTH);

		panelPista.cambiarEstado(false);
		panelInformacion.cambiarEstado(false);
		panelMovimientos.cambiarEstado(false);
		mundo = new Sudoku();
	}
	
	public void ingresar(String n)
	{
		mundo.ingresar(n);
		panelTablero.actualizar(mundo.darCantidadFilasZona(), mundo.darCantidadColumnasZona(), mundo.darSudoku());
		panelTablero.moverse(mundo.darFilaActual(), mundo.darColumnaActual());
		panelInformacion.actualizar("Sudoku " + mundo.darCantidadColumnasZona() + "x" + mundo.darCantidadFilasZona(), mundo.contarPistasIniciales() + "" , mundo.contarCasillasEnBlanco() + "", mundo.darMovimientosRealizados() + "" );
		panelPista.actualizarPista(mundo.darCasillaActual().esPista(), mundo.darFilaActual() + "", mundo.darColumnaActual() + "");
		panelTablero.moverse(mundo.darFilaActual(), mundo.darColumnaActual());
	}
	
	public int maximo()
	{
		return mundo.darCantidadColumnasZona()*mundo.darCantidadFilasZona();
	}
	

	public void mover(int x, int y) {

		try {
			mundo.mover(x,y);
			panelTablero.moverse(mundo.darFilaActual(), mundo.darColumnaActual());
			panelInformacion.actualizar("Sudoku " + mundo.darCantidadColumnasZona() + "x" + mundo.darCantidadFilasZona(), mundo.contarPistasIniciales() + "" , mundo.contarCasillasEnBlanco() + "", mundo.darMovimientosRealizados() + "" );
			panelPista.actualizarPista(mundo.darCasillaActual().esPista(), mundo.darFilaActual() + "", mundo.darColumnaActual() + "");

		} 
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage(), "Sudoku", JOptionPane.ERROR_MESSAGE);

		}
		
	}

	public void cargar() {

		try {
			
			
			File archivo=pedirTablero();
			mundo.cargar(archivo);
			mundo.inicializar();
			panelTablero.actualizar(mundo.darCantidadFilasZona(), mundo.darCantidadColumnasZona(), mundo.darSudoku());
			panelTablero.moverse(mundo.darFilaActual(), mundo.darColumnaActual());
			panelInformacion.actualizar("Sudoku " + mundo.darCantidadColumnasZona() + "x" + mundo.darCantidadFilasZona(), mundo.contarPistasIniciales() + "" , mundo.contarCasillasEnBlanco() + "", mundo.darMovimientosRealizados() + "" );
			panelOpciones.cambiarEstado(true);
			panelMovimientos.cambiarEstado(true);
			panelInformacion.cambiarEstado(true);
			panelPista.cambiarEstado(true);
			panelPista.actualizarPista(mundo.darCasillaActual().esPista(), mundo.darFilaActual() + "", mundo.darColumnaActual() + "");
			
			
			TitledBorder borde = new TitledBorder( "Tablero" );
			int width = panelTablero.getWidth();
			int height = panelTablero.getHeight();
			int max = Math.max(width, height);
			max = mundo.darCantidadColumnasZona()*mundo.darCantidadFilasZona()*45;
			max= Math.min(max, width);
			max=Math.min(max, height);
			int hMargin = Math.abs((max - height)/2);
			int wMargin = Math.abs((max-width)/2);
			panelTablero.setBorder(new CompoundBorder(borde, BorderFactory.createEmptyBorder(hMargin, wMargin, hMargin, wMargin)));
			
			

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage(), "Sudoku", JOptionPane.ERROR_MESSAGE);

		}

	}

	public File pedirTablero(){  

		JFileChooser fc = new JFileChooser( "./data" );
		fc.setDialogTitle( "Seleccionar mapa" );
		int resultado = fc.showOpenDialog( this );
		File tablero =null;
		if( resultado == JFileChooser.APPROVE_OPTION )
		{
			 tablero = fc.getSelectedFile( );
			
		}
		return tablero;
		
	}

	public void reiniciar() 
	{
		mundo.reiniciar();
		panelTablero.actualizar(mundo.darCantidadFilasZona(), mundo.darCantidadColumnasZona(), mundo.darSudoku());
		panelTablero.moverse(mundo.darFilaActual(), mundo.darColumnaActual());
		panelPista.actualizarPista(mundo.darCasillaActual().esPista(), mundo.darFilaActual() + "", mundo.darColumnaActual() + "")	;	
		panelOpciones.cambiarEstado(true);
		panelMovimientos.cambiarEstado(true);
		panelPista.cambiarEstado(true);

	}

	public void solucionar() 
	{
		mundo.solucionar();
		panelTablero.actualizar(mundo.darCantidadFilasZona(), mundo.darCantidadColumnasZona(), mundo.darSudoku());
		panelTablero.mostrarSolucion();
		panelOpciones.solucionar();
		panelMovimientos.cambiarEstado(false);
		panelPista.cambiarEstado(false);

	}

	public void validar() 
	{
		boolean gano=mundo.validar();
		panelTablero.actualizar(mundo.darCantidadFilasZona(), mundo.darCantidadColumnasZona(), mundo.darSudoku());

		panelTablero.moverse(mundo.darFilaActual(), mundo.darColumnaActual());
		if(gano)
		{
			JOptionPane.showMessageDialog( null,"Haz completado correctamente el sudoku", "Sudoku", JOptionPane.INFORMATION_MESSAGE);
			panelOpciones.solucionar();
			panelMovimientos.cambiarEstado(false);
			panelPista.cambiarEstado(false);
		}
		else
		{
			JOptionPane.showMessageDialog( null,"Hay errores en el sudoku", "Sudoku", JOptionPane.INFORMATION_MESSAGE);

		}
	}


	public String darTablero() 
	{
		return "./data/imagenes/mundo" + mundo.darCantidadColumnasZona() + "x" + mundo.darCantidadFilasZona() + ".png";
	}

	public String darColumActual() 
	{	 
		return mundo.darColumnaActual() + "";
	}

	public String darFilaActual() 
	{
		return mundo.darFilaActual() + "";
	}

	public void reqFuncOpcion1() 
	{
		JOptionPane.showMessageDialog( null,mundo.metodo1(), "Sudoku", JOptionPane.INFORMATION_MESSAGE);

	}

	public void reqFuncOpcion2() {

		JOptionPane.showMessageDialog( null,mundo.metodo2(), "Sudoku", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		try
		{
			InterfazSudoku vent = new InterfazSudoku( );
			vent.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage(), "Sudoku", JOptionPane.ERROR_MESSAGE);

		}

	}
}

