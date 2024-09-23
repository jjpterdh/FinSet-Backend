package board.domain;

import common.util.UploadFiles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import common.util.UploadFiles;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j
public class BoardAttachmentVO {
    private Long no;
    private Long bno;

    private String path;
    private String contentType;
    private Long size;
    private Date regDate;


    public static BoardAttachmentVO of(MultipartFile part, Long bno, String path) {
        return builder()
                .bno(bno)

                .path(path)
                .contentType(part.getContentType())
                .size(part.getSize())
                .build();
    }
    public String getFileSize() {
        return UploadFiles.getFormatSize(size);
    }
}

