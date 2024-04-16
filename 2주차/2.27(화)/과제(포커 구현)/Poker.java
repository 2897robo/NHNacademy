/*
1. 카드 클래스를 만들어 52장 	(무늬, 번호)
2. 섞는다. Collections 에 있는 셔플 사용
3. 2~10명이 플레이한다.
4. 유저 명수를 입력 (유저 클래스 구현)
5. 인당 5장 씩 나눠준다.
6. 승부를 가린다.
7. 승자 1명을 출력한다.

-- 승리조건 --
2, 3, 4, 5, 6, 7, 8, 9, 10, j, q, k, a
하이카드 < 원페어 < 투페어
 */

import java.util.*;

class Card {
    String suit;  // 카드의 무늬
    int rank;  // 카드의 숫자

    public Card(String suit, int rank) {
        this.suit = suit;
        if (rank == 1) {  // A를 가장 높은 카드로 설정
            this.rank = 14;
        } else {
            this.rank = rank;
        }
    }

    // 카드의 숫자를 문자로 변환하는 메소드
    public String getRankString() {
        switch (rank) {
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            case 14:
                return "A";
            default:
                return String.valueOf(rank);
        }
    }
}

class Player {
    String name;  // 플레이어의 이름
    List<Card> cards = new ArrayList<>();  // 플레이어가 가진 카드

    public Player(String name) {
        this.name = name;
    }

    // 플레이어에게 카드를 주는 메소드
    public void receiveCard(Card card) {
        cards.add(card);
    }

    // (하이카드) 플레이어가 가진 카드 중 가장 높은 숫자를 반환하는 메소드
    public int highestCard() {
        int maxRank = 0;
        for (Card card : cards) {
            if (card.rank > maxRank) {
                maxRank = card.rank;
            }
        }
        return maxRank;
    }

    // (원페어 < 투페어) 플레이어가 가진 카드 중 동일 숫자의 카드 쌍의 개수를 반환하는 메소드
    public int[] countPairs() {
        int[] counts = new int[15];
        for (Card card : cards) {
            counts[card.rank]++;
        }
        int onePair = 0;
        int twoPair = 0;
        for (int count : counts) {
            if (count == 2) {
                onePair++;
            } else if (count >= 3) {
                twoPair++;
            }
        }
        return new int[] {onePair, twoPair};
    }

    // 플레이어가 가진 원페어의 숫자 값을 반환하는 메소드
    public int getPairRank() {
        int[] counts = new int[15];
        for (Card card : cards) {
            counts[card.rank]++;
        }
        for (int i = 14; i >= 2; i--) {  // 숫자가 큰 카드부터 확인
            if (counts[i] == 2) {  // 해당 숫자의 카드가 2장이면
                return i;  // 해당 숫자를 반환
            }
        }
        return 0;  // 원페어가 없는 경우
    }

    // 플레이어가 가진 카드를 출력하는 메소드
    public void printCards() {
        System.out.println("--------------------------");
        System.out.println(name + "'s cards:");
        for (Card card : cards) {
            System.out.println(card.suit + " " + card.getRankString());
        }
        System.out.println("--------------------------");
    }
}

public class Poker {
    public static void main(String[] args) {
        List<Card> deck = new ArrayList<>();  // 카드 덱
        String[] suits = {"Spade", "Heart", "Diamond", "Clover"};  // 카드의 무늬
        for (String suit : suits) {
            for (int i = 1; i <= 13; i++) {
                deck.add(new Card(suit, i));  // 1. 카드 클래스를 만들어 52장 	(무늬, 번호)
            }
        }
        Collections.shuffle(deck);  // 2. 섞는다. Collections 에 있는 셔플 사용

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of players (2-10): ");
        int numPlayers = sc.nextInt();  // 4. 유저 명수를 입력 (유저 클래스 구현)
        sc.close();     

        if(numPlayers<2 || numPlayers>10) {     // 3. 2~10명이 플레이한다.
            System.out.println("Invalid number of players entered. Exits game.");
            return;
        }

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1)));  // 플레이어를 생성하여 리스트에 추가
        }

        for (int i = 0; i < 5; i++) {
            for (Player player : players) {
                player.receiveCard(deck.remove(deck.size() - 1));  // 5. 인당 5장 씩 나눠준다.
            }
        }

        for (Player player : players) {
            player.printCards();  // 각 플레이어가 가진 카드를 출력
        }

        // 6. 승부를 가린다.
        Player winner = players.get(0);  // 첫 번째 플레이어를 일단 승자로 설정
        int[] maxPairs = winner.countPairs();  // 첫 번째 플레이어의 페어 수
        int maxPairRank = winner.getPairRank();  // 첫 번째 플레이어의 원페어 숫자 값
        for (Player player : players) {
            int[] playerPairs = player.countPairs();  // 현재 플레이어의 페어 수
            int playerPairRank = player.getPairRank();  // 현재 플레이어의 원페어 숫자 값

                // 현재 플레이어의 투페어가 더 많거나
            if (playerPairs[1] > maxPairs[1] ||  
                // 투페어 수가 같고 원페어가 더 많거나
                (playerPairs[1] == maxPairs[1] && playerPairs[0] > maxPairs[0]) ||  
                // 투페어와 원페어 수가 같고 원페어의 숫자 값이 더 높거나
                (playerPairs[1] == maxPairs[1] && playerPairs[0] == maxPairs[0] && playerPairRank > maxPairRank) ||  
                // 투페어와 원페어 수가 같고 원페어의 숫자 값도 같고 현재 플레이어의 가장 높은 카드가 더 높으면
                (playerPairs[1] == maxPairs[1] && playerPairs[0] == maxPairs[0] && playerPairRank == maxPairRank && player.highestCard() > winner.highestCard())) {  
                winner = player;  // 승자를 변경
                maxPairs = playerPairs;
                maxPairRank = playerPairRank;
            }
        }

        System.out.println("Winner is " + winner.name);  // 7. 승자 1명을 출력한다.
    }
}