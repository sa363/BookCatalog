/*
 *
 *  * Copyright 2002-2022 the Sergey Anisimov <s.anisimov@gmail.com>
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 *
 */

import java.security.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

class GeneratePrivateKeys {

    public static void main(String... args) {

        try {

// Make object of key pair generator using RSA algorithm
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            System.out.println("KeyPairGenerator  Object :- " + kpg);
            OutputStream out;
//set size of key
            kpg.initialize(2048);

//generate pair of public and private keys
            KeyPair kp = kpg.generateKeyPair();

//make public and private keys
            Key pub = kp.getPublic();
            Key pvt = kp.getPrivate();

            System.out.println("Generated Public key :- " + pub);
            System.out.println("Generated Private key :- " + pvt);

//saving keys in binary format

            String outFile = "public";
            out = new FileOutputStream(outFile + ".key");
            out.write(pvt.getEncoded());
            out.close();

            out = new FileOutputStream(outFile + ".pub");
            out.write(pvt.getEncoded());
            out.close();

            System.err.println("Private key format in which it is created: " + pvt.getFormat());
// prints "Private key format"

            System.err.println("Public key format in which it is created: " + pub.getFormat());
// prints "Public key format"

        } catch (NoSuchAlgorithmException e) {

            System.out.println(e);
        } catch (FileNotFoundException e) {

            System.out.println(e);
        } catch (IOException e) {

            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}