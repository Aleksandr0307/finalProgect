package kontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.DataContract;

/**
 * class for get an object DataContract, if absent creates and writes a new
 * object
 *
 */
public class GetData {

	public static final Logger log = LogManager.getLogger(GetData.class);

	public static DataContract findings(File file) {

		DataContract dataOrganization = new DataContract();
		FileInputStream fis = null;
		ObjectInputStream oin = null;

		try {
			if (file.createNewFile()) {
				SendData.sendData(dataOrganization, file);
			} else {
				try {
					fis = new FileInputStream(file);
					oin = new ObjectInputStream(fis);
					dataOrganization = (DataContract) oin.readObject();
				} catch (FileNotFoundException e) {
					log.error("File write error FileNotFoundException");
					e.printStackTrace();
				} catch (IOException e) {
					log.error("File write error IOException");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					log.error("File write error ClassNotFoundException");
					e.printStackTrace();
				} finally {
					oin.close();
					fis.close();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataOrganization;
	}
}
