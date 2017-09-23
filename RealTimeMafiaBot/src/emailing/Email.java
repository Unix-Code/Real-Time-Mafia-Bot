package emailing;

/**
 *
 * @author David
 */
public class Email {
    private String email;
    private String name;
    private boolean isMafia;

    public Email(String email, String name) {
        this.email = email;
        this.name = name;
    }
    
    public Email(String email, String name, boolean isMafia) {
        this.email = email;
        this.name = name;
        this.isMafia = isMafia;
    }

    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }

    public boolean isMafia() {
        return isMafia;
    }

    public void setIsMafia(boolean isMafia) {
        this.isMafia = isMafia;
    }
    
    
}
