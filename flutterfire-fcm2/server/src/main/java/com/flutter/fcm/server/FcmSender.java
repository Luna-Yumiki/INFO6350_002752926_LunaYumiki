/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flutter.fcm.server;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.io.InputStream;

public class FcmSender {
  private static InputStream getServiceAccount() {
    return FcmSender.class.getClassLoader().getResourceAsStream("service-account.json");
  }

  private static void initFirebaseSDK() throws Exception {
    FirebaseOptions options =
        FirebaseOptions.builder()
                .setReadTimeout(10000)
                .setConnectTimeout(10000)
            .setCredentials(GoogleCredentials.fromStream(getServiceAccount()))
            .build();

    FirebaseApp.initializeApp(options);
  }

  private static void sendMessageToFcmRegistrationToken() throws Exception {
    String registrationToken = "dXvNplNAS7a1MQzA7VXmfy:APA91bHByaHf2VljaMo0mPEOS2zU6grYBg-Lfw2Jtn52PllyGlLmyNkHFOJ_c9RmSrv4FlX-HSLvqCmJqlq6Ji7CNXSo3vx12jNSnwexbopl8YLBIl-8qZ0";
    Message message =
        Message.builder()
            .putData("FCM", "https://firebase.google.com/docs/cloud-messaging")
            .putData("flutter", "https://flutter.dev/")
            .setNotification(
                Notification.builder()
                    .setTitle("Try this new app")
                    .setBody("Learn how FCM works with Flutter")
                    .build())
            .setToken(registrationToken)
            .build();
    System.out.println("Message to FCM Registration Token sent ...start");
    FirebaseMessaging.getInstance().send(message);

    System.out.println("Message to FCM Registration Token sent successfully!!");
  }

  private static void sendMessageToFcmTopic() throws Exception {
    String topicName = "app_promotion";

    Message message =
        Message.builder()
            .setNotification(
                Notification.builder()
                    .setTitle("A new app is available")
                    .setBody("Check out our latest app in the app store.")
                    .build())
            .setAndroidConfig(
                AndroidConfig.builder()
                    .setNotification(
                        AndroidNotification.builder()
                            .setTitle("A new Android app is available")
                            .setBody("Our latest app is available on Google Play store")
                            .build())
                    .build())
            .setTopic("app_promotion")
            .build();

    FirebaseMessaging.getInstance().send(message);

    System.out.println("Message to topic sent successfully!!");
  }

  public static void main(final String[] args) throws Exception {
    System.out.println("Starting Firebase SDK initialization...");
    initFirebaseSDK();
    System.out.println("Firebase SDK initialization completed.");


    // send a single message
    sendMessageToFcmRegistrationToken();

    // uncomment below to send a topic message
    // sendMessageToFcmTopic();
  }
}
