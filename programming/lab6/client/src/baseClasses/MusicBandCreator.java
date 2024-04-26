package baseClasses;

/**
 * Class {@code MusicBandCreator} need to create music band
 *
 * @author Egorova Varvara
 */
public class MusicBandCreator {
    /**
     * Method creates music band from array of string arguments
     * @param fields array of arguments for music band
     * @return Music band or null
     */
    public static MusicBand createMusicBand(String[] fields){
        try{
            String name = fields[0];
            Long x = Long.parseLong(fields[1]);
            float y = Float.parseFloat(fields[2]);
            int numberOfParticipants = Integer.parseInt(fields[3]);
            MusicGenre musicGenre = MusicGenre.valueOf(fields[4].toUpperCase());
            long bands = Long.parseLong(fields[5]);
            return new MusicBand(name, new Coordinates(x, y), numberOfParticipants, musicGenre, new Label(bands));
        } catch (Exception e){
            return null;
        }
    }
}
