package datatypes;

import java.io.Serializable;

public class EventoAvverso implements Serializable {
    private static final long serialVersionUID = 1844985672063253560L;
    private int id;
    private int severita;
    private String note;
    private CentroVaccinale centroVaccinale;
    private Vaccino vaccino;
    private TipologiaEventoAvverso tipologia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeverita() {
        return severita;
    }

    public void setSeverita(int severita) {
        this.severita = severita;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CentroVaccinale getCentroVaccinale() {
        return centroVaccinale;
    }

    public void setCentroVaccinale(CentroVaccinale centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
    }

    public Vaccino getVaccino() {
        return vaccino;
    }

    public void setVaccino(Vaccino vaccino) {
        this.vaccino = vaccino;
    }

    public TipologiaEventoAvverso getTipologia() {
        return tipologia;
    }

    public void setTipologia(TipologiaEventoAvverso tipologia) {
        this.tipologia = tipologia;
    }
}
