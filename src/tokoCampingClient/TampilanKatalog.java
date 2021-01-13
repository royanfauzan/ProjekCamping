package tokoCampingClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TampilanKatalog extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String header[] = {"Jenis Barang","Nama Barang","Stok","Harga(Rp/Hari)"};
	private DefaultTableModel tabelModel;
	private JTextField textField;
	private JComboBox comboJenis;
	private JComboBox comboHarga;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					TampilanKatalog frame = new TampilanKatalog();
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
	public TampilanKatalog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKatalogToko = new JLabel("Katalog Barang");
		lblKatalogToko.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblKatalogToko.setBounds(222, 11, 146, 35);
		contentPane.add(lblKatalogToko);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 127, 541, 175);
		contentPane.add(scrollPane);
		
		
		comboJenis = new JComboBox();

		try {
			getDataJenis();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

		
		comboJenis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kosongkanTabel();
				getDataTabel();
				
			}
		});
		
		comboJenis.setBounds(117, 104, 116, 20);
		contentPane.add(comboJenis);
		
		JLabel lblNewLabel = new JLabel("Jenis Barang");
		lblNewLabel.setBounds(21, 107, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Urutan Harga");
		lblNewLabel_1.setBounds(366, 107, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		comboHarga = new JComboBox();
		comboHarga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kosongkanTabel();
				getDataTabel();
			}
		});
		comboHarga.setModel(new DefaultComboBoxModel(new String[] {"Termurah", "Termahal"}));
		comboHarga.setBounds(461, 104, 100, 20);
		contentPane.add(comboHarga);
		
		textField = new JTextField();
		textField.setBounds(20, 76, 114, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Cari");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kosongkanTabel();
				getDataTabel();
			}
		});
		btnNewButton.setBounds(144, 75, 89, 23);
		contentPane.add(btnNewButton);
		
		tabelModel = new DefaultTableModel(null,header);
		table = new JTable();
		table.setModel(tabelModel);
		scrollPane.setViewportView(table);
		
		
		try {
			getDataTabel();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void getDataTabel() {
		System.out.println("pppppp");
		String keyword = textField.getText();
		String harga="ASC";
		String query="SELECT jenis_barang,nama_barang,stok_barang,harga_barang FROM barang";
		String jenis = "all";
		
		try {
			jenis = comboJenis.getSelectedItem().toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (comboHarga.getSelectedItem().toString().equals("Termahal")) {
			harga ="DESC";
		}
		

		try
		{
			Connection konek = Koneksi.getKoneksi();
			PreparedStatement p = null;
			if (!textField.getText().equals("") && !jenis.equals("all")) {
				query = "SELECT jenis_barang,nama_barang,stok_barang,harga_barang FROM barang WHERE jenis_barang=? AND nama_barang LIKE ? ORDER BY harga_barang "+harga;
				p = konek.prepareStatement(query);
				p.setString(1,jenis);
				p.setString(2,"%"+keyword+"%");
				
			} else if (!jenis.equals("all")) {
				query = "SELECT jenis_barang,nama_barang,stok_barang,harga_barang FROM barang WHERE jenis_barang=? ORDER BY harga_barang "+harga;
				p = konek.prepareStatement(query);
				p.setString(1,jenis);
			} else if (!textField.getText().equals("")) {
				query = "SELECT jenis_barang,nama_barang,stok_barang,harga_barang FROM barang WHERE nama_barang LIKE ? ORDER BY harga_barang "+harga;
				p = konek.prepareStatement(query);
				p.setString(1,"%"+keyword+"%");
			} else {
				query = "SELECT jenis_barang,nama_barang,stok_barang,harga_barang FROM barang ORDER BY harga_barang "+harga;
				p = konek.prepareStatement(query);
			}
			ResultSet rs = p.executeQuery();
			while(rs.next())
			{
				
				Object obj[] = new Object[4];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getInt(3);
				obj[3] = rs.getInt(4);
				
				tabelModel.addRow(obj);
			}
			rs.close();
			p.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex) ;
			ex.printStackTrace();
		}
		
	}
	
	private void getDataJenis() {
		try {
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT DISTINCT jenis_barang FROM barang";
			comboJenis.removeAllItems();
			comboJenis.addItem("all");
			ResultSet rs = state.executeQuery(query);
			while (rs.next()) {
				comboJenis.addItem(rs.getString(1));	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void kosongkanTabel() {
		tabelModel.getDataVector().removeAllElements();
		tabelModel.fireTableDataChanged();
	}
	
}
