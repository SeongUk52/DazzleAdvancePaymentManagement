package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.Customer;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class DazzleAdvancePaymentManagementApplicationTests {

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void testJpa() {
		/*
		Store dazzle = new Store();
		dazzle.setStoreName("대즐");
		dazzle.setStorePaymentBalance(0);
		dazzle.setStoreDate(LocalDateTime.now());
		this.storeRepository.save(dazzle);
		 */
		/*
		Optional<Store> oq =this.storeRepository.findById(1);
		assertTrue(oq.isPresent());
		Store q = oq.get();

		Customer c1 = new Customer();
		c1.setCustomerName("강예구");
		c1.setCustomerJob("교수");
		c1.setCustomerPaymentBalance(396300);
		c1.setCustomerDate(LocalDateTime.now());
		c1.setChangePaymentBalance(0);
		c1.setStore(q);
		this.customerRepository.save(c1);

		 */

	}

}
