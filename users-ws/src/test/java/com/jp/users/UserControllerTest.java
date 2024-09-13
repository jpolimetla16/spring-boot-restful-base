package com.jp.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

	@MockBean
	private UserService userService;

	@Spy
	private ModelMapper mapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testCreateUser_whenValidInput_Return201() throws Exception {
		UserDetailsRequest request = new UserDetailsRequest("jpolimetla@gmail.com", "admin");
		//UserDto createUserDto = new UserDto("jpolimetla@gmail.com","admin");
		UserDto savedUserDto = new UserDto(1,"jpolimetla@gmail.com","admin");
		UserDetailsResponse expectedResponse = new UserDetailsResponse(1,"jpolimetla@gmail.com");
		//when(mapper.map(request, UserDto.class)).thenReturn(createUserDto);
		when(userService.createUser(any(UserDto.class))).thenReturn(savedUserDto);
		//when(mapper.map(savedUserDto, UserDetailsResponse.class)).thenReturn(expectedResponse);
		
		MvcResult result = mockMvc.perform(post("/api/v1/users")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isCreated())
				.andReturn();
		
		
	
		String actualResponse = result.getResponse().getContentAsString();
		System.out.println("JP:"+actualResponse);
		
		assertEquals(objectMapper.writeValueAsString(expectedResponse), actualResponse);
	}

}
