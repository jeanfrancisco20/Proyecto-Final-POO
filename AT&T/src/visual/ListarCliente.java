package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import logico.Cliente;
import logico.Contrato;
import logico.Sistema;

public class ListarCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel modelo;
	private JButton btnEliminar;
	private JButton btnSalir;
	private JButton btnEditar;

	public static void main(String[] args) {
		try {
			ListarCliente dialog = new ListarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListarCliente() {
		setTitle("Listar Cliente");
		setBounds(100, 100, 800, 600);
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
					modelo = new DefaultTableModel() {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					table = new JTable(modelo);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEditar = new JButton("Editar");
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						abrirDetalleCliente();
					}
				});
				btnEditar.setActionCommand("OK");
				buttonPane.add(btnEditar);
				getRootPane().setDefaultButton(btnEditar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eliminarCliente();
					}
				});
				buttonPane.add(btnEliminar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}

		configurarSeleccionTabla();
		cargarClientes();
	}

	private void cargarClientes() {
		String[] columna = { "Id", "Nombre", "Telefono", "Estado" };
		modelo.setDataVector(null, columna);
		for (Cliente cliente : Sistema.getInstance().getMisClientes()) {
			String telefono = cliente.getTelefono();
			String estado = cliente.isDesactivado() ? "Desactivado" : "Activo";

			Object[] fila = { cliente.getIdCliente(), cliente.getNombre(), telefono, estado };
			modelo.addRow(fila);
		}
		btnEliminar.setEnabled(false);
		btnEditar.setEnabled(false);
	}

	private void configurarSeleccionTabla() {
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					boolean seleccionada = table.getSelectedRow() != -1;
					btnEliminar.setEnabled(seleccionada);
					btnEditar.setEnabled(seleccionada);
				}
			}
		});
	}

	private void abrirDetalleCliente() {
		int fila = table.getSelectedRow();
		if (fila == -1) {
			return;
		}
		String idCliente = modelo.getValueAt(fila, 0).toString();
		Cliente cliente = Sistema.getInstance().buscarClientePorId(idCliente);

		if (cliente != null) {
			DetalleCliente detalleCliente = new DetalleCliente(cliente);
			detalleCliente.setModal(true);
			detalleCliente.setVisible(true);
			cargarClientes();
		} else {
			JOptionPane.showMessageDialog(this, "No se encontro el cliente seleccionado.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void eliminarCliente() {
		int fila = table.getSelectedRow();
		if (fila == -1) {
			return;
		}

		Object valor = modelo.getValueAt(fila, 0);
		if (valor == null) {
			return;
		}

		String idCliente = valor.toString();
		Cliente cliente = Sistema.getInstance().buscarClientePorId(idCliente);

		if (cliente == null) {
			return;
		}

		for (Contrato contrato : Sistema.getInstance().getMisContratos()) {
			if (!contrato.getEstadoContrato() || contrato.getCliente() == null) {
				continue;
			}

			if (cliente.getIdCliente().equals(contrato.getCliente().getIdCliente())) {
				JOptionPane.showMessageDialog(this,
						"No se puede eliminar un cliente que este activo en un contrato.", "Error",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar este cliente?",
				"Confirmar eliminación", JOptionPane.YES_NO_OPTION);

		if (opcion == JOptionPane.YES_OPTION) {
			Sistema.getInstance().getMisClientes().remove(cliente);
			JOptionPane.showMessageDialog(this, "Cliente eliminado con éxito.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
			cargarClientes();
		}
	}
}
