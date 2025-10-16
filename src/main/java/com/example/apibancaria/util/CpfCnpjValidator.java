package com.example.apibancaria.util;

public final class CpfCnpjValidator {

    private CpfCnpjValidator() {}

    public static boolean isValidCpf(String cpf) {
        if (cpf == null) return false;
        String num = cpf.replaceAll("\\D", "");
        if (num.length() != 11 || num.chars().distinct().count() == 1) return false;
        try {
            int d1 = calcularDigito(num.substring(0, 9), new int[]{10,9,8,7,6,5,4,3,2});
            int d2 = calcularDigito(num.substring(0, 10), new int[]{11,10,9,8,7,6,5,4,3,2});
            return num.charAt(9) - '0' == d1 && num.charAt(10) - '0' == d2;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidCnpj(String cnpj) {
        if (cnpj == null) return false;
        String num = cnpj.replaceAll("\\D", "");
        if (num.length() != 14 || num.chars().distinct().count() == 1) return false;
        try {
            int d1 = calcularDigito(num.substring(0, 12), new int[]{5,4,3,2,9,8,7,6,5,4,3,2});
            int d2 = calcularDigito(num.substring(0, 13), new int[]{6,5,4,3,2,9,8,7,6,5,4,3,2});
            return num.charAt(12) - '0' == d1 && num.charAt(13) - '0' == d2;
        } catch (Exception e) {
            return false;
        }
    }

    private static int calcularDigito(String base, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < pesos.length; i++) {
            soma += (base.charAt(i) - '0') * pesos[i];
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}


