package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Plan;
import logico.Servicio;
import logico.Sistema;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class RegPlan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton crearBtn;
	private JButton cancelarBtn;
	private JTextField idTxt;
	private JTextField nombreTxt;
	private JTextField descripcionTxt;
	private JTextField precioTxt;
	private JTextField costoTxt;
	private JComboBox dispoCbx;
	private JButton agregarBtn;
	private JComboBox selCbx;
	private ArrayList<Servicio> misServiciosTemp = new ArrayList<>();
	private float costoTotalAcumulado = 0.0f;
	private DefaultComboBoxModel<Object> modelosDisponibles;
	private DefaultComboBoxModel<Object> modelosElegidos;

	private Plan planAEditar = null;
	private boolean esEdicion = false;

	public static void main(String[] args) {
		try {
			RegPlan dialog = new RegPlan();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegPlan() {
		this(null);
	}

	public RegPlan(Plan planSeleccionado) {
		this.planAEditar = planSeleccionado;
		this.esEdicion = (this.planAEditar != null);

		setTitle(esEdicion ? "Modificar Plan" : "Crear Nuevo Plan");
		setBounds(100, 100, 450, 438);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Identificador:");
			lblNewLabel.setBounds(12, 13, 96, 16);
			panel.add(lblNewLabel);
			
			idTxt = new JTextField();
			idTxt.setBounds(12, 32, 196, 22);
			panel.add(idTxt);
			idTxt.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(12, 67, 56, 16);
			panel.add(lblNewLabel_1);
			
			nombreTxt = new JTextField();
			nombreTxt.setBounds(12, 86, 196, 22);
			panel.add(nombreTxt);
			nombreTxt.setColumns(10);
			
			descripcionTxt = new JTextField();
			descripcionTxt.setBounds(12, 142, 398, 22);
			panel.add(descripcionTxt);
			descripcionTxt.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Descripci\u00F3n:");
			lblNewLabel_2.setBounds(12, 121, 96, 16);
			panel.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Precio:");
			lblNewLabel_3.setBounds(214, 67, 56, 16);
			panel.add(lblNewLabel_3);
			
			precioTxt = new JTextField();
			precioTxt.setBounds(214, 86, 196, 22);
			panel.add(precioTxt);
			precioTxt.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("Servicios Disponibles:");
			lblNewLabel_4.setBounds(12, 176, 131, 16);
			panel.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("Servicios Seleccionados:");
			lblNewLabel_5.setBounds(214, 176, 158, 16);
			panel.add(lblNewLabel_5);
			
			modelosDisponibles = new DefaultComboBoxModel<>();
			modelosElegidos = new DefaultComboBoxModel<>();
			
			dispoCbx = new JComboBox<>(modelosDisponibles);
			dispoCbx.setBounds(12, 198, 196, 22);
			panel.add(dispoCbx);
			
			selCbx = new JComboBox<>(modelosElegidos);
			selCbx.setBounds(214, 198, 196, 22);
			panel.add(selCbx);
			
			agregarBtn = new JButton("Agregar->");
			agregarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarServicioSeleccionado();
				}
			});
			agregarBtn.setBounds(111, 233, 97, 25);
			panel.add(agregarBtn);
			
			JButton quitarBtn = new JButton("<-Quitar");
			quitarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarServicioSeleccionado();
				}
			});
			quitarBtn.setBounds(214, 233, 97, 25);
			panel.add(quitarBtn);
			
			JLabel lblNewLabel_6 = new JLabel("Costo Total:");
			lblNewLabel_6.setBounds(214, 293, 73, 16);
			panel.add(lblNewLabel_6);
			
			costoTxt = new JTextField();
			costoTxt.setBounds(294, 290, 116, 22);
			panel.add(costoTxt);
			costoTxt.setColumns(10);
			costoTxt.setEditable(false);
			costoTxt.setText("0.0");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				crearBtn = new JButton(esEdicion ? "Modificar" : "Crear");
				crearBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						registrarOModificarPlan();
					}
				});
				crearBtn.setActionCommand("OK");
				buttonPane.add(crearBtn);
				getRootPane().setDefaultButton(crearBtn);
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

		if (esEdicion) {
			cargarDatosEdicion();
		} else {
			idTxt.setText(generarIdPlanAutomatico());
			idTxt.setEditable(false);
			cargarServiciosDisponiblesIniciales();
		}
	}

	private void cargarServiciosDisponiblesIniciales() {
		for (Servicio s : Sistema.getInstance().getMisServicios()) {
			modelosDisponibles.addElement(s);
		}
	}

	private void cargarDatosEdicion() {
		idTxt.setText(planAEditar.getIdPlan());
		idTxt.setEditable(false);
		nombreTxt.setText(planAEditar.getNombre());
		descripcionTxt.setText(planAEditar.getDescripcion());
		precioTxt.setText(String.valueOf(planAEditar.getPrecioBase()));
		
		costoTotalAcumulado = 0.0f;

		for (Servicio s : planAEditar.getMisServicios()) {
			modelosElegidos.addElement(s);
			misServiciosTemp.add(s);
			costoTotalAcumulado += s.costoMensual();
		}

		for (Servicio s : Sistema.getInstance().getMisServicios()) {
			if (!planAEditar.getMisServicios().contains(s)) {
				modelosDisponibles.addElement(s);
			}
		}

		costoTxt.setText(String.format("%.2f", costoTotalAcumulado));
	}

	private String generarIdPlanAutomatico() {
		int num = Sistema.getInstance().getMisPlanes().size() + 1;
		return "0000" + num;
	}

	private void agregarServicioSeleccionado() {
		Servicio servicioSel = (Servicio) dispoCbx.getSelectedItem();
		
		if (servicioSel != null) {
			modelosDisponibles.removeElement(servicioSel);
			modelosElegidos.addElement(servicioSel);
			
			if (!misServiciosTemp.contains(servicioSel)) {
				misServiciosTemp.add(servicioSel);
			}
			costoTotalAcumulado += servicioSel.costoMensual();
			costoTxt.setText(String.format("%.2f", costoTotalAcumulado));
		} else {
			JOptionPane.showMessageDialog(this, "Selecciona un servicio para agregar.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void eliminarServicioSeleccionado() {
		Servicio servicioSel = (Servicio) selCbx.getSelectedItem();
		
		if (servicioSel != null) {
			modelosElegidos.removeElement(servicioSel);
			modelosDisponibles.addElement(servicioSel);
			
			misServiciosTemp.remove(servicioSel);
			
			costoTotalAcumulado -= servicioSel.costoMensual();
			costoTxt.setText(String.format("%.2f", costoTotalAcumulado));
		} else {
			JOptionPane.showMessageDialog(this, "No hay servicios seleccionados para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void registrarOModificarPlan() {
		String id = idTxt.getText().trim();
		String nombre = nombreTxt.getText().trim();
		String descripcion = descripcionTxt.getText().trim();
		
		if (id.isEmpty() || nombre.isEmpty() || precioTxt.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor complete los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (misServiciosTemp.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un servicio para el plan.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		float precio = Float.parseFloat(precioTxt.getText().trim());

		if (esEdicion) {
			planAEditar.setNombre(nombre);
			planAEditar.setDescripcion(descripcion);
			planAEditar.setPrecioBase(precio);
			planAEditar.setMisServicios(new ArrayList<>(misServiciosTemp));

			JOptionPane.showMessageDialog(this, "¡Plan modificado con éxito, mi pana!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		} else {
			boolean estadoInicial = true;
			Plan nuevoPlan = new Plan(id, nombre, descripcion, precio, new ArrayList<>(misServiciosTemp), estadoInicial);
			
			Sistema.getInstance().registrarPlan(nuevoPlan);
			JOptionPane.showMessageDialog(this, "¡Plan registrado con éxito, mi pana!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		}
		
		dispose();
	}
}