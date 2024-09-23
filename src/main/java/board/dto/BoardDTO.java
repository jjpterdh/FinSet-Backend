package board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import board.domain.BoardAttachmentVO;
import board.domain.BoardVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private Long bno;          // Corresponds to bno
    private Long uno;     // Corresponds to uno
    private String content;   // Corresponds to content
    private Date createdAt;   // Corresponds to createdAt
    private Date updatedAt;   // Corresponds to updatedAt

    private List<BoardAttachmentVO> attaches;
    List<MultipartFile> files = new ArrayList<>();

    public static BoardDTO of(BoardVO vo) {
        return BoardDTO.builder()
                .bno(vo.getBno())
                .content(vo.getContent())
                .uno(vo.getUno())  // Add this line to map userNo
                .createdAt(vo.getCreatedAt())
                .updatedAt(vo.getUpdatedAt())
                .attaches(vo.getAttaches())
                .build();
    }

    public BoardVO toVo() {
        return BoardVO.builder()
                .bno(bno)
                .content(content)
                .uno(uno)  // Add this line to map userNo
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .attaches(attaches)
                .build();
    }
}
