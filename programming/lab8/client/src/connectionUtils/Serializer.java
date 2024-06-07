package connectionUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Class {@code Serializer} with one method needs to serialize commands and users that sens to server
 * @author Egorova Varvara
 */
public final class Serializer {
    /**
     * Method that serialize object
     * @param object serializable object
     * @param <T>
     * @return bytes of serialized object
     */
    public static <T extends Serializable> byte[] serialize(T object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        } catch (IOException e) {
            System.err.println("Unable to create ObjectOutputStream: " + e);
            return new byte[0];
        }
        try {
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            System.err.println("Unable to serialize object: " + object.getClass().getSimpleName() + ": " + e);
            return new byte[0];
        }
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            System.err.println("Unable to close ObjectOutputStream");
        }
        return byteArrayOutputStream.toByteArray();
    }
}
