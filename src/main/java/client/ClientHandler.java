package client;

import datatypes.CentroVaccinale;
import datatypes.EventoAvverso;
import datatypes.Vaccinato;
import datatypes.Vaccinazione;
import datatypes.protocolmessages.*;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandler {
    private static final int PORT = 9123;
    private NioSocketConnector connector = null;
    private IoSession session = null;
    private final Map<String, PacketReceivedListener> listeners;

    public ClientHandler(){
        listeners = new ConcurrentHashMap<>();
    }

    public boolean connect() {
        connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(2000);
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.setHandler(new ClientConnectionHandler(listeners));
        try {
            ConnectFuture future = connector.connect(new InetSocketAddress(PORT));
            future.awaitUninterruptibly();
            session = future.getSession();
        } catch (RuntimeIoException e) {
            System.err.println("Failed to connect.");
            connector.dispose();
            connector = null;
            session = null;
            return false;
        }
        return true;
    }

    public boolean disconnect(){
        try {
            session.getCloseFuture().awaitUninterruptibly();
            connector.dispose();
        }
        catch(Exception ex){ return false; }
        return true;
    }

    public void addListener(String event, PacketReceivedListener ipr){
        listeners.put(event,ipr);
    }

    public boolean requestUserRegistration(Vaccinato vaccinato, String key){
        if(session == null) return false;
        session.write(new UserRegistrationRequest(vaccinato, key));
        return true;
    }

    public boolean requestUserLogin(String username, String password) {
        if(session == null) return false;
        session.write(new UserLoginRequest(username, password));
        return true;
    }

    public boolean insertCV(CentroVaccinale cv){
        if(session == null) return false;
        session.write(new RegistrationCVRequest(cv));
        return true;
    }

    public boolean insertVaccination(Vaccinazione v){
        if(session == null) return false;
        session.write(new RegistrationVaccinatedRequest(v));
        return true;
    }

    public boolean insertEV(EventoAvverso ev, Vaccinato vaccinato){
        if(session == null) return false;
        session.write(new RegistrationEVRequest(ev, vaccinato));
        return true;
    }

}
