package com.book.AddressBook;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressBookNonMockTest {

        @LocalServerPort
        private int port;

        @Autowired
        private AddressBookController controller;

        @Autowired
        private TestRestTemplate restTemplate;

        @Test
        void contextLoads() {
            assertThat(controller).isNotNull();
        }

	@Test
	public void addBook() throws Exception{
        AddressBook book = new AddressBook();
        book.setBookName("Book 1");
        ResponseEntity<String> result = this.restTemplate.postForEntity("http://localhost:" + port + "/addressbook", book , String.class);
        assertEquals(200, result.getStatusCodeValue());
	}

    @Test
    public void getBuddy() throws Exception{
        ResponseEntity<String> result = this.restTemplate.getForEntity("http://localhost:" + port + "/addressbook", String.class);
        assertEquals(200, result.getStatusCodeValue());
    }

}
