package tokoCampingClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.SystemColor;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FormPengembalian extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String header[] = {"Nama Pelanggan","Nama Barang","Jumlah","Tanggal Selesai","No Telp"};
	private DefaultTableModel tabelModel;
	private JLabel lblTanggal;
	private JLabel lblNewLabelRusak;
	private JLabel lblNewLabelHilang;
	
	private JComboBox comboBoxKondisi;

	private JTextField textFieldIdPel;
	private JTextField textFieldIdBar;
	private JTextField textFieldJmlPinjam;
	private JTextField textFieldRusak;
	private JTextField textFieldHilang;
	
	private ArrayList<String> listMulai;
	private ArrayList<String> idBarang;
	private ArrayList<Integer> idPelanggan;
	private ArrayList<Integer> idPesanan;
	private ArrayList<Integer> listHarga;
	private ArrayList<Integer> listStok;
	private ArrayList<Integer> gantiRusak;
	private ArrayList<Integer> gantiHilang;
	
	private JTextArea textArea;
	
	private DateFormat dateFormat;
	public Date tanggalSkrg,tanggalLese;
	
	private String TbIdBar;
	private int currIdPelanggan,idBefore,currIdPesan,currStok;
	private int jmlPesan,lamaPinjam,hargaPerBar,rusakPerBar,hilangPerBar,bayarItem,bayarAwal,totalBayar;
	private int lambat,denda,hilang,rusak;
	
	private String kalimat;
	private JTextField textFieldNamaPeminjam;

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
		

		idPelanggan = new ArrayList<Integer>();
		idPesanan = new ArrayList<Integer>();
		idBarang = new ArrayList<String>();
		listMulai = new ArrayList<String>();
		listHarga = new ArrayList<Integer>();
		listStok = new ArrayList<Integer>();
		gantiRusak = new ArrayList<Integer>();
		gantiHilang = new ArrayList<Integer>();
		
		kalimat = "";
		idBefore = 0;
		bayarAwal = 0;
		totalBayar =0;
		rusakPerBar = 0;
		hilangPerBar = 0;
		denda = 0;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    tanggalSkrg = new Date();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 95, 548, 188);
		contentPane.add(scrollPane);
		
		textFieldIdPel = new JTextField();
		textFieldIdPel.setBounds(21, 71, 115, 20);
		contentPane.add(textFieldIdPel);
		textFieldIdPel.setColumns(10);
		
		tabelModel = new DefaultTableModel(null,header);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				
				comboBoxKondisi.setSelectedIndex(0);
				hilang = 0;
				rusak = 0;
				
				lambat = 0;
				currIdPesan = idPesanan.get(i);
				currIdPelanggan = idPelanggan.get(i);
				currStok = listStok.get(i);
				
				hilangPerBar = gantiHilang.get(i);
				rusakPerBar = gantiRusak.get(i);
				hargaPerBar = listHarga.get(i);
				TbIdBar = idBarang.get(i);
				jmlPesan = (int)table.getValueAt(i, 2);
				String tglSelesai = (String)table.getValueAt(i, 3);
				String tglMulai = listMulai.get(i);
				
				String namaPem = (String) table.getValueAt(i, 0);
				
				try {
					tanggalLese = dateFormat.parse(tglSelesai);
					Date tanggalMulai = dateFormat.parse(tglMulai);
										
					long miliLambat = tanggalSkrg.getTime()-tanggalLese.getTime();
					lambat = (int)Math.floor(TimeUnit.DAYS.convert(miliLambat, TimeUnit.MILLISECONDS));
					
					if (lambat<0) {
						lambat = 0;
					}
					
					long miliLamaPin = tanggalLese.getTime()-tanggalMulai.getTime();
					lamaPinjam = (int)TimeUnit.DAYS.convert(miliLamaPin, TimeUnit.MILLISECONDS);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				textFieldIdBar.setText(TbIdBar);
				textFieldJmlPinjam.setText(""+jmlPesan);
				textFieldRusak.setText(""+rusak);
				textFieldHilang.setText(""+hilang);
				
				textFieldNamaPeminjam.setText(""+currIdPesan);
			}
		});
		table.setModel(tabelModel);
		scrollPane.setViewportView(table);
		
		try {
			kosongkanTabel();
			getDataPeminjam();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldIdPel.setText("");
				textArea.setText("");
				kosongkanInput();
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
	
		
		
		JButton btnNewButton_1 = new JButton("Cari");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kosongkanTabel();
				getDataPeminjam();
			}
		});
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
		lblNewLabel_3.setBounds(21, 407, 71, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Jumlah Barang");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3_1.setBounds(21, 436, 89, 14);
		contentPane.add(lblNewLabel_3_1);
		
		textFieldIdBar = new JTextField();
		textFieldIdBar.setBounds(135, 407, 71, 20);
		contentPane.add(textFieldIdBar);
		textFieldIdBar.setColumns(10);
		
		
		textFieldJmlPinjam = new JTextField();
		textFieldJmlPinjam.setBounds(135, 433, 47, 20);
		contentPane.add(textFieldJmlPinjam);
		textFieldJmlPinjam.setColumns(10);
		
		lblTanggal = new JLabel("Tanggal : "+dateFormat.format(tanggalSkrg));
		lblTanggal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTanggal.setBounds(21, 339, 147, 31);
		contentPane.add(lblTanggal);
		
		JLabel lblNewLabel_5 = new JLabel("ubah");
		lblNewLabel_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				kosongkanInput();
				penggantiTanggal();
			}
		});
		lblNewLabel_5.setForeground(SystemColor.textHighlight);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_5.setBounds(165, 348, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnKonfirmasi = new JButton("Konfirmasi");
		btnKonfirmasi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!textFieldIdBar.getText().equals("")) {
					
					int localHilang = Integer.parseInt(textFieldHilang.getText());
					int localRusak = Integer.parseInt(textFieldRusak.getText());
					int penghitung = localHilang+localRusak;
					
					if (jmlPesan>=penghitung) {
						//Hitung Biaya
						
						if (comboBoxKondisi.getSelectedIndex()==1) {
							rusak = localRusak;
							hilang = localHilang;
						}
						
						bayarAwal =(int) hargaPerBar*jmlPesan*lamaPinjam;
						denda = (int)(hargaPerBar*lambat*jmlPesan)+(rusakPerBar*rusak)+(hilangPerBar*hilang);
						bayarItem = bayarAwal+denda;
						
						if (currIdPelanggan!=idBefore) {
							kalimat = "iD Pelanggan :"+currIdPelanggan+"\n";
							kalimat+="Pesanan id "+currIdPesan+" Kembali \n R"+rusak+" H"+hilang+"\t Rp."+bayarItem+"\n";
							
							idBefore = currIdPelanggan;
							
							
							totalBayar =bayarItem;
						} else {
							totalBayar +=bayarItem;
							kalimat+="Pesanan id "+currIdPesan+" Kembali \n R"+rusak+" H"+hilang+"\t Rp."+bayarItem+"\n";
						}
						
						textArea.setText(kalimat+"\n"+"Total Bayar : Rp."+totalBayar);
						
						kembalikan();
						ubahStok();
						kosongkanInput();
						kosongkanTabel();
						getDataPeminjam();
						
						
					} else {
						JOptionPane.showMessageDialog(null, "Jumlah Kerusakan Tidak Sesuai!") ;
					}
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Pilih Data Dari Tabel!") ;
				}
				
				
				
				
			}
		});
		btnKonfirmasi.setBounds(135, 537, 100, 23);
		contentPane.add(btnKonfirmasi);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(287, 368, 282, 250);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		textFieldRusak = new JTextField();
		textFieldRusak.setBounds(135, 506, 47, 20);
		contentPane.add(textFieldRusak);
		textFieldRusak.setColumns(10);
		
		textFieldHilang = new JTextField();
		textFieldHilang.setBounds(203, 506, 47, 20);
		contentPane.add(textFieldHilang);
		textFieldHilang.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Kondisi Barang");
		lblNewLabel_4.setBounds(21, 464, 100, 14);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabelRusak = new JLabel("Rusak");
		lblNewLabelRusak.setBounds(135, 492, 37, 14);
		contentPane.add(lblNewLabelRusak);
		
		lblNewLabelHilang = new JLabel("Hilang");
		lblNewLabelHilang.setBounds(203, 492, 47, 14);
		contentPane.add(lblNewLabelHilang);
		
		comboBoxKondisi = new JComboBox();
		comboBoxKondisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxKondisi.getSelectedIndex()==1) {
					lblNewLabelRusak.setVisible(true);
					lblNewLabelHilang.setVisible(true);
					
					textFieldRusak.setVisible(true);
					textFieldHilang.setVisible(true);
				} else {
					lblNewLabelRusak.setVisible(false);
					lblNewLabelHilang.setVisible(false);
					
					textFieldRusak.setVisible(false);
					textFieldRusak.revalidate();
					textFieldRusak.repaint();
					
					textFieldHilang.setVisible(false);
					textFieldHilang.revalidate();
					textFieldHilang.repaint();
				}
			}
		});
		comboBoxKondisi.setModel(new DefaultComboBoxModel(new String[] {"Lengkap", "Tidak Lengkap"}));
		comboBoxKondisi.setBounds(135, 461, 115, 20);
		contentPane.add(comboBoxKondisi);
		
		JLabel lblNewLabel_6 = new JLabel("Nama Peminjam");
		lblNewLabel_6.setBounds(21, 381, 89, 14);
		contentPane.add(lblNewLabel_6);
		
		textFieldNamaPeminjam = new JTextField();
		textFieldNamaPeminjam.setBounds(135, 376, 115, 20);
		contentPane.add(textFieldNamaPeminjam);
		textFieldNamaPeminjam.setColumns(10);
		
		JButton btnKeMenu = new JButton("Kembali Ke Menu");
		btnKeMenu.setBounds(439, 637, 130, 23);
		btnKeMenu.setBackground(new Color(230,0,35));
		btnKeMenu.setOpaque(true);
		contentPane.add(btnKeMenu);
		
		
		
		textFieldIdBar.disable();
		textFieldJmlPinjam.disable();
		textFieldNamaPeminjam.disable();
		lblNewLabelRusak.setVisible(false);
		lblNewLabelHilang.setVisible(false);
		textFieldHilang.setVisible(false);
		textFieldRusak.setVisible(false);
	}
	
	private void getDataPeminjam() {
		try {
			Connection konek = Koneksi.getKoneksi();
//			Statement state = konek.createStatement();
			PreparedStatement p =null;
			String query = "SELECT pelanggan.nama_pelanggan,barang.nama_barang,pesanan.jumlah_pesanan,pesanan.tanggal_selesai,pelanggan.no_telp,pelanggan.id_pelanggan,barang.id_barang,pesanan.tanggal_mulai,barang.harga_barang,barang.stok_barang,barang.ganti_rusak,pesanan.id_pesanan,barang.ganti_hilang FROM pelanggan INNER JOIN pesanan ON pelanggan.id_pelanggan=pesanan.id_pelanggan INNER JOIN barang ON pesanan.id_barang = barang.id_barang WHERE pesanan.`tanggal_kembali` IS NULL AND pesanan.konfirm=1 ORDER BY pesanan.`tanggal_selesai`";
			
			if (!textFieldIdPel.getText().equals("")) {
				int id = Integer.parseInt(textFieldIdPel.getText());
				query = "SELECT pelanggan.nama_pelanggan,barang.nama_barang,pesanan.jumlah_pesanan,pesanan.tanggal_selesai,pelanggan.no_telp,pelanggan.id_pelanggan,barang.id_barang,pesanan.tanggal_mulai,barang.harga_barang,barang.stok_barang,barang.ganti_rusak,pesanan.id_pesanan,barang.ganti_hilang FROM pelanggan INNER JOIN pesanan ON pelanggan.id_pelanggan=pesanan.id_pelanggan INNER JOIN barang ON pesanan.id_barang = barang.id_barang WHERE pesanan.`tanggal_kembali` IS NULL AND pesanan.konfirm=1 AND pesanan.id_pelanggan = ? ORDER BY pesanan.`tanggal_selesai`";
				p = konek.prepareStatement(query);
				p.setInt(1, id);
			} else {
								
				p = konek.prepareStatement(query);
			}
			
			
			ResultSet rs = p.executeQuery();
			while(rs.next())
			{
				
				Object obj[] = new Object[5];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getInt(3);
				obj[3] = rs.getString(4);
				obj[4] = rs.getString(5);
				
				idPelanggan.add(rs.getInt(6));
				idBarang.add(rs.getString(7));
				listMulai.add(rs.getString(8));
				listHarga.add(rs.getInt(9));
				listStok.add(rs.getInt(10));
				gantiRusak.add(rs.getInt(11));
				idPesanan.add(rs.getInt(12));
				gantiHilang.add(rs.getInt(13));
				
				
				tabelModel.addRow(obj);
			}
			rs.close();
			p.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void kembalikan() {
		String tglKembaliStr = dateFormat.format(tanggalSkrg);
		
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "Update pesanan set tanggal_kembali=?,jumlah_hilang=?,jumlah_rusak=? where id_pesanan=?";
			PreparedStatement p = konek.prepareStatement(query);
//			p.setInt(1, denda);
			p.setString(1, tglKembaliStr);
			p.setInt(2, hilang);
			p.setInt(3, rusak);
			p.setInt(4, currIdPesan);

			p.executeUpdate();
			p.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void ubahStok() {
		int jumlahBaru = currStok+(jmlPesan-(rusak+hilang)) ;
		
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "Update barang set stok_barang=? where id_barang=?";
			PreparedStatement p = konek.prepareStatement(query);
			p.setInt(1, jumlahBaru);
			p.setString(2, TbIdBar);

			p.executeUpdate();
			p.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void kosongkanTabel() {
		tabelModel.getDataVector().removeAllElements();	
		tabelModel.fireTableDataChanged();
		
		listMulai.removeAll(listMulai);
		idPelanggan.removeAll(idPelanggan);
		idBarang.removeAll(idBarang);
		listHarga.removeAll(listHarga);
		listStok.removeAll(listStok);
		gantiRusak.removeAll(gantiRusak);
		gantiHilang.removeAll(gantiHilang);
		idPesanan.removeAll(idPesanan);

	}
	
	private void kosongkanInput() {
		textFieldNamaPeminjam.setText("");
		textFieldIdBar.setText("");
		textFieldJmlPinjam.setText("");
		comboBoxKondisi.setSelectedIndex(0);
		
		textFieldRusak.setText("");
		textFieldHilang.setText("");
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
