package tokoCampingClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TampilanMenuAdmin extends JFrame {

	private JPanel contentPane;
	private JLabel lblNama;
	private String NamaUser;
	private int lvlUser;
	private JLabel lblEditUser;

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
					TampilanMenuAdmin frame = new TampilanMenuAdmin();
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
	public TampilanMenuAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelUtama = new JPanel();
		panelUtama.setLayout(null);
		panelUtama.setBackground(Color.WHITE);
		panelUtama.setBounds(61, 164, 518, 256);
		contentPane.add(panelUtama);
		
		JPanel panelTampilanClient = new JPanel();
		panelTampilanClient.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelTampilanClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TambahBarang frameTambah = new TambahBarang();
				frameTambah.setVisible(true);
				frameTambah.setLabelNama(NamaUser, lvlUser);
				setVisible(false);
				dispose();
			}
		});
		panelTampilanClient.setLayout(null);
		panelTampilanClient.setBounds(190, 30, 141, 82);
		panelUtama.add(panelTampilanClient);
		
		JLabel lblNewLabel = new JLabel("Tambah Barang Baru");
		lblNewLabel.setBounds(20, 61, 121, 21);
		panelTampilanClient.add(lblNewLabel);
		
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setIcon(new ImageIcon(TampilanMenuAdmin.class.getResource("/iconTokoCamping/TambahBarang.png")));
		lblNewLabel_5_1.setBounds(42, 11, 53, 53);
		panelTampilanClient.add(lblNewLabel_5_1);
		
		JPanel panelUbahStok = new JPanel();
		panelUbahStok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelUbahStok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UbahDataBarang frameUbah = new UbahDataBarang();
				frameUbah.setVisible(true);
				frameUbah.setLabelNama(NamaUser, lvlUser);
				setVisible(false);
				dispose();
			}
		});
		panelUbahStok.setLayout(null);
		panelUbahStok.setBounds(190, 137, 141, 82);
		panelUtama.add(panelUbahStok);
		
		JLabel lblNewLabel_1 = new JLabel("Ubah Stok Barang");
		lblNewLabel_1.setBounds(34, 61, 97, 21);
		panelUbahStok.add(lblNewLabel_1);
		
		JLabel lblNewLabel_5_4 = new JLabel("");
		lblNewLabel_5_4.setIcon(new ImageIcon(TampilanMenuAdmin.class.getResource("/iconTokoCamping/ubahStok.png")));
		lblNewLabel_5_4.setBounds(43, 11, 53, 53);
		panelUbahStok.add(lblNewLabel_5_4);
		
		JPanel panelCekPinjam = new JPanel();
		panelCekPinjam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelCekPinjam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CekBarangDipinjam framePinjam = new CekBarangDipinjam();
				framePinjam.setVisible(true);
				framePinjam.setLabelNama(NamaUser, lvlUser);
				setVisible(false);
				dispose();
			}
		});
		panelCekPinjam.setLayout(null);
		panelCekPinjam.setBounds(353, 30, 141, 82);
		panelUtama.add(panelCekPinjam);
		
		JLabel lblNewLabel_2 = new JLabel("Cek Barang Di Pinjam");
		lblNewLabel_2.setBounds(21, 61, 110, 21);
		panelCekPinjam.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5_2 = new JLabel("");
		lblNewLabel_5_2.setIcon(new ImageIcon(TampilanMenuAdmin.class.getResource("/iconTokoCamping/CekBarangDipinjam.png")));
		lblNewLabel_5_2.setBounds(40, 11, 53, 53);
		panelCekPinjam.add(lblNewLabel_5_2);
		
		JPanel panelPengembalian = new JPanel();
		panelPengembalian.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelPengembalian.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FormPengembalian frameKembali = new FormPengembalian();
				frameKembali.setVisible(true);
				frameKembali.setLabelNama(NamaUser, lvlUser);
				setVisible(false);
				dispose();
			}
		});
		panelPengembalian.setLayout(null);
		panelPengembalian.setBounds(32, 137, 141, 82);
		panelUtama.add(panelPengembalian);
		
		JLabel lblFormPengembalian = new JLabel("Form Pengembalian");
		lblFormPengembalian.setBounds(25, 61, 106, 21);
		panelPengembalian.add(lblFormPengembalian);
		
		JLabel lblNewLabel_5_3 = new JLabel("");
		lblNewLabel_5_3.setIcon(new ImageIcon(TampilanMenuAdmin.class.getResource("/iconTokoCamping/FormPengembalian.png")));
		lblNewLabel_5_3.setBounds(42, 11, 53, 53);
		panelPengembalian.add(lblNewLabel_5_3);
		
		JPanel panelLaporan = new JPanel();
		panelLaporan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelLaporan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LaporanPendapatan frameLapor = new LaporanPendapatan();
				frameLapor.setVisible(true);
				frameLapor.setLabelNama(NamaUser, lvlUser);
				setVisible(false);
				dispose();
			}
		});
		panelLaporan.setLayout(null);
		panelLaporan.setBounds(353, 137, 141, 82);
		panelUtama.add(panelLaporan);
		
		JLabel lblBukaLaporan = new JLabel("Buka Laporan");
		lblBukaLaporan.setBounds(43, 61, 76, 21);
		panelLaporan.add(lblBukaLaporan);
		
		JLabel lblNewLabel_5_5 = new JLabel("");
		lblNewLabel_5_5.setIcon(new ImageIcon(TampilanMenuAdmin.class.getResource("/iconTokoCamping/BukaLaporan.png")));
		lblNewLabel_5_5.setBounds(43, 11, 53, 53);
		panelLaporan.add(lblNewLabel_5_5);
		
		JPanel panelOrder = new JPanel();
		panelOrder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				OrderBaruAdmin frameOrder = new OrderBaruAdmin();
				frameOrder.setVisible(true);
				frameOrder.setLabelNama(NamaUser, lvlUser);
				setVisible(false);
				dispose();
			}
		});
		panelOrder.setLayout(null);
		panelOrder.setBounds(32, 30, 141, 82);
		panelUtama.add(panelOrder);
		
		JLabel lblOrderBaru = new JLabel("Order Masuk");
		lblOrderBaru.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblOrderBaru.setBounds(38, 57, 79, 21);
		panelOrder.add(lblOrderBaru);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(TampilanMenuAdmin.class.getResource("/iconTokoCamping/Order-Masuk.png")));
		lblNewLabel_5.setBounds(38, 11, 53, 53);
		panelOrder.add(lblNewLabel_5);
		
		lblEditUser = new JLabel("Edit User");
		lblEditUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TambahEditAkun frameAkun = new TambahEditAkun();
				frameAkun.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		lblEditUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEditUser.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblEditUser.setForeground(Color.BLUE);
		lblEditUser.setBounds(228, 242, 62, 14);
		panelUtama.add(lblEditUser);
		
		
		lblNama = new JLabel("User");
		lblNama.setBounds(61, 148, 116, 14);
		contentPane.add(lblNama);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TampilanMenuAdmin.class.getResource("/iconTokoCamping/tent.png")));
		lblNewLabel_4.setBounds(23, 24, 90, 90);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblXCamp = new JLabel("X");
		lblXCamp.setFont(new Font("Century Gothic", Font.BOLD, 34));
		lblXCamp.setBounds(122, 24, 42, 41);
		contentPane.add(lblXCamp);
		
		JLabel lblCamp = new JLabel("camp");
		lblCamp.setFont(new Font("Century Gothic", Font.BOLD, 27));
		lblCamp.setBounds(145, 30, 87, 35);
		contentPane.add(lblCamp);
		
		JLabel lblSistemPenyewaan = new JLabel("Sistem Sewa");
		lblSistemPenyewaan.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblSistemPenyewaan.setBounds(122, 54, 116, 35);
		contentPane.add(lblSistemPenyewaan);
		
		JLabel lblPeralatanCampingadmin = new JLabel("Peralatan Camping(Admin)");
		lblPeralatanCampingadmin.setFont(new Font("Century Gothic", Font.BOLD, 22));
		lblPeralatanCampingadmin.setBounds(122, 76, 308, 35);
		contentPane.add(lblPeralatanCampingadmin);
		
		JButton btnNewButton = new JButton("Kembali Ke Menu Utama");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TampilanMenuUtama frameUtama = new TampilanMenuUtama();
				frameUtama.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton.setBounds(409, 431, 170, 23);
		contentPane.add(btnNewButton);
		
		lblEditUser.setVisible(false);
	}
	
	
	public void setLabelNama(String a,int b) {
		this.NamaUser=a;
		this.lvlUser=b;
		lblNama.setText(NamaUser);
		if (lvlUser>1) {
			lblEditUser.setVisible(true);
		}				
	}
}
