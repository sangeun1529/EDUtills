package nest.hava.edutills.util;

public class MoreObjects {

	public static <T> T firstNonNull(T first, T second) {
		return first != null ? first : checkNotNull(second);
	}

	public static <T> T checkNotNull(T reference) {
		if (reference == null) {
			throw new NullPointerException();
		}
		return reference;
	}
}
