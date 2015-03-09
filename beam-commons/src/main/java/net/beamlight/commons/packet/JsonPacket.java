package net.beamlight.commons.packet;

import java.nio.charset.Charset;

import net.beamlight.commons.frame.BeamPacket;
import net.beamlight.commons.util.JsonUtils;

/**
 * @author gaofeihang
 * @since Mar 9, 2015
 */
public class JsonPacket extends BeamPacket {
    
    private static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    
    public JsonPacket(byte type, byte[] data) {
        super(type, data);
    }
    
    public JsonPacket(byte type, Object model) {
        super(type, modelToBytes(model));
    }
    
    public <T> T getModel(Class<T> clazz) {
        return JsonUtils.toT(new String(getData(), DEFAULT_CHARSET), clazz);
    }
    
    public void setModel(Object model) {
        setData(modelToBytes(model));
    }
    
    private static byte[] modelToBytes(Object model) {
        return JsonUtils.toJSON(model).getBytes(DEFAULT_CHARSET);
    }

}
