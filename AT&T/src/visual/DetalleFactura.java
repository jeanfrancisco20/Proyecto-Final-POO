package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Factura;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DetalleFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField idTxt;
	private JTextField clienteTxt;
	private JTextField montoTxt;
	private JSpinner fechaEmiCbx;
	private JButton salirBtn;
	private JSpinner fechaPagoCbx;
	private Factura facturaActual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetalleFactura dialog = new DetalleFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public DetalleFactura() {
		this(null);
	}

	/**
	 * Create the dialog.
	 */
	public DetalleFactura(Factura factura) {
		this.facturaActual= factura;
		
		setTitle("Detalle de la Factura");
		setBounds(100, 100, 433, 340);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Id:");
			lblNewLabel.setBounds(12, 13, 56, 16);
			panel.add(lblNewLabel);
			
			idTxt = new JTextField();
			idTxt.setBounds(12, 35, 182, 22);
			idTxt.setEditable(false);
			panel.add(idTxt);
			idTxt.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Cliente:");
			lblNewLabel_1.setBounds(12, 70, 56, 16);
			panel.add(lblNewLabel_1);
			
			clienteTxt = new JTextField();
			clienteTxt.setBounds(12, 91, 182, 22);
			clienteTxt.setEditable(false);
			panel.add(clienteTxt);
			clienteTxt.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Monto Total:");
			lblNewLabel_2.setBounds(121, 215, 73, 16);
			panel.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Fecha de Emisi\u00F3n:");
			lblNewLabel_3.setBounds(12, 126, 116, 16);
			panel.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Fecha de Pago:");
			lblNewLabel_4.setBounds(208, 126, 89, 16);
			panel.add(lblNewLabel_4);
			
			fechaEmiCbx = new JSpinner();
			fechaEmiCbx.setModel(new SpinnerDateModel(new Date(1795060800000L), null, null, Calendar.DAY_OF_YEAR));
			fechaEmiCbx.setBounds(12, 151, 182, 22);
			((JSpinner.DefaultEditor) fechaEmiCbx.getEditor()).getTextField().setEditable(false);
			panel.add(fechaEmiCbx);
			
			fechaPagoCbx = new JSpinner();
			fechaPagoCbx.setModel(new SpinnerDateModel(new Date(1795060800000L), null, null, Calendar.DAY_OF_YEAR));
			fechaPagoCbx.setBounds(208, 151, 182, 22);
			((JSpinner.DefaultEditor) fechaPagoCbx.getEditor()).getTextField().setEditable(false);
			panel.add(fechaPagoCbx);
			
			montoTxt = new JTextField();
			montoTxt.setBounds(208, 212, 182, 22);
			montoTxt.setEditable(false);
			panel.add(montoTxt);
			montoTxt.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				salirBtn = new JButton("Salir");
				salirBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				salirBtn.setActionCommand("Cancel");
				buttonPane.add(salirBtn);
			}
		}
		if (facturaActual != null) {
			cargarDatosFactura();
		}
		else {
			((JSpinner.DefaultEditor) fechaPagoCbx.getEditor()).getTextField().setText("0");
		}
	}
	private void cargarDatosFactura() {
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		
		idTxt.setText(facturaActual.getIdFactura());
		
		String nombreCliente= facturaActual.getCliente().getNombre(); 
		clienteTxt.setText(nombreCliente);
		fechaEmiCbx.setValue(facturaActual.getFechaEmision());
		
		JTextField editorPago = ((JSpinner.DefaultEditor) fechaPagoCbx.getEditor()).getTextField();
		if (facturaActual.isPagada() && facturaActual.getFechaPago() != null) {
			fechaPagoCbx.setValue(facturaActual.getFechaPago());
			editorPago.setText(sdf.format(facturaActual.getFechaPago()));
		} else {
			editorPago.setText("0");
		}

		montoTxt.setText(String.format("%.2f", facturaActual.getMontoTotal()));
	}
}
