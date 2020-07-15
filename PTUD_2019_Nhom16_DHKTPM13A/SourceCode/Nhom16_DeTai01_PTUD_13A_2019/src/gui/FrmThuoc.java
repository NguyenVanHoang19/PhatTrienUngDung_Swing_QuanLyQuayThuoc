/*
 * Người làm : Nguyễn Đình Quốc
 * Chức năng : Quản lý thuốc 
 * */
package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import dao.NhaCungCapDAO;
import dao.ThuocDAO;
import database.ConectDatabase;
import entities.NhaCungCap;
import entities.Thuoc;


import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.util.Locale;
import javax.swing.JRadioButton;

public class FrmThuoc extends JFrame {

	public static JPanel contentPane;
	
	private JTextField txtThuoc_Ma;
	private JTextField txtThuoc_Ten;
	private JTextField txtThuoc_SDK;
	private JTextField txtThuoc_GiaNhap;
	private JTextField txtThuoc_DonGia;
	private JTextField txtThuoc_SLN;
	private JTextField txtThuoc_QuyCach;
	private JTextField txtThuoc_TieuChuan;
	private JTable tblThuoc;
	private JTextField txtThuoc_DBC;
	private DefaultTableModel tbModel = new DefaultTableModel();
	private JComboBox cmbDonViTinh,cmbPhanLoai,cmbTrangThai,cmbNhomThuoc,cmbTim;
	public static JComboBox cmbNCC;
	private JDateChooser dateNgaySX,dateHanSD;
	private ThuocDAO dao= new ThuocDAO();
	private NhaCungCapDAO dao_NCC= new NhaCungCapDAO();
	private JTextField txtHoatChat;
	private JTextField txtHamLuong;
	private String filename = null;
	private byte[] imageThuoc;
	private JLabel lblAnhThuoc;
	private JRadioButton radPhanLoai,radMa,radNhomThuoc,radNCC,radTenThuoc;
	private DefaultComboBoxModel cboModeNCC= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModePhanLoai= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModeDonViTinh= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModeTrangThai= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModeNhomThuoc= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModeTim= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModeMa= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModeTenThuoc= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModeTimPhanLoai= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModeTimNhomThuoc= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModeTimNCC= new DefaultComboBoxModel();
	
	
	private JButton btnLamMoi;
	private List<String> listPhanLoai= new ArrayList<>();
	private List<String> listDonViTinh= new ArrayList<>();
	private List<String> listNhomThuoc= new ArrayList<>();	
	private List<String> listTrangThai= new ArrayList<>();
	private List<String> listNCC= new ArrayList<>();
	private List<String> listMa= new ArrayList<>();
	private List<String> listTenThuoc= new ArrayList<>();
	private List<String> listTimPhanLoai= new ArrayList<>();
	private List<String> listTimNhomThuoc= new ArrayList<>();
	private List<String> listTimNCC= new ArrayList<>();
	
	///
	public static JButton btnThem,btnThemNCC,btnThemNhomThuoc,btnThemPhanLoai,btnThemTrangThai,btnThemDVT;
	public static JPanel pnlChucNang;
	public static JButton btnXoa;
	public static JButton btnLuu;
	public static JButton btnSua;
	private int trangThaiThem;
	private int trangThaiSua;
	private FrmThemNhaCungCap frmthemNCC=new FrmThemNhaCungCap();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public FrmThuoc() {
		trangThaiThem=0;
		trangThaiSua=0;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1351,686);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnl_Thuoc = new JPanel();
		pnl_Thuoc.setBackground(new Color(176, 224, 230));
		pnl_Thuoc.setBounds(10, -23, 1323, 684);
		contentPane.add(pnl_Thuoc);
		pnl_Thuoc.setLayout(null);

		JPanel pnlNoiDung = new JPanel();
		pnlNoiDung.setBackground(new Color(173, 216, 230));
		pnlNoiDung.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nội dung quản lý", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnlNoiDung.setBounds(20, 70, 604, 240);
		pnl_Thuoc.add(pnlNoiDung);
		pnlNoiDung.setLayout(null);

		JLabel lblThuoc_Ten = new JLabel("Mã :");
		lblThuoc_Ten.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_Ten.setBounds(10, 23, 53, 30);
		pnlNoiDung.add(lblThuoc_Ten);

		txtThuoc_Ma = new JTextField();
		txtThuoc_Ma.setEditable(false);
		txtThuoc_Ma.setBounds(105, 23, 197, 30);
		pnlNoiDung.add(txtThuoc_Ma);
		txtThuoc_Ma.setColumns(10);

		JLabel lblTen = new JLabel("Tên:\r\n");
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTen.setBounds(307, 23, 53, 30);
		pnlNoiDung.add(lblTen);

		txtThuoc_Ten = new JTextField();
		txtThuoc_Ten.setEditable(false);
		txtThuoc_Ten.setColumns(10);
		txtThuoc_Ten.setBounds(390, 23, 210, 30);
		pnlNoiDung.add(txtThuoc_Ten);

		JLabel lblThuoc_SDK = new JLabel("Số đăng ký:\r\n");
		lblThuoc_SDK.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_SDK.setBounds(10, 56, 71, 30);
		pnlNoiDung.add(lblThuoc_SDK);

		txtThuoc_SDK = new JTextField();
		txtThuoc_SDK.setEditable(false);
		txtThuoc_SDK.setColumns(10);
		txtThuoc_SDK.setBounds(105, 56, 197, 30);
		pnlNoiDung.add(txtThuoc_SDK);

		JLabel lblThuoc_NCC = new JLabel("Nhà Cung Cấp:");
		lblThuoc_NCC.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_NCC.setBounds(307, 56, 86, 30);
		pnlNoiDung.add(lblThuoc_NCC);

		JLabel lblThuoc_NgSX = new JLabel("Ngày sản xuất :");
		lblThuoc_NgSX.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_NgSX.setBounds(10, 90, 86, 30);
		pnlNoiDung.add(lblThuoc_NgSX);

		JLabel lblThuoc_HSD = new JLabel("Hạn sự dụng:\r\n");
		lblThuoc_HSD.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_HSD.setBounds(307, 89, 76, 30);
		pnlNoiDung.add(lblThuoc_HSD);

