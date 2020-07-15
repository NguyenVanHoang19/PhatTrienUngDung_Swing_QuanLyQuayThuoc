package entities;

public class DiaChi {
	private int maDiaChi;
	private String soNha;
	private String tenDuong;
	private String phuong;
	private String quan;
	private String thanhPho;
	private String quocGia;
	public DiaChi(int maDiaChi, String soNha, String tenDuong, String phuong, String quan, String thanhPho,
			String quocGia) {
		super();
		this.maDiaChi = maDiaChi;
		this.soNha = soNha;
		this.tenDuong = tenDuong;
		this.phuong = phuong;
		this.quan = quan;
		this.thanhPho = thanhPho;
		this.quocGia = quocGia;
	}
	public DiaChi(String soNha, String tenDuong, String phuong, String quan, String thanhPho, String quocGia) {
		super();
		this.soNha = soNha;
		this.tenDuong = tenDuong;
		this.phuong = phuong;
		this.quan = quan;
		this.thanhPho = thanhPho;
		this.quocGia = quocGia;
	}
	public DiaChi(int maDiaChi) {
		super();
		this.maDiaChi = maDiaChi;
	}

	public DiaChi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaDiaChi() {
		return maDiaChi;
	}
	public void setMaDiaChi(int maDiaChi) {
		this.maDiaChi = maDiaChi;
	}
	public String getSoNha() {
		return soNha;
	}
	public void setSoNha(String soNha) {
		this.soNha = soNha;
	}
	public String getTenDuong() {
		return tenDuong;
	}
	public void setTenDuong(String tenDuong) {
		this.tenDuong = tenDuong;
	}
	public String getPhuong() {
		return phuong;
	}
	public void setPhuong(String phuong) {
		this.phuong = phuong;
	}
	public String getQuan() {
		return quan;
	}
	public void setQuan(String quan) {
		this.quan = quan;
	}
	public String getThanhPho() {
		return thanhPho;
	}
	public void setThanhPho(String thanhPho) {
		this.thanhPho = thanhPho;
	}
	public String getQuocGia() {
		return quocGia;
	}
	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}
	@Override
	public String toString() {
		return soNha + "," + tenDuong + "," + phuong + "," + quan + "," + thanhPho + "," + quocGia ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maDiaChi;
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
		DiaChi other = (DiaChi) obj;
		if (maDiaChi != other.maDiaChi)
			return false;
		return true;
	}
	
}
