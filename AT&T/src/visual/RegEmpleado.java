package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import logico.Administrativo;
import logico.Comercial;
import logico.Empleado;
import logico.Sistema;

public class RegEmpleado extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtIDEmpleado;
	private JTextField txtNombre;
	private JTextField txtContrasena;
	private JTextField txtPuesto;
	private JTextField txtPago;
	private JTextField txtFecha;
	private Empleado e = null;
	private JTextField txtBonoAnual;
	private JTextField txtComisiones;
	private JComboBox<String> cbxTipo;
	private JSpinner spinner;

	public static void main(String[] args) {
		try {
			RegEmpleado dialog = new RegEmpleado(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public RegEmpleado(Empleado aux) {
		if (aux != null) {
			e = aux;
			setTitle("Modificar Empleado");
		} else {
			setTitle("Crear Nuevo Empleado");
		}

		setBounds(100, 100, 450, 441);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		{
			JLabel lblNewLabel = new JLabel("Identificador:");
			lblNewLabel.setBounds(12, 13, 141, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			txtIDEmpleado = new JTextField();
			txtIDEmpleado.setBounds(12, 33, 200, 22);
			txtIDEmpleado.setText(generarIdEmpleado());
			txtIDEmpleado.setEditable(false);
			contentPanel.add(txtIDEmpleado);
			txtIDEmpleado.setColumns(10);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(12, 88, 200, 22);
			contentPanel.add(txtNombre);
		}
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(12, 68, 141, 16);
			contentPanel.add(lblNombre);
		}
		{
			txtPuesto = new JTextField();
			txtPuesto.setColumns(10);
			txtPuesto.setBounds(220, 88, 200, 22);
			contentPanel.add(txtPuesto);
		}
		{
			JLabel lblPuesto = new JLabel("Puesto:");
			lblPuesto.setBounds(220, 68, 141, 16);
			contentPanel.add(lblPuesto);
		}
		{
			JLabel lblContraseaEmpleado = new JLabel("Contrase\u00F1a empleado:");
			lblContraseaEmpleado.setBounds(12, 121, 141, 16);
			contentPanel.add(lblContraseaEmpleado);
		}
		{
			txtContrasena = new JTextField();
			txtContrasena.setColumns(10);
			txtContrasena.setBounds(12, 141, 200, 22);
			contentPanel.add(txtContrasena);
		}
		{
			JLabel lblFechaDeIngreso = new JLabel("Fecha de ingreso:");
			lblFechaDeIngreso.setBounds(220, 123, 141, 16);
			contentPanel.add(lblFechaDeIngreso);
		}
		{
			txtFecha = new JTextField();
			txtFecha.setText("dd/mm/yyyy");
			txtFecha.setColumns(10);
			txtFecha.setBounds(220, 143, 200, 22);
			contentPanel.add(txtFecha);
		}
		{
			JLabel lblPagoPorHora = new JLabel("Pago por hora:");
			lblPagoPorHora.setBounds(12, 176, 141, 16);
			contentPanel.add(lblPagoPorHora);
		}
		{
			txtPago = new JTextField();
			txtPago.setColumns(10);
			txtPago.setBounds(12, 196, 200, 22);
			contentPanel.add(txtPago);
		}

		JLabel lblNewLabel_1 = new JLabel("Tipo:");
		lblNewLabel_1.setBounds(220, 176, 56, 16);
		contentPanel.add(lblNewLabel_1);

		cbxTipo = new JComboBox<>();
		cbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sel = cbxTipo.getSelectedItem().toString();
				boolean esAdmin = "Administrador".equalsIgnoreCase(sel);
				boolean esComercial = "Comercial".equalsIgnoreCase(sel);

				txtBonoAnual.setEnabled(esAdmin);
				txtBonoAnual.setEditable(esAdmin);
				if (!esAdmin) {
					txtBonoAnual.setText("");
				}

				txtComisiones.setEnabled(esComercial);
				txtComisiones.setEditable(esComercial);
				if (!esComercial) {
					txtComisiones.setText("");
				}
			}
		});
		cbxTipo.setModel(new DefaultComboBoxModel<>(new String[] { "<<Seleccione tipo>>", "Administrador", "Comercial" }));
		cbxTipo.setBounds(220, 195, 200, 22);
		contentPanel.add(cbxTipo);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner.setBounds(12, 248, 200, 22);
		contentPanel.add(spinner);

		JLabel lblHorasTrabajadas = new JLabel("Horas trabajadas:");
		lblHorasTrabajadas.setBounds(12, 229, 141, 16);
		contentPanel.add(lblHorasTrabajadas);

		txtComisiones = new JTextField();
		txtComisiones.setEditable(false);
		txtComisiones.setEnabled(false);
		txtComisiones.setColumns(10);
		txtComisiones.setBounds(220, 247, 200, 22);
		contentPanel.add(txtComisiones);

		JLabel lblComisionesPorVenta = new JLabel("Comisiones por venta:");
		lblComisionesPorVenta.setBounds(220, 227, 141, 16);
		contentPanel.add(lblComisionesPorVenta);

		txtBonoAnual = new JTextField();
		txtBonoAnual.setEditable(false);
		txtBonoAnual.setEnabled(false);
		txtBonoAnual.setColumns(10);
		txtBonoAnual.setBounds(12, 294, 200, 22);
		contentPanel.add(txtBonoAnual);

		JLabel lblBonoAnual = new JLabel("Bono anual:");
		lblBonoAnual.setBounds(12, 274, 141, 16);
		contentPanel.add(lblBonoAnual);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear");
				if (e != null) {
					okButton.setText("Modificar");
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String n = txtNombre.getText().trim();
						String pass = txtContrasena.getText().trim();
						String p = txtPuesto.getText().trim();
						String pp = txtPago.getText().trim();
						String f = txtFecha.getText().trim();
						int h = (int) spinner.getValue();
						String tipo = cbxTipo.getSelectedItem().toString();

						if (n.isEmpty() || p.isEmpty() || pp.isEmpty() || f.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Por favor complete los datos del empleado", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						if (e == null && pass.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Por favor ingrese una contraseña para el empleado", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						if (cbxTipo.getSelectedIndex() <= 0) {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						sdf.setLenient(false);
						Date fechaIngreso = null;
						try {
							fechaIngreso = sdf.parse(f);
						} catch (ParseException ex) {
							JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use dd/MM/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						float pagoHora = 0.0f;
						try {
							pagoHora = Float.parseFloat(pp);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "El pago por hora debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						float comision = 0.0f;
						float bono = 0.0f;

						if ("Administrador".equalsIgnoreCase(tipo)) {
							try {
								bono = Float.parseFloat(txtBonoAnual.getText().trim());
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(null, "El bono debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						} else if ("Comercial".equalsIgnoreCase(tipo)) {
							try {
								comision = Float.parseFloat(txtComisiones.getText().trim());
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(null, "La comisión debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}

						if (e == null) {
							Empleado empleado = null;
							if ("Administrador".equalsIgnoreCase(tipo)) {
								empleado = new Administrativo(txtIDEmpleado.getText().trim(), n, pass, p, h, pagoHora, fechaIngreso, bono);
							} else if ("Comercial".equalsIgnoreCase(tipo)) {
								empleado = new Comercial(txtIDEmpleado.getText().trim(), n, pass, p, h, pagoHora, fechaIngreso, 0, comision);
							}

							if (empleado != null) {
								Sistema.getInstance().registrarEmpleado(empleado);
								JOptionPane.showMessageDialog(null, "Empleado registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
								limpiarCampos();
							}
						} else {
							e.setNombre(n);
							if (!pass.isEmpty()) {
								e.setContrasena(pass);
							}
							e.setPuesto(p);
							e.setHorasTrabajadas(h);
							e.setPagoXHora(pagoHora);
							e.setFechaIngreso(fechaIngreso);

							if (e instanceof Administrativo && "Administrador".equalsIgnoreCase(tipo)) {
								((Administrativo) e).setBonoAnual(bono);
							} else if (e instanceof Comercial && "Comercial".equalsIgnoreCase(tipo)) {
								((Comercial) e).setComisionPorVenta(comision);
							}

							JOptionPane.showMessageDialog(null, "Empleado modificado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
							limpiarCampos();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		if (e != null) {
			cargarEdicion();
		}
	}

	private void limpiarCampos() {
		if (e == null) {
			txtIDEmpleado.setText(generarIdEmpleado());
			cbxTipo.setEnabled(true);
			cbxTipo.setSelectedIndex(0);
		}
		txtNombre.setText("");
		txtContrasena.setText("");
		txtPuesto.setText("");
		txtPago.setText("");
		txtFecha.setText("dd/mm/yyyy");
		spinner.setValue(1);
		txtBonoAnual.setText("");
		txtComisiones.setText("");
	}

	private String generarIdEmpleado() {
		int num = Sistema.getInstance().getMisEmpleados().size() + 1;
		return "EMP-000" + num;
	}

	private void cargarEdicion() {
		txtIDEmpleado.setText(e.getIdEmpleado());
		txtIDEmpleado.setEditable(false);
		txtNombre.setText(e.getNombre());
		txtContrasena.setText(e.getContrasena());
		txtPuesto.setText(e.getPuesto());
		txtPago.setText(String.valueOf(e.getPagoXHora()));
		spinner.setValue(e.getHorasTrabajadas());

		if (e.getFechaIngreso() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			txtFecha.setText(sdf.format(e.getFechaIngreso()));
		}

		if (e instanceof Administrativo) {
			cbxTipo.setSelectedItem("Administrador");
			txtBonoAnual.setText(String.valueOf(((Administrativo) e).getBonoAnual()));
		} else if (e instanceof Comercial) {
			cbxTipo.setSelectedItem("Comercial");
			txtComisiones.setText(String.valueOf(((Comercial) e).getComisionPorVenta()));
		}
		cbxTipo.setEnabled(false);
	}
}