package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

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

import logico.Administrativo;
import logico.Comercial;
import logico.Contrato;
import logico.Empleado;
import logico.Sistema;

public class NominaEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel modelo;
	private JButton btnSalir;
	private JButton btnEditar;
	private JButton btnDespedir;

	public static void main(String[] args) {
		try {
			NominaEmpleado dialog = new NominaEmpleado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NominaEmpleado() {
		setTitle("Nómina de Empleados");
		setBounds(100, 100, 800, 500);
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
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(modelo);
		scrollPane.setViewportView(table);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDespedir = new JButton("Despedir");
				btnDespedir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						despedirEmpleado();
					}
				});
				buttonPane.add(btnDespedir);
			}
			{
				btnEditar = new JButton("Modificar");
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						editarEmpleado();
					}
				});
				buttonPane.add(btnEditar);
				getRootPane().setDefaultButton(btnEditar);
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
		cargarEmpleados();
	}

	private void cargarEmpleados() {
		String[] columnas = { "Id", "Nombre", "Puesto", "Tiempo", "Tipo", "Salario" };
		modelo.setDataVector(null, columnas);

		for (Empleado emp : Sistema.getInstance().getMisEmpleados()) {
			String id = emp.getIdEmpleado();
			String nombre = emp.getNombre();
			String puesto = emp.getPuesto();
			String tiempo = calcularTiempoEnEmpresa(emp.getFechaIngreso());

			String tipo = "Empleado";
			float salario = emp.getHorasTrabajadas() * emp.getPagoXHora();

			if (emp instanceof Administrativo) {
				tipo = "Administrador";
			} else if (emp instanceof Comercial) {
				tipo = "Comercial";
				Comercial com = (Comercial) emp;
				salario += com.getVentasRealizadas() * com.getComisionPorVenta();
			}

			Object[] fila = { id, nombre, puesto, tiempo, tipo, salario };
			modelo.addRow(fila);
		}

		btnEditar.setEnabled(false);
		btnDespedir.setEnabled(false);
	}

	private String calcularTiempoEnEmpresa(Date fechaIngreso) {
		if (fechaIngreso == null) {
			return "N/A";
		}

		LocalDate inicio = fechaIngreso.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
		LocalDate hoy = LocalDate.now();

		if (inicio.isAfter(hoy)) {
			return "0 días";
		}

		Period periodo = Period.between(inicio, hoy);
		int anios = periodo.getYears();
		int meses = periodo.getMonths();
		int dias = periodo.getDays();

		if (anios > 0) {
			return anios + (anios == 1 ? " año, " : " años, ")
					+ meses + (meses == 1 ? " mes" : " meses");
		}
		if (meses > 0) {
			return meses + (meses == 1 ? " mes, " : " meses, ")
					+ dias + (dias == 1 ? " día" : " días");
		}
		return dias + (dias == 1 ? " día" : " días");
	}

	private void configurarSeleccionTabla() {
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					boolean haySeleccion = table.getSelectedRow() != -1;
					btnEditar.setEnabled(haySeleccion);
					btnDespedir.setEnabled(haySeleccion);
				}
			}
		});
	}

	private Empleado obtenerEmpleadoSeleccionado() {
		int fila = table.getSelectedRow();
		if (fila == -1) {
			return null;
		}
		Object valorId = modelo.getValueAt(fila, 0);
		if (valorId == null) {
			return null;
		}
		String id = valorId.toString();
		for (Empleado emp : Sistema.getInstance().getMisEmpleados()) {
			if (id.equals(emp.getIdEmpleado())) {
				return emp;
			}
		}
		return null;
	}

	private void editarEmpleado() {
		Empleado emp = obtenerEmpleadoSeleccionado();
		if (emp == null) {
			return;
		}

		RegEmpleado reg = new RegEmpleado(emp);
		reg.setModal(true);
		reg.setVisible(true);
		cargarEmpleados();
	}

	private boolean tieneContratoActivo(Empleado emp) {
		for (Contrato contrato : Sistema.getInstance().getMisContratos()) {
			if (!contrato.getEstadoContrato()) {
				continue;
			}
			Comercial asociado = contrato.getComercialAsignado();
			if (asociado != null && emp.getIdEmpleado().equals(asociado.getIdEmpleado())) {
				return true;
			}
		}
		return false;
	}

	private void despedirEmpleado() {
		Empleado emp = obtenerEmpleadoSeleccionado();
		if (emp == null) {
			return;
		}

		if (tieneContratoActivo(emp)) {
			JOptionPane.showMessageDialog(
					this,
					"No se puede despedir a este empleado porque está asociado a un contrato activo.",
					"Contrato activo",
					JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		int opcion = JOptionPane.showConfirmDialog(
				this,
				"¿Está seguro que desea despedir a " + emp.getNombre() + "?",
				"Confirmar despido",
				JOptionPane.YES_NO_OPTION
		);

		if (opcion == JOptionPane.YES_OPTION) {
			Sistema.getInstance().getMisEmpleados().remove(emp);
			JOptionPane.showMessageDialog(
					this,
					"Empleado dado de baja de la nómina.",
					"Éxito",
					JOptionPane.INFORMATION_MESSAGE
			);
			cargarEmpleados();
		}
	}
}