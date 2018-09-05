package bookmark.entity;

public class BookmarkEntity {
	
	private String bookmark_id;
	private String bookmark_url;
	private String bookmark_img;
	private String bookmark_video;
	private String bookmark_title;
	private String bookmark_description;
	private String bookmark_interest;
	
	public String getBookmark_interest(){
		return bookmark_interest;
	}
	public void setBookmark_interest(String bookmark_interest){
		this.bookmark_interest=bookmark_interest;
	}
	
	public String getBookmark_id() {
		return bookmark_id;
	}
	public void setBookmark_id(String bookmark_id) {
		this.bookmark_id = bookmark_id;
	}
	public String getBookmark_url() {
		return bookmark_url;
	}
	public void setBookmark_url(String bookmark_url) {
		this.bookmark_url = bookmark_url;
	}
	public String getBookmark_img() {
		return bookmark_img;
	}
	public void setBookmark_img(String bookmark_img) {
		this.bookmark_img = bookmark_img;
	}
	public String getBookmark_title() {
		return bookmark_title;
	}
	public void setBookmark_title(String bookmark_title) {
		this.bookmark_title = bookmark_title;
	}
	public String getBookmark_description() {
		return bookmark_description;
	}
	public void setBookmark_description(String bookmark_description) {
		this.bookmark_description = bookmark_description;
	}
	public String getBookmark_video() {
		return bookmark_video;
	}
	public void setBookmark_video(String bookmark_video) {
		this.bookmark_video = bookmark_video;
	}
}
