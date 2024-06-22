package in.co.rays.project_3.dto;

import java.util.Date;

public class ProjectDTO extends BaseDTO {
	private String name;
	private String category;
	private Date date;
	private long version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return category+"";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return category+"";
	}

}
