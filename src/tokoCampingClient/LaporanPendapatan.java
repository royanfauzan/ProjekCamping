package tokoCampingClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class LaporanPendapatan extends JFrame {

	private JPanel contentPane;
	private JTable tableDetilTransaksi;
	private String headerDetTrans[] = {"iD Pesanan","Nama Pelanggan","Sewa","Denda","Ganti Rugi"};
	private DefaultTableModel tabelModelDetTrans;
	private JTable tablePendapatan;
	private String headerPendapatan[] = {"Sumber","Nominal"};
	private DefaultTableModel tabelModelPendapatan;
	private JTable tableBarangLaris;
	private String headerPerforma[] = {"iD Barang","Nama Barang","Jumlah Dipinjam","Rerata Durasi(Hari)"};
	private DefaultTableModel tabelModelPerforma;
	private JTable tableHilangRusak;
	private String headerHR[] = {"iD Barang","Nama Barang","Hilang","Rusak"};
	private DefaultTableModel tabelModelHR;
	private String tglMulai,tglSelesai;
	private DefaultTableCellRenderer rightRenderer;
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
					LaporanPendapatan frame = new LaporanPendapatan();
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
	public LaporanPendapatan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1018, 687);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblLaporanPendapatan = new JLabel("Laporan Pendapatan");
		lblLaporanPendapatan.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblLaporanPendapatan.setBounds(389, 11, 196, 35);
		contentPane.add(lblLaporanPendapatan);
		
		JLabel lblNewLabel = new JLabel("Mulai Dari :");
		lblNewLabel.setBounds(29, 82, 70, 14);
		contentPane.add(lblNewLabel);
		
		JDateChooser dateChooserMulai = new JDateChooser();
		dateChooserMulai.setBounds(109, 76, 112, 20);
		contentPane.add(dateChooserMulai);
		
		JLabel lblNewLabel_1 = new JLabel("Sampai     :");
		lblNewLabel_1.setBounds(29, 120, 70, 14);
		contentPane.add(lblNewLabel_1);
		
		JDateChooser dateChooserSelesai = new JDateChooser();
		dateChooserSelesai.setBounds(109, 120, 112, 20);
		contentPane.add(dateChooserSelesai);
		
		JLabel lblDetilTransaksi = new JLabel("Detil Transaksi");
		lblDetilTransaksi.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblDetilTransaksi.setBounds(29, 199, 196, 26);
		contentPane.add(lblDetilTransaksi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 236, 584, 159);
		contentPane.add(scrollPane);
		
		tabelModelDetTrans = new DefaultTableModel(null,headerDetTrans);
		tableDetilTransaksi = new JTable();
		tableDetilTransaksi.setModel(tabelModelDetTrans);
		scrollPane.setViewportView(tableDetilTransaksi);
		
		JButton btnProses = new JButton("Proses");
		btnProses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (dateChooserMulai.getDate()==null || dateChooserSelesai.getDate()==null) {
					JOptionPane.showMessageDialog(null, "Pilih Tanggal Terlebih Dahulu!") ;
				} else {
					tglMulai = new SimpleDateFormat("yyyy-MM-dd").format(dateChooserMulai.getDate());
					tglSelesai = new SimpleDateFormat("yyyy-MM-dd").format(dateChooserSelesai.getDate());
					
					kosongkanTabel();
					getDetilTransaksi();
					isiTabelTotal();
					getPerformaBarang();
					getHilangRusak();
					
					tablePendapatan.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
					tableDetilTransaksi.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
					tableDetilTransaksi.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
					tableDetilTransaksi.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
					tableBarangLaris.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
					tableBarangLaris.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
					tableHilangRusak.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
					tableHilangRusak.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

				}
				

			}
		});
		btnProses.setBounds(107, 151, 114, 23);
		contentPane.add(btnProses);
		
		JLabel lblTotalKasMasuk = new JLabel("Total Pendapatan");
		lblTotalKasMasuk.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblTotalKasMasuk.setBounds(692, 235, 196, 26);
		contentPane.add(lblTotalKasMasuk);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(692, 272, 286, 123);
		contentPane.add(scrollPane_1);
		
		tabelModelPendapatan = new DefaultTableModel(null,headerPendapatan);
		tablePendapatan = new JTable();
		tablePendapatan.setModel(tabelModelPendapatan);
		scrollPane_1.setViewportView(tablePendapatan);
		
		JLabel lblJumlahHilangrusak = new JLabel("Barang Terlaris");
		lblJumlahHilangrusak.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblJumlahHilangrusak.setBounds(29, 429, 196, 26);
		contentPane.add(lblJumlahHilangrusak);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(39, 466, 425, 113);
		contentPane.add(scrollPane_2);
		
		tabelModelPerforma = new DefaultTableModel(null,headerPerforma);
		tableBarangLaris = new JTable();
		tableBarangLaris.setModel(tabelModelPerforma);
		scrollPane_2.setViewportView(tableBarangLaris);
		
		JLabel lblBarangHilangrusak = new JLabel("Barang Hilang/Rusak");
		lblBarangHilangrusak.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblBarangHilangrusak.setBounds(527, 429, 270, 26);
		contentPane.add(lblBarangHilangrusak);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(537, 466, 311, 113);
		contentPane.add(scrollPane_3);
		
		tabelModelHR = new DefaultTableModel(null,headerHR);
		tableHilangRusak = new JTable();
		tableHilangRusak.setModel(tabelModelHR);
		scrollPane_3.setViewportView(tableHilangRusak);
		
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
	
	private void kosongkanTabel() {
		tabelModelDetTrans.getDataVector().removeAllElements();
		tabelModelDetTrans.fireTableDataChanged();
		
		tabelModelHR.getDataVector().removeAllElements();
		tabelModelHR.fireTableDataChanged();
		
		tabelModelPendapatan.getDataVector().removeAllElements();
		tabelModelPendapatan.fireTableDataChanged();
		
		tabelModelPerforma.getDataVector().removeAllElements();
		tabelModelPerforma.fireTableDataChanged();
		
	}
	
	private void getDetilTransaksi() {
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "SELECT id_pesanan,pelanggan.`nama_pelanggan`,total_bayar,CAST(DATEDIFF(`tanggal_kembali`,`tanggal_selesai`)*(total_bayar/(jumlah_pesanan*DATEDIFF(`tanggal_selesai`,`tanggal_mulai`))) AS DECIMAL(10,0)) AS denda,(jumlah_hilang*ganti_hilang)+(jumlah_rusak*`ganti_rusak`) AS gantiRugi \r\n" + 
					"FROM pelanggan \r\n" + 
					"INNER JOIN pesanan ON pelanggan.id_pelanggan=pesanan.id_pelanggan \r\n" + 
					"INNER JOIN barang ON pesanan.id_barang = barang.id_barang\r\n" + 
					"WHERE pesanan.`tanggal_kembali` >= CAST(? AS DATE) AND pesanan.`tanggal_kembali` <= CAST(? AS DATE) AND pesanan.`konfirm`=1\r\n" + 
					"GROUP BY pesanan.`id_pesanan`\r\n" + 
					"ORDER BY pesanan.`id_pesanan`;";
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1, tglMulai);
			p.setString(2, tglSelesai);
			ResultSet rs = p.executeQuery();
			while(rs.next())
			{
				
				Object obj[] = new Object[5];
				obj[0] = rs.getInt(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getInt(3);
				
				int dendaMinus = rs.getInt(4);
				if (dendaMinus<0) {
					dendaMinus =0;
				}
				
				obj[3] = dendaMinus;
				obj[4] = rs.getInt(5);
				
				
				tabelModelDetTrans.addRow(obj);
			}
			rs.close();
			p.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void getPerformaBarang() {
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "SELECT pesanan.id_barang,`nama_barang`,SUM(`jumlah_pesanan`) AS jumlah,CAST(AVG(DATEDIFF(`tanggal_selesai`,`tanggal_mulai`)) AS DECIMAL(5,0)) AS rata\r\n" + 
					"FROM pesanan INNER JOIN barang ON pesanan.id_barang = barang.id_barang\r\n" + 
					"WHERE pesanan.`tanggal_kembali` >= CAST(? AS DATE) AND pesanan.`tanggal_kembali` <= CAST(? AS DATE) AND pesanan.`konfirm`=1\r\n" + 
					"GROUP BY id_barang\r\n" + 
					"ORDER BY jumlah DESC;";
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1, tglMulai);
			p.setString(2, tglSelesai);
			ResultSet rs = p.executeQuery();
			while(rs.next())
			{
				
				Object obj[] = new Object[4];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getInt(3);	
				obj[3] = rs.getInt(4);
				
				
				tabelModelPerforma.addRow(obj);
			}
			rs.close();
			p.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void getHilangRusak() {
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "SELECT pesanan.`id_barang`,barang.`nama_barang`,SUM(pesanan.`jumlah_hilang`) AS hilang,SUM(pesanan.`jumlah_rusak`) AS rusak\r\n" + 
					"FROM pesanan INNER JOIN barang ON pesanan.id_barang = barang.id_barang\r\n" + 
					"WHERE pesanan.`tanggal_kembali` >= CAST(? AS DATE) AND pesanan.`tanggal_kembali` <= CAST(? AS DATE) AND pesanan.`konfirm`=1\r\n" + 
					"GROUP BY `id_barang`;";
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1, tglMulai);
			p.setString(2, tglSelesai);
			ResultSet rs = p.executeQuery();
			while(rs.next())
			{
				
				Object obj[] = new Object[4];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getInt(3);	
				obj[3] = rs.getInt(4);
				
				
				tabelModelHR.addRow(obj);
			}
			rs.close();
			p.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void isiTabelTotal() {
		int totalSewa=0;
		int totalDenda=0;
		int totalGantiR=0;
		for (int i = 0; i < tabelModelDetTrans.getRowCount(); i++) {
			totalSewa += (int)tabelModelDetTrans.getValueAt(i, 2);
			totalDenda += (int)tabelModelDetTrans.getValueAt(i, 3);
			totalGantiR += (int)tabelModelDetTrans.getValueAt(i, 4);
		}
		
		Object obj[] = new Object[2];
		obj[0] = "Sewa";
		obj[1] = totalSewa;
		tabelModelPendapatan.addRow(obj);
		
		obj[0] = "Denda";
		obj[1] = totalDenda;
		tabelModelPendapatan.addRow(obj);
		
		obj[0] = "Ganti Rugi";
		obj[1] = totalGantiR;
		tabelModelPendapatan.addRow(obj);
		
		int pendapatanKotor = totalDenda+totalGantiR+totalSewa;
		
		obj[0] = "Total Pendapatan Kotor";
		obj[1] = pendapatanKotor;
		tabelModelPendapatan.addRow(obj);
		
		int pajak = (int) ((int) -1*(pendapatanKotor)*0.1);
		
		obj[0] = "Potongan Pajak(10%)";
		obj[1] = pajak;
		tabelModelPendapatan.addRow(obj);
		
		int totalPendapatan = (pendapatanKotor)+pajak;
		
		obj[0] = "Total Pendapatan Bersih";
		obj[1] = totalPendapatan;
		tabelModelPendapatan.addRow(obj);
		
	}
	
	public void setLabelNama(String a,int b) {
		this.NamaUser=a;
		this.lvlUser=b;
	}
}
