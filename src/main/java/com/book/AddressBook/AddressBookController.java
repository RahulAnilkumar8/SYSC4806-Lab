package com.book.AddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the AddressBook. Please refer to the quickguide.txt file in the project directory for steps to
 * add an addressbook, buddies and to view the template in a step by step format.
 */
@Controller
public class AddressBookController {

    private AddressBookRepository repository;

    @Autowired
    public void setRepository(AddressBookRepository repository) {
        this.repository = repository;
    }

    public AddressBookRepository getRepository() {
        return repository;
    }

    @GetMapping("/")
    public String getLandingPage(Model model) {
        model.addAttribute("addbook", new AddressBook());
        return "landingpage";
    }

    @GetMapping("/insertBuddyInfo/{bookid}")
    public String InsertBuddyForm( @PathVariable  Long bookid, Model model) {
        model.addAttribute("bookId", bookid);
        model.addAttribute("buddy", new BuddyInfo());
        return "insertBuddy";
    }

    /**
     * Custom route to add an addressbook.
     * This endpoint is accessed at http://localhost:8080/addressbook
     * @return
     */
    @PostMapping("/addaddressbook")
    public String createAddressBook(@ModelAttribute AddressBook book, Model model){
        repository.save(book);
        model.addAttribute("book", book);
        return "bookList";
    }

    /**
     * Custom route to add a buddy to an addressbook
     * @param buddyInfo
     * @param id
     * @return
     */
    @PostMapping("/addressbook/buddyInput/{id}")
    public String addBuddy(@ModelAttribute BuddyInfo buddyInfo, @PathVariable Long id, Model model){
        AddressBook book = repository.findById(id).get();
        if(book != null){
            System.out.println(buddyInfo.toString());
            book.addBuddy(buddyInfo);
            repository.save(book);
            model.addAttribute("addressbook", book);
            return "addressbookui";
        }
        else{
            return "viewBook";
        }
    }


    /**
     * Route to access the template page for an added address book.
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/addressbook/{id}")
    public String getAddressBookById(@PathVariable Long id, Model model){
        AddressBook book = repository.findById(id).get();
        model.addAttribute("addressbook", book);
        return "addressbookui";
    }


    /**
     * Custom route to get an addressbook.To get the standard route provided by spring boot rest repositories
     * use /addressbooks endpoint.
     * @return
     */
    @GetMapping(value = "/addressbook")
    @ResponseBody
    public List<AddressBook> getAllAddressBooks(){
        return repository.findAll();
    }


    /**
     * Custom route to add an addressbook.
     * This endpoint is accessed at http://localhost:8080/addressbook
     * @param addressBook
     * @return
     */
    @PostMapping(path="/addressbook", produces=MediaType.APPLICATION_JSON_VALUE,consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AddressBook createAddressBook(@RequestBody AddressBook addressBook){
        return repository.save(addressBook);
    }

    /**
     * Custom route to add a buddy to an addressbook
     * @param buddyInfo
     * @param id
     * @return
     */
    @PostMapping("/addressbook/{id}/BuddyInfo")
    @ResponseBody
    public AddressBook addBuddyInfo(@RequestBody BuddyInfo buddyInfo, @PathVariable Long id){
        AddressBook book = repository.findById(id).get();
        if(book != null){
            System.out.println(buddyInfo.toString());
            book.addBuddy(buddyInfo);
            repository.save(book);
            return book;
        }
        else{
            return null;
        }
    }

    /**
     * Custom route to delete a buddyinfo object
     * @param id
     * @param buddyId
     * @return
     */
    @DeleteMapping("/addressBook/{id}/BuddyInfo/{buddyId}")
    @ResponseBody
    public AddressBook deleteBuddy(@PathVariable Long id, @PathVariable Long buddyId) {
        System.out.println(buddyId);
        System.out.println(id);
        AddressBook book = repository.findById(id).get();
        book.removeBuddyById(buddyId);
        System.out.println(book.printAllBuddyInformation());
        return repository.save(book);
    }

}
