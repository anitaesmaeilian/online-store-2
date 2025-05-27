package unrelated;

import users.Account;

import java.io.Serializable;

public class Comment implements Serializable {
    public enum commentStatus
    {WAITING, APPROVED, UNAPPROVED}
    commentStatus commentStatus;
    private Account commentingUser;
    private products.Product commentedProduct;
    private String commentText;
    private boolean haveBought;

    public Comment(Account commentingUser, products.Product commentedProduct, String commentText, boolean haveBought) {
        this.commentStatus = commentStatus.WAITING;
        this.commentingUser = commentingUser;
        this.commentedProduct = commentedProduct;
        this.commentText = commentText;
        this.haveBought = haveBought;
    }

    public Comment.commentStatus getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Comment.commentStatus commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Account getCommentingUser() {
        return commentingUser;
    }

    public void setCommentingUser(Account commentingUser) {
        this.commentingUser = commentingUser;
    }

    public products.Product getCommentedProduct() {
        return commentedProduct;
    }

    public void setCommentedProduct(products.Product commentedProduct) {
        this.commentedProduct = commentedProduct;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public boolean getHaveBought() {
        return haveBought;
    }

    public void setHaveBought(boolean haveBought) {
        this.haveBought = haveBought;
    }

    @Override
    public String toString() {
        return  "commentStatus=" + commentStatus +
                ", commentingUser=" + commentingUser +
                ", commentedProduct=" + commentedProduct +
                ", commentText='" + commentText + '\'' +
                ", haveBought=" + haveBought ;
    }

}
