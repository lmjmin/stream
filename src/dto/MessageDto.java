package dto;

public class MessageDto {
	
	private String message;
	private String writer;
	
	public MessageDto(String message, String writer) {
		this.message = message;
		this.writer = writer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
}
