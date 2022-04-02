package fr.unantes.sce.security;

import java.util.HashMap;
import java.util.Map;

/**
 * PasswordManager provides method used by UserManager to identify user through a password system.
 */
public class PasswordManager {
    private Map<String, String> usersToPasswords;

    public PasswordManager() {
        this.usersToPasswords = new HashMap<>();
    }

    public Map<String, String> getUsersToPasswords(){
        return usersToPasswords;
    }

    /**
     * Decrypt a password
     * @param encrypted - Password to decrypt
     * @return Decrypted password
     */
    String decryptPassword(String encrypted) {
        return encrypted.replaceAll("e", "a");
    }

    /**
     * Encrypt a password
     * @param password - Password to encrypt
     * @return Encrypted password
     * @throws IllegalArgumentException
     */
    String encryptPassword(String password) throws IllegalArgumentException {
        if (password.contains("a")) {
            throw new IllegalArgumentException("The password contains unsecure characters, cannot perform encryption.");
        }
        return password.replaceAll("a", "e");
    }
}
