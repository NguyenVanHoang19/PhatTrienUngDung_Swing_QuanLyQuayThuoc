package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dialog.ModalExclusionType;

public class FrmCT_HoaDon extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaHD;
	private JTextField txtDonGia;
	private JTextField txtGiamGia;
	private JTextField txtMaThuoc;
	private JTextField txtDonViTinh;
	private JTextField textField;
	private JTable table;
	private JScrollPane scrollPane;
	DefaultTableModel tablemodel2 = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCT_HoaDon frame = new FrmCT_HoaDon();
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
	public FrmCT_HoaDon() {
		setTitle("Thông tin chi tiết hóa đơn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(175, 238, 238));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 814, 550);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 794, 71);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(new Color(255, 250, 205));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Th\u00F4ng Tin Chi Ti\u1EBFt H\u00F3a \u0110\u01A1n");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(268, 11, 356, 49);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 93, 794, 178);
		panel_2.setBackground(new Color(175, 238, 238));
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(175, 238, 238));
		panel_3.setBorder(new TitledBorder(null, "Th\u00F4ng tin chi ti\u1EBFt h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 11, 761, 156);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblMaHD = new JLabel("M\u00E3 H\u00F3a  \u0110\u01A1n");
		lblMaHD.setBounds(31, 37, 69, 14);
		panel_3.add(lblMaHD);
		
		txtMaHD = new JTextField();
		txtMaHD.setBounds(120, 34, 141, 31);
		panel_3.add(txtMaHD);
		txtMaHD.setColumns(10);
		
		JLabel lblMThuc = new JLabel("M\u00E3 Thu\u1ED1c");
		lblMThuc.setBounds(271, 37, 88, 14);
		panel_3.add(lblMThuc);
		
		JLabel lblSLng = new JLabel("S\u1ED1 L\u01B0\u1EE3ng");
		lblSLng.setBounds(497, 37, 88, 14);
		panel_3.add(lblSLng);
		
		JLabel lblnGi = new JLabel("\u0110\u01A1n Gi\u00E1");
		lblnGi.setBounds(31, 104, 69, 14);
		panel_3.add(lblnGi);
		
		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(120, 96, 141, 31);
		panel_3.add(txtDonGia);
		
		JLabel lblGimGi = new JLabel("Gi\u1EA3m Gi\u00E1");
		lblGimGi.setBounds(271, 104, 88, 14);
		panel_3.add(lblGimGi);
		
		JLabel lblnVTnh = new JLabel("\u0110\u01A1n V\u1ECB T\u00EDnh");
		lblnVTnh.setBounds(484, 104, 88, 14);
		panel_3.add(lblnVTnh);
		
		txtGiamGia = new JTextField();
		txtGiamGia.setColumns(10);
		txtGiamGia.setBounds(327, 96, 141, 31);
		panel_3.add(txtGiamGia);
		
		txtMaThuoc = new JTextField();
		txtMaThuoc.setColumns(10);
		txtMaThuoc.setBounds(327, 34, 141, 31);
		panel_3.add(txtMaThuoc);
		
		txtDonViTinh = new JTextField();
		txtDonViTinh.setColumns(10);
		txtDonViTinh.setBounds(568, 96, 141, 31);
		panel_3.add(txtDonViTinh);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(568, 34, 141, 31);
		panel_3.add(textField);
		
	    scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 282, 794, 257);
		panel.add(scrollPane);
		
		table = new JTable();
		String[] tq = new String[] {"STT","Mã Hóa Đơn","Mã Thuốc","Đơn Giá","Giảm Giá","Số Lượng","Đơn Vị Tính"};
		tablemodel2 = new DefaultTableModel(tq, 0);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "M\u00E3 H\u00F3a  \u0110\u01A1n", "M\u00E3 Thu\u1ED1c", "\u0110\u01A1n Gi\u00E1", "Gi\u1EA3m Gi\u00E1", "S\u1ED1 L\u01B0\u1EE3ng", "\u0110\u01A1n V\u1ECB T\u00EDnh"
			}
		));
		scrollPane.setViewportView(table);
	}
}