		JLabel lblThuoc_DVT = new JLabel("Đơn vị tính:");
		lblThuoc_DVT.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_DVT.setBounds(10, 125, 86, 30);
		pnlNoiDung.add(lblThuoc_DVT);

		JLabel lblThuoc_GiaNhap = new JLabel("Giá nhập:\r\n");
		lblThuoc_GiaNhap.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_GiaNhap.setBounds(307, 125, 76, 30);
		pnlNoiDung.add(lblThuoc_GiaNhap);

		txtThuoc_GiaNhap = new JTextField();
		txtThuoc_GiaNhap.setEditable(false);
		txtThuoc_GiaNhap.setColumns(10);
		txtThuoc_GiaNhap.setBounds(390, 125, 210, 30);
		pnlNoiDung.add(txtThuoc_GiaNhap);

		JLabel lblThuoc_DonGia = new JLabel("Đơn giá:");
		lblThuoc_DonGia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_DonGia.setBounds(10, 160, 71, 30);
		pnlNoiDung.add(lblThuoc_DonGia);

		txtThuoc_DonGia = new JTextField();
		txtThuoc_DonGia.setEditable(false);
		txtThuoc_DonGia.setColumns(10);
		txtThuoc_DonGia.setBounds(105, 160, 197, 30);
		pnlNoiDung.add(txtThuoc_DonGia);

		JLabel lblThuoc_SLN = new JLabel("Số lượng nhập:\r\n");
		lblThuoc_SLN.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_SLN.setBounds(307, 160, 86, 30);
		pnlNoiDung.add(lblThuoc_SLN);

		txtThuoc_SLN = new JTextField();
		txtThuoc_SLN.setEditable(false);
		txtThuoc_SLN.setColumns(10);
		txtThuoc_SLN.setBounds(390, 160, 210, 30);
		pnlNoiDung.add(txtThuoc_SLN);

		cmbNCC = new JComboBox();
		cmbNCC.setBounds(390, 55, 178, 30);
		pnlNoiDung.add(cmbNCC);

		cmbDonViTinh = new JComboBox();
		cmbDonViTinh.setForeground(new Color(0, 0, 0));
		cmbDonViTinh.setBackground(new Color(255, 255, 255));
		cmbDonViTinh.setBounds(105, 125, 166, 30);
		pnlNoiDung.add(cmbDonViTinh);

		btnThemNCC = new JButton("");
		btnThemNCC.setBounds(570, 56, 30, 30);
		pnlNoiDung.add(btnThemNCC);
		btnThemNCC.setIcon(new ImageIcon("Hinh\\add.png"));

		btnThemDVT = new JButton("");
		btnThemDVT.setForeground(new Color(0, 255, 0));
		btnThemDVT.setIcon(new ImageIcon("Hinh\\add.png"));
		btnThemDVT.setBounds(273, 125, 30, 30);
		pnlNoiDung.add(btnThemDVT);

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTrangThai.setBounds(10, 197, 71, 30);
		pnlNoiDung.add(lblTrangThai);

		cmbTrangThai = new JComboBox();
		cmbTrangThai.setBounds(105, 197, 166, 30);
		pnlNoiDung.add(cmbTrangThai);

		btnThemTrangThai = new JButton("");
		btnThemTrangThai.setIcon(new ImageIcon("Hinh\\add.png"));
		btnThemTrangThai.setBounds(273, 197, 30, 30);
		pnlNoiDung.add(btnThemTrangThai);

		JLabel lblThuoc_PhanLoai = new JLabel("Phân loại:");
		lblThuoc_PhanLoai.setBounds(307, 197, 61, 30);
		pnlNoiDung.add(lblThuoc_PhanLoai);
		lblThuoc_PhanLoai.setFont(new Font("Tahoma", Font.BOLD, 11));

		cmbPhanLoai = new JComboBox();
		cmbPhanLoai.setBounds(390, 196, 176, 30);
		pnlNoiDung.add(cmbPhanLoai);

		btnThemPhanLoai = new JButton("");
		btnThemPhanLoai.setBounds(570, 197, 30, 30);
		pnlNoiDung.add(btnThemPhanLoai);
		btnThemPhanLoai.setIcon(new ImageIcon("Hinh\\add.png"));
		
		dateNgaySX = new JDateChooser();
		dateNgaySX.setLocale(new Locale("vi", "VN"));
		dateNgaySX.setDateFormatString("dd-MM-yyyy");
		dateNgaySX.setBounds(105, 90, 197, 30);
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateNgaySX.getDateEditor();
		editor.setEditable(false);
		pnlNoiDung.add(dateNgaySX);
		
		dateHanSD = new JDateChooser();
		dateHanSD.setLocale(new Locale("vi", "VN"));
		dateHanSD.setDateFormatString("dd-MM-yyyy");
		dateHanSD.setBounds(390, 90, 210, 30);
		JTextFieldDateEditor editor2 = (JTextFieldDateEditor) dateHanSD.getDateEditor();
		editor2.setEditable(false);
		pnlNoiDung.add(dateHanSD);

