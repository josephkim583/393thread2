//package GO;
//
//import java.net.*;
//import java.io.*;
//
//import org.json.simple.JSONArray;
//
//import java.util.ArrayList;
//
//public class testDriver7 {
//    public static void main(String[] args) throws Exception {
//        InputParser input = new InputParser();
//        ArrayList<Object> parsedInput = input.parser();
//        JSONArray outputArray = new JSONArray();
//        ProxyPlayer proxyPlayer = new ProxyPlayer(8152);
//
//        for (Object parse : parsedInput) {
//            JSONArray commandArray = ((JSONArray) parse);
//            String command = commandArray.get(0).toString();
//            switch (command) {
//                case ("register"): {
//                    String registered = proxyPlayer.register();
//                    outputArray.add(registered);
//                    break;
//                }
////                case ("receive-stones"): {
////                    Stone playerStone = new Stone(((JSONArray) parse).get(1).toString());
////                    thisGame.receiveStones(playerStone);
////                    break;
////                }
////                case ("make-a-move"): {
////                    ArrayList<Board> boards = new ArrayList<Board>();
////                    JSONArray boardJSONArray = (JSONArray) ((JSONArray) parse).get(1);
////                    for (int i = 0; i < boardJSONArray.size(); i++) {
////                        Board temp = new Board(input.parseJSONboard((JSONArray) boardJSONArray.get(i)));
////                        boards.add(temp);
////                    }
////                    String move = thisGame.makeAMove(boards, 1);
////                    outputArray.add(move);
////                }
//            }
//        }
//        System.out.println(outputArray);
//    }
//}
