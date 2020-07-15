package entities;

public class ThongKeTinhTrangThuoc {
	private String maThuoc;
	private String tenThuoc;
	private String loaiThuoc;
	private int soLuong;
	private String ngaySanXuat;
	private String hanSuDung;
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
	public String getLoaiThuoc() {
		return loaiThuoc;
	}
	public void setLoaiThuoc(String loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getNgaySanXuat() {
		return ngaySanXuat;
	}
	public void setNgaySanXuat(String ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}
	public String getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(String hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public ThongKeTinhTrangThuoc(String maThuoc, String tenThuoc, String loaiThuoc, int soLuong, String ngaySanXuat,
			String hanSuDung) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.loaiThuoc = loaiThuoc;
		this.soLuong = soLuong;
		this.ngaySanXuat = ngaySanXuat;
		this.hanSuDung = hanSuDung;
	}
	public ThongKeTinhTrangThuoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
