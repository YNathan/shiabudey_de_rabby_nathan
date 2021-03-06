package controllers;

import play.mvc.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import BL.getterBL;
import akka.event.Logging;
import play.Logger;
import play.libs.Json;

/**
 * 
 * @author Yaacov
 *
 */
public class getter {
	private static final Lock lock = new ReentrantLock();
	private static getterBL getterBL = new getterBL();

	public static Result isLoginPermited(String szUserName, String szPassword) {
		if ((szUserName != null) && (szPassword != null)) {
			if (getterBL.isLoginPermited(szUserName, szPassword)) {
				play.Logger.info(szUserName +" is login");
				return play.mvc.Results.ok("true");
			}
			System.out.println("[INFO] Error when trying to connect with wrong user-name or password.\nUSER_NAME : '"
					+ szUserName + "'\nPASSWORD : '" + szPassword + "'");
			return play.mvc.Results.badRequest("The user-name or the password is incorrect");
		} else {
			return play.mvc.Results.badRequest(
					"Null pointer screw you! \nyou send your request with an empty user-name or an empty password!");
		}
	}

	public static Result isUserNameAlreadyExist(String szUserName) {
		if (szUserName != null) {
			if (getterBL.isUserNameAlreadyExist(szUserName)) {
				return play.mvc.Results.badRequest("user name already exist");
			}
			return play.mvc.Results.ok("true");
		} else {
			return play.mvc.Results
					.badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
		}
	}

	public static Result isEmailAlreadyExist(String szEmail) {
		if (szEmail != null) {
			if (getterBL.isEmailAlreadyExist(szEmail)) {
				return play.mvc.Results.badRequest("email already exist");
			}
			return play.mvc.Results.ok("true");
		} else {
			return play.mvc.Results.badRequest("Null pointer screw you! \nyou send your request with an empty email!");
		}

	}

	public static Result getGelts(String szUserName) {
		if (szUserName != null) {
			String szResponce = getterBL.getGeltsByNameForOutput(szUserName).toString();
			play.Logger.info(szResponce);
			return play.mvc.Results.ok(szResponce);
		} else {
			return play.mvc.Results
					.badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
		}
	}

	public static Result getUsers(String szUserName) {
		if (szUserName != null) {
			return play.mvc.Results.ok(Json.toJson(getterBL.getUsers(szUserName)));
		} else {
			return play.mvc.Results
					.badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
		}
	}

	public static Result checkIfUserIsDebter(String szUserName) {
		lock.lock();
		play.Logger.info("<GETTER> " + szUserName + " ask if is debter");
		System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())+" <GETTER> " + szUserName + " ask if is debter");
		lock.unlock();
		if (szUserName != null) {
			String szResponce = getterBL.checkIfUserIsDebter(szUserName).toString();
			play.Logger.info("<GETTER> <DATA>"+szResponce);
			return play.mvc.Results.ok(szResponce);
		} else {
			return play.mvc.Results
					.badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
		}
	}

}
