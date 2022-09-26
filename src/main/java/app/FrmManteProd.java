package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Categoria;
import model.Producto;
import model.Proveedor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class FrmManteProd extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtCodigo;
	private JComboBox <String> cboCategorias;
	private JComboBox <String> cboProveedores;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTable tblSalida;
	
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(324, 29, 89, 23);
		contentPane.add(btnRegistrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 677, 89, 23);
		contentPane.add(btnListado);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboCategorias = new JComboBox <String> ();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);

		JLabel lblProveedores = new JLabel("Proveedor:");
		lblProveedores.setBounds(230, 106, 102, 14);
		contentPane.add(lblProveedores);

		cboProveedores = new JComboBox <String> ();
		cboProveedores.setBounds(300, 104, 120, 22);
		contentPane.add(cboProveedores);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(324, 63, 89, 23);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 325, 414, 341);
		contentPane.add(scrollPane_1);
		
		tblSalida = new JTable();
		scrollPane_1.setViewportView(tblSalida);
		tblSalida.setModel(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("Producto");
		modelo.addColumn("Stock");
		modelo.addColumn("Precio");
		modelo.addColumn("Categoria");
		modelo.addColumn("Proveedor");

		llenaComboCategoria();
		llenaComboProveedor();
	}

	void llenaComboCategoria() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		List<Categoria> lstCategorias = em.createQuery("select c from Categoria c", Categoria.class).getResultList();
		cboCategorias.addItem("Seleccionar");
		for (Categoria c : lstCategorias) {
			cboCategorias.addItem(c.getIdcategoria() + "-" + c.getDescripcion());
		}
		em.close();
	}
	void llenaComboProveedor() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		List<Proveedor> lstProveedores = em.createQuery("select p from Proveedor p", Proveedor.class).getResultList();
		cboProveedores.addItem("Seleccionar");
		for (Proveedor p : lstProveedores) {
			cboProveedores.addItem(p.getIdproveedor() + "-" + p.getNombre());
		}
		em.close();
	}

	void registrar() {
		String codigo = leerCodigo();
		String nombre = leerNombre();
		int categoria = leerCategoria();
		int stock = leerStock();
		double precio = leerPrecio();
		int proveedor = leerProveedor();
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		Producto p = new Producto();
		p.setIdprod(codigo);
		p.setDescripcion(nombre);
		p.setIdcategoria(categoria);
		p.setStock(stock);
		p.setPrecio(precio);
		p.setEstado(true);
		p.setIdproveedor(proveedor);
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			aviso("Producto " + p.getDescripcion() + " registrado", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			aviso("Producto " + p.getDescripcion() + " no registrado\n" + e.getMessage(), "Error en proceso", JOptionPane.ERROR_MESSAGE);
		}
		em.close();
	}

	void listado() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		List<Producto> lstProductos = em.createQuery("select p from Producto p", Producto.class).getResultList();
		txtSalida.setText("");
		imprimir("******************************************************");
		for (Producto p : lstProductos) {
			imprimir("Id Producto : " + p.getIdprod());
			imprimir("Descripción : " + p.getDescripcion());
			imprimir("Stock       : " + p.getStock());
			imprimir("Precio      : " + p.getPrecio());
			imprimir("Categoria   : " + p.getIdcategoria() + "-" + p.getObjCategoria().getDescripcion());
			imprimir("Id Proveedor : " + p.getIdproveedor() + "-" + p.getObjProveedor().getNombre());
			imprimir("******************************************************");
			Object datos[] = {
					p.getIdprod(),
					p.getDescripcion(),
					p.getStock(),
					p.getPrecio(),
					p.getIdcategoria() + "-" + p.getObjCategoria().getDescripcion(),
					p.getIdproveedor() + "-" + p.getObjProveedor().getNombre()
			};
			modelo.addRow(datos);
		}
		em.close();
	}
	
	Proveedor buscarProveedor() {
		int proveedor = leerProveedor();
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		Proveedor p = em.find(Proveedor.class, proveedor);
		em.close();
		return p;
	}
	
	Categoria buscarCategoria() {
		int categoria = leerCategoria();
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		Categoria c = em.find(Categoria.class, categoria);
		em.close();
		return c;
	}
	
	void buscar() {
		String codigo = leerCodigo();
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		//em.getTransaction().begin();
		Producto p = em.find(Producto.class, codigo);
		if (p == null) {
			aviso("Código no existe", "Aviso del sistema", JOptionPane.WARNING_MESSAGE);
		} else {
			txtDescripcion.setText(p.getDescripcion());
			cboCategorias.setSelectedIndex(p.getIdcategoria());
			txtStock.setText("" + p.getStock());
			txtPrecio.setText("" + p.getPrecio());
			cboProveedores.setSelectedIndex(p.getIdproveedor());
		}
		//em.getTransaction().commit();
		em.close();
	}
	String leerCodigo() {
		if (txtCodigo.getText().isEmpty()) {
			aviso("Debe ingresar un valor en el código", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			return txtCodigo.getText();
		}
	}
	String leerNombre() {
		if (txtDescripcion.getText().isEmpty()) {
			aviso("Debe ingresar un valor en el nombre", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			return txtDescripcion.getText();
		}
	}
	int leerCategoria() {
		if (cboCategorias.getSelectedIndex() == 0) {
			aviso("Debe ingresar un valor en la categoria", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		} else {
			return cboCategorias.getSelectedIndex();
		}
	}
	int leerStock() {
		if (txtStock.getText().isEmpty()) {
			aviso("Debe ingresar un valor en el stock", "Mensaje de Erorr", JOptionPane.ERROR_MESSAGE);
			return 0;
		} else {
			return Integer.parseInt(txtStock.getText());
		}
	}
	double leerPrecio() {
		if (txtPrecio.getText().isEmpty()) {
			aviso("Debe ingresar un valor en el precio", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			return 0.00;
		} else {
			return Double.parseDouble(txtPrecio.getText());
		}
	}
	int leerProveedor() {
		if (cboProveedores.getSelectedIndex() == 0) {
			aviso("Debe ingresar un valor en el proveedor", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		} else {
			return cboProveedores.getSelectedIndex();
		}
	}
	void aviso(String s, String titulo, int icono) {
		JOptionPane.showMessageDialog(this, s, titulo, icono);
	}
	void imprimir(String s) {
		txtSalida.append(s+ "\n");
	}
}
