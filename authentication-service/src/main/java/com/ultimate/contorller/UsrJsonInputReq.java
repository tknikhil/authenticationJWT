package com.ultimate.contorller;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsrJsonInputReq {
	 public final static String LOGIN_DATA = "{\n"
	            + "    \"data\": {\n"
	            + "        \"loginData\": {\n"
	            + "            \"userCode\": \"admin\",\n"
	            + "            \"password\": \"12345\",\n"
	            + "            \"companyId\": \"\"\n"
	            + "        }\n"
	            + "    }\n"
	            + "}";

}
