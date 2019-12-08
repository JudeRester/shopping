package shopping.bean;

import java.sql.Date;

public class ProductDTO {
	private String pro_num;
	private String title;
	private String pro_desc;
	private int price;
	private Date begin_date;
	private Date end_date;
	private String title_img;
	public String getPro_num() {
		return pro_num;
	}
	public void setPro_num(String pro_num) {
		this.pro_num = pro_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPro_desc() {
		return pro_desc;
	}
	public void setPro_desc(String pro_desc) {
		this.pro_desc = pro_desc;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getTitle_img() {
		return title_img;
	}
	public void setTitle_img(String title_img) {
		this.title_img = title_img;
	}
	
}
