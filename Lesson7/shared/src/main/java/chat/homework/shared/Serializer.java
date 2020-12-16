package chat.homework.shared;

import org.apache.commons.lang3.SerializationUtils;
import java.io.*;
import java.util.Base64;

public class Serializer
{
    private Serializer()
    {
    }

    public static byte[] serialize(Serializable obj)
    {
        return SerializationUtils.serialize(obj);
    }

    public static String serializeBase64(Serializable obj)
    {
        return Base64.getEncoder().encodeToString(serialize(obj));
    }

    public static <T> T deserialize(byte[] data)
    {
        return SerializationUtils.deserialize(data);
    }

    public static <T> T deserializeBase64(String strBase64)
    {
        return SerializationUtils.deserialize(Base64.getDecoder().decode(strBase64));
    }
}