import com.xkkj.common.util.jwt.RsaUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CREATE BY YB ON 2019/12/26 14:21
 */
public class RsaUtilsTest {

    private String privateFilePath = "D:\\Document\\IDEA\\all_out_path\\security\\rsa";
    private String publicFilePath = "D:\\Document\\IDEA\\all_out_path\\security\\rsa.pub";

    /**
     * 生成公钥和私钥
     * @throws Exception
     */
    @Test
    public void generateKey() throws Exception {
        RsaUtils.generateKey(publicFilePath,privateFilePath,"yangbo",2048);
    }

    /**
     * 得到公钥
     */
    @Test
    public void getPublicKey() throws Exception {
        System.out.println(RsaUtils.getPublicKey(publicFilePath));
    }

    /**
     * 得到私钥
     */
    @Test
    public void getPrivateKey() throws Exception {
        System.out.println(RsaUtils.getPrivateKey(privateFilePath));
    }


}