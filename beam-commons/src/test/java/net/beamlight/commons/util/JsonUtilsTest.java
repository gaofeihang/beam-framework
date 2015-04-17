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
    
    private static final Logger logger = LoggerFactory.getLogger(JsonUtilsTest.class);
	
	@Test
	public void testToJson() {
	    Person person = TestUtils.defaultPerson();
	    logger.info(JsonUtils.toJSON(person));
	}
	
	@Test
	public void testToT() {
		Person person = TestUtils.defaultPerson();
		
		String jsonStr = JsonUtils.toJSON(person);
		logger.info(jsonStr);
		
		person = JsonUtils.toT(jsonStr, Person.class);
		logger.info(JsonUtils.prettyPrint(person));
	}
	
	@Test
	public void testToMap() {
	    Person person = TestUtils.defaultPerson();
		
		String jsonStr = JsonUtils.toJSON(person);
		logger.info(jsonStr);
		
		Map<String, Object> map = JsonUtils.toMap(jsonStr);
		logger.info(JsonUtils.prettyPrint(map));
	}
	
	@Test
	public void testPrettyPrint() {
	    Person person = TestUtils.defaultPerson();
		logger.info(JsonUtils.prettyPrint(person));
	}

}
