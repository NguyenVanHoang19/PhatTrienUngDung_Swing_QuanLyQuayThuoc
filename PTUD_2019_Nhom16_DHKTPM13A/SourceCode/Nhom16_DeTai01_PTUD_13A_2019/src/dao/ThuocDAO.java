/**
 * Người làm: Nguyễn Đình Quốc
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.ConectDatabase;
import entities.DiaChi;
import entities.NhaCungCap;
import entities.Thuoc;
import main.Main;

public class ThuocDAO {
	public ThuocDAO() {
		// TODO Auto-generated constructor stub
		ConectDatabase.getInstance().connect();
	}
	/**
	 * lấy dữ liệu từ cơ sở dữ liệu 
	 * @return danh sách thuốc được lấy từ cơ sỡ dữ liệu
	 */
	public List<Thuoc> getThuoc() {
		List<Thuoc> list= new ArrayList<>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [MaThuoc],[SoDangKi],[TenThuoc],[PhanLoai],[HoatChat],[HamLuong],[DangBaoChe],[QuyCachDongGoi],[TieuChuan],n.Ten,[NgaySanXuat],[HanSuDung],[DonViTinh],[GiaNhap],[DonGia],[SoLuongNhap],[HinhAnh],[TrangThai],[NhomThuoc]\r\n" + 
					"from [dbo].[Thuoc] t join [dbo].[NhaCungCap] n on t.MaNhaCungCap=n.MaNhaCungCap";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
				int ma=rs.getInt(1);	
				String soDangKi= rs.getString(2);
				String tenThuoc= rs.getString(3);
				String phanLoai=rs.getString(4);
				String hoatChat= rs.getString(5);
				String hamLuong= rs.getString(6);
				String dangBaoChe= rs.getString(7);
				String quyCach= rs.getString(8);
				String tieuChuan= rs.getString(9);
				String nhaCungCap= rs.getString(10);
				Date ngaySanXuat= rs.getDate(11); 	
				Date hanSuDung= rs.getDate(12);
				String donViTinh= rs.getString(13);
				float giaNhap=rs.getFloat(14);
				float donGia=rs.getFloat(15);
				int sl= rs.getInt(16);
				byte[] hinhAnh=rs.getBytes(17);
				String trangThai=rs.getString(18);
				String nhomThuoc=rs.getString(19);
				NhaCungCap ncc= new NhaCungCap(nhaCungCap);
				Thuoc thuoc = new Thuoc(ma, soDangKi, tenThuoc,nhomThuoc, phanLoai,hoatChat, hamLuong, dangBaoChe, quyCach, tieuChuan, ncc, ngaySanXuat, hanSuDung, donViTinh, giaNhap, donGia, sl, hinhAnh,trangThai);
				list.add(thuoc);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * Thêm dữ liệu vào cơ sở dữ liệu
	 * @param soDangKi
	 * @param tenThuoc
	 * @param phanLoai
	 * @param nhomThuoc
	 * @param hoatChat
	 * @param hamLuong
	 * @param dangBaoChe
	 * @param quyCach
	 * @param tieuChuan
	 * @param ncc
	 * @param ngaySanXuat
	 * @param hanSuDung
	 * @param donViTinh
	 * @param giaNhap
	 * @param donGia
	 * @param soLuong
	 * @param hinhAnh
	 * @param trangThai
	 * @return true nếu thành công dữ liệu vào cơ sở dữ liệu
	 * @throws ParseException
	 */
	public boolean them(String soDangKi,String tenThuoc,String phanLoai,String nhomThuoc,String hoatChat,String hamLuong,String dangBaoChe,String quyCach,String tieuChuan,int ncc,Date ngaySanXuat,Date hanSuDung,String donViTinh,float giaNhap,float donGia,int soLuong,byte[] hinhAnh,String trangThai) throws ParseException {
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		Connection con = ConectDatabase.getInstance().getConnection();
		String sql="Insert into [dbo].[Thuoc] values\r\n" + 
				"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1,soDangKi );
			stmt.setString(2,tenThuoc );
			stmt.setString(3,phanLoai );
			stmt.setString(4,nhomThuoc );
			stmt.setString(5,hoatChat );
			stmt.setString(6,hamLuong );
			stmt.setString(7,dangBaoChe );
			stmt.setString(8,quyCach );
			stmt.setString(9,tieuChuan );
			stmt.setString(10,String.valueOf(ncc) );
			java.sql.Date nsx = new java.sql.Date(ngaySanXuat.getTime());
			java.sql.Date hsd = new java.sql.Date(hanSuDung.getTime());
			stmt.setDate(11, nsx);
			stmt.setDate(12, hsd);
			stmt.setString(13,donViTinh );
			stmt.setString(14,String.valueOf(giaNhap) );
			stmt.setString(15,String.valueOf(giaNhap) );
			stmt.setString(16,String.valueOf(soLuong) );
			stmt.setBytes(17, hinhAnh);
			stmt.setString(18,trangThai);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}
	/**
	 * Sửa dữ liệu
	 * @param ma
	 * @param soDangKi
	 * @param tenThuoc
	 * @param phanLoai
	 * @param nhomThuoc
	 * @param hoatChat
	 * @param hamLuong
	 * @param dangBaoChe
	 * @param quyCach
	 * @param tieuChuan
	 * @param ncc
	 * @param ngaySanXuat
	 * @param hanSuDung
	 * @param donViTinh
	 * @param giaNhap
	 * @param donGia
	 * @param soLuong
	 * @param hinhAnh
	 * @param trangThai
	 * @return true nếu thành công
	 * @throws ParseException
	 */
	public boolean sua(int ma,String soDangKi,String tenThuoc,String phanLoai,String nhomThuoc,String hoatChat,String hamLuong,String dangBaoChe,String quyCach,String tieuChuan,int ncc,Date ngaySanXuat,Date hanSuDung,String donViTinh,float giaNhap,float donGia,int soLuong,byte[] hinhAnh,String trangThai) throws ParseException {
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		Connection con = ConectDatabase.getInstance().getConnection();
		String sql="Update [dbo].[Thuoc]\r\n" + 
				"set [SoDangKi]=?,[TenThuoc]=?,[PhanLoai]=?,[NhomThuoc] = ?, [HoatChat]=?,[HamLuong]=?,[DangBaoChe]=?,[QuyCachDongGoi]=?,[TieuChuan]=?,[MaNhaCungCap]=?,[NgaySanXuat]=?,[HanSuDung]=?,[DonViTinh]=?,[GiaNhap]=?,[DonGia]=?,[SoLuongNhap]=?,[HinhAnh]=?,[TrangThai]=?\r\n" + 
				"where [MaThuoc]=?";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1,soDangKi );
			stmt.setString(2,tenThuoc );
			stmt.setString(3,phanLoai );
			stmt.setString(4,nhomThuoc );
			stmt.setString(5,hoatChat );
			stmt.setString(6,hamLuong );
			stmt.setString(7,dangBaoChe );
			stmt.setString(8,quyCach );
			stmt.setString(9,tieuChuan );
			stmt.setString(10,String.valueOf(ncc) );
			java.sql.Date nsx = new java.sql.Date(ngaySanXuat.getTime());
			java.sql.Date hsd = new java.sql.Date(hanSuDung.getTime());
			stmt.setDate(11, nsx);
			stmt.setDate(12, hsd);
			stmt.setString(13,donViTinh );
			stmt.setString(14,String.valueOf(giaNhap) );
			stmt.setString(15,String.valueOf(giaNhap) );
			stmt.setString(16,String.valueOf(soLuong) );
			stmt.setBytes(17, hinhAnh);
			stmt.setString(18,trangThai);
			stmt.setInt(19,ma);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}
	/**
	 * Xóa thuốc khi biết mã
	 * @param ma
	 */
	public void xoa(int ma) {
		Connection con = ConectDatabase.getInstance().getConnection();
		String qrl = "update [dbo].[Thuoc]\r\n" + 
				"set [TrangThai]=N'Ngừng bán'\r\n" + 
				"where [MaThuoc]=?";
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(qrl);
			stm.setInt(1, ma);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Lấy danh sách tên nhà cung cấp
	 * @return danh sách tên nhà cung cấp
	 */
	public List<String> getTenCungCaps(){
		List<String> list = new ArrayList<String>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [Ten]\r\n" + 
					"from [dbo].[NhaCungCap]";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String t= rs.getString(1);
				list.add(t);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	/**
	 * Lấy mã nhà cung cấp khi biết tên
	 * @param ten
	 * @return ten nhà cung cấp
	 */
	public int getmaNCC(String ten){
		int ma=0;
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [MaNhaCungCap]\r\n" + 
					"from [dbo].[NhaCungCap]\r\n" + 
					"where [Ten] like N'"+ten+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int t= rs.getInt(1);
				ma=t;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ma;
	}
	/**
	 * Lấy dữ liệu thuốc bằng mã
	 * @param maTim
	 * @return thuốc
	 */
	public Thuoc getThuocbyMa(int maTim) {
		Thuoc t= new Thuoc();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [MaThuoc],[SoDangKi],[TenThuoc],[PhanLoai],[HoatChat],[HamLuong],[DangBaoChe],[QuyCachDongGoi],[TieuChuan],n.Ten,[NgaySanXuat],[HanSuDung],[DonViTinh],[GiaNhap],[DonGia],[SoLuongNhap],[HinhAnh],[TrangThai],[NhomThuoc]\r\n" + 
					"from [dbo].[Thuoc] t join [dbo].[NhaCungCap] n on t.MaNhaCungCap=n.MaNhaCungCap\r\n" + 
					"where [MaThuoc] = "+maTim+"";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
				int ma=rs.getInt(1);	
				String soDangKi= rs.getString(2);
				String tenThuoc= rs.getString(3);
				String phanLoai=rs.getString(4);
				String hoatChat= rs.getString(5);
				String hamLuong= rs.getString(6);
				String dangBaoChe= rs.getString(7);
				String quyCach= rs.getString(8);
				String tieuChuan= rs.getString(9);
				String nhaCungCap= rs.getString(10);
				Date ngaySanXuat= rs.getDate(11); 	
				Date hanSuDung= rs.getDate(12);
				String donViTinh= rs.getString(13);
				float giaNhap=rs.getFloat(14);
				float donGia=rs.getFloat(15);
				int sl= rs.getInt(16);
				byte[] hinhAnh=rs.getBytes(17);
				String trangThai=rs.getString(18);
				String nhomThuoc=rs.getString(19);
				NhaCungCap ncc= new NhaCungCap(nhaCungCap);
				Thuoc thuoc = new Thuoc(ma, soDangKi, tenThuoc, phanLoai,nhomThuoc,hoatChat, hamLuong, dangBaoChe, quyCach, tieuChuan, ncc, ngaySanXuat, hanSuDung, donViTinh, giaNhap, donGia, sl, hinhAnh,trangThai);
				t=thuoc;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return t;
	}
	/**
	 * Lấy dữ liệu thuốc khi biết tên thuốc
	 * @param tenTim
	 * @return danh sách thuốc thỏa mãn điều kiện 
	 */
	public List<Thuoc> getThuocbyTen(String tenTim) {
		List<Thuoc> list= new ArrayList<Thuoc>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [MaThuoc],[SoDangKi],[TenThuoc],[PhanLoai],[HoatChat],[HamLuong],[DangBaoChe],[QuyCachDongGoi],[TieuChuan],n.Ten,[NgaySanXuat],[HanSuDung],[DonViTinh],[GiaNhap],[DonGia],[SoLuongNhap],[HinhAnh],[TrangThai],[NhomThuoc]\r\n" + 
					"from [dbo].[Thuoc] t join [dbo].[NhaCungCap] n on t.MaNhaCungCap=n.MaNhaCungCap\r\n" + 
					"where [TenThuoc] like N'%"+tenTim+"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
				int ma=rs.getInt(1);	
				String soDangKi= rs.getString(2);
				String tenThuoc= rs.getString(3);
				String phanLoai=rs.getString(4);
				String hoatChat= rs.getString(5);
				String hamLuong= rs.getString(6);
				String dangBaoChe= rs.getString(7);
				String quyCach= rs.getString(8);
				String tieuChuan= rs.getString(9);
				String nhaCungCap= rs.getString(10);
				Date ngaySanXuat= rs.getDate(11); 	
				Date hanSuDung= rs.getDate(12);
				String donViTinh= rs.getString(13);
				float giaNhap=rs.getFloat(14);
				float donGia=rs.getFloat(15);
				int sl= rs.getInt(16);
				byte[] hinhAnh=rs.getBytes(17);
				String trangThai=rs.getString(18);
				String nhomThuoc=rs.getString(19);
				NhaCungCap ncc= new NhaCungCap(nhaCungCap);
				Thuoc thuoc = new Thuoc(ma, soDangKi, tenThuoc, phanLoai,nhomThuoc,hoatChat, hamLuong, dangBaoChe, quyCach, tieuChuan, ncc, ngaySanXuat, hanSuDung, donViTinh, giaNhap, donGia, sl, hinhAnh,trangThai);
				list.add(thuoc);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * Lấy dữ liệu thuốc khi biết nhóm thuốc
	 * @param nhomThuocTim
	 * @return danh sách thuốc thỏa mãn điều kiện 
	 */ 
	public List<Thuoc> getThuocbynhomThuoc(String nhomThuocTim) {
		List<Thuoc> list= new ArrayList<Thuoc>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [MaThuoc],[SoDangKi],[TenThuoc],[PhanLoai],[HoatChat],[HamLuong],[DangBaoChe],[QuyCachDongGoi],[TieuChuan],n.Ten,[NgaySanXuat],[HanSuDung],[DonViTinh],[GiaNhap],[DonGia],[SoLuongNhap],[HinhAnh],[TrangThai],[NhomThuoc]\r\n" + 
					"from [dbo].[Thuoc] t join [dbo].[NhaCungCap] n on t.MaNhaCungCap=n.MaNhaCungCap\r\n" + 
					"where [NhomThuoc] like N'%"+nhomThuocTim+"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
				int ma=rs.getInt(1);	
				String soDangKi= rs.getString(2);
				String tenThuoc= rs.getString(3);
				String phanLoai=rs.getString(4);
				String hoatChat= rs.getString(5);
				String hamLuong= rs.getString(6);
				String dangBaoChe= rs.getString(7);
				String quyCach= rs.getString(8);
				String tieuChuan= rs.getString(9);
				String nhaCungCap= rs.getString(10);
				Date ngaySanXuat= rs.getDate(11); 	
				Date hanSuDung= rs.getDate(12);
				String donViTinh= rs.getString(13);
				float giaNhap=rs.getFloat(14);
				float donGia=rs.getFloat(15);
				int sl= rs.getInt(16);
				byte[] hinhAnh=rs.getBytes(17);
				String trangThai=rs.getString(18);
				String nhomThuoc=rs.getString(19);
				NhaCungCap ncc= new NhaCungCap(nhaCungCap);
				Thuoc thuoc = new Thuoc(ma, soDangKi, tenThuoc, phanLoai,nhomThuoc,hoatChat, hamLuong, dangBaoChe, quyCach, tieuChuan, ncc, ngaySanXuat, hanSuDung, donViTinh, giaNhap, donGia, sl, hinhAnh,trangThai);
				list.add(thuoc);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * Lấy dữ liệu thuốc khi biết phân loại của thuốc
	 * @param phanLoaiTim
	 * @return danh sách thuốc thỏa mãn điều kiện 
	 */
	public List<Thuoc> getThuocbyPhanLoai(String phanLoaiTim) {
		List<Thuoc> list= new ArrayList<Thuoc>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [MaThuoc],[SoDangKi],[TenThuoc],[PhanLoai],[HoatChat],[HamLuong],[DangBaoChe],[QuyCachDongGoi],[TieuChuan],n.Ten,[NgaySanXuat],[HanSuDung],[DonViTinh],[GiaNhap],[DonGia],[SoLuongNhap],[HinhAnh],[TrangThai],[NhomThuoc]\r\n" + 
					"from [dbo].[Thuoc] t join [dbo].[NhaCungCap] n on t.MaNhaCungCap=n.MaNhaCungCap\r\n" + 
					"where [PhanLoai] like N'%"+phanLoaiTim+"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
				int ma=rs.getInt(1);	
				String soDangKi= rs.getString(2);
				String tenThuoc= rs.getString(3);
				String phanLoai=rs.getString(4);
				String hoatChat= rs.getString(5);
				String hamLuong= rs.getString(6);
				String dangBaoChe= rs.getString(7);
				String quyCach= rs.getString(8);
				String tieuChuan= rs.getString(9);
				String nhaCungCap= rs.getString(10);
				Date ngaySanXuat= rs.getDate(11); 	
				Date hanSuDung= rs.getDate(12);
				String donViTinh= rs.getString(13);
				float giaNhap=rs.getFloat(14);
				float donGia=rs.getFloat(15);
				int sl= rs.getInt(16);
				byte[] hinhAnh=rs.getBytes(17);
				String trangThai=rs.getString(18);
				String nhomThuoc=rs.getString(19);
				NhaCungCap ncc= new NhaCungCap(nhaCungCap);
				Thuoc thuoc = new Thuoc(ma, soDangKi, tenThuoc, phanLoai,nhomThuoc,hoatChat, hamLuong, dangBaoChe, quyCach, tieuChuan, ncc, ngaySanXuat, hanSuDung, donViTinh, giaNhap, donGia, sl, hinhAnh,trangThai);
				list.add(thuoc);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * Lấy dữ liệu thuốc khi biết tên nhà cung cấp
	 * @param nccTim
	 * @return danh sách thuốc thỏa mãn điều kiện 
	 */
	public List<Thuoc> getThuocbynhaCungCap(String nccTim) {
		List<Thuoc> list= new ArrayList<Thuoc>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select [MaThuoc],[SoDangKi],[TenThuoc],[PhanLoai],[HoatChat],[HamLuong],[DangBaoChe],[QuyCachDongGoi],[TieuChuan],n.Ten,[NgaySanXuat],[HanSuDung],[DonViTinh],[GiaNhap],[DonGia],[SoLuongNhap],[HinhAnh],[TrangThai],[NhomThuoc]\r\n" + 
					"from [dbo].[Thuoc] t join [dbo].[NhaCungCap] n on t.MaNhaCungCap=n.MaNhaCungCap\r\n" + 
					"where  n.Ten like N'%"+nccTim+"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
				int ma=rs.getInt(1);	
				String soDangKi= rs.getString(2);
				String tenThuoc= rs.getString(3);
				String phanLoai=rs.getString(4);
				String hoatChat= rs.getString(5);
				String hamLuong= rs.getString(6);
				String dangBaoChe= rs.getString(7);
				String quyCach= rs.getString(8);
				String tieuChuan= rs.getString(9);
				String nhaCungCap= rs.getString(10);
				Date ngaySanXuat= rs.getDate(11); 	
				Date hanSuDung= rs.getDate(12);
				String donViTinh= rs.getString(13);
				float giaNhap=rs.getFloat(14);
				float donGia=rs.getFloat(15);
				int sl= rs.getInt(16);
				byte[] hinhAnh=rs.getBytes(17);
				String trangThai=rs.getString(18);
				String nhomThuoc=rs.getString(19);
				NhaCungCap ncc= new NhaCungCap(nhaCungCap);
				Thuoc thuoc = new Thuoc(ma, soDangKi, tenThuoc, phanLoai,nhomThuoc,hoatChat, hamLuong, dangBaoChe, quyCach, tieuChuan, ncc, ngaySanXuat, hanSuDung, donViTinh, giaNhap, donGia, sl, hinhAnh,trangThai);
				list.add(thuoc);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
