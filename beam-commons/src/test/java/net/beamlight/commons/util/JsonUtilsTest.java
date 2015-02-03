package net.beamlight.commons.util;

import java.util.Map;

import net.beamlight.commons.test.Person;
import net.beamlight.commons.test.TestUtils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gaofeihang
 * @since Jan 30, 2015
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
