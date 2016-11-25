package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PersonDAO;
import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.entity.Store;
import cz.muni.fi.pa165.enums.PersonRole;
import cz.muni.fi.pa165.exceptions.RegisterServiceException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of PersonService interface.
 *
 * @author Jakub Fiser
 * @date 14/11/2016
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Inject
    private PersonDAO personDAO;


    @Override
    public void registerPerson(Person person, String unencryptedPassword) {
        person.setPasswordHash(createHash(unencryptedPassword));
        personDAO.save(person);
    }

    @Override
    public void changePassword(Person person, String newPassword) {
        if (personDAO.findByLogin(person.getLogin()) == null) {
            throw new RegisterServiceException("Cannot change password on non-registered person.");
        }
        person.setPasswordHash(createHash(newPassword));
        personDAO.save(person);
    }

    @Override
    public boolean authenticate(Person person, String password) {
        return validatePassword(password, person.getPasswordHash());
    }

    @Override
    public boolean worksForLaboratory(Person person, Laboratory laboratory) {
        boolean isRole = PersonRole.LAB.equals(person.getRole());
        boolean correctLaboratory = laboratory.equals(person.getLaboratory());
        return isRole && correctLaboratory;
    }

    @Override
    public boolean worksForManufacturer(Person person, Manufacturer manufacturer) {
        boolean isRole = PersonRole.MANUFACTURER.equals(person.getRole());
        boolean correctManufacturer = manufacturer.equals(person.getManufacturer());
        return isRole && correctManufacturer;
    }

    @Override
    public boolean worksForStore(Person person, Store store) {
        boolean isRole = PersonRole.STORE_OWNER.equals(person.getRole());
        boolean correctStore = store.equals(person.getStore());
        return isRole && correctStore;
    }

    @Override
    public boolean isPolice(Person person) {
        return PersonRole.POLICE.equals(person.getRole());
    }

    @Override
    public List<Person> findAll() {
        return personDAO.findAll();
    }

    @Override
    public Person findUserById(Long personId) {
        return personDAO.findOne(personId);
    }

    @Override
    public Person findUserByLogin(String login) {
        return personDAO.findByLogin(login);
    }

    @Override
    public Person findUserByEmail(String email) {
        return personDAO.findByEmail(email);
    }


    // Some security functions taken from demo project presented on lectures
    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean validatePassword(String password, String correctHash) {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }
}
