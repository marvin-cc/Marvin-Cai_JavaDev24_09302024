package com.crypto.util;

public class OptionPriceCalculator {
    // approximate CDF from https://en.wikipedia.org/wiki/Normal_distribution#Error_function
    private static double calcuLateCDF(double x) {
        return 0.5 * (1.0 + erf(x / Math.sqrt(2)));
    }

    // ERF implementation borrowed from Princeton's lib: https://introcs.cs.princeton.edu/java/21function/ErrorFunction.java.html
    public static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));

        double ans = 1 - t * Math.exp( -z*z   -   1.26551223 +
                t * ( 1.00002368 +
                        t * ( 0.37409196 +
                                t * ( 0.09678418 +
                                        t * (-0.18628806 +
                                                t * ( 0.27886807 +
                                                        t * (-1.13520398 +
                                                                t * ( 1.48851587 +
                                                                        t * (-0.82215223 +
                                                                                t * ( 0.17087277))))))))));
        if (z >= 0) return  ans;
        else        return -ans;
    }

    private static double calculateD1(double S, double K, double r, double sigma, double t) {
        return (Math.log(S / K) + (r + 0.5 * sigma * sigma) * t) / (sigma * Math.sqrt(t));
    }

    private static double calculateD2(double sigma, double t, double d1) {
        return d1 - sigma * Math.sqrt(t);
    }

    public static double calculateCallPrice(double S, double K, double r, double sigma, double t) {
        double d1 = calculateD1(S, K, r, sigma, t);
        return S * calcuLateCDF(d1) - K * Math.exp(-r * t) * calcuLateCDF(calculateD2(sigma, t, d1));
    }

    public static double calculatePutPrice(double S, double K, double r, double sigma, double t) {
        double d1 = calculateD1(S, K, r, sigma, t);
        return K * Math.exp(-r * t) * calcuLateCDF(-calculateD2(sigma, t, d1)) - S * calcuLateCDF(-d1);
    }
}
