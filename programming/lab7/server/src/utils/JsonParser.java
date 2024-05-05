package utils;
import baseClasses.LocalDateTimeTypeAdapter;
import baseClasses.MusicBand;
import collection.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * The {@code Parser} manage parsing form and to json using `GSON` library.
 *
 * @author Egorova Varvara
 */
public class JsonParser implements Parser{
    /**
     * filename of main collection.
     */
    private static String filename = "server.json";

    /**
     * Constructor of class objects.
     * @param filename of main collection
     */
    public JsonParser(String filename){
        JsonParser.filename = filename;
    }
    public static String getFilename(){
        return filename;
    }
    @Override
    public void save() {
        HashSet<MusicBand> musicBands = CollectionManager.getCollection();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter());
        Gson gson = gsonBuilder.create();
        String data = gson.toJson(musicBands);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))){
            bufferedWriter.write(data);
            ServerLogger.getLogger().info("Коллекция сохранена в файл. ");
        }catch (IOException e){
            if (!(new File(filename)).canWrite()){
                ServerLogger.getLogger().warning("Нет прав на запись в файл");
            } else {
                ServerLogger.getLogger().warning("Что-то пошло не так. Данные не сохранены. ");
            }
        }
    }

    /**
     * Loads collection from json file.
     * @return HashSet with main collection
     * @throws JsonSyntaxException if something wrong in file
     * @throws JsonIOException if something wrong with file
     */
    @Override
    public HashSet<MusicBand> load() throws JsonSyntaxException, JsonIOException{
        HashSet<MusicBand> musicBands = new HashSet<>();
        ArrayList<MusicBand> buffer;
        Validator validator = new Validator();
        IdGenerator idGenerator = validator.getIdGenerator();
        try{
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            Type itemsArrayType = new TypeToken<ArrayList<MusicBand>>() {}.getType();
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter());
            Gson gson = gsonBuilder.create();
            String data = "";
            while (sc.hasNextLine()){
                data = data.concat(sc.nextLine());
            }
            buffer = gson.fromJson(data, itemsArrayType);
            for (MusicBand musicBand : buffer){
                MusicBand validatedMusicBand = validator.getValidatedElement(musicBand);
                if (!(validatedMusicBand == null) && !(idGenerator.getGeneratedIds().contains(validatedMusicBand.getId()))){
                    musicBands.add(validatedMusicBand);
                    idGenerator.addId(validatedMusicBand);
                }else{
                    ServerLogger.getLogger().warning("Пропущен некорректный элемент. ");
                }
            }
            return musicBands;
        }catch (JsonIOException | NullPointerException | JsonSyntaxException e){
            ServerLogger.getLogger().warning("Что-то не так с файлом или он пуст. Коллекция не содержит элементов. ");
            return musicBands;
        }catch (FileNotFoundException e){
            if (!(new File(filename).canRead())){
                ServerLogger.getLogger().warning("Нет прав на чтение данного файла.");
            } else {
                ServerLogger.getLogger().warning("Данный файл не найден.");
            }
            return musicBands;
        }
    }
}
