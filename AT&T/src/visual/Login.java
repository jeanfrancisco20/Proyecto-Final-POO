package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Administrativo;
import logico.Empleado;
import logico.Sistema;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usuarioTxt;
	private JPasswordField contrasenaPsf;
	private JButton loginBtn;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				cargarDatosIniciales();

				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void cargarDatosIniciales() {
		try (FileInputStream data = new FileInputStream("data.dat");
				ObjectInputStream dataRead = new ObjectInputStream(data)) {

			Sistema temp = (Sistema) dataRead.readObject();
			if (temp != null) {
				Sistema.setSistema(temp);
			}
		} catch (FileNotFoundException e) {
			try (FileOutputStream data2 = new FileOutputStream("data.dat");
					ObjectOutputStream dataWrite = new ObjectOutputStream(data2)) {

				Date fecha = new Date();
				Empleado aux = new Administrativo("1", "Admin", "Admin", "Administrador", 0, 0f, 0f, fecha);
				Sistema.getInstance().registrarEmpleado(aux);
				dataWrite.writeObject(Sistema.getInstance());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 248, 242);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(12, 13, 56, 16);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Contraseña:");
		lblNewLabel_1.setBounds(12, 70, 104, 16);
		panel.add(lblNewLabel_1);

		usuarioTxt = new JTextField();
		usuarioTxt.setBounds(12, 35, 193, 22);
		panel.add(usuarioTxt);
		usuarioTxt.setColumns(10);

		contrasenaPsf = new JPasswordField();
		contrasenaPsf.setBounds(12, 99, 193, 22);
		panel.add(contrasenaPsf);

		loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = usuarioTxt.getText().trim();
				String contrasena = new String(contrasenaPsf.getPassword());

				if (Sistema.getInstance().confirmarLogin(usuario, contrasena)) {
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(
						Login.this,
						"Usuario o contraseña incorrectos.",
						"Error de acceso",
						JOptionPane.ERROR_MESSAGE
					);
				}
			}
		});
		loginBtn.setBounds(60, 147, 97, 25);
		panel.add(loginBtn);
	}
}
