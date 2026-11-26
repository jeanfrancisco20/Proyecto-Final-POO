package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Contrato;
import logico.Factura;
import logico.Sistema;

public class ListContrato extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public static DefaultTableModel model;
	public static Object row[];
	private JTable table;
	private JButton vdbtn;
	private JButton deletebtn;
	private Contrato contrato = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListContrato dialog = new ListContrato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListContrato() {
		setTitle("Listar contratos");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				String header[] = { "ID", "Cliente asociado", "Empleado asociado", "Plan asociado", "Estado",
						"Fecha de inicio", "Cargo mensual" };
				model = new DefaultTableModel();
				model.setColumnIdentifiers(header);
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						int i = table.getSelectedRow();
						if (i > -1) {
							vdbtn.setEnabled(true);
							deletebtn.setEnabled(true);
							contrato = Sistema.getInstance().buscarContratoPorId(model.getValueAt(i, 0).toString());
						}
					}
				});
				table.setModel(model);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				deletebtn = new JButton("Eliminar");
				deletebtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (contrato == null) {
							return;
						}

						Factura facturaAsociada = Sistema.getInstance().buscarFacturaDeContrato(contrato);

						if (facturaAsociada == null || !facturaAsociada.isPagada()) {
							JOptionPane.showMessageDialog(
								null,
								"No se puede eliminar el contrato " + contrato.getIdContrato()
									+ " porque su factura no está pagada o no existe.",
								"Factura pendiente",
								JOptionPane.WARNING_MESSAGE
							);
							return;
						}

						int op = JOptionPane.showConfirmDialog(
							null,
							"Confirmas eliminar el contrato: " + contrato.getIdContrato(),
							"Confirmar",
							JOptionPane.WARNING_MESSAGE
						);

						if (op == JOptionPane.YES_OPTION) {
							Sistema.getInstance().eliminarContrato(contrato.getIdContrato());
							if (facturaAsociada.getIdFactura() != null) {
								Sistema.getInstance().desactivarDespuesDeFactura(facturaAsociada.getIdFactura());
							}
							loadContratos();
							contrato = null;
							deletebtn.setEnabled(false);
						}
					}
				});
				deletebtn.setEnabled(false);
				buttonPane.add(deletebtn);
			}
			{
				vdbtn = new JButton("Modificar");
				vdbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						RegContrato rg = new RegContrato(contrato);
						rg.setModal(true);
						rg.setVisible(true);
						loadContratos();
						vdbtn.setEnabled(false);
						deletebtn.setEnabled(false);
					}
				});
				vdbtn.setEnabled(false);
				buttonPane.add(vdbtn);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadContratos();
	}

	protected void loadContratos() {
		model.setRowCount(0);
		if (table != null) {
			table.clearSelection();
		}
		contrato = null;
		if (deletebtn != null)
			deletebtn.setEnabled(false);
		if (vdbtn != null)
			vdbtn.setEnabled(false);

		for (Contrato aux : Sistema.getInstance().getMisContratos()) {
			String clienteStr = (aux.getCliente() != null)
					? aux.getCliente().getIdCliente() + " - " + aux.getCliente().getNombre()
					: "";
			String empleadoStr = (aux.getComercialAsignado() != null)
					? aux.getComercialAsignado().getIdEmpleado() + " - " + aux.getComercialAsignado().getNombre()
					: "";
			String planStr = (aux.getPlan() != null) ? aux.getPlan().getIdPlan() + " - " + aux.getPlan().getNombre()
					: "";
			String estadoStr = aux.getEstadoContrato() ? "Activo" : "Inactivo";

			row = new Object[model.getColumnCount()];
			row[0] = aux.getIdContrato();
			row[1] = clienteStr;
			row[2] = empleadoStr;
			row[3] = planStr;
			row[4] = estadoStr;
			row[5] = (aux.getFechaInicio() != null) ? aux.getFechaInicio().toString() : "";
			row[6] = aux.getCargoFijoMensual();
			model.addRow(row);
		}
	}

}
