package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Image;

import logico.Sistema;

public class Principal extends JFrame {

	private static final Color NEGRO_FONDO = new Color(30, 41, 59);
	private static final Color NEGRO_BARRA = new Color(15, 23, 42);
	private static final Color GRIS_PANEL = new Color(51, 65, 85);
	private static final Color GRIS_RESALTADO = new Color(59, 130, 246);
	private static final Color BLANCO_TEXTO = new Color(248, 250, 252);

	private static final Color AZUL_BOTON = new Color(37, 99, 235);
	private static final Color BORDE = new Color(100, 116, 139);

	private static final Font FUENTE =
	        new Font("Segoe UI", Font.PLAIN, 14);

	private static final Font FUENTE_TITULO =
	        new Font("Segoe UI", Font.BOLD, 14);
	
	private JPanel contentPane;

	public static void main(String[] args) {
		try {
			System.setProperty("awt.useSystemAAFontSettings", "on");
			System.setProperty("swing.aatext", "true");

			Font fuenteMenu = new Font("Segoe UI", Font.BOLD, 14);
			Font fuenteItem = new Font("Segoe UI", Font.PLAIN, 13);

			UIManager.put("MenuBar.background", NEGRO_BARRA);
			UIManager.put("MenuBar.foreground", BLANCO_TEXTO);
			UIManager.put("MenuBar.font", fuenteMenu);

			UIManager.put("Menu.background", NEGRO_BARRA);
			UIManager.put("Menu.foreground", BLANCO_TEXTO);
			UIManager.put("Menu.selectionBackground", GRIS_RESALTADO);
			UIManager.put("Menu.selectionForeground", BLANCO_TEXTO);
			UIManager.put("Menu.font", fuenteMenu);

			UIManager.put("PopupMenu.background", NEGRO_BARRA);
			UIManager.put("PopupMenu.foreground", BLANCO_TEXTO);

			UIManager.put("MenuItem.background", NEGRO_BARRA);
			UIManager.put("MenuItem.foreground", BLANCO_TEXTO);
			UIManager.put("MenuItem.selectionBackground", GRIS_RESALTADO);
			UIManager.put("MenuItem.selectionForeground", BLANCO_TEXTO);
			UIManager.put("MenuItem.font", fuenteItem);

			UIManager.put("Panel.background", GRIS_PANEL);
			UIManager.put("OptionPane.background", GRIS_PANEL);
			UIManager.put("OptionPane.messageForeground", BLANCO_TEXTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		setBackground(SystemColor.menu);
		setTitle("AT&T");
		setIconImage(
			    new ImageIcon(
			        getClass().getResource("/img/conexion.png")
			    ).getImage()
			);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 683);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar() {
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(NEGRO_BARRA);
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		menuBar.setOpaque(false);
		menuBar.setPreferredSize(new Dimension(0, 42));
		menuBar.setBorder(new EmptyBorder(4, 12, 4, 12));
		setJMenuBar(menuBar);

		Font fuenteMenu = new Font("Arial", Font.BOLD, 14);
		Font fuenteItem = new Font("Arial", Font.PLAIN, 13);

		JMenu mnNewMenu_2 = new JMenu("Gesti\u00F3n de Empleados y Clientes");
		menuBar.add(mnNewMenu_2);
		

		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar Empleado");
		ImageIcon iconRegistrar = new ImageIcon(
		        getClass().getResource("/img/RegEmp.png"));

		Image imgRegistrar = iconRegistrar.getImage()
		        .getScaledInstance(16, 16, Image.SCALE_SMOOTH);

		mntmNewMenuItem.setIcon(new ImageIcon(imgRegistrar));
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEmpleado regEmpleado = new RegEmpleado(null);
				aplicarTemaOscuro(regEmpleado);
				regEmpleado.setModal(true);
				regEmpleado.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Manejo de Empleados");
		ImageIcon iconListEmp = new ImageIcon(
		        getClass().getResource("/img/ListEmp.png"));

		Image imgListEmp = iconListEmp.getImage()
		        .getScaledInstance(16, 16, Image.SCALE_SMOOTH);

		mntmNewMenuItem_2.setIcon(new ImageIcon(imgListEmp));
		
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NominaEmpleado nominaEmpleado = new NominaEmpleado();
				aplicarTemaOscuro(nominaEmpleado);
				nominaEmpleado.setModal(true);
				nominaEmpleado.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenu_1 = new JMenuItem("Lista de Clientes");
		ImageIcon iconListCli = new ImageIcon(
		        getClass().getResource("/img/ListCli.png"));

		Image imgListCli = iconListCli.getImage()
		        .getScaledInstance(16, 16, Image.SCALE_SMOOTH);

		mntmNewMenu_1.setIcon(new ImageIcon(imgListCli));
		
		mntmNewMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCliente listarCliente = new ListarCliente();
				aplicarTemaOscuro(listarCliente);
				listarCliente.setModal(true);
				listarCliente.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenu_1);

		JMenu mnNewMenu = new JMenu("Planes y Servicios");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Registrar Servicio");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegServicio regServicio = new RegServicio();
				aplicarTemaOscuro(regServicio);
				regServicio.setModal(true);
				regServicio.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Registrar Plan");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegPlan regPlan = new RegPlan();
				aplicarTemaOscuro(regPlan);
				regPlan.setModal(true);
				regPlan.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Historial de Planes");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListPlan listPlan = new ListPlan();
				aplicarTemaOscuro(listPlan);
				listPlan.setModal(true);
				listPlan.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_5);

		JMenu mnNewMenu_3 = new JMenu("Contratos y Facturaci\u00F3n");
		menuBar.add(mnNewMenu_3);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Registrar Contrato");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegContrato regContrato = new RegContrato(null);
				aplicarTemaOscuro(regContrato);
				regContrato.setModal(true);
				regContrato.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Historial de Contratos");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListContrato listContrato = new ListContrato();
				aplicarTemaOscuro(listContrato);
				listContrato.setModal(true);
				listContrato.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Generar Facturas");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.ArrayList<logico.Factura> creadas = Sistema.getInstance().generarFacturasTodosLosClientes();
				if (creadas.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No había facturas nuevas por generar.", "Facturación",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Facturas generadas correctamente.", "Facturación",
							JOptionPane.INFORMATION_MESSAGE);
				}
				ListarFacturas listarFacturas = new ListarFacturas();
				aplicarTemaOscuro(listarFacturas);
				listarFacturas.setModal(true);
				listarFacturas.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_9);

		estilizarMenu(mnNewMenu_2, fuenteMenu, fuenteItem);
		estilizarMenu(mnNewMenu, fuenteMenu, fuenteItem);
		estilizarMenu(mnNewMenu_3, fuenteMenu, fuenteItem);

		contentPane = new JPanel();
		contentPane.setBackground(GRIS_PANEL);
		contentPane.setForeground(SystemColor.scrollbar);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	private void estilizarMenu(JMenu menu, Font fuenteMenu, Font fuenteItem) {
		menu.setFont(fuenteMenu);
		menu.setForeground(BLANCO_TEXTO);
		menu.setBackground(NEGRO_BARRA);
		menu.setOpaque(true);
		menu.setBorder(new EmptyBorder(4, 12, 4, 12));

		menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menu.setBackground(GRIS_RESALTADO);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!menu.isSelected() && !menu.isPopupMenuVisible()) {
					menu.setBackground(NEGRO_BARRA);
				}
			}
		});

		JPopupMenu popup = menu.getPopupMenu();
		popup.setBackground(NEGRO_BARRA);
		popup.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));

		popup.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				menu.setBackground(GRIS_RESALTADO);
				int anchoTitulo = menu.getWidth();
				int altoActual = popup.getPreferredSize().height;
				popup.setPreferredSize(new Dimension(anchoTitulo, altoActual));
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				menu.setBackground(NEGRO_BARRA);
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				menu.setBackground(NEGRO_BARRA);
			}
		});

		for (int i = 0; i < menu.getMenuComponentCount(); i++) {
			if (menu.getMenuComponent(i) instanceof JMenuItem) {
				JMenuItem item = (JMenuItem) menu.getMenuComponent(i);
				item.setFont(fuenteItem);
				item.setForeground(BLANCO_TEXTO);
				item.setOpaque(true);
				item.setBackground(NEGRO_BARRA);
				item.setBorder(new EmptyBorder(6, 12, 6, 12));

				item.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						item.setBackground(GRIS_RESALTADO);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						item.setBackground(NEGRO_BARRA);
					}
				});
			}
		}
	}

	private void aplicarTemaOscuro(Window ventana) {
		if (ventana == null) {
			return;
		}
		if (ventana instanceof JFrame) {
			((JFrame) ventana).getContentPane().setBackground(GRIS_PANEL);
		} else if (ventana instanceof javax.swing.JDialog) {
			((javax.swing.JDialog) ventana).getContentPane().setBackground(GRIS_PANEL);
		}
		estilizarComponenteRecursivo(ventana);
	}

	private void estilizarComponenteRecursivo(Component comp) {

	    comp.setFont(FUENTE);

	    if (comp instanceof JPanel) {

	        comp.setBackground(GRIS_PANEL);
	        comp.setForeground(BLANCO_TEXTO);

	    } else if (comp instanceof JLabel) {

	        comp.setForeground(BLANCO_TEXTO);
	        comp.setFont(FUENTE_TITULO);

	    } else if (comp instanceof JButton) {

	        JButton btn = (JButton) comp;

	        btn.setBackground(AZUL_BOTON);
	        btn.setForeground(Color.WHITE);
	        btn.setFocusPainted(false);
	        btn.setBorderPainted(false);
	        btn.setOpaque(true);

	        btn.setBorder(
	            BorderFactory.createEmptyBorder(8, 15, 8, 15));
	        
	    } else if (comp instanceof JTextField) {

	        JTextField txt = (JTextField) comp;

	        txt.setBackground(NEGRO_FONDO);
	        txt.setForeground(BLANCO_TEXTO);
	        txt.setCaretColor(BLANCO_TEXTO);

	        txt.setBorder(
	        	    BorderFactory.createCompoundBorder(
	        	        BorderFactory.createLineBorder(BORDE, 1),
	        	        BorderFactory.createEmptyBorder(6, 8, 6, 8)
	        	    )
	        	);

	    } else if (comp instanceof JTextArea) {

	        JTextArea txt = (JTextArea) comp;

	        txt.setBackground(NEGRO_FONDO);
	        txt.setForeground(BLANCO_TEXTO);
	        txt.setCaretColor(BLANCO_TEXTO);

	        txt.setBorder(
	            BorderFactory.createCompoundBorder(
	                BorderFactory.createLineBorder(BORDE),
	                BorderFactory.createEmptyBorder(5, 8, 5, 8)
	            )
	        );

	    } else if (comp instanceof JComboBox) {

	        JComboBox<?> combo = (JComboBox<?>) comp;

	        combo.setBackground(NEGRO_FONDO);
	        combo.setForeground(BLANCO_TEXTO);

	        combo.setBorder(
	            BorderFactory.createLineBorder(BORDE)
	        );

	    } else if (comp instanceof JTable) {

	        JTable tabla = (JTable) comp;

	        tabla.setBackground(NEGRO_FONDO);
	        tabla.setForeground(BLANCO_TEXTO);
	        tabla.setRowHeight(28);

	        tabla.setSelectionBackground(GRIS_RESALTADO);
	        tabla.setSelectionForeground(BLANCO_TEXTO);

	        if (tabla.getTableHeader() != null) {

	            tabla.getTableHeader().setBackground(NEGRO_BARRA);
	            tabla.getTableHeader().setForeground(BLANCO_TEXTO);
	            tabla.getTableHeader().setFont(FUENTE_TITULO);
	        }

	    } else if (comp instanceof JScrollPane) {

	        JScrollPane sp = (JScrollPane) comp;
	        sp.getViewport().setBackground(NEGRO_FONDO);
	    } else if (comp instanceof JCheckBox) {

	        JCheckBox chk = (JCheckBox) comp;

	        chk.setBackground(GRIS_PANEL);
	        chk.setForeground(BLANCO_TEXTO);
	        chk.setFocusPainted(false);
	    }

	    if (comp instanceof Container) {
	        for (Component hijo : ((Container) comp).getComponents()) {
	            estilizarComponenteRecursivo(hijo);
	        }
	    }
	}
}