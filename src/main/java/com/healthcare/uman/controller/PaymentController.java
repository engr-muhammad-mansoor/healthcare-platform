package com.healthcare.uman.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.uman.annotation.SupervisorLogAudit;
import com.healthcare.uman.dto.payment.PaymentDTO;
import com.healthcare.uman.model.payment.PaymentIntentResponse;
import com.healthcare.uman.model.payment.PaymentRequest;
import com.healthcare.uman.service.PaymentService;
import com.healthcare.uman.service.StripeService;
import com.stripe.exception.StripeException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Payment API")
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;
    private final StripeService stripeService;

    public PaymentController(PaymentService paymentService, StripeService stripeService) {
        this.paymentService = paymentService;
        this.stripeService = stripeService;
    }

    @PostMapping("/create-payment-intent")
    public PaymentIntentResponse createPaymentIntent(@RequestBody PaymentRequest paymentIntent) throws StripeException {
        return stripeService.createPaymentIntent(paymentIntent);
    }



    /* @SupervisorLogAudit
    @PostMapping
    @Operation(summary = "Subscribe to a plan", description = "Subscribe to a plan")
    public ResponseEntity<String> createPlan() {
        return ResponseEntity.ok().body(null);
    }

    @SupervisorLogAudit
    @PutMapping
    @Operation(summary = "Update bank information for a pro", description = "Update bank information for a pro")
    public ResponseEntity<String> updateBankInformation() {
        return ResponseEntity.ok().body(null);
    }

    @SupervisorLogAudit
    @GetMapping("/users/{id}")
    @Operation(summary = "Get the payment informations for a user by user Id", description = "Get the payment informations for a user by user Id")
    public ResponseEntity<PaymentDTO> getPaymentsForUser(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            LOGGER.error("Failed to get user account: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @SupervisorLogAudit
    @PutMapping("/disable/{id}")
    @Operation(summary = "Disable plan for a pro", description = "Disable plan for a pro")
    public ResponseEntity<String> disablePlan(@PathVariable String proId) {
        return ResponseEntity.ok().body(null);
    }

    @SupervisorLogAudit
    @PostMapping("/payment/{id}")
    @Operation(summary = "Make a payment", description = "Make a payment for a client")
    public ResponseEntity<String> makePayment(@PathVariable String patientId) {
        return ResponseEntity.ok().body(null);
    }
*/


}
