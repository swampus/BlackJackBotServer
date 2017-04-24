package blackjack.bot.server.validator;

import blackjack.bot.server.exception.ObjectAlreadyExistsException;
import blackjack.bot.server.exception.ObjectIsNotExists;

public interface Validator<T> {
	default T databaseContainsEquals(T databaseObject, T newObject) {
		if (databaseObject != null && databaseObject.equals(newObject)) {
			throw new ObjectAlreadyExistsException(newObject.getClass().getSimpleName());
		}
		return newObject;
	}

	default T checkExists(T object) {
		if (object == null) {
			assert false;
			throw new ObjectIsNotExists(object.getClass().getSimpleName());
		}
		return object;
	}

}
