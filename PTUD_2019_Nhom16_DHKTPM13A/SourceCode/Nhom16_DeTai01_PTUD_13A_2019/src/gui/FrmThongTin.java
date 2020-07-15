package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class FrmThongTin extends JFrame {

	public static JPanel contentPane;
	private JTextField txtXemTT_Ma;
	private JTextField txtXemTT_Ten;
	private JTextField txtXemTT_NgaySinh;
	private JTextField txtXemTT_GioiTinh;
	private JTextField txtXemTT_SoCMND;
	private JTextField txtXemTT_SDT;
	private JTextField txtXemTT_DiaChi;
	private JTextField txtXemTT_TenDangNhap;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmThongTin frame = new FrmThongTin();
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
	public FrmThongTin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1030, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlXemTT = new JPanel();
		pnlXemTT.setBackground(new Color(135, 206, 235));
		pnlXemTT.setBounds(10, 10, 996, 593);
		contentPane.add(pnlXemTT);
		pnlXemTT.setLayout(null);
		
		JLabel lblXemTT_anh = new JLabel("New label");
		lblXemTT_anh.setIcon(new ImageIcon("Hinh\\NhanVien.PNG"));
		lblXemTT_anh.setBounds(384, 24, 176, 176);
		pnlXemTT.add(lblXemTT_anh);
		
		JLabel lblXemTT_Ma = new JLabel("Mã nhân viên:");
		lblXemTT_Ma.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblXemTT_Ma.setBounds(75, 220, 125, 43);
		pnlXemTT.add(lblXemTT_Ma);
		
		txtXemTT_Ma = new JTextField();
		txtXemTT_Ma.setEditable(false);
		txtXemTT_Ma.setBounds(210, 220, 625, 47);
		pnlXemTT.add(txtXemTT_Ma);
		txtXemTT_Ma.setColumns(10);
		
		JLabel lblXemTT_Ten = new JLabel("Tên nhân viên:");
		lblXemTT_Ten.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblXemTT_Ten.setBounds(75, 271, 136, 43);
		pnlXemTT.add(lblXemTT_Ten);
		
		txtXemTT_Ten = new JTextField();
		txtXemTT_Ten.setEditable(false);
		txtXemTT_Ten.setColumns(10);
		txtXemTT_Ten.setBounds(210, 277, 625, 43);
		pnlXemTT.add(txtXemTT_Ten);
		
		JLabel lblXemTT_NgaySinh = new JLabel("Ngày Sinh:");
		lblXemTT_NgaySinh.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblXemTT_NgaySinh.setBounds(75, 324, 136, 43);
		pnlXemTT.add(lblXemTT_NgaySinh);
		
		txtXemTT_NgaySinh = new JTextField();
		txtXemTT_NgaySinh.setEditable(false);
		txtXemTT_NgaySinh.setColumns(10);
		txtXemTT_NgaySinh.setBounds(210, 324, 262, 47);
		pnlXemTT.add(txtXemTT_NgaySinh);
		
		JLabel lblXemTT_GioiTinh = new JLabel("Giới tính:");
		lblXemTT_GioiTinh.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblXemTT_GioiTinh.setBounds(473, 328, 136, 43);
		pnlXemTT.add(lblXemTT_GioiTinh);
		
		txtXemTT_GioiTinh = new JTextField();
		txtXemTT_GioiTinh.setEditable(false);
		txtXemTT_GioiTinh.setColumns(10);
		txtXemTT_GioiTinh.setBounds(598, 324, 237, 47);
		pnlXemTT.add(txtXemTT_GioiTinh);
		
		JLabel lblXemTT_SoCMND = new JLabel("Số CMND:");
		lblXemTT_SoCMND.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblXemTT_SoCMND.setBounds(75, 377, 136, 43);
		pnlXemTT.add(lblXemTT_SoCMND);
		
		txtXemTT_SoCMND = new JTextField();
		txtXemTT_SoCMND.setEditable(false);
		txtXemTT_SoCMND.setColumns(10);
		txtXemTT_SoCMND.setBounds(210, 377, 262, 47);
		pnlXemTT.add(txtXemTT_SoCMND);
		
		JLabel lblXemTT_SDT = new JLabel("Số điện thoại :");
		lblXemTT_SDT.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblXemTT_SDT.setBounds(473, 381, 136, 43);
		pnlXemTT.add(lblXemTT_SDT);
		
		txtXemTT_SDT = new JTextField();
		txtXemTT_SDT.setEditable(false);
		txtXemTT_SDT.setColumns(10);
		txtXemTT_SDT.setBounds(598, 377, 237, 47);
		pnlXemTT.add(txtXemTT_SDT);
		
		JLabel lblXemTT_DiaChi = new JLabel("Địa chỉ:");
		lblXemTT_DiaChi.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblXemTT_DiaChi.setBounds(75, 430, 136, 43);
		pnlXemTT.add(lblXemTT_DiaChi);
		
		txtXemTT_DiaChi = new JTextField();
		txtXemTT_DiaChi.setEditable(false);
		txtXemTT_DiaChi.setColumns(10);
		txtXemTT_DiaChi.setBounds(210, 430, 625, 47);
		pnlXemTT.add(txtXemTT_DiaChi);
		
		JLabel lblXemTT_TenDangNhap = new JLabel("Tên đăng nhập:");
		lblXemTT_TenDangNhap.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblXemTT_TenDangNhap.setBounds(75, 481, 136, 43);
		pnlXemTT.add(lblXemTT_TenDangNhap);
		
		txtXemTT_TenDangNhap = new JTextField();
		txtXemTT_TenDangNhap.setEditable(false);
		txtXemTT_TenDangNhap.setColumns(10);
		txtXemTT_TenDangNhap.setBounds(210, 483, 466, 47);
		pnlXemTT.add(txtXemTT_TenDangNhap);
		
		JButton btnXemTT_DoiMatKhau = new JButton("Đổi mật khẩu");
		btnXemTT_DoiMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXemTT_DoiMatKhau.setBounds(686, 487, 147, 43);
		pnlXemTT.add(btnXemTT_DoiMatKhau);
		
		JButton btnXemTT_Thoat = new JButton("Thoát\r\n");
		btnXemTT_Thoat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnXemTT_Thoat.setBounds(453, 540, 107, 43);
		pnlXemTT.add(btnXemTT_Thoat);
	}
}
