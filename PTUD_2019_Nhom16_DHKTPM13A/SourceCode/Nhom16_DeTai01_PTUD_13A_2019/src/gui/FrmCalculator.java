/*
 * Người làm : Nguyễn Văn Hoàng
 * Chức năng : Màn hình máy tính tiền
 * 
 * */
package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class FrmCalculator extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtKetQua;
	private JButton btnDauNgoac;
	private JButton btnDauNgoacPhai;
	private JButton btnPhanTram ;
	private JButton btnAc;
	private JButton btn7;
	JButton btn8;
	JButton btn9;
	JButton btnChia;
	JButton btn4;
	JButton btn5 ;
	JButton btn6;
	JButton btnDauNhan;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JButton btnTru;
	JButton btn0 ;
	JButton btnDauCham;
	JButton btnBang;
	JButton btnCong;
	private String ketQua ="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCalculator frame = new FrmCalculator();
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
	public FrmCalculator() {
		setTitle("Máy tính");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 415, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 389, 73);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtKetQua = new JTextField();
		txtKetQua.setEditable(false);
		txtKetQua.setHorizontalAlignment(SwingConstants.RIGHT);
		txtKetQua.setText("0");
		txtKetQua.setFont(new Font("Times New Roman", Font.PLAIN, 48));
		txtKetQua.setBackground(Color.LIGHT_GRAY);
		txtKetQua.setBounds(0, 0, 389, 73);
		panel.add(txtKetQua);
		txtKetQua.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 84, 389, 230);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnDauNgoac = new JButton("(");
		btnDauNgoac.setBackground(Color.LIGHT_GRAY);
		btnDauNgoac.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDauNgoac.setBounds(10, 11, 86, 33);
		panel_1.add(btnDauNgoac);
		
		btnDauNgoacPhai = new JButton(")");
		btnDauNgoacPhai.setBackground(Color.LIGHT_GRAY);
		btnDauNgoacPhai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDauNgoacPhai.setBounds(115, 11, 87, 33);
		panel_1.add(btnDauNgoacPhai);
		
		btnPhanTram = new JButton("%");
		btnPhanTram.setBackground(Color.LIGHT_GRAY);
		btnPhanTram.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnPhanTram.setBounds(219, 11, 89, 33);
		panel_1.add(btnPhanTram);
		
		btnAc = new JButton("AC");
		btnAc.setBackground(Color.LIGHT_GRAY);
		btnAc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAc.setBounds(324, 11, 65, 33);
		panel_1.add(btnAc);
		
		btn7 = new JButton("7");
		btn7.setBackground(Color.LIGHT_GRAY);
		btn7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn7.setBounds(10, 55, 86, 33);
		panel_1.add(btn7);
		
		btn8 = new JButton("8");
		btn8.setBackground(Color.LIGHT_GRAY);
		btn8.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn8.setBounds(115, 55, 87, 33);
		panel_1.add(btn8);
		
		btn9 = new JButton("9");
		btn9.setBackground(Color.LIGHT_GRAY);
		btn9.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn9.setBounds(219, 55, 89, 33);
		panel_1.add(btn9);
		
		btnChia = new JButton("\\");
		btnChia.setBackground(Color.LIGHT_GRAY);
		btnChia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnChia.setBounds(324, 55, 65, 33);
		panel_1.add(btnChia);
		
		btn4 = new JButton("4");
		btn4.setBackground(Color.LIGHT_GRAY);
		btn4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn4.setBounds(10, 99, 86, 33);
		panel_1.add(btn4);
		
		btn5 = new JButton("5");
		btn5.setBackground(Color.LIGHT_GRAY);
		btn5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn5.setBounds(115, 99, 87, 33);
		panel_1.add(btn5);
		
		btn6 = new JButton("6");
		btn6.setBackground(Color.LIGHT_GRAY);
		btn6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn6.setBounds(219, 99, 89, 33);
		panel_1.add(btn6);
		
		btnDauNhan = new JButton("x");
		btnDauNhan.setBackground(Color.LIGHT_GRAY);
		btnDauNhan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDauNhan.setBounds(324, 99, 65, 33);
		panel_1.add(btnDauNhan);
		
		btn1 = new JButton("1");
		btn1.setBackground(Color.LIGHT_GRAY);
		btn1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn1.setBounds(10, 143, 86, 33);
		panel_1.add(btn1);
		
		btn2 = new JButton("2");
		btn2.setBackground(Color.LIGHT_GRAY);
		btn2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn2.setBounds(115, 143, 87, 33);
		panel_1.add(btn2);
		
		btn3 = new JButton("3");
		btn3.setBackground(Color.LIGHT_GRAY);
		btn3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn3.setBounds(219, 143, 89, 33);
		panel_1.add(btn3);
		
		btnTru = new JButton("-");
		btnTru.setBackground(Color.LIGHT_GRAY);
		btnTru.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnTru.setBounds(324, 143, 65, 33);
		panel_1.add(btnTru);
		
		btn0 = new JButton("0");
		btn0.setBackground(Color.LIGHT_GRAY);
		btn0.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn0.setBounds(10, 187, 86, 33);
		panel_1.add(btn0);
		
		btnDauCham = new JButton(".");
		btnDauCham.setBackground(Color.LIGHT_GRAY);
		btnDauCham.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDauCham.setBounds(115, 187, 87, 33);
		panel_1.add(btnDauCham);
		
		btnBang = new JButton("=");
		btnBang.setForeground(Color.WHITE);
		btnBang.setBackground(Color.BLUE);
		btnBang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnBang.setBounds(219, 187, 89, 33);
		panel_1.add(btnBang);
		
		btnCong = new JButton("+");
		btnCong.setBackground(Color.LIGHT_GRAY);
		btnCong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCong.setBounds(324, 187, 65, 33);
		panel_1.add(btnCong);
		btn0.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		btn6.addActionListener(this);
		btn7.addActionListener(this);
		btn8.addActionListener(this);
		btn9.addActionListener(this);
		btnAc.addActionListener(this);
		btnBang.addActionListener(this);
		btnChia.addActionListener(this);
		btnCong.addActionListener(this);
		btnDauNhan.addActionListener(this);
		btnDauCham.addActionListener(this);
		btnTru.addActionListener(this);
		btnDauNgoac.addActionListener(this);
		btnDauNgoacPhai.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj.equals(btn0)) {
			ketQua += "0";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btn1)) {
			ketQua += "1";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btn2)) {
			ketQua += "2";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btn3)) {
			ketQua += "3";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btn4)) {
			ketQua += "4";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btn5)) {
			ketQua += "5";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btn6)) {
			ketQua += "6";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btn7)) {
			ketQua += "7";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btn8)) {
			ketQua += "8";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btn9)) {
			ketQua += "9";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btnDauNgoac)) {
			ketQua += "(";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btnDauNgoacPhai)) {
			ketQua += ")";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btnDauCham)) {
			ketQua += ".";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btnDauNhan)) {
			ketQua += "*";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btnChia)) {
			ketQua += "\\";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btnCong)) {
			ketQua += "+";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btnTru)) {
			ketQua += "-";
			txtKetQua.setText(ketQua);
		}
		else if(obj.equals(btnAc)) {
			ketQua = "";
			txtKetQua.setText(ketQua);
		}
	}
}
