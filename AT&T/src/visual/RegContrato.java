package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logico.Cliente;
import logico.Comercial;
import logico.Contrato;
import logico.Empleado;
import logico.Plan;
import logico.Sistema;

public class RegContrato extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final float TASA_ITBIS = 0.18f;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIDcontrato;
	private JTextField txtFecha;
	private JTextField txtCargoMensual;
	private JComboBox<Object> cbxPlanes;
	private JComboBox<Object> cbxComerciales;

	private Contrato contratoAEditar = null;
	private boolean esEdicion = false;
	private JButton crearBtn;
	private JTextField txtIdcliente;
	private JTextField txtNomCliente;
	private JTextField txtDireCliente;
	private JTextField txtTelCliente;
	private Cliente clienteActual = null;
	private JTextField txtContrasena;

	public static void main(String[] args) {
		try {
			RegContrato dialog = new RegContrato(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegContrato(Contrato aux) {
		this.contratoAEditar = aux;
		this.esEdicion = (this.contratoAEditar != null);

		setTitle(esEdicion ? "Modificar Contrato" : "Crear Nuevo Contrato");
		setBounds(100, 100, 470, 563);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID del Contrato:");
		lblNewLabel.setBounds(240, 406, 176, 14);
		contentPanel.add(lblNewLabel);

		txtIDcontrato = new JTextField();
		txtIDcontrato.setBounds(240, 424, 200, 33);
		contentPanel.add(txtIDcontrato);
		txtIDcontrato.setColumns(10);

		cbxPlanes = new JComboBox<>();
		cbxPlanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxPlanes.getSelectedIndex() > 0) {
					Plan planSel = obtenerPlanSeleccionado();
					if (planSel != null) {
						float totalConItbis = calcularCargoConItbis(planSel.getPrecioBase());
						txtCargoMensual.setText(String.format(Locale.US, "%.2f", totalConItbis));
					}
				} else if (!esEdicion) {
					txtCargoMensual.setText("");
				}
			}
		});
		cbxPlanes.setBounds(12, 293, 200, 33);
		contentPanel.add(cbxPlanes);

		JLabel lblPlanes = new JLabel("Planes:");
		lblPlanes.setBounds(12, 276, 100, 14);
		contentPanel.add(lblPlanes);

		cbxComerciales = new JComboBox<>();
		cbxComerciales.setBounds(240, 293, 200, 33);
		contentPanel.add(cbxComerciales);

		JLabel lblComerciales = new JLabel("Comerciales:");
		lblComerciales.setBounds(239, 276, 145, 14);
		contentPanel.add(lblComerciales);

		txtFecha = new JTextField();
		txtFecha.setText("dd/mm/yyyy");
		txtFecha.setColumns(10);
		txtFecha.setBounds(12, 424, 200, 33);
		contentPanel.add(txtFecha);

		JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio:");
		lblFechaDeInicio.setBounds(12, 406, 156, 14);
		contentPanel.add(lblFechaDeInicio);

		txtCargoMensual = new JTextField();
		txtCargoMensual.setEditable(false);
		txtCargoMensual.setColumns(10);
		txtCargoMensual.setBounds(12, 360, 200, 33);
		contentPanel.add(txtCargoMensual);

		JLabel lblCargoMensual = new JLabel("Cargo Mensual (c/ ITBIS):");
		lblCargoMensual.setBounds(12, 339, 200, 14);
		contentPanel.add(lblCargoMensual);

		JLabel lblNewLabel_2 = new JLabel("ID del Cliente:");
		lblNewLabel_2.setBounds(12, 13, 145, 16);
		contentPanel.add(lblNewLabel_2);

		txtIdcliente = new JTextField();
		txtIdcliente.setBounds(12, 32, 200, 33);
		contentPanel.add(txtIdcliente);
		txtIdcliente.setColumns(10);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = txtIdcliente.getText().trim();
				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor ingrese id del cliente", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Cliente cli = Sistema.getInstance().buscarClientePorId(id);
				if (cli != null) {
					clienteActual = cli;
					txtNomCliente.setText(cli.getNombre());
					txtDireCliente.setText(cli.getDireccion());
					txtTelCliente.setText(cli.getTelefono());
					txtContrasena.setText(cli.getContrasena() != null ? cli.getContrasena() : "");
				} else {
					clienteActual = null;
					txtNomCliente.setText("");
					txtDireCliente.setText("");
					txtTelCliente.setText("");
					txtContrasena.setText("");
					JOptionPane.showMessageDialog(null, "Cliente no encontrado. Complete los campos para registrarlo.", "Información", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(222, 31, 97, 34);
		contentPanel.add(btnNewButton);

		JLabel lblNombreCliente = new JLabel("Nombre del Cliente:");
		lblNombreCliente.setBounds(12, 78, 145, 16);
		contentPanel.add(lblNombreCliente);

		txtNomCliente = new JTextField();
		txtNomCliente.setColumns(10);
		txtNomCliente.setBounds(12, 98, 428, 33);
		contentPanel.add(txtNomCliente);

		JLabel lblDireccinCliente = new JLabel("Direcci\u00F3n del Cliente:");
		lblDireccinCliente.setBounds(12, 144, 170, 16);
		contentPanel.add(lblDireccinCliente);

		txtDireCliente = new JTextField();
		txtDireCliente.setColumns(10);
		txtDireCliente.setBounds(12, 165, 428, 33);
		contentPanel.add(txtDireCliente);

		JLabel lblTlefonoCliente = new JLabel("T\u00E9lefono del Cliente:");
		lblTlefonoCliente.setBounds(12, 211, 170, 16);
		contentPanel.add(lblTlefonoCliente);

		txtTelCliente = new JTextField();
		txtTelCliente.setColumns(10);
		txtTelCliente.setBounds(12, 230, 200, 33);
		contentPanel.add(txtTelCliente);

		txtContrasena = new JTextField();
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(240, 230, 200, 33);
		contentPanel.add(txtContrasena);

		JLabel lblContraseaCliente = new JLabel("Contrase\u00F1a del Cliente:");
		lblContraseaCliente.setBounds(240, 211, 176, 14);
		contentPanel.add(lblContraseaCliente);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				crearBtn = new JButton(esEdicion ? "Modificar" : "Crear");
				crearBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						registrarOModificarContrato();
					}
				});
				crearBtn.setActionCommand("OK");
				buttonPane.add(crearBtn);
				getRootPane().setDefaultButton(crearBtn);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		cargarCombos();

		if (esEdicion) {
			cargarDatosEdicion();
		} else {
			txtIDcontrato.setText(generarIdContrato());
			txtIDcontrato.setEditable(false);
		}
	}

	private float calcularCargoConItbis(float precioBase) {
		return precioBase * (1.0f + TASA_ITBIS);
	}

	private void cargarCombos() {
		cbxComerciales.removeAllItems();
		cbxPlanes.removeAllItems();

		cbxPlanes.addItem("<<Seleccione un plan>>");
		for (Plan plan : Sistema.getInstance().getMisPlanes()) {
			cbxPlanes.addItem(plan.getNombre());
		}

		cbxComerciales.addItem("<<Seleccione un comercial>>");
		for (Empleado emp : Sistema.getInstance().getMisEmpleados()) {
			if (emp instanceof Comercial) {
				Comercial com = (Comercial) emp;
				cbxComerciales.addItem(com.getIdEmpleado() + " - " + com.getNombre());
			}
		}
	}

	private Plan obtenerPlanSeleccionado() {
		if (cbxPlanes.getSelectedIndex() <= 0 || cbxPlanes.getSelectedItem() == null) {
			return null;
		}
		String nombrePlan = cbxPlanes.getSelectedItem().toString();
		for (Plan plan : Sistema.getInstance().getMisPlanes()) {
			if (nombrePlan.equals(plan.getNombre())) {
				return plan;
			}
		}
		return null;
	}

	private Comercial obtenerComercialSeleccionado() {
		if (cbxComerciales.getSelectedIndex() <= 0 || cbxComerciales.getSelectedItem() == null) {
			return null;
		}
		String item = cbxComerciales.getSelectedItem().toString();
		String idCom = item.split(" - ", 2)[0].trim();

		for (Empleado emp : Sistema.getInstance().getMisEmpleados()) {
			if (emp instanceof Comercial) {
				Comercial com = (Comercial) emp;
				if (idCom.equals(com.getIdEmpleado())) {
					return com;
				}
			}
		}
		return null;
	}

	private void cargarDatosEdicion() {
		txtIDcontrato.setText(contratoAEditar.getIdContrato());
		txtIDcontrato.setEditable(false);

		clienteActual = contratoAEditar.getCliente();
		if (clienteActual != null) {
			txtIdcliente.setText(clienteActual.getIdCliente());
			txtIdcliente.setEditable(false);
			txtNomCliente.setText(clienteActual.getNombre());
			txtDireCliente.setText(clienteActual.getDireccion());
			txtTelCliente.setText(clienteActual.getTelefono());
			txtContrasena.setText(clienteActual.getContrasena() != null ? clienteActual.getContrasena() : "");
		}

		if (contratoAEditar.getPlan() != null) {
			cbxPlanes.setSelectedItem(contratoAEditar.getPlan().getNombre());
		} else {
			cbxPlanes.setSelectedIndex(0);
		}

		if (contratoAEditar.getComercialAsignado() != null) {
			Comercial com = contratoAEditar.getComercialAsignado();
			cbxComerciales.setSelectedItem(com.getIdEmpleado() + " - " + com.getNombre());
		} else {
			cbxComerciales.setSelectedIndex(0);
		}

		if (contratoAEditar.getFechaInicio() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			txtFecha.setText(sdf.format(contratoAEditar.getFechaInicio()));
		} else {
			txtFecha.setText("dd/mm/yyyy");
		}

		txtCargoMensual.setText(String.format(Locale.US, "%.2f", contratoAEditar.getCargoFijoMensual()));
	}

	private void registrarOModificarContrato() {
		String idContrato = txtIDcontrato.getText().trim();
		String idCliente = txtIdcliente.getText().trim();
		String nombreCli = txtNomCliente.getText().trim();
		String contrasenaCli = txtContrasena.getText().trim();
		String direCli = txtDireCliente.getText().trim();
		String telCli = txtTelCliente.getText().trim();
		String fechaStr = txtFecha.getText().trim();

		if (idCliente.isEmpty() || nombreCli.isEmpty() || direCli.isEmpty() || telCli.isEmpty() || fechaStr.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Complete todos los datos del cliente y la fecha de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (clienteActual == null && contrasenaCli.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese una contraseña para el nuevo cliente.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Plan planSel = obtenerPlanSeleccionado();
		Comercial comercialSel = obtenerComercialSeleccionado();
		if (planSel == null || comercialSel == null) {
			JOptionPane.showMessageDialog(this, "Seleccione un plan y un comercial válidos.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		Date fechaInicio;
		try {
			fechaInicio = sdf.parse(fechaStr);
		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		float cargoMensual = calcularCargoConItbis(planSel.getPrecioBase());

		if (clienteActual == null) {
			clienteActual = new Cliente(nombreCli, contrasenaCli, idCliente, telCli, direCli, false);
			Sistema.getInstance().registrarCliente(clienteActual);
		} else {
			clienteActual.setNombre(nombreCli);
			clienteActual.setDireccion(direCli);
			clienteActual.setTelefono(telCli);
			if (!contrasenaCli.isEmpty()) {
				clienteActual.setContrasena(contrasenaCli);
			}
		}

		if (esEdicion) {
			contratoAEditar.setPlan(planSel);
			contratoAEditar.setComercialAsignado(comercialSel);
			contratoAEditar.setFechaInicio(fechaInicio);
			contratoAEditar.setCargoFijoMensual(cargoMensual);

			JOptionPane.showMessageDialog(this, "¡Contrato modificado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Contrato nuevoContrato = new Contrato(idContrato, clienteActual, planSel, comercialSel, fechaInicio, true, cargoMensual);
			Sistema.getInstance().registrarContrato(nuevoContrato);
			comercialSel.setVentasRealizadas(comercialSel.getVentasRealizadas() + 1);

			JOptionPane.showMessageDialog(this, "¡Contrato registrado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		}

		dispose();
	}

	private String generarIdContrato() {
		int num = Sistema.getInstance().getMisContratos().size() + 1;
		return "CONT-" + num;
	}
}