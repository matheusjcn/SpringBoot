package com.appsdelevloper.app.ws.ui.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appsdelevloper.app.ws.ui.model.request.LoginRequestModel;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;



@RestController
public class AutenticationController {
	
	@ApiOperation("User Login")
	@ApiResponses(value= {
			@ApiResponse(code=200,
					message = "Response Headers",
					responseHeaders = {
							@ResponseHeader(name="authorization",
									description = "Bearer <JWT value>",
									response= String.class),
							@ResponseHeader(name="userId",
									description = "<User ID>",
									response= String.class)
				})
	})
	
	@PostMapping("/users/login")
	public void theFakeLogin(@RequestBody LoginRequestModel loginRequestModel)  {
		throw new IllegalStateException("This method shoud not be called...");
	}
	
}
