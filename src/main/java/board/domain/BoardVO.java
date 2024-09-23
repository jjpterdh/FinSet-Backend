package board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVO {
    private Long bno;                       // Corresponds to bno
    private Long uno;                   // Corresponds to uno
    private String content;
    private String writer;// Corresponds to content
    private Date createdAt;                 // Corresponds to createdAt
    private Date updatedAt;                 // Corresponds to updatedAt
    private List<BoardAttachmentVO> attaches;

    // Optional: You can include title if needed for future enhancements
    // private String title;
}
