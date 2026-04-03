package com.ktdsuniversity.edu.files.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.vo.request.UploadVO;

// 만들었는데 용도가 애매하다 ==> Controller, Service, Mapper모두 아니다.
// @Component ==> 객체를 만들어 bean컨테이너에 넣는다.

// @Service : Component를 상속해서 만든 annotation이다.
// @Mapper는 @Component가 없는 이유? ==> SpringFramework가 MyBatis를 공식적으로 지원하지 않는다.
@Component
public class MultipartFileHandler {

	@Autowired
	private FilesDao filesDao;
		
	public void upload(List<MultipartFile> attachFiles,
			           String fileGroupId) {
		if(attachFiles != null && attachFiles.size() > 0) { // ==> 업로드한 파일이 있으면
			for(int i =0; i < attachFiles.size(); i++){//setFileNum에서 수를 넣어줘야하기에 index로 변경./*MultipartFile uploadedFile: attachFiles*/
				
				// 업로드를 하지 않았는데 했다고 판단한 경우에는 다음 반복으로 넘어가라.
				if(attachFiles.get(i).isEmpty()) {
					continue;
				}
				
				// 파일 실제이름 난독화
				//UUID ==> 현재 시간을 기준으로 난수화 된 값을 가져오는 방법.
				// 전세계에서 동시에 발급받더라도 절대로 중복이 일어나지 않는다.
				String obfuscateName = UUID.randomUUID().toString();
				
				// 업로드한 파일이 서버 컴퓨터의 파일 시스템에 저장되도록 한다.
				File storeFile = new File("C:\\uploadFiles",
										   obfuscateName);
						/*attachFiles.get(i).getOriginalFilename());*/
				if(!storeFile.getParentFile().exists()) { // C:\\uploadFiles가 없으면 생성해라.
					storeFile.getParentFile().mkdirs();
				}
				
					try {
						attachFiles.get(i).transferTo(storeFile); //사용자가 업로드한 파일(uploadedFile)을 storeFile에 저장해라.

						// FILES 테이블에 첨부파일 데이터를 INSERT
						UploadVO uploadVO = new UploadVO();
						String filename = attachFiles.get(i).getOriginalFilename(); // i번째 file의 이름을 그대로 가져옴.
						String ext = filename.substring(filename.lastIndexOf(".") + 1); // filename의 .뒤를 가져옴 ==> 확장자명
//					uploadVO.setFileNum( i + 1 );	
					// 새롭게 등록되는 게시글의 아이디를 지금은 알 수 없다.
					// BoardDaoMapper참고. 이제는 알 수 있다.
					//(BoardDaoMapper에서 insert전에 SELECT로 id를 만들어 writeVO의 id값에 넣어줬음.)
					uploadVO.setFileGroupId(fileGroupId); 
					uploadVO.setObfuscateName(obfuscateName);
					uploadVO.setDisplayName(filename);
					uploadVO.setExtendName(ext);
					uploadVO.setFileLength(storeFile.length()); // 실제 경로에 업로드된 파일의 크기
					uploadVO.setFilePath(storeFile.getAbsolutePath()); // 실제 경로에 저장된 파일의 절대 경로
					
					this.filesDao.insertAttachFile(uploadVO); // 만든 file DB에 보내기 위한 작업
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}// DB에 넣지않아서 DB에는 안들어가지만 파일은 업로드가 된다.
					
			}
		}
	}
}
