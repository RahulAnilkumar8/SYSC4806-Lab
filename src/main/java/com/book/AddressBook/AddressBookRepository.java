package com.book.AddressBook;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "addressbooks", path="addressbooks")
public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {

    List<AddressBook> findAll();
}
