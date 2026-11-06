package com.core.newJavaFeatures;

sealed interface Payment permits CreditCard, PayPal, BitCoin {
    void processPayment();
}

final class CreditCard implements Payment {
    public void processPayment() {
        System.out.println("Payment processed via CreditCard");
    }
}

final class PayPal implements Payment {
    public void processPayment() {
        System.out.println("Payment processed via PayPal");
    }
}

final class BitCoin implements Payment {
    public void processPayment() {
        System.out.println("Payment proceessed via Bitcoin");
    }
}

// final class Paytm implements Payment {
   // public void processPayment() {

   // }
 // }

public class PaymentExample {
    public static void main(String[] args) {
        BitCoin bitCoin = new BitCoin();
        PayPal payPal = new PayPal();
        CreditCard creditCard = new CreditCard();

        payPal.processPayment();
        creditCard.processPayment();
        bitCoin.processPayment();
    }
}
