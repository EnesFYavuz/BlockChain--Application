package pkt;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockChain blockchain = new BlockChain(4);//Zorluk derecesi parametre verilerek sinif degiskeni olusturulur
		blockchain.addBlock(blockchain.newBlock("Second data"));//Veri parametre verilerek yeni bloklar eklenir
		blockchain.addBlock(blockchain.newBlock("Third data"));
		blockchain.addBlock(blockchain.newBlock("Fourth data"));
	
		
		blockchain.displayChain();//Block zinciri gosterilir
		
		blockchain.isValid();//Blok butunlugunun saglanip saglanmadigi kontrol edilir

	}

}
