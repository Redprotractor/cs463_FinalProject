/**
 * 
 */
package finalProject;

import java.io.IOException;

import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

/**
 * CS 463 Cryptography for Cybersecurity 
 * 
 * Final Project
 * 
 * Created by Dylan Montgomery
 * 
 * Public/private key exchange using the Diffie-Hellman Key Exchange
 * 
 * 
 * DHKE Set-up
 * 	Choose a large prime p
 *  Choose an integer a that lies in {2,3,...,p-2}
 * 	Publish p and a
 * 
 * DHKE Exchange
 * 	Alice chooses x in {2,3,...,p-2}
 *  Alice computes public key A = a^x mod p
 *  
 *  Bob chooses y in {2,3,...,p-2}
 *  Bob computes public key B = a^y mod p
 *  
 *  Alice and Bob exchange their public keys, A and B
 *  Alice computes the shared key K = B^x mod p
 *  Bob computes the shared key K = A^y mod p
 *  
 *  Verify that both computed keys K are equal
 */
public class PublicPrivateKey {
	
	public static void main(String[] args) throws IOException {
		int p = 0; // a large prime number
		int alpha = 0; // generator value
		int a = 0; // Alice chosen prime
		int b = 0; // Bob chosen prime
		int A = 0; // Alice public key
		int B = 0; // Bob public key
		int AP = 0; // Alice shared key
		int BP = 0; // Bob shared key
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("Diffie-Hellman Key Exchange\n");
		
		/**
		 * Setup
		 * Choose a large prime number p and a generator value a
		 */
		System.out.println("SETUP PHASE");
		while (!isPrime(p)) {
			System.out.println("Enter a prime number, 'p'");
			p = input.nextInt();
			if (!isPrime(p))
				System.out.println("'p' must be a prime number");
		}
		
		System.out.println("\nChoose a value for 'alpha' where alpha>1 && alpha< p-1");
		while (alpha < 2 || alpha > p-2) {
		    alpha = input.nextInt();
		    if (alpha < 2 || alpha > p-2) {
				System.out.println("Invalid input\n 'alpha' must lie in {2,3,...,p-2}");
			}
		}
		
		System.out.println("\nGENERATE PUBLIC KEYS");
		/**
		 * Generate Alice's public key
		 *  Alice chooses a in {2,3,...,p-2}
		 *  Alice computes public key A = alpha^a mod p
		 * 
		 */
        System.out.println("Alice chooses a value for 'a' where a>1 && a<p-1");
        while (a < 2 || a > p-2) {
            a = input.nextInt();
            if (a < 2 || a > p-2) {
                System.out.println("Invalid input\n'a' must lie in {2,3,...,p-2}");
            }
        }
        System.out.println("Generate Alice's public key");
        A = Key(alpha, a, p);
        
        /**
         * Generate Bob's public key
         *  Bob chooses b in {2,3,...,p-2}
         *  Bob computes public key B = alpha^b mod p
         * 
         */
        System.out.println("Bob chooses a value for 'b' where b>1 && b<p-1");
        while (b < 2 || b > p-2) {
            b = input.nextInt();
            if (b < 2 || b > p-2) {
                System.out.println("Invalid input\n'b' must lie in {2,3,...,p-2}");
            }
        }
        System.out.println("Generate Bob's public key");
        B = Key(alpha, b, p);
        
        System.out.println("Alice and Bob exchange public keys");
        System.out.println("Alice's public key is " + A);
        System.out.println("Bob's public key is " + B);
        
        System.out.println("\nGENERATE SHARED KEY");
        System.out.println("Alice computes shared key");
        AP = Key(B, a, p);
        System.out.println("Bob computes shared key");
        BP = Key(A, b, p);
        System.out.println("\nCheck that Alice and Bob's shared keys are the same");
        System.out.println("Keys are equal: " + areEqual(AP,BP));
		input.close();
	}
	
	/**
	 * Check if the number pri is prime
	 * @param pri
	 * @return true if prime 
	 */
	static boolean isPrime(int pri) {
		if (pri <= 1) {
			return false;
		}
		for (int i = 2; i < pri; i++) {
			if (pri % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param f    prime number
	 * @param g    integer
	 * @param h    user chosen value
	 * @return g^h mod f
	 */
	static int Key(int f, int g, int h) {
	    return (int) (Math.pow(f,g) % h);
	}
	
	/**
	 * 
	 * @param key1
	 * @param key2
	 * @return key1==key2
	 */
	static boolean areEqual(int key1, int key2) {
	    return key1 == key2;
	}
	
}
