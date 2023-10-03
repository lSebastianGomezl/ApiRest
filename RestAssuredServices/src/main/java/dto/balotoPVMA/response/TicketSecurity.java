package dto.balotoPVMA.response;

public class TicketSecurity {
    public Object docToken;
    public Object secureDocToken;
    public String keyModifier;

    public Object getDocToken() {
        return docToken;
    }

    public void setDocToken(Object docToken) {
        this.docToken = docToken;
    }

    public Object getSecureDocToken() {
        return secureDocToken;
    }

    public void setSecureDocToken(Object secureDocToken) {
        this.secureDocToken = secureDocToken;
    }

    public String getKeyModifier() {
        return keyModifier;
    }

    public void setKeyModifier(String keyModifier) {
        this.keyModifier = keyModifier;
    }
}
