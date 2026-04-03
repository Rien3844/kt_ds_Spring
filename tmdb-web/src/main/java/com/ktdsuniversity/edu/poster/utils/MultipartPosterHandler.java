package com.ktdsuniversity.edu.poster.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.poster.dao.PosterDao;
import com.ktdsuniversity.edu.poster.vo.request.UploadVO;

@Component
public class MultipartPosterHandler {
	@Autowired
	private PosterDao posterDao;

	public void upload(List<MultipartFile> attachPoster, String posterGroupId) {
		if (attachPoster != null && attachPoster.size() > 0) // ==> 업로드한 파일이 있으면
			for (int i = 0; i < attachPoster.size(); i++) {

				// 업로드를 하지 않았는데 했다고 판단한 경우에는 다음 반복으로 넘어가라.
				if (attachPoster.get(i).isEmpty()) {
					continue;
				}
				// 파일 실제이름 난독화
				// UUID ==> 현재 시간을 기준으로 난수화 된 값을 가져오는 방법.
				// 전세계에서 동시에 발급받더라도 절대로 중복이 일어나지 않는다.
				String obfuscateName = UUID.randomUUID().toString();

				// 업로드한 파일이 서버 컴퓨터의 파일 시스템에 저장되도록 한다.
				File storeImg = new File("C:\\uploadFiles", obfuscateName);

				if (!storeImg.getParentFile().exists()) {
					storeImg.getParentFile().mkdirs();
				}

				try {
					attachPoster.get(i).transferTo(storeImg);

					// FILES 테이블에 첨부파일 데이터를 INSERT
					UploadVO uploadVO = new UploadVO();
					String posterName = attachPoster.get(i).getOriginalFilename();
					String ext = posterName.substring(posterName.lastIndexOf(".") + 1);
					uploadVO.setPosterNum(i + 1);
					uploadVO.setPosterGroupId(posterGroupId);
					uploadVO.setObfuscateName(obfuscateName);
					uploadVO.setDisplayName(posterName);
					uploadVO.setExtendName(ext);
					uploadVO.setPosterLength(storeImg.length()); // 실제 경로에 업로드된 파일의 크기
					uploadVO.setPosterPath(storeImg.getAbsolutePath()); // 실제 경로에 저장된 파일의 절대 경로

					this.posterDao.insertAttachPoster(uploadVO);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
	}
}
