package client;

import datatypes.protocolmessages.Packet;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.util.Map;

public class ClientConnectionHandler extends IoHandlerAdapter {
    private final Map<String, PacketReceivedListener> listeners;
    
    public ClientConnectionHandler(Map<String, PacketReceivedListener> listeners) {
        this.listeners = listeners;
    }
    IoSession session;

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        this.session = session;
    }

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {
    }
    @Override
    public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {

    }

    @Override
    public void messageReceived(IoSession ioSession, Object message) throws Exception {
        Packet packet = (Packet) message;
        PacketReceivedListener listener = listeners.get(packet.getPacketName());
        if(listener != null)
            listener.onPacketReceived(packet);
    }

}
