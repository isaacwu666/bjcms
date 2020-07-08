package tomweb.xyz.bjcms.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tomweb.xyz.bjcms.pojo.BjAccount;

import static org.junit.jupiter.api.Assertions.*;

class BaiJiaHaoUtilsTest {

    BjAccount bjAccount=null;
    @BeforeEach
    void setUp() {
        bjAccount=new BjAccount();
        bjAccount.setAppId("1663934735086891");
        bjAccount.setAppToken("30b639fc41348753f55b80a2980bb30e");
        bjAccount.setServerUrl("https://bjh.tomweb.xyz");
        bjAccount.setServerToken("sciacEBTfgvKpKGsbd2GcZIRnBjHgPPu");
        bjAccount.setServerEncodingAESKey("ZRpWPRmLImsqNIGtu62Kemmgv48GzS8h9ICK1PSsWRq");
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getArticleList() {
        System.out.println(bjAccount.toString());
    }
}