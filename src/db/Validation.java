package db;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Validation {

	private final Object toValidate;

	public Validation(Object toValidate) {
		this.toValidate = toValidate;
	}

	public void validate() throws ValidationException {
		validateSizes();

	}

	private void validateSizes() throws ValidationException {
		for (Field declaredField : toValidate.getClass().getDeclaredFields()) {
			if (declaredField.isAnnotationPresent(Size.class))
				validateFieldSize(declaredField);
		}

	}

	private void validateFieldSize(Field declaredField) throws ValidationException {
		try {
			String fieldname = declaredField.getName();
			fieldname = fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
			Method methodToCall = toValidate.getClass().getMethod("get" + fieldname);

			Size size = declaredField.getAnnotation(Size.class);
			Object invoke = methodToCall.invoke(toValidate);
			if (invoke instanceof String) {
				if (((String) invoke).length() > size.max()) {
					throw new ValidationException(size.message() + ": " + size.max());
				}
			}
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
