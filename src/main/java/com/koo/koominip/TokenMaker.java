package com.koo.koominip;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class TokenMaker {
	public static void makeToken(HttpServletRequest req) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hhmmssSSS");
		String token = sdf.format(date);
		req.setAttribute("token", token);
	}
}
