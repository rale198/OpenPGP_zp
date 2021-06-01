package etf.openpgp.sr170398dsl170423d.gui.dtos;
import etf.openpgp.sr170398dsl170423d.impl.Constants;
import etf.openpgp.sr170398dsl170423d.impl.RingOutput;

public class SlanjePorukeDtos {
	public Constants Algoritam;
	public RingOutput[] JavniKljucevi;
	public boolean Autentikacija;
	public RingOutput PrivatniKljuc;
	public String Lozinka;
	public boolean Kompresija;
	public boolean Radix;
	
	public SlanjePorukeDtos() {
		Algoritam = null;
		JavniKljucevi = null;
		Autentikacija = false;
		PrivatniKljuc = null;
		Lozinka = "";
		Kompresija = false;
		Radix = false;
		
	}
}
