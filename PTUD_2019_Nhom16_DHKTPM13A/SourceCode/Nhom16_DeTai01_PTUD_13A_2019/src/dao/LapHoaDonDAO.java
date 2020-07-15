package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.ConectDatabase;
import entities.DiaChi;
import entities.KhachHang;
import entities.NhaCungCap;
import entities.Thuoc;
import gui.FrmDangNhap;

public class LapHoaDonDAO {
	public LapHoaDonDAO() {
		// TODO Auto-generated constructor stub
		ConectDatabase.getInstance().connect();
	}
	public boolean addKhachHang(String ten,String ho,Date ngaySinh,String gioiTinh,String cmnd,String soDienThoai,int maDiaChi ) {
		Connection con = ConectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement("insert into dbo.KhachHang values\r\n" + 
					"(?,?,?,?,?,?,?)");
			stmt.setString(1, ten);
			stmt.setString(2, ho);
			stmt.setDate(3, ngaySinh);
			stmt.setString(4, gioiTinh);
			stmt.setString(5, cmnd);
			stmt.setString(6, soDienThoai);
			stmt.setInt(7, maDiaChi);
			n= stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		return n>0;
	}
	public boolean addDiaChi(String soNha,String tenDuong,String phuong,String quan,String thanhPho,String quocGia) {
		Connection con = ConectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement("insert into dbo.DiaChi values\r\n" + 
					"(?,?,?,?,?,?)");
			stmt.setString(1, soNha);
			stmt.setString(2, tenDuong);
			stmt.setString(3, phuong);
			stmt.setString(4, quan);
			stmt.setString(5, thanhPho);
			stmt.setString(6, quocGia);
			n= stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		return n>0;
	}
	public int maDiaChiMax() {
		int maDiaChiMax = 0;
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql  = "select MAX(MaDiaChi)\r\n" + 
					"from dbo.DiaChi";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while(rs.next()) {
				maDiaChiMax = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maDiaChiMax;
	}
	public int maKhachHangBySDT(String soDienThoai) {
		int maKhachHang = 0;
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select top 1 MaKhachHang\r\n" + 
					"from dbo.KhachHang\r\n" + 
					"where SoDienThoai = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,soDienThoai);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				maKhachHang = rs.getInt(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return maKhachHang;
	}
	public boolean addHoaDonLoaiKeDon(int maNhanVien,int maKhachHang,float tongTien) {
		Connection con = ConectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement("insert into dbo.HoaDon(MaNhanVien,MaKhachHang,TongTien) values\r\n" + 
					"(?,?,?)");
			stmt.setInt(1,maNhanVien);
			stmt.setInt(2, maKhachHang);
			stmt.setFloat(3, tongTien);
			n= stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		return n>0;
	}
	public List<Thuoc> updateComboxTenThuoc() {
		List<Thuoc> lists = new ArrayList<Thuoc>();	
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql  = "select TenThuoc\r\n" + 
					"from dbo.Thuoc\r\n" + 
					"where HanSuDung > GETDATE() and TrangThai like N'đang bán' \r\n" + 
					"order by TenThuoc";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			String tenThuoc ="";
			while(rs.next()) {
				Thuoc thuoc = new Thuoc();
				tenThuoc = rs.getString(1);
				thuoc.setTenThuoc(tenThuoc);
				lists.add(thuoc);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lists;
	}
	public List<Thuoc> timKiemThuocTheoTen(String tenThuoc) {
		List<Thuoc> lists = new ArrayList<Thuoc>();
		Thuoc thuoc = new Thuoc();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select TenThuoc,PhanLoai,DonViTinh,HamLuong,DonGia\r\n" + 
					"from dbo.Thuoc\r\n" + 
					"where TenThuoc =?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,tenThuoc);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String tenThuocT = rs.getString(1).toString();
				String phanLoai = rs.getString(2);
				String donViTinh = rs.getString(3);
				String hamLuong = rs.getString(4);
				float donGia = rs.getFloat(5);
				thuoc.setTenThuoc(tenThuocT);
				thuoc.setPhanLoai(phanLoai);
				thuoc.setDonViTinh(donViTinh);
				thuoc.setHamLuong(hamLuong);
				thuoc.setDonGia(donGia);

				lists.add(thuoc);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return lists;
	}
	public String maNhanVien(String tenDN) {
		String tenDN1 = null;
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select MaNhanVien\r\n" + 
					"from dbo.TaiKhoan t join dbo.NhanVien n on t.TenTaiKhoan = n.TenTaiKhoan\r\n" + 
					"where t.TenTaiKhoan =?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,tenDN);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				tenDN1 = rs.getString(1).toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tenDN1;
	}
	public String maThuoc(String tenThuoc) {
		String thuoc = null;
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select MaThuoc\r\n" + 
					"from dbo.Thuoc \r\n" + 
					"where TenThuoc = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,tenThuoc);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				thuoc = rs.getString(1).toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return thuoc;
	}
	public static String tenNhanVien(String tenTaiKhoan) {
		String tenTaiKhoan1 = null;
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select Ten + ' '+Ho\r\n" + 
					"from dbo.NhanVien n join dbo.TaiKhoan t on n.TenTaiKhoan = t.TenTaiKhoan\r\n" + 
					"where t.TenTaiKhoan = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,tenTaiKhoan);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				tenTaiKhoan1 = rs.getString(1).toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tenTaiKhoan1;
	}
	public String maHoaDon(String maNhanVien) {
		String maHoaDon = null;
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select 'HD'+convert(nvarchar,MAX(RIGHT(LEFT(MaHoaDon,7),5))+'NV'+ ? +CONVERT(nvarchar,GETDATE(),112))\r\n" + 
					"					from dbo.HoaDon";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,maNhanVien);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				maHoaDon = rs.getString(1).toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maHoaDon;
	}
	public static int soLuongThuocDaBan(int maThuoc) {
		int soLuongThuoc =0;
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select soLuongThuocDaBan = SUM(SoLuong)\r\n" + 
					"from dbo.CT_HoaDon\r\n" + 
					"where MaThuoc = ?\r\n" + 
					"group by MaThuoc";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,maThuoc);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				soLuongThuoc = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return soLuongThuoc;
	}
	public int soLuongNhap(int maThuoc) {
		int soLuongNhap =0;
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select SoLuongNhap\r\n" + 
					"from dbo.Thuoc \r\n" + 
					"where MaThuoc = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,maThuoc);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				soLuongNhap = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return soLuongNhap;
	}
	public int maThuocTheoTenVaDonViTinh(String tenThuoc,String donViTinh) {
		int maThuoc =0;
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select MaThuoc\r\n" + 
					"from dbo.Thuoc\r\n" + 
					"where TenThuoc = ? and DonViTinh = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tenThuoc);
			stmt.setString(2,donViTinh);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				maThuoc = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maThuoc;
	}
	public List<Thuoc> updateCboDonViTinhFrmChonDonViTinh(String tenThuoc) {
		List<Thuoc> lists = new ArrayList<Thuoc>();

		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select DonViTinh\r\n" + 
					"from dbo.Thuoc\r\n" + 
					"where TenThuoc = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,tenThuoc);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Thuoc thuoc = new Thuoc();
				String donViTinh = rs.getString(1);
				thuoc.setDonViTinh(donViTinh);
				lists.add(thuoc);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lists;
	}
	public boolean addCT_HoaDon(String maHoaDon,int maThuoc,float donGia,float giamGia,int soLuong,String donViTinh) {
		Connection con = ConectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement("insert into dbo.CT_HoaDon values\r\n" + 
					"(?,?,?,?,?,?)");
			stmt.setString(1,maHoaDon);
			stmt.setInt(2, maThuoc);
			stmt.setFloat(3, donGia);
			stmt.setFloat(4, giamGia);
			stmt.setInt(5, soLuong);
			stmt.setString(6,donViTinh);
			n= stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		return n>0;
	}
	public static List<Thuoc> danhSachThuocTheoTenVaDonViTinh(String tenThuoc,String donViTinh) {
		List<Thuoc> lists = new ArrayList<Thuoc>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select TenThuoc,PhanLoai,DonViTinh,HamLuong,DonGia\r\n" + 
					"from dbo.Thuoc\r\n" + 
					"where TenThuoc like ? and DonViTinh like ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,tenThuoc);
			stmt.setString(2, donViTinh);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String tenThuocs = rs.getString(1);
				String loaiThuoc = rs.getString(2);
				String donViTinhs = rs.getString(3);
				String hamLuong = rs.getString(4);
				float donGia = rs.getFloat(5);

				Thuoc thuoc = new Thuoc();
				thuoc.setTenThuoc(tenThuocs);
				thuoc.setPhanLoai(loaiThuoc);
				thuoc.setDonViTinh(donViTinhs);
				thuoc.setHamLuong(hamLuong);
				thuoc.setDonGia(donGia);

				lists.add(thuoc);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lists;
	}
	public List<KhachHang> timKhachHangBySDT(String sdt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		List<KhachHang> lists = new ArrayList<KhachHang>();
		try {
			int soLuong=1;
			double thanhTien=0;
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select Ho,Ten,GioiTinh,CMND,NgaySinh,SoNha,TenDuong,Phuong,Quan,ThanhPho,QuocGia\r\n" + 
					"from dbo.KhachHang k join dbo.DiaChi d on k.MaDiaChi = d.MaDiaChi\r\n" + 
					"where SoDienThoai = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,sdt);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String ho = rs.getString(1).toString();
				String ten = rs.getString(2);
				String gioiTinh = rs.getString(3);
				String CMND = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String soNha = rs.getString(6);
				String tenDuong = rs.getString(7);
				String phuong = rs.getString(8);
				String quan = rs.getString(9);
				String thanhPho = rs.getString(10);
				String quocGia = rs.getString(11);
				KhachHang khachHang = new KhachHang();
				khachHang.setHo(ho);
				khachHang.setTen(ten);
				khachHang.setGioiTinh(gioiTinh);
				khachHang.setCmnd(CMND);
				khachHang.setNgaySinh(ngaySinh);

				DiaChi diaChi = new DiaChi();
				diaChi.setSoNha(soNha);
				diaChi.setTenDuong(tenDuong);
				diaChi.setPhuong(phuong);
				diaChi.setQuan(quan);
				diaChi.setThanhPho(thanhPho);
				diaChi.setQuocGia(quocGia);
				khachHang.setDiaChi(diaChi);

				lists.add(khachHang);
			}  
		}catch (Exception e) {
			// TODO: handle exception
		}
		return lists;
	}
	public List<Thuoc> danhSachThuocTimKiemTheoTen_TimKiemTuongDoi(String tenThuoc){
		List<Thuoc> dsThuoc = new ArrayList<Thuoc>();
		List<Thuoc> dsThuocTimKiemTuongDoi = new ArrayList<Thuoc>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select MaThuoc,NhomThuoc,TenThuoc,PhanLoai,HamLuong,DangBaoChe,DonViTinh,DonGia,Ten,SoLuongNhap\r\n" + 
					"from dbo.Thuoc t join dbo.NhaCungCap n on t.MaNhaCungCap = n.MaNhaCungCap\r\n" + 
					"where HanSuDung > GETDATE() and TrangThai like N'đang bán'";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Thuoc thuoc = new Thuoc();
				thuoc.setMaThuoc(rs.getInt(1));
				thuoc.setNhomThuoc(rs.getString(2));
				thuoc.setTenThuoc(rs.getString(3));
				thuoc.setPhanLoai(rs.getString(4));
				thuoc.setHamLuong(rs.getString(5));
				thuoc.setDangBaoChe(rs.getString(6));
				thuoc.setDonViTinh(rs.getString(7));
				thuoc.setDonGia(rs.getFloat(8));
				thuoc.setNhaCungcap(new NhaCungCap(rs.getString(9)));
				thuoc.setSoLuongNhap(rs.getInt(10));
				dsThuoc.add(thuoc);
			}  
		}catch (Exception e) {
			// TODO: handle exception
		}
		for(Thuoc thuoc : dsThuoc) {
			if(thuoc.getTenThuoc().contains(tenThuoc) && (thuoc.getSoLuongNhap() > soLuongThuocDaBan(thuoc.getMaThuoc()))) {
				dsThuocTimKiemTuongDoi.add(thuoc);
				
			}
			
		}


		return dsThuocTimKiemTuongDoi;
	}

	public Thuoc ThuocTheoMa(int maThuoc){
		Thuoc thuoc = new Thuoc();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select MaThuoc,TenThuoc,PhanLoai,DonViTinh,HamLuong,DonGia\r\n" + 
					"					from dbo.Thuoc \r\n" + 
					"					where MaThuoc = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, maThuoc);;
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int maThuocs = rs.getInt(1);
				String tenThuocs = rs.getString(2);
				String loaiThuoc = rs.getString(3);
				String donViTinhs = rs.getString(4);
				String hamLuong = rs.getString(5);
				float donGia = rs.getFloat(6);
				thuoc.setMaThuoc(maThuocs);
				thuoc.setTenThuoc(tenThuocs);
				thuoc.setPhanLoai(loaiThuoc);
				thuoc.setDonViTinh(donViTinhs);
				thuoc.setHamLuong(hamLuong);
				thuoc.setDonGia(donGia);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return thuoc;
	}
	public static void main(String[] args) {
		LapHoaDonDAO lapHoaDonDAO =new LapHoaDonDAO();
		
		System.out.println("danh sach thuoc");
		System.out.println(lapHoaDonDAO.ThuocTheoMa(2));
	}
}
