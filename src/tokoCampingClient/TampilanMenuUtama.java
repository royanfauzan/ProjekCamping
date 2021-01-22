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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class TampilanMenuUtama extends JFrame {

	private JPanel contentPane;

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
					TampilanMenuUtama frame = new TampilanMenuUtama();
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
	public TampilanMenuUtama() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelUtama = new JPanel();
		panelUtama.setBackground(Color.WHITE);
		panelUtama.setBounds(37, 119, 518, 218);
		contentPane.add(panelUtama);
		panelUtama.setLayout(null);
		
		JPanel panelTampilanClient = new JPanel();
		panelTampilanClient.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelTampilanClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TampilanClient clientFrame = new TampilanClient();
				clientFrame.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		panelTampilanClient.setBounds(31, 33, 141, 82);
		panelUtama.add(panelTampilanClient);
		panelTampilanClient.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pinjam Barang");
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setBounds(39, 57, 81, 21);
		panelTampilanClient.add(lblNewLabel);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(TampilanMenuUtama.class.getResource("/iconTokoCamping/pinjamBarang.png")));
		lblNewLabel_5.setBounds(47, 11, 53, 53);
		panelTampilanClient.add(lblNewLabel_5);
		
		JPanel panelBukaKatalog = new JPanel();
		panelBukaKatalog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBukaKatalog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TampilanKatalog frameKatalog = new TampilanKatalog();
				frameKatalog.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		panelBukaKatalog.setBounds(190, 33, 141, 82);
		panelUtama.add(panelBukaKatalog);
		panelBukaKatalog.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Lihat Katalog");
		lblNewLabel_1.setBounds(41, 57, 71, 21);
		panelBukaKatalog.add(lblNewLabel_1);
		
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setIcon(new ImageIcon(TampilanMenuUtama.class.getResource("/iconTokoCamping/lihatKatalog.png")));
		lblNewLabel_5_1.setBounds(40, 11, 53, 53);
		panelBukaKatalog.add(lblNewLabel_5_1);
		
		JPanel panelCekPinjam = new JPanel();
		panelCekPinjam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelCekPinjam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CekPinjamanClient framePinjamC = new CekPinjamanClient();
				framePinjamC.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		panelCekPinjam.setBounds(352, 33, 141, 82);
		panelUtama.add(panelCekPinjam);
		panelCekPinjam.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Cek Barang Pinjaman");
		lblNewLabel_2.setBounds(25, 57, 106, 21);
		panelCekPinjam.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5_2 = new JLabel("");
		lblNewLabel_5_2.setIcon(new ImageIcon(TampilanMenuUtama.class.getResource("/iconTokoCamping/CekBarangDipinjamClient.png")));
		lblNewLabel_5_2.setBounds(41, 11, 53, 53);
		panelCekPinjam.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_3 = new JLabel("Kelola Sistem");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login framL = new Login();
				framL.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(437, 193, 71, 14);
		panelUtama.add(lblNewLabel_3);
		
		JLabel lblSistemPenyewaan = new JLabel("Sistem Sewa");
		lblSistemPenyewaan.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblSistemPenyewaan.setBounds(109, 41, 116, 35);
		contentPane.add(lblSistemPenyewaan);
		
		JLabel lblPeralatanCamping = new JLabel("Peralatan Camping");
		lblPeralatanCamping.setFont(new Font("Century Gothic", Font.BOLD, 22));
		lblPeralatanCamping.setBounds(109, 63, 250, 35);
		contentPane.add(lblPeralatanCamping);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TampilanMenuUtama.class.getResource("/iconTokoCamping/tent.png")));
		lblNewLabel_4.setBounds(10, 11, 90, 90);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblXCamp = new JLabel("X");
		lblXCamp.setFont(new Font("Century Gothic", Font.BOLD, 34));
		lblXCamp.setBounds(109, 11, 42, 41);
		contentPane.add(lblXCamp);
		
		JLabel lblCamp = new JLabel("camp");
		lblCamp.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblCamp.setBounds(132, 17, 87, 35);
		contentPane.add(lblCamp);
	}
}
