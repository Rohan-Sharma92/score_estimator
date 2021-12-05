package com.sellics.score_estimator.controller;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScoreEstimatorControllerTest {


	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext applicationContext;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}
	
	@Test
	public void testScoreEstimateForCommonWord() throws Exception {
		mockMvc.perform(get("/api/estimate").param("keyword", "samsung").contentType("text/plain"))
		.andExpect(status().isOk()).andExpect(jsonPath("score", is(100)));
	}
	
	@Test
	public void testScoreEstimateForPartialMatchingWord() throws Exception {
		mockMvc.perform(get("/api/estimate").param("keyword", "MI power bank").contentType("text/plain"))
		.andExpect(status().isOk()).andExpect(jsonPath("score", is(91)));
	}
	
	@Test
	public void testScoreEstimateForNoMatchingWord() throws Exception {
		mockMvc.perform(get("/api/estimate").param("keyword", "shsgh").contentType("text/plain"))
		.andExpect(status().isOk()).andExpect(jsonPath("score", is(0)));
	}
}
