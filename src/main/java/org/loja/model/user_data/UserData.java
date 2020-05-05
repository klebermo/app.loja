package org.loja.model.user_data;

import javax.persistence.Entity;
import org.loja.model.Model;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class UserData extends Model {
  @Id
  private String id;

	/**
	* Default empty UserData constructor
	*/
	public UserData() {
		super();
    this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	* Default UserData constructor
	*/
	public UserData(String id) {
		super();
		this.id = id;
	}

	/**
	* Returns value of id
	* @return
	*/
	public String getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(String id) {
		this.id = id;
	}

	/**
	* Create string representation of UserData for printing
	* @return
	*/
	@Override
	public String toString() {
		return "UserData [id=" + id + "]";
	}
}
