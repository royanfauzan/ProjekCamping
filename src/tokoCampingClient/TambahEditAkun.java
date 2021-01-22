package tokoCampingClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class TambahEditAkun extends JFrame {

	private JPanel contentPane;
	private JTextField txtuserid;
	private TampilanMenuAdmin fmenu;
	private JPasswordField passwordFieldPass;
	private String NamaUser;
	private int lvlUser;
	private String passwordBaru;
	private JPasswordField passwordFieldBaru;

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
					TambahEditAkun frame = new TambahEditAkun();
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
	public TambahEditAkun() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserId = new JLabel("User id");
		lblUserId.setBounds(73, 96, 46, 14);
		contentPane.add(lblUserId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(73, 121, 46, 14);
		contentPane.add(lblPassword);
		
		txtuserid = new JTextField();
		txtuserid.setBounds(154, 93, 108, 20);
		contentPane.add(txtuserid);
		txtuserid.setColumns(10);
		
		passwordFieldPass = new JPasswordField();
		passwordFieldPass.setBounds(154, 118, 108, 20);
		passwordFieldPass.setEchoChar('*');
		contentPane.add(passwordFieldPass);
		
		JButton btnLogin = new JButton("Tambah Akun");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!txtuserid.getText().equals("")&&!passwordFieldPass.getText().equals("")) {
					if(cekuser()){
						JOptionPane.showMessageDialog(null, "User Sudah Ada!") ;
					}else {
						tambahUser();
						JOptionPane.showMessageDialog(null, "Tambah User Berhasil!") ;
					}
				}else {
					JOptionPane.showMessageDialog(null, "Data Kosong!") ;
				}
				
				
			}
		});
		btnLogin.setBounds(90, 196, 108, 23);
		contentPane.add(btnLogin);
		
		
		
		JCheckBox chckbxPass = new JCheckBox("Tampilkan Password");
		chckbxPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxPass.isSelected()) {
					passwordFieldPass.setEchoChar((char)0);
					passwordFieldBaru.setEchoChar((char)0);
				} else {
					passwordFieldPass.setEchoChar('*');
					passwordFieldBaru.setEchoChar('*');
				}
			}
		});
		chckbxPass.setBounds(268, 117, 130, 23);
		contentPane.add(chckbxPass);
		
		JButton btnNewButton = new JButton("BATAL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TampilanMenuUtama frameMenu = new TampilanMenuUtama();
				frameMenu.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton.setBounds(149, 230, 113, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/iconTokoCamping/user.png")));
		lblNewLabel.setBounds(153, 11, 81, 74);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Update Akun");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cekuser()){
					updateUser();
					JOptionPane.showMessageDialog(null, "Update Sukses!") ;
				}else {
					JOptionPane.showMessageDialog(null, "User Id atau Password Salah!") ;
				}
			}
		});
		btnNewButton_1.setBounds(208, 196, 113, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("New Password");
		lblNewLabel_1.setBounds(73, 151, 81, 14);
		contentPane.add(lblNewLabel_1);
		
		passwordFieldBaru = new JPasswordField();
		passwordFieldBaru.setBounds(154, 149, 108, 20);
		contentPane.add(passwordFieldBaru);
		
		passwordFieldBaru.setEchoChar('*');
	}
	
	public boolean cekuser(){
		boolean adauser=false;
		String userid=this.txtuserid.getText();
		String pwd = this.passwordFieldPass.getText();
		
		try
		{
			Connection konek = Koneksi.getKoneksi();
			String query = "SELECT userid,password,akseslv FROM user WHERE userid=? AND password=?";
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1, userid);
			p.setString(2, pwd);
			
			ResultSet rs = p.executeQuery();
			while(rs.next())
			{	
				adauser=true;
				NamaUser=rs.getString(1);
				lvlUser=rs.getInt(3);
			}
			rs.close();
		}
		catch(Exception ex)
		{
		}
		return (adauser);
	}
	
	public void updateUser() {
		String userName = txtuserid.getText();
		String newPass = passwordFieldBaru.getText();
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "UPDATE `user` SET password=? WHERE userid=?";
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1,newPass);
			p.setString(2,userName );
			
			
			p.executeUpdate();
			p.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			System.out.print(e);
		}
	}
	
	
	
	public void tambahUser() {
		String userName = txtuserid.getText();
		String newPass = passwordFieldPass.getText();
		try {
			Connection konek = Koneksi.getKoneksi();
			String query = "INSERT INTO `user` VALUES(?,?,?)";
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1, userName);
			p.setString(2, newPass);
			p.setInt(3, 1);
			
			p.executeUpdate();
			p.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			System.out.print(e);
		}
	}
}
