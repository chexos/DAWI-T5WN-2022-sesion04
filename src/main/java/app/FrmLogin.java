package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FrmLogin extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 30, 161, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblClave = new JLabel("Clave :");
		lblClave.setBounds(10, 64, 102, 14);
		contentPane.add(lblClave);
		
		txtClave = new JTextField();
		txtClave.setColumns(10);
		txtClave.setBounds(122, 61, 161, 20);
		contentPane.add(txtClave);
		
	}

	
	private JTextField txtClave;
	
	
	void registrar() {
		String usuario = leerUsuario();
		String clave = leerClave();
		System.out.println(usuario + " " + clave);
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();		
		Usuario u; 
		try {
			u = em.createQuery("select u from Usuario u where u.usuario = :xusr and u.clave = :xcla", Usuario.class)
									.setParameter("xusr", usuario)
									.setParameter("xcla", clave)
									.getSingleResult();
			aviso("Bienvenido " + u.getNombre(), "Mensaje del sistema", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			u = null;
			aviso("Usuario o clave incorrecto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
		}
		em.close();
	}
	String leerUsuario() {
		if (txtUsuario.getText().isEmpty()) {
			aviso("Debe ingresar un usuario", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);;
			return null;
		} else {
			return txtUsuario.getText();
		}
	}
	String leerClave() {
		if (txtClave.getText().isEmpty()) {
			aviso("Debe ingresar una clave", "Mensaje de Errro", JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			return txtClave.getText();
		}
	}
	void aviso(String s, String titulo, int icono) {
		JOptionPane.showMessageDialog(this, s, titulo, icono);
	}
}
