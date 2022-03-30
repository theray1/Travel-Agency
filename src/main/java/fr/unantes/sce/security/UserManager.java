package fr.unantes.sce.security;

import fr.unantes.sce.people.Person;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private PasswordManager passwordManager;
    private Map<String, Person> namesToUsers;

    public UserManager() {
        namesToUsers = new HashMap<>();
        passwordManager = new PasswordManager();
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
        passwordManager.getUsersToPasswords().put(person.getName(), passwordManager.encryptPassword(password));
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
            passwordManager.getUsersToPasswords().remove(p.getName());
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
            String reference = passwordManager.getUsersToPasswords().get(p.getName());
            return passwordManager.decryptPassword(reference).equals(password);
        }
        return false;
    }




}
