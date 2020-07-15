package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.LapHoaDonDAO;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.print.attribute.standard.DialogOwner;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FrmChonThuoc extends JFrame implements ActionListener{

	private JPanel contentPane;
	public static JTable table;
	private JButton btnThem;
	public static DefaultTableModel tableModel ;
	private LapHoaDonDAO lapHoaDonDAO = new LapHoaDonDAO();
	public static JLabel lblThuocCoTen;
	private JMenuItem mntmThm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmChonThuoc frame = new FrmChonThuoc();
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
	public FrmChonThuoc() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1312, 498);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1296, 61);
		contentPane.add(panel);
		panel.setLayout(null);

		lblThuocCoTen = new JLabel("THUỐC CÓ TÊN ");
		lblThuocCoTen.setBackground(Color.WHITE);
		lblThuocCoTen.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblThuocCoTen.setHorizontalAlignment(SwingConstants.CENTER);
		lblThuocCoTen.setBounds(10, 11, 1091, 39);
		panel.add(lblThuocCoTen);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 63, 1296, 345);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		scrollPane.setInheritsPopupMenu(true);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 24, 1286, 310);
		panel_1.add(scrollPane);

		String[] tb = new String[] {"Mã", "Nhóm Thuốc", "Tên Thuốc", "Loại Thuốc", "Hàm Lượng", "Dạng Bào Chế", "Đơn Vị Tính","Đơn Giá","Nhà Cung Cấp"};
		tableModel = new DefaultTableModel(tb, 0);

		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table.setRowHeight(30);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.setBackground(Color.WHITE);

		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(220);
		table.getColumnModel().getColumn(2).setPreferredWidth(220);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
		table.getColumnModel().getColumn(7).setPreferredWidth(150);
		table.getColumnModel().getColumn(8).setPreferredWidth(350);

		table.setDefaultEditor(Object.class, null);

		scrollPane.setViewportView(table); 

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);

		mntmThm = new JMenuItem("Thêm");
		mntmThm.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		popupMenu.add(mntmThm);


		btnThem = new JButton("");
		btnThem.setBackground(Color.WHITE);
		btnThem.setIcon(new ImageIcon("Hinh\\add.png"));
		btnThem.setBounds(563, 419, 67, 29);
		contentPane.add(btnThem);

		btnThem.addActionListener(this);
		mntmThm.addActionListener(this);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	public static void xoaDuLieuTableModel() {
		tableModel.addRow(new Object[] {

		});
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.getDataVector().removeAllElements();
	}
	public void themThuoc() {
		int row = table.getSelectedRow();
		int maThuoc = Integer.parseInt(table.getValueAt(row, 0).toString());
		boolean check = true;
		while(check) {
			try {
				String soLuongThuocObj = JOptionPane.showInputDialog("Nhập số lượng thuốc mua.",JOptionPane.YES_NO_CANCEL_OPTION);
				if(soLuongThuocObj == null) {
					check = false;
				}
				else if(soLuongThuocObj != null) {
					if(soLuongThuocObj.matches("^[0-9]+$")) {
						int soLuongThuocInt = Integer.parseInt(soLuongThuocObj.toString());
						if((lapHoaDonDAO.soLuongThuocDaBan(maThuoc) + soLuongThuocInt) <= lapHoaDonDAO.soLuongNhap(maThuoc)) {
							FrmLapHoaDon.txtSoLuong.setText(soLuongThuocObj);
							FrmLapHoaDon.danhSachThuocTheoMaThuoc(maThuoc);
							this.setVisible(false);
							check = false;
						}
						else
							JOptionPane.showMessageDialog(this, "Số Lượng Thuốc Cần Mua Vượt Giới Hạn Thuốc Trong Kho!\n Nhập Số Lượng Ít Hơn "+(lapHoaDonDAO.soLuongNhap(maThuoc)-lapHoaDonDAO.soLuongThuocDaBan(maThuoc)));
					}
					else {
						JOptionPane.showMessageDialog(null, "Số Lượng Phải Nhập Bằng Chữ!");
						check = true;
					}
				}
			} catch (NullPointerException e2) {
				// TODO: handle exception

			}catch (NumberFormatException e3) {
				// TODO: handle exception
				e3.printStackTrace();
				//					JOptionPane.showMessageDialog(null, "Số Lượng Phải Nhập Bằng Chữ!");
				//					check = true;
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj.equals(btnThem)) {
			themThuoc();
		}
		else if(obj.equals(mntmThm)) {
			themThuoc();
		}
	}
}
