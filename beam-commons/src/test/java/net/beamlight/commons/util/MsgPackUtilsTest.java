package net.beamlight.commons.util;

import java.util.Map;

import net.beamlight.commons.test.Person;
import net.beamlight.commons.test.TestUtils;
import net.beamlight.commons.util.ByteArrayUtils;
import net.beamlight.commons.util.JsonUtils;
import net.beamlight.commons.util.MsgPackUtils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on Jan 30, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public class MsgPackUtilsTest {
    
    private static final Logger logger = LoggerFactory.getLogger(MsgPackUtilsTest.class);
	
	@Test
	public void testEncode() {
	    Person person = TestUtils.defaultPerson();
	    
	    byte[] bytes = MsgPackUtils.encode(person);
	    logger.info(ByteArrayUtils.prettyPrint(bytes));
	}
	
	@Test
	public void testDecode() {
		Person person = TestUtils.defaultPerson();
		
		byte[] bytes = MsgPackUtils.encode(person);
		logger.info(ByteArrayUtils.prettyPrint(bytes));
		
		person = MsgPackUtils.decode(bytes, Person.class);
		logger.info(JsonUtils.prettyPrint(person));
	}
	
	@Test
	public void testToMap() {
	    Person person = TestUtils.defaultPerson();
        
        byte[] bytes = MsgPackUtils.encode(person);
        logger.info(ByteArrayUtils.prettyPrint(bytes));
		
		Map<String, Object> map = MsgPackUtils.toMap(bytes);
		logger.info(JsonUtils.prettyPrint(map));
	}
	
    @Test
    public void testException() {
        Map<String, Object> map = MsgPackUtils.toMap(new byte[] { 1 });
        logger.info(JsonUtils.prettyPrint(map));
    }

}
