package forestdemo.service.storage;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = 8462779006960508277L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {

	}
}
