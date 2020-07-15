package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import database.ConectDatabase;
import entities.ThongKeBaoCaoTQ;
import entities.ThongKeHDTheoNhanVien;
import gui.FrmBaoCaoThongKe;
import gui.FrmXuatHD;



public class DSThongKeHDTheoNhanVienDAO {
	ArrayList<ThongKeHDTheoNhanVien> ds;
	ThongKeHDTheoNhanVien tknv;
	public DSThongKeHDTheoNhanVienDAO()
	{
		ds= new ArrayList<ThongKeHDTheoNhanVien>();
	}

	public ArrayList<ThongKeHDTheoNhanVien> docBang(String ngayBan){
		try {
			// dang ky driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// thiet lap ke noi
			Connection con = ConectDatabase.getInstance().getConnection();
			//Connection con = Database.getConnection();
			CallableStatement command = con.prepareCall("{call ThongKeNhanVien(?)}");
			command.setString(1, ngayBan);

			// ResultSet rs = command.executeQuery();
			ResultSet rs = command.executeQuery();
			// duyet ket qua
			while (rs.next()) {
				String maThuoc = rs.getString(1);
				String tenThuoc = rs.getString(2);
				String soLuong= rs.getString(3);
				String  dongGia = rs.getString(4);
				String maNhanVien =rs.getString(5);
				String tenNhanVien = rs.getString(6);
				String caLam = rs.getString(7);
				String ngay = rs.getString(8);
				String thanhTien = rs.getString(9);
				ThongKeHDTheoNhanVien tk = new ThongKeHDTheoNhanVien();
				DecimalFormat df=new DecimalFormat("#,###.0(VND)");
				//		(String ngay, String maNhanVien, String tenNhanVien, String caLam, String donthuoc,String maThuoc, String tenThuoc, int soLuong, double donGia, double loiNhuan) {
				ThongKeHDTheoNhanVien s = new ThongKeHDTheoNhanVien(ngay,maNhanVien, tenNhanVien,caLam, tk.getDonthuoc(),maThuoc,tenThuoc, Integer.parseInt(soLuong), Double.parseDouble(dongGia),Double.parseDouble(thanhTien));
				ds.add(s);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ds;

	}


	public ArrayList<ThongKeHDTheoNhanVien> chonTenNhanVien(String ngayBan){
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = ConectDatabase.getInstance().getConnection();
			CallableStatement command = con.prepareCall("{call LocTenNhanVien(?)}");
			command.setString(1, ngayBan);

			ResultSet rs = command.executeQuery();
			while(rs.next())
			{
				String maNhanvVien = rs.getString(1);
				String tenNhanVien = rs.getString(2);
				ThongKeHDTheoNhanVien tk = new ThongKeHDTheoNhanVien();
				ThongKeHDTheoNhanVien s = new ThongKeHDTheoNhanVien(tk.getNgay(),maNhanvVien, tenNhanVien,tk.getCaLam(), tk.getDonthuoc(),tk.getMaThuoc(),tk.getTenThuoc(), tk.getSoLuong(),tk.getDonGia(),tk.getLoiNhuan());
				ds.add(s);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ds;

	}

	public ArrayList<ThongKeHDTheoNhanVien> chonMaNhanVien(){
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql ="select distinct MaNhanVien,TenNhanVien=nv.Ho+''+nv.Ten\r\n" + 
					"from NhanVien nv";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next())
			{
				String maNhanvVien = rs.getString(1);
				String tenNhanVien = rs.getString(2);
				ThongKeHDTheoNhanVien tk = new ThongKeHDTheoNhanVien();
				ThongKeHDTheoNhanVien s = new ThongKeHDTheoNhanVien(tk.getNgay(),maNhanvVien, tenNhanVien,tk.getCaLam(), tk.getDonthuoc(),tk.getMaThuoc(),tk.getTenThuoc(), tk.getSoLuong(),tk.getDonGia(),tk.getLoiNhuan());
				ds.add(s);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}



	public ArrayList<ThongKeHDTheoNhanVien> chonCaLam(){
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql ="select distinct CaLamViec\r\n" + 
					"from NhanVien";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next())
			{
				String caLam = rs.getString(1);

				ThongKeHDTheoNhanVien tk = new ThongKeHDTheoNhanVien();
				ThongKeHDTheoNhanVien s = new ThongKeHDTheoNhanVien (tk.getNgay(),tk.getMaNhanVien(), tk.getTenNhanVien(),caLam, tk.getDonthuoc(),tk.getMaThuoc(),tk.getTenThuoc(), tk.getSoLuong(),tk.getDonGia(),tk.getLoiNhuan());
				ds.add(s);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	public void thongKeNhanVienLapHoaDonTheoNgay(int ngay,int thang,int nam) {
		DecimalFormat tien = new DecimalFormat("###,###,### VND");
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select ct.[MaHoaDon],nv.[MaNhanVien],nv.[CaLamViec],CONVERT (nvarchar(10), h.[NgayLap], 103) as NgayLap ,h.[TongTien],t.[PhanLoai],sum(ct.[SoLuong])\r\n" + 
					"		from [dbo].[CT_HoaDon] ct left join [dbo].[Thuoc] t on ct.[MaThuoc]= t.[MaThuoc] left join [dbo].[HoaDon] h on h.[MaHoaDon]= ct.[MaHoaDon] left join [dbo].[NhanVien] nv on h.[MaNhanVien] = nv.[MaNhanVien]\r\n" + 
					"		where day(h.[NgayLap]) =?  and month(h.[NgayLap]) = ? and year(h.[NgayLap]) =?\r\n" + 
					"		group by ct.[MaHoaDon],nv.[MaNhanVien],nv.[CaLamViec],h.[NgayLap] ,h.[TongTien],t.[PhanLoai]";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,ngay);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			ResultSet rs = stmt.executeQuery();
			int i=0;
			String maHoaDonSoSanh= "";
			Object [] ds = null;
			int checkNull=0;
			double tongTienDaBan = 0;
			int tongSoLuongThuocDaBan =0;
			while(rs.next()) {
				if(rs.getString(1).equalsIgnoreCase(maHoaDonSoSanh)==false) {
					++i;
					String stt = i +"";
					ds = new String [] { stt ,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),tien.format(rs.getDouble(5)),rs.getString(6),rs.getString(7)}; 
					maHoaDonSoSanh = rs.getString(1);
					FrmBaoCaoThongKe.tablemodel.addRow( ds);
					tongTienDaBan += rs.getDouble(5);
					tongSoLuongThuocDaBan +=rs.getInt(7);
					 checkNull++;
				}	
			}
		if(checkNull==0) {
				JOptionPane.showMessageDialog(null, "Không có dữ liệu của ngày:"+ngay+"/"+thang+"/"+nam);
			}
			
			FrmBaoCaoThongKe.txtTongTienDaBan.setText(tien.format(tongTienDaBan));
			FrmBaoCaoThongKe.txtTongSoHD.setText(i+"");
			FrmBaoCaoThongKe.txtTongSLTDB.setText(tongSoLuongThuocDaBan +"");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
//	public void thongKeNhanVienLapHoaDonKhongKeDonTheoNgay(int ngay,int thang,int nam) {
//		try {
//			Connection con = ConectDatabase.getInstance().getConnection();
//			PreparedStatement stmt = null;
//			String sql = "select ct.[MaHoaDon],nv.[MaNhanVien],nv.[CaLamViec],CONVERT (nvarchar(10), h.[NgayLap], 103) as NgayLap  ,h.[TongTien],t.[PhanLoai],sum(ct.[SoLuong])\r\n" + 
//					"					from [dbo].[CT_HoaDon] ct left join [dbo].[Thuoc] t on ct.[MaThuoc]= t.[MaThuoc] left join [dbo].[HoaDon] h on h.[MaHoaDon]= ct.[MaHoaDon] left join [dbo].[NhanVien] nv on h.[MaNhanVien] = nv.[MaNhanVien]\r\n" + 
//					"					where day(h.[NgayLap]) =?  and month(h.[NgayLap]) = ? and year(h.[NgayLap]) =? and h.MaKhachHang is null\r\n" + 
//					"					group by ct.[MaHoaDon],nv.[MaNhanVien],nv.[CaLamViec],h.[NgayLap] ,h.[TongTien],t.[PhanLoai]";
//			stmt = con.prepareStatement(sql);
//			stmt.setInt(1,ngay);
//			stmt.setInt(2, thang);
//			stmt.setInt(3, nam);
//			ResultSet rs = stmt.executeQuery();
//			int i=0;
//			String maHoaDonSoSanh= "";
//			Object [] ds = null;
//			double tongTienDaBan = 0;
//			int checkNull=0;
//			int tongSoLuongThuocDaBan =0;
//			while(rs.next()) {
//				if(rs.getString(1).equalsIgnoreCase(maHoaDonSoSanh)==false) {
//					++i;
//					String stt = i +"";
//					ds = new String [] { stt ,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)}; 
//					maHoaDonSoSanh = rs.getString(1);
//					FrmBaoCaoThongKe.tablemodel.addRow( ds);
//					tongTienDaBan += rs.getDouble(5);
//					tongSoLuongThuocDaBan +=rs.getInt(7);
//				}	
//			}
//			
//			FrmBaoCaoThongKe.txtTongTienDaBan.setText(tongTienDaBan + "");
//			FrmBaoCaoThongKe.txtTongSoHD.setText(i+"");
//			FrmBaoCaoThongKe.txtTongSLTDB.setText(tongSoLuongThuocDaBan +"");
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

//
//	public void thongKeNhanVienLapHoaDonKeDon_TheoNgay(int ngay,int thang,int nam) {
//		try {
//			Connection con = ConectDatabase.getInstance().getConnection();
//			PreparedStatement stmt = null;
//			String sql = "select ct.[MaHoaDon],nv.[MaNhanVien],nv.[CaLamViec],CONVERT (nvarchar(10), h.[NgayLap], 103) as NgayLap  ,h.[TongTien],t.[PhanLoai],sum(ct.[SoLuong])\r\n" + 
//					"					from [dbo].[CT_HoaDon] ct left join [dbo].[Thuoc] t on ct.[MaThuoc]= t.[MaThuoc] left join [dbo].[HoaDon] h on h.[MaHoaDon]= ct.[MaHoaDon] left join [dbo].[NhanVien] nv on h.[MaNhanVien] = nv.[MaNhanVien]\r\n" + 
//					"					where day(h.[NgayLap]) =?  and month(h.[NgayLap]) = ? and year(h.[NgayLap]) =? and h.MaKhachHang is not null\r\n" + 
//					"					group by ct.[MaHoaDon],nv.[MaNhanVien],nv.[CaLamViec],h.[NgayLap] ,h.[TongTien],t.[PhanLoai]";
//			stmt = con.prepareStatement(sql);
//			stmt.setInt(1,ngay);
//			stmt.setInt(2, thang);
//			stmt.setInt(3, nam);
//			ResultSet rs = stmt.executeQuery();
//			int i=0;
//			int checkNull=0;
//			String maHoaDonSoSanh= "";
//			Object [] ds = null;
//			double tongTienDaBan = 0;
//			int tongSoLuongThuocDaBan =0;
//			while(rs.next()) {
//				if(rs.getString(1).equalsIgnoreCase(maHoaDonSoSanh)==false) {
//					++i;
//					String stt = i +"";
//					ds = new String [] { stt ,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)}; 
//					maHoaDonSoSanh = rs.getString(1);
//					FrmBaoCaoThongKe.tablemodel.addRow( ds);
//					tongTienDaBan += rs.getDouble(5);
//					tongSoLuongThuocDaBan +=rs.getInt(7);
//				}	
//			
//			}
//			
//			FrmBaoCaoThongKe.txtTongTienDaBan.setText(tongTienDaBan + "");
//			FrmBaoCaoThongKe.txtTongSoHD.setText(i+"");
//			FrmBaoCaoThongKe.txtTongSLTDB.setText(tongSoLuongThuocDaBan +"");
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

	public void thongKeNhanVienLapHoaDonKeDon_TheoNgay_TheoMa(int ngay,int thang,int nam,int maNhanVien) {
		DecimalFormat tien = new DecimalFormat("###,###,### VND");
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "	select ct.[MaHoaDon],nv.[MaNhanVien],nv.[CaLamViec],CONVERT (nvarchar(10), h.[NgayLap], 103) as NgayLap ,h.[TongTien],t.[PhanLoai],sum(ct.[SoLuong])\r\n" + 
					"									from [dbo].[CT_HoaDon] ct left join [dbo].[Thuoc] t on ct.[MaThuoc]= t.[MaThuoc] left join [dbo].[HoaDon] h on h.[MaHoaDon]= ct.[MaHoaDon] left join [dbo].[NhanVien] nv on h.[MaNhanVien] = nv.[MaNhanVien]\r\n" + 
					"									where day(h.[NgayLap]) =?  and month(h.[NgayLap]) = ? and year(h.[NgayLap]) =? and h.MaKhachHang is not null \r\n" + 
					"									group by ct.[MaHoaDon],nv.[MaNhanVien],nv.[CaLamViec],h.[NgayLap] ,h.[TongTien],t.[PhanLoai]";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,ngay);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			ResultSet rs = stmt.executeQuery();
			int i=0;
			String maHoaDonSoSanh= "";
			Object [] ds = null;
			double tongTienDaBan = 0;
			int tongSoLuongThuocDaBan =0;
			int checkNull=0;
			while(rs.next()) {
				if(rs.getInt(2) == maNhanVien) {
					if(rs.getString(1).equalsIgnoreCase(maHoaDonSoSanh)==false) {
						++i;
						String stt = i +"";
						ds = new String [] { stt ,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),tien.format(rs.getDouble(5)),rs.getString(6),rs.getString(7)}; 
						maHoaDonSoSanh = rs.getString(1);
						FrmBaoCaoThongKe.tablemodel.addRow( ds);
						tongTienDaBan += rs.getDouble(5);
						tongSoLuongThuocDaBan +=rs.getInt(7);
						checkNull ++;
					}	
				}
			}
			if(checkNull==0) {
				JOptionPane.showMessageDialog(null, "Không có dữ liệu của ngày:"+ngay+"/"+thang+"/"+nam);
			}
			FrmBaoCaoThongKe.txtTongTienDaBan.setText(tien.format(tongTienDaBan));
			FrmBaoCaoThongKe.txtTongSoHD.setText(i+"");
			FrmBaoCaoThongKe.txtTongSLTDB.setText(tongSoLuongThuocDaBan +"");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void thongKeNhanVienLapHoaDonKeDon_TheoNgay_TheoMaKeDonKKeDon(int ngay,int thang,int nam,int maNhanVien) {
		DecimalFormat tien = new DecimalFormat("###,###,### VND");
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "	select ct.[MaHoaDon],nv.[MaNhanVien],nv.[CaLamViec],CONVERT (nvarchar(10), h.[NgayLap], 103) as NgayLap ,h.[TongTien],t.[PhanLoai],sum(ct.[SoLuong])\r\n" + 
					"									from [dbo].[CT_HoaDon] ct left join [dbo].[Thuoc] t on ct.[MaThuoc]= t.[MaThuoc] left join [dbo].[HoaDon] h on h.[MaHoaDon]= ct.[MaHoaDon] left join [dbo].[NhanVien] nv on h.[MaNhanVien] = nv.[MaNhanVien]\r\n" + 
					"									where day(h.[NgayLap]) =?  and month(h.[NgayLap]) = ? and year(h.[NgayLap]) =? and h.MaKhachHang is null \r\n" + 
					"									group by ct.[MaHoaDon],nv.[MaNhanVien],nv.[CaLamViec],h.[NgayLap] ,h.[TongTien],t.[PhanLoai]";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,ngay);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			ResultSet rs = stmt.executeQuery();
			int i=0;
			String maHoaDonSoSanh= "";
			Object [] ds = null;
			double tongTienDaBan = 0;
			int tongSoLuongThuocDaBan =0;
			int checkNull=0;
			while(rs.next()) {
				if(rs.getInt(2) == maNhanVien) {
					if(rs.getString(1).equalsIgnoreCase(maHoaDonSoSanh)==false) {
						++i;
						String stt = i +"";
						ds = new String [] { stt ,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),tien.format(rs.getDouble(5)),rs.getString(6),rs.getString(7)}; 
						maHoaDonSoSanh = rs.getString(1);
						FrmBaoCaoThongKe.tablemodel.addRow( ds);
						tongTienDaBan += rs.getDouble(5);
						tongSoLuongThuocDaBan +=rs.getInt(7);
						checkNull ++;
					}	
				}
			}
			if(checkNull==0) {
				JOptionPane.showMessageDialog(null, "Không có dữ liệu của ngày:"+ngay+"/"+thang+"/"+nam);
			}
			FrmBaoCaoThongKe.txtTongTienDaBan.setText(tien.format(tongTienDaBan));
			FrmBaoCaoThongKe.txtTongSoHD.setText(i+"");
			FrmBaoCaoThongKe.txtTongSLTDB.setText(tongSoLuongThuocDaBan +"");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	public void thongKeNhanVienLapHoaDon_TheoNgay_TheoMaTatCa(int ngay,int thang,int nam,int maNhanVien) {
		DecimalFormat tien = new DecimalFormat("###,###,### VND");
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "	select ct.[MaHoaDon],nv.[MaNhanVien],nv.[CaLamViec],CONVERT (nvarchar(10), h.[NgayLap], 103) as NgayLap ,h.[TongTien],t.[PhanLoai],sum(ct.[SoLuong])\r\n" + 
					"									from [dbo].[CT_HoaDon] ct left join [dbo].[Thuoc] t on ct.[MaThuoc]= t.[MaThuoc] left join [dbo].[HoaDon] h on h.[MaHoaDon]= ct.[MaHoaDon] left join [dbo].[NhanVien] nv on h.[MaNhanVien] = nv.[MaNhanVien]\r\n" + 
					"									where day(h.[NgayLap]) =?  and month(h.[NgayLap]) = ? and year(h.[NgayLap]) =?  \r\n" + 
					"									group by ct.[MaHoaDon],nv.[MaNhanVien],nv.[CaLamViec],h.[NgayLap] ,h.[TongTien],t.[PhanLoai]";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,ngay);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			ResultSet rs = stmt.executeQuery();
			int i=0;
			String maHoaDonSoSanh= "";
			Object [] ds = null;
			double tongTienDaBan = 0;
			int tongSoLuongThuocDaBan =0;
			int checkNull=0;
			while(rs.next()) {
				if(rs.getInt(2) == maNhanVien) {
					if(rs.getString(1).equalsIgnoreCase(maHoaDonSoSanh)==false) {
						++i;
						String stt = i +"";
						ds = new String [] { stt ,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),tien.format(rs.getDouble(5)),rs.getString(6),rs.getString(7)}; 
						maHoaDonSoSanh = rs.getString(1);
						FrmBaoCaoThongKe.tablemodel.addRow( ds);
						tongTienDaBan += rs.getDouble(5);
						tongSoLuongThuocDaBan +=rs.getInt(7);
						checkNull ++;
					}	
				}
			}
			if(checkNull==0) {
				JOptionPane.showMessageDialog(null,"Không có dữ liệu của ngày:"+ngay+"/"+thang+"/"+nam);
			}
			FrmBaoCaoThongKe.txtTongTienDaBan.setText(tien.format(tongTienDaBan));
			FrmBaoCaoThongKe.txtTongSoHD.setText(i+"");
			FrmBaoCaoThongKe.txtTongSLTDB.setText(tongSoLuongThuocDaBan +"");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	


