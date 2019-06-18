package uniandes.cupi2.sudoku.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelOpciones extends JPanel implements ActionListener 
{
	public final static String CARGAR = "cargar";

	public final static String REINICIAR = "reiniciar";

	public final static String VALIDAR = "validar";

	public final static String SOLUCIONAR = "solucionar";

	private final static String OPCION_1 = "OPCION_1";

	private final static String OPCION_2 = "OPCION_2";

	private InterfazSudoku principal;


	private JButton btnCargar;


	private JButton btnReiniciar;

	private JButton btnValidar;

	private JButton btnSolucionar;

	private JButton btnOpcion1;

	private JButton btnOpcion2;


	public PanelOpciones( InterfazSudoku pPrincipal )
	{
		principal = pPrincipal;
		setLayout(new GridLayout(1, 6));

		TitledBorder border = new TitledBorder( "Opciones" );
		border.setTitleColor( Color.BLUE );
		setBorder( border );

		btnCargar = new JButton( "Cargar" );
		btnCargar.setActionCommand( CARGAR );
		btnCargar.addActionListener( this );
		add(btnCargar);

		btnReiniciar = new JButton( "Reiniciar" );
		btnReiniciar.setActionCommand( REINICIAR );
		btnReiniciar.addActionListener( this );
		add(btnReiniciar);

		btnValidar = new JButton( "Validar" );
		btnValidar.setActionCommand( VALIDAR );
		btnValidar.addActionListener( this );
		add(btnValidar);

		btnSolucionar = new JButton( "Solucionar" );
		btnSolucionar.setActionCommand( SOLUCIONAR );
		btnSolucionar.addActionListener( this );
		add(btnSolucionar);



		btnOpcion1 = new JButton( "Opción 1" );
		btnOpcion1.setActionCommand( OPCION_1 );
		btnOpcion1.addActionListener( this );
		add(btnOpcion1);

		btnOpcion2 = new JButton( "Opción 2" );
		btnOpcion2.setActionCommand( OPCION_2 );
		btnOpcion2.addActionListener( this );
		add(btnOpcion2);
	}

	public void actionPerformed( ActionEvent pEvento )
	{
		String comando = pEvento.getActionCommand( );

		if( comando.equals( CARGAR ) )
		{
			principal.cargar();
		} 
		else if (comando.equals(REINICIAR) )
		{
			principal.reiniciar();
		} 
		else if  ( comando.equals(VALIDAR) )
		{
			principal.validar();
		} 
		else if (comando.equals (SOLUCIONAR))
		{
			principal.solucionar();
		}     
		else if( comando.equals( OPCION_1 ) )
		{
			principal.reqFuncOpcion1( );
		}
		else if( comando.equals( OPCION_2 ) )
		{
			principal.reqFuncOpcion2( );
		}

	}
	public void solucionar() {
		btnValidar.setEnabled(false);
		btnSolucionar.setEnabled(false);
	}
	public void cambiarEstado(boolean pEstado) {
		btnValidar.setEnabled(pEstado);
		btnSolucionar.setEnabled(pEstado);
		btnReiniciar.setEnabled(pEstado);
		btnOpcion1.setEnabled(pEstado);
		btnOpcion2.setEnabled(pEstado);
	}
}
