public class stringbuffer {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        sb.append("hello");
        sb.append("   ");

        String str = sb.toString();

        System.out.println(str);
    }
}
