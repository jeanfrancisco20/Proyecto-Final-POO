package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Contrato;
import logico.Sistema;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListContrato extends JDialog {

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
				String header[] = {"ID", "Fecha de inicio", "Cargo mensual"};
				model = new DefaultTableModel();
				model.setColumnIdentifiers(header);
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						int i = table.getSelectedRow();
						if(i > -1) {
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
						if(contrato != null) {
							int op = JOptionPane.showConfirmDialog(null, "Confirmas eliminar el contrato: "+contrato.getIdContrato(), "Confirmar", JOptionPane.WARNING_MESSAGE);
							if(op == JOptionPane.YES_OPTION) {
								Sistema.getInstance().eliminarContrato(contrato.getIdContrato());
								loadContratos();
								deletebtn.setEnabled(false);
							}
						}
					}
				});
				deletebtn.setEnabled(false);
				deletebtn.setActionCommand("OK");
				buttonPane.add(deletebtn);
				getRootPane().setDefaultButton(deletebtn);
			}
			{
				vdbtn = new JButton("Ver detalles/Editar");
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
		loadContratos();
	}

	protected void loadContratos() {
		model.setRowCount(0);
		if(table != null) {
			table.clearSelection();
		}
		contrato = null;
		if(deletebtn != null) deletebtn.setEnabled(false);
		if(vdbtn != null)vdbtn.setEnabled(false);
		
		for(Contrato aux : Sistema.getInstance().getMisContratos()) {
			row = new Object[model.getColumnCount()];
			row[0] = aux.getIdContrato();
			row[1] = aux.getFechaInicio().toString();
			row[2] = aux.getCargoFijoMensual();
			model.addRow(row);
		}
	}

}
