package unrelated;

import users.Account;

import java.io.Serializable;

public class Score implements Serializable {
    private Account scoringUser;
    private int score;
    private products.Product scoredProduct;

    public Score(Account scoringUser, int score, products.Product scoredProduct) {
        this.scoringUser = scoringUser;
        this.score = score;
        this.scoredProduct = scoredProduct;
    }

    public Account getScoringUser() {
        return scoringUser;
    }

    public void setScoringUser(Account scoringUser) {
        this.scoringUser = scoringUser;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public products.Product getScoredProduct() {
        return scoredProduct;
    }

    public void setScoredProduct(products.Product scoredProduct) {
        this.scoredProduct = scoredProduct;
    }
}
