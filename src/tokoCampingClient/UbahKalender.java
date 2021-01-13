package tokoCampingClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class UbahKalender extends JFrame {

	private JPanel contentPane;
	FormPengembalian pengembalian;

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
				
//				try {
//					UbahKalender frame = new UbahKalender();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UbahKalender(FormPengembalian locKembali) {
		pengembalian = locKembali;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 306, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooser.setBounds(62, 53, 151, 20);
		contentPane.add(dateChooser);
		
		JButton btnNewButton = new JButton("Ubah Tanggal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pengembalian.setTanggal(dateChooser.getDate());
				setVisible(false);
				dispose();
			}
		});
		btnNewButton.setBounds(85, 104, 111, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblPilihTanggal = new JLabel("Pilih Tanggal");
		lblPilihTanggal.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblPilihTanggal.setBounds(32, 7, 196, 35);
		contentPane.add(lblPilihTanggal);
		
		
	}
	
}
