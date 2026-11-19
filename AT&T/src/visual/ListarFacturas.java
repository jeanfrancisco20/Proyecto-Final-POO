package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import logico.Factura;
import logico.Sistema;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarFacturas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton pagarBtn;
	private JButton detalleBtn;
	private JTable table;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarFacturas dialog = new ListarFacturas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarFacturas() {
		setTitle("Listado de Facturas");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					modelo= new DefaultTableModel() {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					table = new JTable();
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				pagarBtn = new JButton("Pagar");
				pagarBtn.setEnabled(false);
				pagarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pagarFacturaSeleccionada();
					}
				});
				pagarBtn.setActionCommand("OK");
				buttonPane.add(pagarBtn);
				getRootPane().setDefaultButton(pagarBtn);
			}
			{
				detalleBtn = new JButton("Detalle");
				detalleBtn.setEnabled(false);
				detalleBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						abrirDetalleFactura();
					}
				});
				buttonPane.add(detalleBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
		configurarSeleccionTabla();
		cargarFacturas();
	}
	
	private void configurarSeleccionTabla() {
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!e.getValueIsAdjusting()) {
					boolean seleccionada= table.getSelectedRow() != -1;
					pagarBtn.setEnabled(seleccionada);
					detalleBtn.setEnabled(seleccionada);
				}
			}
		});
	}
	
	private void cargarFacturas() {
		String[] columna = {"Id", "Cliente", "Estado", "Monto"};
		modelo.setDataVector(null, columna);
		for (Factura f: Sistema.getInstance().getMisFacturas()) {
			String nombreCliente= f.getCliente().getNombre();
			String estado = f.isPagada() ? "Pagada" : "La debe";
			Object[] fila= {f.getIdFactura(), nombreCliente, estado, f.getMontoTotal()};
		modelo.addRow(fila);
		}
		pagarBtn.setEnabled(false);
		detalleBtn.setEnabled(false);
	}	
	
	private void abrirDetalleFactura() {
		int fila= table.getSelectedRow();
		if (fila == -1) {
			return;
		}
		String idFactura= modelo.getValueAt(fila, 0).toString();
		Factura factura= Sistema.getInstance().buscarFacturaPorId(idFactura);
		
		if (factura != null) {
			DetalleFactura detalleFactura= new DetalleFactura(factura);
			detalleFactura.setModal(true);
			detalleFactura.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "No se encontró la factura seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void pagarFacturaSeleccionada() {
		int fila= table.getSelectedRow();
		if (fila == -1) {
			return;
		}
		
		String idFactura = modelo.getValueAt(fila, 0).toString();
		Factura factura = Sistema.getInstance().buscarFacturaPorId(idFactura);

		if (factura == null) {
			return;
		}

		if (factura.isPagada()) {
			JOptionPane.showMessageDialog(this, "Esta factura ya está pagada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Sistema.getInstance().registrarPagoFactura(idFactura);
		JOptionPane.showMessageDialog(this, "¡Factura pagada con éxito, compadre!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		cargarFacturas();
	}
}
