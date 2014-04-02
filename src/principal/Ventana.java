package principal;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ventana{
	JFrame frame;
	TextArea texto = new TextArea("Agregue un archivo .txt o introduzca el texto aquí");
	JButton button = new JButton("Selecccionar Un Archivo .TXT");
	JButton  transformar = new JButton("Transformar");
	TextArea transformado = new TextArea("Estructura de Datos. \n   Equipo 5:"
                + "\n     - Jimenez Martinez Diego Israel \n     - Juarez Morales Juan Antonio \n     - Peña Velazquez Mikael Jesus \n     - Tominez Cruz Kevin Albino \n     - Torres Vazquez Rene \n     - Vargas Martinez Victor Alfonso");
	Huffman huf;

	public void mostrar(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		this.frame = new JFrame("Huffman.");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String direccion = "";
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(new TxTFilter());
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					direccion = selectedFile.getPath();
				}
				texto.setText(new Archivos().leerArchivo(direccion).replaceAll("EOL","\n"));
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});
		
		transformar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(texto.getText().length()<=0){
					JOptionPane.showMessageDialog(frame, "No hay texto para Transformar!!");
				}else{
					huf = new Huffman(texto.getText());
					transformado.setText(huf.codigo);
					SwingUtilities.updateComponentTreeUI(frame);
				}
			}
		});
		
		button.setBounds(200,10 , 30, 30);
		transformado.setEditable(false);
		frame.add(button);
		frame.add(texto);
		frame.add(transformar);
		frame.add(transformado);
		frame.setVisible(true);
	}
}


