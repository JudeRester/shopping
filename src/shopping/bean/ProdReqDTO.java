package shopping.bean;

public class ProdReqDTO {
	private String req_code;
	private String os;
	private String cpu;
	private String mem;
	private String vga;
	private String directx;
	private String disk_storage;
	public String getReq_code() {
		return req_code;
	}
	public void setReq_code(String req_code) {
		this.req_code = req_code;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getMem() {
		return mem;
	}
	public void setMem(String mem) {
		this.mem = mem;
	}
	public String getVga() {
		return vga;
	}
	public void setVga(String vga) {
		this.vga = vga;
	}
	public String getDirectx() {
		return directx;
	}
	public void setDirectx(String directx) {
		this.directx = directx;
	}
	public String getDisk_storage() {
		return disk_storage;
	}
	public void setDisk_storage(String disk_storage) {
		this.disk_storage = disk_storage;
	}
	
}
