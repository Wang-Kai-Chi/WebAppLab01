package lab00.oos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;

import lab01.model.MemberBean;

public class OOSDemo {

	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("C:\\temp\\OOS.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			MemberBean memberBean = new MemberBean("A1101", "君雅", "P@ssw0rd", "0919-123456", Date.valueOf("2000-12-21"),
					null, 0.0);
			oos.writeObject(memberBean);
			oos.writeObject("Hello, Kitty");
			oos.writeObject(Integer.valueOf(10000));
			System.out.println("finished");
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

}
