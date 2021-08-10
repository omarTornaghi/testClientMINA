package client;

import datatypes.*;
import datatypes.protocolmessages.*;

import java.sql.Date;
import java.util.List;

public class ControllerTest extends Thread implements PacketReceivedListener {
    ClientHandler client;
    public ControllerTest(ClientHandler client){
        this.client = client;
        this.client.addListener(RegistrationCVResponse.class.toString(), this);
        this.client.addListener(RegistrationVaccinatedResponse.class.toString(), this);
        this.client.addListener(RegistrationEVResponse.class.toString(), this);
        this.client.addListener(UserLoginResponse.class.toString(), this);
        this.client.addListener(UserRegistrationResponse.class.toString(), this);
        this.client.addListener(GetCVResponse.class.toString(), this);
        this.client.addListener(GetEvTypologiesResponse.class.toString(), this);
        this.client.addListener(GetVaccinesResponse.class.toString(), this);
        this.client.addListener(GetReportResponse.class.toString(), this);
        this.client.addListener(GetVaccinationByKeyResponse.class.toString(), this);
        this.client.addListener(CheckUserIdResponse.class.toString(), this);
        this.client.addListener(CheckEmailResponse.class.toString(), this);
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
            System.out.println("Registrazione ev: " + res.isEsito());
        }
        if(packet instanceof GetCVResponse){
            GetCVResponse res = (GetCVResponse) packet;
            List<CentroVaccinale> list = res.getCvList();
            for(CentroVaccinale cv : list){
                System.out.println(cv);
            }
        }
        if(packet instanceof GetEvTypologiesResponse){
            List<TipologiaEventoAvverso> list = ((GetEvTypologiesResponse)packet).getTypologies();
            list.forEach(System.out::println);
        }
        if(packet instanceof GetVaccinesResponse){
            List<Vaccino> list = ((GetVaccinesResponse)packet).getVaccines();
            list.forEach(System.out::println);
        }
        if(packet instanceof GetReportResponse){
            ReportCV report = ((GetReportResponse)packet).getReport();
            System.out.println(report.getReportList());
        }
        if(packet instanceof GetVaccinationByKeyResponse){
            GetVaccinationByKeyResponse res = (GetVaccinationByKeyResponse) packet;
            Vaccinazione v = res.getVaccination();
            System.out.println("Data: " + v.getDataVaccinazione());
        }
        if(packet instanceof CheckUserIdResponse){
            System.out.println("Esiste userId? " + ((CheckUserIdResponse)packet).isEsito());
        }
        if(packet instanceof CheckEmailResponse){
            System.out.println("Esiste email? " + ((CheckEmailResponse)packet).isEsito());
        }
    }


    /* Run per simulare il comportamento di una GUI */
    @Override
    public void run() {
        CentroVaccinale cv = new CentroVaccinale();
        cv.setId(11);
        client.getReport(cv);
        /*
        Vaccinato vaccinato = new Vaccinato();
        vaccinato.setCodiceFiscale("1111");
        vaccinato.setCognome("Masin");
        vaccinato.setNome("Davide");
        vaccinato.setUserId("davide");
        vaccinato.setEmail("davide.masin40@gmail.com");
        vaccinato.setPassword("davide");



        Vaccino vaccino = new Vaccino();
        vaccino.setId(6);

        Vaccinazione v = new Vaccinazione();
        v.setDataVaccinazione(new Date(System.currentTimeMillis()));
        v.setVaccinato(vaccinato);
        v.setVaccino(vaccino);
        v.setCentroVaccinale(cv);

        //client.insertVaccination(v);
        client.requestUserLogin("davide", "davide");
        EventoAvverso ev = new EventoAvverso();
        ev.setSeverita(5);
        ev.setNote("Prova database 3");
        ev.setCentroVaccinale(cv);
        TipologiaEventoAvverso tipologiaEV = new TipologiaEventoAvverso();
        tipologiaEV.setId(1);
        ev.setTipologia(tipologiaEV);
        client.insertEV(ev);
        */
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
