package uniandes.cupi2.sudoku.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelPista extends JPanel implements  ActionListener
{

	private static final String INGRESAR = "Ingresar";

	private static final String BORRAR = "Borrar";

	private InterfazSudoku principal;

	private JTextField txtFila;

	private JTextField txtColumna;


	private JCheckBox chPista;


	private JButton btnIngresar;

	private JButton btnBorrar;



	public PanelPista (InterfazSudoku pPrincipal)
	{
		principal = pPrincipal;

		setLayout(new GridLayout(4, 2));

		JLabel lblPista = new JLabel ("Pista");
		add (lblPista);

		chPista = new JCheckBox();
		chPista.setEnabled(false);
		add (chPista);

		JLabel lblFila = new JLabel ("Fila");
		add (lblFila);

		txtFila = new JTextField();
		txtFila.setEditable(false);
		add (txtFila);

		JLabel lblColumna = new JLabel ("Columna");
		add (lblColumna);

		txtColumna = new JTextField();
		txtColumna.setEditable(false);
		add (txtColumna);

		btnIngresar = new JButton ("Ingresar");
		btnIngresar.setActionCommand(INGRESAR);
		btnIngresar.addActionListener(this);
		add (btnIngresar);

		btnBorrar = new JButton ("Borrar");
		btnBorrar.setActionCommand(BORRAR);
		btnBorrar.addActionListener(this);
		add (btnBorrar);


	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(INGRESAR))
		{
			String strModelo = JOptionPane.showInputDialog( this , "Introduzca el numero en la casilla " + principal.darColumActual() + "," + principal.darFilaActual() 
			, "Input", JOptionPane.QUESTION_MESSAGE );
			if( strModelo != null )
			{
				try{
					int numeroIngresado = Integer.parseInt(strModelo);
					int max = principal.maximo();
					if (numeroIngresado <= max && numeroIngresado >= 1)
					{
						principal.ingresar(strModelo);
					}
					else {
						JOptionPane.showMessageDialog( null,"El numero ingresado es invalido, debe ser uno entre 1 y "+max+".", "Sudoku", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (Exception x){
					JOptionPane.showMessageDialog( null,"Debe ingresar un número válido.", "Sudoku", JOptionPane.ERROR_MESSAGE);

				}
			}
		}
		else if (e.getActionCommand().equals(BORRAR))
		{
			principal.ingresar("");
		}

	}

	public void actualizarPista(boolean esPista, String fila, String columna) 
	{

		chPista.setSelected(esPista);
		txtFila.setText(fila);
		txtColumna.setText(columna);

	}

	public void cambiarEstado(boolean pEstado) 
	{
		btnIngresar.setEnabled(pEstado);
		btnBorrar.setEnabled(pEstado);
	}



}
