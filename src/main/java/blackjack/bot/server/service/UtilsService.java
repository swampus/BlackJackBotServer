package blackjack.bot.server.service;

import blackjack.bot.server.storage.model.Card;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilsService {
	public String createRandomId() {
		return String.valueOf(UUID.randomUUID());
	}
}
