package FileManager;
import BaseClasses.MusicBand;
import Collection.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Parser extends FileManager{
    private final String filename;
    public Parser(String filename){
        this.filename = filename;
    }

    @Override
    public void saveToJson(HashSet<MusicBand> musicBands) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String data = gson.toJson(musicBands);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.filename))){
            bufferedWriter.write(data);
        }catch (IOException e){
            System.out.println("Что-то пошло не так. Данные не сохранены. ");
        }
    }
    @Override
    public HashSet<MusicBand> loadFromJson() throws JsonSyntaxException, JsonIOException{
        HashSet<MusicBand> musicBands = new HashSet<>();
        ArrayList<MusicBand> buffer;
        Validator validator = new Validator();
        IdGenerator idGenerator = validator.getIdGenerator();
        try{
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            Type itemsArrayType = new TypeToken<ArrayList<MusicBand>>() {}.getType();
            String data = "";
            while (sc.hasNextLine()){
                data = data.concat(sc.nextLine());
            }
            buffer = new Gson().fromJson(data, itemsArrayType);
            for (MusicBand musicBand : buffer){
                MusicBand validatedMusicBand = validator.getValidatedElement(musicBand);
                if (!(validatedMusicBand == null) && !(idGenerator.getGeneratedIds().contains(validatedMusicBand.getId()))){
                    musicBands.add(validatedMusicBand);
                    idGenerator.addId(validatedMusicBand);
                }else{
                    System.out.println("Пропущен некорректный элемент. ");
                }
            }
            return musicBands;
        }catch (JsonSyntaxException e){
            System.out.println("Что-то не так с файлом или он пуст. Коллекция не содержит элементов. ");
            return musicBands;
        }catch (JsonIOException | FileNotFoundException e){
            System.out.println("Данный файл не найден.");
            return musicBands;
        }
    }
}
