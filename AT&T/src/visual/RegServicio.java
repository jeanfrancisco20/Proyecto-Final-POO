package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

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

import logico.Cable;
import logico.Internet;
import logico.Sistema;
import logico.Telefono;

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
		setTitle("Crear Servicio");
		setBounds(100, 100, 470, 692);
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
		lblNewLabel.setBounds(15, 13, 69, 20);
		panel.add(lblNewLabel);

		txtId = new JTextField();
		txtId.setBounds(15, 35, 197, 34);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Fecha de Contratacion:");
		lblNewLabel_1.setBounds(224, 13, 187, 20);
		panel.add(lblNewLabel_1);

		txtFechaContratacion = new JSpinner();
		txtFechaContratacion.setModel(new SpinnerDateModel(new java.util.Date(), null, null, Calendar.DAY_OF_YEAR));
		txtFechaContratacion.setBounds(224, 35, 212, 34);
		panel.add(txtFechaContratacion);

		JLabel lblNewLabel_2 = new JLabel("Seleccione el Servicio:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2.setBounds(137, 82, 187, 20);
		panel.add(lblNewLabel_2);

		chckbxInternet = new JCheckBox("Internet");
		chckbxInternet.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		chckbxInternet.setBounds(15, 111, 139, 29);
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
		lblNewLabel_3.setBounds(15, 149, 180, 20);
		panel.add(lblNewLabel_3);

		txtVelocidadBajada = new JTextField();
		txtVelocidadBajada.setBounds(15, 172, 197, 34);
		panel.add(txtVelocidadBajada);
		txtVelocidadBajada.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Velocidad de Subida:");
		lblNewLabel_4.setBounds(239, 149, 161, 20);
		panel.add(lblNewLabel_4);

		txtVelocidadSubida = new JTextField();
		txtVelocidadSubida.setBounds(239, 172, 197, 34);
		panel.add(txtVelocidadSubida);
		txtVelocidadSubida.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Tipo de Conexion:");
		lblNewLabel_5.setBounds(15, 219, 146, 20);
		panel.add(lblNewLabel_5);

		cbxTipoConexion = new JComboBox<String>();
		cbxTipoConexion.setModel(new DefaultComboBoxModel<String>(new String[] {
			"--Seleccione",
			"Fibra Optica -> $800",
			"HFC (Coaxial) -> $600",
			"ADSL (Inalambrico) -> $400"
		}));
		cbxTipoConexion.setBounds(15, 246, 197, 34);
		panel.add(cbxTipoConexion);

		JLabel lblNewLabel_6 = new JLabel("Limite de Datos:");
		lblNewLabel_6.setBounds(239, 219, 146, 20);
		panel.add(lblNewLabel_6);

		txtLimiteDatos = new JTextField();
		txtLimiteDatos.setBounds(239, 246, 197, 34);
		panel.add(txtLimiteDatos);
		txtLimiteDatos.setColumns(10);

		chckbxTelefono = new JCheckBox("Telefono");
		chckbxTelefono.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		chckbxTelefono.setBounds(15, 295, 139, 29);
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
		lblNewLabel_8.setBounds(15, 333, 161, 20);
		panel.add(lblNewLabel_8);

		txtNumeroTelefono = new JTextField();
		txtNumeroTelefono.setBounds(15, 354, 197, 34);
		panel.add(txtNumeroTelefono);
		txtNumeroTelefono.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Minutos Incluidos:");
		lblNewLabel_10.setBounds(239, 333, 146, 20);
		panel.add(lblNewLabel_10);

		txtMinutosIncluidos = new JTextField();
		txtMinutosIncluidos.setBounds(239, 354, 197, 34);
		panel.add(txtMinutosIncluidos);
		txtMinutosIncluidos.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Tipo de Linea:");
		lblNewLabel_9.setBounds(15, 401, 146, 20);
		panel.add(lblNewLabel_9);

		cbxTipoLinea = new JComboBox<String>();
		cbxTipoLinea.setModel(new DefaultComboBoxModel<String>(new String[] {
			"--Seleccione",
			"Movil -> $500",
			"Fijo (Residencial) -> $350",
			"VoIP (Telefonia por Internet) -> $250"
		}));
		cbxTipoLinea.setBounds(15, 423, 421, 34);
		panel.add(cbxTipoLinea);

		chckbxCable = new JCheckBox("Cable");
		chckbxCable.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		chckbxCable.setBounds(15, 469, 139, 29);
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
		lblNewLabel_11.setBounds(15, 503, 170, 20);
		panel.add(lblNewLabel_11);

		txtCantCanales = new JTextField();
		txtCantCanales.setBounds(15, 525, 197, 34);
		panel.add(txtCantCanales);
		txtCantCanales.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Tipo del Paquete de Cable:");
		lblNewLabel_12.setBounds(239, 503, 197, 20);
		panel.add(lblNewLabel_12);

		cbxTipoPaqueteCable = new JComboBox<String>();
		cbxTipoPaqueteCable.setModel(new DefaultComboBoxModel<String>(new String[] {
			"--Seleccione",
			"Basico -> $500",
			"HD -> $900",
			"Premium -> $1,400"
		}));
		cbxTipoPaqueteCable.setBounds(239, 525, 197, 34);
		panel.add(cbxTipoPaqueteCable);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Crear");
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
		txtId.setText(generarIdServicioAutomatico());
		txtId.setEditable(false);
		actualizarCampos();
	}

	private String generarIdServicioAutomatico() {
		int num = Sistema.getInstance().getMisServicios().size() + 1;
		return "SER-" + num;
	}

	private String limpiarEtiquetaCombo(String opcion) {
		int idx = opcion.indexOf(" ->");
		String limpio = (idx >= 0) ? opcion.substring(0, idx).trim() : opcion.trim();
		int parenIdx = limpio.indexOf(" (");
		return (parenIdx >= 0) ? limpio.substring(0, parenIdx).trim() : limpio;
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
			java.util.Date fechaUtil = (java.util.Date) txtFechaContratacion.getValue();
			Date fechaSql = new Date(fechaUtil.getTime());
			boolean estado = true;

			if (chckbxInternet.isSelected()) {
				if (txtVelocidadBajada.getText().trim().isEmpty()
						|| txtVelocidadSubida.getText().trim().isEmpty()
						|| txtLimiteDatos.getText().trim().isEmpty()
						|| cbxTipoConexion.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(this, "Complete todos los campos de Internet.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int bajada = Integer.parseInt(txtVelocidadBajada.getText().trim());
				int subida = Integer.parseInt(txtVelocidadSubida.getText().trim());
				String tipoConexion = limpiarEtiquetaCombo(cbxTipoConexion.getSelectedItem().toString());
				float limiteDatos = Float.parseFloat(txtLimiteDatos.getText().trim());

				Internet nuevo = new Internet(id, estado, fechaSql, bajada, subida, tipoConexion, limiteDatos);
				Sistema.getInstance().getMisServicios().add(nuevo);

			} else if (chckbxTelefono.isSelected()) {
				if (txtNumeroTelefono.getText().trim().isEmpty()
						|| txtMinutosIncluidos.getText().trim().isEmpty()
						|| cbxTipoLinea.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(this, "Complete todos los campos de Teléfono.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				String numero = txtNumeroTelefono.getText().trim();
				String tipoLinea = limpiarEtiquetaCombo(cbxTipoLinea.getSelectedItem().toString());
				int minutos = Integer.parseInt(txtMinutosIncluidos.getText().trim());

				Telefono nuevo = new Telefono(id, estado, fechaSql, numero, tipoLinea, minutos);
				Sistema.getInstance().getMisServicios().add(nuevo);

			} else if (chckbxCable.isSelected()) {
				if (txtCantCanales.getText().trim().isEmpty()
						|| cbxTipoPaqueteCable.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(this, "Complete todos los campos de Cable.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int cantCanales = Integer.parseInt(txtCantCanales.getText().trim());
				String paqueteCable = limpiarEtiquetaCombo(cbxTipoPaqueteCable.getSelectedItem().toString());

				Cable nuevo = new Cable(id, estado, fechaSql, cantCanales, paqueteCable);
				Sistema.getInstance().getMisServicios().add(nuevo);
			}

			JOptionPane.showMessageDialog(this, "Servicio registrado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
			limpiarcampos();

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Verifique que los valores numéricos sean correctos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarcampos() {
		txtId.setText(generarIdServicioAutomatico());
		txtFechaContratacion.setValue(new java.util.Date());

		chckbxInternet.setSelected(false);
		chckbxTelefono.setSelected(false);
		chckbxCable.setSelected(false);

		actualizarCampos();
	}
}