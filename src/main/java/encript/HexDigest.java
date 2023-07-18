/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encript;

import java.security.MessageDigest;

/**
 *
 * @author Blueweb
 */
public class HexDigest {

    public static String hexDigest(String message) {
        MessageDigest md;

        byte[] buffer, digest;
        String hash = "";

        try {
            buffer = message.getBytes("UTF-8");
            md = MessageDigest.getInstance("SHA1");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        md.update(buffer);
        digest = md.digest();

        for (byte aux : digest) {
            int b = aux & 0xff;

            String s = Integer.toHexString(b);

            if (s.length() == 1) {
                hash += "0";
            }
            hash += s;
        }
        return hash;
    }

}
