package domain.dto;

public class Book {

	private Long id;
	private String titile;
	private String description;
	
	public Book(Long id, String titile, String description) {
		super();
		this.id = id;
		this.titile = titile;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitile() {
		return titile;
	}
	public void setTitile(String titile) {
		this.titile = titile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
