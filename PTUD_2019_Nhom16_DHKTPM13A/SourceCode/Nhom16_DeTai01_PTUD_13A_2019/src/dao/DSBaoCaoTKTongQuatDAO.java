package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.ConectDatabase;
import entities.NhaCungCap;
import entities.ThongKeBaoCaoTQ;
import entities.Thuoc;

public class DSBaoCaoTKTongQuatDAO {
	Connection con;
	PreparedStatement pre;
	ResultSet rs;

	ArrayList<ThongKeBaoCaoTQ> ds;
	ThongKeBaoCaoTQ tkbc;

	public DSBaoCaoTKTongQuatDAO() {
		ds = new ArrayList<ThongKeBaoCaoTQ>();
	}
	/*public List<Thuoc> getThuoc() {
		 List<Thuoc> list= new ArrayList<>();
		 try {
				Connection con = ConectDatabase.getInstance().getConnection();
				String sql="select t.MaThuoc,t.TenThuoc,t.DonGia,t.GiaNhap,SUM(t.SoLuongNhap) AS SoLuongNhap,sum(ct.SoLuong) as SoLuongBan,sum(ct.SoLuong*t.DonGia) as TienBan ,CONVERT (nvarchar(10), t.HanSuDung, 103) as HanSuDung,t.[SoDangKi]\r\n" + 
						"		from CT_HoaDon ct join Thuoc t on ct.maThuoc= t.maThuoc join HoaDon h on  ct.MaHoaDon=h.MaHoaDon\r\n" + 
						"		where month(NgayLap)=?\r\n" + 
						"		group by  t.MaThuoc,t.TenThuoc,t.DonGia,t.GiaNhap,CONVERT (nvarchar(10), t.HanSuDung, 103),t.[SoDangKi]";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()) {
					SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
					String maThuoc = rs.getString(1);
					String tenThuoc = rs.getString(2);
					String donGia = rs.getString(3);
					String donGiaNhap=rs.getString(4);
					String ngayhan = rs.getString(8);
					String loiNhuan = rs.getString(7);
					//String slKho = rs.getString(4);
					String slNhap = rs.getString(5);
					String   slban = rs.getString(6);
					String maNhap = rs.getString(9);
					ThongKeBaoCaoTQ s =new ThongKeBaoCaoTQ(maThuoc,tenThuoc,Double.parseDouble(donGia),Double.parseDouble(donGiaNhap),Integer.parseInt(slNhap),Integer.parseInt(slban),Double.parseDouble(loiNhuan),ngayhan,maNhap);
					ds.add(s);
					
//	 				String soLuongNhap=String.valueOf(sl);
//					String rowData[] = {maThuoc,soDangKi,tenThuoc,phanLoai,hoatChat,hamLuong,dangBaoChe,quyCach,tieuChuan,nhaCungCap,ngaySanXuat,hanSuDung,donViTinh,giaNhap,donGia,soLuongNhap};
//					lblAnhThuoc.setIcon(new ImageIcon(hinhAnh)); 
	 				//NhaCungCap ncc= new NhaCungCap(nhaCungCap);
	 				
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return list;
	 }

	
*/
	
	
	public List<String> getAllNgay() throws Exception {
		List<String> result = new ArrayList<String>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select NgayLap from HoaDon";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			String ngay;
			while (rs.next()) {
				ngay = rs.getString(1);
				result.add(ngay);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return result;
	}

	public double tinhTongThuocBanDuocTheoThang(int thang, int nam) throws Exception {
		double tongTien = 0;
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql = "select sum(c.SoLuong* c.DonGia) from HoaDon h join CT_HoaDon c on h.MaHoaDon=c.MaHoaDon\r\n"
					+ "where month(NgayLap)=? and  year(NgayLap)=?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, thang);
			pre.setInt(2, nam);
			rs = pre.executeQuery();
			while (rs.next()) {
				tongTien = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return tongTien;
	}

	 

//	public double tinhTongTienThuocDaNhapTheoThang(int thang, int nam) throws Exception {
//		double tongTien = 0;
//		try {
//			Connection con = ConectDatabase.getInstance().getConnection();
//			String sql = "select sum(c.SoLuong*t.GiaNhap) from HoaDon h join CT_HoaDon c\r\n" + 
//					"on h.MaHoaDon=c.MaHoaDon \r\n" + 
//					"join Thuoc t on t.MaThuoc=c.MaThuoc\r\n" + 
//					"where month(h.NgayLap) =? and year(NgayLap)=? group by t.MaThuoc \r\n" + 
//					"";
//			pre = con.prepareStatement(sql);
//			pre.setInt(1, thang);
//			pre.setInt(2, nam);
//			rs = pre.executeQuery();
//			while (rs.next()) {
//				tongTien = rs.getDouble(1);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//		}
//		return tongTien;
//	}
	
	public double tinhTongTienThuocDaNhapTheoThang(int thang, int nam) throws Exception {
		double tongTien = 0;
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql =  "								select t.MaThuoc,t.TenThuoc,t.DonGia,t.GiaNhap,CONVERT (nvarchar(10), h.NgayLap, 103) as HanSuDung,t.[SoDangKi],SUM(t.SoLuongNhap) AS SoLuongNhap,sum(ct.SoLuong) as SoLuongBan,sum(ct.SoLuong*ct.DonGia) as TienBan,sum(ct.SoLuong*t.GiaNhap) as TienNhap\r\n" + 
					"					from CT_HoaDon ct join Thuoc t on ct.maThuoc= t.maThuoc join HoaDon h on  ct.MaHoaDon=h.MaHoaDon\r\n" + 
					"					where month(NgayLap)=? and year(NgayLap)=?\r\n" + 
					"					group by  t.MaThuoc,t.TenThuoc,t.DonGia,t.GiaNhap,CONVERT (nvarchar(10), h.NgayLap, 103),t.[SoDangKi]\r\n" + 
					"					";
			pre = con.prepareStatement(sql);
			pre.setInt(1, thang);
			pre.setInt(2, nam);
			rs = pre.executeQuery();
			while (rs.next()) {
				tongTien += rs.getInt(8) * rs.getDouble(4);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return tongTien;
	}
	
	
	
	public List<ThongKeBaoCaoTQ> thongKeThuocDaBan_DoanhThu_TheoThang(int thang, int nam) throws Exception {
		List<ThongKeBaoCaoTQ> dsThuoc = new ArrayList<>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql = "								select t.MaThuoc,t.TenThuoc,t.DonGia,t.GiaNhap,CONVERT (nvarchar(10), h.NgayLap, 103) as HanSuDung,t.[SoDangKi],SUM(t.SoLuongNhap) AS SoLuongNhap,sum(ct.SoLuong) as SoLuongBan,sum(ct.SoLuong*ct.DonGia) as TienBan,sum(ct.SoLuong*t.GiaNhap) as TienNhap\r\n" + 
					"					from CT_HoaDon ct join Thuoc t on ct.maThuoc= t.maThuoc join HoaDon h on  ct.MaHoaDon=h.MaHoaDon\r\n" + 
					"					where month(NgayLap)=? and year(NgayLap)=?\r\n" + 
					"					group by  t.MaThuoc,t.TenThuoc,t.DonGia,t.GiaNhap,CONVERT (nvarchar(10), h.NgayLap, 103),t.[SoDangKi]\r\n" + 
					"					";
			pre = con.prepareStatement(sql);
			pre.setInt(1, thang);
			pre.setInt(2, nam);
			rs = pre.executeQuery();
			while (rs.next()) {
				ThongKeBaoCaoTQ thuoc = new ThongKeBaoCaoTQ();
				thuoc.setMaThuoc(rs.getString(1));
				thuoc.setTenThuoc(rs.getString(2));
				thuoc.setDonGia(rs.getDouble(3));
				thuoc.setDonGiaNhap(rs.getDouble(4));
				thuoc.setNgay(rs.getString(5));
				thuoc.setSoDK(rs.getString(6));
				thuoc.setSoLuongNhap(rs.getInt(7));
				thuoc.setSoLuongBan(rs.getInt(8));
				thuoc.setLoiNhuan(rs.getDouble(9));
				thuoc.setTienThuocNhap(rs.getDouble(10));
				dsThuoc.add(thuoc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return dsThuoc;
			
		
	}
	

}
