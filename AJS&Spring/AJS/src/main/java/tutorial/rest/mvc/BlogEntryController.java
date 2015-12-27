package tutorial.rest.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tutorial.core.entities.BlogEntry;
import tutorial.core.services.BlogEntryService;
import tutorial.rest.resources.BlogEntryResource;
import tutorial.rest.resources.asm.BlogEntryResourceAsm;

@Controller
public class BlogEntryController {

	private BlogEntryService service;
	
	public BlogEntryController(BlogEntryService service) {
		 this.service = service;
	}
	
	@RequestMapping(value = "/rest/blog-entries/{entryId}", method = RequestMethod.GET)
	public @ResponseBody BlogEntryResource getEntry(@PathVariable Long entryId){
		BlogEntry entry =  service.find(entryId);
		BlogEntryResource res = new BlogEntryResourceAsm().toResource(entry);
		return res;
	}
}
