package com.pk.firebaseauth.test;

import com.pk.firebaseauth.FirebaseTokenAuthorizer;
import com.pk.firebaseauth.impl.FirebaseTokenAuthorizerImpl;

/**
 * @author pk
 */
public class FirebaseTokenAuthorizerTest {

    public static void main(String[] args) {
        String firebaseIdToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6Ijg4ODQ4YjVhZmYyZDUyMDEzMzFhNTQ3ZDE5MDZlNWFhZGY2NTEzYzgiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vbXVzdGktODYyNjUiLCJhdWQiOiJtdXN0aS04NjI2NSIsImF1dGhfdGltZSI6MTU4ODY2NDUyMCwidXNlcl9pZCI6IlN1dEpadUFZSUVnd3BKZnNaNEJPMVFaWDRiZTIiLCJzdWIiOiJTdXRKWnVBWUlFZ3dwSmZzWjRCTzFRWlg0YmUyIiwiaWF0IjoxNTg4NjY0NTIwLCJleHAiOjE1ODg2NjgxMjAsInBob25lX251bWJlciI6Iis5MTkwMzU5ODg5MDkiLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7InBob25lIjpbIis5MTkwMzU5ODg5MDkiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwaG9uZSJ9fQ.gqO9Kzt2v3YdBsBxEaDnGhv72z20DjqXTnURu_84555u834utmdscdsuFIedcnsiuefc_UyGeHE7_fhM5FXsVtTMbdP496gL6vy6eZ9Fpt19eQWBzDAGEiySOwOeW_Gdn0lazOW5dUf7juD0uzTiy6HvM3WzRzgX1IgWHzV_o1w3aNmWNfLxe3w4KaHNVTZRRCVXaI-84eAeT7_i7xc_837KJdDTogN_bswEZPuVjy1WGF1uh7a7V4eUL5wap_Zd0tHTJAmhzPql21iXcbfCISNnV49B39D_efjfjfnfekfjfekfiofCi-r_wMCuRjOVJYwrWpcrz603D5tWjzQ";
        String registeredPhoneNumber = "9999999123";
        FirebaseTokenAuthorizer firebaseTokenAuthorizer = new FirebaseTokenAuthorizerImpl();
        String phNo = firebaseTokenAuthorizer.verify(firebaseIdToken);
        if(phNo!= null && registeredPhoneNumber.equals(phNo)){
            System.out.println("Firebase Id token is Verified !!");
        }


    }
}
