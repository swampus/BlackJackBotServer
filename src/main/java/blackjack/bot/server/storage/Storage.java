package blackjack.bot.server.storage;

import rx.Observable;

public interface Storage<T> {
	 Observable<Void> put(T value);
	 Observable<T> get(String key);
}
