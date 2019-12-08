package shopping.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class MngrDataBean {
	private String pro_num; //게임 등록번호
	private String kind;	//게임 카테고리
	private String title; //게임이름
	private String pro_desc; //게임내용
	private String min_sys;	//최소사양
	private String rec_sys;	//권장사양
	private int price; //게임가격
	private short count; //게임 재고수량
	private String publishing_com; //게임출판사
	private String publishing_date; //출판일
	private Date begin_date; //마감할인시작일
	private Date end_date; //마감할인끝일
	private String title_image; //게임이미지명
	private byte discount_rate; //게임의 할인율
	private String grade;	//게임 등급
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getPublishing_date() {
		return publishing_date;
	}
	public void setPublishing_date(String publishing_date) {
		this.publishing_date = publishing_date;
	}
	public String getPro_num() {
		return pro_num;
	}
	public void setPro_num(String pro_num) {
		this.pro_num = pro_num;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
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
	public String getMin_sys() {
		return min_sys;
	}
	public void setMin_sys(String min_sys) {
		this.min_sys = min_sys;
	}
	public String getRec_sys() {
		return rec_sys;
	}
	public void setRec_sys(String rec_sys) {
		this.rec_sys = rec_sys;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public short getCount() {
		return count;
	}
	public void setCount(short count) {
		this.count = count;
	}
	public String getPublishing_com() {
		return publishing_com;
	}
	public void setPublishing_com(String publishing_com) {
		this.publishing_com = publishing_com;
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
	public String getTitle_image() {
		return title_image;
	}
	public void setTitle_image(String title_image) {
		this.title_image = title_image;
	}
	public byte getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(byte discount_rate) {
		this.discount_rate = discount_rate;
	}
	
	
}