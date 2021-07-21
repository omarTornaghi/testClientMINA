package client;


import datatypes.protocolmessages.Packet;

public interface PacketReceivedListener {
    void onPacketReceived(Packet packet);
}
