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

import logico.Cliente;
import logico.Contrato;
import logico.Factura;
import logico.Sistema;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class RegFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	private Factura f = null;
	private JTextField txtIdFactura;
	private JTextField txtFechaEmision;
	private JTextField txtFechaPago;
	private JTextField txtMonto;
	private JComboBox<Object> cbxClientes;
	private JComboBox<Object> cbxContratos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegFactura dialog = new RegFactura(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegFactura(Factura aux) {
		if(aux != null) {
			f = aux;
			setTitle("Modificar factura");
		}else {
			setTitle("Crear nueva factura");
		}
		setBounds(100, 100, 450, 327);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Identificador:");
		lblNewLabel.setBounds(12, 13, 86, 16);
		contentPanel.add(lblNewLabel);
		
		txtIdFactura = new JTextField();
		txtIdFactura.setBounds(12, 31, 201, 22);
		txtIdFactura.setText(generarIdFactura());
		contentPanel.add(txtIdFactura);
		txtIdFactura.setColumns(10);
		
		JLabel label = new JLabel("Clientes:");
		label.setBounds(12, 66, 80, 14);
		contentPanel.add(label);
		
	    cbxClientes = new JComboBox<Object>();
		cbxClientes.setBounds(12, 83, 200, 20);
		contentPanel.add(cbxClientes);
		
		JLabel lblContratos = new JLabel("Contratos:");
		lblContratos.setBounds(222, 66, 70, 14);
		contentPanel.add(lblContratos);
		
		cbxContratos = new JComboBox<Object>();
		cbxContratos.setBounds(222, 83, 200, 20);
		contentPanel.add(cbxContratos);
		
		JLabel lblFechaDeEmisin = new JLabel("Fecha de emisi\u00F3n:");
		lblFechaDeEmisin.setBounds(12, 116, 159, 16);
		contentPanel.add(lblFechaDeEmisin);
		
		txtFechaEmision = new JTextField();
		txtFechaEmision.setColumns(10);
		txtFechaEmision.setBounds(12, 134, 201, 22);
		contentPanel.add(txtFechaEmision);
		
		txtFechaPago = new JTextField();
		txtFechaPago.setColumns(10);
		txtFechaPago.setBounds(222, 134, 201, 22);
		contentPanel.add(txtFechaPago);
		
		JLabel lblFechaDePago = new JLabel("Fecha de pago:");
		lblFechaDePago.setBounds(222, 116, 159, 16);
		contentPanel.add(lblFechaDePago);
		
		JLabel lblMontoTotal = new JLabel("Monto total:");
		lblMontoTotal.setBounds(12, 169, 159, 16);
		contentPanel.add(lblMontoTotal);
		
		txtMonto = new JTextField();
		txtMonto.setColumns(10);
		txtMonto.setBounds(12, 187, 201, 22);
		contentPanel.add(txtMonto);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear");
				if(f != null) {
					okButton.setText("Modificar");
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(cbxClientes.getItemCount() == 0) {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						if(cbxContratos.getItemCount() == 0) {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un contrato", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						if(txtFechaEmision.getText().trim().isEmpty() || 
						   txtFechaPago.getText().trim().isEmpty() ||
						   txtMonto.getText().trim().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Debe llenar los campos", "Error", JOptionPane.ERROR_MESSAGE);
						}
						float monto = 0.0f;
						try {
							monto = Float.parseFloat(txtMonto.getText().trim());
						} catch (NumberFormatException e) {
							// TODO: handle exception
						}
						limpiar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
			cargarCombos();
		}
	}

	protected void limpiar() {
		txtFechaEmision.setText("");
		txtFechaPago.setText("");
		txtMonto.setText("");
		cbxClientes.removeAllItems();
		cbxContratos.removeAllItems();
		cargarCombos();
	}

	private String generarIdFactura() {
		int num = Sistema.getInstance().getMisFacturas().size() + 1;
		return "0000" + num;
	}
	
	private void cargarCombos() {
		cbxClientes.removeAllItems();
		cbxContratos.removeAllItems();
		
		cbxContratos.addItem("<<Seleccione contrato>>");
		cbxClientes.addItem("<<Seleccione cliente>>");
		for(Cliente auxCliente : Sistema.getInstance().getMisClientes()) {
			cbxClientes.addItem(auxCliente.getIdCliente()+" - "+auxCliente.getNombre());
		}
		
		for(Contrato auxContrato : Sistema.getInstance().getMisContratos()) {
			cbxContratos.addItem(auxContrato.getIdContrato()+" - "+auxContrato.getCargoFijoMensual());
		}
	}
	
	
}
