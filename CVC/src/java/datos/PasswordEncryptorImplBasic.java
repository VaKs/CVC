
package datos;

import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Sergio Vacas
 */
public class PasswordEncryptorImplBasic implements PasswordEncryptor {

    @Autowired
    PasswordEncryptor passwordEncryptor;
    
    @Override
    public String encryptPassword(String password) {

        String encryptedPassword = passwordEncryptor.encryptPassword(password);
        return encryptedPassword;
    }

    @Override
    public boolean checkPassword(String unEncryptedPassword, String encryptedPassword) {

        if (passwordEncryptor.checkPassword(unEncryptedPassword, encryptedPassword)==true) {
            return true;
        } else {
            return false;
        }
    }
    
}
