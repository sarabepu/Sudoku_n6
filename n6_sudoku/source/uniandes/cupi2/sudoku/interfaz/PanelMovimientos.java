package uniandes.cupi2.sudoku.interfaz;

import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelMovimientos extends JPanel implements ActionListener 
{

	private static final String IZQARR = "IAR";

	private static final String ARRIBA = "AR";

	private static final String DERARR = "DAR";

	private static final String IZQUIERDA = "I";

	private static final String DERAB = "DA";

	private static final String ABAJO = "A";

	private static final String IZQAB = "IA";

	private static final String DERECHA = "D";
	private InterfazSudoku principal;
	private JButton btnArriba;

	private JButton btnAbajo;

	private JButton btnIzquierda;

	private JButton btnDerecha;

	private JButton btnDerAb;

	private JButton btnDerArr;

	private JButton btnIzqAb;

	private JButton btnIzqArr;
	public PanelMovimientos(InterfazSudoku pPrincipal) {

		principal = pPrincipal;

		setLayout(new GridLayout(3, 3));

		btnIzqArr = new JButton();
		btnIzqArr.setIcon(new  ImageIcon("./data/imagenes/isqArr.png"));
		btnIzqArr.setActionCommand(IZQARR);
		btnIzqArr.addActionListener(this);
		add(btnIzqArr);

		btnArriba = new JButton();
		btnArriba.setIcon(new  ImageIcon("./data/imagenes/arriba.png"));
		btnArriba.setActionCommand(ARRIBA);
		btnArriba.addActionListener(this);
		add(btnArriba);

		btnDerArr = new JButton();
		btnDerArr.setIcon(new  ImageIcon("./data/imagenes/derArr.png"));
		btnDerArr.setActionCommand(DERARR);
		btnDerArr.addActionListener(this);
		add(btnDerArr);

		btnIzquierda = new JButton();
		btnIzquierda.setIcon(new  ImageIcon("./data/imagenes/izquierda.png"));
		btnIzquierda.setActionCommand(IZQUIERDA);
		btnIzquierda.addActionListener(this);
		add(btnIzquierda);

		JLabel centro = new JLabel ();
		add(centro);

		btnDerecha = new JButton();
		btnDerecha.setIcon(new  ImageIcon("./data/imagenes/derecha.png"));
		btnDerecha.setActionCommand(DERECHA);
		btnDerecha.addActionListener(this);
		add(btnDerecha);

		btnIzqAb = new JButton();
		btnIzqAb.setIcon(new  ImageIcon("./data/imagenes/isqAb.png"));
		btnIzqAb.setActionCommand(IZQAB);
		btnIzqAb.addActionListener(this);
		add(btnIzqAb);

		btnAbajo = new JButton();
		btnAbajo.setIcon(new  ImageIcon("./data/imagenes/abajo.png"));
		btnAbajo.setActionCommand(ABAJO);
		btnAbajo.addActionListener(this);
		add(btnAbajo);

		btnDerAb = new JButton();
		btnDerAb.setIcon(new  ImageIcon("./data/imagenes/derAb.png"));
		btnDerAb.setActionCommand(DERAB);
		btnDerAb.addActionListener(this);
		add(btnDerAb);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String accion = e.getActionCommand();
		
		if (accion.equals(IZQARR))
			principal.mover(-1, -1);
		else if (accion.equals(ARRIBA))
			principal.mover(-1, 0);
		
		else if (accion.equals(DERARR))
			principal.mover(-1, 1);
		
		else if (accion.equals(IZQUIERDA))
			principal.mover(0, -1);
		
		else if (accion.equals(DERECHA))
			principal.mover(0, 1);
		
		else if (accion.equals(IZQAB))
			principal.mover(1, -1);
		
		else if (accion.equals(ABAJO))
			principal.mover(1, 0);
		
		else if (accion.equals(DERAB))
		{
			principal.mover(1, 1);
		}
		
		
			

	}

	public void cambiarEstado(boolean pEstado)
	{
		btnArriba.setEnabled(pEstado);
		btnAbajo.setEnabled(pEstado);
		btnIzquierda.setEnabled(pEstado);
		btnDerecha.setEnabled(pEstado);
		btnIzqAb.setEnabled(pEstado);
		btnIzqArr.setEnabled(pEstado);
		btnDerAb.setEnabled(pEstado);
		btnDerArr.setEnabled(pEstado);
	}

}
