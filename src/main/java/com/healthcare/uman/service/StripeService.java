package com.healthcare.uman.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.healthcare.uman.model.payment.PaymentIntentResponse;
import com.healthcare.uman.model.payment.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.model.EphemeralKey;
import com.stripe.model.PaymentIntent;
import com.stripe.param.EphemeralKeyCreateParams;
import com.stripe.param.PaymentIntentCreateParams;

@Service
public class StripeService {

    @Value("${stripe.secretKey}")
    private String stripeSecretKey;

    @Value("${stripe.publicKey}")
    private String stripePublishableKey;

    public PaymentIntentResponse createPaymentIntent(PaymentRequest paymentIntentParams) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        Customer customer = createOrRetrieveCustomer(paymentIntentParams);

        String ephemeralKey = createEphemeralKey(customer.getId(), "2023-10-16");

        PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
                                                                          .setAmount(paymentIntentParams.getAmount())
                                                                          .setCurrency(paymentIntentParams.getCurrency())
                                                                          .setCustomer(customer.getId())
                                                                          .setDescription(paymentIntentParams.getDescription())
                                                                          .addPaymentMethodType("card")
                                                                          .build();

        PaymentIntent paymentIntent = PaymentIntent.create(createParams);
        PaymentIntentResponse paymentIntentResponse = new PaymentIntentResponse();
        paymentIntentResponse.setEphemeralKey(ephemeralKey);
        paymentIntentResponse.setStripePublishableKey(stripePublishableKey);
        return paymentIntentResponse;
    }

    private Customer createOrRetrieveCustomer(PaymentRequest paymentIntentParams) throws StripeException {
        String email = paymentIntentParams.getEmail();
        Customer existingCustomer = retrieveCustomerByEmail(email);

        if (existingCustomer != null) {
            return existingCustomer;
        } else {
            Map<String, Object> customerParams = new HashMap<>();
            customerParams.put("email", email);

            // Ajoutez d'autres informations sur le client si nécessaire
            customerParams.put("name", paymentIntentParams.getName());
            customerParams.put("description", paymentIntentParams.getDescription());
            return Customer.create(customerParams);
        }
    }

    private Customer retrieveCustomerByEmail(String email) throws StripeException {
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("email", email);
        CustomerCollection customers = Customer.list(customerParams);

        if (customers.getData().isEmpty()) {
            return null;
        } else {
            return customers.getData().get(0);
        }
    }

    public String createEphemeralKey(String customerId, String apiVersion) throws StripeException {
        EphemeralKeyCreateParams createParams = EphemeralKeyCreateParams.builder()
                                                                        .setCustomer(customerId)
                                                                        .setStripeVersion(apiVersion)
                                                                        .build();

        EphemeralKey ephemeralKey = EphemeralKey.create(createParams);
        return ephemeralKey.getRawJson();
    }

}
