package blackjack.bot.server.validator;

import blackjack.bot.server.exception.ObjectAlreadyExistsException;
import blackjack.bot.server.exception.ObjectIsNotExists;
import blackjack.bot.server.storage.model.Casino;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class ValidatorUnitTest {

	@Test
	public void testDatabaseContainsEqualsTrue() throws Exception {
		Validator<Casino> validator = new CasinoValidator();
		Casino db = new Casino("N", "A");
		Casino nw = new Casino("N", "A");
		try {
			validator.databaseContainsEquals(db, nw);
			fail();
		} catch (ObjectAlreadyExistsException e) {
			assertEquals("Already Exists in database! - Casino", e.getMessage());
		}
	}

	@Test
	public void checkExistsTest() throws Exception {
		Validator<Casino> validator = new CasinoValidator();
		assertEquals(new Casino("1", "2"), validator.checkExists(new Casino("1", "2")));
	}

	@Test
	public void checkExistsException() throws Exception {
		Validator<Casino> validator = new CasinoValidator();
		try {
			validator.checkExists(null);
			fail();
		} catch (ObjectIsNotExists e) {
			assertEquals("adas", e.getMessage());
		}
	}

	@Test
	public void testDatabaseContainsEqualsFalse() throws Exception {
		Validator<Casino> validator = new CasinoValidator();
		Casino db = new Casino("N", "A");
		Casino nw = new Casino("N", "B");
		try {
			validator.databaseContainsEquals(db, nw);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testDatabaseContainsEqualsNull() throws Exception {
		Validator<Casino> validator = new CasinoValidator();
		Casino db = null;
		Casino nw = new Casino("N", "B");
		try {
			validator.databaseContainsEquals(db, nw);
		} catch (Exception e) {
			fail();
		}
	}
}
