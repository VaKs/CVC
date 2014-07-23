package datos;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;

/**
 *
 * @author Sergio Vacas
 */
public class BusinessException extends Exception{
    private List<BusinessMessage> businessMessages=new ArrayList<BusinessMessage>(); 
    

    //CONSTRUCTOR PARA CUANDO SALTE UNA EXCEPCION DE VALIDACION DE HIBERNATE.        
    public BusinessException(javax.validation.ConstraintViolationException cve) {
        
            
            
            //bucle para mostrar todas las excepciones de validacion que hayan salido
            //for (OBJETOEXCEPCION EXCEPCION: TODAS EXCEPCIONES)
            for (ConstraintViolation constraintViolation : cve.getConstraintViolations()) {
                String fieldName = constraintViolation.getPropertyPath().toString();
                String message = constraintViolation.getMessage();
                
                BusinessMessage businessMessage = new BusinessMessage(fieldName, message);
                businessMessages.add(businessMessage);
            }
    
    }
    //CONSTRUCTOR PARA CUANDO SALTE UNA EXCEPCION DE VALIDACION DESDE LA BD MySQL.        
    public BusinessException(org.hibernate.exception.ConstraintViolationException cve) {
            
            
            //bucle para mostrar todas las excepciones de validacion que hayan salido
                String fieldName=null; //porque no sabemos cual es el nombre del campo
                String message=cve.getLocalizedMessage();
                
                //para cazar el numero de error MYSQL.
                //int errorCodeSQL=cve.getSQLException().getErrorCode(); 
                
                BusinessMessage businessMessage = new BusinessMessage(fieldName, message);
                businessMessages.add(businessMessage);
               
    }

    public List<BusinessMessage> getBusinessMessages() {
        return businessMessages;
    }

}