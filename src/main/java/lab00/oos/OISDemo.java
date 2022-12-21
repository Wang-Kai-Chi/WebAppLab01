package lab00.oos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;

import lab01.model.MemberBean;

public class OISDemo {

	public static void main(String[] args) {
		try (FileInputStream fos = new FileInputStream("C:\\temp\\OOS.dat");
				ObjectInputStream ois = new ObjectInputStream(fos);) {

			while (true) {
				Object obj;
				try {
					obj = ois.readObject();
					if (obj instanceof MemberBean) {
						MemberBean memberBean = (MemberBean) obj;
						System.out.println("姓名: " + memberBean.getName());
					} else if (obj instanceof String) {
						String s = (String) obj;
						System.out.println("字串長度:" + s.length());
					}
					if (obj instanceof Integer) {
						Integer i = (Integer) obj;
						System.out.println("i: " + i);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (EOFException e) {
					System.out.println("read finished");
					break;
				}
			}
			System.out.println("finished");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

}
