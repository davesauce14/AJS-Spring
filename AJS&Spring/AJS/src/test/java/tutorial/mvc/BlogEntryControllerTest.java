package tutorial.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.jayway.jsonpath.*;

import tutorial.core.services.BlogEntryService;
import tutorial.rest.mvc.BlogEntryController;
import tutorial.core.entities.BlogEntry;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;

//import java.awt.PageAttributes.MediaType;
//import javax.ws.rs.core.MediaType;
import org.springframework.http.MediaType;



public class BlogEntryControllerTest {
	
	@InjectMocks
	public BlogEntryController controller;
	
	public MockMvc mockmvc;
	
	@Mock
	public BlogEntryService service;  
	
	@Before
	public void setup(){
		
		MockitoAnnotations.initMocks(this);		
		mockmvc = MockMvcBuilders.standaloneSetup(controller).build();		
	}
	
/*	@Test
	public void test() throws Exception{
		mockmvc.perform(post("/test")
				.content("{\"title\":\"balls in a can\"}")
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(jsonPath("$.title", is("balls in a can")))
				.andDo(print());
	}*/
	
	@Test
	public void getExistingBlogEntry() throws Exception{
		BlogEntry entry = new BlogEntry();
		entry.setId(1L);
		entry.setTitle("Jabba the gould");
		
		when(service.find(1L)).thenReturn(entry);
		mockmvc.perform(get("/rest/blog-entries/1"))
		.andExpect(jsonPath("$.title",is(entry.getTitle())))
		.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/rest/blog-entries/1"))))
		.andExpect(status().isOk()).andDo(print());
	}
	

}
