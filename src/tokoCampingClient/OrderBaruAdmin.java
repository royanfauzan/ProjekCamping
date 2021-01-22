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
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderBaruAdmin extends JFrame {

	private JPanel contentPane;
	private JTable tablePesan;
	
	private String header[] = {"ID Pelanggan","Nama Barang","Jumlah","Tanggal Mulai","no Telpon"};
	private String header2[] = {"Nama Barang","Stok"};
	private DefaultTableModel tabelModelPesan;
	private DefaultTableModel tabelModelStok;
	private JTextField textFieldNamaBar;
	private JTextField textFieldJml;
	private JTextArea textArea;
	
	private JDateChooser dateChooserMulai;
	private JDateChooser dateChooserSelesai;
	private ArrayList<String> listSelesai;
	private ArrayList<Integer> idPesanan;
	private ArrayList<Integer> listHarga;
	private ArrayList<Integer> listStok;
	
	private ArrayList<String> idBarang;
	private int currIdPesan,currIdPelanggan,idBefore,currHarga,currStok,jumlahBar,totalBiaya;

	private String currIdBarang,kalimat;
	private JTable tableStok;
	
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
					OrderBaruAdmin frame = new OrderBaruAdmin();
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
	public OrderBaruAdmin() {
		
		listSelesai = new ArrayList<String>();
		idPesanan = new ArrayList<Integer>();
		idBarang = new ArrayList<String>();
		listHarga = new ArrayList<Integer>();
		listStok = new ArrayList<Integer>();
		idBefore = 0;
		totalBiaya =0;
		kalimat="";

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Order Baru");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblNewLabel.setBounds(58, 28, 112, 33);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 73, 571, 140);
		contentPane.add(scrollPane);
		
		
		tabelModelPesan = new DefaultTableModel(null,header);
		tablePesan = new JTable();
		tablePesan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = tablePesan.getSelectedRow();
				
				int idpel = (int)tabelModelPesan.getValueAt(i, 0);
				String namabar = (String)tabelModelPesan.getValueAt(i, 1);
				int jml = (int)tabelModelPesan.getValueAt(i, 2);
				String strMulai = (String)tabelModelPesan.getValueAt(i, 3);
				int idpes = idPesanan.get(i);
				String idbar = idBarang.get(i);
				int har = listHarga.get(i);
				int sto = listStok.get(i);
				
				currIdPelanggan = idpel;
				currIdPesan = idpes;
				currIdBarang = idbar;
				currHarga = har;
				currStok = sto;
				
				
				try {
					Date tglMulai = new SimpleDateFormat("yyyy-MM-dd").parse(strMulai);
					
					Date tglSelesai = new SimpleDateFormat("yyyy-MM-dd").parse(listSelesai.get(i));
					
					dateChooserMulai.setDate(tglMulai);
					dateChooserSelesai.setDate(tglSelesai);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				textFieldNamaBar.setText(namabar);
				textFieldJml.setText(""+jml);
			}
		});
		tablePesan.setModel(tabelModelPesan);
		scrollPane.setViewportView(tablePesan);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(368, 255, 261, 97);
		contentPane.add(scrollPane_1);
		
		tabelModelStok = new DefaultTableModel(null,header2);
		tableStok = new JTable();
		tableStok.setModel(tabelModelStok);
		scrollPane_1.setViewportView(tableStok);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Pilih data Pesanan");
		lblNewLabel_1.setBounds(58, 59, 157, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblDetilPesanan = new JLabel("Detil Pesanan");
		lblDetilPesanan.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblDetilPesanan.setBounds(58, 228, 112, 33);
		contentPane.add(lblDetilPesanan);
		
		JLabel lblNewLabel_2 = new JLabel("Nama Barang");
		lblNewLabel_2.setBounds(68, 255, 102, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Jumlah");
		lblNewLabel_2_1.setBounds(68, 283, 102, 14);
		contentPane.add(lblNewLabel_2_1);
		
		textFieldNamaBar = new JTextField();
		textFieldNamaBar.setBounds(180, 252, 157, 20);
		contentPane.add(textFieldNamaBar);
		textFieldNamaBar.setColumns(10);
		
		textFieldJml = new JTextField();
		textFieldJml.setBounds(180, 280, 47, 20);
		contentPane.add(textFieldJml);
		textFieldJml.setColumns(10);
		
		
		JButton btnKonfirm = new JButton("Konfirmasi");
		btnKonfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!textFieldJml.getText().equals("")&&dateChooserSelesai.getDate()!=null) {
					jumlahBar = Integer.parseInt(textFieldJml.getText());
					
					
					if (currStok>jumlahBar) {
						konfirmasi();
						ubahStok();
						
						if (currIdPelanggan!=idBefore) {
							kalimat = "iD Pelanggan :"+currIdPelanggan+"\n";
							kalimat+="Pesanan id "+currIdPesan+" Terkonfirmasi \n";
							
							idBefore = currIdPelanggan;
							
							totalBiaya =0;
						} else {
							kalimat+="Pesanan id "+currIdPesan+" Terkonfirmasi \n";
						}
						
//						Penghitungan Biaya
						long jarakMili = dateChooserSelesai.getDate().getTime()-dateChooserMulai.getDate().getTime();
						int jarakHari = (int)TimeUnit.DAYS.convert(jarakMili, TimeUnit.MILLISECONDS);
						
						totalBiaya += currHarga*jarakHari*jumlahBar;
						
						
						
						kosongkanTabel();
						getDataStok();
						getDataTabel();
						kosongkanInput();
						
					} else {
						kalimat+="FAILED |Pesanan id "+currIdPesan+" OUT OF STOCK \n";

					}
					

					textArea.setText(kalimat+"\n"+"Total Harga : Rp."+totalBiaya);
				} else {
					JOptionPane.showMessageDialog(null, "Lengkapi Data Pesanan Terlebih Dahulu!") ;
				}
				
				

				
			}
		});
		btnKonfirm.setBounds(180, 386, 112, 23);
		contentPane.add(btnKonfirm);
		
		JButton btnNewButton = new JButton("Batal Pesan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textFieldJml.getText().equals("")&&dateChooserSelesai.getDate()!=null) {
					
					if (currIdPelanggan!=idBefore) {
						kalimat = "iD Pelanggan :"+currIdPelanggan+"\n";
						kalimat+="Pesanan id "+currIdPesan+" Dibatalkan \n";
						
						idBefore = currIdPelanggan;
						
						totalBiaya =0;
					} else {
						kalimat+="Pesanan id "+currIdPesan+" Dibatalkan \n";
					}
					
					textArea.setText(kalimat+"\n"+"Total Harga : Rp."+totalBiaya);
					
					hapusPesanan();
					kosongkanTabel();
					kosongkanInput();
					getDataStok();
					getDataTabel();
				} else {
					JOptionPane.showMessageDialog(null, "Pilih Data Pesanan Terlebih Dahulu!") ;
				}
			}
		});
		btnNewButton.setBounds(180, 420, 112, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Mulai");
		lblNewLabel_3.setBounds(68, 313, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Selesai");
		lblNewLabel_3_1.setBounds(68, 344, 46, 14);
		contentPane.add(lblNewLabel_3_1);
		
		dateChooserMulai = new JDateChooser();
		dateChooserMulai.setBounds(178, 311, 138, 20);
		contentPane.add(dateChooserMulai);
		
		dateChooserSelesai = new JDateChooser();
		dateChooserSelesai.setBounds(180, 344, 136, 20);
		contentPane.add(dateChooserSelesai);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(368, 363, 264, 80);
		contentPane.add(scrollPane_2);
		
		textArea = new JTextArea();
		scrollPane_2.setViewportView(textArea);
		
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
		

		

		
		
//		Memanggil data tabel Pertama buka
		try {
			getDataTabel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			getDataStok();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dateChooserMulai.setEnabled(false);
		
		JButton btnNewButton_1 = new JButton("Refresh");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				kosongkanTabel();
				getDataStok();
				getDataTabel();
			}
		});
		btnNewButton_1.setBounds(540, 53, 89, 20);
		contentPane.add(btnNewButton_1);
		textFieldNamaBar.disable();
		
	}
	
	public void getDataTabel() {
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT pesanan.id_pelanggan,barang.nama_barang,pesanan.jumlah_pesanan,pesanan.tanggal_mulai,pesanan.tanggal_selesai,pesanan.id_pesanan,barang.id_barang,barang.harga_barang,barang.stok_barang,pelanggan.no_telp FROM pelanggan INNER JOIN pesanan ON pelanggan.id_pelanggan = pesanan.id_pelanggan INNER JOIN barang ON pesanan.id_barang = barang.id_barang WHERE pesanan.konfirm=0";
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				
				Object obj[] = new Object[5];
				obj[0] = rs.getInt(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getInt(3);
				obj[3] = rs.getString(4);
				listSelesai.add(rs.getString(5));
				idPesanan.add(rs.getInt(6));
				idBarang.add(rs.getString(7));
				listHarga.add(rs.getInt(8));
				listStok.add(rs.getInt(9));
				obj[4] = rs.getString(10);
				
				tabelModelPesan.addRow(obj);
			}
			rs.close();
			state.close();
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, ex) ;
		}
		
	}
	
	public void hapusPesanan() {
		try {
			Connection konek=Koneksi.getKoneksi();
			String query = "Delete From pesanan where id_pesanan=?"; 
			PreparedStatement p = konek.prepareStatement(query);
			p.setInt(1, currIdPesan);
			p.executeUpdate();
			p.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void konfirmasi() {
		int jumlahP = Integer.parseInt(textFieldJml.getText());
		String tanggalS = new SimpleDateFormat("yyyy-MM-dd").format(dateChooserSelesai.getDate());
		
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "Update pesanan set jumlah_pesanan=?,tanggal_selesai=?,konfirm=? where id_pesanan=?";
			PreparedStatement p = konek.prepareStatement(query);
			p.setInt(1, jumlahP);
			p.setString(2, tanggalS);
			p.setInt(3, 1);
			p.setInt(4, currIdPesan);

			p.executeUpdate();
			p.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void ubahStok() {
		int jumlahP = Integer.parseInt(textFieldJml.getText());
		int jumlahBaru = currStok - jumlahP;
		
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "Update barang set stok_barang=? where id_barang=?";
			PreparedStatement p = konek.prepareStatement(query);
			p.setInt(1, jumlahBaru);
			p.setString(2, currIdBarang);

			p.executeUpdate();
			p.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void getDataStok() {
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT nama_barang,stok_barang FROM barang";
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				
				Object obj[] = new Object[2];
				obj[0] = rs.getString(1);
				obj[1] = rs.getInt(2);
				
				tabelModelStok.addRow(obj);
			}
			rs.close();
			state.close();
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, ex) ;
		}
		
	}
	
	private void kosongkanTabel() {
		tabelModelPesan.getDataVector().removeAllElements();
		tabelModelStok.getDataVector().removeAllElements();
		
		tabelModelPesan.fireTableDataChanged();
		tabelModelStok.fireTableDataChanged();
		
		listSelesai.removeAll(listSelesai);
		idPesanan.removeAll(idPesanan);
		idBarang.removeAll(idBarang);
		listHarga.removeAll(listHarga);
		listStok.removeAll(listStok);
	}
	
	private void kosongkanInput() {
		textFieldJml.setText("");
		textFieldNamaBar.setText("");
		dateChooserMulai.setDate(null);
		dateChooserSelesai.setDate(null);
	}
	
	public void setLabelNama(String a,int b) {
		this.NamaUser=a;
		this.lvlUser=b;
	}
}
