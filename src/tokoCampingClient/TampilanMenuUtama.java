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
		panelTampilanClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TampilanClient clientFrame = new TampilanClient();
				clientFrame.setVisible(true);
			}
		});
		panelTampilanClient.setBounds(31, 33, 141, 82);
		panelUtama.add(panelTampilanClient);
		panelTampilanClient.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pinjam Barang");
		lblNewLabel.setBounds(36, 57, 81, 14);
		panelTampilanClient.add(lblNewLabel);
		
		JPanel panelBukaKatalog = new JPanel();
		panelBukaKatalog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TampilanKatalog frameKatalog = new TampilanKatalog();
				frameKatalog.setVisible(true);
			}
		});
		panelBukaKatalog.setBounds(190, 33, 141, 82);
		panelUtama.add(panelBukaKatalog);
		panelBukaKatalog.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Lihat Katalog");
		lblNewLabel_1.setBounds(41, 57, 71, 14);
		panelBukaKatalog.add(lblNewLabel_1);
		
		JPanel panelCekPinjam = new JPanel();
		panelCekPinjam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CekPinjamanClient framePinjamC = new CekPinjamanClient();
				framePinjamC.setVisible(true);
			}
		});
		panelCekPinjam.setBounds(352, 33, 141, 82);
		panelUtama.add(panelCekPinjam);
		panelCekPinjam.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Cek Barang Pinjaman");
		lblNewLabel_2.setBounds(25, 57, 106, 14);
		panelCekPinjam.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Kelola Sistem");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login framL = new Login();
				framL.setVisible(true);
			}
		});
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(437, 193, 71, 14);
		panelUtama.add(lblNewLabel_3);
		
		JLabel lblSistemPenyewaan = new JLabel("Sistem Sewa");
		lblSistemPenyewaan.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblSistemPenyewaan.setBounds(233, 11, 116, 35);
		contentPane.add(lblSistemPenyewaan);
		
		JLabel lblPeralatanCamping = new JLabel("Peralatan Camping");
		lblPeralatanCamping.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblPeralatanCamping.setBounds(166, 41, 250, 35);
		contentPane.add(lblPeralatanCamping);
	}
}
