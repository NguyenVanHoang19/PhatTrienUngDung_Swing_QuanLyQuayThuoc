package entities;


import java.util.Arrays;
import java.util.Date;


public class Thuoc {
	private int maThuoc;
	private String soDangky;
	private String tenThuoc;
	private String nhomThuoc;
	private String phanLoai;
	private String hoatChat;
	private String hamLuong;
	private String dangBaoChe;
	private String QuyCach;
	private String tieuChuan;
	private NhaCungCap nhaCungcap;
	private Date ngaySanXuat;
	private Date hanSuDung;
	private String donViTinh;
	private float giaNhap;
	private float donGia;
	private int soLuongNhap;
	private byte[] hinhAnh;
	private String trangThai;
	public Thuoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Thuoc(int maThuoc, String soDangky, String tenThuoc,String nhomThuoc, String phanLoai, String hoatChat, String hamLuong,
			String dangBaoChe, String quyCach, String tieuChuan, NhaCungCap nhaCungcap, Date ngaySanXuat,
			Date hanSuDung, String donViTinh, float giaNhap, float donGia, int soLuongNhap, byte[] hinhAnh,
			String trangThai) {
		super();
		this.maThuoc = maThuoc;
		this.soDangky = soDangky;
		this.tenThuoc = tenThuoc;
		this.nhomThuoc = nhomThuoc;
		this.phanLoai = phanLoai;
		this.hoatChat = hoatChat;
		this.hamLuong = hamLuong;
		this.dangBaoChe = dangBaoChe;
		QuyCach = quyCach;
		this.tieuChuan = tieuChuan;
		this.nhaCungcap = nhaCungcap;
		this.ngaySanXuat = ngaySanXuat;
		this.hanSuDung = hanSuDung;
		this.donViTinh = donViTinh;
		this.giaNhap = giaNhap;
		this.donGia = donGia;
		this.soLuongNhap = soLuongNhap;
		this.hinhAnh = hinhAnh;
		this.trangThai = trangThai;
	}
	
	public Thuoc(String soDangky, String tenThuoc, String nhomThuoc,String phanLoai, String hoatChat, String hamLuong, String dangBaoChe,
			String quyCach, String tieuChuan, NhaCungCap nhaCungcap, Date ngaySanXuat, Date hanSuDung, String donViTinh,
			float giaNhap, float donGia, int soLuongNhap, byte[] hinhAnh, String trangThai) {
		super();
		this.soDangky = soDangky;
		this.tenThuoc = tenThuoc;
		this.nhomThuoc = nhomThuoc;
		this.phanLoai = phanLoai;
		this.hoatChat = hoatChat;
		this.hamLuong = hamLuong;
		this.dangBaoChe = dangBaoChe;
		QuyCach = quyCach;
		this.tieuChuan = tieuChuan;
		this.nhaCungcap = nhaCungcap;
		this.ngaySanXuat = ngaySanXuat;
		this.hanSuDung = hanSuDung;
		this.donViTinh = donViTinh;
		this.giaNhap = giaNhap;
		this.donGia = donGia;
		this.soLuongNhap = soLuongNhap;
		this.hinhAnh = hinhAnh;
		this.trangThai = trangThai;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public int getMaThuoc() {
		return maThuoc;
	}
	public void setMaThuoc(int maThuoc) {
		this.maThuoc = maThuoc;
	}
	public String getSoDangky() {
		return soDangky;
	}
	public void setSoDangky(String soDangky) {
		this.soDangky = soDangky;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	
	public String getNhomThuoc() {
		return nhomThuoc;
	}

	public void setNhomThuoc(String nhomThuoc) {
		this.nhomThuoc = nhomThuoc;
	}

	public String getPhanLoai() {
		return phanLoai;
	}
	public void setPhanLoai(String phanLoai) {
		this.phanLoai = phanLoai;
	}
	public String getHoatChat() {
		return hoatChat;
	}
	public void setHoatChat(String hoatChat) {
		this.hoatChat = hoatChat;
	}
	public String getHamLuong() {
		return hamLuong;
	}
	public void setHamLuong(String hamLuong) {
		this.hamLuong = hamLuong;
	}
	public String getDangBaoChe() {
		return dangBaoChe;
	}
	public void setDangBaoChe(String dangBaoChe) {
		this.dangBaoChe = dangBaoChe;
	}
	public String getQuyCach() {
		return QuyCach;
	}
	public void setQuyCach(String quyCach) {
		QuyCach = quyCach;
	}
	public String getTieuChuan() {
		return tieuChuan;
	}
	public void setTieuChuan(String tieuChuan) {
		this.tieuChuan = tieuChuan;
	}
	public NhaCungCap getNhaCungcap() {
		return nhaCungcap;
	}
	public void setNhaCungcap(NhaCungCap nhaCungcap) {
		this.nhaCungcap = nhaCungcap;
	}
	public Date getNgaySanXuat() {
		return ngaySanXuat;
	}
	public void setNgaySanXuat(Date ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}
	public Date getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(Date hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public float getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(float giaNhap) {
		this.giaNhap = giaNhap;
	}
	public float getDonGia() {
		return donGia;
	}
	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}
	public int getSoLuongNhap() {
		return soLuongNhap;
	}
	public void setSoLuongNhap(int soLuongNhap) {
		this.soLuongNhap = soLuongNhap;
	}
	public byte[] getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	
	@Override
	public String toString() {
		return "Thuoc [maThuoc=" + maThuoc + ", soDangky=" + soDangky + ", tenThuoc=" + tenThuoc + ", nhomThuoc="
				+ nhomThuoc + ", phanLoai=" + phanLoai + ", hoatChat=" + hoatChat + ", hamLuong=" + hamLuong
				+ ", dangBaoChe=" + dangBaoChe + ", QuyCach=" + QuyCach + ", tieuChuan=" + tieuChuan + ", nhaCungcap="
				+ nhaCungcap + ", ngaySanXuat=" + ngaySanXuat + ", hanSuDung=" + hanSuDung + ", donViTinh=" + donViTinh
				+ ", giaNhap=" + giaNhap + ", donGia=" + donGia + ", soLuongNhap=" + soLuongNhap + ", hinhAnh="
				+ Arrays.toString(hinhAnh) + ", trangThai=" + trangThai + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maThuoc;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Thuoc other = (Thuoc) obj;
		if (maThuoc != other.maThuoc)
			return false;
		return true;
	}
	
 }
