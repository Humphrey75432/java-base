package com.hhp.builder;

public class LombokDemo {

    public static void main(String[] args) {
        // Using Lombok plugin
        User user = User.builder()
                .firstName("John")
                .lastName("Smith")
                .age(24)
                .gender("female")
                .email("john_smith@sbl.com")
                .remark("This is just a object from lombok builder annotation")
                .build();
        System.out.println(user);

        // Self implement by lombok anti-compile result
        Account account = Account.builder()
                .username("HelloWorld")
                .password("Password123")
                .address("This is just a address")
                .email("email@abc.com")
                .build();
        System.out.println(account);
    }
}
