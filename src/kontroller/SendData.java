package kontroller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.DataContract;

public class SendData {
	public static final Logger log = LogManager.getLogger(SendData.class);

	/**
	 * save object DateContract
	 * 
	 * @see DataContract
	 * @param dataOrganization
	 *            - object storing a list of concluded agreements
	 * 
	 * @param fileName
	 *            - the path to the file
	 */
	public static void sendData(DataContract dataOrganization, File fileName) {

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dataOrganization);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			log.error("File write error FileNotFoundException", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("File write error IOException", e);
			e.printStackTrace();
		}
	}
}
