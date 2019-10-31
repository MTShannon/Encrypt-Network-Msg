import java.math.BigInteger;
import java.util.Random;


public class RSA {
   private BigInteger prime1;
   private BigInteger prime2;
   private int bitlength = 1024;
   private BigInteger phi;
   
   private BigInteger N;
   private BigInteger key1;
   private BigInteger key2;
   
   
   public RSA() {
   
   }
   
   public BigInteger[] generateKeys() {
      BigInteger[] keys = new BigInteger[3];
      
      Random rand = new Random();
      prime1 = BigInteger.probablePrime(bitlength, rand);
      prime2 = BigInteger.probablePrime(bitlength, rand);
      
      N = prime1.multiply(prime2);
      phi = prime1.subtract(BigInteger.ONE).multiply(prime2.subtract(BigInteger.ONE));
      
      key1 = BigInteger.probablePrime(bitlength / 2, rand);
      
      while (phi.gcd(prime1).compareTo(BigInteger.ONE) > 0 && prime1.compareTo(phi) < 0) {
         prime1.add(BigInteger.ONE);
      }
      
      key2 = key1.modInverse(phi); 
      
      keys[0] = key1;
      keys[1] = key2;
      keys[2] = N;
      
      return keys;
   }
   
   public void setKeys(BigInteger key1, BigInteger key2, BigInteger N) {
      this.key1 = key1;
      this.key2 = key2;
      this.N = N;
   }
    
    
   private static String bytesToString(byte[] encrypted) {
      String test = "";
      for (byte b : encrypted) {
         test += Byte.toString(b);
      }
      return test;
   }
 
   // Encrypt message
   public byte[] encrypt(byte[] message) {
      return (new BigInteger(message)).modPow(key1, N).toByteArray();
   }
 
   // Decrypt message
   public byte[] decrypt(byte[] message) {
      return (new BigInteger(message)).modPow(key2, N).toByteArray();
   }
   
   
}