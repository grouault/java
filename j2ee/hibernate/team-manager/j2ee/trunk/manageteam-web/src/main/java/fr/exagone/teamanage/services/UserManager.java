package fr.exagone.teamanage.services;

import fr.exagone.teamanage.bean.User;

public interface UserManager {

	/**
	 * permet de retourner un user à partir de son login.
	 * @param userLogin
	 * @return
	 */
	public User findByLogin(String userLogin);
	
}
