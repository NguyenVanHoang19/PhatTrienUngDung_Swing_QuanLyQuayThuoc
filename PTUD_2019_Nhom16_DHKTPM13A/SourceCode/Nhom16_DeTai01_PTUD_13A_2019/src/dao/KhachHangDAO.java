package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConectDatabase;
import entities.DiaChi;
import entities.KhachHang;

public class KhachHangDAO {
	Connection con;
	PreparedStatement preStm;
	ResultSet rs;

	public KhachHangDAO() {
	}

	/**
	 * add Khachhang
	 * 
	 * @param kh
	 * @return true/false
	 */
	@SuppressWarnings("static-access")
	public boolean themKhachHang(KhachHang kh) {
		int n = 0;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "insert into KhachHang values(?,?,?,?,?,?,?)";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, kh.getTen());
			preStm.setString(2, kh.getHo());
			preStm.setDate(3, kh.getNgaySinh());
			preStm.setString(4, kh.getGioiTinh());
			preStm.setString(5, kh.getCmnd());
			preStm.setString(6, kh.getSoDienThoai());
			preStm.setInt(7, kh.getDiaChi().getMaDiaChi());
			n = preStm.executeUpdate();
		} catch (Exception e) {
		}
		return n > 0;
	}

	/**
	 * ad DiaChi
	 * 
	 * @param dc
	 * @return true/false
	 */
	@SuppressWarnings("static-access")
	public boolean addDiaChi(DiaChi dc) {
		int n = 0;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "insert into dbo.DiaChi values\r\n" + "(?,?,?,?,?,?)";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, dc.getSoNha());
			preStm.setString(2, dc.getTenDuong());
			preStm.setString(3, dc.getPhuong());
			preStm.setString(4, dc.getQuan());
			preStm.setString(5, dc.getThanhPho());
			preStm.setString(6, dc.getQuocGia());
			n = preStm.executeUpdate();
		} catch (Exception e3) {
			System.out.println("loi sql them dia chi frm KhachHang");
			e3.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * get all KhachHang
	 * 
	 * @return List<KhachHang>
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public List<KhachHang> getAllKhachHang() throws Exception {
		List<KhachHang> result = null;
		KhachHang dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from KhachHang";
			preStm = con.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maKH = rs.getInt("MaKhachHang");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String cmnd = rs.getString("CMND");
				String soDT = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				dto = new KhachHang(maKH, ten, ho, ngaySinh, gioiTinh, cmnd, soDT, diaChi);
				result.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * delete KhachHang
	 * 
	 * @param maKH
	 * @return true/false
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public boolean xoaKhachHang(int maKH) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "delete KhachHang where MaKhachHang=?";
			preStm = con.prepareStatement(sql);
			preStm.setInt(1, maKH);
			check = preStm.executeUpdate() > 0;
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return check;
	}

	/**
	 * get information KhachHang
	 * 
	 * @param maKH
	 * @return KhachHang
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public KhachHang layThongTinKhachHang(int maKH) throws ClassNotFoundException, SQLException {
		KhachHang dto = null;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "Select * from KhachHang where MaKhachHang = ?";
			preStm = con.prepareStatement(sql);
			preStm.setInt(1, maKH);
			rs = preStm.executeQuery();
			if (rs.next()) {
				int maKhachHang = rs.getInt("MaKhachHang");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDT = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				dto = new KhachHang(maKH, ten, ho, ngaySinh, gioiTinh, CMND, soDT, diaChi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	/**
	 * search KhachHang by MaKhachHang
	 * 
	 * @param ma
	 * @return List<KhachHang>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public List<KhachHang> timkiemKhachHangByMa(int ma) throws ClassNotFoundException, SQLException {
		List<KhachHang> result = null;
		KhachHang dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from KhachHang where MaKhachHang like ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, "%" + ma + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maKH = rs.getInt("MaKhachHang");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDienThoai = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				dto = new KhachHang(maKH, ten, ho, ngaySinh, gioiTinh, CMND, soDienThoai, diaChi);
				result.add(dto);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
		}
		return result;
	}

	/**
	 * search KhachHang by TenKhachHang
	 * 
	 * @param tenn
	 * @return List<KhachHang>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public List<KhachHang> timkiemKhachHangByTen(String tenn) throws ClassNotFoundException, SQLException {
		List<KhachHang> result = null;
		KhachHang dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from KhachHang where Ten like ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, "%" + tenn + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maKH = rs.getInt("MaKhachHang");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDienThoai = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				dto = new KhachHang(maKH, ten, ho, ngaySinh, gioiTinh, CMND, soDienThoai, diaChi);
				result.add(dto);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
		}
		return result;
	}

	/**
	 * search KhachHang by SoDienThoai
	 * 
	 * @param sdt
	 * @return List<KhachHang>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public List<KhachHang> timkiemKhachHangBySDT(String sdt) throws ClassNotFoundException, SQLException {
		List<KhachHang> result = null;
		KhachHang dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from KhachHang where SoDienThoai like ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, "%" + sdt + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maKH = rs.getInt("MaKhachHang");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDienThoai = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				dto = new KhachHang(maKH, ten, ho, ngaySinh, gioiTinh, CMND, soDienThoai, diaChi);
				result.add(dto);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Loi tim khachhang theo sdt sql");
		} finally {
		}
		return result;
	}

	/**
	 * search KhachHang by SoCMND
	 * 
	 * @param cmnd
	 * @return List<KhachHang>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public List<KhachHang> timkiemKhachHangByCMND(String cmnd) throws ClassNotFoundException, SQLException {
		List<KhachHang> result = null;
		KhachHang dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from KhachHang where CMND like ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, "%" + cmnd + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maKH = rs.getInt("MaKhachHang");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDienThoai = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				dto = new KhachHang(maKH, ten, ho, ngaySinh, gioiTinh, cmnd, soDienThoai, diaChi);
				result.add(dto);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Loi tim khachhang theo cmnd sql");
		} finally {
		}
		return result;
	}

	/**
	 * update KhachHang
	 * 
	 * @param ma
	 * @param ten
	 * @param ho
	 * @param ngaySinh
	 * @param gioiTinh
	 * @param cmnd
	 * @param sdt
	 * @return true/false
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public boolean capnhatNhanVien(int ma, String ten, String ho, Date ngaySinh, String gioiTinh, String cmnd,
			String sdt) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "Update KhachHang set Ten = ?, Ho = ?, NgaySinh = ?, GioiTinh = ?, "
					+ "CMND = ?, SoDienThoai = ? where MaKhachHang = ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, ten);
			preStm.setString(2, ho);
			preStm.setDate(3, ngaySinh);
			preStm.setString(4, gioiTinh);
			preStm.setString(5, cmnd);
			preStm.setString(6, sdt);
			preStm.setInt(7, ma);
			check = preStm.executeUpdate() > 0;
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
		}
		return check;
	}

	/**
	 * get information DiaChi
	 * 
	 * @param maDC
	 * @return DiaChi
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public DiaChi layThongTinDiaChi(int maDC) throws ClassNotFoundException, SQLException {
		DiaChi dto = null;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "Select * from DiaChi where MaDiaChi = ?";
			preStm = con.prepareStatement(sql);
			preStm.setInt(1, maDC);
			rs = preStm.executeQuery();
			if (rs.next()) {
				String soNha = rs.getString("SoNha");
				String tenDuong = rs.getString("TenDuong");
				String phuong = rs.getString("Phuong");
				String quan = rs.getString("Quan");
				String thanhPho = rs.getString("ThanhPho");
				String quocGia = rs.getString("QuocGia");
				int maDiaChi = rs.getInt("MaDiaChi");
				dto = new DiaChi(maDiaChi, soNha, tenDuong, phuong, quan, thanhPho, quocGia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
