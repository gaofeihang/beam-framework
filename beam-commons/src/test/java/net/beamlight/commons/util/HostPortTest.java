package net.beamlight.commons.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author gaofeihang
 * @since Feb 11, 2015
 */
public class HostPortTest {
    
    @Test
    public void testNormal() {
        String url = "127.0.0.1:8080";
        HostPort hostPort = HostPort.parse(url);
        Assert.assertEquals("127.0.0.1", hostPort.getHost());
        Assert.assertEquals(8080, hostPort.getPort());
    }
    
    @Test
    public void testUrl() {
        String url = "127.0.0.1:8080?name=foo";
        HostPort hostPort = HostPort.parse(url);
        Assert.assertEquals("127.0.0.1", hostPort.getHost());
        Assert.assertEquals(8080, hostPort.getPort());
    }

}
