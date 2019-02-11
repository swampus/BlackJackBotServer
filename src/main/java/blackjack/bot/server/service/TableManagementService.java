package blackjack.bot.server.service;

import blackjack.bot.server.storage.model.Casino;
import blackjack.bot.server.table.Table;
import org.springframework.stereotype.Service;

@Service
public class TableManagementService {

    public Table createTable(Casino casino, long tableId) {
        return new Table(tableId, casino.getName());
    }


}
