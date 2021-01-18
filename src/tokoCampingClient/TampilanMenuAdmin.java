package tokoCampingClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TampilanMenuAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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
		setBounds(100, 100, 653, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelUtama = new JPanel();
		panelUtama.setLayout(null);
		panelUtama.setBackground(Color.WHITE);
		panelUtama.setBounds(61, 164, 518, 242);
		contentPane.add(panelUtama);
		
		JPanel panelTampilanClient = new JPanel();
		panelTampilanClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TambahBarang frameTambah = new TambahBarang();
				frameTambah.setVisible(true);
			}
		});
		panelTampilanClient.setLayout(null);
		panelTampilanClient.setBounds(32, 30, 141, 82);
		panelUtama.add(panelTampilanClient);
		
		JLabel lblNewLabel = new JLabel("Tambah Barang Baru");
		lblNewLabel.setBounds(17, 57, 114, 14);
		panelTampilanClient.add(lblNewLabel);
		
		JPanel panelUbahStok = new JPanel();
		panelUbahStok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UbahDataBarang frameUbah = new UbahDataBarang();
				frameUbah.setVisible(true);
			}
		});
		panelUbahStok.setLayout(null);
		panelUbahStok.setBounds(191, 30, 141, 82);
		panelUtama.add(panelUbahStok);
		
		JLabel lblNewLabel_1 = new JLabel("Ubah Stok Barang");
		lblNewLabel_1.setBounds(24, 57, 94, 14);
		panelUbahStok.add(lblNewLabel_1);
		
		JPanel panelCekPinjam = new JPanel();
		panelCekPinjam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CekBarangDipinjam framePinjam = new CekBarangDipinjam();
				framePinjam.setVisible(true);
			}
		});
		panelCekPinjam.setLayout(null);
		panelCekPinjam.setBounds(353, 30, 141, 82);
		panelUtama.add(panelCekPinjam);
		
		JLabel lblNewLabel_2 = new JLabel("Cek Barang Di Pinjam");
		lblNewLabel_2.setBounds(21, 57, 110, 14);
		panelCekPinjam.add(lblNewLabel_2);
		
		JPanel panelPengembalian = new JPanel();
		panelPengembalian.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FormPengembalian frameKembali = new FormPengembalian();
				frameKembali.setVisible(true);
			}
		});
		panelPengembalian.setLayout(null);
		panelPengembalian.setBounds(97, 137, 141, 82);
		panelUtama.add(panelPengembalian);
		
		JLabel lblFormPengembalian = new JLabel("Form Pengembalian");
		lblFormPengembalian.setBounds(30, 57, 101, 14);
		panelPengembalian.add(lblFormPengembalian);
		
		JPanel panelLaporan = new JPanel();
		panelLaporan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LaporanPendapatan frameLapor = new LaporanPendapatan();
				frameLapor.setVisible(true);
			}
		});
		panelLaporan.setLayout(null);
		panelLaporan.setBounds(280, 137, 141, 82);
		panelUtama.add(panelLaporan);
		
		JLabel lblBukaLaporan = new JLabel("Buka Laporan");
		lblBukaLaporan.setBounds(38, 57, 77, 14);
		panelLaporan.add(lblBukaLaporan);
		
		JLabel lblPanelAdmin = new JLabel("(Panel Admin)");
		lblPanelAdmin.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblPanelAdmin.setBounds(230, 79, 177, 35);
		contentPane.add(lblPanelAdmin);
		
		JLabel lblSistemPenyewaan = new JLabel("Sistem Sewa");
		lblSistemPenyewaan.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblSistemPenyewaan.setBounds(264, 11, 116, 35);
		contentPane.add(lblSistemPenyewaan);
		
		JLabel lblPeralatanCamping = new JLabel("Peralatan Camping");
		lblPeralatanCamping.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblPeralatanCamping.setBounds(197, 41, 250, 35);
		contentPane.add(lblPeralatanCamping);
	}
}
