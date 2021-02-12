package com.book.AddressBook;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BuddyInfo> buddy;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String bookName;


    public AddressBook() {
        buddy = new ArrayList<BuddyInfo>();
    }

    public AddressBook(BuddyInfo... buds) {
        buddy = new ArrayList<BuddyInfo>();
        for (BuddyInfo b:buds) {
            buddy.add(b);
        }
    }

    /**
     * Gets the id of this Buddy.
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id of this Buddy to the specified value.
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * Getter for the list of buddies
     * @return
     */
    public List<BuddyInfo> getBuddy() {
        return buddy;
    }

    /**
     * Setter for the list of buddies
     * @param buddy
     */
    public void setBuddy(List<BuddyInfo> buddy) {
        this.buddy = buddy;
    }

    /**
     * Method to add a buddy to the addressbook.AddressBook
     * @param aBuddyInfo the buddy object to add
     */
    public void addBuddy(BuddyInfo aBuddyInfo) {
        if(aBuddyInfo != null) {
            buddy.add(aBuddyInfo);
        }
    }

    /**
     * Method to remove a buddy from the addressbook.AddressBook
     */
    public void removeBuddy(int bud1) {
        if(bud1<buddy.size()){
            buddy.remove(bud1);
        }
    }

    public void removeBuddyById(Long bud){
        int val=0;
        for(int i = 0; i<buddy.size();i++){
            if(buddy.get(i).getId() == bud){
                val = i;
            }
        }
        buddy.remove(val);
    }


    /**
     * Method to print out the information for each addressbook.BuddyInfo object
     * @return A string value representing the formatted information
     */
    @Transient
    public String printAllBuddyInformation() {
        String info = "";
        for(BuddyInfo bud: buddy) {
            info += bud.toString() + "\n";
        }
        return info;
    }


    /**
     * gets the text from the buddyInfo Object and returns it as a string
     * @return a string which contains the formatted buddy information
     */
    public String printBuddyValues() {
        String s = "";
        for(int i=0; i < buddy.size(); i++) {
            s+=buddy.get(i).toString()+"\n";
        }
        return s;
    }

    public static void main(String[] args) {
        BuddyInfo buddy1 = new BuddyInfo("Bob", "123 Ottawa Ave", "1234567890");
        BuddyInfo buddy2 = new BuddyInfo("Mack", "345 Toronto Drive", "1278561234");
        BuddyInfo buddy3 = new BuddyInfo("Lily", "928 Montreal Street", "1238797650");
        BuddyInfo buddy4 = new BuddyInfo("Skyler", "45 Edmonton Crescent", "1753564534");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);
        addressBook.addBuddy(buddy3);
        addressBook.addBuddy(buddy4);
        System.out.println(addressBook.printAllBuddyInformation());
    }

}
