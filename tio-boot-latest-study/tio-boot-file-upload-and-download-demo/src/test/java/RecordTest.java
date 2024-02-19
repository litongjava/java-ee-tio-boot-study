import org.junit.Test;

import com.litongjava.data.utils.UUIDUtils;
import com.litongjava.jfinal.plugin.activerecord.Record;
import com.litongjava.tio.boo.demo.file.model.FileSaveResult;

public class RecordTest {

  @Test
  public void testFromBean() {
    FileSaveResult fileSaveResult = new FileSaveResult();
    fileSaveResult.setId(UUIDUtils.random());
    fileSaveResult.setSaveFolder("upload");
    fileSaveResult.setSuffixName("jpg");
    fileSaveResult.setFilename("my-photo.jpg");

    Record record = Record.fromBean(fileSaveResult);
    System.out.println(record);

  }

  @Test
  public void TestToBean() {

  }
}
