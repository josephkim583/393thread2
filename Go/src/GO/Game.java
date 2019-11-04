package GO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Game implements GameInterface {
    Player playerOne;
    Player playerTwo;
    Player currentPlayer;
    int consecutivePass = 0;
    int numberOfPlayers = 0;
    boolean registered;
    ArrayList<Board> boardHistory = new ArrayList<>();
    RuleChecker ruleChecker = new RuleChecker();

    public Game(){
        Board emptyBoard = new Board();
        boardHistory.add(emptyBoard);
    }

    @Override
    public String registerPlayer(String name) throws IOException {
        if (numberOfPlayers == 0){
            Stone blackStone = new Stone("B");
            playerOne = new Player(name, blackStone);
            currentPlayer = playerOne;
            numberOfPlayers++;
            return "B";
        } else if (numberOfPlayers == 1){
            Stone whiteStone = new Stone("W");
            playerOne = new Player(name, whiteStone);
            numberOfPlayers++;
            return "W";
        }
        return "Trying to add more than 2 players";
    }

    @Override
    public Object[] pass() throws Exception {
        JSONArray returnHistory = makeCopyofCurrentState();

        consecutivePass++;
        if (consecutivePass == 2) {
            return endGame().toArray();
        }
        alternatePlayer();

        return returnHistory.toArray();
    }

    @Override
    public Object[] makeMove(Point point) throws Exception {
        JSONArray returnHistory = makeCopyofCurrentState();

        consecutivePass = 0;
        Stone currentStone = currentPlayer.getPlayerStone();
        if (!(boolean)ruleChecker.moveCheck(currentStone, point, boardHistory).get(0)) {
            return endGame().toArray();
        }
        Board newBoard = new Board(boardHistory.get(0));
        newBoard.place(currentStone, point);
        if (boardHistory.size() == 3) {
            boardHistory.remove(2);
        }
        boardHistory.add(0, newBoard);
        alternatePlayer();

        return returnHistory.toArray();
    }

    JSONArray endGame() throws Exception {
        RuleChecker ruleChecker = new RuleChecker();
        JSONObject scores = ruleChecker.getScore(boardHistory.get(0));
        JSONArray jsonArray = new JSONArray();
        int blackScore = (int)scores.get("B");
        int whiteScore = (int)scores.get("W");
        if (blackScore > whiteScore){
            jsonArray.add(playerOne.getPlayerName());
        }
        else if (whiteScore > blackScore){
            jsonArray.add(playerTwo.getPlayerName());
        }
        else {
            if (playerOne.getPlayerName().compareTo(playerTwo.getPlayerName()) > 0){
                jsonArray.add(playerOne.getPlayerName());
                jsonArray.add(playerTwo.getPlayerName());
            }
            else {
                jsonArray.add(playerTwo.getPlayerName());
                jsonArray.add(playerOne.getPlayerName());
            }
        }
        return  jsonArray;
    }

    void alternatePlayer() {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

    private JSONArray makeCopyofCurrentState(){
        JSONArray currentHistory = new JSONArray();
        for (Board board : boardHistory){
            Board boardCopy = new Board(board);
            currentHistory.add(boardCopy.board);
        }
        return (currentHistory);
    }
}
