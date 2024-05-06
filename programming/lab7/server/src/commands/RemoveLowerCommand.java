package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import connectionUtils.User;
import utils.DataBaseManager;

import java.io.Serial;
import java.util.HashSet;

/**
 * Command `remove_lower {element}`.
 *
 * @author Egorova Varvara
 */
public class RemoveLowerCommand implements Command{
    @Serial
    private final static long serialVersionUID = 12L;
    private MusicBand musicBand;
    private User user;
    public RemoveLowerCommand(MusicBand musicBand){
        this.musicBand = musicBand;
    }
    @Override
    public Response run() {
        DataBaseManager dataBaseManager = new DataBaseManager();
        HashSet<Integer> ids = dataBaseManager.removeLower(user.getLogin(), musicBand.getNumberOfParticipants());
        if (!ids.isEmpty()){
            for (int id : ids){
                CollectionManager.removeById(id);
            }
            musicBand.setId(dataBaseManager.addObject(musicBand));
            CollectionManager.add(musicBand);
            return new Response(ResponseStatus.OK, "Ваши элементы, меньшие чем заданный удалены");
        }
        return new Response(ResponseStatus.OK, "В коллекции нет доступных элементов для удаленя");
    }

    @Override
    public String getCommandName() {
        return "remove_lower";
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public MusicBand getMusicband() {
        return musicBand;
    }

    @Override
    public Integer getIntArgument() {
        return null;
    }
}
