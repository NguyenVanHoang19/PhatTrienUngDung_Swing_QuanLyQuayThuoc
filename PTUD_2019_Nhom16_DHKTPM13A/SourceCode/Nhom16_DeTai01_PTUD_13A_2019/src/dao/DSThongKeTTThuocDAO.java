package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.ConectDatabase;
import entities.ThongKeHDTheoNhanVien;
import entities.ThongKeTinhTrangThuoc;
import entities.Thuoc;
import gui.FrmBaoCaoThongKe;



public class DSThongKeTTThuocDAO {
	ArrayList<ThongKeTinhTrangThuoc> ds;
	ThongKeTinhTrangThuoc tkttt;
	PreparedStatement pre;
	ResultSet rs;
	public DSThongKeTTThuocDAO() {
		ds = new ArrayList<ThongKeTinhTrangThuoc>();
		tkttt = new ThongKeTinhTrangThuoc();
	}
	
	public List<Thuoc> danhSachThuocHetHan(java.util.Date ngayHetHan) {
		List<Thuoc> dsThuoc = new ArrayList<>();
		try {
			ConectDatabase.getInstance().connect();
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql  = "select [MaThuoc],[TenThuoc],[PhanLoai],[SoLuongNhap],[NgaySanXuat],[HanSuDung]\r\n" + 
					"from [dbo].[Thuoc]";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while(rs.next()) {
				Thuoc thuoc = new Thuoc();
				thuoc.setMaThuoc(rs.getInt(1));
				thuoc.setTenThuoc(rs.getString(2));
				thuoc.setPhanLoai(rs.getString(3));
				thuoc.setSoLuongNhap(rs.getInt(4));
				thuoc.setNgaySanXuat(rs.getDate(5));
				thuoc.setHanSuDung(rs.getDate(6));
				if(thuoc.getHanSuDung().before(ngayHetHan)) {
					dsThuoc.add(thuoc);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dsThuoc;
	}
	
	public List<Thuoc> danhSachThuocConLai(java.util.Date ngay) {
		List<Thuoc> dsThuoc = new ArrayList<>();
		try {
			ConectDatabase.getInstance().connect();
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql  = "select [MaThuoc],[TenThuoc],[PhanLoai],[SoLuongNhap],[NgaySanXuat],[HanSuDung]\r\n" + 
					"from [dbo].[Thuoc]";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while(rs.next()) {
				Thuoc thuoc = new Thuoc();
				thuoc.setMaThuoc(rs.getInt(1));
				thuoc.setTenThuoc(rs.getString(2));
				thuoc.setPhanLoai(rs.getString(3));
				thuoc.setSoLuongNhap(rs.getInt(4));
				thuoc.setNgaySanXuat(rs.getDate(5));
				thuoc.setHanSuDung(rs.getDate(6));
				if(thuoc.getHanSuDung().after(ngay)) {
					dsThuoc.add(thuoc);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dsThuoc;
	}
	
	
	
	
	
	
	
	public int tinhTongLoaiThuocDaBan(int ngay,int thang, int nam) throws Exception {
		int tongLoaiThuoc = 0;
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql = "select COUNT(distinct ct.MaThuoc) as TongThuocDaBan\r\n" + 
					"		from CT_HoaDon ct join HoaDon h on ct.MaHoaDon=h.MaHoaDon\r\n" + 
					"		where day(h.[NgayLap]) =?  and month(h.[NgayLap]) = ? and year(h.[NgayLap]) =?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, ngay);
			pre.setInt(2, thang);
			pre.setInt(3, nam);
			rs = pre.executeQuery();
			
			while (rs.next()) {
				tongLoaiThuoc = rs.getInt(1);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return tongLoaiThuoc;
	}
	
	
	
	
	//Thống kê Danh sách đã bán theo Ngày
	public void danhsachThuocDaBan(int ngay,int thang,int nam) {
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select t.MaThuoc,t.TenThuoc,t.PhanLoai,ct.[SoLuong],CONVERT (nvarchar(10), t.NgaySanXuat, 103) as NgaySanXuat,CONVERT (nvarchar(10) , t.HanSuDung, 103) as HanSuDung,CONVERT (nvarchar(10), h.[NgayLap], 103) as NgayLap \r\n" + 
					"		from CT_HoaDon ct left join Thuoc t on t.MaThuoc = ct.MaThuoc  join HoaDon h on h.MaHoaDon=ct.MaHoaDon\r\n" + 
					"		where ct.MaHoaDon is not null and day(h.[NgayLap]) =?  and month(h.[NgayLap]) = ? and year(h.[NgayLap]) =?\r\n" + 
					"		group by t.MaThuoc,t.TenThuoc,t.PhanLoai,ct.[SoLuong], CONVERT (nvarchar(10), t.NgaySanXuat, 103),CONVERT (nvarchar(10) , t.HanSuDung, 103),h.[NgayLap]\r\n" + 
					"";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,ngay);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			ResultSet rs = stmt.executeQuery();
			int i=0;
			String maHoaDonSoSanh= "";
			Object [] ds = null;
			int tongSLT = 0;
			int checkNull=0;
			while(rs.next()) {
				//if(rs.getString(1).equalsIgnoreCase(maHoaDonSoSanh)==false) {
					++i;
					String stt = i +"";
					ds = new String [] { stt ,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)}; 
					//maHoaDonSoSanh = rs.getString(1);
					FrmBaoCaoThongKe.tablemodel1.addRow( ds);
					tongSLT += rs.getDouble(4);
					checkNull++;
					
				//}	
			}
			if(checkNull==0) {
				JOptionPane.showMessageDialog(null,"Không có dữ liệu của ngày:"+ngay+"/"+thang+"/"+nam);
			}
			FrmBaoCaoThongKe.txtTongSoLuongThuoc.setText(tongSLT+"");
			
		
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	
// Thống kê Danh sách thuốc trong kho
	public void danhsachThuocConLaiTrongKho() {
		DecimalFormat tien = new DecimalFormat("#,##0.00");
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select t.MaThuoc,t.TenThuoc,t.PhanLoai,t.SoLuongNhap,CONVERT (nvarchar(10), t.NgaySanXuat, 103) as NgaySanXuat,CONVERT (nvarchar(10), t.HanSuDung, 103) as HanSuDung\r\n" + 
					"		from  Thuoc t\r\n" + 
					"		where t.SoLuongNhap>0 \r\n" + 
					"		group by t.MaThuoc,t.TenThuoc,t.PhanLoai,t.SoLuongNhap,CONVERT (nvarchar(10), t.NgaySanXuat, 103),CONVERT (nvarchar(10), t.HanSuDung, 103)";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int i=0;
			Object [] ds = null;
			int checkNull=0;
			int tongSoLuongThuocNhap =0;
			while(rs.next()) {
					++i;
					String stt = i +"";
					ds = new String [] { stt ,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)}; 
					FrmBaoCaoThongKe.tablemodel1.addRow( ds);
					tongSoLuongThuocNhap +=rs.getInt(4);
					checkNull++;
			}
			if(checkNull==0) {
				JOptionPane.showMessageDialog(null,"Không còn thuốc trong kho ngày này");
			}
		
			FrmBaoCaoThongKe.txtTongSoLoaiThuoc.setText(i + "");
			FrmBaoCaoThongKe.txtTongSoLuongThuoc.setText(tongSoLuongThuocNhap+"");
		
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		DSThongKeTTThuocDAO dsThongKeTTThuoc = new DSThongKeTTThuocDAO();
		LocalDate localDate = LocalDate.of(2019, 12, 11);
		Date date = Date.valueOf("2019-12-11");
		System.out.println(localDate);
		System.out.println(date);
//		dsThongKeTTThuoc.danhSachThuocHetHan(date).forEach(x->{
//			System.out.println(x);
//		});
		
	}
}
