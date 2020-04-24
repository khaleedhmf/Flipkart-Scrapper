package com.ct.scrapper;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class FlipkartProductScraper {

	public static void main(String[] args) throws AddressException, IOException {
		int limit = 4000;

		final String URL = "https://www.flipkart.com/wd-1-5-tb-wired-external-hard-disk-drive/p/itmfcyh2wheuvhbk?pid=ACCFCYH2FGJBJRFS&lid=LSTACCFCYH2FGJBJRFSIIMUQ8&marketplace=FLIPKART&spotlightTagId=BestsellerId_6bo%2Fjdy%2Fnl6&srno=s_1_1&otracker=AS_QueryStore_OrganicAutoSuggest_1_9_na_na_na&otracker1=AS_QueryStore_OrganicAutoSuggest_1_9_na_na_na&fm=SEARCH&iid=ab23e938-0061-4c4c-84ac-1fe3b7951e28.ACCFCYH2FGJBJRFS.SEARCH&ppt=sp&ppn=sp&ssid=01igxqqols0000001587525475529&qH=f6384665f6ff5d04";
		Document document = Jsoup.connect(URL).timeout(12000).get();

		// Retrieving the price of the product

		String priceId = "div._1vC4OE._3qQ9m1";
		Element priceElement = document.select(priceId).first();
		String price = priceElement.text();

		String newPrice = price.substring(1).replace(",", "");
		int todaysPrice = Integer.parseInt(newPrice);

		// Retrieving the discount the product

		String actualPriceId = "div._3auQ3N._1POkHg";
		Element actualPriceElement = document.select(actualPriceId).first();
		String actualPrice = actualPriceElement.text();

		String discountId = "div.VGWI6T._1iCvwn";
		Element discountElement = document.select(discountId).first();
		String discount = discountElement.text();

		if (todaysPrice <= limit) {
			positiveMailClient(price, actualPrice, discount);
		} else {
			negativeMailClient();
		}

	}

	private static void negativeMailClient() throws AddressException {

		// Recipient's email ID needs to be mentioned.
		String to = "khaleedhmf@gmail.com, haribalambika96@gmail.com, nivethaguru20@gmail.com";
		InternetAddress[] parse = InternetAddress.parse(to, true);

		// Sender's email ID needs to be mentioned
		String from = "tiron073@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("tiron073@gmail.com", "BlackHole@20");

			}

		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipients(Message.RecipientType.BCC, parse);

			// Set Subject: header field
			message.setSubject("Flipkart - It's not your day buddy ): ");

			// Now set the actual message
			message.setText("The Product Price is high today - Better luck tomorrow ):" + "\n\n\n\n" + "Regards," + "\n"
					+ "Flipkart Scraper Admin.");

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	private static void positiveMailClient(String price, String actualPrice, String discount) throws AddressException {

		// Recipient's email ID needs to be mentioned.
		String to = "khaleedhmf@gmail.com, haribalambika96@gmail.com, nivethaguru20@gmail.com";
		InternetAddress[] parse = InternetAddress.parse(to, true);

		// Sender's email ID needs to be mentioned
		String from = "tiron073@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("tiron073@gmail.com", "**********");

			}

		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipients(Message.RecipientType.BCC, parse);

			// Set Subject: header field
			message.setSubject("Flipkart - Hurry Up, Let's go shopping!");

			// Now set the actual message
			message.setText("The price of the Product today : " + price + "\n" + "and the actual price is : "
					+ actualPrice + "\n" + "Current Discount on the Product : " + discount + "\n\n\n\n" + "Regards,"
					+ "\n" + "Flipkart Scraper Admin.");

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}
}