		JPanel pnlTTThuoc = new JPanel();
		pnlTTThuoc.setBackground(new Color(173, 216, 230));
		pnlTTThuoc.setBorder(new TitledBorder(null, "Thông tin thuốc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTTThuoc.setBounds(634, 70, 679, 240);
		pnl_Thuoc.add(pnlTTThuoc);
		pnlTTThuoc.setLayout(null);

		JLabel lblThuoc_QuyCach = new JLabel("Quy cách đóng gói :");
		lblThuoc_QuyCach.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_QuyCach.setBounds(314, 28, 109, 30);
		pnlTTThuoc.add(lblThuoc_QuyCach);

		txtThuoc_QuyCach = new JTextField();
		txtThuoc_QuyCach.setEditable(false);
		txtThuoc_QuyCach.setColumns(10);
		txtThuoc_QuyCach.setBounds(434, 28, 198, 30);
		pnlTTThuoc.add(txtThuoc_QuyCach);

		JLabel lblThuoc_TieuChuan = new JLabel("Tiêu chuẩn:");
		lblThuoc_TieuChuan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_TieuChuan.setBounds(314, 62, 81, 30);
		pnlTTThuoc.add(lblThuoc_TieuChuan);

		txtThuoc_TieuChuan = new JTextField();
		txtThuoc_TieuChuan.setEditable(false);
		txtThuoc_TieuChuan.setColumns(10);
		txtThuoc_TieuChuan.setBounds(434, 62, 198, 30);
		pnlTTThuoc.add(txtThuoc_TieuChuan);

		JLabel lblThuoc_HamLuong = new JLabel("Nồng Độ- hàm lượng:\r\n");
		lblThuoc_HamLuong.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_HamLuong.setBounds(314, 97, 120, 30);
		pnlTTThuoc.add(lblThuoc_HamLuong);


		JLabel lblThuoc_DBC = new JLabel("Dạng bào chế:");
		lblThuoc_DBC.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_DBC.setBounds(10, 62, 89, 30);
		pnlTTThuoc.add(lblThuoc_DBC);

		txtThuoc_DBC = new JTextField();
		txtThuoc_DBC.setEditable(false);
		txtThuoc_DBC.setColumns(10);
		txtThuoc_DBC.setBounds(98, 62, 208, 30);
		pnlTTThuoc.add(txtThuoc_DBC);

		JLabel lblHoatChat = new JLabel("Hoạt Chất");
		lblHoatChat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHoatChat.setBounds(10, 97, 89, 30);
		pnlTTThuoc.add(lblHoatChat);

		txtHoatChat = new JTextField();
		txtHoatChat.setEditable(false);
		txtHoatChat.setColumns(10);
		txtHoatChat.setBounds(98, 97, 208, 30);
		pnlTTThuoc.add(txtHoatChat);

		txtHamLuong = new JTextField();
		txtHamLuong.setEditable(false);
		txtHamLuong.setBounds(434, 97, 198, 30);
		pnlTTThuoc.add(txtHamLuong);
		txtHamLuong.setColumns(10);

		lblAnhThuoc = new JLabel("");
		lblAnhThuoc.setToolTipText("ảnh thuốc.");
		lblAnhThuoc.setForeground(Color.WHITE);
		lblAnhThuoc.setBackground(Color.WHITE);
		lblAnhThuoc.setBounds(98, 139, 297, 88);
		pnlTTThuoc.add(lblAnhThuoc);

		JLabel lblHinhAnh = new JLabel("Hình Ảnh:\r\n");
		lblHinhAnh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHinhAnh.setBounds(10, 160, 89, 30);
		pnlTTThuoc.add(lblHinhAnh);

		JButton btnHinhAnh = new JButton("Chọn ảnh\r\n");
		btnHinhAnh.setBounds(435, 148, 111, 30);
		pnlTTThuoc.add(btnHinhAnh);

		JLabel lblNhomThuoc = new JLabel("Nhóm thuốc:");
		lblNhomThuoc.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNhomThuoc.setBounds(10, 23, 89, 30);
		pnlTTThuoc.add(lblNhomThuoc);

		btnThemNhomThuoc = new JButton("");
		btnThemNhomThuoc.setBounds(272, 23, 30, 30);
		pnlTTThuoc.add(btnThemNhomThuoc);

		cmbNhomThuoc = new JComboBox();
		cmbNhomThuoc.setBounds(98, 23, 172, 30);
		pnlTTThuoc.add(cmbNhomThuoc);

		JPanel pnlDsthuoc = new JPanel();
		pnlDsthuoc.setBackground(new Color(176, 224, 230));
		pnlDsthuoc.setBounds(10, 313, 1313, 206);
		pnl_Thuoc.add(pnlDsthuoc);
		pnlDsthuoc.setLayout(null);
		cboModeTim.addElement("Tìm kiếm theo:");
		cboModeTim.addElement("Tìm theo mã thuốc.");
		cboModeTim.addElement("Tìm theo tên thuốc.");
		cboModeTim.addElement("Tìm theo nhóm thuốc.");
		cboModeTim.addElement("Tìm theo phân loại.");
		cboModeTim.addElement("Tìm theo nhà cung cấp.");

		JPanel pnl_4_Thuoc = new JPanel();

		pnl_4_Thuoc.setBounds(10, 11, 1293, 191);
		pnlDsthuoc.add(pnl_4_Thuoc);
		pnl_4_Thuoc.setLayout(null);
		/*
		 * Tạo Table
		 */

		JScrollPane scrollPaneThuoc = new JScrollPane();
		scrollPaneThuoc.setToolTipText("qq\r\n");
		scrollPaneThuoc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneThuoc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneThuoc.setBounds(10, 11, 1273, 168);
		pnl_4_Thuoc.add(scrollPaneThuoc);
		String[] colsname= {"STT","MaThuoc","SoDangKi","TenThuoc","PhanLoai","Nhóm thuốc","HoatChat","NongDo/HamLuong","DangBaoChe","QuyCach","TieuChuan","NhaCungCap","NgaySan"
				+ "Xuat","HanSuDung","DonViTinh","GiaNhap","DonGia","SoLuongNhap","Trạng thái"};

		tbModel=new DefaultTableModel(colsname,0);
		tblThuoc = new JTable(tbModel);
		tblThuoc.setDefaultEditor(Object.class, null);
		scrollPaneThuoc.setViewportView(tblThuoc);
		tblThuoc.getColumnModel().getColumn(0).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(1).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(2).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(3).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(4).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(5).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(6).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(7).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(8).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(9).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(10).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(11).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(12).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(13).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(14).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(15).setPreferredWidth(120);

		tblThuoc.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tblThuoc.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					hienTable();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JPanel pnlTitile = new JPanel();
		pnlTitile.setBackground(new Color(176, 224, 230));
		pnlTitile.setBounds(18, 11, 1295, 48);
		pnl_Thuoc.add(pnlTitile);
		pnlTitile.setLayout(null);

		JLabel lblTitiel = new JLabel("Quản Lý Thuốc");
		lblTitiel.setBounds(0, 13, 1285, 28);
		pnlTitile.add(lblTitiel);
		lblTitiel.setForeground(Color.RED);
		lblTitiel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitiel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitiel.setBackground(Color.RED);

		pnlChucNang = new JPanel();
		pnlChucNang.setBackground(new Color(176, 224, 230));
		pnlChucNang.setBorder(new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChucNang.setBounds(10, 521, 1303, 129);
		pnl_Thuoc.add(pnlChucNang);
		pnlChucNang.setLayout(null);

		btnThem = new JButton("Thêm ");
		btnThem.setBackground(Color.LIGHT_GRAY);
		btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		btnThem.setIcon(new ImageIcon("Hinh\\add.png"));
		btnThem.setBounds(10, 93, 102, 30);
		pnlChucNang.add(btnThem);
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 12));

		btnXoa = new JButton("Xóa\r\n");
		btnXoa.setBackground(Color.LIGHT_GRAY);
		btnXoa.setIcon(new ImageIcon("Hinh\\delete.png"));
		btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
		btnXoa.setBounds(122, 92, 106, 30);
		pnlChucNang.add(btnXoa);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 15));

		btnLuu = new JButton("Lưu");
		btnLuu.setBackground(Color.LIGHT_GRAY);
		btnLuu.setHorizontalAlignment(SwingConstants.LEFT);
		btnLuu.setEnabled(false);
		btnLuu.setBounds(354, 92, 106, 30);
		pnlChucNang.add(btnLuu);
		btnLuu.setIcon(new ImageIcon("Hinh\\yes.png"));
		btnLuu.setFont(new Font("Times New Roman", Font.BOLD, 15));

		JButton btnThoat = new JButton("Thoát");
		btnThoat.setBackground(Color.LIGHT_GRAY);
		btnThoat.setIcon(new ImageIcon("Hinh\\exit.png"));
		btnThoat.setHorizontalAlignment(SwingConstants.LEFT);
		btnThoat.setBounds(1175, 92, 118, 30);
		pnlChucNang.add(btnThoat);
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThoat.setFont(new Font("Times New Roman", Font.BOLD, 15));

		btnSua = new JButton("Sửa");
		btnSua.setBackground(Color.LIGHT_GRAY);
		btnSua.setHorizontalAlignment(SwingConstants.LEFT);
		btnSua.setIcon(new ImageIcon("Hinh\\edit.png"));
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSua.setBounds(238, 92, 106, 30);
		pnlChucNang.add(btnSua);

		btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setBackground(Color.LIGHT_GRAY);
		btnLamMoi.setIcon(new ImageIcon("Hinh\\refresh.png"));
		btnLamMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnLamMoi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnLamMoi.setBounds(483, 92, 132, 30);
		pnlChucNang.add(btnLamMoi);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
						"T\u00ECm ki\u1EBFm h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(255, 0, 0)));
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(10, 13, 1283, 72);
		pnlChucNang.add(panel);
		
		JButton btnTim = new JButton("Tìm kiếm");
		btnTim.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnTim.setBounds(976, 16, 127, 25);
		panel.add(btnTim);
		
		JLabel label = new JLabel("Nhập thông tin tìm kiếm:");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label.setBounds(20, 18, 162, 25);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Tìm theo:");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_1.setBounds(30, 43, 63, 25);
		panel.add(label_1);
		
		radNCC = new JRadioButton("Nhà cung cấp");
		radNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radNCC.setBounds(546, 43, 184, 22);
		panel.add(radNCC);
		
		radPhanLoai = new JRadioButton("Phân loại");
		radPhanLoai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radPhanLoai.setBounds(918, 43, 184, 22);
		panel.add(radPhanLoai);
		
		radMa = new JRadioButton("Mã thuốc");
		radMa.setSelected(true);
		radMa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radMa.setBounds(174, 43, 184, 22);
		panel.add(radMa);
		
		radNhomThuoc = new JRadioButton("Nhóm thuốc");
		radNhomThuoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radNhomThuoc.setBounds(732, 43, 184, 22);
		panel.add(radNhomThuoc);
		
		cmbTim = new JComboBox();
		cmbTim.setBounds(174, 18, 781, 22);
		panel.add(cmbTim);
		
		radTenThuoc = new JRadioButton("Tên thuốc");
		radTenThuoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radTenThuoc.setBounds(360, 43, 184, 22);
		
		panel.add(radTenThuoc);
		ButtonGroup group = new ButtonGroup();
		group.add(radMa);
		group.add(radNCC);
		group.add(radNhomThuoc);
		group.add(radPhanLoai);
		group.add(radTenThuoc);
		
		
		btnHinhAnh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser ch = new JFileChooser();
					ch.showOpenDialog(null);
					File f= ch.getSelectedFile();
					filename =f.getAbsolutePath();
					ImageIcon imageIcon= new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lblAnhThuoc.getWidth(), lblAnhThuoc.getHeight(), Image.SCALE_SMOOTH));
					lblAnhThuoc.setIcon(imageIcon);
					try {
						File image=new File(filename);
						FileInputStream fis = new FileInputStream(image);
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						byte[] b = new byte[2014];
						for(int readNum; (readNum = fis.read(b)) != -1;) {
							bos.write(b, 0, readNum);
						}
						imageThuoc= bos.toByteArray();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(trangThaiSua==0) {
					sua();
				}
				else {
					btnSua.setText("Sữa");
					lamMoi();
					trangThaiSua=0;
					btnThem.setEnabled(true);
					btnLuu.setEnabled(false);
				}
			}
		});
		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lamMoi();
			}
		});
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					luu();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if(trangThaiThem==0) {
					them();
				}
				else {
					btnThem.setText("Thêm");
					lamMoi();
					trangThaiThem=0;
					btnSua.setEnabled(true);
					btnLuu.setEnabled(false);
				}
			}
		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xoa();
			}
		});
		btnThemPhanLoai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbPhanLoai.setEditable(true);
				themPhanLoai();
				cmbPhanLoai.setEditable(false);
			}
		});
		btnThemDVT.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbDonViTinh.setEditable(true);
				themDonViTinh();
				cmbDonViTinh.setEditable(false);
			}
		});
		btnThemNhomThuoc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbNhomThuoc.setEditable(true);
				themNhomThuoc();
				cmbNhomThuoc.setEditable(false);
			}
		});
		btnThemTrangThai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTrangThai.setEditable(true);
				themTrangThai();
				cmbTrangThai.setEditable(false);
			}
		});
		btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timKiem();
			}
		});
		radMa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTim.setModel(cboModeMa);
			}
		});
		radNCC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTim.setModel(cboModeTimNCC);
			}
		});
		radNhomThuoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTim.setModel(cboModeTimNhomThuoc);
			}
		});
		radPhanLoai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTim.setModel(cboModeTimPhanLoai);
			}
		});
		radTenThuoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTim.setModel(cboModeTenThuoc);
			}
		});
		btnThemNCC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmthemNCC.setVisible(true);
			}
		});
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FrmManHinhChinh.tabbedPane.remove(contentPane);
			}
		});
		AutoCompleteDecorator.decorate(cmbNCC);
		AutoCompleteDecorator.decorate(cmbDonViTinh);
		AutoCompleteDecorator.decorate(cmbNhomThuoc);
		AutoCompleteDecorator.decorate(cmbPhanLoai);
		AutoCompleteDecorator.decorate(cmbTrangThai);
		AutoCompleteDecorator.decorate(cmbTim);
		docDuLieu();
		btnThemDVT.setEnabled(false);
		btnThemNCC.setEnabled(false);
		btnThemNhomThuoc.setEnabled(false);
		btnThemPhanLoai.setEnabled(false);
		btnThemTrangThai.setEnabled(false);
	}
	/** Dùng để lấy dự liệu từ sql lên bảng
	 * 
	 */
	public void docDuLieu() {
		int d=1;
		List<Thuoc> list = dao.getThuoc();
		for(Thuoc x:list) {
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			tbModel.addRow(new Object[] {
					d++,x.getMaThuoc(),x.getSoDangky(),x.getTenThuoc(),x.getPhanLoai(),x.getNhomThuoc(),x.getHamLuong(),x.getHoatChat(),x.getDangBaoChe(),
					x.getQuyCach(),x.getTieuChuan(),x.getNhaCungcap().getTenNCC(),df.format(x.getNgaySanXuat()) ,df.format(x.getHanSuDung() ),x.getDonViTinh(),x.getGiaNhap(),
					x.getDonGia(),x.getSoLuongNhap(),x.getTrangThai()
			});
			if(listPhanLoai.contains(x.getPhanLoai())==false) {
				cboModePhanLoai.addElement(x.getPhanLoai());
				cmbPhanLoai.setModel(cboModePhanLoai);	
				listPhanLoai.add(x.getPhanLoai());
			}
			if(listDonViTinh.contains(x.getDonViTinh())==false) {
				cboModeDonViTinh.addElement(x.getDonViTinh());
				cmbDonViTinh.setModel(cboModeDonViTinh);	
				listDonViTinh.add(x.getDonViTinh());
			}
			if(listNhomThuoc.contains(x.getNhomThuoc())==false) {
				cboModeNhomThuoc.addElement(x.getNhomThuoc());
				cmbNhomThuoc.setModel(cboModeNhomThuoc);
				listNhomThuoc.add(x.getNhomThuoc());
			}
			if(listTrangThai.contains(x.getTrangThai())==false) {
				cboModeTrangThai.addElement(x.getTrangThai());
				cmbTrangThai.setModel(cboModeTrangThai);	
				listTrangThai.add(x.getTrangThai());
			}
			if(listMa.contains(String.valueOf(x.getMaThuoc()))==false) {
				cboModeMa.addElement(x.getMaThuoc());
				listMa.add(String.valueOf(x.getMaThuoc()));
			}
			if(listTenThuoc.contains(x.getTenThuoc())==false) {
				cboModeTenThuoc.addElement(x.getTenThuoc());
				listTenThuoc.add(x.getTenThuoc());
			}
			if(listTimPhanLoai.contains(x.getPhanLoai())==false) {
				cboModeTimPhanLoai.addElement(x.getPhanLoai());
				listTimPhanLoai.add(x.getPhanLoai());
			}
			if(listTimNhomThuoc.contains(x.getNhomThuoc())==false) {
				cboModeTimNhomThuoc.addElement(x.getNhomThuoc());
				listTimNhomThuoc.add(x.getNhomThuoc());
			}
		}	
		dao.getTenCungCaps().forEach(x->{
			if(listNCC.contains(x)==false) {
				cboModeNCC.addElement(x);
				cmbNCC.setModel(cboModeNCC);	
				listNCC.add(x);
			}
			if(listTimNCC.contains(x)==false) {
				cboModeTimNCC.addElement(x);
				listTimNCC.add(x);
			}
		});
	}
	/**
	 * Dùng để hiện thị các text và combobox từ bảng lên 
	 */
	public void hienTable() {
		int row;
		int rowCount;
		row=tblThuoc.getSelectedRow();
		txtThuoc_Ma.setText(tbModel.getValueAt(row, 1).toString());
		txtThuoc_SDK.setText(tbModel.getValueAt(row, 2).toString());
		txtThuoc_Ten.setText(tbModel.getValueAt(row, 3).toString());
		cmbPhanLoai.setSelectedItem(dao.getThuoc().get(row).getPhanLoai());
		cmbNhomThuoc.setSelectedItem(dao.getThuoc().get(row).getNhomThuoc());
		txtHamLuong.setText(tblThuoc.getValueAt(row, 6).toString());	
		txtHoatChat.setText(tblThuoc.getValueAt(row, 7).toString());
		txtThuoc_DBC.setText(tbModel.getValueAt(row, 8).toString().toString());
		txtThuoc_QuyCach.setText(tbModel.getValueAt(row, 9).toString());
		txtThuoc_TieuChuan.setText(tbModel.getValueAt(row, 10).toString());
		cmbNCC.setSelectedItem(tbModel.getValueAt(row,11).toString());
		dateNgaySX.setDate(dao.getThuoc().get(row).getNgaySanXuat());
		dateHanSD.setDate(dao.getThuoc().get(row).getHanSuDung());
		cmbDonViTinh.setSelectedItem(tbModel.getValueAt(row, 14));
		txtThuoc_GiaNhap.setText(tbModel.getValueAt(row, 15).toString());
		txtThuoc_DonGia.setText(tbModel.getValueAt(row, 16).toString());
		txtThuoc_SLN.setText(tbModel.getValueAt(row, 17).toString());
		cmbTrangThai.setSelectedItem(dao.getThuoc().get(row).getTrangThai());
		try {
			byte[] img=dao.getThuoc().get(row).getHinhAnh();
			ImageIcon ima=new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lblAnhThuoc.getWidth(), lblAnhThuoc.getHeight(), Image.SCALE_SMOOTH));
			lblAnhThuoc.setIcon(ima);
			imageThuoc=img;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * Mở giao diên sửa 
	 */
	public void sua()  {
		btnThemDVT.setEnabled(true);
		btnThemNCC.setEnabled(true);
		btnThemNhomThuoc.setEnabled(true);
		btnThemPhanLoai.setEnabled(true);
		btnThemTrangThai.setEnabled(true);
		txtHamLuong.setEditable(true);
		txtHoatChat.setEditable(true);
		txtThuoc_DBC.setEditable(true);
		txtThuoc_DonGia.setEditable(true);
		txtThuoc_GiaNhap.setEditable(true);
		txtThuoc_QuyCach.setEditable(true);
		txtThuoc_SDK.setEditable(true);
		txtThuoc_SLN.setEditable(true);
		txtThuoc_Ten.setEditable(true);
		txtThuoc_TieuChuan.setEditable(true);
		cmbPhanLoai.setSelectedItem("Không kê đơn");
		btnSua.setText("Hủy");
		trangThaiSua=1;
		btnThem.setEnabled(false);
		btnLuu.setEnabled(true);
	}
	/**
	 * Dùng để lưu các các thuộc tính đã thêm hoặc sửa 
	 * @throws ParseException
	 */
	public void luu() throws ParseException {
		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
		String maThuoc= txtThuoc_Ma.getText().toString();
		String soDangKi= txtThuoc_SDK.getText().toString();
		String tenThuoc= txtThuoc_Ten.getText().toString();
		String phanLoai= (String) cmbPhanLoai.getSelectedItem();
		String nhomThuoc= (String) cmbNhomThuoc.getSelectedItem();
		String hoatChat= txtHoatChat.getText().toString();
		String hamLuong= txtHamLuong.getText().toString();
		String dangBaoChe= txtThuoc_DBC.getText().toString();
		String quyCach= txtThuoc_QuyCach.getText().toString();
		String tieuChuan= txtThuoc_TieuChuan.getText().toString();
		String tenNCC= (String) cmbNCC.getSelectedItem();
		Date ngaySX = dateNgaySX.getDate();
		Date ngaySD= dateHanSD.getDate();
		String donViTinh= (String) cmbDonViTinh.getSelectedItem();
		String gn= txtThuoc_GiaNhap.getText().toString();
		String dg= txtThuoc_DonGia.getText().toString();
		String sl= txtThuoc_SLN.getText().toString();
		String trangThai= (String) cmbTrangThai.getSelectedItem();
		if(trangThaiThem != 0 && trangThaiSua==0) {
			if(kiemTra()==true) {
				dao.them(soDangKi, tenThuoc, phanLoai,nhomThuoc,hoatChat, hamLuong, dangBaoChe, quyCach, tieuChuan, dao.getmaNCC(tenNCC), ngaySX, ngaySD, donViTinh, Float.parseFloat(gn), Float.parseFloat(dg), Integer.parseInt(sl), imageThuoc,trangThai);
				JOptionPane.showMessageDialog(this, "Thêm thành công ");
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				btnLuu.setEnabled(false);
				trangThaiThem=0;
			}
			else {
				return;
			}
			
		}
		else if(trangThaiThem == 0 && trangThaiSua != 0) {
			if(kiemTra()==true) {
				String m = txtThuoc_Ma.getText();
				int ma=0;
				ma= Integer.parseInt(m);
				dao.sua(ma, soDangKi, tenThuoc, phanLoai, nhomThuoc, hoatChat, hamLuong, dangBaoChe, quyCach, tieuChuan, dao.getmaNCC(tenNCC), ngaySX, ngaySD, donViTinh, Float.parseFloat(gn), Float.parseFloat(dg), Integer.parseInt(sl), imageThuoc, trangThai);
				JOptionPane.showMessageDialog(this, "Sữa Thành Công");
				btnThem.setEnabled(true);
				btnSua.setText("Sửa");
				btnLuu.setEnabled(false);
				trangThaiSua=0;
			}
			else {
				return;
			}
		}
		lamMoi();

	}
	/**
	 * Dùng để mở giao diện thêm phân loại thuốc mới
	 */
	public void themPhanLoai() {
		String moi= JOptionPane.showInputDialog(this, "Phân loại mới");
		cmbPhanLoai.setSelectedItem(moi);
	}
	/**
	 * Dùng để mở giao diện thêm đơn vị tính thuốc mới
	 */
	public void themDonViTinh() {
		String moi= JOptionPane.showInputDialog(this, "Đơn vị tính mới");
		cmbDonViTinh.setSelectedItem(moi);
	}
	/**
	 * Dùng để mở giao diện thêm nhóm thuốc thuốc mới
	 */
	public void themNhomThuoc() {
		String moi= JOptionPane.showInputDialog(this, "Nhóm thuốc mới");
		cmbNhomThuoc.setSelectedItem(moi);
	}
	/**
	 * Dùng để mở giao diện thêm trạng thái thuốc mới
	 */
	public void themTrangThai() {
		String moi= JOptionPane.showInputDialog(this, "Trạng thái mới");
		cmbTrangThai.setSelectedItem(moi);
	}
	
	/**
	 * Mở giao diện thêm
	 */
	public void them() {
		lamMoi();
		btnThemDVT.setEnabled(true);
		btnThemNCC.setEnabled(true);
		btnThemNhomThuoc.setEnabled(true);
		btnThemPhanLoai.setEnabled(true);
		btnThemTrangThai.setEnabled(true);
		txtHamLuong.setEditable(true);
		txtHoatChat.setEditable(true);
		txtThuoc_DBC.setEditable(true);
		txtThuoc_DonGia.setEditable(true);
		txtThuoc_GiaNhap.setEditable(true);
		txtThuoc_QuyCach.setEditable(true);
		txtThuoc_SDK.setEditable(true);
		txtThuoc_SLN.setEditable(true);
		txtThuoc_Ten.setEditable(true);
		txtThuoc_TieuChuan.setEditable(true);
		btnThem.setText("Hủy");
		cmbPhanLoai.setSelectedItem("Không kê đơn");
		cmbTrangThai.setSelectedItem("Đang bán");
		trangThaiThem=1;
		btnSua.setEnabled(false);
		btnLuu.setEnabled(true);
	}
	/**
	 * Dùng để xóa thuốc.
	 */
	public void xoa() {
		int i=JOptionPane.showConfirmDialog(this, "Bạn có chắ muốn chuyển sang trạng thái ngừng bán");
		if(i==0) {
			dao.xoa(Integer.parseInt(txtThuoc_Ma.getText()));
			lamMoi();
		}
		else if(i==1) {
			return;
		}

	}
	/**
	 * Dùng để xóa dữ liệu bảng
	 */
	public void xoaTable() {
		tbModel.addRow(new Object[] {

		});
		DefaultTableModel tbl = (DefaultTableModel) tblThuoc.getModel();
		tbl.getDataVector().removeAllElements();	
	}
	/**
	 * Dùng để làm mới giao diện
	 */
	public void lamMoi() {
		txtThuoc_Ma.setText("");
		txtThuoc_SDK.setText("");
		txtThuoc_Ten.setText("");
		cmbPhanLoai.setSelectedItem("");
		txtHoatChat.setText("");
		txtHamLuong.setText("");	
		txtThuoc_DBC.setText("");
		txtThuoc_QuyCach.setText("");
		txtThuoc_TieuChuan.setText("");
		cmbNCC.setSelectedItem("");
		cmbDonViTinh.setSelectedItem("");
		txtThuoc_GiaNhap.setText("");
		txtThuoc_DonGia.setText("");
		txtThuoc_SLN.setText("");
		cmbTrangThai.setSelectedItem("");
		cmbNCC.setSelectedItem("");
		cmbDonViTinh.setSelectedItem("");
		cmbNhomThuoc.setSelectedItem("");
		cmbTrangThai.setSelectedItem("");
		txtHamLuong.setEditable(false);
		txtHoatChat.setEditable(false);
		txtThuoc_DBC.setEditable(false);
		txtThuoc_DonGia.setEditable(false);
		txtThuoc_GiaNhap.setEditable(false);
		txtThuoc_Ma.setEditable(false);
		txtThuoc_QuyCach.setEditable(false);
		txtThuoc_SDK.setEditable(false);
		txtThuoc_SLN.setEditable(false);
		txtThuoc_Ten.setEditable(false);
		txtThuoc_TieuChuan.setEditable(false);
		xoaTable();
		docDuLieu();
		lblAnhThuoc.setIcon(null);
		btnSua.setText("Sửa");
		btnSua.setEnabled(true);
		trangThaiSua=0;
		btnThem.setText("Thêm");
		trangThaiThem=0;
		btnThem.setEnabled(true);
		
		btnThemDVT.setEnabled(false);
		btnThemNCC.setEnabled(false);
		btnThemNhomThuoc.setEnabled(false);
		btnThemPhanLoai.setEnabled(false);
		btnThemTrangThai.setEnabled(false);
	
	}
	/**
	 * Dùng để tìm kiếm thuốc.
	 */
	public void timKiem() {
		lamMoi();
		int d=1;
		String tim = "";
		try {
			tim=cmbTim.getSelectedItem().toString();
		} catch (NullPointerException e) {
			// TODO: handle exception
			
		}
		
		if(tim.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, "Không được rỗng");
			return; 
		}
		
		if(radMa.isSelected()){
			xoaTable();
			Thuoc x = dao.getThuocbyMa(Integer.parseInt(tim));
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			tbModel.addRow(new Object[] {
					d++,x.getMaThuoc(),x.getSoDangky(),x.getTenThuoc(),x.getPhanLoai(),x.getNhomThuoc(),x.getHamLuong(),x.getHoatChat(),x.getDangBaoChe(),
					x.getQuyCach(),x.getTieuChuan(),x.getNhaCungcap().getTenNCC(),df.format(x.getNgaySanXuat()) ,df.format(x.getHanSuDung() ),x.getDonViTinh(),x.getGiaNhap(),
					x.getDonGia(),x.getSoLuongNhap(),x.getTrangThai()
			});
		}
		else if(radNhomThuoc.isSelected()) {
			xoaTable();
			List<Thuoc> list = dao.getThuocbynhomThuoc(tim);
			for(Thuoc x:list) {
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				tbModel.addRow(new Object[] {
						d++,x.getMaThuoc(),x.getSoDangky(),x.getTenThuoc(),x.getPhanLoai(),x.getNhomThuoc(),x.getHamLuong(),x.getHoatChat(),x.getDangBaoChe(),
						x.getQuyCach(),x.getTieuChuan(),x.getNhaCungcap().getTenNCC(),df.format(x.getNgaySanXuat()) ,df.format(x.getHanSuDung() ),x.getDonViTinh(),x.getGiaNhap(),
						x.getDonGia(),x.getSoLuongNhap(),x.getTrangThai()
				});
			}
		}
		else if(radPhanLoai.isSelected()){
			xoaTable();
			List<Thuoc> list = dao.getThuocbyPhanLoai(tim);
			for(Thuoc x:list) {
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				tbModel.addRow(new Object[] {
						d++,x.getMaThuoc(),x.getSoDangky(),x.getTenThuoc(),x.getPhanLoai(),x.getNhomThuoc(),x.getHamLuong(),x.getHoatChat(),x.getDangBaoChe(),
						x.getQuyCach(),x.getTieuChuan(),x.getNhaCungcap().getTenNCC(),df.format(x.getNgaySanXuat()) ,df.format(x.getHanSuDung() ),x.getDonViTinh(),x.getGiaNhap(),
						x.getDonGia(),x.getSoLuongNhap(),x.getTrangThai()
				});
			}
		}
		else if(radNCC.isSelected()){
			xoaTable();
			List<Thuoc> list = dao.getThuocbynhaCungCap(tim);
			for(Thuoc x:list) {
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				tbModel.addRow(new Object[] {
						d++,x.getMaThuoc(),x.getSoDangky(),x.getTenThuoc(),x.getPhanLoai(),x.getNhomThuoc(),x.getHamLuong(),x.getHoatChat(),x.getDangBaoChe(),
						x.getQuyCach(),x.getTieuChuan(),x.getNhaCungcap().getTenNCC(),df.format(x.getNgaySanXuat()) ,df.format(x.getHanSuDung() ),x.getDonViTinh(),x.getGiaNhap(),
						x.getDonGia(),x.getSoLuongNhap(),x.getTrangThai()
				});
			}
		}
		else if(radTenThuoc.isSelected()){
			xoaTable();
			List<Thuoc> list = dao.getThuocbyTen(tim);
			for(Thuoc x:list) {
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				tbModel.addRow(new Object[] {
						d++,x.getMaThuoc(),x.getSoDangky(),x.getTenThuoc(),x.getPhanLoai(),x.getNhomThuoc(),x.getHamLuong(),x.getHoatChat(),x.getDangBaoChe(),
						x.getQuyCach(),x.getTieuChuan(),x.getNhaCungcap().getTenNCC(),df.format(x.getNgaySanXuat()) ,df.format(x.getHanSuDung() ),x.getDonViTinh(),x.getGiaNhap(),
						x.getDonGia(),x.getSoLuongNhap(),x.getTrangThai()
				});
			}
		}
	}
	/**
	 * Dùng kiểm tra dữ liệu.
	 * @return true nếu các dữ liệu đúng.
	 * @return false nếu dữ liệu sai.
	 */
	public boolean kiemTra()  {
		try {
			SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
			String maThuoc= txtThuoc_Ma.getText().toString();
			String soDangKi= txtThuoc_SDK.getText().toString();
			String tenThuoc= txtThuoc_Ten.getText().toString();
			String phanLoai= cmbPhanLoai.getSelectedItem().toString();
			String nhomThuoc= cmbNhomThuoc.getSelectedItem().toString();
			String hoatChat= txtHoatChat.getText().toString();
			String hamLuong= txtHamLuong.getText().toString();
			String dangBaoChe= txtThuoc_DBC.getText().toString();
			String quyCach= txtThuoc_QuyCach.getText().toString();
			String tieuChuan= txtThuoc_TieuChuan.getText().toString();
			String tenNCC= (String) cmbNCC.getSelectedItem();
			Date ngaySX = dateNgaySX.getDate();
			Date ngaySD= dateHanSD.getDate();
			String donViTinh= (String) cmbDonViTinh.getSelectedItem();
			String gn= txtThuoc_GiaNhap.getText().toString();
			String dg= txtThuoc_DonGia.getText().toString();
			String sl= txtThuoc_SLN.getText().toString();
			String trangThai= (String) cmbTrangThai.getSelectedItem();
			if (soDangKi.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số đăng ký !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtThuoc_SDK.requestFocus();
				txtThuoc_SDK.selectAll();
				return false;
			}
			else if (tenThuoc.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thuốc !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtThuoc_Ten.requestFocus();
				txtThuoc_Ten.selectAll();
				return false;
			}
			
			else if (ngaySX==null) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sản xuất !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				dateNgaySX.requestFocus();
				return false;
			}
			else if (ngaySD==null) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sản xuất !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				dateHanSD.requestFocus();
				return false;
			}
			else if (phanLoai.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập phân loại của thuốc !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				return false;
			}
			else if (donViTinh.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn vị tính !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				return false;
			}
			else if (hoatChat.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập hoạt chất của thuốc !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtHoatChat.requestFocus();
				txtHoatChat.selectAll();
				return false;
			}
			else if (hamLuong.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập hàm lượng của thuốc !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtHamLuong.requestFocus();
				txtHamLuong.selectAll();
				return false;
			}
			else if (dangBaoChe.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập dạng bào chế của thuốc!", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtThuoc_DBC.requestFocus();
				txtThuoc_DBC.selectAll();
				return false;
			}
			else if (quyCach.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập quy cách đóng gói của thuốc !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtThuoc_QuyCach.requestFocus();
				txtThuoc_QuyCach.selectAll();
				return false;
			}
			else if (tieuChuan.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập tiêu chuẩn của thuốc !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtThuoc_TieuChuan.requestFocus();
				txtThuoc_TieuChuan.selectAll();
				return false;
			}
			else if (tenNCC.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhà cung cấp của thuốc !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				return false;
			}
			else if (gn.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập giá nhập của thuốc !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtThuoc_GiaNhap.requestFocus();
				txtThuoc_GiaNhap.selectAll();
				return false;
			}
			else if (gn.matches("^[0-9]*") == false) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập giá nhập bằng số !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtThuoc_GiaNhap.requestFocus();
				txtThuoc_GiaNhap.selectAll();
				return false;
			}
			else if (dg.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá của thuốc !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtThuoc_DonGia.requestFocus();
				txtThuoc_DonGia.selectAll();
				return false;
			}
			else if (dg.matches("^[0-9]*") == false) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá bằng số !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtThuoc_DonGia.requestFocus();
				txtThuoc_DonGia.selectAll();
				return false;
			}
			else if (sl.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng nhập !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtThuoc_SLN.requestFocus();
				txtThuoc_SLN.selectAll();
				return false;
			}
			else if (sl.matches("^[0-9]*") == false) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá bằng số !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtThuoc_SLN.requestFocus();
				txtThuoc_SLN.selectAll();
				return false;
			}
			else if (trangThai.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập trạng thái !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				cmbTrangThai.requestFocus();
				return false;
			}
			else if (imageThuoc==null) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh của thuốc !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConectDatabase.getInstance().connect();
					FrmThuoc frame = new FrmThuoc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
