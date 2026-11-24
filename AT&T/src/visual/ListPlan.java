package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import logico.Contrato;
import logico.Factura;
import logico.Plan;
import logico.Sistema;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListPlan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton cancelarBtn;
	private JButton eliminarBtn;
	private JButton desgloceBtn;
	private JTable table;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListPlan dialog = new ListPlan();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListPlan() {
		setTitle("Lista de Planes");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
				desgloceBtn = new JButton("Modificar/Detallar");
				desgloceBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						abrirDetallePlan();
					}
				});
				buttonPane.add(desgloceBtn);
			}
			{
				eliminarBtn = new JButton("Eliminar");
				eliminarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eliminarPlan();
					}
				});
				buttonPane.add(eliminarBtn);
			}
			{
				cancelarBtn = new JButton("Cancelar");
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
		cargarPlanes();
	}
	private void cargarPlanes() {
		String[] columna = {"Id", "Nombre", "Estado", "Precio", "Cantidad de Servicios"};
		modelo.setDataVector(null, columna);
		for (Plan plan: Sistema.getInstance().getMisPlanes()) {
			String nombre= plan.getNombre();
			String estado = plan.isEstado() ? "En uso" : "Disponible";
			int cantServicios= plan.getMisServicios().size();
				
			Object[] fila= {plan.getIdPlan(), nombre, estado, plan.getPrecioBase(), cantServicios};
		modelo.addRow(fila);
		}
		desgloceBtn.setEnabled(false);
		eliminarBtn.setEnabled(false);
	}	
	private void configurarSeleccionTabla() {
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!e.getValueIsAdjusting()) {
					boolean seleccionada= table.getSelectedRow() != -1;
					desgloceBtn.setEnabled(seleccionada);
					eliminarBtn.setEnabled(seleccionada);
				}
			}
		});
	}
	private void abrirDetallePlan() {
		int fila= table.getSelectedRow();
		if (fila == -1) {
			return;
		}
		String idPlan= modelo.getValueAt(fila, 0).toString();
		Plan plan= Sistema.getInstance().buscarPlanPorId(idPlan);
		
		if (plan != null) {
			RegPlan regPlan= new RegPlan(plan);
			regPlan.setModal(true);
			regPlan.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "No se encontró el plan seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void eliminarPlan() {
		int fila = table.getSelectedRow();
		if (fila == -1) {
			return;
		}

		Object valor = modelo.getValueAt(fila, 0);
		if (valor == null) {
			return;
		}

		String idPlan = valor.toString();
		Plan plan= Sistema.getInstance().buscarPlanPorId(idPlan);
		
		if (plan == null) {
			return;
		}
		
		for (Contrato contrato: Sistema.getInstance().getMisContratos()) {
			if (!contrato.getEstadoContrato() || contrato.getPlan() == null) {
				continue;
			} 
			
			if (plan.getIdPlan().equals(contrato.getPlan().getIdPlan())) {
				JOptionPane.showConfirmDialog(this, "No se puede eliminar un plan que este activo en un contrato.", "Error", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar este plan?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
	
		if (opcion == JOptionPane.YES_OPTION) {
			Sistema.getInstance().getMisPlanes().remove(plan);
			JOptionPane.showConfirmDialog(this, "Plan eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			cargarPlanes();
		}
	}
}
