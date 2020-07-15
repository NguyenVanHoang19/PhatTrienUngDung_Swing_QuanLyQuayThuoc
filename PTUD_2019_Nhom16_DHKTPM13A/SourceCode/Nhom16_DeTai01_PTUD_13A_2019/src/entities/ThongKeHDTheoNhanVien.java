package entities;

import java.text.DecimalFormat;

public class ThongKeHDTheoNhanVien {
	
	private String ngay;
	private  String maNhanVien;
	private String tenNhanVien;
	private String caLam;
	private String donthuoc;
	private String maThuoc;
	private String tenThuoc;
	private int soLuong;
	private double donGia;
	private double loiNhuan;
	public String getNgay() {
		return ngay;
	}
	public void setNgay(String ngay) {
		this.ngay = ngay;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public String getCaLam() {
		return caLam;
	}
	public void setCaLam(String caLam) {
		this.caLam = caLam;
	}
	public String getDonthuoc() {
		return donthuoc;
	}
	public void setDonthuoc(String donthuoc) {
		this.donthuoc = donthuoc;
	}
	public String getMaThuoc() {
		return maThuoc;
	}
	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public double getLoiNhuan() {
		return loiNhuan;
	}
	public void setLoiNhuan(double loiNhuan) {
		this.loiNhuan = loiNhuan;
	}
	public ThongKeHDTheoNhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ThongKeHDTheoNhanVien(String ngay, String maNhanVien, String tenNhanVien, String caLam, String donthuoc,
			String maThuoc, String tenThuoc, int soLuong, double donGia, double loiNhuan) {
		super();
		this.ngay = ngay;
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.caLam = caLam;
		this.donthuoc = donthuoc;
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.loiNhuan = loiNhuan;
	}
	DecimalFormat df=new DecimalFormat("#,###.0(VND)");
	@Override
	public String toString() {
		return "Class_ThongKeHDTheoNhanVien [ngay=" + ngay + ", maNhanVien=" + maNhanVien + ", tenNhanVien="
				+ tenNhanVien + ", caLam=" + caLam + ", donthuoc=" + donthuoc + ", maThuoc=" + maThuoc + ", tenThuoc="
				+ tenThuoc + ", soLuong=" + soLuong + ", donGia=" +df.format(getDonGia())+ "loiNhuan=" + loiNhuan + "]";
	}
	
	

}
