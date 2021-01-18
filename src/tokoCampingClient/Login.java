package tokoCampingClient;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
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


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtuserid;
	private TampilanMenuAdmin fmenu;
	private JPasswordField passwordFieldPass;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserId = new JLabel("User id");
		lblUserId.setBounds(65, 45, 46, 14);
		contentPane.add(lblUserId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(65, 70, 46, 14);
		contentPane.add(lblPassword);
		
		txtuserid = new JTextField();
		txtuserid.setBounds(146, 42, 108, 20);
		contentPane.add(txtuserid);
		txtuserid.setColumns(10);
		
		passwordFieldPass = new JPasswordField();
		passwordFieldPass.setBounds(146, 67, 108, 20);
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
				
					fmenu.setLocationRelativeTo(null);
					fmenu.setVisible(true);
				}
			}
		});
		btnLogin.setBounds(146, 123, 89, 23);
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
		chckbxPass.setBounds(146, 94, 141, 23);
		contentPane.add(chckbxPass);
	}
	
	public boolean cekuser(){
		boolean adauser=false;
		String userid=this.txtuserid.getText();
		String pwd = this.passwordFieldPass.getText();
		
		try
		{
			Connection konek = Koneksi.getKoneksi();
			String query = "SELECT userid,password FROM user WHERE userid=? and password=?";
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1, userid);
			p.setString(2, pwd);
			
			ResultSet rs = p.executeQuery();
			while(rs.next())
			{	
				adauser=true;
			}
			rs.close();
		}
		catch(Exception ex)
		{
		}
		return (adauser);
	}
}
