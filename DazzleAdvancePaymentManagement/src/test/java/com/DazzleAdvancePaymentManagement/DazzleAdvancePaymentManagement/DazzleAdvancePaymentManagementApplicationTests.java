package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class DazzleAdvancePaymentManagementApplicationTests {

	@Autowired
	private StoreRepository storeRepository;

	@Test
	void testJpa() {
		Store dazzle = new Store();
		dazzle.setStoreName("대즐");
		dazzle.setStorePaymentBalance(0);
		dazzle.setStoreDate(LocalDateTime.now());
		this.storeRepository.save(dazzle);
	}

}
