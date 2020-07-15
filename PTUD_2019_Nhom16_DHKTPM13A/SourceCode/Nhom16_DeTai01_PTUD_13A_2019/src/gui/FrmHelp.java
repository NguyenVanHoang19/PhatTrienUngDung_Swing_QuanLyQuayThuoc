/*
 * Người làm: Nguyễn Văn Hoàng (Xử lý + giao diện ) 
 * 	
 * 			 
 *
 * Chức năng: chức năng giúp đỡ 
 * */
package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class FrmHelp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmHelp frame = new FrmHelp();
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
	public FrmHelp() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 795, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTiLiuGip = new JLabel("Tài liệu giúp đỡ trong file: Nhom16_7_ApplicationDevelopment_UserManual.docx\r\n");
		lblTiLiuGip.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblTiLiuGip.setBounds(10, 23, 759, 62);
		contentPane.add(lblTiLiuGip);
	}
}
