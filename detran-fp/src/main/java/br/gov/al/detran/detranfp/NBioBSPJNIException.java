package br.gov.al.detran.detranfp;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

public class NBioBSPJNIException extends Exception {

	private int code;
    private HttpStatus status;

	public NBioBSPJNIException(String message) {
		super(message);
	}

	public NBioBSPJNIException(NBioExceptionCodes code) {
		super(code.getMsg());
		this.code = code.getId();
		this.status = code.getStatus();
	}

	private static String generateMessage(String entity, Map<String, String> searchParams) {
		return StringUtils.capitalize(entity) + " was not found for parameters " + searchParams;
	}

	private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
		if (entries.length % 2 == 1)
			throw new IllegalArgumentException("Invalid entries");
		return IntStream.range(0, entries.length / 2).map(i -> i * 2).collect(HashMap::new,
				(m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])), Map::putAll);
	}


	public HttpStatus getStatus() {
		return status;
	}

}
