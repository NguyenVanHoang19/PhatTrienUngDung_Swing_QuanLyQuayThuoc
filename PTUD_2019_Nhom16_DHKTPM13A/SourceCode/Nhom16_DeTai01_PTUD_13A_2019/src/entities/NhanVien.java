package entities;

import java.sql.Date;
import java.util.Vector;

public class NhanVien {
	private int maNhanVien;
	private String caLamViec;
	private String ten;
	private String ho;
	private Date ngaySinh;
	private String gioiTinh;
	private String cmnd;
	private String soDienThoai;
	private DiaChi diaChi;
	private TaiKhoan taiKhoan;
	private String loaiNhanVien;
	private String trangThai;
	public int getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getCaLamViec() {
		return caLamViec;
	}
	public void setCaLamViec(String caLamViec) {
		this.caLamViec = caLamViec;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getLoaiNhanVien() {
		return loaiNhanVien;
	}
	public void setLoaiNhanVien(String loaiNhanVien) {
		this.loaiNhanVien = loaiNhanVien;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	public DiaChi getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(DiaChi diaChi) {
		this.diaChi = diaChi;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	
	public NhanVien(int maNhanVien, String caLamViec, String ten, String ho, Date ngaySinh, String gioiTinh,
			String cmnd, String soDienThoai, DiaChi diaChi, TaiKhoan taiKhoan, String loaiNhanVien, String trangThai) {
		super();
		this.maNhanVien = maNhanVien;
		this.caLamViec = caLamViec;
		this.ten = ten;
		this.ho = ho;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.cmnd = cmnd;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.taiKhoan = taiKhoan;
		this.loaiNhanVien = loaiNhanVien;
		this.trangThai = trangThai;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", caLamViec=" + caLamViec + ", ten=" + ten + ", ho=" + ho
				+ ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", cmnd=" + cmnd + ", soDienThoai="
				+ soDienThoai + ", diaChi=" + diaChi + ", taiKhoan=" + taiKhoan + ", loaiNhanVien=" + loaiNhanVien
				+ ", trangThai=" + trangThai + "]";
	}
	
	public Vector<Object> toVector() {
		Vector<Object> v = new Vector<>();
		v.add("");
		v.add(maNhanVien);
		v.add(ho);
		v.add(ten);
		v.add(ngaySinh);
		v.add(gioiTinh);
		v.add(cmnd);
		v.add(soDienThoai);
		v.add(caLamViec);
		v.add(diaChi.getMaDiaChi());
		v.add(taiKhoan.getTenTaiKhoan());
		v.add(loaiNhanVien);
		v.add(trangThai);
		return v;
	}
}
	
