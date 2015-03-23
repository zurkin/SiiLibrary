package pl.sii.library.dto;

public class Request<T> {

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T t) {
		this.data = t;
	}
}
