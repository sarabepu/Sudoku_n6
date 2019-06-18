/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_sudoku
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.sudoku.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import uniandes.cupi2.sudoku.mundo.Casilla;
import uniandes.cupi2.sudoku.mundo.Casilla.Estado;

/**
 * Panel con las casillas del sudoku organizadas en un tablero cuadrado.
 */
public class PanelTablero extends JPanel {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
     * Representa el color que toman las casillas cuando el Sudoku es resuelto.
     */
    private final static Color COLOR_EXITO = new Color( 87, 150, 38 );

    /**
     * Representa el color que toman las casillas cuando el Sudoku tiene errores.
     */
    private final static Color COLOR_ERROR = new Color( 200, 1, 1 );

    /**
     * Representa el color que toman las casillas cuando están vacías.
     */
    private final static Color COLOR_VACIO = new Color( 229, 132, 15 );
	
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Matriz con las zonas del tablero.
	 */
	private JPanel[][] zonas;

	/**
	 * Matriz que contiene los campos de texto que conforman el tablero del sudoku.
	 */
	private JTextField[][] tablero;


	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------
	/**
	 * Constructor de la clase.
	 */
	public PanelTablero() {
		

	}
	
	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	
	/**
	 * Actualiza el tablero con la información de las casillas dadas por parámetro. 
	 * Este método quita todos los elementos del panel y los vuelve a agregar según corresponda de acuerdo a la información dada por parámetro.
	 * @param pFilasZona Cantidad de filas que tiene una zona. pFilasZona>=2.
	 * @param pColumnasZona Cantidad de columnas que tiene una zona. pColumnasZona >=2.
	 * @param pCasillas Casillas del sudoku. pCasillas != null.
	 */
	public void actualizar(int pFilasZona, int pColumnasZona, Casilla[][] pCasillas){
		removeAll(); //Quitar todos los componentes del panel
		setLayout(new GridLayout(pColumnasZona, pFilasZona));

		//Crear el tablero
		
		//TODO Parte3 PuntoA: Inicializar el atributo zonas
		
		zonas= new JPanel[pColumnasZona][pFilasZona];
		
		//TODO Parte3 PuntoB: Inicializar el atributo tablero
		tablero= new JTextField[pFilasZona*pColumnasZona][pFilasZona*pColumnasZona];
		
		for (int i = 0; i < pColumnasZona; i++) {
			for (int j = 0; j < pFilasZona; j++) {
				zonas[i][j] = new JPanel();
				crearZona(zonas[i][j], i,j, pFilasZona, pColumnasZona, pCasillas);
			
				Border b = new BevelBorder( BevelBorder.RAISED );
				zonas[i][j].setBorder(b);
				add(zonas[i][j]);
			}
		}
		
		revalidate();
		repaint();
	}
	
	/**
	 * Crea los campos de textos correspondientes a la zona que inicia en pFila y pColumna. 
	 * Los campos de texto son agregados al panel pPanelZona en la ubicación que le corresponde. 
	 * @param pPanelZona panel donde se deben ubicar los campos de texto de la zona. pPanelZona != null.
	 * @param pFila Fila donde inicia la zona (fila de la casilla superior izquierda de la zona). pFila>=0.
	 * @param pColumna Columna donde inicia la zona (columna de la casilla superior izquierda de la zona). pColumna >=0.
	 * @param pFilasZona Cantidad de filas que hay en una zona.
	 * @param pColumnasZona Cantidad de columnas que hay en una zona.
	 * @param pCasillas Casillas del sudoku.
	 */
	public void crearZona(JPanel pPanelZona, int pFila, int pColumna, int pFilasZona, int pColumnasZona, Casilla[][] pCasillas){
		
		//TODO Parte3 PuntoC: Asignar el layout correspondiente a pPanelZona
		pPanelZona.setLayout(new GridLayout(pFilasZona, pColumnasZona));
		
		for(int i=0; i< pFilasZona; i++){
			for(int j=0; j<pColumnasZona; j++){
				JTextField txt = new JTextField();
				txt.setEditable(false);
				int fila = pFila*pFilasZona + i + 1;
				int columna = pColumna*pColumnasZona + j +1;
				Casilla casillaActual = pCasillas[fila-1][columna-1];
				tablero[fila-1][columna-1] = txt;
				 tablero[fila-1][columna-1].setBorder(new LineBorder(Color.DARK_GRAY));
				
				//TODO Parte3 PuntoD:Actualizar el texto que se debe mostrar de acuerdo a la casilla actual.
				
				txt.setText(casillaActual.darValor()+"");
				
				Font tipoLetra = txt.getFont( );
                Font nuevoTipoLetra = new Font( tipoLetra.getName( ), Font.PLAIN, tipoLetra.getSize( ) + 3 );

                txt.setHorizontalAlignment( JTextField.CENTER );  
                txt.setFont( nuevoTipoLetra );
				
                //TODO Parte3 PuntoE: Agregar el campo de texto a la zona (pPanelZona)
                pPanelZona.add(txt);
                // Se cambia el look and feel de acuerdo a la información de la casilla
                
                //TODO Parte3 PuntoF: Cambiar el estilo del campo de texto de la casilla según las reglas descritas en la descripción.
               
               
                
                if(casillaActual.darEstado().equals(Estado.ERROR))
                {
                	 txt.setBackground( COLOR_ERROR);
                }
                if(casillaActual.darEstado().equals(Estado.CORRECTA))
                {
                	txt.setBackground(COLOR_EXITO);
                }
                if(casillaActual.darEstado().equals(Estado.VACIO))
                {
                	txt.setBackground(COLOR_VACIO);
                }
                if(casillaActual.esPista())
                {
                	txt.setBackground(Color.WHITE);
                	txt.setFont( new Font("Helvetica", Font.BOLD, 14) );
                }
                if(casillaActual.darEstado().equals(Estado.LLENA))
                {
                	txt.setBackground(Color.WHITE);
                }
                
               
			}
		}
		pPanelZona.setBorder(new BevelBorder( BevelBorder.RAISED));
	}
	

	/**
	 * Mueve el indicador de la casilla actual 
	 * @param pFila la fila de la casilla actual.
	 * @param pColumna la columna de la casilla actual.
	 */
	public void moverse(int pFila, int pColumna) {
		
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				tablero[i][j].setBorder(new LineBorder(Color.DARK_GRAY));
			}
		}

		tablero[pFila][pColumna].setBorder(new LineBorder(Color.BLUE,2));
	}
	
	 /**
     * Se aplica el estilo del tablero solucionado.
     */
    public void mostrarSolucion( )
    {
        for( int i = 0; i < tablero.length; i++ )
        {
            for( int j = 0; j < tablero[i].length; j++ )
            {
            	tablero[ i ][ j ].setBackground( COLOR_EXITO );
            }
        }
    }


}
