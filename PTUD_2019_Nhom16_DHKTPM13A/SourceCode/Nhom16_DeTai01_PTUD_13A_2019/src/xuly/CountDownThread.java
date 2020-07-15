package xuly;

import java.awt.Color;

import database.ConectDatabase;
import gui.FrmDangNhap;
import gui.FrmGioiThieu;

public class CountDownThread extends Thread {
	public void run() {
		int count = 3;
		ConectDatabase.getInstance().connect();
		FrmGioiThieu frmGioiThieu = new FrmGioiThieu();
		frmGioiThieu.setVisible(true);
		frmGioiThieu.setLocationRelativeTo(null);
		for(int i=0;i<count;i++) {
			try {
				Thread.sleep(1000);
				frmGioiThieu.progressBar.setValue(33);
				if(i==1)
					frmGioiThieu.progressBar.setValue(66);
				if(i==2)
					frmGioiThieu.progressBar.setValue(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		frmGioiThieu.setVisible(false);
		FrmDangNhap frmDangNhap = new FrmDangNhap();
		frmDangNhap.setVisible(true);
		frmDangNhap.setLocationRelativeTo(null);
	}
}
