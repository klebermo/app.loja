package org.loja.payment.paypal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class Checkout {
	Map<String, String> map = new HashMap<String, String>();

	public Boolean register(String PayerID, String guid) {
		return createPayment(PayerID, guid);
	}

	public Payment createPayment(String PayerID, String guid) {
		Payment createdPayment = null;

    org.loja.settings.paypal.PaypalDao paypalDao = new org.loja.settings.paypal.PaypalDao();
    org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(paypalDao);
    org.loja.settings.paypal.Paypal paypal = (org.loja.settings.paypal.Paypal) paypalDao.get();
    String clientId = paypal.getClientId();
    String clientSecret = paypal.getClientSecret();

		APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
		if (PayerID != null) {
			Payment payment = new Payment();
			if (guid != null) {
				payment.setId(map.get(guid));
			}

			PaymentExecution paymentExecution = new PaymentExecution();
			paymentExecution.setPayerId(PayerID);
			try {
				createdPayment = payment.execute(apiContext, paymentExecution);
				//ResultPrinter.addResult(req, resp, "Executed The Payment", Payment.getLastRequest(), Payment.getLastResponse(), null);
			} catch (PayPalRESTException e) {
				//ResultPrinter.addResult(req, resp, "Executed The Payment", Payment.getLastRequest(), null, e.getMessage());
			}
		} else {
			Details details = new Details();
			details.setShipping("1");
			details.setSubtotal("5");
			details.setTax("1");

			Amount amount = new Amount();
			amount.setCurrency("USD");
			amount.setTotal("7");
			amount.setDetails(details);

			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction.setDescription("This is the payment transaction description.");

			Item item = new Item();
			item.setName("Ground Coffee 40 oz").setQuantity("1").setCurrency("USD").setPrice("5");
			ItemList itemList = new ItemList();
			List<Item> items = new ArrayList<Item>();
			items.add(item);
			itemList.setItems(items);

			transaction.setItemList(itemList);

			List<Transaction> transactions = new ArrayList<Transaction>();
			transactions.add(transaction);

			Payer payer = new Payer();
			payer.setPaymentMethod("paypal");

			Payment payment = new Payment();
			payment.setIntent("sale");
			payment.setPayer(payer);
			payment.setTransactions(transactions);

			RedirectUrls redirectUrls = new RedirectUrls();
			guid = UUID.randomUUID().toString().replaceAll("-", "");
      redirectUrls.setCancelUrl("http://localhost:8080/cart");
      redirectUrls.setReturnUrl("http://localhost:8080/usuario/checkout_paypal?payerId="+PayerID+"?guid="+guid);
			payment.setRedirectUrls(redirectUrls);

			try {
				createdPayment = payment.create(apiContext);
				Iterator<Links> links = createdPayment.getLinks().iterator();
				while (links.hasNext()) {
					Links link = links.next();
					if (link.getRel().equalsIgnoreCase("approval_url")) {
            String redirectUrl = link.getHref();
					}
				}
				map.put(guid, createdPayment.getId());
			} catch (PayPalRESTException e) {
        e.printStackTrace();
			}
		}
		return createdPayment;
	}
}
