package client;

import datatypes.CentroVaccinale;
import datatypes.Vaccinato;
import datatypes.Vaccinazione;
import datatypes.Vaccino;
import datatypes.protocolmessages.LoginResponse;
import datatypes.protocolmessages.Packet;
import datatypes.protocolmessages.RegistrationCVResponse;
import datatypes.protocolmessages.RegistrationVaccinatedResponse;

import java.sql.Date;

public class ControllerTest extends Thread implements PacketReceivedListener {
    MinaClient client;
    public ControllerTest(MinaClient client){
        this.client = client;
        this.client.addListener(RegistrationCVResponse.class.toString(), this);
        this.client.addListener(RegistrationVaccinatedResponse.class.toString(), this);
    }
    @Override
    public void onPacketReceived(Packet packet) {
        if(packet instanceof RegistrationCVResponse){
            RegistrationCVResponse res = (RegistrationCVResponse) packet;
            System.out.println(res.getPacketName() + " " + res.isEsito());
        }
        if(packet instanceof RegistrationVaccinatedResponse){
            RegistrationVaccinatedResponse res = (RegistrationVaccinatedResponse) packet;
            System.out.println(res.getPacketName() + " " + res.isEsito());
        }
    }


    /* Run per simulare il comportamento di una GUI */
    @Override
    public void run() {
        CentroVaccinale cv = new CentroVaccinale();
        cv.setId(11);
        Vaccinazione vaccinazione = new Vaccinazione();
        vaccinazione.setCentroVaccinale(cv);
        Vaccinato vaccinato = new Vaccinato();
        vaccinato.setCodiceFiscale("0000");
        vaccinato.setCognome("Tornaghi");
        vaccinato.setNome("Omar");
        vaccinazione.setVaccinato(vaccinato);
        Vaccino vaccino = new Vaccino();
        vaccino.setId(6);
        vaccinazione.setVaccino(vaccino);
        vaccinazione.setDataVaccinazione(new Date(System.currentTimeMillis()));
        client.insertVaccination(vaccinazione);
        int i = 0;
        while(true){
            System.out.println("Cont: " + i++);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
