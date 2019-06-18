/**~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_sudoku
 * Autor: Equipo Cupi2 2019
 * ~~~~ 
 */

package uniandes.cupi2.sudoku.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import uniandes.cupi2.sudoku.mundo.Casilla.Estado;



/**
 * Juego de Sudoku.
 */
public class Sudoku {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Cantidad de filas que hay en una zona.
	 */
	private int cantidadFilasZona;

	/**
	 * Cantidad de columnas que hay en una zona.
	 */
	private int cantidadColumnasZona;

	/**
	 * Fila actual que se encuentra seleccionada.
	 */
	private int filaActual;

	/**
	 * Columna actual que se encuentra seleccionada.
	 */
	private int columnaActual;

	private Properties datos;

	private String[][] solucion;

	private Casilla[][] sudoku;

	private int movimientosRealizados;

	// ----------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------

	/**
	 * Crea un nuevo juego de sudoku. <br>
	 * <b>post: </b> Se inicializaron los atributos cantidadFilaZona y cantidadColumnasZona con valores aleatorios entre 2 y 4. La fila actual y la columna actual se inicializaron en 0.
	 */
	public Sudoku(){

		filaActual = 0 ;
		columnaActual = 0;
		movimientosRealizados=0;
	}


	// ----------------------------------------------------------------
	// Métodos
	// ----------------------------------------------------------------

	/**
	 * Retorna la cantidad de filas que hay por zona.
	 * @return Cantidad de filas por zona.
	 */
	public int darCantidadFilasZona(){
		return cantidadFilasZona;
	}

	/**
	 * Retorna la cantidad de columnas que hay por zona.
	 * @return Cantidad de columnas por zona.
	 */
	public int darCantidadColumnasZona(){
		return cantidadColumnasZona;
	}

	/**
	 * Retorna la fila actualmente seleccionada.
	 * @return Fila actualmente seleccionada.
	 */
	public int darFilaActual(){
		return filaActual;
	}

	/**
	 * Retorna la columna actualmente seleccionada.
	 * @return Columna actualmente seleccionada.
	 */
	public int darColumnaActual(){
		return columnaActual;
	}

	/**
	 * Retorna la casilla actual.
	 * @return Casilla aleatoria.
	 */
	public Casilla darCasillaActual(){
		return sudoku[filaActual][columnaActual];
	}


	/**
	 * Cambia los valores de los atributos por numeros aleatorios.
	 * <b>post:</b> Se cambiaron los atributos cantidadFilaZona y cantidadColumnasZona con valores aleatorios entre 2 y 4. La fila actual y la columna actual se reiniciaron en 0.
	 * @return Ruta de la imagen del sudoku cargado.
	 * @throws Exception Si un número aleatoriamente generado entre 0 y 10 es igual a 5.
	 */
	public void cargar( File pFile ) throws Exception
	{
		datos = new Properties( );

		FileInputStream in = new FileInputStream( pFile );


		try
		{
			datos.load( in );
			in.close( );

		}
		catch( Exception e )
		{
			throw new Exception( "Error al cargar el archivo, archivo no válido." );
		}


	}

	public void inicializar()
	{
		cantidadFilasZona = Integer.parseInt( datos.getProperty("sudoku.M") );
		cantidadColumnasZona = Integer.parseInt( datos.getProperty("sudoku.N") );

		int cuadrado = cantidadColumnasZona*cantidadFilasZona;

		solucion = new String[cuadrado][cuadrado];
		sudoku = new Casilla[cuadrado][cuadrado];

		for(int i = 0; i < cuadrado; i++)
		{
			String filaActual = datos.getProperty("sudoku.fila"+(i+1));

			for(int j = 0; j < cuadrado; j++)
			{
				solucion[i][j] = filaActual.charAt(j)+"";
				sudoku[i][j] = new Casilla("", false, Estado.VACIO);
			}
		}




		for(int i = 0; i < cantidadColumnasZona; i++)
		{
			for (int j = 0; j < cantidadFilasZona; j++)
			{
				int cantidad = (cantidadColumnasZona*cantidadFilasZona)/3;
				int x = i*cantidadFilasZona;
				int y = j*cantidadColumnasZona;

				while (cantidad>0)
				{

					int pistaX = generarNumeroAleatorio(x, x+cantidadFilasZona);
					int pistaY = generarNumeroAleatorio(y, y+cantidadColumnasZona);
					if (sudoku[pistaX][pistaY].darEstado()==Estado.VACIO)
					{
						sudoku[pistaX][pistaY].cambiarValor(solucion[pistaX][pistaY]);
						sudoku[pistaX][pistaY].cambiarAPista();
						sudoku[pistaX][pistaY].cambiarEstado(Estado.LLENA);
						cantidad--;
					}

				}

			}
		}


	}

	public boolean estaEnTablero(int x, int y)
	{
		if(x<0||y<0||x>(solucion.length-1)||y>(solucion[0].length-1))
		{
			return false;
		}
		else
		{
			return true;
		}
	}


