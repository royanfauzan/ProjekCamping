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
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CekBarangDipinjam extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String header[] = {"Nama Pelanggan","Nama Barang","Jumlah","Tanggal Selesai","No Telp"};
	private DefaultTableModel tabelModel;
	private String NamaUser;
	private int lvlUser;

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
					CekBarangDipinjam frame = new CekBarangDipinjam();
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
	public CekBarangDipinjam() {
		
//	mainnya hebat2
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cek Peminjaman Barang");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblNewLabel.setBounds(21, 23, 229, 37);
		contentPane.add(lblNewLabel);
		
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
		btnNewButton.setBounds(21, 71, 89, 23);
		contentPane.add(btnNewButton);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 76, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmItemMenuUtama = new JMenuItem("Menu Admin");
		mntmItemMenuUtama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TampilanMenuAdmin frameMenu = new TampilanMenuAdmin();
				frameMenu.setVisible(true);
				frameMenu.setLabelNama(NamaUser, lvlUser);
				setVisible(false);
				dispose();
			}
		});
		mnNewMenu.add(mntmItemMenuUtama);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Menu Utama");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TampilanMenuUtama frameUtama = new TampilanMenuUtama();
				frameUtama.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmItemKeluar = new JMenuItem("Keluar");
		mntmItemKeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		mnNewMenu.add(mntmItemKeluar);
				
	}
	
	private void getDataPeminjam() {
		try {
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT pelanggan.nama_pelanggan,barang.nama_barang,pesanan.jumlah_pesanan,pesanan.tanggal_selesai,pelanggan.no_telp FROM pelanggan INNER JOIN pesanan ON pelanggan.id_pelanggan=pesanan.id_pelanggan INNER JOIN barang ON pesanan.id_barang = barang.id_barang WHERE pesanan.`tanggal_kembali` IS NULL AND pesanan.konfirm=1 ORDER BY pesanan.`tanggal_selesai`";
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				
				Object obj[] = new Object[5];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getInt(3);
				obj[3] = rs.getString(4);
				obj[4] = rs.getString(5);
				
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

	}
	

	
	public void setLabelNama(String a,int b) {
		this.NamaUser=a;
		this.lvlUser=b;
	}
}
