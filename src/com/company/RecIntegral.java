package com.company;

public class RecIntegral {
    /**
     * Нижний предел интегрирования
     */
    private String a;
    /**
     * Верхний предел интегрирования
     */
    private String b;
    /**
     * Шаг интегрирования
     */
    private String n;

    /**
     * Создать запись вычисления
     *
     * @param a Нижний предел интегрирования
     * @param b Верхний предел интегрирования
     * @param n Шаг интегрирования
     */
    public RecIntegral(String a, String b, String n) {
        this.a = a;
        this.b = b;
        this.n = n;
    }

    /**
     * Получить значение нижнего предела интегрирования
     *
     * @return
     */
    public String getA() {
        return a;
    }

    /**
     * Получить значение верхнего предела интегрирования
     *
     * @return
     */
    public String getB() {
        return b;
    }

    /**
     * Получить значение шага интегрирования
     *
     * @return Верхний предел интегрирования
     */
    public String getN() {
        return n;
    }

}