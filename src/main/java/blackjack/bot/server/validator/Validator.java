package blackjack.bot.server.validator;

import blackjack.bot.server.exception.ObjectAlreadyExistsException;

public interface Validator<T> {
	default T databaseContainsEquals(T databaseObject, T newObject) {
		if (databaseObject != null && databaseObject.equals(newObject)) {
			throw new ObjectAlreadyExistsException(newObject.getClass().getSimpleName());
		}
		return newObject;
	}

	default T checkExists(T object) {
		if (object == null) {
			throw new ObjectAlreadyExistsException(object.getClass().getSimpleName());
		}
		return object;
	}

}
