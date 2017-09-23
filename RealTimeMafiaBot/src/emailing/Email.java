package emailing;

/**
 *
 * @author David
 */
public class Email {
    private String email;
    private String name;
    private boolean isMafia;
    private boolean isSK;
    

    public Email(String email, String name) {
        this.email = email;
        this.name = name;
        this.isMafia = false;
        this.isSK = false;
    }

    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }

    public boolean isSK() {
        return isSK;
    }
    
    public boolean isMafia() {
        return isMafia;
    }

    public void setIsMafia(boolean isMafia) {
        this.isMafia = isMafia;
    }

    public void setIsSK(boolean isSK) {
        this.isSK = isSK;
    }
}
