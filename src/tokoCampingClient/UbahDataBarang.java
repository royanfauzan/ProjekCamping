package tokoCampingClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UbahDataBarang extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String header[] = {"Jenis Barang","Nama Barang","Stok","Harga(Rp/Hari)"};
	private DefaultTableModel tabelModel;
	private JTextField textField;
	private JComboBox comboJenis;
	private JComboBox comboHarga;
	private String header2[] = {"Kode Barang","Jenis Barang","Nama Barang","Stok","Harga","Rusak","Hilang"};
	private JLabel lblNewLabel_3;
	private JTextField textFieldKode;
	
	private ArrayList<String> listKode;
	private JLabel lblNewLabel_4;
	private JTextField textFieldNama;
	private JLabel lblNewLabel_5;
	private JTextField textFieldStok;
	private JLabel lblNewLabel_6;
	private JTextField textFieldHarga;
	private JLabel lblNewLabel_7;
	private JButton btnNewButton_1;
	private JScrollPane scrollPane_1;
	private String kalimat;
	private String kodeBarang,namaBarang;
	private int stokBarang,hargaBarang;
	private Boolean kembar;
	private JTextArea textArea;

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
					UbahDataBarang frame = new UbahDataBarang();
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
	public UbahDataBarang() {
		listKode = new ArrayList<String>();
		kalimat = "Status Perubahan Barang.. \n";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(350, 371, 278, 153);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setText(kalimat);
		scrollPane_1.setViewportView(textArea);
		
		
		JLabel lblKatalogToko = new JLabel("Form Barang");
		lblKatalogToko.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblKatalogToko.setBounds(271, 11, 146, 35);
		contentPane.add(lblKatalogToko);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 127, 593, 175);
		contentPane.add(scrollPane);
		
		textFieldKode = new JTextField();
		textFieldKode.setBounds(149, 371, 77, 20);
		contentPane.add(textFieldKode);
		textFieldKode.setColumns(10);
		
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
		lblNewLabel_1.setBounds(414, 107, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		
		
		comboHarga = new JComboBox();
		comboHarga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kosongkanTabel();
				getDataTabel();
			}
		});
		comboHarga.setModel(new DefaultComboBoxModel(new String[] {"Termurah", "Termahal"}));
		comboHarga.setBounds(513, 104, 100, 20);
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
		
		tabelModel = new DefaultTableModel(null,header2);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				kodeBarang = (String) tabelModel.getValueAt(i, 0);
				namaBarang = (String) tabelModel.getValueAt(i, 2);
				stokBarang = (int) tabelModel.getValueAt(i, 3);
				hargaBarang = (int) tabelModel.getValueAt(i, 4);
				
				textFieldKode.setText(kodeBarang);
				textFieldNama.setText(namaBarang);
				textFieldStok.setText(""+stokBarang);
				textFieldHarga.setText(""+hargaBarang);
			}
		});
		table.setModel(tabelModel);
		scrollPane.setViewportView(table);
		
		JLabel lblTambahBarang = new JLabel("Ubah Data Barang");
		lblTambahBarang.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblTambahBarang.setBounds(20, 325, 175, 35);
		contentPane.add(lblTambahBarang);
		

		
		lblNewLabel_3 = new JLabel("Kode Barang");
		lblNewLabel_3.setBounds(30, 374, 77, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Nama Barang");
		lblNewLabel_4.setBounds(30, 414, 77, 14);
		contentPane.add(lblNewLabel_4);
		
		textFieldNama = new JTextField();
		textFieldNama.setBounds(149, 411, 86, 20);
		contentPane.add(textFieldNama);
		textFieldNama.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Stok");
		lblNewLabel_5.setBounds(30, 456, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		textFieldStok = new JTextField();
		textFieldStok.setBounds(149, 453, 46, 20);
		contentPane.add(textFieldStok);
		textFieldStok.setColumns(10);
		
		lblNewLabel_6 = new JLabel("Harga Sewa");
		lblNewLabel_6.setBounds(30, 493, 77, 14);
		contentPane.add(lblNewLabel_6);
		
		textFieldHarga = new JTextField();
		textFieldHarga.setBounds(149, 490, 66, 20);
		contentPane.add(textFieldHarga);
		textFieldHarga.setColumns(10);
		
		lblNewLabel_7 = new JLabel("/ Hari");
		lblNewLabel_7.setBounds(230, 493, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		btnNewButton_1 = new JButton("Update Data Barang");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String TbkodeBarang = textFieldKode.getText();
				String TbnamaBarang = textFieldNama.getText();
				String TbstokBarang = textFieldStok.getText();
				String TbhargaBarang = textFieldHarga.getText();
//				String TbgantiRusak = textFieldRusak.getText();
//				String TbgantiHilang = textFieldHilang.getText();
				
				if (!TbnamaBarang.equals("")&&
					!textFieldStok.getText().equals("")&&
					!textFieldHarga.getText().equals("")) {
					
				
							ubahDataBarang();
							
							
								kalimat+=TbkodeBarang+"|"+"|"+TbstokBarang+"|"+TbhargaBarang+"|--"+"Berhasil DiUbah\n";
								textArea.setText(kalimat);
								kosongkanInput();
								getDataJenis();
							
							

							kosongkanTabel();
							getDataTabel();
							
						
						
					} else {
						JOptionPane.showMessageDialog(null, "Pilih/Isi Jenis Barang!") ;
					}
			}
		});
		btnNewButton_1.setBounds(117, 537, 146, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_11 = new JLabel("Buah");
		lblNewLabel_11.setBounds(217, 456, 46, 14);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_10_2 = new JLabel("Rp.");
		lblNewLabel_10_2.setBounds(108, 493, 26, 14);
		contentPane.add(lblNewLabel_10_2);
		

		
		
		try {
			kosongkanTabel();
			getDataTabel();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		textFieldKode.disable();
	}
	
	public void getDataTabel() {
		System.out.println("pppppp");
		String keyword = textField.getText();
		String harga="ASC";
		String query="SELECT*FROM barang";
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
				query = "SELECT*FROM barang WHERE jenis_barang=? AND nama_barang LIKE ? ORDER BY harga_barang "+harga;
				p = konek.prepareStatement(query);
				p.setString(1,jenis);
				p.setString(2,"%"+keyword+"%");
				
			} else if (!jenis.equals("all")) {
				query = "SELECT*FROM barang WHERE jenis_barang=? ORDER BY harga_barang "+harga;
				p = konek.prepareStatement(query);
				p.setString(1,jenis);
			} else if (!textField.getText().equals("")) {
				query = "SELECT*FROM barang WHERE nama_barang LIKE ? ORDER BY harga_barang "+harga;
				p = konek.prepareStatement(query);
				p.setString(1,"%"+keyword+"%");
			} else {
				query = "SELECT*FROM barang ORDER BY harga_barang "+harga;
				p = konek.prepareStatement(query);
			}
			ResultSet rs = p.executeQuery();
			while(rs.next())
			{
				
				Object obj[] = new Object[7];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getString(3);
				obj[3] = rs.getInt(4);
				obj[4] = rs.getInt(5);
				obj[5] = rs.getInt(6);
				obj[6] = rs.getInt(7);
				
				
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
			
//			listKode.removeAll(listKode);
//			listKode.add("");
			
			comboJenis.addItem("all");
			ResultSet rs = state.executeQuery(query);
			while (rs.next()) {
				comboJenis.addItem(rs.getString(1));
//				listKode.add(rs.getString(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

	
	private void ubahDataBarang(){
		kembar = false;
//		if (chckbxJenis.isSelected()) {
//			jenisBar=textFieldJenis.getText();
//		}else {
//			jenisBar=comboJenisTambah.getSelectedItem().toString();
//		}
		String kodeBarang = textFieldKode.getText();
		String namaBarang = textFieldNama.getText();
		int stokBarang = Integer.parseInt(textFieldStok.getText());
		int hargaBarang = Integer.parseInt(textFieldHarga.getText());

		
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "UPDATE barang SET nama_barang=?,stok_barang=?,harga_barang=? WHERE id_barang=?";
			PreparedStatement p = konek.prepareStatement(query);
			
			p.setString(1, namaBarang);
			p.setInt(2, stokBarang);
			p.setInt(3, hargaBarang);
			p.setString(4, kodeBarang);

			p.executeUpdate();
			p.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			kembar=true;
			System.out.print(e);
		}
		
	}
	
	private void kosongkanInput() {

		textFieldKode.setText("");
		textFieldNama.setText("");
		textFieldStok.setText("");
		textFieldHarga.setText("");

		

	}
	
	private void kosongkanTabel() {
		tabelModel.getDataVector().removeAllElements();
		tabelModel.fireTableDataChanged();
	}
}
