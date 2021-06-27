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

import in.dilshad.dto.MemberDetailsDTO;
import in.dilshad.service.MemberService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;


	@MockBean
	private MemberService userServiceMock;

	@InjectMocks
	AuthController authController;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testValidLogin() throws Exception {
		MemberDetailsDTO memberObj = new MemberDetailsDTO();

		memberObj.setEmailId("dilshad@gmail.com");
		memberObj.setPassword("pass123#");

		String memberJson = new ObjectMapper().writeValueAsString(memberObj);

		mockMvc.perform(
				post("/motorcycleapp/v1/auth/member/login").contentType(MediaType.APPLICATION_JSON)
				.content(memberJson))
		.andExpect(status().isOk()).andExpect(jsonPath("$.emailId").value("dilshad@gmail.com"))
		.andExpect(jsonPath("$.password").value("pass123#"));
	}

	@Test
	public void testInvalidLogin() throws Exception {
		MemberDetailsDTO memberObj = new MemberDetailsDTO();
		memberObj.setEmailId("dilshad@gmail.com");
		memberObj.setPassword("pass123");
		String memberJson = new ObjectMapper().writeValueAsString(memberObj);
		mockMvc.perform(
				post("/motorcycleapp/v1/auth/member/login").contentType(MediaType.APPLICATION_JSON).content(memberJson))
		.andExpect(status().isUnauthorized())
		.andExpect(jsonPath("$.errorMessage").value("Invalid email/password"));
	}

}
