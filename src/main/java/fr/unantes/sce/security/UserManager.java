package fr.unantes.sce.security;

import fr.unantes.sce.people.Person;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, Person> namesToUsers;
    private Map<String, String> usersToPasswords;

    public UserManager() {
        namesToUsers = new HashMap<>();
        usersToPasswords = new HashMap<>();
    }

    /**
     * Test if an user is registered in the manager
     * @param person - User to search for
     * @return True if the user is registered, False otherwise
     */
    public boolean hasUser(Person person) {
        return namesToUsers.containsKey(person.getName());
    }

    /**
     * Add a new user to the manager
     * @param person - User to add
     * @param password - User's password
     * @return True if everything went smoothly, False otherwise
     * @throws IllegalArgumentException
     */
    public boolean addUser(Person person, String password) throws IllegalArgumentException {
        if (namesToUsers.containsKey(person.getName())) {
            throw new IllegalArgumentException("Invalid argument: the person is already registered.");
        }
        namesToUsers.put(person.toString(), person);
        usersToPasswords.put(person.getName(), encryptPassword(password));
        return true;
    }

    /**
     * Remove a user from the manager
     * @param person - User to remove
     * @return True if everything went smoothly, False otherwise
     */
    public boolean removeUser(Person person) {
        if (namesToUsers.containsKey(person.getName())) {
            Person p = namesToUsers.get(person.getName());
            usersToPasswords.remove(p.getName());
            namesToUsers.remove(p.getName());
        }
        return true;
    }

    /**
     * Valid a password
     * @param person - User associated to the password
     * @param password - password to validate
     * @return True if the password is valid, false otherwise
     */
    public boolean validatePassword(Person person, String password) {
        if (namesToUsers.containsKey(person.getName())) {
            Person p = namesToUsers.get(person.getName());
            String reference = usersToPasswords.get(p.getName());
            return decryptPassword(reference).equals(password);
        }
        return false;
    }

    /**
     * Encrypt a password
     * @param password - Password to encrypt
     * @return Encrypted password
     * @throws IllegalArgumentException
     */
    private String encryptPassword(String password) throws IllegalArgumentException {
        if (password.contains("a")) {
            throw new IllegalArgumentException("The password contains unsecure characters, cannot perform encryption.");
        }
        return password.replaceAll("a", "e");
    }

    /**
     * Decrypt a password
     * @param encrypted - Password to decrypt
     * @return Decrypted password
     */
    private String decryptPassword(String encrypted) {
        return encrypted.replaceAll("e", "a");
    }
}
