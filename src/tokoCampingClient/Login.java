package tokoCampingClient;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtuserid;
	private TampilanMenuAdmin fmenu;
	private JPasswordField passwordFieldPass;
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
					Login frame = new Login();
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
	public Login() {
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
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cekuser()){
					fmenu = new TampilanMenuAdmin();
//					fmenu.setUsername(txtuserid.getText());
//					
//					fmenu.setTF(txtuserid.getText(), passwordFieldPass.getText());
//					
//					fmenu.username=txtuserid.getText();	
//					fmenu.textFieldNama.setText(txtuserid.getText());
					fmenu.setLabelNama(NamaUser, lvlUser);
					fmenu.setLocationRelativeTo(null);
					fmenu.setVisible(true);
					setVisible(false);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "User Id atau Password Salah!") ;
				}
			}
		});
		btnLogin.setBounds(154, 174, 89, 23);
		contentPane.add(btnLogin);
		
		
		
		JCheckBox chckbxPass = new JCheckBox("Tampilkan Password");
		chckbxPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxPass.isSelected()) {
					passwordFieldPass.setEchoChar((char)0);
				} else {
					passwordFieldPass.setEchoChar('*');
				}
			}
		});
		chckbxPass.setBounds(154, 145, 141, 23);
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
		btnNewButton.setBounds(154, 208, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/iconTokoCamping/user.png")));
		lblNewLabel.setBounds(153, 11, 81, 74);
		contentPane.add(lblNewLabel);
	}
	
	public boolean cekuser(){
		boolean adauser=false;
		String userid=this.txtuserid.getText();
		String pwd = this.passwordFieldPass.getText();
		
		try
		{
			Connection konek = Koneksi.getKoneksi();
			String query = "SELECT userid,password,akseslv FROM user WHERE userid=? and password=?";
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
}
