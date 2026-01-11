package com.maybank.cards.util;

public class MaskingUtil {
	
	public static String maskAccountNumber(String accountNumber) {

        if (accountNumber == null || accountNumber.length() < 4) {
            return "****";
        }

        int visibleDigits = 4;
        int maskedLength = accountNumber.length() - visibleDigits;

        return "*".repeat(maskedLength)
                + accountNumber.substring(maskedLength);
    }

}
