package tokoCampingClient;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CekPinjamanClient extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIdUser;
	private JTable table;
	private String header[] = {"Nama Barang","Jumlah","Tanggal Mulai","Tanggal Selesai"};
	private DefaultTableModel tabelModelPinjam;

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
					CekPinjamanClient frame = new CekPinjamanClient();
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
	public CekPinjamanClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cek Pinjaman");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 30, 146, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kode Peminjaman");
		lblNewLabel_1.setBounds(22, 76, 112, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldIdUser = new JTextField();
		textFieldIdUser.setBounds(130, 73, 100, 20);
		contentPane.add(textFieldIdUser);
		textFieldIdUser.setColumns(10);
		
		JButton btnNewButton = new JButton("Cari");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kosongkanTabel();
				getTabelPinjaman();
			}
		});
		btnNewButton.setBounds(240, 72, 63, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 106, 462, 210);
		contentPane.add(scrollPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 76, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmItemMenuUtama = new JMenuItem("Menu Utama");
		mntmItemMenuUtama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TampilanMenuUtama frameMenu = new TampilanMenuUtama();
				frameMenu.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		mnNewMenu.add(mntmItemMenuUtama);
		
		JMenuItem mntmItemKeluar = new JMenuItem("Keluar");
		mntmItemKeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		mnNewMenu.add(mntmItemKeluar);
		
		tabelModelPinjam = new DefaultTableModel(null,header);
		table = new JTable();
		table.setModel(tabelModelPinjam);
		scrollPane.setViewportView(table);
		
	}
	
	public void getTabelPinjaman() {
		int id = Integer.parseInt(textFieldIdUser.getText());
		
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "SELECT barang.nama_barang,pesanan.jumlah_pesanan,pesanan.tanggal_mulai,pesanan.tanggal_selesai FROM pesanan INNER JOIN barang ON pesanan.id_barang = barang.id_barang WHERE pesanan.id_pelanggan=? AND pesanan.konfirm=1";
			PreparedStatement p = konek.prepareStatement(query);
			p.setInt(1, id);
			ResultSet hasil = p.executeQuery();
			while (hasil.next()) {
				Object obj[] = new Object[4];
				obj[0] = hasil.getString(1);
				obj[1] = hasil.getInt(2);
				obj[2] = hasil.getString(3);
				obj[3] = hasil.getString(4);
				
				tabelModelPinjam.addRow(obj);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void kosongkanTabel() {
		tabelModelPinjam.getDataVector().removeAllElements();		
		tabelModelPinjam.fireTableDataChanged();		

	}
}
