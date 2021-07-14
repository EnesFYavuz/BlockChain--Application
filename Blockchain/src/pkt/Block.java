package pkt;

import java.security.MessageDigest;
import java.util.Date;

public class Block {
	private int index;
	// block un olusturulma zamanini
	private long timestamp;
	// block hash degeri
	private String hash;
	// onceki block un hash degeri
	private String previousHash;
	// block ta saklanacak data (transactionlar)
	private String data;
	private int nonce;

	public  Block(int index, long timestamp, String previousHash, String data) {//Kurucu fonksiyonlar degiskenlere atama yapilir
		this.index = index;
		this.timestamp = timestamp;
		this.previousHash = previousHash;
		this.data = data;
		this.nonce = 0;
		this.hash = calculateHash(blockField());
	}
	public String calculateHash(String input){		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");//SHA-256 algoritmasi kullanilir        
			byte[] hash = digest.digest(input.getBytes("UTF-8"));	        
			StringBuffer hexString = new StringBuffer(); 
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();//Hash degeri dondurulur
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	} 
	
  	public  String blockField() {//Blok bilgileri dondurulur
		return index + timestamp + previousHash + data + nonce;
	}
  	public void mineBlock(int difficulty) {//Madencilik yapilan fonksiyon
  		String target = new String(new char[difficulty]).replace('\0', '0');  
		while(!hash.substring( 0, difficulty).equals(target)) {//Gonderilen zorluk seviyesi kadar '0' ile basliyan hash bulana kadar devam eder
			nonce ++;
			this.hash = calculateHash(blockField());//hash hesaplanir
		}
		System.out.println("Block Mined: " + hash);
	}
  	//Private degiskenlere erismek icin get set kullanilir
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Date getTimestamp() {//Olusturulan blok zaman degeri unix timestamp olarak geliyor
		Date date = new Date();
		date.setTime((timestamp*1000));//Ve tarih formatina cevrilip donduruluyor
		return date;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getPreviousHash() {
		return previousHash;
	}
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getNonce() {
		return nonce;
	}
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

}
