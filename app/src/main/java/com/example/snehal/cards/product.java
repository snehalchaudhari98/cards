package com.example.snehal.cards;

public class product {

    private int n,p,k;
    private String crop;
    private int image;

    public product(int n, int p, int k, String crop, int image) {
        this.n = n;
        this.p = p;
        this.k = k;
        this.crop = crop;
        this.image = image;
    }

    public int getN() {
        return n;
    }

    public int getP() {
        return p;
    }

    public int getK() {
        return k;
    }

    public String getCrop() {
        return crop;
    }

    public int getImage() {
        return image;
    }


}
