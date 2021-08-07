package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class UserLoginRequest extends Packet {
    private static final long serialVersionUID = -8877420813199743460L;
    private String username;
    private String password;

    public UserLoginRequest(){}
    public UserLoginRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPacketName() {
        return this.getClass().toString();
    }
}
