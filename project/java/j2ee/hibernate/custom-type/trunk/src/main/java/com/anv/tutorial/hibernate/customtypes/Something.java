package com.anv.tutorial.hibernate.customtypes;

public class Something {
	private Long id;
	private Kilos weight;

	public void setWeight(Kilos customField) {
		this.weight = customField;
	}
	
	public Kilos getWeight() {
		return weight;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Something))
			return false;
		Something other = (Something) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
