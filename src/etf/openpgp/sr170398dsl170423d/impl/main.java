package etf.openpgp.sr170398dsl170423d.impl;

import java.io.File;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		Backend b = new Backend();
		//b.generateKeyPair("spasoje", "spas@gmail.com", 4096, "123");
		//b.exportPublicKey(-1254776031991395818l, "spas.asc");
		//insert(b);
		receive(b);
		//show(b);
		//System.out.println(b.isUsingEncryption("C:\\Users\\Sonja\\Desktop\\msg_1.gpg"));
	}

	public static void show(Backend b)
	{
		ArrayList<RingOutput> r = b.showPrivateKeyRingCollection();
		for(RingOutput rr: r)
			System.out.println(rr);
		System.out.println("---");
		r = b.showPublicKeyRingCollection();
		for(RingOutput rr: r)
			System.out.println(rr);
	}
	public static void insert(Backend b)
	{
		File f = new File("C:\\Users\\Sonja\\Desktop\\msg.txt");
		long[] receivers = new long[2];
		receivers[0] = -7882090506529290298l;
		receivers[1] = 2657159746924513900l;
		b.sendMessage(f, 
				true, false, false, true, 
				Constants.CAST5, 
				2657159746924513900l, 
				receivers, 
				"123");
	}
	
	public static void receive(Backend b)
	{
		ArrayList<String> msg = b.receiveMessage("C:\\Users\\Sonja\\Desktop\\msg_1.gpg");
		
		for(String m: msg)
			System.out.println(m);
	}
}
