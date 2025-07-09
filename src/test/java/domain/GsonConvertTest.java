package domain;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GsonConvertTest {
	@ToString
	@AllArgsConstructor
	class Protoss {
	    String myLifeForAiur;
	    String prepareToDie;
	}
	
	private Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES).create();
	
	@Test
	public void testToJson() {
		Protoss protoss = new Protoss("질럿", "커세어");
		String result = gson.toJson(protoss);
		log.info(result);
	}
	
	@Test
	public void testFromJson() {
		String input = "{\"MY_LIFE_FOR_AIUR\":\"zealot\",\"PREPARE_TO_DIE\":\"corsair\"}";
		Protoss protoss = gson.fromJson(input, Protoss.class);
		log.info("{}", protoss);
	}
}	
