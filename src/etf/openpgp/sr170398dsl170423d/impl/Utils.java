package etf.openpgp.sr170398dsl170423d.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {
	public static String USER_ID(String username, String email)
	{
		return username+" <"+email+">";
	}
	
	public static void write(byte[] data, String filename) throws IOException
	{
		FileOutputStream outputStream = new FileOutputStream(filename);
		outputStream.write(data);
		outputStream.close();
	}
	
	//treba izmeniti ovo, da preko gui zadaje naziv fajla
	public static String PublicKeyFilename(String username, String email, byte[] fingerPrint)
	{
		return String.format(Constants.PublicKeyFilePath+"%s.asc", 
				String.format("%s_%s", username, email));
	}
	
	public static void createRingFile(String filename)
	{
		File f = new File(filename);
		if(!f.exists())
		{
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
