/**
 * Người làm: Nguyễn Đình Quốc
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.ConectDatabase;
import entities.DiaChi;
import entities.NhaCungCap;

public class NhaCungCapDAO {
	public NhaCungCapDAO() {
		// TODO Auto-generated constructor stub
		 ConectDatabase.getInstance().connect();
	}
	/**
	 * Lấy dữ liệu nhà cung cấp từ cơ sỡ dữ liệu
	 * @return danh sách nhà cung cấp
	 */
	public List<NhaCungCap> getNhaCungCaps(){
		List<NhaCungCap> list= new ArrayList<>();
		
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select * from [dbo].[NhaCungCap]";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int maNCC= rs.getInt(1);
				String ten=rs.getString(2);
				String soDienThoai= rs.getString(3);
				String gmail= rs.getString(4);
				int maDC= rs.getInt(5);
				DiaChi dc= new DiaChi(maDC);
				NhaCungCap ncc= new NhaCungCap(maNCC, ten, soDienThoai, gmail, dc);
				list.add(ncc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * Thêm địa chỉ vào cơ sỡ dữ liệu
	 * @param soNha
	 * @param tenDuong
	 * @param phuong
	 * @param quan
	 * @param thanhPho
	 * @param quocGia
	 * @return true nếu thành công
	 */
	public boolean themDiaChi(String soNha,String tenDuong,String phuong,String quan,String thanhPho,String quocGia) {
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
	/**
	 * Thêm nhà cung cấp vào cơ sở dữ liệu
	 * @param ten
	 * @param sdt
	 * @param email
	 * @param maDC
	 * @return true nếu thành công
	 */
	public boolean them(String ten,String sdt,String email,int maDC) {
		Connection con = ConectDatabase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement("insert [dbo].[NhaCungCap] values \r\n" + 
					"(?,?,?,?)");
			stmt.setString(1, ten);
			stmt.setString(2, sdt);
			stmt.setString(3, email);
			stmt.setInt(4, maDC);
			n= stmt.executeUpdate();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		return n>0;
	}
	/**
	 * Lấy mã địa chỉ
	 * @param soNha
	 * @param tenDuong
	 * @param phuong
	 * @param quan
	 * @param thanhPho
	 * @param quocGia
	 * @return mã địa chỉ
	 */
	public List<Integer> getMaDiaChi(String soNha,String tenDuong,String phuong,String quan,String thanhPho,String quocGia){
		List<Integer> list = new ArrayList<Integer>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			String sql="select[MaDiaChi]  \r\n" + 
					"from [dbo].[DiaChi]\r\n" + 
					"where [SoNha] like N'"+soNha+"' and [TenDuong] like N'"+tenDuong+"' and [Phuong] like N'"+phuong+"' and [Quan] like N'"+quan+"' "
							+ "and [ThanhPho] like N'"+thanhPho+"'  and [QuocGia]like N'"+quocGia+"'\r\n" + "";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int t= rs.getInt(1);
				list.add(t);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
}
