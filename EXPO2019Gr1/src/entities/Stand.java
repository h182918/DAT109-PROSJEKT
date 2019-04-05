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


	/**
	 * Returns email registered to stand
	 * @return
	 */
	public String getEpostadmin() {
		return epostadmin;
	}

	/**
	 * Sets email for stand
	 * @param epostadmin
	 */
	public void setEpostadmin(String epostadmin) {
		this.epostadmin = epostadmin;
	}

	/**
	 * Gets the pin
	 * @return
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * Sets the pinkode
	 * @param pin
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}

	/**
	 * Returns the stand Id
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the stand id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the url for the image
	 * @return
	 */
	public String getImageurl() {
		return imageurl;
	}

	/**
	 * Sets the url for the image
	 * @param imageUrl
	 */
	public void setImageurl(String imageUrl) {
		this.imageurl = imageUrl;
	}
}
