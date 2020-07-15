/**
 * Người làm: Nguyễn Đình Quốc
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.Label;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import com.toedter.components.JSpinField;

import dao.HoaDonDAO;
import entities.ChiTietHoaDon;
import entities.HoaDon;

import javax.swing.SpinnerNumberModel;

public class FrmQuanLyHoaDon extends JFrame {

	public static JPanel contentPane;
	private JTextField txtMa;
	private JTextField txtTenKH;
	private JTextField txtNVLap;
	private JTable tblHoaDon;
	private JTextField txtTongTien;
	private JTable tblChiTiet;
	private DefaultTableModel tblModelHoaDon = new DefaultTableModel();
	private DefaultTableModel tblModelChiTiet = new DefaultTableModel();
	private HoaDonDAO dao= new HoaDonDAO();
	private JTextField txtNgayLap;
	private JComboBox cmbTim;
	private JRadioButton radMaHD,radTenKH,radTenNV,radNgayLap;
	private DefaultComboBoxModel cboModetenKH= new DefaultComboBoxModel();
	private List<String> listtenKH = new ArrayList<String>();
	private DefaultComboBoxModel cboModetenNV= new DefaultComboBoxModel();
	private List<String> listtenNV = new ArrayList<String>();
	private DefaultComboBoxModel cboModema= new DefaultComboBoxModel();
	private List<String> listma = new ArrayList<String>();
	private DefaultComboBoxModel cboModeNgayLap= new DefaultComboBoxModel();
	private List<String> listNgayLap = new ArrayList<String>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmQuanLyHoaDon frame = new FrmQuanLyHoaDon();
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
	public FrmQuanLyHoaDon() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("Hinh\\pm.png"));
		setType(Type.POPUP);
		setResizable(false);
		setForeground(new Color(176, 224, 230));
		setBackground(new Color(176, 224, 230));
		setTitle("Phần mềm quản lý nhà thuốc Tây Nam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 662);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlThongTinHD = new JPanel();
		pnlThongTinHD.setBackground(new Color(176, 224, 230));
		pnlThongTinHD.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng tin h\u00F3a \u0111\u01A1n",
						TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnlThongTinHD.setBounds(10, 75, 643, 208);
		contentPane.add(pnlThongTinHD);
		pnlThongTinHD.setLayout(null);

		JLabel lblMHan = new JLabel("Mã hóa đơn:");
		lblMHan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMHan.setBounds(10, 20, 122, 30);
		pnlThongTinHD.add(lblMHan);

		txtMa = new JTextField();
		txtMa.setEnabled(false);
		txtMa.setBounds(155, 22, 484, 30);
		pnlThongTinHD.add(txtMa);
		txtMa.setColumns(10);

		JLabel lblTnKhchHng = new JLabel("Khách Hàng:");
		lblTnKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnKhchHng.setBounds(10, 132, 147, 30);
		pnlThongTinHD.add(lblTnKhchHng);

		txtTenKH = new JTextField();
		txtTenKH.setBounds(155, 132, 484, 30);
		pnlThongTinHD.add(txtTenKH);
		txtTenKH.setColumns(10);

		JLabel lblNhanVien = new JLabel("Nhân viên :");
		lblNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNhanVien.setBounds(10, 98, 122, 30);
		pnlThongTinHD.add(lblNhanVien);

		txtNVLap = new JTextField();
		txtNVLap.setBounds(154, 98, 484, 30);
		pnlThongTinHD.add(txtNVLap);
		txtNVLap.setColumns(10);

		JLabel lblNgayLap = new JLabel("Ngày lập:");
		lblNgayLap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNgayLap.setBounds(10, 58, 100, 30);
		pnlThongTinHD.add(lblNgayLap);
		
		JLabel lblTongtien = new JLabel("Tổng Tiền:");
		lblTongtien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTongtien.setBounds(10, 171, 100, 30);
		pnlThongTinHD.add(lblTongtien);
		
		txtTongTien = new JTextField();
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(155, 165, 484, 30);
		pnlThongTinHD.add(txtTongTien);
		
		txtNgayLap = new JTextField();
		txtNgayLap.setColumns(10);
		txtNgayLap.setBounds(154, 58, 484, 30);
		pnlThongTinHD.add(txtNgayLap);

		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBackground(new Color(176, 224, 230));
		pnlChucNang.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "C\u00E1c ch\u1EE9c n\u0103ng",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pnlChucNang.setBounds(10, 476, 1270, 150);
		contentPane.add(pnlChucNang);
		pnlChucNang.setLayout(null);
		ImageIcon iconAdd = new ImageIcon("Hinh/add.png");
		ImageIcon iconDelete = new ImageIcon("Hinh/delete.png");
		ImageIcon iconUpdate = new ImageIcon("Hinh/update.png");
		ImageIcon iconSearch = new ImageIcon("Hinh/search.png");
		ImageIcon iconSave = new ImageIcon("Hinh/save.png");
		ImageIcon iconExit = new ImageIcon("Hinh/exit.png");
		ImageIcon iconExcel = new ImageIcon("Hinh/excel.png");

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBackground(new Color(176, 224, 230));
		pnlTimKiem.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"T\u00ECm ki\u1EBFm h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 0, 0)));
		pnlTimKiem.setBounds(12, 13, 1265, 96);
		pnlChucNang.add(pnlTimKiem);
		pnlTimKiem.setLayout(null);

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(979, 21, 136, 30);
		pnlTimKiem.add(btnTimKiem);
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnTimKiem.setIcon(iconSearch);

		JLabel lblNhpThngTin = new JLabel("Nhập thông tin tìm kiếm:");
		lblNhpThngTin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNhpThngTin.setBounds(20, 18, 162, 30);
		pnlTimKiem.add(lblNhpThngTin);

		JLabel lblTmTheo = new JLabel("Tìm theo:");
		lblTmTheo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTmTheo.setBounds(20, 61, 63, 30);
		pnlTimKiem.add(lblTmTheo);

		radTenKH = new JRadioButton("Tên Khách Hàng ");
		radTenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radTenKH.setBounds(401, 60, 207, 29);
		pnlTimKiem.add(radTenKH);

		radNgayLap = new JRadioButton("Ngày lập");
		radNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radNgayLap.setBounds(841, 60, 207, 29);
		pnlTimKiem.add(radNgayLap);
		
				radMaHD = new JRadioButton("Mã Hóa Đơn");
				radMaHD.setBounds(174, 59, 207, 30);
				pnlTimKiem.add(radMaHD);
				radMaHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
				radMaHD.setSelected(true);
				
				radTenNV = new JRadioButton("Tên nhân viên");
				radTenNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
				radTenNV.setBounds(617, 60, 207, 29);
				pnlTimKiem.add(radTenNV);

		JButton btnThoat = new JButton("Thoát");
		btnThoat.setBackground(Color.LIGHT_GRAY);
		btnThoat.setHorizontalAlignment(SwingConstants.LEFT);
		ImageIcon iconThoat = new ImageIcon("Hinh/exit.png");
		btnThoat.setIcon(new ImageIcon("E:\\Phat Trien Ung Dung\\File_GopCuoi_Quoc\\Nhom16_DeTai01_PTUD_13A_2019\\Hinh\\iconDelete.png"));
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThoat.setBounds(1124, 112, 136, 30);
		pnlChucNang.add(btnThoat);

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlTitle.setBackground(new Color(176, 224, 230));
		pnlTitle.setBounds(0, 0, 1300, 68);
		contentPane.add(pnlTitle);
		pnlTitle.setLayout(null);

		JLabel lblQunLNhn = new JLabel("XEM HÓA ĐƠN");
		lblQunLNhn.setForeground(Color.RED);
		lblQunLNhn.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLNhn.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblQunLNhn.setBounds(10, 11, 1278, 53);
		pnlTitle.add(lblQunLNhn);

		JPanel pnlHoaDon = new JPanel();
		pnlHoaDon.setLayout(null);
		pnlHoaDon.setForeground(Color.BLACK);
		pnlHoaDon.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh s\u00E1ch h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pnlHoaDon.setBackground(new Color(176, 224, 230));
		pnlHoaDon.setBounds(10, 290, 1270, 187);
		contentPane.add(pnlHoaDon);

		JScrollPane scrHoaDon = new JScrollPane();
		scrHoaDon.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrHoaDon.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrHoaDon.setBounds(12, 22, 1250, 154);
		pnlHoaDon.add(scrHoaDon);
		String[] col= {"STT", "Mã hóa đơn", "Ngày lập","Tổng tiền" ,"Tên khách hàng", "Nhân viên lập"};
		tblModelHoaDon = new DefaultTableModel(col,0);
		tblHoaDon = new JTable(tblModelHoaDon);
		
		scrHoaDon.setViewportView(tblHoaDon);

		JPanel pnlChiTiet = new JPanel();
		pnlChiTiet.setBackground(new Color(176, 224, 230));
		pnlChiTiet.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Th\u00F4ng tin chi ti\u1EBFt h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 0, 0)));
		pnlChiTiet.setBounds(664, 75, 618, 208);
		contentPane.add(pnlChiTiet);
		pnlChiTiet.setLayout(null);
		
		JScrollPane scrChiTiet = new JScrollPane();
		scrChiTiet.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrChiTiet.setBounds(10, 21, 596, 176);
		pnlChiTiet.add(scrChiTiet);
		String[] col2= {
				"STT","Tên thuốc","Đơn vị tính","Đơn giá","Số lượng","Giảm giá","Thành tiền"
		};
		tblModelChiTiet = new DefaultTableModel(col2, 0);
		tblChiTiet = new JTable(tblModelChiTiet);
		scrChiTiet.setViewportView(tblChiTiet);
		ButtonGroup group = new ButtonGroup();
		group.add(radMaHD);
		group.add(radNgayLap);
		group.add(radTenNV);
		group.add(radTenKH);
		
		cmbTim = new JComboBox();
		cmbTim.setBounds(184, 22, 771, 22);
		pnlTimKiem.add(cmbTim);
		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(Color.LIGHT_GRAY);
		btnLamMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnLamMoi.setIcon(new ImageIcon("E:\\Phat Trien Ung Dung\\File_GopCuoi_Quoc\\Nhom16_DeTai01_PTUD_13A_2019\\Hinh\\refresh.png"));
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnLamMoi.setBounds(543, 112, 136, 30);
		pnlChucNang.add(btnLamMoi);
		
		docDuLieuHD();
		tblHoaDon.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				cilckMouse();
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
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnLamMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lamMoi();
				docDuLieuHD();
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TimKiem();
			}
		});
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FrmManHinhChinh.tabbedPane.remove(contentPane);
			}
		});
		radMaHD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTim.setModel(cboModema);
			}
		});
		radTenKH.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTim.setModel(cboModetenKH);
			}
		});
		radTenNV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTim.setModel(cboModetenNV);
			}
		});
		radNgayLap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTim.setModel(cboModeNgayLap);
			}
		});
		docDuLieuCmb();
	}
	public void docDuLieuHD() {
		int d=1;
		List<HoaDon> list = dao.getHoaDons();
		for(HoaDon x : list) {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String nhanVien= dao.getTenNV(x.getNhanVien());
			String khachHang= dao.getTenKH(x.getKhachHang());
			tblModelHoaDon.addRow(new Object[] {
					d++,x.getMa(),df.format(x.getNgayLap()),x.getTongTien(),khachHang,nhanVien
			});
			
		}
	}
	/**
	 * Dùng đọc dữ liệu từ cơ sở dữ liệu lên bảng
	 */
	public void docDuLieuCmb() {
		int d=1;
		List<HoaDon> list = dao.getHoaDons();
		for(HoaDon x : list) {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String nhanVien= dao.getTenNV(x.getNhanVien());
			String khachHang= dao.getTenKH(x.getKhachHang());
			if(listtenKH.contains(khachHang)==false) {
				cboModetenKH.addElement(khachHang);	
				listtenKH.add(khachHang);
			}
			if(listtenNV.contains(nhanVien)==false) {
				cboModetenNV.addElement(nhanVien);	
				listtenNV.add(nhanVien);
			}
			if(listma.contains(x.getMa())==false) {
				cboModema.addElement(x.getMa());	
				listma.add(x.getMa());
			}
			if(listNgayLap.contains(df.format(x.getNgayLap()))==false) {
				cboModeNgayLap.addElement(df.format(x.getNgayLap()));	
				listNgayLap.add(df.format(x.getNgayLap()));
			}
		}
	}
	/**
	 * Xóa dữ liêu bảng chi tiết
	 */
	public void xoaTableChiTiet() {
		tblModelChiTiet.addRow(new Object[] {
				
		});
		DefaultTableModel tblModel1 = (DefaultTableModel) tblChiTiet.getModel();
		tblModel1.getDataVector().removeAllElements();	
	}
	/**
	 * Xóa dữ liệu bảng hóa đơn
	 */
	public void xoaTableHoaDon() {
		tblModelHoaDon.addRow(new Object[] {
				
		});
		DefaultTableModel tblModel2 = (DefaultTableModel) tblHoaDon.getModel();
		tblModel2.getDataVector().removeAllElements();	
	}
	/**
	 * làm mởi lại giao diện
	 */
	public void lamMoi() {
		txtMa.setText("");
		txtNgayLap.setText("");
		txtNVLap.setText("");
		txtTenKH.setText("");
		txtTongTien.setText("");
		xoaTableChiTiet();
		xoaTableHoaDon();
		
	}
	/**
	 * bắt sự kiện chuột trong bảng đưa dữ liệu từ bảng lên các text
	 */
	public void cilckMouse() {
		int d=1;
		int row= tblHoaDon.getSelectedRow();
		txtMa.setText(tblHoaDon.getValueAt(row, 1).toString());
		txtNgayLap.setText(tblHoaDon.getValueAt(row, 2).toString());
		txtNVLap.setText(tblHoaDon.getValueAt(row, 5).toString());
		txtTenKH.setText(tblHoaDon.getValueAt(row, 4).toString());
		txtTongTien.setText(tblHoaDon.getValueAt(row, 3).toString());
		List<ChiTietHoaDon> list = dao.getChiTiets(tblHoaDon.getValueAt(row, 1).toString());
		xoaTableChiTiet();
		for(ChiTietHoaDon ct : list) {
			String thuoc = dao.gettenThuoc(ct.getMaThuoc());
			double tongTien= ct.getDonGia()*ct.getSoLuong()-ct.getGiamGia();
			tblModelChiTiet.addRow(new Object[] {
					d++,thuoc,ct.getDonViTinh(),ct.getDonGia(),ct.getSoLuong(),ct.getGiamGia(),tongTien
			});
		}
	}
	/**
	 * Dùng để tìm kiếm dữ liệu
	 */
	public void TimKiem() {
		if(radMaHD.isSelected()) {
			int d=1;
			String ma= cmbTim.getSelectedItem().toString();
			HoaDon hd= dao.getHoaDonByMa(ma);
			lamMoi();
			String nhanVien= dao.getTenNV(hd.getNhanVien());
			String khachHang= dao.getTenKH(hd.getKhachHang());
			tblModelHoaDon.addRow(new Object[] {
					d,hd.getMa(),hd.getNgayLap(),hd.getTongTien(),khachHang,nhanVien
			});
		}
		if(radTenKH.isSelected()) {
			int d=1;
			String ten= cmbTim.getSelectedItem().toString();
			List<HoaDon> hds= dao.getHoaDonsByKhachHang(ten);
			lamMoi();
			for(HoaDon hd: hds) {
				String nhanVien= dao.getTenNV(hd.getNhanVien());
				String khachHang= dao.getTenKH(hd.getKhachHang());
				tblModelHoaDon.addRow(new Object[] {
						d++,hd.getMa(),hd.getNgayLap(),hd.getTongTien(),khachHang,nhanVien
				});
			}
			
		}
		if(radTenNV.isSelected()) {
			int d=1;
			String ten= cmbTim.getSelectedItem().toString();
			System.out.println(ten);
			List<HoaDon> hds= dao.getHoaDonsByNhanVien(ten);
			lamMoi();
			for(HoaDon hd: hds) {
				String nhanVien= dao.getTenNV(hd.getNhanVien());
				String khachHang= dao.getTenKH(hd.getKhachHang());
				tblModelHoaDon.addRow(new Object[] {
						d++,hd.getMa(),hd.getNgayLap(),hd.getTongTien(),khachHang,nhanVien
				});
			}
		}
		if(radNgayLap.isSelected()) {
			int d=1;
			String nl= cmbTim.getSelectedItem().toString();
			String[] ngaylap = nl.split("-");
			int ngay = Integer.parseInt(ngaylap[0]);
			int thang = Integer.parseInt(ngaylap[1]);
			int nam = Integer.parseInt(ngaylap[2]);
			
			List<HoaDon> hds= dao.getHoaDonsByNgayLap(ngay, thang, nam);
			lamMoi();
			for(HoaDon hd: hds) {
				String nhanVien= dao.getTenNV(hd.getNhanVien());
				String khachHang= dao.getTenKH(hd.getKhachHang());
				tblModelHoaDon.addRow(new Object[] {
						d++,hd.getMa(),hd.getNgayLap(),hd.getTongTien(),khachHang,nhanVien
				});
			}
		}
		
	}
}
