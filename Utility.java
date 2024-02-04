/** 
 * This class contains various utility functions used in the blockchain implementation.
 *
 * The class contains three static methods:
 *   - applySha256(String input): Applies the SHA-256 algorithm to a given input string and returns the resulting hash as a hexadecimal string.
 *   - getJson(Object o): Converts a given object to a JSON string using the Gson library.
 *   - getDificultyString(int difficulty): Returns a string of '0' characters with a length determined by the given difficulty parameter.
 */

package blockchain;
import java.security.MessageDigest;
import com.google.gson.GsonBuilder;

public class Utility {
	
	//Applies Sha256 to a string and returns the result. 
	//takes a string and applies SHA256 algorithm to it, and returns the generated signature as a string.
	public static String applySha256(String input){
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        
			//Applies sha256 to input, 
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
	        
			StringBuffer hexString = new StringBuffer(); // contains hash as hexidecimal
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//turn Object into a json string
	public static String getJson(Object o) {
		return new GsonBuilder().setPrettyPrinting().create().toJson(o);
	}
	
	//Returns difficulty string target
	//to compare to hash. eg difficulty of 5 will return "00000"  
	public static String getDificultyString(int difficulty) {
		return new String(new char[difficulty]).replace('\0', '0');
	}
	
	
}