/**
 * This class represents a simple implementation of a blockchain.
 * It contains an ArrayList of Block objects, each representing a block in the blockchain.
 * The blockchain is mined by adding new blocks to it.
 * The isValid() method checks the validity of the blockchain.
 * The addBlock() method mines a new block and adds it to the blockchain.
 */

package blockchain;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class BlockChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;

	public static void main(String[] args) {	
		
		//add blocks to blockchain:
		System.out.println("Mining block 1... ");
		addBlock(new Block("First block", "0"));
		System.out.println("Mining block 2... ");
		addBlock(new Block("Second block",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Mining block 3... ");
		addBlock(new Block("Third block",blockchain.get(blockchain.size()-1).hash));	
		System.out.println("\nBlockchain is Valid: " + isValid());
		
		//String blockchainJson = Utility.getJson(blockchain);
		System.out.println("\nThe block chain: ");
		//System.out.println(blockchainJson);
		System.out.println(blockchain.get(0).getData());
		System.out.println(blockchain.get(0).getNonce());
		System.out.println(blockchain.get(0).getTimeStamp());
	}
	
	public static Boolean isValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered vs calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
			
		}
		return true;
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
}