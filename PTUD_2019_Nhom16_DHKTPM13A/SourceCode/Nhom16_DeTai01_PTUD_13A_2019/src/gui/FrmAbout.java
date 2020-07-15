/*
 * Nguoi lam : Nguyen Van Hoang
 * Chuc nang : Thong tin cua phan mem
 * 
 * */
package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class FrmAbout extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAbout frame = new FrmAbout();
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
	public FrmAbout() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 634, 249);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("Hinh\\logo.png"));
		label.setBounds(10, 11, 169, 107);
		panel.add(label);
		
		JLabel lblPhnMmNh = new JLabel("Phần Mềm Nhà Thuốc Tây Nam");
		lblPhnMmNh.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblPhnMmNh.setBounds(189, 11, 435, 58);
		panel.add(lblPhnMmNh);
		
		JLabel lblinThoi = new JLabel("Điện thoại : 0802.090.301  - 0975.326.451 ");
		lblinThoi.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblinThoi.setBounds(189, 73, 435, 46);
		panel.add(lblinThoi);
		
		JLabel lblNewLabel = new JLabel("Địa chí : 56 Quang Trung - Quận Gò Vấp - Tp.Hồ Chí Minh");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblNewLabel.setBounds(10, 129, 624, 46);
		panel.add(lblNewLabel);
		
		JLabel lblVer = new JLabel("Ver 1.0");
		lblVer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblVer.setBounds(10, 216, 229, 22);
		panel.add(lblVer);
	}
}
