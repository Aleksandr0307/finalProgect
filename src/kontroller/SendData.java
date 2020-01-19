package kontroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.DataContract;

public class SendData {

	public static final String FILE_NAME = GetData.FILE_NAME;

	public static void sendData(DataContract dataOrganization) throws IOException {

		FileOutputStream fos = new FileOutputStream(FILE_NAME);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(dataOrganization);
		oos.flush();
		oos.close();
	}
}
