package chat.homework.shared;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

public class Serializer
{
    private Serializer()
    {
    }

    public static String serialize(Serializable o)
    {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        String out = null;
        try
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bas);
            objectOutputStream.writeObject(o);
            out = DatatypeConverter.printBase64Binary(bas.toByteArray());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return out;
    }

    public static Object deserialize(String str)
    {
        byte[] bytes = DatatypeConverter.parseBase64Binary(str);
        ByteArrayInputStream bias = new ByteArrayInputStream(bytes);
        Object obj = null;
        try
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(bias);
            obj = objectInputStream.readObject();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return obj;
    }
}