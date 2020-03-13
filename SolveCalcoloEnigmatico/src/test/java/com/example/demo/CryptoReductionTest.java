package com.example.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.persistence.services.OperationHelper;

@RunWith(SpringJUnit4ClassRunner.class)
public class CryptoReductionTest {

	@Test
	public void CrytoAlgoTest() {
		
		String reduced1 = OperationHelper.ReduceToCrypto("aba:c=de");
				
		String reduced2 = OperationHelper.ReduceToCrypto("fgh+fi=fbl");
				
		String reduced3 = OperationHelper.ReduceToCrypto("efd-fel=fih");
		
		assertTrue(reduced1.equals("aba:c=de"));
		assertTrue(reduced2.equals("abc+ad=aef"));
		assertTrue(reduced3.equals("abc-bad=bef"));
	}

}
