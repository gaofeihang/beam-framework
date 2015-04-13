package net.beamlight.remoting.benchmark;

import net.beamlight.commons.frame.BeamRequest;
import net.beamlight.commons.frame.BeamResponse;
import net.beamlight.commons.util.ThreadUtils;
import net.beamlight.core.serialize.Codec;
import net.beamlight.remoting.BeamClient;
import net.beamlight.remoting.BeamPacket;
import net.beamlight.remoting.Protocol;
import net.beamlight.remoting.util.PacketUtils;

/**
 * @author gaofeihang
 * @since Mar 11, 2015
 */
public class BeamClientBootstrap {
    
    private int msgCount;
    private BeamClient client;
    
    public BeamClientBootstrap(BeamClient client, int msgCount) {
        this.client = client;
        this.msgCount = msgCount;
    }
    
    public void start() {
        try {
            client.open();
            
            for (int i = 0; i < msgCount; i++) {
                BeamPacket respPacket = client.sendAndGet(
                        PacketUtils.encodeRequest(new BeamRequest("test"), Protocol.CMD_REQUEST, Codec.MSGPACK));
                BeamResponse response = PacketUtils.decode(respPacket, BeamResponse.class);
                System.out.println(response);
            }
            
            ThreadUtils.sleep(100);
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
