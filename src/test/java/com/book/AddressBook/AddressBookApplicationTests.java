package com.book.AddressBook;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AddressBookApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private AddressBookController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void addBook() throws Exception{
		AddressBook book = new AddressBook();
		book.setBookName("Book 1");
		BuddyInfo buddy = new BuddyInfo("First Name", "123 Address", "123456789012");
		mockMvc.perform( MockMvcRequestBuilders
				.post("/addressbook")
				.content(asJsonString(book))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

		//Test Adding BuddyInfo
		mockMvc.perform( MockMvcRequestBuilders
				.post("/addressbook/1/BuddyInfo")
				.content(asJsonString(buddy))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("123 Address")));
	}

	@Test
	public void getBook() throws Exception{
		mockMvc.perform(get("/addressbook")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testRemoveBuddyInfo() throws Exception {
		mockMvc.perform(delete("/addressBook/1/BuddyInfo/2"))
				.andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



}
