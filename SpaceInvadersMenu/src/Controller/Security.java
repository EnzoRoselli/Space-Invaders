package Controller;

/**
 *
 * @author InvadersTeam
 */
public class Security{

    /* Retorna un hash a partir de un tipo y un texto */

    /**
     *
     * @param txt
     * @param hashType
     * @return
     */

    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /* Retorna un hash MD5 a partir de un texto */

    /**
     *
     * @param txt
     * @return
     */

    public static String md5(String txt) {
        return Security.getHash(txt, "MD5");
    }

    /* Retorna un hash SHA1 a partir de un texto */

    /**
     *
     * @param txt
     * @return
     */

    public static String sha1(String txt) {
        return Security.getHash(txt, "SHA1");
    }
}
