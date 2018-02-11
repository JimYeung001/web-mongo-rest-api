package com.jim.webrestmongo;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jim.webrestmongo.model.Profile;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebServiceProfilesTest {
	
	private static final String URI = "/profiles";
	private static final String URI_ONE_VAR = "/profiles/{lastName}";
	
	@Autowired
	private MockMvc mockMvc;
	
	protected ObjectMapper mapper;
	
	
	@Before
	public void setUp() {
		mapper = new ObjectMapper();		
		
	}
	
	@Test
	public void fetch_profile_by_last_name_and_return_list_profiles() throws Exception {
		this.mockMvc.perform(get(URI_ONE_VAR, "Yeung")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(greaterThan(0))))
			.andExpect(jsonPath("$[0].lastName", is("Yeung")));
	}
	
	@Test 
	public void create_new_profile_and_returned_saved_profile() throws Exception {
		Profile profile = new Profile("jim.yang101@hotmail.com", "Jim", "Yeung", 45, "123 Fake Road, New York, Canada");	
		String json = mapper.writeValueAsString(profile);
		
		this.mockMvc.perform(post(URI)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(notNullValue())));
	}
	
	
	@Test
	public void add_new_profile_then_delete_the_profile() throws Exception {
		Profile profile = new Profile("jim2.yang101@gmail.com", "Jim2", "Yeung3", 45, "12345 Fake Road, New York, Canada");
		String json = mapper.writeValueAsString(profile);
		String response = this.mockMvc.perform(post(URI)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andReturn().getResponse().getContentAsString();
		Profile saved = mapper.readValue(response, Profile.class);
		json = mapper.writeValueAsString(saved);
				
		this.mockMvc.perform(delete(URI)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andExpect(status().isOk());
		
	}
	
	

}
