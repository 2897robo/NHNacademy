import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

public class TestSimple1 {
    public static int runTest(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while(matcher.find()) {     // find() 호출 시에만 하나씩 찾는다.
            matches++;
        }

        return matches;
    }

    @Test
    public void givenText_whenSimpleRegexMatches_thenCorrect() {
        String source = "foo";
        Pattern pattern = Pattern.compile(source);
        Matcher matcher = pattern.matcher("foofoo");

        assertTrue(matcher.find());
    }
    

    @Test
    public void givenText_whenSimpleRegexMatchesTwice_thenCorrect() {
        String source = "foofoo";
        Pattern pattern = Pattern.compile("foo");
        Matcher matcher = pattern.matcher(source);

        int matches = 0;
        while(matcher.find()) {
            matches++;
        }

        assertEquals(matches, 2);
    }

    // 메타 문자
    @Test
    public void givenText_whenMatchesWithDotMetach_thenCorrect() {
        int matches = runTest(".", "foo");     // . 은 임의의 문자로 인식하고, 모든 문자와 일치한다

        assertTrue(matches > 0);
    }

    @Test
    public void givenORSet_whenMatchesAny_thenCorrect() {
        int matches = runTest("[abc]", "b");

        assertEquals(matches, 1);
    }

    @Test
    public void givenORSet_whenMatchesAnyAndAll_thenCorrect() {
        int matches = runTest("[abc]", "cab");

        assertEquals(matches, 3);
    }

    @Test
    public void givenORSet_whenMatchesAllCombinations_thenCorrect() {
        int matches = runTest("[bcr]at", "bat cat rat");
        //시작은 b,c,r 중 하나, 뒤는 at으로 끝나면 된다.

        assertEquals(matches, 3);
    }

    @Test
    public void givenNORSet_whenMatchesNon_thenCorrect() {
        int matches = runTest("[^abc]", "g");

        assertTrue(matches > 0);
    }

    @Test
    public void givenNORSet_whenMatchesAllExceptElements_thenCorrect() {
        int matches = runTest("[^bcr]at", "sat mat eat");
        // b,c,r 로 시작되지 않는것

        assertTrue(matches > 0);
    }

    @Test
    public void givenUpperCaseRange_whenMatchesUpperCase_thenCorrect() {
        int matches = runTest(
        "[A-Z]", "Two Uppercase alphabets 34 overall");
        // 해당 문장에서 A~Z 대문자가 몇개가 있는가

        assertEquals(matches, 2);
    }

    @Test
    public void givenLowerCaseRange_whenMatchesLowerCase_thenCorrect() {
        int matches = runTest(
        "[a-z]", "Two Uppercase alphabets 34 overall");
        // 해당 문장에서 a~z 소문자가 몇개가 있는가

        assertEquals(matches, 26);
    }

    @Test
    public void givenBothLowerAndUpperCaseRange_whenMatchesAllLetters_thenCorrect() {
        int matches = runTest(
        "[a-zA-Z]", "Two Uppercase alphabets 34 overall");

        assertEquals(matches, 28);
    }

    @Test
    public void givenTwoSets_whenMatchesUnion_thenCorrect() {
        int matches = runTest("[1-3[7-9]]", "123456789");

        assertEquals(matches, 6);
    }

    @Test
    public void givenTwoSets_whenMatchesIntersection_thenCorrect() {
        int matches = runTest("[1-6&&[3-9]]", "123456789");

        assertEquals(matches, 4);
    }

    @Test
    public void givenOneOrManyQuantifier_whenMatches_thenCorrect() {
        int matches = runTest("\\a+", "hi");

        assertFalse(matches > 0);
    }

    @Test
    public void givenBraceQuantifierWithRange_whenMatches_thenCorrect() {
        int matches = runTest("a{2,3}", "aaaa");

        assertEquals(matches, 1);
    }

    @Test
    public void givenCapturingGroup_whenMatchesWithBackReference_thenCorrect2() {
        int matches = runTest("(\\d\\d)\\1\\1\\1", "12121212");
        // 앞의 것이 같은 패턴이면 된다. 12 가 4번
        // (\\d\\d)(ab)\\2\\1 "12abab1212"  도 가능

        assertEquals(matches, 1);
    }

    @Test
    public void givenText_whenMatchesAtWordBoundary_thenCorrect() {
        int matches = runTest("\\bdog\\b", "a dog is friendly");

        assertTrue(matches > 0);
    }

    @Test
    public void givenText_integer() {
        int matches = runTest("\\b[+-]?[0-9]{1,10}\\b", "123456789");

        assertTrue(matches > 0);
    }
}