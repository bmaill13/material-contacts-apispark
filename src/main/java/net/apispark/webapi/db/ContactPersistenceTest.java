package net.apispark.webapi.db;

import java.util.ArrayList;
import java.util.List;

import net.apispark.webapi.representation.Contact;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ContactPersistenceTest {

	@Before
	public void deleteAllData(){
		List<Contact> contacts = ContactPersistence.INSTANCE.getContacts();
		for (Contact contact : contacts) {
			ContactPersistence.INSTANCE.deleteContact(contact.getId());
		}	
	}
	
	@Test
	public void added_contact_is_correct(){
		//GIVEN
		Contact c = new Contact();
		c.setFirstName("Baptiste");
		c.setLastName("Maillot");
		c.setGender("Male");
		
		//WHEN
		ContactPersistence.INSTANCE.addContact(c);
		
		//THEN
		Assert.assertNotNull(c.getId());
	}
	
	@Test
	public void added_contact_is_well_retrieved_by_id(){
		//GIVEN
		Contact c = new Contact();
		c.setFirstName("Sonia");
		c.setLastName("Pigot");
		c.setGender("Femelle");
		ContactPersistence.INSTANCE.addContact(c);
		
		//WHEN
		Contact c2 = ContactPersistence.INSTANCE.getContact(c.getId());
		
		//THEN
		Assert.assertEquals(c.getId(), c2.getId());
	}
	
	@Test
	public void added_contacts_are_well_retrieved(){
		//GIVEN
		Contact c1 = new Contact();
		c1.setFirstName("Micael");
		c1.setLastName("MBagira");
		c1.setGender("Male");
		ContactPersistence.INSTANCE.addContact(c1);
		Contact c2 = new Contact();
		c2.setFirstName("Melyna");
		c2.setLastName("Bon");
		c2.setGender("Femelle");
		ContactPersistence.INSTANCE.addContact(c2);
		
		
		//WHEN
		List<Contact> contacts2 = ContactPersistence.INSTANCE.getContacts();
		
		//THEN
		List<Contact> contacts1 = new ArrayList<Contact>();
		contacts1.add(c1);
		contacts1.add(c2);
		
		Assert.assertEquals(contacts1, contacts2);
	}
}
