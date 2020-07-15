/**
 * Người làm: Nguyễn Đình Quốc
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.ConectDatabase;
import entities.DiaChi;
import entities.NhanVien;
import entities.TaiKhoan;

public class NhanVienDAO {
	Connection con;
	PreparedStatement preStm;
	ResultSet rs;

	public NhanVienDAO() {
	}

	@SuppressWarnings("unused")
	private void closeConnection() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (preStm != null) {
			preStm.close();
		}
		if (con != null) {
			con.close();
		}
	}

	/**
	 * add NhanVien
	 * 
	 * @param dto
	 * @return true/false
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public boolean themNhanVien(NhanVien dto) throws SQLException {
		con = ConectDatabase.getInstance().getConnection();
		int n = 0;
		try {
			String sql = "insert into NhanVien\r\n" + " values(?,?,?,?,?,?,?,?,?,?,?)";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, dto.getCaLamViec());
			preStm.setString(2, dto.getTen());
			preStm.setString(3, dto.getHo());
			preStm.setDate(4, dto.getNgaySinh());
			preStm.setString(5, dto.getGioiTinh());
			preStm.setString(6, dto.getCmnd());
			preStm.setString(7, dto.getSoDienThoai());
			preStm.setInt(8, dto.getDiaChi().getMaDiaChi());
			preStm.setString(9, dto.getTaiKhoan().getTenTaiKhoan());
			preStm.setString(10, dto.getLoaiNhanVien());
			preStm.setString(11, dto.getTrangThai());
			n = preStm.executeUpdate();
		} catch (Exception e1) {
			System.out.println("Loi add nhanVien sql");
			e1.printStackTrace();
		} finally {
		}
		return n > 0;
	}

	/**
	 * delete nhanvien
	 * 
	 * @param maNV
	 * @return true/false
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public boolean xoaNhanVien(NhanVien nv) throws ClassNotFoundException, SQLException {
		con = ConectDatabase.getInstance().getConnection();
		boolean check = false;
		try {
			String sql = "update NhanVien\r\n" + "set TrangThai=N'Nghỉ việc' where MaNhanVien=?";
			preStm = con.prepareStatement(sql);
			preStm.setInt(1, nv.getMaNhanVien());
			check = preStm.executeUpdate() > 0;
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Loi xóa sql");
		} finally {
		}
		return check;
	}


	/**
	 * delete nhanvien by MaNhanVien
	 * 
	 * @param ma
	 * @param trangThai
	 * @return true/false
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public boolean deleteNhanVien(int ma, String trangThai) throws SQLException {
		Connection con = null;
		con = ConectDatabase.getInstance().getConnection();
		int n = 0;
		try {
			String sql = "Update NhanVien set TrangThai = ? where MaNhanVien = ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, trangThai);
			preStm.setInt(2, ma);
			n = preStm.executeUpdate();
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
		}
		return n > 0;
	}

	/**
	 * update NhanVien
	 * 
	 * @param dto
	 * @return true/false
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public boolean capnhatNhanVien(NhanVien dto) throws ClassNotFoundException, SQLException {
		con = ConectDatabase.getInstance().getConnection();
		int n = 0;
		try {
			String sql = "Update NhanVien set Ho = ?,Ten = ?,NgaySinh = ?,GioiTinh = ?,CMND = ?,SoDienThoai = ?,CaLamViec = ?,LoaiNhanVien = ?,TrangThai = ? where MaNhanVien = ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, dto.getHo());
			preStm.setString(2, dto.getTen());
			preStm.setDate(3, dto.getNgaySinh());
			preStm.setString(4, dto.getGioiTinh());
			preStm.setString(5, dto.getCmnd());
			preStm.setString(6, dto.getSoDienThoai());
			preStm.setString(7, dto.getCaLamViec());
			preStm.setString(8, dto.getLoaiNhanVien());
			preStm.setString(9, dto.getTrangThai());
			preStm.setInt(10, dto.getMaNhanVien());
			n = preStm.executeUpdate();
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Loi cap nhat sql");
		} finally {
		}
		return n > 0;
	}

	/**
	 * update nhanvien
	 * 
	 * @param ma
	 * @param ho
	 * @param ten
	 * @param ngaySinh
	 * @param gioiTinh
	 * @param cmnd
	 * @param sdt
	 * @param caLamViec
	 * @param loaiNV
	 * @param trangThai
	 * @return true/false
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public boolean updateNhanVien(int ma, String ho, String ten, Date ngaySinh, String gioiTinh, String cmnd,
			String sdt, String caLamViec, String loaiNV, String trangThai) throws SQLException {
		con = ConectDatabase.getInstance().getConnection();
		int n = 0;
		try {
			String sql = "Update NhanVien set Ho = ?,Ten = ?,NgaySinh = ?,GioiTinh = ?,CMND = ?,SoDienThoai = ?,CaLamViec = ?,LoaiNhanVien = ?,TrangThai = ? where MaNhanVien = ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, ho);
			preStm.setString(2, ten);
			preStm.setDate(3, ngaySinh);
			preStm.setString(4, gioiTinh);
			preStm.setString(5, cmnd);
			preStm.setString(6, sdt);
			preStm.setString(7, caLamViec);
			preStm.setString(8, loaiNV);
			preStm.setString(9, trangThai);
			preStm.setInt(10, ma);
			n = preStm.executeUpdate();
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Loi cap nhat sql");
		} finally {
		}
		return n > 0;
	}

	/**
	 * Search NhanVien by MaNhanVien
	 * 
	 * @param id
	 * @return List<NhanVien>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public List<NhanVien> timkiemNhanVienByMa(int ma) throws ClassNotFoundException, SQLException {
		List<NhanVien> result = null;
		NhanVien dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from NhanVien where MaNhanVien like ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, "%" + ma + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maNV = rs.getInt("MaNhanVien");
				String caLamViec = rs.getString("CaLamViec");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDT = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				String tenTK = rs.getString("TenTaiKhoan");
				String loaiNV = rs.getString("LoaiNhanVien");
				String trangThai = rs.getString("TrangThai");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setTenTaiKhoan(tenTK);
				dto = new NhanVien(maNV, caLamViec, ten, ho, ngaySinh, gioiTinh, CMND, soDT, diaChi, taiKhoan, loaiNV,
						trangThai);
				result.add(dto);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Loi tim nhan vien theo id sql");
		} finally {
		}
		return result;
	}

	/**
	 * Search NhanVien by SoDienThoai
	 * 
	 * @param id
	 * @return List<NhanVien>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public List<NhanVien> timkiemNhanVienBySDT(String sdt) throws ClassNotFoundException, SQLException {
		List<NhanVien> result = null;
		NhanVien dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from NhanVien where SoDienThoai like ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, "%" + sdt + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maNV = rs.getInt("MaNhanVien");
				String caLamViec = rs.getString("CaLamViec");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDT = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				String tenTK = rs.getString("TenTaiKhoan");
				String loaiNV = rs.getString("LoaiNhanVien");
				String trangThai = rs.getString("TrangThai");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setTenTaiKhoan(tenTK);
				dto = new NhanVien(maNV, caLamViec, ten, ho, ngaySinh, gioiTinh, CMND, soDT, diaChi, taiKhoan, loaiNV,
						trangThai);
				result.add(dto);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
		}
		return result;
	}

	/**
	 * Search NhanVien by Ten
	 * 
	 * @param id
	 * @return List<NhanVien>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public List<NhanVien> timkiemNhanVienByTen(String tennv) throws ClassNotFoundException, SQLException {
		List<NhanVien> result = null;
		NhanVien dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from NhanVien where Ten like ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, "%" + tennv + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maNV = rs.getInt("MaNhanVien");
				String caLamViec = rs.getString("CaLamViec");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDT = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				String tenTK = rs.getString("TenTaiKhoan");
				String loaiNV = rs.getString("LoaiNhanVien");
				String trangThai = rs.getString("TrangThai");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setTenTaiKhoan(tenTK);
				dto = new NhanVien(maNV, caLamViec, ten, ho, ngaySinh, gioiTinh, CMND, soDT, diaChi, taiKhoan, loaiNV,
						trangThai);
				result.add(dto);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
		}
		return result;
	}

	/**
	 * Search NhanVien By CaLamViec
	 * 
	 * @param caLamViecc
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public List<NhanVien> timkiemNhanVienByCalamViec(String caLamViecc) throws ClassNotFoundException, SQLException {
		List<NhanVien> result = null;
		NhanVien dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from NhanVien where CaLamViec like ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, "%" + caLamViecc + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maNV = rs.getInt("MaNhanVien");
				String caLamViec = rs.getString("CaLamViec");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDT = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				String tenTK = rs.getString("TenTaiKhoan");
				String loaiNV = rs.getString("LoaiNhanVien");
				String trangThai = rs.getString("TrangThai");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setTenTaiKhoan(tenTK);
				dto = new NhanVien(maNV, caLamViec, ten, ho, ngaySinh, gioiTinh, CMND, soDT, diaChi, taiKhoan, loaiNV,
						trangThai);
				result.add(dto);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
		}
		return result;
	}

	@SuppressWarnings("static-access")
	public NhanVien layThongTinNhanVien(int maNV) throws ClassNotFoundException, SQLException {
		NhanVien dto = null;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "Select * from NhanVien where MaNhanVien = ?";
			preStm = con.prepareStatement(sql);
			preStm.setInt(1, maNV);
			rs = preStm.executeQuery();
			if (rs.next()) {
				String caLamViec = rs.getString("CaLamViec");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDT = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				String tenTK = rs.getString("TenTaiKhoan");
				String loaiNV = rs.getString("LoaiNhanVien");
				String trangThai = rs.getString("TrangThai");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setTenTaiKhoan(tenTK);
				dto = new NhanVien(maNV, caLamViec, ten, ho, ngaySinh, gioiTinh, CMND, soDT, diaChi, taiKhoan, loaiNV,
						trangThai);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
		}
		return dto;
	}

	/**
	 * get all NhanVien
	 * 
	 * @return List<NhanVien>
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public List<NhanVien> getAllNhanVien() throws Exception {
		List<NhanVien> result = null;
		NhanVien dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select MaNhanVien,CaLamViec,Ten ,Ho,NgaySinh,GioiTinh,CMND,SoDienThoai,MaDiaChi,TenTaiKhoan,LoaiNhanVien,TrangThai from NhanVien";
			preStm = con.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maNV = rs.getInt("MaNhanVien");
				String caLamViec = rs.getString("CaLamViec");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDT = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				String tenTK = rs.getString("TenTaiKhoan");
				String loaiNV = rs.getString("LoaiNhanVien");
				String trangThai = rs.getString("TrangThai");

				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setTenTaiKhoan(tenTK);
				dto = new NhanVien(maNV, caLamViec, ten, ho, ngaySinh, gioiTinh, CMND, soDT, diaChi, taiKhoan, loaiNV,
						trangThai);
				result.add(dto);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
		}
		return result;
	}

	/**
	 * add TaiKhoan
	 * 
	 * @param tk
	 * @return true/false
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public boolean addTaiKhoan(TaiKhoan tk) throws ClassNotFoundException, SQLException {
		con = ConectDatabase.getInstance().getConnection();
		int n = 0;
		try {
			preStm = con.prepareStatement("insert into TaiKhoan values\r\n" + "(?,?)");
			preStm.setString(1, tk.getTenTaiKhoan());
			preStm.setString(2, tk.getMatKhau());
			n = preStm.executeUpdate();
		} catch (Exception e3) {
			e3.printStackTrace();
		} finally {
		}
		return n > 0;
	}

	/**
	 * get all TenTaiKhoan
	 * 
	 * @return List<String>
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public List<String> getAllTenTaiKhoan() throws Exception {
		List<String> result = null;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select [TenTaiKhoan] from [dbo].[TaiKhoan]";
			preStm = con.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String taiKhoan = rs.getString(1);
				result.add(taiKhoan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return result;
	}

	/**
	 * add DiaChi
	 * 
	 * @param dc
	 * @return true/false
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public boolean addDiaChi(DiaChi dc) throws SQLException {
		con = ConectDatabase.getInstance().getConnection();
		boolean check = false;
		try {
			preStm = con.prepareStatement("insert into dbo.DiaChi values\r\n" + "(?,?,?,?,?,?)");
			preStm.setString(1, dc.getSoNha());
			preStm.setString(2, dc.getTenDuong());
			preStm.setString(3, dc.getPhuong());
			preStm.setString(4, dc.getQuan());
			preStm.setString(5, dc.getThanhPho());
			preStm.setString(6, dc.getQuocGia());
			check = preStm.executeUpdate() > 0;
		} catch (Exception e3) {
			System.out.println("loi sql them dia chi");
			e3.printStackTrace();
		} finally {
		}
		return check;
	}

	/**
	 * get all MaDiaChi
	 * 
	 * @return List<Integer>
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public List<Integer> getAllMaDiaChi() throws Exception {
		List<Integer> result = new ArrayList<Integer>();
		con = ConectDatabase.getInstance().getConnection();
		try {
			String sql = "select [MaDiaChi] from [dbo].[DiaChi]";
			preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();
			while (rs.next()) {
				int maDC = rs.getInt(1);
				result.add(maDC);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lỗi lấy all MaDiaChi");
		} finally {
		}
		return result;
	}

	/**
	 * get MaDiaChiMax
	 * 
	 * @param maDiaChiMax
	 * @return maDiaChiMax
	 */
	@SuppressWarnings("static-access")
	public int getMaDiaChiMax() {
		int maDiaChiMax = 0;
		con = ConectDatabase.getInstance().getConnection();
		try {
			String sql = "select MAX(MaDiaChi)\r\n" + "from dbo.DiaChi";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				maDiaChiMax = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maDiaChiMax;
	}

	/**
	 * get maNhanVienMax
	 * 
	 * @return maNhanVienMax
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public int getMaNhanVienMax() throws SQLException {
		int maNhanVienMax = 0;
		con = ConectDatabase.getInstance().getConnection();
		try {
			String sql = "select MAX(MaNhanVien)\r\n" + "from dbo.NhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				maNhanVienMax = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return maNhanVienMax;
	}

	/**
	 * get all maNhanVien
	 * 
	 * @return List<Integer>
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public List<Integer> getAllMaNV() throws Exception {
		List<Integer> result = new ArrayList<Integer>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select MaNhanVien from NhanVien";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int maNV;
			while (rs.next()) {
				maNV = rs.getInt(1);
				result.add(maNV);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return result;
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

	/**
	 * get full name NhanVien by tenTaiKhoan
	 * 
	 * @param tenTaiKhoan
	 * @return String
	 */
	@SuppressWarnings("static-access")
	public String getTenNhanVienByTenTaiKhoan(String tenTaiKhoan) {
		String tenTaiKhoan1 = null;

		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select Ho + ' '+Ten\r\n"
					+ "from dbo.NhanVien n join dbo.TaiKhoan t on n.TenTaiKhoan = t.TenTaiKhoan\r\n"
					+ "where t.TenTaiKhoan = ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, tenTaiKhoan);
			rs = preStm.executeQuery();
			while (rs.next()) {
				tenTaiKhoan1 = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error getTenNhanVienByTenTaiKhoan SQL !");
		}
		return tenTaiKhoan1;
	}
}
