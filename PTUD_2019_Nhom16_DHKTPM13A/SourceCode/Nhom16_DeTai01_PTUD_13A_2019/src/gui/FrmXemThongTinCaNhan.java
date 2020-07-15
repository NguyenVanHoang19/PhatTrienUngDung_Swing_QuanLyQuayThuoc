package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.ThongTinCaNhanDAO;
import entities.NhanVien;
import entities.TaiKhoan;

import javax.swing.JPasswordField;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmXemThongTinCaNhan extends JFrame implements ActionListener{

	private JPanel contentPane;
	private  JTextField txtMaNhanVien;
	private JTextField txtTen;
	private JTextField txtDiaChi;
	private JTextField txtCMND;
	private JTextField txtKQTK;
	private JTable table_1;
	private JTabbedPane tabbedPane ;
	public static JPanel pnlTabXemThongTinCaNhan ;
	private JTextField txtNgaySinh;
	private JTextField txtTenDangNhap;
	private JPasswordField txtMatKhau;
	private JPasswordField txtMatKhauMoi;
	private JButton btnCapNhat;
	private JComboBox cboGioiTinh;
	private JTextField txtSoDienThoai;
	private JTextField txtHo;
	private JButton btnThoat;
	public FrmXemThongTinCaNhan() {
		setSize(1349,685);
		setLocationRelativeTo(null);
		pnlTabXemThongTinCaNhan = new JPanel();
		getContentPane().add(pnlTabXemThongTinCaNhan);
		

		pnlTabXemThongTinCaNhan.setLayout(null);

		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setLayout(null);
		pnlChucNang.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"C\u00E1c ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChucNang.setBackground(new Color(204, 204, 255));
		pnlChucNang.setBounds(10, 440, 1313, 195);
		pnlTabXemThongTinCaNhan.add(pnlChucNang);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		panel.setBorder(new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 34, 1293, 150);
		pnlChucNang.add(panel);
		panel.setLayout(null);

		/**
		 * Thoat tab quan ly nhan vien
		 */
		btnThoat = new JButton("Thoát");
		
		btnThoat.setBackground(new Color(255, 0, 51));
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnThoat.setBounds(1137, 85, 146, 40);
		btnThoat.setIcon(new ImageIcon("images\\exit.png"));
		panel.add(btnThoat);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(Color.GREEN);
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCapNhat.setBounds(569, 23, 159, 40);
		btnCapNhat.setIcon(new ImageIcon("E:\\JPA_WorkSpace\\LTPTJAVA_DHKTPM13B_BAITAPLON_NHOM17\\Hinh\\iconSave.png"));
		panel.add(btnCapNhat);

		JPanel pnlThongTinNhanVien = new JPanel();
		pnlThongTinNhanVien.setLayout(null);
		pnlThongTinNhanVien.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongTinNhanVien.setBackground(new Color(153, 204, 255));
		pnlThongTinNhanVien.setBounds(10, 82, 1313, 347);
		pnlTabXemThongTinCaNhan.add(pnlThongTinNhanVien);

		JPanel pnlThongTinCoBan = new JPanel();
		pnlThongTinCoBan.setLayout(null);
		pnlThongTinCoBan.setForeground(Color.BLACK);
		pnlThongTinCoBan.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Th\u00F4ng tin c\u01A1 b\u1EA3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTinCoBan.setBackground(new Color(153, 204, 255));
		pnlThongTinCoBan.setBounds(10, 27, 1293, 309);
		pnlThongTinNhanVien.add(pnlThongTinCoBan);

		JLabel lblMaNhanVien = new JLabel("Mã nhân viên:");
		lblMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaNhanVien.setBounds(62, 25, 143, 20);
		pnlThongTinCoBan.add(lblMaNhanVien);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMaNhanVien.setToolTipText("");
		txtMaNhanVien.setColumns(10);
		txtMaNhanVien.setBounds(251, 25, 290, 20);
		pnlThongTinCoBan.add(txtMaNhanVien);

		JLabel lblTen = new JLabel("Tên:");
		lblTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTen.setBounds(910, 24, 50, 23);
		pnlThongTinCoBan.add(lblTen);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtTen.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTen.setToolTipText("Nhập họ");
		txtTen.setColumns(10);
		txtTen.setBounds(981, 25, 283, 20);
		pnlThongTinCoBan.add(txtTen);

		JLabel lblDiaChi = new JLabel("Địa chỉ :");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDiaChi.setBounds(685, 122, 149, 20);
		pnlThongTinCoBan.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtDiaChi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDiaChi.setToolTipText("Nhập tên");
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(811, 119, 453, 20);
		pnlThongTinCoBan.add(txtDiaChi);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGioiTinh.setBounds(685, 75, 150, 17);
		pnlThongTinCoBan.add(lblGioiTinh);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		cboGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ", "Khác"}));
		cboGioiTinh.setToolTipText("Chọn giới tính");
		cboGioiTinh.setBounds(878, 72, 82, 23);
		pnlThongTinCoBan.add(cboGioiTinh);

		JLabel lblCMND = new JLabel("CMND:");
		lblCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCMND.setBounds(685, 175, 149, 23);
		pnlThongTinCoBan.add(lblCMND);

		txtCMND = new JTextField();
		txtCMND.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!((e.getKeyChar() >= 48 && e.getKeyChar() <= 57) || e.getKeyChar() == 8)) {
					e.consume();
				}
			}
		});
		txtCMND.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtCMND.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCMND.setToolTipText("Nhập chứng minh nhân dân");
		txtCMND.setColumns(10);
		txtCMND.setBounds(811, 168, 453, 20);
		pnlThongTinCoBan.add(txtCMND);
		
		JLabel lblNgaySinh = new JLabel("Ngày Sinh :");
		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNgaySinh.setBounds(62, 70, 143, 19);
		pnlThongTinCoBan.add(lblNgaySinh);
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtNgaySinh.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNgaySinh.setToolTipText("");
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(251, 68, 290, 20);
		pnlThongTinCoBan.add(txtNgaySinh);
		txtNgaySinh.setEditable(false);
		JLabel lblTenDangNhap = new JLabel("Tên đăng nhập:");
		lblTenDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenDangNhap.setBounds(62, 119, 143, 23);
		pnlThongTinCoBan.add(lblTenDangNhap);
		
		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtTenDangNhap.setEditable(false);
		txtTenDangNhap.setToolTipText("");
		txtTenDangNhap.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTenDangNhap.setColumns(10);
		txtTenDangNhap.setBounds(251, 118, 290, 20);
		pnlThongTinCoBan.add(txtTenDangNhap);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMatKhau.setBounds(62, 172, 143, 26);
		pnlThongTinCoBan.add(lblMatKhau);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtMatKhau.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMatKhau.setBounds(251, 167, 290, 20);
		pnlThongTinCoBan.add(txtMatKhau);
		
		JLabel lblMatKhauMoi = new JLabel("Mật khẩu mới:");
		lblMatKhauMoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMatKhauMoi.setBounds(62, 222, 143, 19);
		pnlThongTinCoBan.add(lblMatKhauMoi);
		
		txtMatKhauMoi = new JPasswordField();
		txtMatKhauMoi.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtMatKhauMoi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMatKhauMoi.setBounds(251, 221, 290, 20);
		pnlThongTinCoBan.add(txtMatKhauMoi);
		
		JRadioButton radHienMatKhau = new JRadioButton("Hiện mật khẩu");
		radHienMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		radHienMatKhau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(radHienMatKhau.isSelected()) {
					txtMatKhau.setEchoChar((char)0);
					txtMatKhauMoi.setEchoChar((char)0);
				}
				else if(radHienMatKhau.isSelected()==false) {
					txtMatKhau.setEchoChar('*');
					txtMatKhauMoi.setEchoChar('*');
				}
				
			}
		});
		radHienMatKhau.setBounds(251, 259, 162, 23);
		pnlThongTinCoBan.add(radHienMatKhau);

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBackground(new Color(102, 153, 255));
		pnlTitle.setBounds(10, 11, 1313, 60);
		pnlTabXemThongTinCaNhan.add(pnlTitle);
		pnlTitle.setLayout(null);

		JLabel lblTitle = new JLabel("THÔNG TIN CÁ NHÂN");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblTitle.setForeground(Color.RED);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(147, 11, 985, 38);
		pnlTitle.add(lblTitle);
		
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!((e.getKeyChar() >= 48 && e.getKeyChar() <= 57) || e.getKeyChar() == 8)) {
					e.consume();
				}
			}
		});
		txtSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtSoDienThoai.setToolTipText("Nhập chứng minh nhân dân");
		txtSoDienThoai.setText((String) null);
		txtSoDienThoai.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(811, 220, 453, 20);
		pnlThongTinCoBan.add(txtSoDienThoai);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		JLabel lblSDT = new JLabel("Số điện thoại :");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSDT.setBounds(685, 224, 149, 23);
		pnlThongTinCoBan.add(lblSDT);
		
		JLabel lblH = new JLabel("Họ :");
		lblH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblH.setBounds(685, 25, 50, 23);
		pnlThongTinCoBan.add(lblH);
		
		txtHo = new JTextField();
		txtHo.setToolTipText("Nhập họ");
		txtHo.setHorizontalAlignment(SwingConstants.RIGHT);
		txtHo.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtHo.setColumns(10);
		txtHo.setBounds(729, 27, 150, 20);
		pnlThongTinCoBan.add(txtHo);
		
		
		
		btnCapNhat.addActionListener(this);
		btnThoat.addActionListener(this);
		loadThongTinCaNhan();
	}