	/**
	 * Cambia la fila actual y la columna actual por valores aleatorios.
	 * <b>post:</b>Se generó un nuevo valor para la fila actual y la columna actual.
	 * @throws Exception Si un número aleatoriamente generado entre 0 y 10 es igual a 5.
	 */
	public void mover(int i, int j) throws Exception
	{
		int x = filaActual+i;
		int y = columnaActual+j;

		if (!estaEnTablero(x, y))
			throw new Exception("No te puedes mover en esa dirección");

		filaActual=x;
		columnaActual=y;
		movimientosRealizados++;
	}


	public void ingresar(String n)
	{
		if(!sudoku[filaActual][columnaActual].esPista())
		{
			sudoku[filaActual][columnaActual].cambiarValor(n);
			if(!n.equals(""))
			{
				sudoku[filaActual][columnaActual].cambiarEstado(Estado.LLENA);
			}
			else
			{
				sudoku[filaActual][columnaActual].cambiarEstado(Estado.VACIO);
			}
		}
	}



	/**
	 * Reinicia a 0 los valores de filaActual y columnaActual.
	 * <b>post:</b>La fila actual y la columna actual se reiniciaron en 0.
	 * @return Ruta de la imagen del sudoku actualmente cargado.
	 */
	public void reiniciar(){
		filaActual = 0 ;
		columnaActual = 0;
		movimientosRealizados=0;
		inicializar();
	}

	/**
	 * Retorna la ruta a la imagen del sudoku solucionado.
	 * @return Ruta de la imagen del sudoku solucionado.
	 */
	public void solucionar()
	{
		for (int i = 0; i < solucion.length; i++)
		{
			for (int j = 0; j < solucion[0].length; j++)
			{
				sudoku[i][j].cambiarValor(solucion[i][j]);
			}
		}
	}

	/**
	 * Retorna la ruta de la imagen del sudoku validado.
	 * @return Ruta de la imagen del sudoku validado.
	 */
	public boolean validar( )
	{
		boolean correcto=true;
		for (int i = 0; i < solucion.length; i++)
		{
			for (int j = 0; j < solucion[0].length; j++)
			{
				if (sudoku[i][j].darEstado()==Estado.LLENA )
				{
					if(!sudoku[i][j].esPista())
					{
						if(sudoku[i][j].darValor().equals(solucion[i][j]))
						{

							sudoku[i][j].cambiarEstado(Estado.CORRECTA);
						}
						else
						{
							correcto=false;
							sudoku[i][j].cambiarEstado(Estado.ERROR);
						}
					}
				}
				else if(sudoku[i][j].darEstado()==Estado.VACIO)
				{
					correcto=false;
				}
			}

		}
		return correcto;


	}

	/**
	 * Retorna un número aleatorio entre 0 y el total de casillas del tablero.
	 * @return Número aleatorio entre 0 y el total de casillas del tablero.
	 */
	public int contarCasillasEnBlanco() {
		int r=0;
		for (int i = 0; i < solucion.length; i++)
		{
			for (int j = 0; j < solucion[0].length; j++)
			{
				if (sudoku[i][j].darEstado().equals(Estado.VACIO))
					r++;
			}
		}
		return r;

	}

	/**
	 * Retorna un número aleatorio entre 0 y el total de casillas del tablero.
	 * @return Número aleatorio entre 0 y el total de casillas del tablero.
	 */
	public int contarPistasIniciales() {
		int pistaZona = cantidadFilasZona*cantidadColumnasZona/3;
		return pistaZona*cantidadColumnasZona*cantidadFilasZona;
	}

	/**
	 * Retorna un número aleatorio entre 0 y el total de casillas del tablero*2.
	 * @return Número aleatorio entre 0 y el total de casillas del tablero*2.
	 */
	public int darMovimientosRealizados()
	{
		return movimientosRealizados;
	}


	/**
	 * Genera un número entero aleatorio entre pLimiteInferior y pLimiteSuperior - 1
	 * @param pLimiteInferior Límite inferior para el cual se va a generar el número aleatorio. 
	 * @param pLimiteSuperior Límite superior para el cual se va a generar el número aleatorio.
	 * @return Número entero entre pLimiteInferior y pLimiteSuperior - 1
	 */
	private int generarNumeroAleatorio( int pLimiteInferior, int pLimiteSuperior )
	{
		return new Random().nextInt(pLimiteSuperior-pLimiteInferior) + pLimiteInferior;
	}

	/**
	 * Método para la extensión 1.
	 * @return respuesta1.
	 */
	public String metodo1( )

	{
		return "Respuesta 1.";
	}

	/**
	 * Método para la extensión2.
	 * @return respuesta2.
	 */
	public String metodo2( )
	{
		return "Respuesta 2.";
	}


	public Casilla[][] darSudoku() {
		return sudoku;
	}


}