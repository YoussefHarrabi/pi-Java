package models;

public class Wallet {
    private int walletId;
    private int userId;
    private double points;

    public Wallet(int walletId, int userId, double points) {
        this.walletId = walletId;
        this.userId = userId;
        this.points = points;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
