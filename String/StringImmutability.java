/**
 * Demonstration and explanation of why Strings are immutable in Java.
 * 
 * Why is String Immutable in Java?
 * --------------------------------
 * 1. String Pool (Memory Efficiency):
 *    Java uses a special memory region called the String Constant Pool. Multiple references can point 
 *    to the same String literal. If Strings were mutable, modifying a String through one reference 
 *    would silently change the value for all other references pointing to it.
 * 
 * 2. Security:
 *    Strings are extensively used to store sensitive information like database credentials, URLs, 
 *    usernames, passwords, and file paths. If Strings were mutable, an attacker could modify the 
 *    underlying value after it has passed validation checks, compromising security.
 * 
 * 3. Thread Safety:
 *    Immutable objects are implicitly thread-safe. They can be shared across multiple threads without 
 *    requiring synchronization, eliminating concurrency issues like data races.
 * 
 * 4. Hash Code Caching:
 *    Because Strings cannot change, their hashCode() is cached at creation. This makes them highly 
 *    efficient keys in HashMaps, HashSets, and other hash-based collections, as the hash code doesn't 
 *    need to be recomputed.
 */
public class StringImmutability {
    public static void main(String[] args) {
        System.out.println("=== 1. String Pool & Shared References ===");
        String s1 = "Hello";
        String s2 = "Hello";
        System.out.println("s1 and s2 point to the same literal.");
        System.out.println("s1 == s2: " + (s1 == s2)); // true (same reference)

        System.out.println("\n=== 2. Immutability Demonstration ===");
        System.out.println("Original s1: " + s1);
        String s3 = s1.concat(" World");
        System.out.println("After concat operation:");
        System.out.println("s1 (should remain unchanged): " + s1);
        System.out.println("s3 (result of concat): " + s3);

        System.out.println("\n=== 3. Hash Code Caching ===");
        String key = "myKey";
        int initialHash = key.hashCode();
        System.out.println("Hash code of key: " + initialHash);
        // Even if we perform concat, key itself is unchanged, so hash code remains identical.
        key.concat("modified");
        System.out.println("Hash code of key after concat: " + key.hashCode());
        System.out.println("Are hash codes identical? " + (initialHash == key.hashCode()));
    }
}
