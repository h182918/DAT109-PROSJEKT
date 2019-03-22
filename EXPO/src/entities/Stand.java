package entities;

public class Stand {

	private int id;
	private String name;
	private String imageurl;
	private String epostadmin;
	private String pin;

	public Stand() {
	}
	
	

	public Stand(int id, String name, String imageurl, String epostadmin, String pin) {
		super();
		this.id = id;
		this.name = name;
		this.imageurl = imageurl;
		this.epostadmin = epostadmin;
		this.pin = pin;
	}



	public String getEpostadmin() {
		return epostadmin;
	}

	public void setEpostadmin(String epostadmin) {
		this.epostadmin = epostadmin;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageUrl) {
		this.imageurl = imageUrl;
	}
}
