package com.pk.firebaseauth.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.pk.firebaseauth.FirebaseTokenAuthorizer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author pk
 */
public class FirebaseTokenAuthorizerImpl implements FirebaseTokenAuthorizer {
    private static final Logger logger = LoggerFactory.getLogger(FirebaseTokenAuthorizerImpl.class);

    private static FileInputStream fileInputStream =  null;

    static {
        /*
        *This file is generated for one of your project id that is created in Firebase App. Here my project id is "test-552bf".
        * This will differ from project to project. You can check https://firebase.google.com/docs/admin/setup
        * and
        * "To generate a private key file for your service account:"
        *
        * Keep this file at secure place . Don't put it in the source code. for testing purpose i have kept it in the same tree.
        */
        try{
            fileInputStream = new FileInputStream("./test-9ad97-firebase-adminsdk-ghdks-f2c30d190a.json");
        }catch (IOException ex){
            //Use logger to logging the error
            logger.error("File is not loaded :",ex);
        }
    }

    @Override
    public String verify(String firebaseToken) {
        try {
            FirebaseApp firebaseApp = getFirebaseAppInstance();
            //Check the firebase id token is revoked or not
            boolean checkRevoked = true;
            if (StringUtils.isNotBlank(firebaseToken) && firebaseApp != null) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
                String userId = firebaseAuth.verifyIdToken(firebaseToken, checkRevoked).getUid();
                if (userId != null) {
                    //registered phone number
                    String phoneNumber = firebaseAuth.getUser(userId).getPhoneNumber();
                    //revoke the refresh id
                    firebaseAuth.revokeRefreshTokens(userId);
                    return phoneNumber;
                }
            }
        } catch (FirebaseAuthException fae) {
            if (fae.getErrorCode().equals("id-token-revoked")) {
                logger.error("Token has been revoked. Please re-authenticate or signOut() the user.");
            } else {
                logger.error("Id token is invalid");
            }
        } catch (Exception ex) {
            logger.error("Exception occured in verify(..) method");
        }
        return null;
    }


    protected FirebaseApp getFirebaseAppInstance() throws Exception {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(fileInputStream))
                    /*.setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")*/
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                return FirebaseApp.initializeApp(options);
            }
            return FirebaseApp.getInstance();
    }
}
