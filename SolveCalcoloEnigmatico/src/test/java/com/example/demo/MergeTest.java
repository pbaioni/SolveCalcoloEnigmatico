package com.example.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.model.Key;
import app.model.helper.OperationHelper;

@RunWith(SpringJUnit4ClassRunner.class)
public class MergeTest {

	@Test
	public void CrytoAlgoTest() {
		
		Key key = new Key("aba:c=de", "424:8=53");

		assertTrue(key.getKeyAsString().equals("??bead??c?"));
		
		assertTrue(key.isCompatibleResult("fghfifbl", "10917126"));
		
		key.mergeResult("fgh+fi=fbl", "109+17=126");
		
		assertTrue(key.getKeyAsString().equals("gfbeadlich"));
		
		assertTrue(key.isCompatibleResult("efdfelfih", "315136179"));

	}

}
