public class Birthday {
    public static void main(String[] args) {
        BoyFriends boys = new BoyFriends();
        boys.add(new BoyFriend("Ham", 22));
        boys.add(new BoyFriend("Kim", 24));
        boys.add(new BoyFriend("John", 28));

        for(BoyFriend b:boys) {
            b.hear("100원씩 줘");
        }
    }
}
