package pkt;


import java.util.ArrayList;
import java.util.List;

public class BlockChain {
	private int difficulty;
	private List<Block> chain;
	
	public BlockChain(int difficulty) {
		this.difficulty = difficulty;
		chain = new ArrayList<Block>();//Blok liste seklinde olusturulur
		addBlock(createFirstBlock());//Ilk blok kurucu fonksiyonla blok zincirine eklenir
	}
	
	private Block createFirstBlock() {
		return new Block(0, System.currentTimeMillis(), null, "First data");//Ilk blok olusturulur
	}
	
	public void addBlock(Block blk) {//Bloklar zincire eklenir
		if (blk != null) {
			blk.mineBlock(difficulty);//Madencilik zorluk derecesine gore yapilir
			chain.add(blk);
		}
	}
	public Block newBlock(String data) {//Yeni blok olusturma fonksiyonu
		return new Block(getLatestBlock().getIndex() + 1, System.currentTimeMillis(), getLatestBlock().getHash(), data);
	}
	
	public void displayChain() { 
		for(int i=0; i<chain.size(); i++) {//Listedeki blok kadar dongu devam eder
			System.out.println();
			System.out.println("Block: " + chain.get(i).getIndex());
			System.out.println("Timestamp: "+chain.get(i).getTimestamp().toString());
			System.out.println("Data: "+chain.get(i).getData());
			System.out.println("PreviousHash: " + chain.get(i).getPreviousHash());
			System.out.println("Hash: " + chain.get(i).getHash());
		}
		
	}
	
	public Block getLatestBlock() {
		return this.chain.get(chain.size()-1);//son blogu bulmak icin listedeki blok sayisindan 1 cikarilir
	}
	public void isValid() {//Blok butunlugu saglandimi kontrolu yapan fonksiyon
		
		for(int i=chain.size()-1; i>0; i--) { //Tum bloklar dolasilir
			String blockField=chain.get(i).blockField();//Hangi bloktaysa blok degerleri alinir
			if( !(chain.get(i).getHash().equals(chain.get(i).calculateHash(blockField)))) {//Blok hashi hesaplanan hashe esit degilse butunluk saglanmaz
				System.out.println("Chain is not valid");
				return;
			}
			
			if( !(chain.get(i).getPreviousHash().equals(chain.get(i-1).getHash()))) {//Bulunulan bloktaki hash degeri bir onceki hash degerine esit degilse butunluk saglanmaz
				System.out.println("Chain is not valid");
				return;
			}
		}
		System.out.println();
		System.out.println("Chain is valid.");//Kosullar saglaniyosa blok butunlugu saglanir
		
	}
	
}
