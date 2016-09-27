package friends;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FriendsTest {

    @Test
    public void simple() {
        assertEquals("{ABC}", Friends.resolve("{ABC}"));
    }

    @Test
    public void union() {
        assertEquals("{ABCDEFGZ}", Friends.resolve("{ABC}+{DEFG}+{Z}+{}"));
    }

    @Test
    public void intersection() {
        assertEquals("{AB}", Friends.resolve("{ABE}*{ABCD}"));
    }

    @Test
    public void difference() {
        assertEquals("{ABD}", Friends.resolve("{ABCD}-{CZ}"));
    }

    @Test
    public void precendence1() {
        assertEquals("{ABCE}", Friends.resolve("{ABC}+{CDE}*{CEZ}"));
    }

    @Test
    public void precendence2() {
        assertEquals("{CE}", Friends.resolve("({ABC}+{CDE})*{CEZ}"));
    }

}
