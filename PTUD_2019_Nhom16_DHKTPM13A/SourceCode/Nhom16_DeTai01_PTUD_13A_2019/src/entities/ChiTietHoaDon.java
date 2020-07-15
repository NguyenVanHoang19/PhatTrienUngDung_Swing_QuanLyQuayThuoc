package entities;

public class ChiTietHoaDon {
	private String maHoaDon;
	private int maThuoc;
	private float donGia;
	private float giamGia;
	private int soLuong;
	private String donViTinh;

	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public int getMaThuoc() {
		return maThuoc;
	}
	public void setMaThuoc(int maThuoc) {
		this.maThuoc = maThuoc;
	}
	public float getDonGia() {
		return donGia;
	}
	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}
	public float getGiamGia() {
		return giamGia;
	}
	public void setGiamGia(float giamGia) {
		this.giamGia = giamGia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public ChiTietHoaDon(String maHoaDon, int maThuoc, float donGia, float giamGia, int soLuong, String donViTinh) {
		super();
		this.maHoaDon = maHoaDon;
		this.maThuoc = maThuoc;
		this.donGia = donGia;
		this.giamGia = giamGia;
		this.soLuong = soLuong;
		this.donViTinh = donViTinh;
	}
	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [maHoaDon=" + maHoaDon + ", maThuoc=" + maThuoc + ", donGia=" + donGia + ", giamGia="
				+ giamGia + ", soLuong=" + soLuong + ", donViTinh=" + donViTinh + "]";
	}
	
	
}
