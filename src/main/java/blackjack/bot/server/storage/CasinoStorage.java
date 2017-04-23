package blackjack.bot.server.storage;

import blackjack.bot.server.storage.model.Casino;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
public class CasinoStorage extends AbstractStorage implements Storage<Casino> {

	private static String CASINO_LIST = "casinoList";

	@Override
	public Observable<Void> put(Casino casino) {
		return reactiveCommands.lpush(CASINO_LIST, casino.getName())
				.flatMap(t -> reactiveCommands
						.set(casino.getName(), casino.getAddress())
						.map(c -> null));
	}

	@Override
	public Observable<Casino> get(String casinoName) {
		return reactiveCommands.get(casinoName)
				.map(t -> new Casino(casinoName, t));
	}

	@Override
	public Observable<Void> delete(String casinoName) {
		return super.delete(casinoName)
				.flatMap(t -> reactiveCommands.lrem(StorageConstants.CASINO_LIST, 1, casinoName))
				.map(t -> null);
	}

	public Observable<Casino> getAll() {
		return reactiveCommands.lrange(CASINO_LIST, 0, Long.MAX_VALUE)
				.flatMap(casinoName -> reactiveCommands.get(casinoName)
						.map(casinoAddress -> new Casino(casinoName, casinoAddress)));
	}

}
