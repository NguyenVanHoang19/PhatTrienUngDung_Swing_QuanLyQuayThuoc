/**
 * Người làm: Nguyễn Đình Quốc
 */

package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import database.ConectDatabase;
import entities.ChiTietHoaDon;
import entities.HoaDon;
import entities.KhachHang;
import entities.NhanVien;
import entities.Thuoc;

public class HoaDonDAO {
	public HoaDonDAO() {
		// TODO Auto-generated constructor stub
		ConectDatabase.getInstance().connect();
	}
	/**
	 * lấy danh sách hóa đơn từ cơ sở dữ liệu
	 * @return dánh sách hóa đơn
	 */
	public List<HoaDon> getHoaDons() {
		List<HoaDon> list= new ArrayList<HoaDon>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [MaHoaDon],[NgayLap],[TongTien],[MaNhanVien],[MaKhachHang]\r\n" + 
					"from [dbo].[HoaDon] ";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
				String ma=rs.getString(1);
				Date ngayLap= rs.getDate(2);
				double tongTien=rs.getDouble(3);
				int nhanVien= rs.getInt(4);
				int khachHang= rs.getInt(5);
				HoaDon hd= new HoaDon(ma, ngayLap, tongTien, nhanVien, khachHang);
				list.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	/**
	 * Lấy chi tiết hóa đơn từ cơ sở dữ liệu khi biết mã hóa đơn
	 * @param ma
	 * @return chi tiết hóa đơn
	 */
	public List<ChiTietHoaDon> getChiTiets(String ma){
		List<ChiTietHoaDon> list = new ArrayList<ChiTietHoaDon>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [MaHoaDon],[MaThuoc],[DonViTinh],[DonGia],[GiamGia],[SoLuong]\r\n" + 
					"from [dbo].[CT_HoaDon] \r\n" + 
					"where [MaHoaDon] like  '"+ma+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maHD= rs.getString(1);
				int thuoc = rs.getInt(2);
				String dvt= rs.getString(3);
				float donGia= rs.getFloat(4);
				float giamGia= rs.getFloat(5);
				int soLuong= rs.getInt(6);

				ChiTietHoaDon ct = new ChiTietHoaDon(maHD, thuoc, donGia, giamGia, soLuong, dvt);
				list.add(ct);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	/**
	 * Lấy tên khách hàng khi biết mã khách hàng 
	 * @param ma
	 * @return tên khách hàng
	 */
	public String getTenKH(int ma){
		String ten="";
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [Ten]\r\n" + 
					"from [dbo].[KhachHang]\r\n" + 
					"where [MaKhachHang]="+ma+"";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String t= rs.getString(1);
				ten=t;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ten;
	}
	/**
	 * Lấy hóa đơn khi biết mã
	 * @param ma
	 * @return hóa đơn
	 */
	public HoaDon getHoaDonByMa(String ma) {
		HoaDon hd=new HoaDon();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [NgayLap],[TongTien],[MaNhanVien],[MaKhachHang]\r\n" + 
					"from [dbo].[HoaDon] \r\n" + 
					"where [MaHoaDon] like '"+ma+"' ";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
				Date ngayLap= rs.getDate(1);
				double tongTien=rs.getDouble(2);
				int nhanVien= rs.getInt(3);
				int khachHang= rs.getInt(4);
				hd= new HoaDon(ma, ngayLap, tongTien, nhanVien, khachHang);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return hd;
	}
	/**
	 * Lấy danh sách hóa đơn của nhân viên đã lập
	 * @param ten
	 * @return danh sách hóa đơn
	 */
	public List<HoaDon> getHoaDonsByNhanVien(String ten) {
		List<HoaDon> list= new ArrayList<HoaDon>();

		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [MaHoaDon],[NgayLap],[TongTien],nv.[MaNhanVien],[MaKhachHang]\r\n" + 
					"from [dbo].[HoaDon] hd  join [dbo].[NhanVien] nv on hd.MaNhanVien=nv.MaNhanVien\r\n" + 
					"where nv.Ten like N'"+ten+"' ";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String ma=rs.getString(1);
				Date ngayLap= rs.getDate(2);
				double tongTien=rs.getDouble(3);
				int nhanVien= rs.getInt(4);
				int khachHang= rs.getInt(5);
				HoaDon hd= new HoaDon(ma, ngayLap, tongTien, nhanVien, khachHang);
				list.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	/**
	 * Lấy Hóa đơn có ngày lập đã chọn
	 * @param ngay
	 * @param thang
	 * @param nam
	 * @return hóa đơn
	 */
	public List<HoaDon> getHoaDonsByNgayLap(int ngay,int thang,int nam) {
		List<HoaDon> list= new ArrayList<HoaDon>();	
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [MaHoaDon],[NgayLap],[TongTien],[MaNhanVien],[MaKhachHang]\r\n" + 
					"from [dbo].[HoaDon]\r\n" + 
					"where DAY([NgayLap])= "+ngay+"  and MONTH([NgayLap]) = "+thang+" and YEAR([NgayLap])="+nam+"";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String ma=rs.getString(1);
				Date ngayLap= rs.getDate(2);
				double tongTien=rs.getDouble(3);
				int nhanVien= rs.getInt(4);
				int khachHang= rs.getInt(5);
				HoaDon hd= new HoaDon(ma, ngayLap, tongTien, nhanVien, khachHang);
				list.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	/**
	 * Lấy danh sách hóa đơn của khách hàng đã chọn mua 
	 * @param ten
	 * @return danh sách hóa đơn
	 */
	public List<HoaDon> getHoaDonsByKhachHang(String ten) {
		List<HoaDon> list= new ArrayList<HoaDon>();

		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [MaHoaDon],[NgayLap],[TongTien],[MaNhanVien],kh.[MaKhachHang]\r\n" + 
					"from [dbo].[HoaDon] hd  join [dbo].[KhachHang] kh on hd.MaKhachHang=kh.MaKhachHang\r\n" + 
					"where kh.Ten like N'"+ten+"' ";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
				String ma=rs.getString(1);
				Date ngayLap= rs.getDate(2);
				double tongTien=rs.getDouble(3);
				int nhanVien= rs.getInt(4);
				int khachHang= rs.getInt(5);
				HoaDon hd= new HoaDon(ma, ngayLap, tongTien, nhanVien, khachHang);
				list.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	/**
	 * Lấy tên nhân viên khi biết mã nhân vien
	 * @param ma
	 * @return tên nhân viên
	 */
	public String getTenNV(int ma){
		String ten="";
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [Ten]\r\n" + 
					"from [dbo].[NhanVien]\r\n" + 
					"where [MaNhanVien]="+ma+"";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String t= rs.getString(1);
				ten=t;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ten;
	}
	/**
	 * Lấy tên thuốc khi biết mã thuốc
	 * @param ma
	 * @return tên thuốc 
	 */
	public String gettenThuoc(int ma){
		String ten="";
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [TenThuoc]\r\n" + 
					"from [dbo].[Thuoc]\r\n" + 
					"where [MaThuoc]="+ma+"";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String t= rs.getString(1);
				ten=t;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ten;
	}	
}
