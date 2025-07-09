package domain;

import java.io.File;

import org.apache.ibatis.type.Alias;

import controller.attach.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("attach")
public class Attach {
	private String uuid;
	private String path;
	private boolean image;
	private String origin;
//	private Long bno;
//	private Long rno;
	private int odr;
	private long size;
	
	
	public File toFile() {
		return new File(UploadFile.UPLOAD_PATH + "/" + path, uuid);
	}
	
	public Attach toThumb() {
		return Attach.builder().image(image).uuid("t_" + uuid).path(path).origin(origin).odr(odr).size(size).build();
	}
	
	
}
