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
	private JTextField txtEstadoContrato;
	private JTextField txtCargoMensual;
	
	private JComboBox cbxClientes;
	private JComboBox cbxPlanes;
	private JComboBox cbxComerciales;
	
	private Contrato contratoAEditar = null;
	private boolean esEdicion = false;
	private JButton crearBtn;

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
		setBounds(100, 100, 470, 310);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Identificador:");
		lblNewLabel.setBounds(10, 11, 100, 14);
		contentPanel.add(lblNewLabel);
		
		txtIDcontrato = new JTextField();
		txtIDcontrato.setBounds(10, 26, 200, 20);
		contentPanel.add(txtIDcontrato);
		txtIDcontrato.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Clientes:");
		lblNewLabel_1.setBounds(10, 57, 80, 14);
		contentPanel.add(lblNewLabel_1);
		
		cbxClientes = new JComboBox<>();
		cbxClientes.setBounds(10, 74, 200, 20);
		contentPanel.add(cbxClientes);
		
		cbxPlanes = new JComboBox<>();
		cbxPlanes.setBounds(220, 74, 200, 20);
		contentPanel.add(cbxPlanes);
		
		JLabel lblPlanes = new JLabel("Planes:");
		lblPlanes.setBounds(220, 57, 46, 14);
		contentPanel.add(lblPlanes);
		
		cbxComerciales = new JComboBox<>();
		cbxComerciales.setBounds(10, 122, 200, 20);
		contentPanel.add(cbxComerciales);
		
		JLabel lblComerciales = new JLabel("Comerciales:");
		lblComerciales.setBounds(10, 105, 80, 14);
		contentPanel.add(lblComerciales);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(220, 122, 200, 20);
		contentPanel.add(txtFecha);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio:");
		lblFechaDeInicio.setBounds(220, 107, 100, 14);
		contentPanel.add(lblFechaDeInicio);
		
		txtEstadoContrato = new JTextField();
		txtEstadoContrato.setColumns(10);
		txtEstadoContrato.setBounds(10, 174, 200, 20);
		contentPanel.add(txtEstadoContrato);
		
		JLabel lblEstadoDeContrato = new JLabel("Estado de contrato:");
		lblEstadoDeContrato.setBounds(10, 159, 120, 14);
		contentPanel.add(lblEstadoDeContrato);
		
		txtCargoMensual = new JTextField();
		txtCargoMensual.setColumns(10);
		txtCargoMensual.setBounds(220, 174, 200, 20);
		contentPanel.add(txtCargoMensual);
		
		JLabel lblCargoMensual = new JLabel("Cargo mensual:");
		lblCargoMensual.setBounds(220, 159, 100, 14);
		contentPanel.add(lblCargoMensual);
		
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
		cbxClientes.removeAllItems();
		cbxComerciales.removeAllItems();
		cbxPlanes.removeAllItems();
		for (Cliente cliente : Sistema.getInstance().getMisClientes()) {
			cbxClientes.addItem(cliente);
		}
		for (Plan plan : Sistema.getInstance().getMisPlanes()) {
			cbxPlanes.addItem(plan);
		}
		for (Empleado emp : Sistema.getInstance().getMisEmpleados()) {
			if (emp instanceof Comercial) {
				cbxComerciales.addItem((Comercial) emp);
			}
		}
	}

	private void cargarDatosEdicion() {
		txtIDcontrato.setText(contratoAEditar.getIdContrato());
		txtIDcontrato.setEditable(false);
		
		cbxClientes.setSelectedItem(contratoAEditar.getCliente());
		cbxPlanes.setSelectedItem(contratoAEditar.getPlan());
		cbxComerciales.setSelectedItem(contratoAEditar.getComercialAsignado());
		
		txtFecha.setText(contratoAEditar.getFechaInicio() != null ? contratoAEditar.getFechaInicio().toString() : "");
		txtEstadoContrato.setText(contratoAEditar.getEstadoContrato());
		txtCargoMensual.setText(String.valueOf(contratoAEditar.getCargoFijoMensual()));
	}

	private void registrarOModificarContrato() {
		String id = txtIDcontrato.getText().trim();
		Cliente clienteSel = (Cliente) cbxClientes.getSelectedItem();
		Plan planSel = (Plan) cbxPlanes.getSelectedItem();
		Comercial comercialSel = (Comercial) cbxComerciales.getSelectedItem();
		String estado = txtEstadoContrato.getText().trim();
		
		if (clienteSel == null || planSel == null || comercialSel == null || estado.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		float cargoMensual = 0.0f;
		try {
			cargoMensual = Float.parseFloat(txtCargoMensual.getText().trim());
		} catch (NumberFormatException e) {
			cargoMensual = planSel.getPrecioBase(); 
		}

		if (esEdicion) {
			contratoAEditar.setCliente(clienteSel);
			contratoAEditar.setPlan(planSel);
			contratoAEditar.setComercialAsignado(comercialSel);
			contratoAEditar.setEstadoContrato(estado);
			contratoAEditar.setCargoFijoMensual(cargoMensual);
			
			JOptionPane.showMessageDialog(this, "¡Contrato modificado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Contrato nuevoContrato = new Contrato(id, clienteSel, planSel, comercialSel, null, estado, cargoMensual);
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