package net.beamlight.core.serialize;

import net.beamlight.commons.util.MsgPackUtils;

/**
 * @author gaofeihang
 * @since Mar 11, 2015
 */
public class MsgPackSerializer implements Serializer {

    @Override
    public byte[] serialize(Object obj) {
        return MsgPackUtils.encode(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return MsgPackUtils.decode(bytes, clazz);
    }

}
