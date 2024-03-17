package Commands;

import BaseClasses.MusicBand;
import Collection.CollectionManager;

public class UpdateIdCommand implements Command {
    CollectionManager cm;

    public UpdateIdCommand(CollectionManager cm) {
        this.cm = cm;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            try {
                int id = Integer.parseInt(args[1]);
                boolean flag = false;
                for (MusicBand musicBand : cm.getMusicBands()) {
                    if (musicBand.getId() == id) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    MusicBand musicBand = cm.getClientManager().getMusicBand();
                    cm.updateId(musicBand, id);
                } else {
                    System.out.println("Элемента с таким id нет в коллекции. ");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Введены неверные аргументы. Попробуйте еще раз. ");
            }
        } else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }

    @Override
    public String getDescription() {
        return "update id {element}: обновить значение элемента коллекции, id которого равен заданному";
    }
}
