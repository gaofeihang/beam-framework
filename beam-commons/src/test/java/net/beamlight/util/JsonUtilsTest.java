package net.beamlight.util;

import java.util.Map;

import net.beamlight.bean.Person;

import org.junit.Test;

/**
 * @author gaofeihang
 * @since Jan 30, 2015
 */
public class JsonUtilsTest {
	
	private Person defaultPerson() {
		Person person = new Person();
		person.setName("beamlight");
		person.setAge(1);
		return person;
	}
	
	@Test
	public void testToJson() {
		System.out.println(JsonUtils.toJSON(defaultPerson()));
	}
	
	@Test
	public void testToT() {
		Person person = defaultPerson();
		
		String jsonStr = JsonUtils.toJSON(person);
		System.out.println(jsonStr);
		
		person = JsonUtils.toT(jsonStr, Person.class);
		System.out.println(JsonUtils.prettyPrint(person));
	}
	
	@Test
	public void testToMap() {
		Person person = defaultPerson();
		
		String jsonStr = JsonUtils.toJSON(person);
		System.out.println(jsonStr);
		
		Map<String, Object> map = JsonUtils.toMap(jsonStr);
		System.out.println(map);
		System.out.println(JsonUtils.prettyPrint(map));
	}
	
	@Test
	public void testPrettyPrint() {
		Person person = defaultPerson();
		System.out.println(JsonUtils.prettyPrint(person));
	}

}
