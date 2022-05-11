package controller;



import collection.CollectionItem;
import collection.CollectionItemAdapter;
import collection.element.TicketSaver;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import collection.element.Ticket;
import controller.commands.exceptions.OpenFileException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileController {
    private final String fileName;
    private final static Gson converter = new Gson();

    public FileController(String fileName) {
        this.fileName = fileName;
    }


    public void writeCollection(List<Ticket> ticketCollection) {
        try {
            List<TicketSaver> ticketCollection1 = new ArrayList<TicketSaver>();
            for (Ticket ticket : ticketCollection) {
                ticketCollection1.add(new TicketSaver(ticket));
            }
            if (fileName != null) {
                File file = new File(fileName);
                if (!file.canRead() || !file.canWrite())
                    throw new OpenFileException(fileName);
                String result = converter.toJson(ticketCollection1);
                PrintWriter writer = new PrintWriter(file, "UTF-8");
                writer.write(result);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            throw new OpenFileException(fileName);
        }

    }

    public ArrayList<Ticket> readCollection() {
        try {
            if (fileName != null) {
                File file = new File(fileName);
                if (!file.canRead() || !file.canWrite())
                    throw new OpenFileException(fileName);
                InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
                StringBuilder jsonHolder = new StringBuilder();
                char[] buffer = new char[1024];
                int length = reader.read(buffer);
                while (length != -1) {
                    jsonHolder.append(buffer, 0, length);
                    length = reader.read(buffer);
                }
                reader.close();
                Type listType = new TypeToken<ArrayList<TicketSaver>>() {
                }.getType();
                ArrayList<TicketSaver> convertedCollection = converter.fromJson(jsonHolder.toString(), listType);
                if (convertedCollection == null)
                    convertedCollection = new ArrayList<>();
                ArrayList<Ticket> resultCollection = new ArrayList<Ticket>();
                for (TicketSaver ticketSaver : convertedCollection) {
                    resultCollection.add(new Ticket(ticketSaver));
                }

                return resultCollection;
            }
        } catch (IOException e) {
            throw new OpenFileException(fileName);
        }
        return new ArrayList<>();
    }
}
