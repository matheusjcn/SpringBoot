package com.appsdelevloper.app.ws.shared;

import com.appsdelevloper.app.ws.shared.dto.UserDto;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MailJetSES {

	final String API_KEY = "42c87138b88f803294307a2c24d0f192";
	final String API_PASS = "da78db03e0a768e29d269d4fbcd785f9";

	final String FROM = "matheusjcn777@gmail.com";

	final String SUBJECT = "Confirmation Email";
	final String PASSWORD_RESET_SUBJECT = "Reset Passord Request";

	final String HTML_BODY = "<h1>Verifiy Email</h1> " + "open link"
			+ "<a href='http://localhost:8080/verification-service/email-verification.html?token=$tokenValue'> "
			+ "click Here </a>" + "<br/><br/>" + "...";

	final String PASSOWRD_RESET_HTML_BODY = "<h1>Reset Your Password</h1> " + "<p>Aoba $firstName </p>"
			+ "<a href='http://localhost:8080/verification-service/password-reset.html?token=$tokenValue'> "
			+ "click to reset password </a>" + "<br/><br/>" + "...";

	final String TEXT_BODY = "Verifiy Email ___ + " + "open link"
			+ "http://localhost:8080/verification-service/email-verification?token=$tokenValue" + "";

	public void verifyEmail(UserDto userDto) {
		MailjetClient client;
		MailjetRequest request;
		MailjetResponse response;

		String htmlWithToken = HTML_BODY.replace("$tokenValue", userDto.getEmailVerificationToken());

		client = new MailjetClient(API_KEY, API_PASS, new ClientOptions("v3.1"));
		request = new MailjetRequest(Emailv31.resource)
				.property(Emailv31.MESSAGES,
						new JSONArray().put(new JSONObject()
								.put(Emailv31.Message.FROM,
										new JSONObject().put("Email", userDto.getEmail()).put("Name",
												userDto.getFirstName()))
								.put(Emailv31.Message.TO,
										new JSONArray().put(new JSONObject().put("Email", userDto.getEmail())
												.put("Name", "Matheus")))
								.put(Emailv31.Message.SUBJECT, "Verify from Mailjet.")
								.put(Emailv31.Message.TEXTPART, TEXT_BODY).put(Emailv31.Message.HTMLPART, htmlWithToken)
								.put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));

		try {
			response = client.post(request);
			System.out.println(response.getStatus());
			System.out.println(response.getData());
		} catch (MailjetException e) {
			System.out.println("[verifyEmail] - Catch1");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MailjetSocketTimeoutException e) {
			System.out.println("[verifyEmail] -  - Catch2");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean sendPasswordResetRequest(String firstName, String email, String token) {
		boolean returnValue = false;

		MailjetClient client;
		MailjetRequest request;
		MailjetResponse response;

		String htmlBodyWithToken = PASSOWRD_RESET_HTML_BODY.replace("$tokenValue", token);
		htmlBodyWithToken = htmlBodyWithToken.replace("$firstName", firstName);

		System.out.println(token);
		System.out.println(htmlBodyWithToken);
		System.out.println(email);

		client = new MailjetClient(API_KEY, API_PASS, new ClientOptions("v3.1"));
		request = new MailjetRequest(Emailv31.resource).property(Emailv31.MESSAGES,
				new JSONArray().put(new JSONObject()
						.put(Emailv31.Message.FROM, new JSONObject().put("Email", email).put("Name", firstName))
						.put(Emailv31.Message.TO,
								new JSONArray().put(new JSONObject().put("Email", email).put("Name", "Matheus")))
						.put(Emailv31.Message.SUBJECT, PASSWORD_RESET_SUBJECT).put(Emailv31.Message.TEXTPART, TEXT_BODY)
						.put(Emailv31.Message.HTMLPART, htmlBodyWithToken)
						.put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));

		try {
			response = client.post(request);
			System.out.println(response);
			returnValue = true;

		} catch (MailjetException e) {
			System.out.println("[verifyEmail] - Catch1");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MailjetSocketTimeoutException e) {
			System.out.println("[verifyEmail] -  - Catch2");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnValue;
	}

}
