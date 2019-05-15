package uk.ac.gcal.groupthree.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.stream.IntStream;

public class PasswordHash {
	private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
	private static final int SALT_BYTES = 24;
	private static final int HASH_BYTES = 24;
	private static final int PBKDF2_ITERATIONS = 10000;
	
	private static final int ITERATION_INDEX = 0;
	private static final int SALT_INDEX = 1;
	private static final int PBKDF2_INDEX = 2;
	
	public static String createHash(String password) throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		return createHash(password.toCharArray());
	}
	
	public static boolean validatePassword(String password, String hash) throws NoSuchAlgorithmException
			, InvalidKeySpecException {
		return validatePassword(password.toCharArray(), hash);
	}
	
	private static String createHash(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_BYTES];
		random.nextBytes(salt);
		
		byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTES);
		
		return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
	}
	
	private static boolean validatePassword(char[] password, String goodHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] params = goodHash.split(":");
		int iterations = Integer.parseInt(params[ITERATION_INDEX]);
		byte[] salt = fromHex(params[SALT_INDEX]);
		byte[] hash = fromHex(params[PBKDF2_INDEX]);
		
		byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
		return slowEquals(hash, testHash);
	}
	
	private static byte[] pbkdf2(char[] password, byte[] salt, int pbkdf2Iterations, int hashBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PBEKeySpec keySpec = new PBEKeySpec(password, salt, pbkdf2Iterations, hashBytes * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
		
		return skf.generateSecret(keySpec).getEncoded();
	}
	
	private static boolean slowEquals(byte[] hash, byte[] testHash) {
		int diff = hash.length ^ testHash.length;
//    for (int i = 0; i < hash.length && i < testHash.length; i++) diff |= hash[i] ^ testHash[i];
		diff |= IntStream.iterate(0, i -> i < hash.length && i < testHash.length, i -> i + 1).map(i -> hash[i] ^ testHash[i]).reduce(0, (a, b) -> a | b);
		return diff == 0;
	}
	
	private static byte[] fromHex(String hex) {
		byte[] binary = new byte[hex.length() / 2];
    /*for (int i = 0; i < binary.length; i++)
      binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);*/
		IntStream.range(0, binary.length).forEach(i -> binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16));
		return binary;
	}
	
	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) return String.format("%0" + paddingLength + "d", 0);
		else return hex;
	}
}
