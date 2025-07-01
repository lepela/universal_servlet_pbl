package schedule;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import controller.attach.UploadFile;
import domain.Attach;
import lombok.extern.slf4j.Slf4j;
import mapper.AttachMapper;
import util.MybatisUtil;

@Slf4j
public class GhostFileCleanupJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		// File 인스턴스 생성 > 어제날짜의 업로드폴더
		File file = new File(UploadFile.UPLOAD_PATH, genYesterdayPath());
		if(!file.exists() || !file.isDirectory()) {
			return;
		}
		
		List<File> fsListFiles = new ArrayList<>(Arrays.asList(file.listFiles()));
		SqlSession session = MybatisUtil.getSqlSession();
		
		// 현재의 이슈
		// dbListFiles에는 thumbnail 파일에 대한 추가가 되지 않음
		// 이미지파일2개 일반 파일1개로 구성된 총 3개의 attach라면
		List<Attach> attachs = new ArrayList<>(session.getMapper(AttachMapper.class).selectYesterdayList());
		//log.info("attachs : {} ", attachs);
		log.info("============= attachs =============");
		attachs.forEach(a -> log.info("{}, ", a));
		List<Attach> thumbs = new ArrayList<>(attachs).stream().filter(Attach::isImage).map(Attach::toThumb).toList();
		log.info("============= thumbs =============");
		thumbs.forEach(a -> log.info("{}, ", a));
		//log.info("thumbs : {} ", thumbs);
		// 이미지파일2개 + 섬네일2개 + 일반파일1개로 구성된 총 5개의 attachlist로 변경되어야함.
		attachs.addAll(thumbs);
		log.info("============= attachs =============");
		attachs.forEach(a -> log.info("{}, ", a));
		//log.info("attachs : {} ", attachs);
		// 체이닝을 통해 한꺼번에 처리하기보단 List<Attach>상태에서 추가작업 후 추후에 List<File>로 변경하길 추천
		List<File> dbListFiles = attachs.stream().map(Attach::toFile).toList();
		log.info("============= 삭제하지 말아야할 파일 =============");
		dbListFiles.forEach(a -> log.info("{}, ", a));
		//log.info("dbListFiles : {} ", dbListFiles);
		session.close();
		fsListFiles.removeAll(dbListFiles);
		log.info("============= 삭제 대상 파일들 =============");
		fsListFiles.forEach(a -> log.info("{}, ", a));
		
		fsListFiles.forEach(f -> f.delete());
		
	}
	private String genYesterdayPath() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime() - 1000 * 60 * 60 * 24);
	}
	
	public static void main(String[] args) throws Exception{
		new GhostFileCleanupJob().execute(null);
	}
	
}
