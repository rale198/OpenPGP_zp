package etf.openpgp.sr170398dsl170423d.impl;

import java.io.File;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		Backend b = new Backend();
		//insert(b);
		receive(b);
		show(b);
	}

	public static void show(Backend b)
	{
		b.showPrivateKeyRingCollection();
		System.out.println("---");
		b.showPublicKeyRingCollection();
	}
	public static void insert(Backend b)
	{
		File f = new File("C:\\Users\\Sonja\\Desktop\\msg.txt");
		long[] receivers = new long[2];
		receivers[0] = -7882090506529290298l;
		receivers[1] = 2657159746924513900l;
		b.sendMessage(f, 
				false, true, true, true, 
				Constants.CAST5, 
				2657159746924513900l, 
				receivers, 
				"123");
	}
	
	public static void receive(Backend b)
	{
		ArrayList<String> msg = b.receiveMessage("C:\\Users\\Sonja\\Desktop\\msg_1.gpg","123");
		
		for(String m: msg)
			System.out.println(m);
	}
}
