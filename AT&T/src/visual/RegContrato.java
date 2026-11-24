package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import logico.Contrato;
import logico.Empleado;
import logico.Cliente;
import logico.Plan;
import logico.Comercial;
import logico.Sistema;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

public class RegContrato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIDcontrato;
	private JTextField txtFecha;
	private JTextField txtCargoMensual;
	private JComboBox cbxPlanes;
	private JComboBox cbxComerciales;
	
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
		setBounds(100, 100, 470, 416);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Identificador factura:");
		lblNewLabel.setBounds(224, 217, 156, 14);
		contentPanel.add(lblNewLabel);
		
		txtIDcontrato = new JTextField();
		txtIDcontrato.setBounds(224, 232, 200, 22);
		contentPanel.add(txtIDcontrato);
		txtIDcontrato.setColumns(10);
		
		cbxPlanes = new JComboBox<>();
		cbxPlanes.setBounds(12, 180, 200, 22);
		contentPanel.add(cbxPlanes);
		
		JLabel lblPlanes = new JLabel("Planes:");
		lblPlanes.setBounds(12, 163, 46, 14);
		contentPanel.add(lblPlanes);
		
		cbxComerciales = new JComboBox<>();
		cbxComerciales.setBounds(12, 232, 200, 22);
		contentPanel.add(cbxComerciales);
		
		JLabel lblComerciales = new JLabel("Comerciales:");
		lblComerciales.setBounds(12, 215, 80, 14);
		contentPanel.add(lblComerciales);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(224, 182, 200, 22);
		contentPanel.add(txtFecha);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio:");
		lblFechaDeInicio.setBounds(224, 167, 100, 14);
		contentPanel.add(lblFechaDeInicio);
		
		txtCargoMensual = new JTextField();
		txtCargoMensual.setColumns(10);
		txtCargoMensual.setBounds(12, 282, 200, 22);
		contentPanel.add(txtCargoMensual);
		
		JLabel lblCargoMensual = new JLabel("Cargo mensual:");
		lblCargoMensual.setBounds(12, 267, 100, 14);
		contentPanel.add(lblCargoMensual);
		
		JLabel lblNewLabel_2 = new JLabel("Identificador cliente:");
		lblNewLabel_2.setBounds(12, 13, 145, 16);
		contentPanel.add(lblNewLabel_2);
		
		txtIdcliente = new JTextField();
		txtIdcliente.setBounds(12, 32, 200, 22);
		contentPanel.add(txtIdcliente);
		txtIdcliente.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = txtIdcliente.getText().trim();
				if(id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor ingrese id del cliente", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Cliente cli = Sistema.getInstance().buscarClientePorId(id);
				if(cli != null) {
					clienteActual = cli;
					txtNomCliente.setText(cli.getNombre());
					txtDireCliente.setText(cli.getDireccion());
					txtTelCliente.setText(cli.getTelefono());
				}else {
					clienteActual = null;
					txtNomCliente.setText("");
					txtDireCliente.setText("");
					txtTelCliente.setText("");
				}
			}
		});
		btnNewButton.setBounds(222, 31, 97, 25);
		contentPanel.add(btnNewButton);
		
		JLabel lblNombreCliente = new JLabel("Nombre cliente:");
		lblNombreCliente.setBounds(12, 59, 145, 16);
		contentPanel.add(lblNombreCliente);
		
		txtNomCliente = new JTextField();
		txtNomCliente.setColumns(10);
		txtNomCliente.setBounds(12, 78, 200, 22);
		contentPanel.add(txtNomCliente);
		
		JLabel lblDireccinCliente = new JLabel("Direcci\u00F3n cliente:");
		lblDireccinCliente.setBounds(222, 59, 145, 16);
		contentPanel.add(lblDireccinCliente);
		
		txtDireCliente = new JTextField();
		txtDireCliente.setColumns(10);
		txtDireCliente.setBounds(222, 78, 200, 22);
		contentPanel.add(txtDireCliente);
		
		JLabel lblTlefonoCliente = new JLabel("T\u00E9lefono cliente:");
		lblTlefonoCliente.setBounds(12, 113, 145, 16);
		contentPanel.add(lblTlefonoCliente);
		
		txtTelCliente = new JTextField();
		txtTelCliente.setColumns(10);
		txtTelCliente.setBounds(12, 132, 200, 22);
		contentPanel.add(txtTelCliente);
		
		txtContrasena = new JTextField();
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(224, 132, 200, 22);
		contentPanel.add(txtContrasena);
		
		JLabel lblContraseaCliente = new JLabel("Contrase\u00F1a cliente:");
		lblContraseaCliente.setBounds(224, 117, 123, 14);
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

	private void cargarCombos() {
		cbxComerciales.removeAllItems();
		cbxPlanes.removeAllItems();
		cbxPlanes.addItem("<<Seleccione un plan>>");;
		for (Plan plan : Sistema.getInstance().getMisPlanes()) {
			cbxPlanes.addItem(plan);
		}
		cbxComerciales.addItem("<<Seleccione un comercial>>");;
		for (Empleado emp : Sistema.getInstance().getMisEmpleados()) {
			if (emp instanceof Comercial) {
				cbxComerciales.addItem((Comercial) emp);
			}
		}
	}

	private void cargarDatosEdicion() {
		txtIDcontrato.setText(contratoAEditar.getIdContrato());
		txtIDcontrato.setEditable(false);
		
		cbxPlanes.setSelectedItem(contratoAEditar.getPlan());
		cbxComerciales.setSelectedItem(contratoAEditar.getComercialAsignado());
		
		txtFecha.setText(contratoAEditar.getFechaInicio() != null ? contratoAEditar.getFechaInicio().toString() : "");
		txtCargoMensual.setText(String.valueOf(contratoAEditar.getCargoFijoMensual()));
	}

	private void registrarOModificarContrato() {
		String idContrato = txtIDcontrato.getText().trim();
		String idCliente = txtIdcliente.getText().trim();
		String nombreCli = txtNomCliente.getText().trim();
		String contrasenaCli = txtContrasena.getText().trim();
		String direCli = txtDireCliente.getText().trim();
		String telCli = txtTelCliente.getText().trim();
		Plan planSel = (Plan) cbxPlanes.getSelectedItem();
		Comercial comercialSel = (Comercial) cbxComerciales.getSelectedItem();
		
		
		if (idCliente.isEmpty() || nombreCli.isEmpty() || direCli.isEmpty() || telCli.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Complete todos los datos del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (cbxPlanes.getSelectedIndex() <= 0 || cbxComerciales.getSelectedIndex() <= 0) {
			JOptionPane.showMessageDialog(null, "Seleccione un plan y un comercial válidos.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		float cargoMensual = 0.0f;
		try {
			cargoMensual = Float.parseFloat(txtCargoMensual.getText().trim());
		} catch (NumberFormatException e) {
			cargoMensual = planSel.getPrecioBase(); 
		}

		if (clienteActual == null) {
			clienteActual = new Cliente(idCliente, nombreCli, contrasenaCli, telCli, direCli, true); 
			Sistema.getInstance().getMisClientes().add(clienteActual); 
		} else {
			clienteActual.setNombre(nombreCli);
			clienteActual.setDireccion(direCli);
			clienteActual.setTelefono(telCli);
		}
		
		if (esEdicion) {
			contratoAEditar.setPlan(planSel);
			contratoAEditar.setComercialAsignado(comercialSel);
			contratoAEditar.setCargoFijoMensual(cargoMensual);
			
			JOptionPane.showMessageDialog(this, "¡Contrato modificado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Contrato nuevoContrato = new Contrato(idContrato, clienteActual, planSel, comercialSel, null, true, cargoMensual);
			Sistema.getInstance().registrarContrato(nuevoContrato);
			
			JOptionPane.showMessageDialog(this, "¡Contrato registrado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		}
		
		dispose();
	}

	private String generarIdContrato() {
		int num = Sistema.getInstance().getMisContratos().size() + 1;
		return "0000" + num;
	}
}