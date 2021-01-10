package tokoCampingClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TampilanKatalog extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TampilanKatalog frame = new TampilanKatalog();
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
	public TampilanKatalog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKatalogToko = new JLabel("Katalog Barang");
		lblKatalogToko.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblKatalogToko.setBounds(222, 11, 146, 35);
		contentPane.add(lblKatalogToko);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 127, 541, 175);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(117, 104, 116, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Jenis Barang");
		lblNewLabel.setBounds(21, 107, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Urutan Harga");
		lblNewLabel_1.setBounds(377, 107, 74, 14);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(461, 104, 100, 20);
		contentPane.add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(20, 76, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Cari");
		btnNewButton.setBounds(117, 75, 89, 23);
		contentPane.add(btnNewButton);
	}
}
