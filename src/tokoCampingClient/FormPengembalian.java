package tokoCampingClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.SystemColor;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class FormPengembalian extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String header[] = {"Nama Pelanggan","Nama Barang","Jumlah","Tanggal Selesai","No Telp"};
	private DefaultTableModel tabelModel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblTanggal;
	private DateFormat dateFormat;
	public Date tanggalSkrg;
	private JTextField textFieldKembali;
	private JTextField textFieldRusak;
	private JTextField textFieldHilang;
	
	private ArrayList<String> listMulai;
	private ArrayList<String> idBarang;
	private ArrayList<Integer> idPesanan;
	private ArrayList<Integer> listHarga;
	private ArrayList<Integer> listStok;
	private ArrayList<Integer> gantiRugi;

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
					FormPengembalian frame = new FormPengembalian();
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
	public FormPengembalian() {
		

		idPesanan = new ArrayList<Integer>();
		idBarang = new ArrayList<String>();
		listMulai = new ArrayList<String>();
		listHarga = new ArrayList<Integer>();
		listStok = new ArrayList<Integer>();
		gantiRugi = new ArrayList<Integer>();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 668);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    tanggalSkrg = new Date();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 95, 548, 188);
		contentPane.add(scrollPane);
		
		tabelModel = new DefaultTableModel(null,header);
		table = new JTable();
		table.setModel(tabelModel);
		scrollPane.setViewportView(table);
		
		try {
			getDataPeminjam();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kosongkanTabel();
				getDataPeminjam();
			}
		});
		btnNewButton.setBounds(480, 70, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Form Pengembalian");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblNewLabel.setBounds(216, 11, 196, 35);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(21, 71, 115, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Cari");
		btnNewButton_1.setBounds(146, 70, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("ID Pelanggan");
		lblNewLabel_1.setBounds(21, 57, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("*Klik data dari Tabel diatas");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_2.setBounds(31, 284, 137, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ID Barang");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(21, 381, 71, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Jumlah Barang");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3_1.setBounds(21, 410, 89, 14);
		contentPane.add(lblNewLabel_3_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(135, 381, 71, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(135, 407, 47, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		lblTanggal = new JLabel("Tanggal : "+dateFormat.format(tanggalSkrg));
		lblTanggal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTanggal.setBounds(21, 339, 147, 31);
		contentPane.add(lblTanggal);
		
		JLabel lblNewLabel_5 = new JLabel("ubah");
		lblNewLabel_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				penggantiTanggal();
			}
		});
		lblNewLabel_5.setForeground(SystemColor.textHighlight);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_5.setBounds(165, 348, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton_2 = new JButton("Konfirmasi");
		btnNewButton_2.setBounds(135, 539, 100, 23);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(287, 368, 282, 250);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		textFieldKembali = new JTextField();
		textFieldKembali.setBounds(135, 438, 47, 20);
		contentPane.add(textFieldKembali);
		textFieldKembali.setColumns(10);
		
		textFieldRusak = new JTextField();
		textFieldRusak.setBounds(135, 469, 47, 20);
		contentPane.add(textFieldRusak);
		textFieldRusak.setColumns(10);
		
		textFieldHilang = new JTextField();
		textFieldHilang.setBounds(135, 500, 47, 20);
		contentPane.add(textFieldHilang);
		textFieldHilang.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Jumlah Kembali");
		lblNewLabel_4.setBounds(21, 441, 100, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("Jumlah Rusak");
		lblNewLabel_6.setBounds(21, 472, 89, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Jumlah Hilang");
		lblNewLabel_7.setBounds(21, 503, 89, 14);
		contentPane.add(lblNewLabel_7);
	}
	
	private void getDataPeminjam() {
		try {
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT pelanggan.nama_pelanggan,barang.nama_barang,pesanan.jumlah_pesanan,pesanan.tanggal_selesai,pelanggan.no_telp,pelanggan.id_pelanggan,barang.id_barang,pesanan.tanggal_mulai,barang.harga_barang,barang.stok_barang,barang.ganti_rugi FROM pelanggan INNER JOIN pesanan ON pelanggan.id_pelanggan=pesanan.id_pelanggan INNER JOIN barang ON pesanan.id_barang = barang.id_barang WHERE pesanan.`tanggal_kembali` IS NULL ORDER BY pesanan.`tanggal_selesai`";
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				
				Object obj[] = new Object[5];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getInt(3);
				obj[3] = rs.getString(4);
				obj[4] = rs.getString(5);
				
				idPesanan.add(rs.getInt(6));
				idBarang.add(rs.getString(7));
				listMulai.add(rs.getString(8));
				listHarga.add(rs.getInt(9));
				listStok.add(rs.getInt(10));
				gantiRugi.add(rs.getInt(11));
				
				tabelModel.addRow(obj);
			}
			rs.close();
			state.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void kosongkanTabel() {
		tabelModel.getDataVector().removeAllElements();	
		tabelModel.fireTableDataChanged();
		
		listMulai.removeAll(listMulai);
		idPesanan.removeAll(idPesanan);
		idBarang.removeAll(idBarang);
		listHarga.removeAll(listHarga);
		listStok.removeAll(listStok);
		gantiRugi.removeAll(gantiRugi);

	}
	
	private void penggantiTanggal() {
		UbahKalender picker = new UbahKalender(this);
		picker.setLocationRelativeTo(this);
		picker.setVisible(true);
	}
	
	public void setTanggal(Date tglBaru) {
		tanggalSkrg = tglBaru;
		lblTanggal.setText("Tanggal : "+dateFormat.format(tanggalSkrg));
	}
}
