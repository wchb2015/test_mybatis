import com.lvmama.utils.DateUtils;

import java.util.Date;

/**
 * Created by wangchongbei on 15-12-29.
 */
public class Test {

    @org.junit.Test
    public void testDate() {
        DateUtils.getDateAfter2359Date(new Date(), 1);
        System.out.println("haha");
    }

}
