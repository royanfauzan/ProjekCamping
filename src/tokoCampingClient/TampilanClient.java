package tokoCampingClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;

public class TampilanClient extends JFrame {

	private JPanel contentPane;
	public JTextField textFieldNama;
	String header[] = {"Jenis Barang","Nama Barang","Stok","Harga(Rp/Hari)"};
	DefaultTableModel tabelModel;
	String header2[] = {"id Barang"};
	DefaultTableModel tabelModel2;
	private JTable table;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textFieldBarang;
	private JLabel lblNewLabel_4;
	private JTextField textFieldJumlah;
	private JTextField textFieldIdUser;
	private JTextField textFieldStok;
	private JTextArea textArea;
	private int id,stok,bayar,total_bayar,hargaPer,jumlah,lama,konfirm;
	private String idBarang;
	private String kalimat;
	private String tanggal_mulai,tanggal_selesai;
	private ArrayList<String> nyoba;
	private JDateChooser dateChooserpinjam;
	
	public String username;
	
	private SimpleDateFormat pinjem ;
	private JTextField textFieldHari;
	private JTextField textFieldNoTelp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TampilanClient frame = new TampilanClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TampilanClient() {
//		initComponen("");

		nyoba = new ArrayList<String>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nama.");
		lblNewLabel.setBounds(20, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		textFieldNama = new JTextField();
		textFieldNama.setBounds(20, 26, 145, 19);
		contentPane.add(textFieldNama);
		textFieldNama.setColumns(10);
//		textFieldNama.setText(a);
		
		textFieldIdUser = new JTextField();
		textFieldIdUser.setBounds(633, 26, 40, 20);
		contentPane.add(textFieldIdUser);
		textFieldIdUser.setColumns(10);
		textFieldIdUser.setText(username);
		

		
		JLabel lblNewLabel_5 = new JLabel("Kode Peminjaman");
		lblNewLabel_5.setBounds(525, 29, 109, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_1 = new JLabel("List Barang");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 94, 131, 20);
		contentPane.add(lblNewLabel_1);
		
		textFieldHari = new JTextField();
		textFieldHari.setBounds(106, 402, 63, 20);
		contentPane.add(textFieldHari);
		textFieldHari.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Tanggal Pesan");
		lblNewLabel_7.setBounds(10, 377, 86, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Lama Pinjam");
		lblNewLabel_8.setBounds(10, 405, 86, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Hari");
		lblNewLabel_9.setBounds(179, 405, 46, 14);
		contentPane.add(lblNewLabel_9);
		
		lblNewLabel_2 = new JLabel("*Klik barang yang ingin di pesan");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_2.setBounds(10, 126, 180, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Nama Barang");
		lblNewLabel_3.setBounds(10, 282, 86, 14);
		contentPane.add(lblNewLabel_3);
		
		textFieldBarang = new JTextField();
		textFieldBarang.setBounds(106, 279, 143, 20);
		contentPane.add(textFieldBarang);
		textFieldBarang.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Jumlah Pesan");
		lblNewLabel_4.setBounds(10, 343, 88, 14);
		contentPane.add(lblNewLabel_4);
		
		textFieldJumlah = new JTextField();
		textFieldJumlah.setBounds(106, 340, 65, 20);
		contentPane.add(textFieldJumlah);
		textFieldJumlah.setColumns(10);
		
		
		JLabel lblNewLabel_6 = new JLabel("Stok");
		lblNewLabel_6.setBounds(10, 307, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		textFieldStok = new JTextField();
		textFieldStok.setBounds(106, 310, 65, 20);
		contentPane.add(textFieldStok);
		textFieldStok.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(259, 282, 414, 208);
		contentPane.add(textArea);
		
		
		//Mulai Tabel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 151, 663, 120);
		contentPane.add(scrollPane);
		
		tabelModel2 = new DefaultTableModel(null,header2);
		
		tabelModel = new DefaultTableModel(null,header);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				
				String namabar=(String)tabelModel.getValueAt(i, 1);
				stok=(int)tabelModel.getValueAt(i, 2);
				String stokStr=Integer.toString(stok);
				
				hargaPer=(int)tabelModel.getValueAt(i, 3);
				
				textFieldBarang.setText(namabar);
				textFieldStok.setText(stokStr);
				
//				idBarang=(String)nyoba.get(i);
				idBarang=(String)pengambilIdBa();
				
				
				
				
				
			}
	
		});
		table.setModel(tabelModel);
		scrollPane.setViewportView(table);
		
		
		
		JButton tombolDaftar = new JButton("Daftar");
		tombolDaftar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				daftar();
				id =pengambilIdPe();
				textFieldIdUser.setText(""+id);
				getDataTabel();
			}
		});
		tombolDaftar.setBounds(350, 25, 89, 23);
		contentPane.add(tombolDaftar);
		
		kalimat="Barang \t | Hari"+"\t"+"Harga"+"\t"+"Kembali";
		textArea.setText(kalimat);
		total_bayar=0;
		konfirm=0;
		JButton tombolPesan = new JButton("Pesan");
		tombolPesan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jumlah = Integer.parseInt(textFieldJumlah.getText());
				lama= Integer.parseInt(textFieldHari.getText());
				bayar = hargaPer*jumlah*lama;
				
				total_bayar+=bayar;
				


				pinjem = new SimpleDateFormat("yyyy-MM-dd");
				String pengambil=gettanggalan();
				Date tanggal = new Date();
				Date tanggal2 = new Date();
				
				tanggal = dateChooserpinjam.getDate();
				
				tanggal_mulai = pinjem.format(tanggal);
				
				Calendar cal = Calendar.getInstance();
				
