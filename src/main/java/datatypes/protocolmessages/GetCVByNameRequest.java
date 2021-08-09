package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class GetCVByNameRequest extends Packet{
    private static final long serialVersionUID = 6550664636511014674L;
    private String nome;

    public GetCVByNameRequest() {
    }

    public GetCVByNameRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
