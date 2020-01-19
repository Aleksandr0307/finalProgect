package kontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import model.DataContract;

public class GetData {
	public static final String FILE_NAME = "fp1.2";

	public static DataContract findings() {
		DataContract dataOrganization = new DataContract();
		File file = new File(FILE_NAME);
		FileInputStream fis = null;
		ObjectInputStream oin = null;

		try {
			if (file.createNewFile()) {
				SendData.sendData(dataOrganization);
				System.out.println("װאיכ " + file.getName() + " סמחהאם ג " + file.getAbsolutePath());
			} else {
				try {
					fis = new FileInputStream(FILE_NAME);
					oin = new ObjectInputStream(fis);
					dataOrganization = (DataContract) oin.readObject();
					oin.close();
					fis.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataOrganization;

	}

}
