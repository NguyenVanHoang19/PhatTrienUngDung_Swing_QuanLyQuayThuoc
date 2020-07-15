package entities;

public class ThongKeBaoCaoTQ {

	private String maThuoc;
	private String tenThuoc;
	private double donGia;
	private double donGiaNhap;
	private int soLuongNhap;
	private int soLuongBan;
	private double loiNhuan;
	private String ngay;
	private String soDK;
	private double tienThuocNhap;
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
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}


	
	public double getDonGiaNhap() {
		return donGiaNhap;
	}
	public void setDonGiaNhap(double donGiaNhap) {
		this.donGiaNhap = donGiaNhap;
	}
	
	public int getSoLuongNhap() {
		return soLuongNhap;
	}
	public void setSoLuongNhap(int soLuongNhap) {
		this.soLuongNhap = soLuongNhap;
	}
	public int getSoLuongBan() {
		return soLuongBan;
	}
	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
	}
	public double getLoiNhuan() {
		return loiNhuan;
	}
	public void setLoiNhuan(double loiNhuan) {
		this.loiNhuan = loiNhuan;
	}
	public String getNgay() {
		return ngay;
	}
	public void setNgay(String ngay) {
		this.ngay = ngay;
	}
	public String getSoDK() {
		return soDK;
	}
	public void setSoDK(String soDK) {
		this.soDK = soDK;
	}
	public double getTienThuocNhap() {
		return tienThuocNhap;
	}
	public void setTienThuocNhap(double tienThuocNhap) {
		this.tienThuocNhap= tienThuocNhap;
	}
	public ThongKeBaoCaoTQ() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ThongKeBaoCaoTQ(String maThuoc, String tenThuoc, double donGia,double donGiaNhap, int soLuongNhap,
			int soLuongBan, double loiNhuan, String ngay, String soDK,double tienThuocNhap) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donGia = donGia;
		this.donGiaNhap=donGiaNhap;
		this.soLuongNhap = soLuongNhap;
		this.soLuongBan = soLuongBan;
		this.loiNhuan = loiNhuan;
		this.ngay = ngay;
		this.soDK = soDK;
		this.tienThuocNhap=tienThuocNhap;
	}
	@Override
	public String toString() {
		return "ThongKeBaoCaoTQ [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", donGia=" + donGia + ", donGiaNhap="
				+ donGiaNhap + ", soLuongNhap=" + soLuongNhap + ", soLuongBan=" + soLuongBan + ", loiNhuan=" + loiNhuan
				+ ", ngay=" + ngay + ", soDK=" + soDK + ", tienThuocNhap=" + tienThuocNhap + "]";
	}
	
	

}
