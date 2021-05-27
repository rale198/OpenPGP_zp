package etf.openpgp.sr170398dsl170423d.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Utils {
	public static String USER_ID(String username, String email)
	{
		return username+"_"+email;
	}
	
	public static void write(byte[] data, String filename) throws IOException
	{
		FileOutputStream outputStream = new FileOutputStream(filename);
		outputStream.write(data);
		outputStream.close();
	}
	
	public static String PublicKeyFilename(String username, String email, byte[] fingerPrint)
	{
		return String.format(Constants.PublicKeyFilePath+"%s_%s.asc", 
				Utils.USER_ID(username, email), new String(fingerPrint, StandardCharsets.UTF_16));
	}
}
