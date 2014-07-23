package datos;

import java.util.List;

/**
 *
 * @author Sergio Vacas
 */
public class BusinessMessage {

    private String fieldName;
    private String message;
  
            public BusinessMessage() {
            }

            public BusinessMessage(String fieldName, String message) {
                this.fieldName=fieldName;
                this.message=message;
            }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
// MOSTRAR TODOS LOS MENSAJES DE ERROR QUE SALEN DE LAS EXCEPCIONES VALIDACION POR HIBERNATE Y SQL.
static void mostrarMensajes(List<BusinessMessage> businessMessages) {
        int n=0;
        System.out.println("Ups l´has cagat: ");
        
        for (BusinessMessage businessMessage: businessMessages){
            n++;    
            mostrarMensaje(businessMessage);
        }
    }

// CAPTURAR MOSTRAR UN MENSAJE DE ERROR
static void mostrarMensaje(BusinessMessage businessMessage) {
        
            String fieldName= businessMessage.getFieldName();
            String message= businessMessage.getMessage();
            
            if (fieldName!=null) {
            System.out.println("HV: En el campo \""+fieldName+"\" Se ha producido el error "+message);}
            else {
            System.out.println("SQL: Se ha producido el error "+message);
            }
    }    

}
