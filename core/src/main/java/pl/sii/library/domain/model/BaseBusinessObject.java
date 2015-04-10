package pl.sii.library.domain.model;

public abstract class BaseBusinessObject<T> {

	T entity;
	Class<T> clazz;
	
	public BaseBusinessObject() {
		super();
	}

	public BaseBusinessObject(T entity, Class<T> clazz) {
		super();
		this.entity = entity;
		this.clazz = clazz;
	}
	
	public void attach(T entity, Class<T> clazz) {
		this.entity = entity;
		this.clazz = clazz;
	}
	
	public T getEntity() {
		return entity;
	}
}