	public void XemThongtinCTHoaDon(String maHD) {
		FrmBaoCaoThongKe.frmXuatHD.tableModel.addRow(new Object[] {

		});
		FrmBaoCaoThongKe.frmXuatHD.tableModel = (DefaultTableModel) FrmBaoCaoThongKe.frmXuatHD.table.getModel();
		FrmBaoCaoThongKe.frmXuatHD.tableModel.getDataVector().removeAllElements();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select t.[TenThuoc],t.[DonViTinh],ct.[DonGia],ct.[SoLuong],ct.[GiamGia],ct.[DonGia]*ct.[SoLuong]\r\n" + 
					"from [dbo].[CT_HoaDon] ct join [dbo].[HoaDon] h\r\n" + 
					"on ct.[MaHoaDon]=h.MaHoaDon join Thuoc t \r\n" + 
					"on t.MaThuoc=ct.MaThuoc\r\n" + 
					"where ct.MaHoaDon=?\r\n" + 
					"";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,maHD);
			ResultSet rs = stmt.executeQuery();
			int i=0;
			Object [] ds = null;
			while(rs.next()) {
				++i;
				ds = new String [] { i+"" ,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)}; 

				FrmBaoCaoThongKe.frmXuatHD.tableModel.addRow(ds);
			}	
			FrmBaoCaoThongKe.frmXuatHD.lblSL.setText(i+"");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}






	public void XemThongtinCTHoaDonn(String maHD) {
		DecimalFormat tien = new DecimalFormat("###,###,### VND");
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select ct.[MaHoaDon],t.[PhanLoai],CONVERT (nvarchar(10), h.[NgayLap], 103) as NgayLap,h.TongTien\r\n" + 
					"					from [dbo].[CT_HoaDon] ct join [dbo].[HoaDon] h\r\n" + 
					"					on ct.[MaHoaDon]=h.MaHoaDon join Thuoc t \r\n" + 
					"					on t.MaThuoc=ct.MaThuoc\r\n" + 
					"					where ct.MaHoaDon=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,maHD);
			ResultSet rs = stmt.executeQuery();
			int i=0;
			Object [] ds = null;
			boolean checkKeDon = false;
			double tienThuoc = 0;
			while(rs.next()) {
				//Frmrs.getString(1),rs.getString(2),rs.getString(3)}; 
				FrmBaoCaoThongKe.frmXuatHD.lblMaHD.setText(rs.getString(1));
				if(rs.getString(2).equalsIgnoreCase("Thuốc kê đơn")) {
					checkKeDon = true;
				}
				tienThuoc= rs.getDouble(4);
				FrmBaoCaoThongKe.frmXuatHD.lblNgayLap1.setText(rs.getString(3));
			}	
			if(checkKeDon==true) {
				FrmBaoCaoThongKe.frmXuatHD.lblLoaiHD1.setText("Thuốc kê đơn");
			}
			else 
				FrmBaoCaoThongKe.frmXuatHD.lblLoaiHD1.setText("Không kê đơn");
			FrmBaoCaoThongKe.frmXuatHD.lblTongTThuoc.setText(tien.format(tienThuoc));
			FrmBaoCaoThongKe.frmXuatHD.lblTongT.setText(tien.format(tienThuoc));
		}catch (Exception e) {
			// TODO: handle exception
		}
	}


	public void XemThongtinCTHoaDonnn(String maHD) {
		
		DecimalFormat tien = new DecimalFormat("###,###,### VND");
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "\r\n" + 
					"select HoTen=kh.[Ho]+''+kh.[Ten],CONVERT (nvarchar(10), kh.[NgaySinh], 103) as NgaySinh,kh.[GioiTinh],kh.[SoDienThoai],kh.[MaDiaChi],HoTen=nv.[Ho]+''+nv.[Ten]\r\n" + 
					"from [dbo].[CT_HoaDon] ct join [dbo].[HoaDon] h\r\n" + 
					"on ct.[MaHoaDon]=h.MaHoaDon join KhachHang kh on kh.MaKhachHang=h.MaKhachHang join Thuoc t \r\n" + 
					"on t.MaThuoc=ct.MaThuoc join [dbo].[NhanVien] nv on nv.[MaNhanVien]=h.MaNhanVien\r\n" + 
					"where h.MaHoaDon=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,maHD);
			ResultSet rs = stmt.executeQuery();
			int i=0;
			Object [] ds = null;
			boolean checkKeDon = false;
			double tienThuoc = 0;
			while(rs.next()) {
				//Frmrs.getString(1),rs.getString(2),rs.getString(3)}; 
				FrmBaoCaoThongKe.frmXuatHD.lblTenKH1.setText(rs.getString(1));
				FrmBaoCaoThongKe.frmXuatHD.lblNamSinh1.setText(rs.getString(2));
				FrmBaoCaoThongKe.frmXuatHD.lblGTinh1.setText(rs.getString(3));
				FrmBaoCaoThongKe.frmXuatHD.lblsdtkh1.setText(rs.getString(4));
				FrmBaoCaoThongKe.frmXuatHD.lblDCKH1.setText(rs.getString(5));
				FrmBaoCaoThongKe.frmXuatHD.lblNguoiBan.setText(rs.getString(6));
				FrmBaoCaoThongKe.frmXuatHD.lblNguoiMuaHang1.setText(rs.getString(1));
				
				
				
				if(rs.getString(2).equalsIgnoreCase("Thuốc kê đơn")) {
					checkKeDon = true;
				}
			}	
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

















}
