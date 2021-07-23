package client;

import datatypes.*;
import datatypes.protocolmessages.*;

import java.sql.Date;

public class ControllerTest extends Thread implements PacketReceivedListener {
    ClientHandler client;
    public ControllerTest(ClientHandler client){
        this.client = client;
        this.client.addListener(RegistrationCVResponse.class.toString(), this);
        this.client.addListener(RegistrationVaccinatedResponse.class.toString(), this);
        this.client.addListener(RegistrationEVResponse.class.toString(), this);
        this.client.addListener(UserLoginResponse.class.toString(), this);
        this.client.addListener(UserRegistrationResponse.class.toString(), this);
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
        if(packet instanceof UserRegistrationResponse){
            UserRegistrationResponse res = (UserRegistrationResponse) packet;
            System.out.println("Registrazione: " + res.isEsito());
        }
        if(packet instanceof UserLoginResponse){
            UserLoginResponse res = (UserLoginResponse) packet;
            System.out.println("Login: " + res.isEsito());
        }
        if(packet instanceof RegistrationEVResponse){
            RegistrationEVResponse res = (RegistrationEVResponse) packet;
            System.out.println(res.isEsito());
        }
    }


    /* Run per simulare il comportamento di una GUI */
    @Override
    public void run() {
        Vaccinato vaccinato = new Vaccinato();
        vaccinato.setCodiceFiscale("1111");
        vaccinato.setCognome("Masin");
        vaccinato.setNome("Davide");
        vaccinato.setUserId("davide");
        vaccinato.setEmail("davide.masin40@gmail.com");
        vaccinato.setPassword("davide");

        CentroVaccinale cv = new CentroVaccinale();
        cv.setId(11);

        Vaccino vaccino = new Vaccino();
        vaccino.setId(6);

        Vaccinazione v = new Vaccinazione();
        v.setDataVaccinazione(new Date(System.currentTimeMillis()));
        v.setVaccinato(vaccinato);
        v.setVaccino(vaccino);
        v.setCentroVaccinale(cv);

        //client.insertVaccination(v);
        client.requestUserRegistration(vaccinato, "VthQ6M0hol9aNlEl");

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

    /*
    Vaccinato vaccinato = new Vaccinato();
        vaccinato.setCodiceFiscale("0000");
        vaccinato.setCognome("Tornaghi");
        vaccinato.setNome("Omar");

        CentroVaccinale cv = new CentroVaccinale();
        cv.setId(11);

        Vaccino vaccino = new Vaccino();
        vaccino.setId(6);

        TipologiaEventoAvverso tipologia = new TipologiaEventoAvverso();
        tipologia.setId(1);

        EventoAvverso ev = new EventoAvverso();
        ev.setCentroVaccinale(cv);
        ev.setVaccino(vaccino);
        ev.setTipologia(tipologia);
        ev.setNote("Il davidz è ricchione");
        ev.setSeverita(5);

     */


}
