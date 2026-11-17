package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JButton txtGuardar;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegCliente dialog = new RegCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegCliente() {
		setTitle("Registrar Cliente");
		setBounds(100, 100, 489, 410);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("ID:");
			lblNewLabel.setBounds(15, 16, 69, 20);
			panel.add(lblNewLabel);
			
			txtId = new JTextField();
			txtId.setBounds(15, 52, 146, 26);
			panel.add(txtId);
			txtId.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:\r\n");
			lblNewLabel_1.setBounds(15, 116, 69, 20);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Telefono:");
			lblNewLabel_2.setBounds(278, 116, 69, 20);
			panel.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Direcci\u00F3n:");
			lblNewLabel_3.setBounds(15, 208, 109, 20);
			panel.add(lblNewLabel_3);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(15, 152, 146, 26);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtTelefono = new JTextField();
			txtTelefono.setBounds(278, 152, 146, 26);
			panel.add(txtTelefono);
			txtTelefono.setColumns(10);
			
			txtDireccion = new JTextField();
			txtDireccion.setBounds(15, 244, 409, 26);
			panel.add(txtDireccion);
			txtDireccion.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				txtGuardar = new JButton("Guardar\r\n");
				txtGuardar.setActionCommand("OK");
				buttonPane.add(txtGuardar);
				getRootPane().setDefaultButton(txtGuardar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
