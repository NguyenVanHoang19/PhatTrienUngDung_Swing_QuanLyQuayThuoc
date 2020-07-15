package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dao.NhanVienDAO;
import entities.DiaChi;

public class FrmThemDiaChi extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = -5905206843673219821L;
	private JPanel contentPane;
	public static JTextField txtSoNha;
	public static JTextField txtTenDuong;
	public static JTextField txtPhuong;
	public static JTextField txtQuan;
	public static JTextField txtThanhPho;
	public static JTextField txtQuocGia;
	private JButton btnThemDiaChi;
	NhanVienDAO nvDao = new NhanVienDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmThemDiaChi frame = new FrmThemDiaChi();
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
	public FrmThemDiaChi() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 474);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(70, 130, 180));
		panel.setBounds(0, 0, 434, 67);
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("Địa Chỉ");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		panel.add(lblNewLabel);

		JLabel lblSNh = new JLabel("Số Nhà :");
		lblSNh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSNh.setBounds(10, 93, 91, 28);
		contentPane.add(lblSNh);

		JLabel lblTnng = new JLabel("Tên đường :");
		lblTnng.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTnng.setBounds(10, 142, 91, 28);
		contentPane.add(lblTnng);

		JLabel lblPhng = new JLabel("Phường/Xã:");
		lblPhng.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPhng.setBounds(10, 194, 101, 28);
		contentPane.add(lblPhng);

		JLabel lblQun = new JLabel("Quận/Huyện:");
		lblQun.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblQun.setBounds(10, 245, 101, 28);
		contentPane.add(lblQun);

		JLabel lblThnhPh = new JLabel("Thành phố :");
		lblThnhPh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblThnhPh.setBounds(10, 301, 91, 28);
		contentPane.add(lblThnhPh);

		JLabel lblQucGia = new JLabel("Quốc gia :");
		lblQucGia.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblQucGia.setBounds(10, 350, 91, 28);
		contentPane.add(lblQucGia);

		txtSoNha = new JTextField();
		txtSoNha.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoNha.setBounds(121, 96, 265, 22);
		contentPane.add(txtSoNha);
		txtSoNha.setColumns(10);

		txtTenDuong = new JTextField();
		txtTenDuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTenDuong.setColumns(10);
		txtTenDuong.setBounds(121, 148, 265, 22);
		contentPane.add(txtTenDuong);

		txtPhuong = new JTextField();
		txtPhuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtPhuong.setColumns(10);
		txtPhuong.setBounds(121, 200, 265, 22);
		contentPane.add(txtPhuong);

		txtQuan = new JTextField();
		txtQuan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtQuan.setColumns(10);
		txtQuan.setBounds(121, 251, 265, 22);
		contentPane.add(txtQuan);

		txtThanhPho = new JTextField();
		txtThanhPho.setText("Hồ Chí Minh");
		txtThanhPho.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtThanhPho.setColumns(10);
		txtThanhPho.setBounds(121, 307, 265, 22);
		contentPane.add(txtThanhPho);

		txtQuocGia = new JTextField();
		txtQuocGia.setHorizontalAlignment(SwingConstants.LEFT);
		txtQuocGia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtQuocGia.setText("Việt Nam");
		txtQuocGia.setColumns(10);
		txtQuocGia.setBounds(121, 356, 265, 22);
		contentPane.add(txtQuocGia);

		btnThemDiaChi = new JButton("");

		btnThemDiaChi.setBackground(new Color(50, 205, 50));
		btnThemDiaChi.setForeground(new Color(50, 205, 50));
		btnThemDiaChi.setIcon(new ImageIcon("Hinh\\iconSave.png"));
		btnThemDiaChi.setBounds(180, 389, 89, 35);
		contentPane.add(btnThemDiaChi);
		btnThemDiaChi.addActionListener(this);
		contentPane.addKeyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btnThemDiaChi)) {
			String soNha = txtSoNha.getText().trim();
			String duong = txtTenDuong.getText().trim();
			String phuong = txtPhuong.getText().trim();
			String quan = txtQuan.getText().trim();
			String thanhPho = txtThanhPho.getText().trim();
			String quocGia = txtQuocGia.getText().trim();
			String invalid = "";
			if (!soNha.equalsIgnoreCase("")) {
				if (soNha.length() > 15) {
					invalid += "Số nhà không hơp lệ !\n";
				}
			}
			if (!duong.equalsIgnoreCase("")) {
				if (duong.length() > 30) {
					invalid += "Tên đường không hơp lệ !\n";
				}
			}
			if (phuong.equalsIgnoreCase("")) {
				invalid += "Vui lòng nhập phường/xã !\n";
			} else if (phuong.length() > 30) {
				invalid += "Phường/Xã không hợp lệ !\n";
			}
			if (quan.equalsIgnoreCase("")) {
				invalid += "Vui lòng nhập quận/huyện !\n";
			} else if (quan.length() > 30) {
				invalid += "Quận/Huyện không hợp lệ !\n";
			}
			if (thanhPho.equalsIgnoreCase("")) {
				invalid += "Vui lòng nhập Tỉnh/Thành phố !\n";
			} else if (thanhPho.length() > 30) {
				invalid += "Tỉnh/Thành phố không hợp lệ !\n";
			}
			if (quocGia.equalsIgnoreCase("")) {
				invalid += "Vui lòng nhập quốc gia !\n";
			} else if (quocGia.length() > 30) {
				invalid += "Quốc gia không hợp lệ !\n";
			}
			String diaChi = "";
			if (!txtSoNha.getText().trim().equalsIgnoreCase("") && !txtTenDuong.getText().trim().equalsIgnoreCase("")) {
				diaChi = txtSoNha.getText() + ", " + txtTenDuong.getText() + ", " + txtPhuong.getText() + ", "
						+ txtQuan.getText() + ", " + txtThanhPho.getText() + ", " + txtQuocGia.getText();
			} else {
				diaChi = txtPhuong.getText() + ", " + txtQuan.getText() + ", " + txtThanhPho.getText() + ", "
						+ txtQuocGia.getText();
			}
			if (invalid.equalsIgnoreCase("")) {
				try {
					DiaChi dc = new DiaChi(txtSoNha.getText(), txtTenDuong.getText(), txtPhuong.getText(),
							txtQuan.getText(), txtThanhPho.getText(), txtQuocGia.getText());
					if (nvDao.addDiaChi(dc)) {
						FrmQuanLyNhanVien.check = true;
						FrmQuanLyNhanVien.txtMaDiaChi.setText(nvDao.getMaDiaChiMax() + "");
						FrmQuanLyNhanVien.txtDiaChi.setText(diaChi);
						JOptionPane.showMessageDialog(this, "Thêm địa chỉ thành công !", "Thông báo !",
								JOptionPane.CLOSED_OPTION);
						try {
							FrmQuanLyNhanVien.themNhanVien();
							JOptionPane.showMessageDialog(this, "Đã thêm nhân viên !", "Thông báo !",
									JOptionPane.CLOSED_OPTION);
							FrmQuanLyNhanVien.xoaAllDataTable();
							FrmQuanLyNhanVien.docDuLieu();
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại !", "Thông báo !",
									JOptionPane.ERROR_MESSAGE);
						}
						FrmQuanLyNhanVien.btnThem.setEnabled(true);
						this.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(this, "Thêm địa chỉ thất bại !", "Thông báo !",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, invalid, "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
