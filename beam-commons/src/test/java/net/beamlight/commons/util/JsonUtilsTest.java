package net.beamlight.commons.util;

import java.util.Map;

import net.beamlight.commons.test.Person;
import net.beamlight.commons.test.TestUtils;
import net.beamlight.commons.util.JsonUtils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on Jan 30, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public class JsonUtilsTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtilsTest.class);
	
	@Test
	public void testToJson() {
	    Person person = TestUtils.defaultPerson();
	    LOG.info(JsonUtils.toJSON(person));
	}
	
	@Test
	public void testToT() {
		Person person = TestUtils.defaultPerson();
		
		String jsonStr = JsonUtils.toJSON(person);
		LOG.info(jsonStr);
		
		person = JsonUtils.toT(jsonStr, Person.class);
		LOG.info(JsonUtils.prettyPrint(person));
	}
	
	@Test
	public void testToMap() {
	    Person person = TestUtils.defaultPerson();
		
		String jsonStr = JsonUtils.toJSON(person);
		LOG.info(jsonStr);
		
		Map<String, Object> map = JsonUtils.toMap(jsonStr);
		LOG.info(JsonUtils.prettyPrint(map));
	}
	
	@Test
	public void testPrettyPrint() {
	    Person person = TestUtils.defaultPerson();
		LOG.info(JsonUtils.prettyPrint(person));
	}

}
