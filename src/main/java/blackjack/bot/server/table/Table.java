package blackjack.bot.server.table;

import blackjack.bot.server.box.Box;
import blackjack.bot.server.box.eenum.BoxStatus;
import blackjack.bot.server.exception.BoxDoNotExistsException;

import java.util.List;
import java.util.Optional;

public class Table {

    private Long id;
    private String casinoName;
    private List<Box> boxInTable;

    public Table(Long id, String casinoName) {
        this.id = id;
        this.casinoName = casinoName;
    }

    public void openNewBox(Box box) {
        boxInTable.add(box);
    }

    public void closeBox(Long id) {
        Box box = findBoxById(id);
        box.setStatus(BoxStatus.CLOSED);
    }

    public void holdBox(Long id) {
        Box box = findBoxById(id);
        box.setStatus(BoxStatus.ON_HOLD);
    }

    public void splitBox(Long id) {
        Box box = findBoxById(id);
        Box newBox = box.splitAndCreateNewBox();
        boxInTable.add(newBox);
    }

    public Long getId() {
        return id;
    }

    public String getCasinoName() {
        return casinoName;
    }

    public List<Box> getBoxInTable() {
        return boxInTable;
    }

    private Box findBoxById(Long id) {
        Optional<Box> optBox = boxInTable.stream().filter(box -> box.getId().equals(id)).findFirst();
        if (optBox.isPresent()) {
            return optBox.get();
        }
        throw new BoxDoNotExistsException("Box with id: " + id + " on table: "
                + this.id + " Is not Exists!");
    }

    private void removeBoxById(Long id) {
        boxInTable.remove(findBoxById(id));
    }


}
