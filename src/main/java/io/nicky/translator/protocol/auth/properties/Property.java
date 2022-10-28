package io.nicky.auth.properties;

import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.PublicKey;
import java.util.Base64;

/**
 * The type Property.
 */
public class Property
{
    private final String name;
    private final String value;
    private final String signature;

    /**
     * Instantiates a new Property.
     *
     * @param value the value
     * @param name  the name
     */
    public Property(final String value, final String name) {
        this(value, name, null);
    }

    /**
     * Instantiates a new Property.
     *
     * @param name      the name
     * @param value     the value
     * @param signature the signature
     */
    public Property(final String name, final String value, final String signature) {
        super();
        this.name = name;
        this.value = value;
        this.signature = signature;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Gets signature.
     *
     * @return the signature
     */
    public String getSignature() {
        return this.signature;
    }

    /**
     * Has signature boolean.
     *
     * @return the boolean
     */
    public boolean hasSignature() {
        return this.signature != null;
    }

    /**
     * Is signature valid boolean.
     *
     * @param publicKey the public key
     * @return the boolean
     */
    public boolean isSignatureValid(final PublicKey publicKey) {
        try {
            final Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(publicKey);
            signature.update(this.value.getBytes());
            return signature.verify(Base64.getDecoder().decode(this.signature));
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (InvalidKeyException e2) {
            e2.printStackTrace();
        }
        catch (SignatureException e3) {
            e3.printStackTrace();
        }
        return false;
    }
}
