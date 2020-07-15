package entities;

import java.util.Date;

public class HoaDon {
	private String ma;
	private Date ngayLap;
	private double tongTien;
	private int nhanVien;
	private int khachHang;
	public HoaDon(String ma, Date ngayLap, double tongTien, int nhanVien, int khachHang) {
		super();
		this.ma = ma;
		this.ngayLap = ngayLap;
		this.tongTien = tongTien;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
	}
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public int getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(int nhanVien) {
		this.nhanVien = nhanVien;
	}
	public int getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(int khachHang) {
		this.khachHang = khachHang;
	}
	@Override
	public String toString() {
		return "HoaDon [ma=" + ma + ", ngayLap=" + ngayLap + ", tongTien=" + tongTien + ", nhanVien=" + nhanVien
				+ ", khachHang=" + khachHang + "]";
	}
	
	
}
