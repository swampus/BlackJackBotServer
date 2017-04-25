package blackjack.bot.server.service.strategy.card.calculation.factory;

import blackjack.bot.server.exception.UnknownCalculationSystem;
import blackjack.bot.server.service.strategy.card.calculation.HalvesSystem;
import blackjack.bot.server.service.strategy.card.calculation.KoSystem;
import blackjack.bot.server.service.strategy.card.calculation.OmegaTwoSystem;
import blackjack.bot.server.service.strategy.card.calculation.ZenCountSystem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(MockitoJUnitRunner.class)
public class CalculationSystemFactoryUnitTest {

	@Mock
	private HalvesSystem halvesSystem;

	@Mock
	private KoSystem koSystem;

	@Mock
	private OmegaTwoSystem omegaTwoSystem;

	@Mock
	private ZenCountSystem zenCountSystem;

	@InjectMocks
	private CalculationSystemFactory calculationSystemFactory = new CalculationSystemFactory();

	@Test
	public void testCreateCardCalculationSystem() throws Exception {
		assertEquals(halvesSystem,
				calculationSystemFactory.createCardCalculationSystem("Halves"));
		assertEquals(koSystem,
				calculationSystemFactory.createCardCalculationSystem("Ko"));
		assertEquals(omegaTwoSystem,
				calculationSystemFactory.createCardCalculationSystem("OmegaTwo"));
		assertEquals(zenCountSystem,
				calculationSystemFactory.createCardCalculationSystem("ZenCount"));
	}

	@Test
	public void testUnknownSystem() throws Exception {
		try {
			assertEquals(halvesSystem,
					calculationSystemFactory.createCardCalculationSystem("Unknown"));
			fail();
		} catch (UnknownCalculationSystem e) {
			assertEquals("Unknown CalculationSystem: Unknown", e.getMessage());
		}
	}
}
