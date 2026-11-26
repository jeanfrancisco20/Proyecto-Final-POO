package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import logico.Factura;
import logico.Sistema;

public class ListarFacturas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton pagarBtn;
	private JButton detalleBtn;
	private JTable table;
	private DefaultTableModel modelo;

	public static void main(String[] args) {
		try {
			ListarFacturas dialog = new ListarFacturas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListarFacturas() {
		setTitle("Listado de Facturas");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(modelo);
		scrollPane.setViewportView(table);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

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

		detalleBtn = new JButton("Detalle");
		detalleBtn.setEnabled(false);
		detalleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDetalleFactura();
			}
		});
		buttonPane.add(detalleBtn);

		JButton cancelarBtn = new JButton("Salir");
		cancelarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelarBtn.setActionCommand("Cancel");
		buttonPane.add(cancelarBtn);

		configurarSeleccionTabla();
		cargarFacturas();
	}

	private void configurarSeleccionTabla() {
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int fila = table.getSelectedRow();
					boolean seleccionada = fila != -1;
					detalleBtn.setEnabled(seleccionada);

					if (seleccionada) {
						String estado = modelo.getValueAt(fila, 2).toString();
						pagarBtn.setEnabled(!"Pagada".equalsIgnoreCase(estado));
					} else {
						pagarBtn.setEnabled(false);
					}
				}
			}
		});
	}

	private void limpiarTabla() {
		String[] columna = { "Id", "Nombre", "Estado", "Monto" };
		modelo.setRowCount(0);
		modelo.setColumnIdentifiers(columna);
		pagarBtn.setEnabled(false);
		detalleBtn.setEnabled(false);
	}

	private void cargarFacturas() {
		limpiarTabla();

		Set<String> idsAgregados = new HashSet<>();
		for (Factura f : Sistema.getInstance().getMisFacturas()) {
			if (f == null || f.getIdFactura() == null || !idsAgregados.add(f.getIdFactura())) {
				continue;
			}
			String nombreCliente = (f.getCliente() != null) ? f.getCliente().getNombre() : "Sin cliente";
			String estado = f.isPagada() ? "Pagada" : "Pendiente";
			Object[] fila = { f.getIdFactura(), nombreCliente, estado, f.getMontoTotal() };
			modelo.addRow(fila);
		}
	}

	private void abrirDetalleFactura() {
		int fila = table.getSelectedRow();
		if (fila == -1) {
			return;
		}
		String idFactura = modelo.getValueAt(fila, 0).toString();
		Factura factura = Sistema.getInstance().buscarFacturaPorId(idFactura);

		if (factura != null) {
			DetalleFactura detalleFactura = new DetalleFactura(factura);
			detalleFactura.setModal(true);
			detalleFactura.setVisible(true);
			cargarFacturas();
		} else {
			JOptionPane.showMessageDialog(this, "No se encontró la factura seleccionada.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void pagarFacturaSeleccionada() {
		int fila = table.getSelectedRow();
		if (fila == -1) {
			return;
		}

		String idFactura = modelo.getValueAt(fila, 0).toString();
		Factura factura = Sistema.getInstance().buscarFacturaPorId(idFactura);

		if (factura == null) {
			return;
		}

		if (factura.isPagada()) {
			JOptionPane.showMessageDialog(this, "Esta factura ya está pagada.", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Sistema.getInstance().registrarPagoFactura(idFactura);
		JOptionPane.showMessageDialog(this, "¡Factura pagada con éxito, compadre!", "Éxito",
				JOptionPane.INFORMATION_MESSAGE);
		cargarFacturas();
	}
}