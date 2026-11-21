package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;

public class RegServicio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtVelocidadBajada;
	private JTextField txtVelocidadSubida;
	private JTextField txtLimiteDatos;
	private JTextField txtNumeroTelefono;
	private JTextField txtMinutosIncluidos;
	private JTextField txtCantCanales;
	private JSpinner txtFechaContratacion;

	private JCheckBox chckbxInternet;
	private JCheckBox chckbxTelefono;
	private JCheckBox chckbxCable;

	private JComboBox<String> cbxTipoConexion;
	private JComboBox<String> cbxTipoLinea;
	private JComboBox<String> cbxTipoPaqueteCable;
	private JButton btnGuardar;
	private JButton btnCancelar;

	public static void main(String[] args) {
		try {
			RegServicio dialog = new RegServicio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegServicio() {
		setTitle("Registrar Servicio");
		setBounds(100, 100, 1128, 570);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(15, 66, 69, 20);
		panel.add(lblNewLabel);

		txtId = new JTextField();
		txtId.setBounds(15, 97, 146, 26);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Fecha de Contratacion:");
		lblNewLabel_1.setBounds(216, 66, 187, 20);
		panel.add(lblNewLabel_1);

		txtFechaContratacion = new JSpinner();
		txtFechaContratacion.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
		txtFechaContratacion.setBounds(216, 97, 161, 26);
		panel.add(txtFechaContratacion);

		JLabel lblNewLabel_2 = new JLabel("Seleccione el Servicio:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2.setBounds(15, 154, 287, 20);
		panel.add(lblNewLabel_2);

		chckbxInternet = new JCheckBox("Internet");
		chckbxInternet.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		chckbxInternet.setBounds(15, 200, 139, 29);
		chckbxInternet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxInternet.isSelected()) {
					chckbxTelefono.setSelected(false);
					chckbxCable.setSelected(false);
				}
				actualizarCampos();
			}
		});
		panel.add(chckbxInternet);

		JLabel lblNewLabel_3 = new JLabel("Velocidad de Bajada:");
		lblNewLabel_3.setBounds(15, 252, 180, 20);
		panel.add(lblNewLabel_3);

		txtVelocidadBajada = new JTextField();
		txtVelocidadBajada.setBounds(15, 288, 146, 26);
		panel.add(txtVelocidadBajada);
		txtVelocidadBajada.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Velocidad de Subida:");
		lblNewLabel_4.setBounds(216, 252, 161, 20);
		panel.add(lblNewLabel_4);

		txtVelocidadSubida = new JTextField();
		txtVelocidadSubida.setBounds(216, 288, 146, 26);
		panel.add(txtVelocidadSubida);
		txtVelocidadSubida.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Tipo de Conexion:");
		lblNewLabel_5.setBounds(15, 347, 146, 20);
		panel.add(lblNewLabel_5);

		cbxTipoConexion = new JComboBox<String>();
		cbxTipoConexion.setModel(new DefaultComboBoxModel<String>(new String[] {
			"--Seleccione",
			"Fibra Optica -> $800",
			"HFC (Coaxial) -> $600",
			"ADSL (Inalambrico) -> $400"
		}));
		cbxTipoConexion.setBounds(15, 383, 146, 26);
		panel.add(cbxTipoConexion);

		JLabel lblNewLabel_6 = new JLabel("Limite de Datos:");
		lblNewLabel_6.setBounds(216, 347, 146, 20);
		panel.add(lblNewLabel_6);

		txtLimiteDatos = new JTextField();
		txtLimiteDatos.setBounds(216, 383, 146, 26);
		panel.add(txtLimiteDatos);
		txtLimiteDatos.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Registrar Servicio");
		lblNewLabel_7.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(473, 16, 251, 26);
		panel.add(lblNewLabel_7);

		chckbxTelefono = new JCheckBox("Telefono");
		chckbxTelefono.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		chckbxTelefono.setBounds(442, 200, 139, 29);
		chckbxTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxTelefono.isSelected()) {
					chckbxInternet.setSelected(false);
					chckbxCable.setSelected(false);
				}
				actualizarCampos();
			}
		});
		panel.add(chckbxTelefono);

		JLabel lblNewLabel_8 = new JLabel("Numero de Telefono:");
		lblNewLabel_8.setBounds(442, 252, 161, 20);
		panel.add(lblNewLabel_8);

		txtNumeroTelefono = new JTextField();
		txtNumeroTelefono.setBounds(442, 288, 146, 26);
		panel.add(txtNumeroTelefono);
		txtNumeroTelefono.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Minutos Incluidos:");
		lblNewLabel_10.setBounds(641, 252, 146, 20);
		panel.add(lblNewLabel_10);

		txtMinutosIncluidos = new JTextField();
		txtMinutosIncluidos.setBounds(641, 288, 146, 26);
		panel.add(txtMinutosIncluidos);
		txtMinutosIncluidos.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Tipo de Linea:");
		lblNewLabel_9.setBounds(442, 347, 146, 20);
		panel.add(lblNewLabel_9);

		cbxTipoLinea = new JComboBox<String>();
		cbxTipoLinea.setModel(new DefaultComboBoxModel<String>(new String[] {
			"--Seleccione",
			"Movil -> $500",
			"Fijo (Residencial) -> $350",
			"VoIP (Telefonia por Internet) -> $250"
		}));
		cbxTipoLinea.setBounds(442, 387, 345, 26);
		panel.add(cbxTipoLinea);

		chckbxCable = new JCheckBox("Cable");
		chckbxCable.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		chckbxCable.setBounds(861, 200, 139, 29);
		chckbxCable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxCable.isSelected()) {
					chckbxInternet.setSelected(false);
					chckbxTelefono.setSelected(false);
				}
				actualizarCampos();
			}
		});
		panel.add(chckbxCable);

		JLabel lblNewLabel_11 = new JLabel("Cantidad de Canales:");
		lblNewLabel_11.setBounds(861, 252, 170, 20);
		panel.add(lblNewLabel_11);

		txtCantCanales = new JTextField();
		txtCantCanales.setBounds(861, 288, 146, 26);
		panel.add(txtCantCanales);
		txtCantCanales.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Tipo del Paquete de Cable:");
		lblNewLabel_12.setBounds(861, 347, 197, 20);
		panel.add(lblNewLabel_12);

		cbxTipoPaqueteCable = new JComboBox<String>();
		cbxTipoPaqueteCable.setModel(new DefaultComboBoxModel<String>(new String[] {
			"--Seleccione",
			"Basico -> $500",
			"HD -> $900",
			"Premium -> $1,400"
		}));
		cbxTipoPaqueteCable.setBounds(861, 387, 146, 26);
		panel.add(cbxTipoPaqueteCable);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setActionCommand("OK");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarServicio();
					}
				});
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}

		actualizarCampos();
	}

	private void actualizarCampos() {
		boolean internet = chckbxInternet.isSelected();
		boolean telefono = chckbxTelefono.isSelected();
		boolean cable = chckbxCable.isSelected();
		
		txtVelocidadBajada.setEnabled(internet);
		txtVelocidadSubida.setEnabled(internet);
		cbxTipoConexion.setEnabled(internet);
		txtLimiteDatos.setEnabled(internet);
		if (!internet) {
			txtVelocidadBajada.setText("");
			txtVelocidadSubida.setText("");
			cbxTipoConexion.setSelectedIndex(0);
			txtLimiteDatos.setText("");
		}

		txtNumeroTelefono.setEnabled(telefono);
		txtMinutosIncluidos.setEnabled(telefono);
		cbxTipoLinea.setEnabled(telefono);
		if (!telefono) {
			txtNumeroTelefono.setText("");
			txtMinutosIncluidos.setText("");
			cbxTipoLinea.setSelectedIndex(0);
		}

		txtCantCanales.setEnabled(cable);
		cbxTipoPaqueteCable.setEnabled(cable);
		if (!cable) {
			txtCantCanales.setText("");
			cbxTipoPaqueteCable.setSelectedIndex(0);
		}
	}

	private void guardarServicio() {
		if (!chckbxInternet.isSelected() && !chckbxTelefono.isSelected() && !chckbxCable.isSelected()) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de servicio.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (txtId.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese o genere un ID para el servicio.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			String id = txtId.getText().trim();
			Date fechaContratacion = (Date) txtFechaContratacion.getValue();

			if (chckbxInternet.isSelected()) {
				if (txtVelocidadBajada.getText().trim().isEmpty()
						|| txtVelocidadSubida.getText().trim().isEmpty()
						|| txtLimiteDatos.getText().trim().isEmpty()
						|| cbxTipoConexion.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(this, "Complete todos los campos de Internet.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				float bajada = Float.parseFloat(txtVelocidadBajada.getText().trim());
				float subida = Float.parseFloat(txtVelocidadSubida.getText().trim());
				float limiteDatos = Float.parseFloat(txtLimiteDatos.getText().trim());
				String tipoConexion = cbxTipoConexion.getSelectedItem().toString();


			} else if (chckbxTelefono.isSelected()) {
				if (txtNumeroTelefono.getText().trim().isEmpty()
						|| txtMinutosIncluidos.getText().trim().isEmpty()
						|| cbxTipoLinea.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(this, "Complete todos los campos de Teléfono.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				String numero = txtNumeroTelefono.getText().trim();
				int minutos = Integer.parseInt(txtMinutosIncluidos.getText().trim());
				String tipoLinea = cbxTipoLinea.getSelectedItem().toString();


			} else if (chckbxCable.isSelected()) {
				if (txtCantCanales.getText().trim().isEmpty()
						|| cbxTipoPaqueteCable.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(this, "Complete todos los campos de Cable.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int cantCanales = Integer.parseInt(txtCantCanales.getText().trim());
				String paqueteCable = cbxTipoPaqueteCable.getSelectedItem().toString();

			}

			JOptionPane.showMessageDialog(this, "Servicio registrado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
			dispose();

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Verifique que los valores numéricos sean correctos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}