				cal.setTime(tanggal);
				cal.add(Calendar.DATE, lama);
				
				tanggal2 = cal.getTime();
				
				tanggal_selesai = pinjem.format(tanggal2);
				
				kalimat+="\n"+jumlah+textFieldBarang.getText()+" |"+lama+"h"+"\t"+bayar+"\t"+tanggal_selesai;
				textArea.setText(kalimat);
				
				pesan();
				
//				pinjem = new SimpleDateFormat("yyyy-MM-dd");
//				String pengambil=gettanggalan();
//				Date tanggal = new Date();
//				
//				try {
//					tanggal=pinjem.parse(pengambil);
//				} catch (ParseException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				pinjem.format(dateChooserpinjam.getDate());
//				
//				penanggal();
//				
//								
//				textArea.setText(pinjem.format(dateChooserpinjam.getDate())+"\n"+tanggal);
//				
//				Calendar cal = Calendar.getInstance();
//				
//				cal.setTime(tanggal);
//				cal.add(Calendar.DATE, 7);
//				
//				tanggal = cal.getTime();
//				
//				dateChooserpinjam.setDate(tanggal);
			}
		});
		tombolPesan.setBounds(106, 450, 89, 23);
		contentPane.add(tombolPesan);
		
		
		
		JButton tombolSelesai = new JButton("Hitung TOTAL");
		tombolSelesai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				kalimat+="\n\n"+"\tTotal Bayar \t:"+total_bayar;
				textArea.setText(kalimat);
			}
		});
		tombolSelesai.setBounds(324, 501, 115, 23);
		contentPane.add(tombolSelesai);
		
//		JButton btnNewButton = new JButton("Tampilkan");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				getDataTabel();
//			}
//		});
//		btnNewButton.setBounds(259, 117, 89, 23);
//		contentPane.add(btnNewButton);
		
		dateChooserpinjam = new JDateChooser();
		dateChooserpinjam.setBounds(106, 371, 131, 20);
		contentPane.add(dateChooserpinjam);
		
		textFieldNoTelp = new JTextField();
		textFieldNoTelp.setBounds(175, 26, 145, 20);
		contentPane.add(textFieldNoTelp);
		textFieldNoTelp.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("No.Telpon");
		lblNewLabel_10.setBounds(175, 11, 102, 14);
		contentPane.add(lblNewLabel_10);
		
		
		
		//disable
		textFieldIdUser.disable();
		textFieldStok.disable();
		textFieldBarang.disable();
	}



	/**
	 * Create the frame.
	 */
//	public TampilanClient(String usern) {
//		initComponen(usern);
//		
//	}
	
//	private void initComponen(String a) {
//		
//	}
	
	public void daftar() {
		String nama = textFieldNama.getText();
		String noTelp = textFieldNoTelp.getText();
		
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "INSERT INTO pelanggan(nama_pelanggan,no_telp) VALUES(?,?)";
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1, nama);
			p.setString(2, noTelp);
			p.executeUpdate();
			p.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.print(e);
		}
				
	}
	
	public void pesan() {
		
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "INSERT INTO pesanan(jumlah_pesanan,total_bayar,tanggal_mulai,tanggal_selesai,id_barang,id_pelanggan,konfirm) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement p = konek.prepareStatement(query);
			p.setInt(1, jumlah);
			p.setInt(2, bayar);
			p.setString(3, tanggal_mulai);
			p.setString(4, tanggal_selesai);
			p.setString(5, idBarang);
			p.setInt(6, id);
			p.setInt(7, konfirm);
			p.executeUpdate();
			p.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.print(e);
		}
				
	}
	
	public int pengambilIdPe() {
		int a=0;
		try {
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT id_pelanggan FROM pelanggan ORDER BY id_pelanggan DESC LIMIT 1";
			ResultSet hasil = state.executeQuery(query);
			while (hasil.next()) {
				a = hasil.getInt(1);	
			}
			hasil.close();
			state.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.print(e);
		}
		return a;
	}
	
	public void getDataTabel() {
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT jenis_barang,nama_barang,stok_barang,harga_barang,id_barang FROM barang";
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				
				Object obj[] = new Object[4];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getInt(3);
				obj[3] = rs.getInt(4);
				
				tabelModel.addRow(obj);
				nyoba.add((String)rs.getString(5));
			}
			rs.close();
			state.close();
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, ex) ;
		}
		
	}
	
	public String pengambilIdBa() {
		String a=textFieldBarang.getText();
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "SELECT id_barang FROM barang where nama_barang=?";
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1, a);
			ResultSet hasil = p.executeQuery();
			while (hasil.next()) {
				a = hasil.getString(1);	
			}
			hasil.close();
			p.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.print(e);
		}
		return a;
	}
	
	public void penanggal() {	
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "INSERT INTO tanggalan VALUES(?)";
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1, pinjem.format(dateChooserpinjam.getDate()));
			p.executeUpdate();
			p.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.print(e);
		}
				
	}
	
	public String gettanggalan(){
		String ku="";
		try {
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT tanggal FROM tanggalan";
			ResultSet hasil = state.executeQuery(query);
			while (hasil.next()) {
				
				ku = hasil.getString(1);
				
				
			}
			hasil.close();
			state.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.print(e);
		}
		return ku;
	}
	
	public void setUsername(String a) {
		this.username=a;
		System.out.println(a);
		System.out.println(getUsername());
	}
	
	public String getUsername() {
		System.out.println(username);
		return this.username;
		
	}
	
	public void setTF(String a,String b) {
		this.textFieldJumlah.setText(a);
		this.textFieldIdUser.setText(b);
	}
}
