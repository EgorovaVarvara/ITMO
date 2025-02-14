package org.example.backend.service;

public class HitChecker {
    public static boolean hit(double x, double y, double r) {
        System.out.println("x: " + x + " y: " + y + " r: " + r);
        if (r > 0){
            if (x < 0 && y > 0) {
                return false;
            } else if (x >= 0 && y >= 0) {
                return x <= r / 2 && y <= Math.sqrt((r * r) / 4 - x * x);
            } else if (x >= 0) {
                return x <= r && Math.abs(y) <= r;
            } else {
                return Math.abs(x) <= r / 2 && y >= -x - r / 2;
            }
        }else if (r < 0){
            if (x > 0 && y < 0){
                return false;
            }else if (x <= 0 && y <= 0){
                return Math.abs(x) <= Math.abs(r / 2) && Math.abs(y) <= Math.sqrt((r * r) / 4 - x * x);
            } else if (x <= 0){
                return Math.abs(x) <= Math.abs(r) && Math.abs(y) <= Math.abs(r);
            } else {
                return Math.abs(x) <= Math.abs(r) / 2 && Math.abs(y) <= -Math.abs(x) + Math.abs(r)/2;
            }
        }else{
            return true;
        }
    }
}
