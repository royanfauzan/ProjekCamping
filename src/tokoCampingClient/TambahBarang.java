package tokoCampingClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class TambahBarang extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String header[] = {"Jenis Barang","Nama Barang","Stok","Harga(Rp/Hari)"};
	private DefaultTableModel tabelModel;
	private JTextField textField;
	private JCheckBox chckbxJenis;
	private JComboBox comboJenis;
	private JComboBox comboHarga;
	private JComboBox comboJenisTambah;
	private String header2[] = {"id Barang","Jenis Barang","Nama Barang","Stok","Harga","Rusak","Hilang"};
	private JTextField textFieldJenis;
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
	private JLabel lblNewLabel_8;
	private JTextField textFieldRusak;
	private JLabel lblNewLabel_9;
	private JTextField textField_1;
	private JButton btnNewButton_1;
	private JScrollPane scrollPane_1;

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
					TambahBarang frame = new TambahBarang();
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
	public TambahBarang() {
		
		listKode = new ArrayList<String>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKatalogToko = new JLabel("Form Barang");
		lblKatalogToko.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblKatalogToko.setBounds(271, 11, 146, 35);
		contentPane.add(lblKatalogToko);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 127, 593, 175);
		contentPane.add(scrollPane);
		
		textFieldKode = new JTextField();
		textFieldKode.setBounds(117, 428, 77, 20);
		contentPane.add(textFieldKode);
		textFieldKode.setColumns(10);
		
		textFieldJenis = new JTextField();
		textFieldJenis.setBounds(117, 397, 86, 20);
		contentPane.add(textFieldJenis);
		textFieldJenis.setColumns(10);
		
		//CHECKBOX
		chckbxJenis = new JCheckBox("Lainnya");
		
		comboJenis = new JComboBox();
		comboJenisTambah = new JComboBox();
		comboJenisTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				if (comboJenisTambah.getSelectedIndex()>0) {
					chckbxJenis.setSelected(false);
					textFieldJenis.setVisible(false);
					textFieldJenis.revalidate();
					textFieldJenis.repaint();
					
					
					int inx = comboJenisTambah.getSelectedIndex();
					String kodeHuruf = listKode.get(inx);
					StringBuffer sb = new StringBuffer(kodeHuruf);
					sb.delete(kodeHuruf.length()-3,kodeHuruf.length());
					textFieldKode.setText(sb.toString());
				}
			}
		});

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
		

		comboJenisTambah.setBounds(117, 369, 116, 20);
		contentPane.add(comboJenisTambah);
		
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
		table.setModel(tabelModel);
		scrollPane.setViewportView(table);
		
		JLabel lblTambahBarang = new JLabel("Tambah Barang");
		lblTambahBarang.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblTambahBarang.setBounds(20, 325, 146, 35);
		contentPane.add(lblTambahBarang);
		
		JLabel lblNewLabel_2 = new JLabel("Jenis Barang");
		lblNewLabel_2.setBounds(30, 372, 87, 14);
		contentPane.add(lblNewLabel_2);
		

		

		chckbxJenis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxJenis.isSelected()) {
					textFieldJenis.setVisible(true);
					textFieldJenis.revalidate();
					textFieldJenis.repaint();
					
					comboJenisTambah.setSelectedIndex(0);
					textFieldKode.setText("");
				}else {
					textFieldJenis.setVisible(false);
					textFieldJenis.revalidate();
					textFieldJenis.repaint();
				}
			}
		});
		chckbxJenis.setBounds(239, 368, 97, 23);
		contentPane.add(chckbxJenis);
		

		
		lblNewLabel_3 = new JLabel("Kode Barang");
		lblNewLabel_3.setBounds(30, 431, 77, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Nama Barang");
		lblNewLabel_4.setBounds(30, 471, 77, 14);
		contentPane.add(lblNewLabel_4);
		
		textFieldNama = new JTextField();
		textFieldNama.setBounds(117, 468, 86, 20);
		contentPane.add(textFieldNama);
		textFieldNama.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Stok");
		lblNewLabel_5.setBounds(30, 513, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		textFieldStok = new JTextField();
		textFieldStok.setBounds(117, 510, 46, 20);
		contentPane.add(textFieldStok);
		textFieldStok.setColumns(10);
		
		lblNewLabel_6 = new JLabel("Harga Sewa");
		lblNewLabel_6.setBounds(30, 550, 77, 14);
		contentPane.add(lblNewLabel_6);
		
		textFieldHarga = new JTextField();
		textFieldHarga.setBounds(117, 547, 66, 20);
		contentPane.add(textFieldHarga);
		textFieldHarga.setColumns(10);
		
		lblNewLabel_7 = new JLabel("/ Hari");
		lblNewLabel_7.setBounds(193, 550, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Ganti Rusak");
		lblNewLabel_8.setBounds(30, 584, 77, 14);
		contentPane.add(lblNewLabel_8);
		
		textFieldRusak = new JTextField();
		textFieldRusak.setBounds(117, 578, 86, 20);
		contentPane.add(textFieldRusak);
		textFieldRusak.setColumns(10);
		
		lblNewLabel_9 = new JLabel("Ganti Hilang");
		lblNewLabel_9.setBounds(30, 612, 77, 14);
		contentPane.add(lblNewLabel_9);
		
		textField_1 = new JTextField();
		textField_1.setBounds(117, 609, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btnNewButton_1 = new JButton("Tambah Barang");
		btnNewButton_1.setBounds(117, 640, 146, 23);
		contentPane.add(btnNewButton_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(350, 510, 278, 153);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		

		
		
		try {
			kosongkanTabel();
			getDataTabel();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		textFieldJenis.setVisible(false);
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
			String query = "SELECT DISTINCT jenis_barang,id_barang FROM barang";
			comboJenis.removeAllItems();
			comboJenisTambah.removeAllItems();
			
			listKode.removeAll(listKode);
			listKode.add("");
			
			comboJenis.addItem("all");
			comboJenisTambah.addItem("all");
			ResultSet rs = state.executeQuery(query);
			while (rs.next()) {
				comboJenis.addItem(rs.getString(1));
				comboJenisTambah.addItem(rs.getString(1));
				listKode.add(rs.getString(2));
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
