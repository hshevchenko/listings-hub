package com.businessbook.unit.domains.users;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.businessbook.domain.users.controllers.UsersRestController;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersRestController.class)
public class UsersRestControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UsersRestController mockUsersRestController;
	
	
}
