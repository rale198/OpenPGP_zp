package etf.openpgp.sr170398dsl170423d.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RingOutput {

	private String name, email;
	private long keyID;
	private byte[] fingerPrint;
	private boolean isPrivate;
	
	
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	private Date validFrom, validUntil;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getKeyID() {
		return keyID;
	}
	public void setKeyID(long keyID) {
		this.keyID = keyID;
	}
	public byte[] getFingerPrint() {
		return fingerPrint;
	}
	public void setFingerPrint(byte[] fingerPrint) {
		this.fingerPrint = fingerPrint;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
	@Override
    public String toString() {
        DateFormat f = new SimpleDateFormat("YYYY-MM");
        StringBuilder sb = new StringBuilder();
        sb.append(this.name+" ");
        sb.append(this.email+" ");
        sb.append(f.format(validFrom)+" ");
        sb.append(f.format(validUntil)+" ");
        sb.append(this.keyID+" ");

        return sb.toString();
    }
}