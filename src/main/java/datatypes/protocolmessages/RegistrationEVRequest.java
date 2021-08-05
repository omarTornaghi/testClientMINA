package datatypes.protocolmessages;

import datatypes.EventoAvverso;
import datatypes.Vaccinato;

public class RegistrationEVRequest extends Packet{
    private EventoAvverso eventoAvverso;

    public RegistrationEVRequest() {
    }

    public RegistrationEVRequest(EventoAvverso eventoAvverso) {
        this.eventoAvverso = eventoAvverso;
    }

    public EventoAvverso getEventoAvverso() {
        return eventoAvverso;
    }

    public void setEventoAvverso(EventoAvverso eventoAvverso) {
        this.eventoAvverso = eventoAvverso;
    }

    @Override
    public String getPacketName() {
        return null;
    }
}
