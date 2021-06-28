package in.dilshad.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.dilshad.dto.MemberDTO;
import in.dilshad.service.MemberService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testValidLogin() throws Exception {
		System.out.println("Inside test case - Valid");
		MemberDTO memberObj = new MemberDTO();

		memberObj.setEmail("dil@gmail.com");
		memberObj.setPassword("Dilshad123");

		String memberJson = new ObjectMapper().writeValueAsString(memberObj);

		mockMvc.perform(
				post("/motorcycleapp/v1/auth/member/login").contentType(MediaType.APPLICATION_JSON)
				.content(memberJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.email").value("dil@gmail.com"))
				.andExpect(jsonPath("$.password").value("Dilshad123"));
	}

	@Test
	public void testInvalidLogin() throws Exception {
		MemberDTO memberObj = new MemberDTO();
		memberObj.setEmail("dilshad@gmail.com");
		memberObj.setPassword("pass123");
		String memberJson = new ObjectMapper().writeValueAsString(memberObj);
		mockMvc.perform(
				post("/motorcycleapp/v1/auth/member/login").contentType(MediaType.APPLICATION_JSON).content(memberJson))
		.andExpect(status().isUnauthorized())
		.andExpect(jsonPath("$.errorMessage").value("Invalid email/password"));
	}

}