//	public static void main(String[] args) {
//		FrmXemThongTinCaNhan frmQuanLyNhanVien = new FrmXemThongTinCaNhan();
//		frmQuanLyNhanVien.setVisible(true);
//	}
	public void loadThongTinCaNhan() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		ThongTinCaNhanDAO thongTinCaNhanDAO = new ThongTinCaNhanDAO();
		//FrmDangNhap frmDangNhap = new FrmDangNhap();
			try {
				NhanVien nhanVien = thongTinCaNhanDAO.loadNhanVien(FrmDangNhap.taiKhoan.getTenTaiKhoan());
				System.out.println(nhanVien);
				txtMaNhanVien.setText(nhanVien.getMaNhanVien()+"");
				String ngaySinh = "";
				if(nhanVien.getNgaySinh() != null) {
					ngaySinh += dateFormat.format(nhanVien.getNgaySinh());
				}
				txtNgaySinh.setText(ngaySinh);
				txtTenDangNhap.setText(nhanVien.getTaiKhoan().getTenTaiKhoan());
				txtMatKhau.setText(nhanVien.getTaiKhoan().getMatKhau());
				txtHo.setText(nhanVien.getHo());
				txtTen.setText(nhanVien.getTen());
				cboGioiTinh.setSelectedItem(nhanVien.getGioiTinh());
				String diaChi = nhanVien.getDiaChi().getSoNha() + "," + nhanVien.getDiaChi().getTenDuong() + ","+nhanVien.getDiaChi().getPhuong()+","+nhanVien.getDiaChi().getQuan()+","+nhanVien.getDiaChi().getThanhPho()+","+nhanVien.getDiaChi().getQuocGia();
				
				txtDiaChi.setText(diaChi);
				txtCMND.setText(nhanVien.getCmnd());
				txtSoDienThoai.setText(nhanVien.getSoDienThoai());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
	}
	public boolean kiemTraDuLieu() {
		String soDienThoai = txtSoDienThoai.getText();
		String soChungMinh = txtCMND.getText();
//		String [] soDienThoaiArray = soChungMinh.
		String sodient = soDienThoai.valueOf(0);
		if(!soDienThoai.valueOf(0).equalsIgnoreCase("0")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng số 0!");
			return false;
		}
		else if(soDienThoai.length() < 10) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không được bé hơn 10 số! ");
			return false;
		}
		else if(soDienThoai.length() > 10) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không được lớn hơn 10 số!");
			return false;
		}
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if(obj.equals(btnCapNhat)) {
			NhanVien nhanVien = new NhanVien();
			nhanVien.setMaNhanVien(Integer.parseInt(txtMaNhanVien.getText()));
			java.util.Date ngaySinh;
			Date ngaySinhSQL =null;
			try {
				ngaySinh = new SimpleDateFormat("dd/MM/yyyy").parse(txtNgaySinh.getText());
				ngaySinhSQL = new Date(ngaySinh.getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			nhanVien.setNgaySinh(ngaySinhSQL);
			TaiKhoan taiKhoan = new TaiKhoan();
			taiKhoan.setTenTaiKhoan(txtTenDangNhap.getText());
			taiKhoan.setMatKhau(txtMatKhauMoi.getText());
			
			nhanVien.setTaiKhoan(taiKhoan);
			
			nhanVien.setGioiTinh(cboGioiTinh.getSelectedItem().toString());

			nhanVien.setTen(txtTen.getText());
			nhanVien.setHo(txtHo.getText());
			nhanVien.setCmnd(txtCMND.getText());
			nhanVien.setSoDienThoai(txtSoDienThoai.getText());
			ThongTinCaNhanDAO thongTinCaNhanDAO = new ThongTinCaNhanDAO();
			Object check = JOptionPane.showConfirmDialog(this, "Chắc chắc sửa thông tin?");
			if(check.equals(0)) {
				if(kiemTraDuLieu()){
					if(txtTenDangNhap.getText().equalsIgnoreCase("ADMIN")==false) {
						thongTinCaNhanDAO.updateThongTinCaNhan(nhanVien);
						thongTinCaNhanDAO.updateTaiKhoan(nhanVien);
						JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!");
					}
					else {
						JOptionPane.showMessageDialog(this,"Không được sửa thông tin của ADMIN");
					}
				}
				
			}
		}
		else if(obj.equals(btnThoat)) {
			FrmManHinhChinh.tabbedPane.remove(pnlTabXemThongTinCaNhan);
		}
	}
}
