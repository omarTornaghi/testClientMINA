package datatypes.protocolmessages;


import datatypes.Vaccinazione;

public class RegistrationVaccinatedRequest extends Packet{
    private Vaccinazione vaccinazione;

    public RegistrationVaccinatedRequest() {
    }

    public RegistrationVaccinatedRequest(Vaccinazione vaccinazione) {
        this.vaccinazione = vaccinazione;
    }

    public Vaccinazione getVaccinazione() {
        return vaccinazione;
    }

    public void setVaccinazione(Vaccinazione vaccinazione) {
        this.vaccinazione = vaccinazione;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
