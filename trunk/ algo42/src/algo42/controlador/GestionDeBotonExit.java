package algo42.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GestionDeBotonExit implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);	
	}
}