/**
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

importScripts("https://www.gstatic.com/firebasejs/9.6.10/firebase-app-compat.js");
importScripts("https://www.gstatic.com/firebasejs/9.6.10/firebase-messaging-compat.js");

// todo Copy/paste firebaseConfig from Firebase Console
const firebaseConfig = {
  apiKey: "AIzaSyD16hua7h94aOrsASU8hRy_Shiu0Zba0vg",
  authDomain: "hypergaragesale-a643d.firebaseapp.com",
  projectId: "hypergaragesale-a643d",
  storageBucket: "hypergaragesale-a643d.firebasestorage.app",
  messagingSenderId: "124721597369",
  appId: "1:124721597369:web:e7bc7a456243946067c468",
  measurementId: "G-KCGK73X2H5"
};

firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();

// todo Set up background message handler
messaging.onBackgroundMessage((message) => {
 console.log("onBackgroundMessage", message);
});
