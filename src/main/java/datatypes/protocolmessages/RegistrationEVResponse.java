package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class RegistrationEVResponse extends PacketACK{
    private static final long serialVersionUID = 2769755372426194885L;

    public RegistrationEVResponse() {
    }

    public RegistrationEVResponse(boolean esito) {
        super(esito);
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
