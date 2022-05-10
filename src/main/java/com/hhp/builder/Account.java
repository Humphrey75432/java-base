package com.hhp.builder;

public class Account {

    private String username;
    private String password;
    private String email;
    private String address;

    public Account(String username, String password, String email, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public static Account.AccountBuilder builder() {
        return new Account.AccountBuilder();
    }

    public static class AccountBuilder {
        private String username;
        private String password;
        private String email;
        private String address;

        AccountBuilder() {}

        public Account.AccountBuilder username(String username) {
            this.username = username;
            return this;
        }

        public Account.AccountBuilder password(String password) {
            this.password = password;
            return this;
        }

        public Account.AccountBuilder email(String email) {
            this.email = email;
            return this;
        }

        public Account.AccountBuilder address(String address) {
            this.address = address;
            return this;
        }

        public Account build() {
            return new Account(this.username, this.password, this.email, this.address);
        }

        @Override
        public String toString() {
            return "AccountBuilder{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", email='" + email + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Account(" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ')';
    }
}
