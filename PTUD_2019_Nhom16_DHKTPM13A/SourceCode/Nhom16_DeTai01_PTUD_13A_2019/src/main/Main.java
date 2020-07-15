package main;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

import database.*;
import gui.FrmDangNhap;
import gui.FrmGioiThieu;
import gui.FrmLapHoaDon;
import xuly.CountDownThread;

public class Main {
	public static void main(String[] args) {
		try {
			ConectDatabase.getInstance().connect();
			CountDownThread countDownThread = new CountDownThread();
			countDownThread.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
//		
	}
}
